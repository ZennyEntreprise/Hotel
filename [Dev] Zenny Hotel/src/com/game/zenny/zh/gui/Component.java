package com.game.zenny.zh.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import com.game.zenny.zh.scene.Scene;

public abstract class Component {

	private Scene scene;
	protected float x = 0;
	protected float y = 0;
	protected float width;
	protected float height;
	protected boolean focused;
	protected boolean renderByScene = true;
	protected boolean clicked;
	protected boolean disabled;
	private Runnable clickAction;

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
	 * @return center x
	 */
	public float getRealX() {
		return x - width / 2;
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
	 * @return center y
	 */
	public float getRealY() {
		return y - height / 2;
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
	 * @return the renderByScene
	 */
	public boolean isRenderByScene() {
		return renderByScene;
	}

	/**
	 * @param renderByScene
	 *            the renderByScene to set
	 */
	public void setRenderByScene(boolean renderByScene) {
		this.renderByScene = renderByScene;
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
	 * @return the disabled
	 */
	public boolean isDisabled() {
		return disabled;
	}

	/**
	 * @param disabled
	 *            the disabled to set
	 */
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	/**
	 * @return the clickAction
	 */
	public Runnable getClickAction() {
		return clickAction;
	}

	/**
	 * @param clickAction
	 *            the clickAction to set
	 */
	public void setClickAction(Runnable clickAction) {
		this.clickAction = clickAction;
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

	/**
	 * Run clickAction
	 */
	public void componentClickAction() {
		if (clickAction != null)
			clickAction.run();
	}
}
