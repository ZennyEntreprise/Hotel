package com.game.zenny.zh.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.StateBasedGame;

import com.game.zenny.zh.App;
import com.game.zenny.zh.scene.Scene;
import com.game.zenny.zh.util.ZennyColor;

public class Button extends Component {

	private TrueTypeFont font;
	private String text;
	private Color textColor, buttonHoverTextColor, buttonClickTextColor;
	private Color buttonColor, buttonHoverColor, buttonClickColor;
	private int cornerRadius;

	private Color bColor, tColor;
	
	public Button(Scene scene, float x, float y, float width, float height, String text) {
		super(scene, x, y, width, height);

		this.font = App.getFont(App.getFonts().OpenSans_BOLD, 28);
		this.text = text;

		this.textColor = ZennyColor.DEFAULT_TEXT_COLOR.getColor();
		this.buttonHoverTextColor = ZennyColor.DEFAULT_TEXT_COLOR.getColor();
		this.buttonClickTextColor = ZennyColor.DEFAULT_TEXT_COLOR.getColor();

		this.buttonColor = ZennyColor.DEFAULT_BUTTON_COLOR.getColor();
		this.buttonHoverColor = ZennyColor.DEFAULT_BUTTON_COLOR.getColor().darker(0.07f);
		this.buttonClickColor = ZennyColor.DEFAULT_BUTTON_COLOR.getColor().darker(0.14f);

		this.cornerRadius = 3;
		
		this.bColor = buttonColor;
		this.tColor = textColor;
	}

	@Override
	public void renderComponent(GameContainer gc, StateBasedGame sbg, Graphics g) {
		g.setColor(bColor);
		g.fillRoundRect(x, y, width, height, cornerRadius);

		font.drawString(Math.round(x + (width - font.getWidth(text)) / 2), Math.round(y - 3 + (height - font.getHeight(text)) / 2), text, tColor);
	}

	@Override
	public void updateComponent(GameContainer gc, StateBasedGame sbg, int delta) {
		if (focused && clicked) {
			bColor = buttonClickColor;
			tColor = buttonClickTextColor;
		} else if (focused && !clicked) {
			bColor = buttonHoverColor;
			tColor = buttonHoverTextColor;
		} else {
			bColor = buttonColor;
			tColor = textColor;
		}
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
	}

	/**
	 * @return the textColor
	 */
	public Color getTextColor() {
		return textColor;
	}

	/**
	 * @param textColor
	 *            the textColor to set
	 */
	public void setTextColor(Color textColor) {
		this.textColor = textColor;
	}

	/**
	 * @return the buttonHoverTextColor
	 */
	public Color getButtonHoverTextColor() {
		return buttonHoverTextColor;
	}

	/**
	 * @param buttonHoverTextColor
	 *            the buttonHoverTextColor to set
	 */
	public void setButtonHoverTextColor(Color buttonHoverTextColor) {
		this.buttonHoverTextColor = buttonHoverTextColor;
	}

	/**
	 * @return the buttonClickTextColor
	 */
	public Color getButtonClickTextColor() {
		return buttonClickTextColor;
	}

	/**
	 * @param buttonClickTextColor
	 *            the buttonClickTextColor to set
	 */
	public void setButtonClickTextColor(Color buttonClickTextColor) {
		this.buttonClickTextColor = buttonClickTextColor;
	}

	/**
	 * @return the buttonColor
	 */
	public Color getButtonColor() {
		return buttonColor;
	}

	/**
	 * @param buttonColor
	 *            the buttonColor to set
	 */
	public void setButtonColor(Color buttonColor) {
		this.buttonColor = buttonColor;
	}

	/**
	 * @return the buttonHoverColor
	 */
	public Color getButtonHoverColor() {
		return buttonHoverColor;
	}

	/**
	 * @param buttonHoverColor
	 *            the buttonHoverColor to set
	 */
	public void setButtonHoverColor(Color buttonHoverColor) {
		this.buttonHoverColor = buttonHoverColor;
	}

	/**
	 * @return the buttonClickColor
	 */
	public Color getButtonClickColor() {
		return buttonClickColor;
	}

	/**
	 * @param buttonClickColor
	 *            the buttonClickColor to set
	 */
	public void setButtonClickColor(Color buttonClickColor) {
		this.buttonClickColor = buttonClickColor;
	}

	/**
	 * @return the cornerRadius
	 */
	public int getCornerRadius() {
		return cornerRadius;
	}

	/**
	 * @param cornerRadius
	 *            the cornerRadius to set
	 */
	public void setCornerRadius(int cornerRadius) {
		this.cornerRadius = cornerRadius;
	}

}
