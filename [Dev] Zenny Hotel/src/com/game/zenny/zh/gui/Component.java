package com.game.zenny.zh.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import com.game.zenny.zh.scene.Scene;

public abstract class Component {

	private Scene scene;
	protected float x;
	protected float y;
	protected float width;
	protected float height;
	protected boolean focused;
	protected boolean clicked;

	/**
	 * @param scene
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Component(Scene scene, float x, float y, float width, float height) {
		this.scene = scene;
		this.scene.getGuiComponents().add(this);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.focused = false;
	}

	/**
	 * @param scene
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param focused
	 */
	public Component(Scene scene, float x, float y, float width, float height, boolean focused) {
		this.scene = scene;
		this.scene.getGuiComponents().add(this);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.focused = focused;
	}

	/**
	 * @return the scene
	 */
	public Scene getScene() {
		return scene;
	}

	/**
	 * @param scene
	 *            the scene to set
	 */
	public void setScene(Scene scene) {
		this.scene = scene;
	}

	/**
	 * @return the x
	 */
	public float getX() {
		return x;
	}

	/**
	 * @param x
	 *            the x to set
	 */
	public void setX(float x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public float getY() {
		return y;
	}

	/**
	 * @param y
	 *            the y to set
	 */
	public void setY(float y) {
		this.y = y;
	}

	/**
	 * @return the width
	 */
	public float getWidth() {
		return width;
	}

	/**
	 * @param width
	 *            the width to set
	 */
	public void setWidth(float width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public float getHeight() {
		return height;
	}

	/**
	 * @param height
	 *            the height to set
	 */
	public void setHeight(float height) {
		this.height = height;
	}

	/**
	 * @return the focused
	 */
	public boolean isFocused() {
		return focused;
	}

	/**
	 * @param focused
	 *            the focused to set
	 */
	public void setFocused(boolean focused) {
		this.focused = focused;
	}

	/**
	 * @return the clicked
	 */
	public boolean isClicked() {
		return clicked;
	}

	/**
	 * @param clicked
	 *            the clicked to set
	 */
	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}

	/**
	 * @param gc
	 * @param sbg
	 * @param g
	 */
	public abstract void renderComponent(GameContainer gc, StateBasedGame sbg, Graphics g);

	/**
	 * @param gc
	 * @param sbg
	 * @param delta
	 */
	public abstract void updateComponent(GameContainer gc, StateBasedGame sbg, int delta);
}
