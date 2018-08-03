package com.game.zenny.zh.element;

import org.newdawn.slick.SlickException;

public class Sprite {

	public ZennyImage ground;
	public ZennyImage groundSelection;
	public ZennyImage groundThicknessTL;
	public ZennyImage groundThicknessBL;
	public ZennyImage character;
	
	public Sprite() throws SlickException {
		ground = new ZennyImage("appartment/ground");
		groundSelection = new ZennyImage("appartment/ground-selection");
		groundThicknessTL = new ZennyImage("appartment/ground-thickness-tl");
		groundThicknessBL = new ZennyImage("appartment/ground-thickness-bl");
		character = new ZennyImage("character");
	}
	
}
