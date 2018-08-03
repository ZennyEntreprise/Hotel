package com.game.zenny.zh.scene;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.game.zenny.zh.App;
import com.game.zenny.zh.gui.Button;

public class StartMenu extends Scene {

	private Button connectButton;
	
	public StartMenu() {
		super(App.Scene.START_MENU.getSceneID());
	}

	@Override
	public void initScene(GameContainer gc, StateBasedGame sbg) {
		connectButton = new Button(this, 50, 50, 200, 50, "Wesh nigga");
	}

	@Override
	public void leaveScene(GameContainer gc, StateBasedGame sbg) {
		
	}

	@Override
	public void renderScene(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
	}

	@Override
	public void updateScene(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
	}

}
