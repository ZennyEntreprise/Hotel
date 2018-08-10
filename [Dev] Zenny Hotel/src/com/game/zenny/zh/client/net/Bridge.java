package com.game.zenny.zh.client.net;

import java.net.DatagramSocket;
import java.util.Map.Entry;

import com.game.zenny.zh.client.logger.LogType;
import com.game.zenny.zh.client.logger.Logger;
import com.game.zenny.zh.client.net.packet.Packet;
import com.game.zenny.zh.client.net.packet.UnknownPacket;
import com.game.zenny.zh.client.net.packet.appartment.AppartmentToGoPacket;
import com.game.zenny.zh.client.net.packet.appartment.GoIntoAppartmentPacket;
import com.game.zenny.zh.client.net.packet.disconnect.DisconnectPacket;
import com.game.zenny.zh.client.net.packet.login.ErrorLoginPacket;
import com.game.zenny.zh.client.net.packet.login.LoginPacket;
import com.game.zenny.zh.client.net.packet.login.ValidLoginPacket;

public abstract class Bridge {

	//// STATIC
	public static int defaultPort = 4000;

	//// OBJECT
	// -- BRIDGE
	private String identifier;
	private DatagramSocket socket;
	private Sender sender;
	private Receiver receiver;

	/**
	 * @param socket
	 * @param identifier
	 */
	public Bridge(DatagramSocket socket, String identifier) {
		setIdentifier(identifier);

		this.socket = socket;

		Logger.log(this, LogType.INFO, "Adding default packets...");
		registerPackets();
		Logger.log(this, LogType.INFO, Packet.getPackets().size() + " default packets added : ");

		for (Entry<Long, Class<?>> packet : Packet.getPackets().entrySet())
			Logger.log(this, LogType.INFO, " - " + packet.getValue().getSimpleName());

		sender = new Sender(this);
		receiver = new Receiver(this);
		receiver.start();
	}

	/**
	 * @return identifier
	 */
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * @param identifier
	 */
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	/**
	 * @return the socket
	 */
	public DatagramSocket getSocket() {
		return socket;
	}

	/**
	 * @param socket
	 *            the socket to set
	 */
	public void setSocket(DatagramSocket socket) {
		this.socket = socket;
	}

	/**
	 * @return the sender
	 */
	public Sender getSender() {
		return sender;
	}

	/**
	 * @param sender
	 *            the sender to set
	 */
	public void setSender(Sender sender) {
		this.sender = sender;
	}

	/**
	 * @return the receiver
	 */
	public Receiver getReceiver() {
		return receiver;
	}

	/**
	 * @param receiver
	 *            the receiver to set
	 */
	public void setReceiver(Receiver receiver) {
		this.receiver = receiver;
	}

	// -- PACKET

	private void registerPackets() {
		Packet.registerPacket(0, UnknownPacket.class);
		Packet.registerPacket(1, LoginPacket.class);
		Packet.registerPacket(2, ValidLoginPacket.class);
		Packet.registerPacket(3, ErrorLoginPacket.class);
		Packet.registerPacket(4, DisconnectPacket.class);
		Packet.registerPacket(5, GoIntoAppartmentPacket.class);
		Packet.registerPacket(6, AppartmentToGoPacket.class);
	}

	/**
	 * @param packet
	 * @param fromPlayer
	 */
	public abstract void packetAction(Packet packet, String fromPlayerIdentifier);

}
