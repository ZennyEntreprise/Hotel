package com.game.zenny.zh;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.game.zenny.zh.element.Sprite;
import com.game.zenny.zh.scene.Game;

public class App extends StateBasedGame {

	//// STATIC
	public static int WINDOW_WIDTH = 1366;
	public static int WINDOW_HEIGHT = 768;
	
	private static Sprite sprite;
	
	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new App(), WINDOW_WIDTH, WINDOW_HEIGHT, false);
	    app.setMaximumLogicUpdateInterval(60);
	    app.setUpdateOnlyWhenVisible(false);
	    app.setTargetFrameRate(60);
	    app.setShowFPS(true);
	    app.setVSync(true);
	    app.setAlwaysRender(true);
	    app.start();
	    
	}
	
	public static Sprite getSprites() {
		if (sprite == null)
			try {
				sprite = new Sprite();
			} catch (SlickException e) {
				e.printStackTrace();
			}
		
		return sprite;
	}
	
	//// ENUM
	// -- SCENES
	public static enum Scene {
		GAME(1);
		
		private int sceneID;
		
		Scene(int sceneID) {
			this.sceneID = sceneID;
		}
		
		public int getSceneID() {
			return sceneID;
		}
	}
	
	//// OBJECT
	// -- APP
	public App() {
		super("Zenny Hotel");
		
		addState(new Game());
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		getState(App.Scene.GAME.getSceneID()).init(gc, this);
		enterState(App.Scene.GAME.getSceneID());
	}

}
