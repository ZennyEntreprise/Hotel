package com.game.zenny.zh.net;

import java.net.InetAddress;
import java.net.SocketException;

import com.game.zenny.zh.net.client.Client;
import com.game.zenny.zh.net.packet.Packet;
import com.game.zenny.zh.scene.Game;

public class Network extends Client {

	private Game game;
	
	/**
	 * @param game
	 * @param serverAddress
	 * @param serverPort
	 * @throws SocketException
	 */
	public Network(Game game, InetAddress serverAddress, int serverPort) throws SocketException {
		super(serverAddress, serverPort);
		
		this.game = game;
	}

	@Override
	public void packetAction(Packet packet, String fromUserIdentifier) {
		packet.clientReceivedAction(this, fromUserIdentifier);
	}
	
	/**
	 * @return the game
	 */
	public Game getGame() {
		return game;
	}

	/**
	 * @param game the game to set
	 */
	public void setGame(Game game) {
		this.game = game;
	}

}
