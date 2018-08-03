package com.game.zenny.zh.scene;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

import com.game.zenny.zh.util.ZennyColor;

public abstract class Scene implements GameState {

	private int sceneID;

	public Scene(int sceneID) {
		this.sceneID = sceneID;
	}

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
		return sceneID;
	}

	protected boolean initialized = false;
	
	/**
	 * @param gc
	 * @param sbg
	 */
	public abstract void initScene(GameContainer gc, StateBasedGame sbg);

	/**
	 * @param gc
	 * @param sbg
	 * @throws SlickException
	 */
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		if (!initialized)
			initialized = true;
		else
			return;
		
		initScene(gc, sbg);
	}

	/**
	 * @param gc
	 * @param sbg
	 */
	public abstract void leaveScene(GameContainer gc, StateBasedGame sbg);

	/**
	 * @param gc
	 * @param sbg
	 * @throws SlickException
	 */
	@Override
	public void leave(GameContainer gc, StateBasedGame sbg) throws SlickException {
		leaveScene(gc, sbg);
	}

	/**
	 * @param gc
	 * @param sbg
	 * @param g
	 * @throws SlickException 
	 */
	public abstract void renderScene(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException;

	/**
	 * @param gc
	 * @param sbg
	 * @param g
	 * @throws SlickException
	 */
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// TODO [HOTEL] RENDER GUI ELEMENTS

		g.setBackground(ZennyColor.BACKGROUND_COLOR.getColor());
		
		renderScene(gc, sbg, g);
	}

	/**
	 * @param gc
	 * @param sbg
	 * @param delta
	 * @throws SlickException 
	 */
	public abstract void updateScene(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException;

	/**
	 * @param gc
	 * @param sbg
	 * @param delta
	 * @throws SlickException
	 */
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		// TODO [HOTEL] UPDATE GUI ELEMENTS

		updateScene(gc, sbg, delta);
	}

}
