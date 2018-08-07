package com.game.zenny.zh.net;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.DatagramPacket;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.game.zenny.zh.net.logger.LogType;
import com.game.zenny.zh.net.logger.Logger;
import com.game.zenny.zh.net.packet.Packet;

public class ClientReceivier extends Thread {

	//// OBJECT
	// -- RECEIVER
	private Bridge bridge;

	/**
	 * @param bridge
	 */
	public ClientReceivier(Bridge bridge) {
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
			String fromUserIdentifier = getFromUserIdentifier(json);
			String toUserIdentifier = getToUserIdentifier(json);
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
				packet = (Packet) packetClassConstructor.newInstance((Object) datas, fromUserIdentifier,
						toUserIdentifier);
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

			bridge.packetAction(packet, fromUserIdentifier);
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
	 * @return from user identifier contained in json
	 */
	public String getFromUserIdentifier(JSONObject json) {
		try {
			return (String) json.get("fromUserIdentifier");
		} catch (NullPointerException e) {
			return "unknown";
		}
	}

	/**
	 * @param json
	 * @return to user identifier contained in json
	 */
	public String getToUserIdentifier(JSONObject json) {
		try {
			return (String) json.get("toUserIdentifier");
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
