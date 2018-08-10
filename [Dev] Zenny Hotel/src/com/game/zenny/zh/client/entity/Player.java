package com.game.zenny.zh.client.entity;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Player {

	/**
	 * @param playerJson
	 * @return player
	 */
	public static Player parsePlayerFromJSON(String playerJson) {
		try {
			JSONObject playerJSON = (JSONObject) new JSONParser().parse(playerJson);
			
			String playerIdentifier = (String) playerJSON.get("playerIdentifier");
			InetAddress playerAddress = InetAddress.getByName((String) playerJSON.get("playerAddress"));
			int playerPort = ((Long) playerJSON.get("playerPort")).intValue();
			String playerUsername = (String) playerJSON.get("playerUsername");
			int playerCredits = ((Long) playerJSON.get("playerCredits")).intValue();
			
			return new Player(playerIdentifier, playerAddress, playerPort, playerUsername, playerCredits);
		} catch (ParseException | UnknownHostException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//// OBJECT
	// -- USER
	private String playerIdentifier;
	private InetAddress playerAddress;
	private int playerPort;
	private String playerUsername;
	private int playerCredits;

	/**
	 * @param playerIdentifier
	 * @param playerAddress
	 * @param playerPort
	 * @param playerUsername
	 * @param playerCredits
	 */
	public Player(String playerIdentifier, InetAddress playerAddress, int playerPort, String playerUsername, int playerCredits) {
		super();
		this.playerIdentifier = playerIdentifier;
		this.playerAddress = playerAddress;
		this.playerPort = playerPort;
		this.playerUsername = playerUsername;
		this.playerCredits = playerCredits;
	}

	@SuppressWarnings("unchecked")
	public String toJSON() {
		JSONObject playerJSON = new JSONObject();
		playerJSON.put("playerIdentifier", playerIdentifier);
		playerJSON.put("playerAddress", playerAddress.getHostAddress().toString());
		playerJSON.put("playerPort", playerPort);
		playerJSON.put("playerUsername", playerUsername);
		playerJSON.put("playerCredits", playerCredits);
		
		return playerJSON.toJSONString();
	}
	
	/**
	 * @return the playerIdentifier
	 */
	public String getPlayerIdentifier() {
		return playerIdentifier;
	}

	/**
	 * @param playerIdentifier
	 *            the playerIdentifier to set
	 */
	public void setPlayerIdentifier(String playerIdentifier) {
		this.playerIdentifier = playerIdentifier;
	}

	/**
	 * @return the playerAddress
	 */
	public InetAddress getPlayerAddress() {
		return playerAddress;
	}

	/**
	 * @param playerAddress
	 *            the playerAddress to set
	 */
	public void setPlayerAddress(InetAddress playerAddress) {
		this.playerAddress = playerAddress;
	}

	/**
	 * @return the playerPort
	 */
	public int getPlayerPort() {
		return playerPort;
	}

	/**
	 * @param playerPort
	 *            the playerPort to set
	 */
	public void setPlayerPort(int playerPort) {
		this.playerPort = playerPort;
	}

	/**
	 * @return the playerUsername
	 */
	public String getPlayerUsername() {
		return playerUsername;
	}

	/**
	 * @param playerUsername
	 *            the playerUsername to set
	 */
	public void setPlayerUsername(String playerUsername) {
		this.playerUsername = playerUsername;
	}

	/**
	 * @return the playerCredits
	 */
	public int getPlayerCredits() {
		return playerCredits;
	}

	/**
	 * @param playerCredits
	 *            the playerCredits to set
	 */
	public void setPlayerCredits(int playerCredits) {
		this.playerCredits = playerCredits;
	}

}
