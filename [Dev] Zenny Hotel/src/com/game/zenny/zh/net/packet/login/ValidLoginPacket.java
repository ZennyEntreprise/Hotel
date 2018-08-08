package com.game.zenny.zh.net.packet.login;

import org.json.simple.JSONArray;

import com.game.zenny.zh.NetworkClient;
import com.game.zenny.zh.net.exception.InvalidPacketConstructorException;
import com.game.zenny.zh.net.packet.Packet;

public class ValidLoginPacket extends Packet {

	//// OBJECT
	// -- VALID LOGIN PACKET
	private String playerIdentifier;
	private String playername;
	private int credits;
	
	public ValidLoginPacket(Object[] datas, String fromPlayerIdentifier, String toPlayerIdentifier) {
		super(datas, fromPlayerIdentifier, toPlayerIdentifier);
		
		if (datas.length < 3)
			try {
				throw new InvalidPacketConstructorException("Not enough arguments ! :/");
			} catch (InvalidPacketConstructorException e) {
				e.printStackTrace();
			}
		
		if (datas.length > 3)
			try {
				throw new InvalidPacketConstructorException("Too many arguments !");
			} catch (InvalidPacketConstructorException e) {
				e.printStackTrace();
			}
		
		if (!(datas[0] instanceof String) && !(datas[1] instanceof String) && !(datas[2] instanceof Long))
			try {
				throw new InvalidPacketConstructorException("Arguments aren't correct !");
			} catch (InvalidPacketConstructorException e) {
				e.printStackTrace();
			}
		
		this.playerIdentifier = (String) datas[0];
		this.playername = (String) datas[1];
		this.credits = (int) datas[3];
	}

	@Override
	public int getPacketTypeID() {
		return 2;
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONArray build(JSONArray datas) {
		datas.add(playerIdentifier);
		datas.add(playername);
		datas.add(credits);
		
		return datas;
	}

	@Override
	public void clientReceivedAction(NetworkClient client, String fromPlayerIdentifier) {
		client.setIdentifier(playerIdentifier);
		
		// TODO [HOTEL] SET PLAYER
	}

}
