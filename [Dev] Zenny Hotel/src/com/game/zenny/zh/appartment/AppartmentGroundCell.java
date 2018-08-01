package com.game.zenny.zh.appartment;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.state.StateBasedGame;

import com.game.zenny.zh.App;
import com.game.zenny.zh.element.ZHImage;
import com.game.zenny.zh.util.ZennyMath;
import com.game.zenny.zh.util.ZennyMouse;

public class AppartmentGroundCell {

	private int x, y;
	private boolean activated;
	private Color color = new Color(142, 68, 173);

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
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g, Point cellCooridnates) {
		if (!activated)
			return;

		ZHImage ground = App.getSprites().ground;
		float wd = ground.getWidth() / 2 - 1;
		float hd = ground.getHeight() / 2 + 1;

		if (ground.drawable(cellCooridnates.getX(), cellCooridnates.getY())) {
			ground.draw(cellCooridnates.getX(), cellCooridnates.getY(), color);

			if (selected) {
				App.getSprites().groundSelection.draw(cellCooridnates.getX() + 5, cellCooridnates.getY() + 3);
				g.drawString("Selected cell: X: "+x+" Y: "+y, 10, 90);
			}
			
			if (x == 0 && y == 0) {
				ZHImage character = App.getSprites().character;
				character.draw(Math.round(cellCooridnates.getX() - character.getWidth() / 2 + wd), Math.round(cellCooridnates.getY() - character.getHeight() + hd + hd / 2));
			}
		}

		if (groundThicknessTL)
			App.getSprites().groundThicknessTL.draw(cellCooridnates.getX(), cellCooridnates.getY() + hd, color);

		if (groundThicknessBL)
			App.getSprites().groundThicknessBL.draw(cellCooridnates.getX() + wd + 1, cellCooridnates.getY() + hd,
					color);
	}

	/**
	 * @param gc
	 * @param sbg
	 * @param delta
	 */
	public void update(GameContainer gc, StateBasedGame sbg, int delta, Point cellCooridnates) {
		float swd = App.getSprites().ground.getWidth() / 2 - 2;
		float shd =App.getSprites().ground.getHeight() / 2;
		
		double d = ZennyMath.distance(cellCooridnates.getX() + swd, cellCooridnates.getY() + shd,
				ZennyMouse.getMapX(), ZennyMouse.getMapY());
		
		if (d < swd 
			&& ZennyMath.distance(ZennyMouse.getMapY(),cellCooridnates.getY() + shd) < 0.5 * ZennyMath.distance(ZennyMouse.getMapX(), cellCooridnates.getX() + swd) + shd
			&& ZennyMath.distance(ZennyMouse.getMapY(),cellCooridnates.getY() + shd) < -0.5 * ZennyMath.distance(ZennyMouse.getMapX(), cellCooridnates.getX() + swd) + shd
			&& ZennyMath.distance(ZennyMouse.getMapY(),cellCooridnates.getY() + shd) > -0.5 * ZennyMath.distance(ZennyMouse.getMapX(), cellCooridnates.getX() + swd) - shd
			&& ZennyMath.distance(ZennyMouse.getMapY(),cellCooridnates.getY() + shd) > 0.5 * ZennyMath.distance(ZennyMouse.getMapX(), cellCooridnates.getX() + swd) - shd)
		{
			selected = true;
			
			if (Mouse.isButtonDown(0)) {
				color = new Color(230, 126, 34);
			}
		} else {
			selected = false;
		}
	}
}
