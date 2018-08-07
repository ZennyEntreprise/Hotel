package com.game.zenny.zh.net.packet.login;

import org.json.simple.JSONArray;

import com.game.zenny.zh.net.client.Client;
import com.game.zenny.zh.net.exception.InvalidPacketConstructorException;
import com.game.zenny.zh.net.packet.Packet;

public class LoginPacket extends Packet {

	//// OBJECT
	// -- LOGIN PACKET
	private String userIdentifier;

	public LoginPacket(Object[] datas, String fromUserIdentifier, String toUserIdentifier) {
		super(datas, fromUserIdentifier, toUserIdentifier);

		if (datas.length == 0)
			try {
				throw new InvalidPacketConstructorException("No argument ! :/");
			} catch (InvalidPacketConstructorException e) {
				e.printStackTrace();
			}

		if (datas.length > 1)
			try {
				throw new InvalidPacketConstructorException("Too many arguments !");
			} catch (InvalidPacketConstructorException e) {
				e.printStackTrace();
			}

		if (!(datas[0] instanceof String))
			try {
				throw new InvalidPacketConstructorException("First argument is not a string !");
			} catch (InvalidPacketConstructorException e) {
				e.printStackTrace();
			}

		this.userIdentifier = (String) datas[0];
	}

	@Override
	public int getPacketTypeID() {
		return 1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONArray build(JSONArray datas) {
		datas.add(userIdentifier);

		return datas;
	}

	@Override
	public void clientReceivedAction(Client client, String fromUserIdentifier) {
		return;
	}

}