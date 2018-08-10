package com.game.zenny.zh.client.util;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.game.zenny.zh.client.AppClient;
import com.game.zenny.zh.client.Camera;

public class ZennyImage extends Image {

	/**
	 * @param imagePath
	 * @throws SlickException
	 */
	public ZennyImage(String imagePath) throws SlickException {
		super("res/sprites/" + imagePath + ".png");
	}

	/**
	 * @return render x
	 */
	public float getRenderX(float x, boolean customDimension) {
		if (customDimension)
			return AppClient.WINDOW_WIDTH / 2 - Camera.getRealX() + x - AppClient.proportionalValueByWidth(this.getWidth()) / 2;
		return AppClient.WINDOW_WIDTH / 2 - Camera.getRealX() + x - this.getWidth() / 2;
	}

	/**
	 * @return render y
	 */
	public float getRenderY(float y, boolean customDimension) {
		if (customDimension)
			return AppClient.WINDOW_HEIGHT / 2 - Camera.getRealY() + y - AppClient.proportionalValueByHeight(this.getHeight()) / 2;
		return AppClient.WINDOW_HEIGHT / 2 - Camera.getRealY() + y - this.getHeight() / 2;
	}

	public void draw() {
		this.draw(0, 0);
	}

	/**
	 * @param x
	 * @param y
	 */
	public void draw(float x, float y) {
		if (drawable(x, y))
			super.draw(getRenderX(x, false), getRenderY(y, false));
	}

	/**
	 * @param x
	 * @param y
	 * @param filter
	 */
	public void draw(float x, float y, Color filter) {
		if (drawable(x, y))
			super.draw(getRenderX(x, false), getRenderY(y, false), filter);
	}

	/**
	 * @param x
	 * @param y
	 * @param filter
	 */
	public void draw(float x, float y, ZennyColor filter) {
		draw(x, y, filter.getColor());
	}

	/**
	 * @param x
	 * @param y
	 * @param newWidth
	 * @param newHeight
	 * @param filter
	 */
	public void draw(float x, float y, int newWidth, int newHeight, Color filter) {
		if (drawable(x, y))
			super.draw(getRenderX(x, true), getRenderY(y, true), newWidth, newHeight, filter);
	}

	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param filter
	 */
	public void draw(float x, float y, int width, int height, ZennyColor filter) {
		draw(x, y, width, height, filter.getColor());
	}

	/**
	 * @param x
	 * @param y
	 * @return if the image is drawable
	 */
	public boolean drawable(float x, float y) {
		if (getRenderX(x, false) + this.getWidth() < 0 || getRenderX(x, false) > AppClient.WINDOW_WIDTH
				|| getRenderY(y, false) + this.getHeight() < 0 || getRenderY(y, false) > AppClient.WINDOW_HEIGHT)
			return false;

		return true;
	}
}
