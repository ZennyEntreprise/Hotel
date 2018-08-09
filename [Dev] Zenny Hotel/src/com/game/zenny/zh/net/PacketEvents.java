package com.game.zenny.zh.net;

import com.game.zenny.zh.net.packet.Packet;
import com.game.zenny.zh.net.packet.PacketDestination;
import com.game.zenny.zh.net.packet.appartment.EnterAppartmentPacket;

public class PacketEvents {

	private Network network;
	
	/**
	 * @param network
	 */
	public PacketEvents(Network network) {
		this.network = network;
	}
	
	public void ValidLoginPacketAfter() {
		network.sendPacket(new EnterAppartmentPacket(Packet.buildDatasObject("default"), network.getIdentifier(), PacketDestination.TO_SERVER.getPacketDestination()));
	}

	/**
	 * @return the network
	 */
	public Network getNetwork() {
		return network;
	}

	/**
	 * @param network the network to set
	 */
	public void setNetwork(Network network) {
		this.network = network;
	}
	
}
