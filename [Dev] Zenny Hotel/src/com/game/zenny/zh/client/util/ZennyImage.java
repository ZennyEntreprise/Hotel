package com.game.zenny.zh.client.util;


import java.io.InputStream;

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
	 * @param inputStream
	 * @throws SlickException
	 */
	public ZennyImage(InputStream in) throws SlickException {
		super(in, "", false);
	}

	/**
	 * @return render x
	 */
	public float getRenderX(float x) {
		return AppClient.WINDOW_WIDTH / 2 - Camera.getRealX() + x - this.getWidth() / 2;
	}

	/**
	 * @return render y
	 */
	public float getRenderY(float y) {
		return AppClient.WINDOW_HEIGHT / 2 - Camera.getRealY() + y - this.getHeight() / 2;
	}

	@Override
	public void draw() {
		this.draw(0, 0);
	}

	/**
	 * @param x
	 * @param y
	 */
	@Override
	public void draw(float x, float y) {
		if (drawable(x, y))
			super.draw(getRenderX(x), getRenderY(y));
	}

	/**
	 * @param x
	 * @param y
	 * @param filter
	 */
	@Override
	public void draw(float x, float y, Color filter) {
		if (drawable(x, y))
			super.draw(getRenderX(x), getRenderY(y), filter);
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
	 * @param width
	 * @param height
	 */
	public void drawWithNewDimension(float x, float y, float width, float height) {
		if (drawable(x, y))
			super.draw(getRenderX(x + this.getWidth() / 2 - width / 2), getRenderY(y + this.getHeight() / 2 - height / 2), width, height);
	}

	/**
	 * @param x
	 * @param y
	 * @return if the image is drawable
	 */
	public boolean drawable(float x, float y) {
		if (getRenderX(x) + this.getWidth() < 0 || getRenderX(x) > AppClient.WINDOW_WIDTH
				|| getRenderY(y) + this.getHeight() < 0 || getRenderY(y) > AppClient.WINDOW_HEIGHT)
			return false;

		return true;
	}
}
