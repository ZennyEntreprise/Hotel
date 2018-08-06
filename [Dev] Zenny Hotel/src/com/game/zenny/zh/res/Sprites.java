package com.game.zenny.zh.res;

import org.newdawn.slick.SlickException;

import com.game.zenny.zh.util.ZennyImage;

public class Sprites {

	public ZennyImage ground;
	public ZennyImage groundSelection;
	public ZennyImage groundThicknessTL;
	public ZennyImage groundThicknessBL;
	public ZennyImage character;
	public ZennyImage logoBig;
	
	public Sprites() throws SlickException {
		ground = new ZennyImage("appartment/ground");
		groundSelection = new ZennyImage("appartment/ground-selection");
		groundThicknessTL = new ZennyImage("appartment/ground-thickness-tl");
		groundThicknessBL = new ZennyImage("appartment/ground-thickness-bl");
		character = new ZennyImage("character");
		logoBig = new ZennyImage("logo-big");
	}
	
}
