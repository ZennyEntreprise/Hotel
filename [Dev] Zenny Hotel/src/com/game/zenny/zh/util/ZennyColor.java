package com.game.zenny.zh.util;

import org.newdawn.slick.Color;

public enum ZennyColor {

	BACKGROUND_COLOR(new Color(44, 44, 44)),
	DEFAULT_GROUND_CELL_COLOR(new Color(248, 239, 186)), 
	DEFAULT_TEXT_COLOR(new Color(245, 246, 250)),
	DEFAULT_BUTTON_COLOR(new Color(251, 197, 49));
	
	private Color color;
	
	ZennyColor(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}
}
