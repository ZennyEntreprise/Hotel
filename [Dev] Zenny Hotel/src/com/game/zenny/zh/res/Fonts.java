package com.game.zenny.zh.res;

import java.awt.FontFormatException;
import java.io.IOException;

import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

public class Fonts {

	public java.awt.Font OpenSans_LIGHT;
	public java.awt.Font OpenSans_LIGHT_ITALIC;
	public java.awt.Font OpenSans_REGULAR;
	public java.awt.Font OpenSans_REGULAR_ITALIC;
	public java.awt.Font OpenSans_SEMI_BOLD;
	public java.awt.Font OpenSans_SEMI_BOLD_ITALIC;
	public java.awt.Font OpenSans_BOLD;
	public java.awt.Font OpenSans_BOLD_ITALIC;
	public java.awt.Font OpenSans_EXTRA_BOLD;
	public java.awt.Font OpenSans_EXTRA_BOLD_ITALIC;
	
	public Fonts() throws FontFormatException, IOException {
		OpenSans_LIGHT = java.awt.Font.createFont(0, ResourceLoader.getResourceAsStream("res/fonts/OpenSans-Light.ttf"));
		OpenSans_LIGHT_ITALIC = java.awt.Font.createFont(0, ResourceLoader.getResourceAsStream("res/fonts/OpenSans-LightItalic.ttf"));
		
		OpenSans_REGULAR = java.awt.Font.createFont(0, ResourceLoader.getResourceAsStream("res/fonts/OpenSans-Regular.ttf"));
		OpenSans_REGULAR_ITALIC = java.awt.Font.createFont(0, ResourceLoader.getResourceAsStream("res/fonts/OpenSans-Italic.ttf"));
		
		OpenSans_SEMI_BOLD = java.awt.Font.createFont(0, ResourceLoader.getResourceAsStream("res/fonts/OpenSans-SemiBold.ttf"));
		OpenSans_SEMI_BOLD_ITALIC = java.awt.Font.createFont(0, ResourceLoader.getResourceAsStream("res/fonts/OpenSans-SemiBoldItalic.ttf"));
		
		OpenSans_BOLD = java.awt.Font.createFont(0, ResourceLoader.getResourceAsStream("res/fonts/OpenSans-Bold.ttf"));
		OpenSans_BOLD_ITALIC = java.awt.Font.createFont(0, ResourceLoader.getResourceAsStream("res/fonts/OpenSans-BoldItalic.ttf"));
		
		OpenSans_EXTRA_BOLD = java.awt.Font.createFont(0, ResourceLoader.getResourceAsStream("res/fonts/OpenSans-ExtraBold.ttf"));
		OpenSans_EXTRA_BOLD_ITALIC = java.awt.Font.createFont(0, ResourceLoader.getResourceAsStream("res/fonts/OpenSans-ExtraBoldItalic.ttf"));
	}
	
	public TrueTypeFont get(java.awt.Font font, float size) {
		return new TrueTypeFont(font.deriveFont(size), true);
	}
}
