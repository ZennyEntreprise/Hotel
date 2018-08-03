package com.game.zenny.zh.scene;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.game.zenny.zh.App;
import com.game.zenny.zh.gui.Button;

public class StartMenu extends Scene {

	private Button connectButton;
	
	public StartMenu(App app) {
		super(app, App.Scene.START_MENU.getSceneID());
	}

	@Override
	public void initScene(GameContainer gc, StateBasedGame sbg) {
		connectButton = new Button(this, App.WINDOW_WIDTH / 2 - 100, App.WINDOW_HEIGHT / 2 - 25, 200, 50, "JOUER");
		connectButton.setCornerRadius(3);
		connectButton.setButtonColorAutomatic(new Color(194, 54, 22));
		connectButton.setClickAction(new Runnable() {
			@Override
			public void run() {
				getApp().enterScene(new Game(getApp()), gc);
			}
		});
	}

	@Override
	public void leaveScene(GameContainer gc, StateBasedGame sbg) {
		System.out.println("a");
	}

	@Override
	public void renderScene(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
	}

	@Override
	public void updateScene(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
	}

}
