package com.game.zenny.zh.element;

import org.newdawn.slick.SlickException;

public class Sprite {

	public ZHImage ground;
	public ZHImage groundSelection;
	public ZHImage groundThicknessTL;
	public ZHImage groundThicknessBL;
	public ZHImage character;
	
	public Sprite() throws SlickException {
		ground = new ZHImage("appartment/ground");
		groundSelection = new ZHImage("appartment/ground-selection");
		groundThicknessTL = new ZHImage("appartment/ground-thickness-tl");
		groundThicknessBL = new ZHImage("appartment/ground-thickness-bl");
		character = new ZHImage("character");
	}
	
}
