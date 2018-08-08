package com.game.zenny.zh.entity;

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
			String username = (String) playerJSON.get("username");
			int credits = ((Long) playerJSON.get("credits")).intValue();
			
			return new Player(playerIdentifier, playerAddress, playerPort, username, credits);
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
	private String username;
	private int credits;

	/**
	 * @param playerIdentifier
	 * @param playerAddress
	 * @param playerPort
	 * @param username
	 * @param credits
	 */
	public Player(String playerIdentifier, InetAddress playerAddress, int playerPort, String username, int credits) {
		super();
		this.playerIdentifier = playerIdentifier;
		this.playerAddress = playerAddress;
		this.playerPort = playerPort;
		this.username = username;
		this.credits = credits;
	}

	@SuppressWarnings("unchecked")
	public String toJSON() {
		JSONObject playerJSON = new JSONObject();
		playerJSON.put("playerIdentifier", playerIdentifier);
		playerJSON.put("playerAddress", playerAddress.getHostAddress().toString());
		playerJSON.put("playerPort", playerPort);
		playerJSON.put("username", username);
		playerJSON.put("credits", credits);
		
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
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the credits
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * @param credits
	 *            the credits to set
	 */
	public void setCredits(int credits) {
		this.credits = credits;
	}

}
