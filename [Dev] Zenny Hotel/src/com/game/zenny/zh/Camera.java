package com.game.zenny.zh;

import com.game.zenny.zh.util.LinearInterpolation;
import com.game.zenny.zh.util.ZennyMath;

/**
 * @author Zengetsu_
 *
 */
public class Camera {

	private static int x = 0;
	private static int y = 0;
	private static int relativeX = 0;
	private static int relativeY = 0;

	private static boolean resetingRelativePos = false;
	private static LinearInterpolation resetingPosLinearInterpolationX;
	private static LinearInterpolation resetingPosLinearInterpolationY;

	/**
	 * @return real cam x
	 */
	public static int getRealX() {
		return Math.round(x + relativeX);
	}

	/**
	 * @return real cam y
	 */
	public static int getRealY() {
		return Math.round(y + relativeY);
	}

	/**
	 * @param x
	 */
	public static void setX(int x) {
		Camera.x = x;
	}

	/**
	 * @param x
	 */
	public static void addX(int x) {
		Camera.x += x;
	}

	/**
	 * @param x
	 */
	public static void subX(int x) {
		Camera.x -= x;
	}

	/**
	 * @return x
	 */
	public static int getX() {
		return x;
	}

	/**
	 * @param y
	 */
	public static void setY(int y) {
		Camera.y = y;
	}

	/**
	 * @param y
	 */
	public static void addY(int y) {
		Camera.y += y;
	}

	/**
	 * @param y
	 */
	public static void subY(int y) {
		Camera.y -= y;
	}

	/**
	 * @return y
	 */
	public static int getY() {
		return y;
	}

	/**
	 * @param relativeX
	 */
	public static void setRelativeX(int relativeX) {
		Camera.relativeX = relativeX;
	}

	/**
	 * @param relativeX
	 */
	public static void addRelativeX(int relativeX) {
		Camera.relativeX += relativeX;
	}

	/**
	 * @param relativeX
	 */
	public static void subRelativeX(int relativeX) {
		Camera.relativeX -= relativeX;
	}

	/**
	 * @return relative x
	 */
	public static int getRelativeX() {
		return relativeX;
	}

	/**
	 * @param relativeY
	 */
	public static void setRelativeY(int relativeY) {
		Camera.relativeY = relativeY;
	}

	/**
	 * @param relativeY
	 */
	public static void addRelativeY(int relativeY) {
		Camera.relativeY += relativeY;
	}

	/**
	 * @param relativeY
	 */
	public static void subRelativeY(int relativeY) {
		Camera.relativeY -= relativeY;
	}

	/**
	 * @return relative y;
	 */
	public static int getRelativeY() {
		return relativeY;
	}

	/**
	 * @return the resetingRelativePos
	 */
	public static boolean isResetingRelativePos() {
		return resetingRelativePos;
	}

	/**
	 * @param resetingRelativePos
	 *            the resetingRelativePos to set
	 */
	public static void setResetingRelativePos(boolean resetingRelativePos) {
		Camera.resetingRelativePos = resetingRelativePos;
	}

	/**
	 * Reset relative pos to 0
	 */
	public static void resetRelativePos() {
		float steps = Math.round(ZennyMath.distance(relativeX, relativeY, 0, 0) * 0.1f);
		if (!resetingRelativePos) {
			resetingRelativePos = true;
			resetingPosLinearInterpolationX = new LinearInterpolation(relativeX, 0, steps);
			resetingPosLinearInterpolationY = new LinearInterpolation(relativeY, 0, steps);
		}
		if (resetingPosLinearInterpolationX == null) {
			resetingPosLinearInterpolationX = new LinearInterpolation(relativeX, 0, steps);
		}
		if (resetingPosLinearInterpolationY == null) {
			resetingPosLinearInterpolationY = new LinearInterpolation(relativeY, 0, steps);
		}

		relativeX = Math.round(resetingPosLinearInterpolationX.interpolate());
		relativeY = Math.round(resetingPosLinearInterpolationY.interpolate());

		if (resetingPosLinearInterpolationX.isFinished() && resetingPosLinearInterpolationY.isFinished()) {
			resetingRelativePos = false;
			return;
		}
	}

}
