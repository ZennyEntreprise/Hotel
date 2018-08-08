package com.game.zenny.zh.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.StateBasedGame;

import com.game.zenny.zh.AppClient;
import com.game.zenny.zh.scene.Scene;
import com.game.zenny.zh.util.ZennyColor;

public class Label extends Component {

	private TrueTypeFont font = AppClient.getFont(AppClient.getFonts().OpenSans_EXTRA_BOLD, AppClient.proportionalValueByWidth(36));
	private String text;
	private Color color = ZennyColor.WHITE.getColor();

	/**
	 * @param scene
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param font
	 * @param text
	 */
	public Label(Scene scene, float x, float y, TrueTypeFont font, String text) {
		super(scene, x, y, font.getWidth(text), font.getHeight(text));

		this.font = font;
		this.text = text;
	}

	/**
	 * @param scene
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param text
	 */
	public Label(Scene scene, float x, float y, String text) {
		super(scene, x, y, 0, 0);

		this.text = text;

		setWidth(font.getWidth(text));
		setHeight(font.getHeight(text));
	}

	@Override
	public void renderComponent(GameContainer gc, StateBasedGame sbg, Graphics g) {
		font.drawString(getRealX(), getRealY(), text, color);
	}

	@Override
	public void updateComponent(GameContainer gc, StateBasedGame sbg, int delta) {

	}

	/**
	 * @return the font
	 */
	public TrueTypeFont getFont() {
		return font;
	}

	/**
	 * @param font
	 *            the font to set
	 */
	public void setFont(TrueTypeFont font) {
		this.font = font;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(String text) {
		this.text = text;
		
		setWidth(font.getWidth(text));
		setHeight(font.getHeight(text));
	}

	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @param color
	 *            the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	
	/**
	 * @param color
	 *            the color to set
	 */
	public void setColor(ZennyColor color) {
		this.color = color.getColor();
	}

}
