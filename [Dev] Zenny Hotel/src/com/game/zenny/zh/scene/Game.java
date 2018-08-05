package com.game.zenny.zh.scene;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.game.zenny.zh.App;
import com.game.zenny.zh.Camera;
import com.game.zenny.zh.appartment.Appartment;
import com.game.zenny.zh.util.ZennyMouse;

public class Game extends Scene {

	private Appartment appartment;
	private boolean debug = false;
	
	public Game(App app) {
		super(app, App.Scenes.GAME.getSceneID());
	}

	@Override
	public void initScene(GameContainer gc, StateBasedGame sbg) {
		appartment = new Appartment();
	}

	@Override
	public void leaveScene(GameContainer gc, StateBasedGame sbg) {
		
	}

	@Override
	public void renderScene(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		appartment.render(gc, sbg, g);
		
		if (debug) {
			g.drawLine(0, gc.getHeight() / 2, gc.getWidth(), gc.getHeight() / 2);
			g.drawLine(gc.getWidth() / 2, 0, gc.getWidth() / 2, gc.getHeight());
			g.setColor(Color.white);
			g.drawString("Camera: Real X: " +   Camera.getRealX()   + "     Real Y: " + Camera.getRealY(), 10, 50);
			g.drawString("             X: " +     Camera.getX()     + "          Y: " + Camera.getY(), 10, 70);
			g.drawString("    Relative X: " + Camera.getRelativeX() + " Relative Y: " + Camera.getRelativeY(), 10, 90);
			g.drawString("Mouse: X: " + ZennyMouse.getMapX() + " Y: " + ZennyMouse.getMapY(), 10, 110);
		}
	}

	@Override
	public void updateScene(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		appartment.update(gc, sbg, delta);
		
		if (gc.getInput().isKeyPressed(Input.KEY_D)) {
			debug = !debug;
		}
	}
}
