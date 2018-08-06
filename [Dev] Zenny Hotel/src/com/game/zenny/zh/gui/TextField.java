package com.game.zenny.zh.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.StateBasedGame;

import com.game.zenny.zh.App;
import com.game.zenny.zh.scene.Scene;
import com.game.zenny.zh.util.ZennyColor;

public class TextField extends Component {

	private TrueTypeFont textFont = App.getFont(App.getFonts().OpenSans_BOLD, App.proportionalValueByWidth(15));
	private TrueTypeFont placeHolderFont = App.getFont(App.getFonts().OpenSans_BOLD_ITALIC, App.proportionalValueByWidth(15));
	private String text = "";
	private String placeHolder = "";
	private Color backgroundColor = ZennyColor.WHITE.getColor();
	private int cornerRadius = 0;

	/**
	 * @param scene
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public TextField(Scene scene, float x, float y, float width, float height) {
		super(scene, x, y, width, height);
	}

	/**
	 * @param scene
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param text
	 */
	public TextField(Scene scene, float x, float y, float width, float height, String text) {
		super(scene, x, y, width, height);
		this.text = text;
	}

	/**
	 * @param scene
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param text
	 * @param placeHolder
	 */
	public TextField(Scene scene, float x, float y, float width, float height, String text, String placeHolder) {
		super(scene, x, y, width, height);
		this.text = text;
		this.placeHolder = placeHolder;
	}

	/**
	 * @param gc
	 * @param sbg
	 * @param g
	 */
	@Override
	public void renderComponent(GameContainer gc, StateBasedGame sbg, Graphics g) {
		g.setColor(backgroundColor);
		g.fillRoundRect(getRealX(), getRealY(), width, height, cornerRadius);

		if (text.equals("") && getScene().getSelectedComponent() != this) {
			placeHolderFont.drawString(Math.round(getRealX() + App.proportionalValueByWidth(10)),
					Math.round(y - 1 + (height - textFont.getHeight(placeHolder)) / 2 - height / 2), placeHolder,
					ZennyColor.GREY1.getColor());
		} else {
			textFont.drawString(Math.round(getRealX() + App.proportionalValueByWidth(10)),
					Math.round(y - 1 + (height - textFont.getHeight(text)) / 2 - height / 2), text,
					ZennyColor.GREY3.getColor());

			if (getScene().getSelectedComponent() == this)
				textFont.drawString(Math.round(getRealX() + App.proportionalValueByWidth(7) + textFont.getWidth(text)),
						Math.round(y - 1 + (height - textFont.getHeight("|")) / 2 - height / 2), "|",
						ZennyColor.GREY3.getColor());
		}
	}

	/**
	 * @param gc
	 * @param sbg
	 * @param delta
	 */
	@Override
	public void updateComponent(GameContainer gc, StateBasedGame sbg, int delta) {

	}

	/**
	 * @param c
	 */
	public void addChar(char c) {
		text += c;
	}

	public void removeLastChar() {
		if (text.length() > 0)
			text = text.substring(0, text.length() - 1);
	}

	/**
	 * @return the textFont
	 */
	public TrueTypeFont getTextFont() {
		return textFont;
	}

	/**
	 * @param textFont
	 *            the textFont to set
	 */
	public void setTextFont(TrueTypeFont textFont) {
		this.textFont = textFont;
	}

	/**
	 * @return the placeHolderFont
	 */
	public TrueTypeFont getPlaceHolderFont() {
		return placeHolderFont;
	}

	/**
	 * @param placeHolderFont
	 *            the placeHolderFont to set
	 */
	public void setPlaceHolderFont(TrueTypeFont placeHolderFont) {
		this.placeHolderFont = placeHolderFont;
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
	 * @return the placeHolder
	 */
	public String getPlaceHolder() {
		return placeHolder;
	}

	/**
	 * @param placeHolder
	 *            the placeHolder to set
	 */
	public void setPlaceHolder(String placeHolder) {
		this.placeHolder = placeHolder;
	}

	/**
	 * @return the backgroundColor
	 */
	public Color getBackgroundColor() {
		return backgroundColor;
	}

	/**
	 * @param backgroundColor
	 *            the backgroundColor to set
	 */
	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	
	/**
	 * @param backgroundColor
	 *            the backgroundColor to set
	 */
	public void setBackgroundColor(ZennyColor backgroundColor) {
		this.backgroundColor = backgroundColor.getColor();
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
