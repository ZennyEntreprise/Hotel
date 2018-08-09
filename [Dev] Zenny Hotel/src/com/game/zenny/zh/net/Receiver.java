package com.game.zenny.zh.net;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.DatagramPacket;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.game.zenny.zh.logger.LogType;
import com.game.zenny.zh.logger.Logger;
import com.game.zenny.zh.net.packet.Packet;

public class Receiver extends Thread {

	//// OBJECT
	// -- RECEIVER
	private Bridge bridge;

	/**
	 * @param bridge
	 */
	public Receiver(Bridge bridge) {
		this.bridge = bridge;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void run() {
		while (true) {
			byte[] buffer = new byte[2048];
			DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);

			try {
				bridge.getSocket().receive(datagramPacket);
			} catch (IOException e) {
				e.printStackTrace();
			}

			String jsonString = new String(datagramPacket.getData()).trim();
			JSONObject json = getJSONObject(jsonString);
			if (json == null)
				continue;

			long packetTypeID = getPacketTypeID(json);
			String fromPlayerIdentifier = getFromPlayerIdentifier(json);
			String toPlayerIdentifier = getToPlayerIdentifier(json);
			JSONArray datasArray = getDatasArray(json);
			if (datasArray == null)
				continue;

			Object[] datas = new Object[datasArray.size()];
			for (int i = 0; i < datasArray.size(); i++) {
				datas[i] = datasArray.get(i);
			}

			Packet packet = null;
			try {
				Class<?> packetClass = Packet.getPacketClassByID(packetTypeID);
				Constructor<?> packetClassConstructor = packetClass.getConstructor(Object[].class, String.class,
						String.class);
				packet = (Packet) packetClassConstructor.newInstance((Object) datas, fromPlayerIdentifier,
						toPlayerIdentifier);
			} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException e) {
				continue;
			}

			JSONObject logJson = new JSONObject();
			logJson.put("fromIP", datagramPacket.getAddress().toString());
			logJson.put("fromPORT", datagramPacket.getPort());
			logJson.put("packetClass", packet.getClass().getName());
			logJson.put("packetJSON", json);
			Logger.log(bridge, LogType.INFO, "RECEIVING PACKET   :   " + logJson.toJSONString());

			Method[] packetEventsMethods = PacketEvents.class.getMethods();
			for (Method packetEventsMethod : packetEventsMethods) {
				if (packetEventsMethod.getName().equals(packet.getClass().getSimpleName()+"ReceiverBefore")) {
					try {
						packetEventsMethod.invoke(((Network) bridge).getPacketEvents());
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
			
			bridge.packetAction(packet, fromPlayerIdentifier);
			
			for (Method packetEventsMethod : packetEventsMethods) {
				if (packetEventsMethod.getName().equals(packet.getClass().getSimpleName()+"ReceiverAfter")) {
					try {
						packetEventsMethod.invoke(((Network) bridge).getPacketEvents());
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	// -- PARSE DATAS

	/**
	 * @param jsonString
	 * @return json object from json string
	 */
	public JSONObject getJSONObject(String jsonString) {
		try {
			return (JSONObject) new JSONParser().parse(jsonString);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * @param json
	 * @return packet id contained in json
	 */
	public long getPacketTypeID(JSONObject json) {
		try {
			return (long) json.get("packetTypeID");
		} catch (NullPointerException | ClassCastException e) {
			return 0;
		}
	}

	/**
	 * @param json
	 * @return from player identifier contained in json
	 */
	public String getFromPlayerIdentifier(JSONObject json) {
		try {
			return (String) json.get("fromPlayerIdentifier");
		} catch (NullPointerException e) {
			return "unknown";
		}
	}

	/**
	 * @param json
	 * @return to player identifier contained in json
	 */
	public String getToPlayerIdentifier(JSONObject json) {
		try {
			return (String) json.get("toPlayerIdentifier");
		} catch (NullPointerException e) {
			return "unknown";
		}
	}

	/**
	 * @param json
	 * @return packet datas array contained in json
	 */
	public JSONArray getDatasArray(JSONObject json) {
		try {
			return (JSONArray) json.get("datas");
		} catch (ClassCastException | NullPointerException e) {
			return null;
		}
	}
}
