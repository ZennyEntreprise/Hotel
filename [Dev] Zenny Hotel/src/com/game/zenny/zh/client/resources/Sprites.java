package com.game.zenny.zh.client.resources;

import org.newdawn.slick.SlickException;

import com.game.zenny.zh.client.util.ZennyImage;

public class Sprites {

	public ZennyImage ground;
	public ZennyImage groundSelection;
	public ZennyImage groundThicknessTL;
	public ZennyImage groundThicknessBL;
	public ZennyImage logoBig;
	public ZennyImage furnitureStand;
	
	public Sprites() throws SlickException {
		ground = new ZennyImage("appartment/ground");
		groundSelection = new ZennyImage("appartment/ground-selection");
		groundThicknessTL = new ZennyImage("appartment/ground-thickness-tl");
		groundThicknessBL = new ZennyImage("appartment/ground-thickness-bl");
		logoBig = new ZennyImage("logo-big");
		furnitureStand = new ZennyImage("furniture/stand");
	}
	
}
