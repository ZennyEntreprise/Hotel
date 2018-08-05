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

	private TrueTypeFont font = App.getFont(App.getFonts().OpenSans_BOLD, App.proportionalValueByWidth(23));
	private String text;
	private Color textColor = ZennyColor.WHITE.getColor(); 
	private Color buttonHoverTextColor = ZennyColor.WHITE.getColor(); 
	private Color buttonClickTextColor = ZennyColor.WHITE.getColor();
	private Color buttonColor;
	private Color buttonHoverColor; 
	private Color buttonClickColor;
	private int cornerRadius = 0;

	private Color bColor;
	private Color tColor = ZennyColor.WHITE.getColor();

	/**
	 * @param scene
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param text
	 */
	public Button(Scene scene, float x, float y, float width, float height, String text) {
		super(scene, x, y, width, height);

		this.text = text;
		this.setButtonColorAutomatic(ZennyColor.CYAN1.getColor());
		this.bColor = this.buttonColor;
	}

	/**
	 * @param gc
	 * @param sbg
	 * @param g
	 */
	@Override
	public void renderComponent(GameContainer gc, StateBasedGame sbg, Graphics g) {
		g.setColor(bColor);
		g.fillRoundRect(x - width / 2, y - height / 2, width, height, cornerRadius);

		font.drawString(Math.round(x + (width - font.getWidth(text)) / 2 - width / 2),
				Math.round(y - 1 + (height - font.getHeight(text)) / 2 - height / 2), text, tColor);
	}

	/**
	 * @param gc
	 * @param sbg
	 * @param delta
	 */
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
	 * @param buttonColor
	 */
	public void setButtonColorAutomatic(ZennyColor buttonColor) {
		setButtonColorAutomatic(buttonColor.getColor());
	}

	/**
	 * @param buttonColor
	 *            the buttonColor to set
	 */
	public void setButtonColorAutomatic(Color buttonColor) {
		this.buttonColor = buttonColor;
		this.buttonHoverColor = buttonColor.darker(0.07f);
		this.buttonClickColor = buttonColor.darker(0.14f);
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
