package com.game.zenny.zh.client.scene;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.state.StateBasedGame;

import com.game.zenny.zh.client.AppClient;
import com.game.zenny.zh.client.appartment.Appartment;
import com.game.zenny.zh.client.appartment.AppartmentStructure;
import com.game.zenny.zh.client.entity.Player;
import com.game.zenny.zh.client.net.Bridge;
import com.game.zenny.zh.client.net.Network;
import com.game.zenny.zh.client.net.packet.Packet;
import com.game.zenny.zh.client.net.packet.PacketDestination;
import com.game.zenny.zh.client.net.packet.appartment.GoIntoAppartmentPacket;

public class Game extends Scene {

	private final String playerIndentifier;
	private Network network;
	private Player player;
	private Appartment appartment;

	/**
	 * @param app
	 * @throws IOException
	 * @throws SlickException
	 */
	public Game(AppClient app, String playerIdentifier) {
		super(app, AppClient.Scenes.GAME.getSceneID());
		
		this.playerIndentifier = playerIdentifier;
	}

	@Override
	public void initScene(GameContainer gc, StateBasedGame sbg) {
		try {
			network = new Network(this, InetAddress.getByName("localhost"), Bridge.defaultPort);
		} catch (SocketException | UnknownHostException e) {
			System.exit(0);
		}

		network.connect(playerIndentifier);
	}

	@Override
	public void leaveScene(GameContainer gc, StateBasedGame sbg) {

	}

	@SuppressWarnings("unchecked")
	@Override
	public void renderScene(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		if (player == null || appartment == null)
			return;

		AppartmentStructure appartStructure = appartment.getAppartmentStructure();
		
		ArrayList<Player> players = (ArrayList<Player>) appartment.getPlayersInAppartment().clone();
		players.add(player);
		
		for (int y = 0; y < appartStructure.getStructure().length; y++) {
			for (int x = 0; x < appartStructure.getStructure()[y].length; x++) {
				float cX = appartStructure.getCellXCoordinate(x, y);
				float cY = appartStructure.getCellYCoordinate(x, y);
				
				appartStructure.getStructure()[y][x].render(gc, sbg, g, new Point(cX, cY));
				
				for (Player player : players) {
					if (!player.isRender() && player.getX() == cX && player.getY() == cY) {
						player.setRender(true);
						player.renderPlayer(gc, sbg, g);
					}
				}
			}
		}
		
		for (Player player : players) {
			if (player.isRender()) {
				player.setRender(false);
			}
		}

		g.drawString("UUID: " + player.getPlayerIdentifier(), 10, 180);
		g.drawString("Username: " + player.getPlayerUsername(), 10, 200);
		g.drawString("Credits: " + player.getPlayerCredits(), 10, 220);

		g.drawString("Player(s) in appartment with me (" + getAppartment().getPlayersInAppartment().size() + "): ", 10,
				240);
		for (int i = 0; i < getAppartment().getPlayersInAppartment().size(); i++) {
			g.drawString("- " + getAppartment().getPlayersInAppartment().get(i).getPlayerUsername(), 10, 260 + i * 20);
		}

	}

	@Override
	public void updateScene(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		if (player == null || appartment == null)
			return;

		appartment.update(gc, sbg, delta);

		if (gc.getInput().isKeyPressed(Input.KEY_1))
			network.sendPacket(new GoIntoAppartmentPacket(Packet.buildDatasObject("default"), network.getIdentifier(),
					PacketDestination.TO_SERVER.getPacketDestination()));
		else if (gc.getInput().isKeyPressed(Input.KEY_2))
			network.sendPacket(new GoIntoAppartmentPacket(Packet.buildDatasObject("test"), network.getIdentifier(),
					PacketDestination.TO_SERVER.getPacketDestination()));
	}

	/**
	 * @return the networkClient
	 */
	public Network getNetwork() {
		return network;
	}

	/**
	 * @param networkClient
	 *            the networkClient to set
	 */
	public void setNetwork(Network network) {
		this.network = network;
	}

	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * @param x
	 * @param y
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Player getPlayer(int x, int y) {
		ArrayList<Player> players = (ArrayList<Player>) appartment.getPlayersInAppartment().clone();
		players.add(this.player);
		for (Player player : players) {
			if (player.getX() == appartment.getAppartmentStructure().getCellXCoordinate(x, y) && player.getY() == appartment.getAppartmentStructure().getCellYCoordinate(x, y))
				return player;
		}
		return null;
	}
	
	/**
	 * @param player
	 *            the player to set
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * @return the appartment
	 */
	public Appartment getAppartment() {
		return appartment;
	}

	/**
	 * @param appartment
	 *            the appartment to set
	 */
	public void setAppartment(Appartment appartment) {
		this.appartment = appartment;
	}
}
