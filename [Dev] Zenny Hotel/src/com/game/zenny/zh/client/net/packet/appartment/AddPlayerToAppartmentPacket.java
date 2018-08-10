package com.game.zenny.zh.client.net.packet.appartment;

import org.json.simple.JSONArray;

import com.game.zenny.zh.client.entity.Player;
import com.game.zenny.zh.client.net.Network;
import com.game.zenny.zh.client.net.exception.InvalidPacketConstructorException;
import com.game.zenny.zh.client.net.packet.Packet;

public class AddPlayerToAppartmentPacket extends Packet {

	private String playerToAddJSON;
	
	public AddPlayerToAppartmentPacket(Object[] datas, String fromPlayerIdentifier, String toPlayerIdentifier) {
		super(datas, fromPlayerIdentifier, toPlayerIdentifier);

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

		this.playerToAddJSON = (String) datas[0];
	}

	@Override
	public int getPacketTypeID() {
		return 7;
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONArray build(JSONArray datas) {
		datas.add(playerToAddJSON);
		
		return datas;
	}

	@Override
	public void clientReceivedAction(Network client, String fromPlayerIdentifier) {
		client.getGame().getAppartment().addPlayer(Player.parsePlayerFromJSON(playerToAddJSON));
	}

}
