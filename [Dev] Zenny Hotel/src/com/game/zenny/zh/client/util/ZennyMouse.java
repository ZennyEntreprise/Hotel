package com.game.zenny.zh.client.util;

import org.lwjgl.input.Mouse;

import com.game.zenny.zh.client.AppClient;
import com.game.zenny.zh.client.Camera;

public class ZennyMouse {

	/**
	 * @return mouse x
	 */
	public static int getX() {
		return Mouse.getX();
	}
	
	/**
	 * @return mouse x in map
	 */
	
	public static int getMapX() {
		return getX() - (AppClient.WINDOW_WIDTH / 2) + Camera.getRealX();
	}
	
	/**
	 * @return mouse x dir
	 */
	public static int getDX() {
		return Mouse.getDX();
	}
	
	/**
	 * @return mouse y
	 */
	public static int getY() {
		return Mouse.getY() * -1 + AppClient.WINDOW_HEIGHT;
	}
	
	/**
	 * @return mouse y in map
	 */
	public static int getMapY() {
		return getY() - AppClient.WINDOW_HEIGHT / 2 + Camera.getRealY();
	}
	
	/**
	 * @return mou y dir
	 */
	public static int getDY() {
		return Mouse.getDY();
	}
}
