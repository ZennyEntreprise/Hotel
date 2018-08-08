package com.game.zenny.zh;

import java.awt.FontFormatException;
import java.io.IOException;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.StateBasedGame;

import com.game.zenny.zh.res.Fonts;
import com.game.zenny.zh.res.Sprites;
import com.game.zenny.zh.scene.Scene;
import com.game.zenny.zh.scene.StartMenu;
import com.game.zenny.zh.util.ZennyMath;

public class App extends StateBasedGame {

	//// STATIC
	public static AppGameContainer appGameContainer;

	public final static int DEV_WINDOW_WIDTH = 1280;
	public final static int DEV_WINDOW_HEIGHT = 720;
	public final static boolean devMode = true;
	public final static String serverDomain = "http://localhost/ZennyHotel/";
	
	public static int WINDOW_WIDTH = 1920;
	public static int WINDOW_HEIGHT = 1080;

	private static Fonts fonts;
	private static Sprites sprites;

	public static void main(String[] args) throws SlickException {
		boolean fullscreen = true;
		if (devMode) {
			WINDOW_WIDTH = DEV_WINDOW_WIDTH;
			WINDOW_HEIGHT = DEV_WINDOW_HEIGHT;
			fullscreen = false;
		}

		appGameContainer = new AppGameContainer(new App(), WINDOW_WIDTH, WINDOW_HEIGHT, fullscreen);
		appGameContainer.setMaximumLogicUpdateInterval(60);
		appGameContainer.setUpdateOnlyWhenVisible(false);
		appGameContainer.setTargetFrameRate(60);
		appGameContainer.setShowFPS(false);
		appGameContainer.setVSync(true);
		appGameContainer.setAlwaysRender(true);
		appGameContainer.start();
	}

	/**
	 * @return all fonts
	 */
	public static Fonts getFonts() {
		if (fonts == null)
			try {
				fonts = new Fonts();
			} catch (FontFormatException | IOException e) {
				e.printStackTrace();
			}

		return fonts;
	}

	/**
	 * @param font
	 * @param size
	 * @return truetype font from font
	 */
	public static TrueTypeFont getFont(java.awt.Font font, float size) {
		return App.getFonts().get(font, size);
	}

	/**
	 * @return all sprites
	 */
	public static Sprites getSprites() {
		if (sprites == null)
			try {
				sprites = new Sprites();
			} catch (SlickException e) {
				e.printStackTrace();
			}

		return sprites;
	}

	// -- MATH

	/**
	 * @param defaultValue
	 */
	public static float proportionalValueByWidth(float defaultValue) {
		return ZennyMath.crossProduct(DEV_WINDOW_WIDTH, defaultValue, WINDOW_WIDTH);
	}

	/**
	 * @param defaultValue
	 */
	public static float proportionalValueByHeight(float defaultValue) {
		return ZennyMath.crossProduct(DEV_WINDOW_HEIGHT, defaultValue, WINDOW_HEIGHT);
	}

	//// ENUM
	// -- SCENES
	public static enum Scenes {
		GAME(1), START_MENU(2);

		private int sceneID;

		Scenes(int sceneID) {
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
	}

	/**
	 * @param gc
	 */
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		enterScene(new StartMenu(this), gc);
	}

	public static void enterScene(Scene scene, GameContainer gc) {
		if (scene.getApp().getState(scene.getID()) == null)
			scene.getApp().addState(scene);

		try {
			scene.init(gc, scene.getApp());
		} catch (SlickException e) {
			e.printStackTrace();
		}

		scene.getApp().enterState(scene.getID());
	}

}
