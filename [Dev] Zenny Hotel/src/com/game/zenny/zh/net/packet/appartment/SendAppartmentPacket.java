package com.game.zenny.zh.net.packet.appartment;

import org.json.simple.JSONArray;

import com.game.zenny.zh.appartment.Appartment;
import com.game.zenny.zh.net.Network;
import com.game.zenny.zh.net.exception.InvalidPacketConstructorException;
import com.game.zenny.zh.net.packet.Packet;

public class SendAppartmentPacket extends Packet {

	private String appartmentJSON;
	
	public SendAppartmentPacket(Object[] datas, String fromPlayerIdentifier, String toPlayerIdentifier) {
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

		this.appartmentJSON = (String) datas[0];
	}

	@Override
	public int getPacketTypeID() {
		return 6;
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONArray build(JSONArray datas) {
		datas.add(appartmentJSON);
		
		return datas;
	}

	@Override
	public void clientReceivedAction(Network client, String fromPlayerIdentifier) {
		// TODO [HOTEL CLIENT] PARSE
		client.getGame().setAppartment(Appartment.parseAppartmentFromJSON(appartmentJSON));
	}

}
