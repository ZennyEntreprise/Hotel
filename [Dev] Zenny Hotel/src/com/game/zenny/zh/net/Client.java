package com.game.zenny.zh.net;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import com.game.zenny.zh.logger.LogType;
import com.game.zenny.zh.logger.Logger;
import com.game.zenny.zh.net.packet.Packet;
import com.game.zenny.zh.net.packet.PacketDestination;
import com.game.zenny.zh.net.packet.disconnect.DisconnectPacket;
import com.game.zenny.zh.net.packet.login.LoginPacket;

public abstract class Client extends Bridge {

	//// OBJECT
	// -- CLIENT
	private InetAddress serverAddress;
	private int serverPort;

	/**
	 * @param serverAddress
	 * @param serverPort
	 * @throws SocketException
	 */
	public Client(InetAddress serverAddress, int serverPort) throws SocketException {
		super(new DatagramSocket(), "");

		Logger.log(this, LogType.INFO, "Creating client...");
		this.serverAddress = serverAddress;
		Logger.log(this, LogType.INFO, "Server address defined on : " + serverAddress.getHostName());
		this.serverPort = serverPort;
		Logger.log(this, LogType.INFO, "Server port defined on : " + serverPort);
		Logger.log(this, LogType.INFO, "Client created !");
	}

	/**
	 * @return the serverAddress
	 */
	public InetAddress getServerAddress() {
		return serverAddress;
	}

	/**
	 * @param serverAddress
	 *            the serverAddress to set
	 */
	public void setServerAddress(InetAddress serverAddress) {
		this.serverAddress = serverAddress;
	}

	/**
	 * @return the serverPort
	 */
	public int getServerPort() {
		return serverPort;
	}

	/**
	 * @param serverPort
	 *            the serverPort to set
	 */
	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}

	// -- CONNECTION

	/**
	 * @param clientID
	 */
	public void connect(String playerIdentifier) {
		Logger.log(this, LogType.NORMAL, "Trying to connect with that clientID: " + playerIdentifier);
		sendPacket(new LoginPacket(Packet.buildDatasObject(playerIdentifier), getIdentifier(), PacketDestination.TO_SERVER.getPacketDestination()));
	}

	public void disconnect() {
		sendPacket(new DisconnectPacket(Packet.buildDatasObject(), getIdentifier(), PacketDestination.TO_SERVER.getPacketDestination()));
		Logger.log(this, LogType.NORMAL, "Disconnecting...");
	}
	
	// -- PACKET

	/**
	 * @param packet
	 * @param player
	 */
	@Override
	public abstract void packetAction(Packet packet, String fromPlayerIdentifier);

	/**
	 * @param packet
	 */
	public void sendPacket(Packet packet) {
		getSender().sendPacket(packet, this.serverAddress, this.serverPort);
	}

}
