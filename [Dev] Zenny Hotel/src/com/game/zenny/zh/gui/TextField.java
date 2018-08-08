package com.game.zenny.zh.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.StateBasedGame;

import com.game.zenny.zh.AppClient;
import com.game.zenny.zh.scene.Scene;
import com.game.zenny.zh.util.ZennyColor;

public class TextField extends Component {

	private TrueTypeFont textFont = AppClient.getFont(AppClient.getFonts().OpenSans_BOLD, AppClient.proportionalValueByWidth(15));
	private TrueTypeFont placeHolderFont = AppClient.getFont(AppClient.getFonts().OpenSans_BOLD_ITALIC,
			AppClient.proportionalValueByWidth(15));
	private String text = "";
	private String placeHolder = "";
	private boolean password = false;
	private boolean acceptSpace = true;
	private boolean acceptAt = false;
	private boolean acceptDot = false;
	private int maxChars = 100;
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
			placeHolderFont.drawString(Math.round(getRealX() + AppClient.proportionalValueByWidth(10)),
									   Math.round(y - 1 + (height - textFont.getHeight(placeHolder)) / 2 - height / 2), 
									   placeHolder,
									   ZennyColor.GREY1.getColor());
		} else {
			String text = this.text;
			if (password) {
				String r = "";
				for (int i = 0; i < text.length(); i++) {
					r += "*";
				}
				text = r;
			}

			String t = "";
			for (int i = text.length() - 1; i >= 0; i--) {
				if (textFont.getWidth(t + text.split("")[i]) < width - 2 * AppClient.proportionalValueByWidth(10)) {
					t = text.split("")[i] + t;
				} else {
					continue;
				}
			}
			
			textFont.drawString(Math.round(getRealX() + AppClient.proportionalValueByWidth(10)),
								Math.round(y - 1 + (height - textFont.getHeight(t)) / 2 - height / 2), 
								t,
								ZennyColor.GREY3.getColor());

			if (getScene().getSelectedComponent() == this)
				textFont.drawString(Math.round(getRealX() + AppClient.proportionalValueByWidth(7) + textFont.getWidth(t)),
									Math.round(y - 1 + (height - textFont.getHeight("|")) / 2 - height / 2), 
									"|",
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
		if ((!acceptSpace && (c + "").equals(" ")) || (!acceptAt && (c + "").equals("@"))
				|| (!acceptDot && (c + "").equals(".")) || text.length() >= maxChars)
			return;
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
	 * @return the password
	 */
	public boolean isPassword() {
		return password;
	}

	/**
	 * @return the acceptSpace
	 */
	public boolean isAcceptSpace() {
		return acceptSpace;
	}

	/**
	 * @param acceptSpace
	 *            the acceptSpace to set
	 */
	public void setAcceptSpace(boolean acceptSpace) {
		this.acceptSpace = acceptSpace;
	}

	/**
	 * @return the acceptAt
	 */
	public boolean isAcceptAt() {
		return acceptAt;
	}

	/**
	 * @param acceptAt
	 *            the acceptAt to set
	 */
	public void setAcceptAt(boolean acceptAt) {
		this.acceptAt = acceptAt;
	}

	/**
	 * @return the acceptDot
	 */
	public boolean isAcceptDot() {
		return acceptDot;
	}

	/**
	 * @param acceptDot
	 *            the acceptDot to set
	 */
	public void setAcceptDot(boolean acceptDot) {
		this.acceptDot = acceptDot;
	}

	/**
	 * @return the maxChars
	 */
	public int getMaxChars() {
		return maxChars;
	}

	/**
	 * @param maxChars
	 *            the maxChars to set
	 */
	public void setMaxChars(int maxChars) {
		this.maxChars = maxChars;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(boolean password) {
		this.password = password;
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
