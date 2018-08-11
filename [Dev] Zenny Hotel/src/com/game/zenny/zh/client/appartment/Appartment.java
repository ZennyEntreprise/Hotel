package com.game.zenny.zh.client.appartment;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.game.zenny.zh.client.Camera;
import com.game.zenny.zh.client.entity.Player;
import com.game.zenny.zh.client.net.Network;
import com.game.zenny.zh.client.util.ZennyMouse;

public class Appartment {

	//// STATIC

	/**
	 * @param appartmentJson
	 * @return
	 */
	public static Appartment parseAppartmentFromJSON(Network network, String appartmentJson) {
		try {
			JSONObject appartmentJSON = (JSONObject) new JSONParser().parse(appartmentJson);

			String appartmentIdentifier = (String) appartmentJSON.get("appartmentIdentifer");
			String ownerPlayerIdentifier = (String) appartmentJSON.get("ownerPlayerIdentifier");
			String appartmentName = (String) appartmentJSON.get("appartmentName");
			JSONArray appartmentStructure = (JSONArray) appartmentJSON.get("appartmentStructure");
			JSONArray playersInAppartmentJSON = (JSONArray) appartmentJSON.get("playersInAppartment");
			ArrayList<Player> playersInAppartment = new ArrayList<Player>();
			for (int i = 0; i < playersInAppartmentJSON.size(); i++) {
				JSONObject playerInAppartmentJSON = (JSONObject) playersInAppartmentJSON.get(i);
				playersInAppartment.add(Player.parsePlayerFromJSON(network, playerInAppartmentJSON.toJSONString()));
			}

			return new Appartment(appartmentIdentifier, ownerPlayerIdentifier, appartmentName,
					AppartmentStructure.parseAppartmentStructureFromJSON(appartmentStructure), playersInAppartment);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	//// OBJECT
	// -- APPARTMENT
	private String appartmentIdentifier;
	private String ownerPlayerIdentifier;
	private String appartmentName;
	private AppartmentStructure appartmentStructure;
	private ArrayList<Player> playersInAppartment;

	//// CONSTRUCTORS

	public Appartment(String appartmentIdentifier, String ownerPlayerIdentifier, String appartmentName,
			AppartmentStructure appartmentStructure, ArrayList<Player> playersInAppartment) {
		this.appartmentIdentifier = appartmentIdentifier;
		this.ownerPlayerIdentifier = ownerPlayerIdentifier;
		this.appartmentName = appartmentName;
		this.appartmentStructure = appartmentStructure;
		this.playersInAppartment = playersInAppartment;
	}

	public void addPlayer(Player player) {
		playersInAppartment.add(player);
	}
	
	public void removePlayer(String playerToRemoveIdentifier) {
		for (Player player : playersInAppartment)
			if (player.getPlayerIdentifier().equals(playerToRemoveIdentifier)) {
				playersInAppartment.remove(player);
				break;
			}
	}
	
	//// GETTERS AND SETTERS

	/**
	 * @return the appartmentIdentifier
	 */
	public String getAppartmentIdentifier() {
		return appartmentIdentifier;
	}

	/**
	 * @param appartmentIdentifier
	 *            the appartmentIdentifier to set
	 */
	public void setAppartmentIdentifier(String appartmentIdentifier) {
		this.appartmentIdentifier = appartmentIdentifier;
	}

	/**
	 * @return the ownerPlayerIdentifier
	 */
	public String getOwnerPlayerIdentifier() {
		return ownerPlayerIdentifier;
	}

	/**
	 * @param ownerPlayerIdentifier
	 *            the ownerPlayerIdentifier to set
	 */
	public void setOwnerPlayerIdentifier(String ownerPlayerIdentifier) {
		this.ownerPlayerIdentifier = ownerPlayerIdentifier;
	}

	/**
	 * @return the appartmentName
	 */
	public String getAppartmentName() {
		return appartmentName;
	}

	/**
	 * @param appartmentName
	 *            the appartmentName to set
	 */
	public void setAppartmentName(String appartmentName) {
		this.appartmentName = appartmentName;
	}

	/**
	 * @return the structure
	 */
	public AppartmentStructure getAppartmentStructure() {
		return appartmentStructure;
	}

	/**
	 * @param structure
	 *            the structure to set
	 */
	public void setAppartmentStructure(AppartmentStructure appartmentStructure) {
		this.appartmentStructure = appartmentStructure;
	}

	/**
	 * @return the playersInAppartment
	 */
	public ArrayList<Player> getPlayersInAppartment() {
		return playersInAppartment;
	}

	/**
	 * @param playersInAppartment
	 *            the playersInAppartment to set
	 */
	public void setPlayersInAppartment(ArrayList<Player> playersInAppartment) {
		this.playersInAppartment = playersInAppartment;
	}

	//// GAME METHODS

	/**
	 * @param gc
	 * @param sbg
	 * @param delta
	 * @throws SlickException
	 */

	private ResetCamMovement rcm;
	private boolean hasMoved = false;

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		if (Mouse.isButtonDown(1)) {
			if (!hasMoved) {
				hasMoved = true;

				if (rcm != null) {
					rcm.running = false;
					Camera.setResetingRelativePos(false);
					rcm = new ResetCamMovement();
				}
			}

			Camera.subRelativeX(Math.round(0.05f * ZennyMouse.getDX() * delta));
			Camera.addRelativeY(Math.round(0.05f * ZennyMouse.getDY() * delta));
		} else {
			if (hasMoved) {
				if (rcm == null)
					rcm = new ResetCamMovement();

				hasMoved = false;

				rcm.running = true;
				rcm.start();
			}

			if (Camera.isResetingRelativePos())
				Camera.resetRelativePos();
		}

		appartmentStructure.update(gc, sbg, delta);
	}

	class ResetCamMovement extends Thread {

		public volatile boolean running = false;

		private int c = 1;

		@Override
		public void run() {
			while (running) {
				if (c == 100)
					Camera.resetRelativePos();

				c++;
				try {
					Thread.sleep(15);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
