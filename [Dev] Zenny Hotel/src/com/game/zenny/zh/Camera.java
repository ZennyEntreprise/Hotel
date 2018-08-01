package com.game.zenny.zh;

import com.game.zenny.zh.util.LinearInterpolation;
import com.game.zenny.zh.util.ZennyMath;

/**
 * @author Zengetsu_
 *
 */
public class Camera {

	private static float x = 0;
	private static float y = 0;
	private static float relativeX = 0;
	private static float relativeY = 0;

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
	public static void setX(float x) {
		Camera.x = x;
	}

	/**
	 * @param x
	 */
	public static void addX(float x) {
		Camera.x += x;
	}

	/**
	 * @param x
	 */
	public static void subX(float x) {
		Camera.x -= x;
	}

	/**
	 * @param y
	 */
	public static void setY(float y) {
		Camera.y = y;
	}

	/**
	 * @param y
	 */
	public static void addY(float y) {
		Camera.y += y;
	}

	/**
	 * @param y
	 */
	public static void subY(float y) {
		Camera.y -= y;
	}

	/**
	 * @param relativeX
	 */
	public static void setRelativeX(float relativeX) {
		Camera.relativeX = relativeX;
	}

	/**
	 * @param relativeX
	 */
	public static void addRelativeX(float relativeX) {
		Camera.relativeX += relativeX;
	}

	/**
	 * @param relativeX
	 */
	public static void subRelativeX(float relativeX) {
		Camera.relativeX -= relativeX;
	}

	/**
	 * @param relativeY
	 */
	public static void setRelativeY(float relativeY) {
		Camera.relativeY = relativeY;
	}

	/**
	 * @param relativeY
	 */
	public static void addRelativeY(float relativeY) {
		Camera.relativeY += relativeY;
	}

	/**
	 * @param relativeY
	 */
	public static void subRelativeY(float relativeY) {
		Camera.relativeY -= relativeY;
	}

	/**
	 * @return the resetingRelativePos
	 */
	public static boolean isResetingRelativePos() {
		return resetingRelativePos;
	}

	/**
	 * @param resetingRelativePos the resetingRelativePos to set
	 */
	
	public static void setResetingRelativePos(boolean resetingRelativePos) {
		Camera.resetingRelativePos = resetingRelativePos;
	}

	
	/**
	 * Reset relative pos to 0
	 */
	public static void resetRelativePos() {
		if (!resetingRelativePos) {
			resetingRelativePos = true;
			resetingPosLinearInterpolationX = new LinearInterpolation(relativeX, 0, (float) ZennyMath.distance(relativeX, relativeY, 0, 0) * 0.1f);
			resetingPosLinearInterpolationY = new LinearInterpolation(relativeY, 0, (float) ZennyMath.distance(relativeX, relativeY, 0, 0) * 0.1f);
		}
		if (resetingPosLinearInterpolationX == null) {
			resetingPosLinearInterpolationX = new LinearInterpolation(relativeX, 0, (float) ZennyMath.distance(relativeX, relativeY, 0, 0) * 0.1f);
		}
		if (resetingPosLinearInterpolationY == null) {
			resetingPosLinearInterpolationY = new LinearInterpolation(relativeY, 0, (float) ZennyMath.distance(relativeX, relativeY, 0, 0) * 0.1f);
		}
		
		relativeX = resetingPosLinearInterpolationX.interpolate();
		relativeY = resetingPosLinearInterpolationY.interpolate();
		
		if (resetingPosLinearInterpolationX.isFinished() && resetingPosLinearInterpolationY.isFinished()) {
			resetingRelativePos = false;
			return;
		}
	}

}
