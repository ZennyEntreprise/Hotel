package com.game.zenny.zh.scene;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

import com.game.zenny.zh.AppClient;
import com.game.zenny.zh.Camera;
import com.game.zenny.zh.gui.Component;
import com.game.zenny.zh.gui.TextField;
import com.game.zenny.zh.util.ZennyColor;
import com.game.zenny.zh.util.ZennyMouse;

public abstract class Scene implements GameState {

	private AppClient app;
	private int sceneID;
	protected boolean initialized = false;
	protected boolean leaved = false;
	protected boolean debug = false;
	private ArrayList<Component> guiComponents = new ArrayList<Component>();
	private Component selectedComponent = null;
	
	/**
	 * @param app
	 * @param sceneID
	 */
	public Scene(AppClient app, int sceneID) {
		this.app = app;
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
		return true;
	}

	@Override
	public void setInput(Input arg0) {
	}

	@Override
	public void keyPressed(int key, char c) {
		if (selectedComponent instanceof TextField) {
			if (Character.isLetter(c) || Character.isDigit(c) || key == 57 || key == 11 || key == 52 || key == 83) {
				((TextField) selectedComponent).addChar(c);
			} else if (key == 14) {
				((TextField) selectedComponent).removeLastChar();
			}
		}
	}

	@Override
	public void keyReleased(int key, char c) {
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
	public int getID() {
		return sceneID;
	}

	@Override
	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
	}

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
		if (!leaved) {
			leaved = true;
			return;
		}

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
		for (int i = guiComponents.size() - 1; i >= 0; i--)
			if (guiComponents.get(i).isRenderByScene())
				guiComponents.get(i).renderComponent(gc, sbg, g);

		g.setAntiAlias(true);
		g.setBackground(ZennyColor.BACKGROUND_COLOR.getColor());

		renderScene(gc, sbg, g);
		
		if (debug) {
			g.drawLine(0, gc.getHeight() / 2, gc.getWidth(), gc.getHeight() / 2);
			g.drawLine(gc.getWidth() / 2, 0, gc.getWidth() / 2, gc.getHeight());
			g.setColor(Color.white);
			g.drawString("Camera: Real X: " +   Camera.getRealX()   + "     Real Y: " + Camera.getRealY(), 10, 30);
			g.drawString("             X: " +     Camera.getX()     + "          Y: " + Camera.getY(), 10, 50);
			g.drawString("    Relative X: " + Camera.getRelativeX() + " Relative Y: " + Camera.getRelativeY(), 10, 70);
			g.drawString("Mouse: X: " + ZennyMouse.getMapX() + " Y: " + ZennyMouse.getMapY(), 10, 90);
		}
	}

	/**
	 * @param gc
	 * @param sbg
	 * @param delta
	 * @throws SlickException
	 */
	public abstract void updateScene(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException;

	private boolean componentClicked = false;

	/**
	 * @param gc
	 * @param sbg
	 * @param delta
	 * @throws SlickException
	 */
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Component focusedComponent = null;

		for (int i = 0; i < guiComponents.size(); i++) {
			Component component = guiComponents.get(i);
			if (ZennyMouse.getX() >= component.getX() - component.getWidth() / 2
					&& ZennyMouse.getX() <= component.getX() - component.getWidth() / 2 + component.getWidth()
					&& ZennyMouse.getY() >= component.getY() - component.getHeight() / 2
					&& ZennyMouse.getY() <= component.getY() - component.getHeight() / 2 + component.getHeight()) {

				if (focusedComponent == null) {
					focusedComponent = component;
					component.setFocused(true);
				} else {
					component.setFocused(false);
					component.setClicked(false);
				}

				if (Mouse.isButtonDown(0)) {
					if (!componentClicked && !component.isDisabled()) {
						componentClicked = true;
						component.setClicked(true);
						component.componentClickAction();
						selectedComponent = component;
					}
				} else {
					if (componentClicked) {
						componentClicked = false;
						component.setClicked(false);
					}
				}
			} else {
				component.setFocused(false);
				component.setClicked(false);

				if (Mouse.isButtonDown(0) && !componentClicked) {
					selectedComponent = null;
				}
			}

			component.updateComponent(gc, sbg, delta);
		}

		if (gc.getInput().isKeyPressed(Input.KEY_MULTIPLY)) {
			debug = !debug;
			AppClient.appGameContainer.setShowFPS(debug);
		}
		
		updateScene(gc, sbg, delta);
	}

	/**
	 * Hide scene with fade from transparent to black
	 */
	public void hide() {
		// TODO [HOTEL CLIENT] HIDE fade
	}

	/**
	 * Show scene with fade from black to transparent
	 */
	public void show() {
		// TODO [HOTEL CLIENT] SHOW fade
	}

	/**
	 * @return the guiComponents
	 */
	public ArrayList<Component> getGuiComponents() {
		return guiComponents;
	}

	/**
	 * @param guiComponents
	 *            the guiComponents to set
	 */
	public void setGuiComponents(ArrayList<Component> guiComponents) {
		this.guiComponents = guiComponents;
	}

	/**
	 * @return the app
	 */
	public AppClient getApp() {
		return app;
	}

	/**
	 * @param app
	 *            the app to set
	 */
	public void setApp(AppClient app) {
		this.app = app;
	}

	/**
	 * @return the selectedComponent
	 */
	public Component getSelectedComponent() {
		return selectedComponent;
	}

	/**
	 * @param selectedComponent
	 *            the selectedComponent to set
	 */
	public void setSelectedComponent(Component selectedComponent) {
		this.selectedComponent = selectedComponent;
	}

}
