package com.game.zenny.zh.util;

import org.newdawn.slick.Color;

public enum ZennyColor {

	BACKGROUND_COLOR(new Color(44, 44, 44)),
	BEIGE(new Color(248, 239, 186)), 
	CYAN1(new Color(26, 188, 156)),
	CYAN2(new Color(22, 160, 133)),
	GREEN1(new Color(46, 204, 113)),
	GREEN2(new Color(39, 174, 96)),
	BLUE1(new Color(52, 152, 219)),
	BLUE2(new Color(41, 128, 185)),
	PURPLE1(new Color(155, 89, 182)),
	PURPLE2(new Color(142, 68, 173)),
	DARKGREY1(new Color(52, 73, 94)),
	DARKGREY2(new Color(44, 62, 80)),
	YELLOW(new Color(241, 196, 15)),
	ORANGE1(new Color(243, 156, 18)),
	ORANGE2(new Color(230, 126, 34)),
	ORANGE3(new Color(211, 84, 0)),
	RED1(new Color(231, 76, 60)),
	RED2(new Color(192, 57, 43)),
	RED3(new Color(234, 32, 39)),
	WHITE(new Color(236, 240, 241)),
	GREY1(new Color(189, 195, 199)),
	GREY2(new Color(149, 165, 166)),
	GREY3(new Color(127, 140, 141));
	
	private Color color;
	
	ZennyColor(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}
}
