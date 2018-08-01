package com.game.zenny.zh.element;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.game.zenny.zh.App;
import com.game.zenny.zh.Camera;

public class ZHImage extends Image {

	/**
	 * @param imagePath
	 * @throws SlickException
	 */
	public ZHImage(String imagePath) throws SlickException {
		super("res/sprites/" + imagePath + ".png");
	}

	/**
	 * @return render x
	 */
	public float getRenderX(float x) {
		return App.WINDOW_WIDTH / 2 - Camera.getRealX() + x - this.getWidth() / 2;
	}

	/**
	 * @return render y
	 */
	public float getRenderY(float y) {
		return App.WINDOW_HEIGHT / 2 - Camera.getRealY() + y - this.getHeight() / 2;
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
			super.draw(getRenderX(x), getRenderY(y));
	}

	/**
	 * @param x
	 * @param y
	 * @param filter
	 */
	public void draw(float x, float y, Color filter) {
		if (drawable(x, y))
			super.draw(getRenderX(x), getRenderY(y), filter);
	}

	/**
	 * @param x
	 * @param y
	 * @return if the image is drawable
	 */
	public boolean drawable(float x, float y) {
		if (getRenderX(x) + this.getWidth() < 0 || getRenderX(x) > App.WINDOW_WIDTH
				|| getRenderY(y) + this.getHeight() < 0 || getRenderY(y) > App.WINDOW_HEIGHT)
			return false;

		return true;
	}
}
