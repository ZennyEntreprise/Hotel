package com.game.zenny.zh.client.net.packet;

import org.json.simple.JSONArray;

import com.game.zenny.zh.client.logger.LogType;
import com.game.zenny.zh.client.logger.Logger;
import com.game.zenny.zh.client.net.Network;

public class UnknownPacket extends Packet {

	//// OBJECT
	// -- UNKNOWN PACKET
	public UnknownPacket(Object[] datas, String from, String to) {
		super(datas, from, to);
	}

	@Override
	public int getPacketTypeID() {
		return 0;
	}

	@Override
	public JSONArray build(JSONArray datas) {
		return datas;
	}

	@Override
	public void clientReceivedAction(Network client, String fromPlayerIdentifier) {
		Logger.log(client, LogType.WARNING, "Unknown Packet");
	}

}
