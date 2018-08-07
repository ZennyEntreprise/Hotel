package personnal.utils.zenny.transmitter.server;

import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;

import personnal.utils.zenny.transmitter.Bridge;
import personnal.utils.zenny.transmitter.User;
import personnal.utils.zenny.transmitter.logger.LogType;
import personnal.utils.zenny.transmitter.logger.Logger;
import personnal.utils.zenny.transmitter.packet.Packet;

public class Server extends Bridge {

	//// STATIC
	public static void main(String[] args) {
		try {
			new Server(Bridge.defaultPort, "server");
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	//// OBJECT
	// -- SERVER
	private int serverPort;

	/**
	 * @param serverPort
	 * @param identifier
	 * @throws SocketException
	 */
	public Server(int serverPort, String identifier) throws SocketException {
		super(new DatagramSocket(serverPort), identifier);

		Logger.log(this, LogType.INFO, "Creating server...");
		this.serverPort = serverPort;
		Logger.log(this, LogType.INFO, "Listening port defined on: " + serverPort);
		Logger.log(this, LogType.INFO, "Server created !");
	}

	/**
	 * @return serverPort
	 */
	public int getServerPort() {
		return serverPort;
	}

	// -- PACKET

	/**
	 * @param packet
	 * @param from user
	 */
	@Override
	public void packetAction(Packet packet, User fromUser) {
		packet.serverReceivedAction(this, fromUser);
	}

	/**
	 * @param packet
	 * @param to user
	 */
	public void sendPacket(Packet packet, User toUser) {
		getSender().sendPacket(packet, toUser.getUserAddress(), toUser.getUserPort());
	}

	/**
	 * @param packet
	 * @param to users
	 */
	public void sendPacket(Packet packet, ArrayList<User> toUsers) {
		for (User user : toUsers) {
			packet.setToUserIdentifier(user.getUserIdentifier());
			sendPacket(packet, user);
		}
	}
}
