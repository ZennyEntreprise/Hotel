package personnal.utils.zenny.transmitter.packet;

import org.json.simple.JSONArray;

import personnal.utils.zenny.transmitter.User;
import personnal.utils.zenny.transmitter.client.Client;
import personnal.utils.zenny.transmitter.logger.LogType;
import personnal.utils.zenny.transmitter.logger.Logger;
import personnal.utils.zenny.transmitter.server.Server;

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
	public void serverReceivedAction(Server server, User fromUser) {
		Logger.log(server, LogType.WARNING, "Unknown Packet");
	}

	@Override
	public void clientReceivedAction(Client client, String fromUserIdentifier) {
		Logger.log(client, LogType.WARNING, "Unknown Packet");
	}

}
