package com.game.zenny.zh.net.packet.disconnect;

import org.json.simple.JSONArray;

import com.game.zenny.zh.net.client.Client;
import com.game.zenny.zh.net.packet.Packet;

public class DisconnectPacket extends Packet {

	//// OBJECT
	// -- DISCONNECT PACKET
	public DisconnectPacket(Object[] datas, String fromUserIdentifier, String toUserIdentifier) {
		super(datas, fromUserIdentifier, toUserIdentifier);
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
	public void clientReceivedAction(Client client, String fromUserIdentifier) {
		return;
	}

}
