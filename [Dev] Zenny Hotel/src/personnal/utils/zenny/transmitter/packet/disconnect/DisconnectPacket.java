package personnal.utils.zenny.transmitter.packet.disconnect;

import org.json.simple.JSONArray;

import personnal.utils.zenny.transmitter.User;
import personnal.utils.zenny.transmitter.client.Client;
import personnal.utils.zenny.transmitter.packet.Packet;
import personnal.utils.zenny.transmitter.packet.user.RemoveUserPacket;
import personnal.utils.zenny.transmitter.server.Server;

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
	public void serverReceivedAction(Server server, User fromUser) {
		if (server.containsUser(fromUser))
			server.sendPacket(new RemoveUserPacket(Packet.buildDatasObject(fromUser.getUserIdentifier()), server.getIdentifier(), null), server.getUsers());
	}

	@Override
	public void clientReceivedAction(Client client, String fromUserIdentifier) {

	}

}
