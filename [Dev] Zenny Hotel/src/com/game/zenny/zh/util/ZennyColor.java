package com.game.zenny.zh.element;

import org.newdawn.slick.Color;

public enum ZennyColor {

	BACKGROUND_COLOR(new Color(44, 44, 44)),
	DEFAULT_GROUND_CELL_COLOR(new Color(248, 239, 186));
	
	private Color color;
	
	ZennyColor(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}
}
