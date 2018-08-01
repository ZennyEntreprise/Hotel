package com.game.zenny.zh;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

import com.game.zenny.zh.appartment.Appartment;
import com.game.zenny.zh.appartment.AppartmentGroundCell;
import com.game.zenny.zh.appartment.AppartmentStructure;

public class Game implements GameState {

	@Override
	public void mouseClicked(int arg0, int arg1, int arg2, int arg3) {

	}

	@Override
	public void mouseDragged(int arg0, int arg1, int arg2, int arg3) {

	}

	@Override
	public void mouseMoved(int arg0, int arg1, int arg2, int arg3) {

	}

	@Override
	public void mousePressed(int arg0, int arg1, int arg2) {

	}

	@Override
	public void mouseReleased(int arg0, int arg1, int arg2) {

	}

	@Override
	public void mouseWheelMoved(int arg0) {

	}

	@Override
	public void inputEnded() {

	}

	@Override
	public void inputStarted() {

	}

	@Override
	public boolean isAcceptingInput() {
		return false;
	}

	@Override
	public void setInput(Input arg0) {

	}

	@Override
	public void keyPressed(int arg0, char arg1) {

	}

	@Override
	public void keyReleased(int arg0, char arg1) {

	}

	@Override
	public void controllerButtonPressed(int arg0, int arg1) {
	}

	@Override
	public void controllerButtonReleased(int arg0, int arg1) {

	}

	@Override
	public void controllerDownPressed(int arg0) {

	}

	@Override
	public void controllerDownReleased(int arg0) {

	}

	@Override
	public void controllerLeftPressed(int arg0) {

	}

	@Override
	public void controllerLeftReleased(int arg0) {

	}

	@Override
	public void controllerRightPressed(int arg0) {

	}

	@Override
	public void controllerRightReleased(int arg0) {

	}

	@Override
	public void controllerUpPressed(int arg0) {

	}

	@Override
	public void controllerUpReleased(int arg0) {

	}

	@Override
	public void enter(GameContainer arg0, StateBasedGame arg1) throws SlickException {

	}

	@Override
	public int getID() {
		return App.Scene.GAME.getSceneID();
	}

	private boolean started = false;
	private Appartment appartment;
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		if (!started)
			started = true;
		else
			return;

		appartment = new Appartment();
	}

	@Override
	public void leave(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
	}

	private boolean debug = false;

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		appartment.render(gc, sbg, g);
		
		if (debug) {
			g.drawLine(0, gc.getHeight() / 2, gc.getWidth(), gc.getHeight() / 2);
			g.drawLine(gc.getWidth() / 2, 0, gc.getWidth() / 2, gc.getHeight());
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		appartment.update(gc, sbg, delta);
		
		if (gc.getInput().isKeyPressed(Input.KEY_D)) {
			debug = !debug;
		}
		
		if (gc.getInput().isKeyDown(Input.KEY_R)) {
			AppartmentGroundCell[][] structure = new AppartmentGroundCell[100][100];
			for (int y = 0; y < structure.length; y++) {
				for (int x = 0; x < structure[y].length; x++) {
					structure[y][x] = new AppartmentGroundCell(x, y);
				}
			}
			
			appartment.setStructure(new AppartmentStructure(structure));
		}
		
	}
}
