package com.game.zenny.zh.appartment;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.state.StateBasedGame;

import com.game.zenny.zh.App;

public class AppartmentStructure {

	private AppartmentGroundCell[][] structure;

	//// CONSTUCTORS

	public AppartmentStructure() {
		structure = new AppartmentGroundCell[10][10];
		for (int y = 0; y < structure.length; y++) {
			for (int x = 0; x < structure[y].length; x++) {
				structure[y][x] = new AppartmentGroundCell(x, y);
			}
		}

		determineThickness();
	}

	/**
	 * @param structure
	 */
	public AppartmentStructure(AppartmentGroundCell[][] structure) {
		this.structure = structure;

		determineThickness();
	}

	//// GETTERS AND SETTERS

	/**
	 * @return the structure
	 */
	public AppartmentGroundCell[][] getStructure() {
		return structure;
	}

	/**
	 * @param structure
	 *            the structure to set
	 */
	public void setStructure(AppartmentGroundCell[][] structure) {
		this.structure = structure;

		determineThickness();
	}

	//// PARTICULAR

	/**
	 * @param x
	 * @param y
	 * @return x coordinate
	 */
	public float getCellXCoordinate(int x, int y) {
		float wd = App.getSprites().ground.getWidth() / 2 - 1;

		float cx = x;
		cx = cx * App.getSprites().ground.getWidth();
		cx = cx - (y + x) * wd;
		cx = cx - 2 * x;
		cx = cx - wd;

		return cx;
	}

	/**
	 * @param x
	 * @param y
	 * @return y coordinate
	 */
	public float getCellYCoordinate(int x, int y) {
		float hd = App.getSprites().ground.getHeight() / 2 + 1;

		float cy = y;
		cy = cy * App.getSprites().ground.getHeight();
		cy = cy + x * hd;
		cy = cy - y * hd;
		cy = cy - x - hd;

		return cy;
	}

	/**
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isWalkable(int x, int y) {
		try {
			return structure[y][x].isActivated();
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
	}

	/**
	 * @param x
	 * @param y
	 */
	public void determineThickness(int x, int y) {
		if (!isWalkable(x, y + 1))
			structure[y][x].setGroundThicknessTL(true);

		if (!isWalkable(x + 1, y))
			structure[y][x].setGroundThicknessBL(true);
	}

	public void determineThickness() {
		for (int y = 0; y < structure.length; y++) {
			for (int x = 0; x < structure[y].length; x++) {
				determineThickness(x, y);
			}
		}
	}

	//// GAME METHODS

	/**
	 * @param gc
	 * @param sbg
	 * @param g
	 * @throws SlickException
	 */
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		for (int y = 0; y < structure.length; y++) {
			for (int x = 0; x < structure[y].length; x++) {
				structure[y][x].render(gc, sbg, g, new Point(getCellXCoordinate(x, y), getCellYCoordinate(x, y)));
			}
		}
	}

	/**
	 * @param gc
	 * @param sbg
	 * @param delta
	 * @throws SlickException
	 */
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		for (int y = 0; y < structure.length; y++) {
			for (int x = 0; x < structure[y].length; x++) {
				structure[y][x].update(gc, sbg, delta, new Point(getCellXCoordinate(x, y), getCellYCoordinate(x, y)));
			}
		}
	}
}
