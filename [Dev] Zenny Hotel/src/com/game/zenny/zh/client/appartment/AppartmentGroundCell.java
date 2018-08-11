package com.game.zenny.zh.client.appartment;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.state.StateBasedGame;

import com.game.zenny.zh.client.AppClient;
import com.game.zenny.zh.client.util.ZennyColor;
import com.game.zenny.zh.client.util.ZennyImage;
import com.game.zenny.zh.client.util.ZennyMath;
import com.game.zenny.zh.client.util.ZennyMouse;

public class AppartmentGroundCell {

	private int x, y;
	private boolean activated;
	private Color color;

	private boolean groundThicknessTL;
	private boolean groundThicknessBL;

	private boolean selected = false;

	//// CONSTRCUTORS

	/**
	 * @param x
	 * @param y
	 */
	public AppartmentGroundCell(int x, int y) {
		this.x = x;
		this.y = y;
		this.activated = true;
		this.color = ZennyColor.BEIGE.getColor();
		this.groundThicknessTL = false;
		this.groundThicknessBL = false;
	}

	/**
	 * @param x
	 * @param y
	 * @param groundThicknessTL
	 * @param groundThicknessBL
	 */
	public AppartmentGroundCell(int x, int y, boolean groundThicknessTL, boolean groundThicknessBL) {
		this.x = x;
		this.y = y;
		this.activated = true;
		this.color = ZennyColor.BEIGE.getColor();
		this.groundThicknessTL = groundThicknessTL;
		this.groundThicknessBL = groundThicknessBL;
	}

	/**
	 * @param x
	 * @param y
	 * @param activated
	 */
	public AppartmentGroundCell(int x, int y, boolean activated) {
		this.x = x;
		this.y = y;
		this.activated = activated;
		this.color = ZennyColor.BEIGE.getColor();
		this.groundThicknessTL = false;
		this.groundThicknessBL = false;
	}

	/**
	 * @param x
	 * @param y
	 * @param activated
	 * @param groundThicknessTL
	 * @param groundThicknessBL
	 */
	public AppartmentGroundCell(int x, int y, boolean activated, boolean groundThicknessTL, boolean groundThicknessBL) {
		this.x = x;
		this.y = y;
		this.activated = activated;
		this.color = ZennyColor.BEIGE.getColor();
		this.groundThicknessTL = groundThicknessTL;
		this.groundThicknessBL = groundThicknessBL;
	}

	//// GETTERS AND SETTERS

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x
	 *            the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y
	 *            the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the activated
	 */
	public boolean isActivated() {
		return activated;
	}

	/**
	 * @param activated
	 *            the activated to set
	 */
	public void setActivated(boolean activated) {
		this.activated = activated;
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
	public void setColor(ZennyColor color) {
		this.color = color.getColor();
	}

	/**
	 * @param color
	 *            the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * @return the groundThicknessTL
	 */
	public boolean isGroundThicknessTL() {
		return groundThicknessTL;
	}

	/**
	 * @param groundThicknessTL
	 *            the groundThicknessTL to set
	 */
	public void setGroundThicknessTL(boolean groundThicknessTL) {
		this.groundThicknessTL = groundThicknessTL;
	}

	/**
	 * @return the groundThicknessBL
	 */
	public boolean isGroundThicknessBL() {
		return groundThicknessBL;
	}

	/**
	 * @param groundThicknessBL
	 *            the groundThicknessBL to set
	 */
	public void setGroundThicknessBL(boolean groundThicknessBL) {
		this.groundThicknessBL = groundThicknessBL;
	}

	/**
	 * @return the selected
	 */
	public boolean isSelected() {
		return selected;
	}

	/**
	 * @param selected
	 *            the selected to set
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	//// GAME METHODS

	/**
	 * @param gc
	 * @param sbg
	 * @param g
	 */
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g, Point cellCoordinates) {
		if (!activated)
			return;

		float cx = cellCoordinates.getX();
		float cy = cellCoordinates.getY();

		ZennyImage ground = AppClient.getSprites().ground;
		if (ground.drawable(cx, cy)) {
			ground.draw(cx, cy, color);

			if (selected) {
				AppClient.getSprites().groundSelection.draw(cx, cy);
			}
		}

		ZennyImage groundThicknessTLImage = AppClient.getSprites().groundThicknessTL;
		if (groundThicknessTL)
			groundThicknessTLImage.draw(cx + groundThicknessTLImage.getWidth() / 2 - ground.getWidth() / 2,
					cy + groundThicknessTLImage.getHeight() / 2, color);

		ZennyImage groundThicknessBLImage = AppClient.getSprites().groundThicknessBL;
		if (groundThicknessBL)
			groundThicknessBLImage.draw(cx + groundThicknessTLImage.getWidth() / 2,
					cy + groundThicknessTLImage.getHeight() / 2, color);
	}

	/**
	 * @param gc
	 * @param sbg
	 * @param delta
	 */
	public void update(GameContainer gc, StateBasedGame sbg, int delta, Point cellCooridnates) {
		double d = ZennyMath.distance(cellCooridnates.getX(), cellCooridnates.getY(), ZennyMouse.getMapX(),
				ZennyMouse.getMapY());

		if (d < AppClient.getSprites().ground.getWidth() / 2
				&& ZennyMath.distance(ZennyMouse.getMapY(),
						cellCooridnates.getY()) < 0.5 * ZennyMath.distance(ZennyMouse.getMapX(), cellCooridnates.getX())
								+ AppClient.getSprites().ground.getHeight() / 2
				&& ZennyMath.distance(ZennyMouse.getMapY(),
						cellCooridnates.getY()) > 0.5 * ZennyMath.distance(ZennyMouse.getMapX(), cellCooridnates.getX())
								- AppClient.getSprites().ground.getHeight() / 2
				&& ZennyMath.distance(ZennyMouse.getMapY(),
						cellCooridnates.getY()) < -0.5
								* ZennyMath.distance(ZennyMouse.getMapX(), cellCooridnates.getX())
								+ AppClient.getSprites().ground.getHeight() / 2
				&& ZennyMath.distance(ZennyMouse.getMapY(),
						cellCooridnates.getY()) > -0.5
								* ZennyMath.distance(ZennyMouse.getMapX(), cellCooridnates.getX())
								- AppClient.getSprites().ground.getHeight() / 2) {

			selected = true;

			if (Mouse.isButtonDown(0)) {
				// TODO [HOTEL CLIENT] Move player
			}
		} else {
			selected = false;
		}
	}

}
