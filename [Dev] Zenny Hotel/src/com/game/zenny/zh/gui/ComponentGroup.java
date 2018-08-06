package com.game.zenny.zh.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.ShapeFill;
import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.geom.RoundedRectangle;
import org.newdawn.slick.state.StateBasedGame;

import com.game.zenny.zh.scene.Scene;

public class ComponentGroup extends Component {

	private ShapeFill filler;
	private int cornerRadius = 0;
	
	/**
	 * @param scene
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public ComponentGroup(Scene scene, float x, float y, float width, float height, ShapeFill filler) {
		super(scene, x, y, width, height);
		
		this.filler = filler;
	}
	
	/**
	 * @param scene
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param filler
	 */
	public ComponentGroup(Scene scene, float x, float y, float width, float height, Color color) {
		super(scene, x, y, width, height);
		
		this.filler = new GradientFill(0, 0, color, width, height, color);
	}

	@Override
	public void renderComponent(GameContainer gc, StateBasedGame sbg, Graphics g) {
		g.fill(new RoundedRectangle(getRealX(), getRealY(), width, height, cornerRadius), filler);
	}

	@Override
	public void updateComponent(GameContainer gc, StateBasedGame sbg, int delta) {
		
	}

	/**
	 * @return the filler
	 */
	public ShapeFill getFiller() {
		return filler;
	}

	/**
	 * @param filler the filler to set
	 */
	public void setFiller(ShapeFill filler) {
		this.filler = filler;
	}

	/**
	 * @return the cornerRadius
	 */
	public int getCornerRadius() {
		return cornerRadius;
	}

	/**
	 * @param cornerRadius the cornerRadius to set
	 */
	public void setCornerRadius(int cornerRadius) {
		this.cornerRadius = cornerRadius;
	}

}
