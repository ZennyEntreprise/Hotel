package com.game.zenny.zh.client.net.packet;

import java.util.HashMap;
import java.util.Map.Entry;

import org.json.simple.JSONArray;

import com.game.zenny.zh.client.net.Network;

public abstract class Packet {

	//// STATIC
	private static HashMap<Long, Class<?>> packets = new HashMap<Long, Class<?>>();

	/**
	 * @return packet class list
	 */
	public static HashMap<Long, Class<?>> getPackets() {
		return packets;
	}

	/**
	 * @param packetID
	 * @param packetClass
	 */
	public static void registerPacket(long packetTypeID, Class<?> packetClass) {
		packets.put(packetTypeID, packetClass);
	}

	/**
	 * @param packetID
	 * @return
	 */
	public static Class<?> getPacketClassByID(long packetID) {
		for (Entry<Long, Class<?>> packet : packets.entrySet())
			if (packet.getKey() == packetID)
				return packet.getValue();
		return getPacketClassByID(0);
	}

	/**
	 * @param datas
	 * @return
	 */
	public static Object[] buildDatasObject(Object... datas) {
		return datas;
	}

	//// OBJECT
	// -- PACKET
	private String fromPlayerIdentifier, toPlayerIdentifier;

	/**
	 * @param datas
	 * @param fromPlayerIdentifier
	 * @param toPlayerIdentifier
	 */
	public Packet(Object[] datas, String fromPlayerIdentifier, String toPlayerIdentifier) {
		this.fromPlayerIdentifier = fromPlayerIdentifier;
		this.toPlayerIdentifier = toPlayerIdentifier;
	}

	/**
	 * @return packet id
	 */
	public abstract int getPacketTypeID();

	/**
	 * @param datas
	 * @return completed datas
	 */
	public abstract JSONArray build(JSONArray datas);

	/**
	 * @param client
	 * @param fromPlayerIdentifier
	 */
	public abstract void clientReceivedAction(Network network, String fromPlayerIdentifier);

	/**
	 * @return the fromPlayerIdentifier
	 */
	public String getFromPlayerIdentifier() {
		return fromPlayerIdentifier;
	}

	/**
	 * @param fromPlayerIdentifier
	 *            the fromPlayerIdentifier to set
	 */
	public void setFromPlayerIdentifier(String fromPlayerIdentifier) {
		this.fromPlayerIdentifier = fromPlayerIdentifier;
	}

	/**
	 * @return the toPlayerIdentifier
	 */
	public String getToPlayerIdentifier() {
		return toPlayerIdentifier;
	}

	/**
	 * @param toPlayerIdentifier
	 *            the toPlayerIdentifier to set
	 */
	public void setToPlayerIdentifier(String toPlayerIdentifier) {
		this.toPlayerIdentifier = toPlayerIdentifier;
	}

}
