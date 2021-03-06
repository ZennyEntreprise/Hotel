package com.game.zenny.zh.client.net.packet.disconnect;

import org.json.simple.JSONArray;

import com.game.zenny.zh.client.net.Network;
import com.game.zenny.zh.client.net.packet.Packet;

public class DisconnectPacket extends Packet {

	//// OBJECT
	// -- DISCONNECT PACKET
	public DisconnectPacket(Object[] datas, String fromPlayerIdentifier, String toPlayerIdentifier) {
		super(datas, fromPlayerIdentifier, toPlayerIdentifier);
	}

	@Override
	public int getPacketTypeID() {
		return 4;
	}

	@Override
	public JSONArray build(JSONArray datas) {
		return datas;
	}

	@Override
	public void clientReceivedAction(Network network, String fromPlayerIdentifier) {
		return;
	}

}
