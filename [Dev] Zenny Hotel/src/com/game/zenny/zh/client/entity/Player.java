package com.game.zenny.zh.client.entity;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.URL;

import javax.imageio.ImageIO;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.game.zenny.zh.client.AppClient;
import com.game.zenny.zh.client.Camera;
import com.game.zenny.zh.client.appartment.AppartmentStructure;
import com.game.zenny.zh.client.net.Network;
import com.game.zenny.zh.client.util.ZennyImage;

public class Player {

	/**
	 * @param playerJson
	 * @return player
	 */
	public static Player parsePlayerFromJSON(Network network, String playerJson) {
		try {
			JSONObject playerJSON = (JSONObject) new JSONParser().parse(playerJson);

			String playerIdentifier = (String) playerJSON.get("playerIdentifier");
			InetAddress playerAddress = InetAddress.getByName((String) playerJSON.get("playerAddress"));
			int playerPort = ((Long) playerJSON.get("playerPort")).intValue();
			String playerUsername = (String) playerJSON.get("playerUsername");
			int playerCredits = ((Long) playerJSON.get("playerCredits")).intValue();
			String playerSkinURL = (String) playerJSON.get("playerSkinURL");

			return new Player(network, playerIdentifier, playerAddress, playerPort, playerUsername, playerCredits,
					playerSkinURL);
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void setSkinImage(Player player) throws IOException, SlickException {
		URL url = new URL(player.getPlayerSkinURL());
		BufferedImage image = ImageIO.read(url);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ImageIO.write(image, "jpg", os);
		InputStream is = new ByteArrayInputStream(os.toByteArray());

		player.setPlayerSkin(new ZennyImage(is, player.getPlayerIdentifier()));
	}

	//// OBJECT
	// -- USER
	private Network network;
	private String playerIdentifier;
	private InetAddress playerAddress;
	private int playerPort;

	private String playerUsername;
	private String playerSkinURL;
	private ZennyImage playerSkin;
	private int playerCredits;
	private float x, y;
	private boolean render = false;

	/**
	 * @param playerIdentifier
	 * @param playerAddress
	 * @param playerPort
	 * @param playerUsername
	 * @param playerCredits
	 */
	public Player(Network network, String playerIdentifier, InetAddress playerAddress, int playerPort,
			String playerUsername, int playerCredits, String playerSkinURL) {
		this.network = network;
		this.playerIdentifier = playerIdentifier;
		this.playerAddress = playerAddress;
		this.playerPort = playerPort;
		this.playerUsername = playerUsername;
		this.playerCredits = playerCredits;
		this.playerSkinURL = playerSkinURL;
	}

	public void renderPlayer(GameContainer gc, StateBasedGame sbg, Graphics g) {
		if (playerSkin == null)
			try {
				Player.setSkinImage(this);
			} catch (IOException | SlickException e) {
				e.printStackTrace();
			}

		AppartmentStructure structure = network.getGame().getAppartment().getAppartmentStructure();
		if (structure == null)
			return;

		float cX = structure.getCellXCoordinate(playerCredits, 0);
		float cY = structure.getCellYCoordinate(playerCredits, 0);

		this.x = cX;
		this.y = cY;
		
		if (network.getGame().getPlayer() == this) {
			Camera.setX((int) cX);
			Camera.setY((int) cY);
		}
		
		AppClient.getSprites().furnitureStand.draw(cX, cY - 19);
		playerSkin.drawWithNewDimension(cX, cY - 80, 66 * 0.6f, 132 * 0.6f);
	}

	//// GETTERS AND SETTERS

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

	/**
	 * @return the playerSkinURL
	 */
	public String getPlayerSkinURL() {
		return playerSkinURL;
	}

	/**
	 * @param playerSkinURL
	 *            the playerSkinURL to set
	 */
	public void setPlayerSkinURL(String playerSkinURL) {
		this.playerSkinURL = playerSkinURL;
	}

	/**
	 * @return the playerSkin
	 */
	public ZennyImage getPlayerSkin() {
		return playerSkin;
	}

	/**
	 * @param playerSkin
	 *            the playerSkin to set
	 */
	public void setPlayerSkin(ZennyImage playerSkin) {
		this.playerSkin = playerSkin;
	}

	/**
	 * @return the x
	 */
	public float getX() {
		return x;
	}

	/**
	 * @param x
	 *            the x to set
	 */
	public void setX(float x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public float getY() {
		return y;
	}

	/**
	 * @param y
	 *            the y to set
	 */
	public void setY(float y) {
		this.y = y;
	}

	/**
	 * @return the render
	 */
	public boolean isRender() {
		return render;
	}

	/**
	 * @param render
	 *            the render to set
	 */
	public void setRender(boolean render) {
		this.render = render;
	}

}
