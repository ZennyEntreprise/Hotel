package com.game.zenny.zh.client.util;

public class ZennyMath {

	/**
	 * @param value
	 * @param istart
	 * @param istop
	 * @param ostart
	 * @param ostop
	 * @return mapped value
	 */
	public static float map(float value, float istart, float istop, float ostart, float ostop) {
		return ostart + (ostop - ostart) * ((value - istart) / (istop - istart));
	}

	/**
	 * @param value
	 * @param istart
	 * @param istop
	 * @param ostart
	 * @param ostop
	 * @return mapped value
	 */
	public static float map(double value, double istart, double istop, double ostart, double ostop) {
		return (float) (ostart + (ostop - ostart) * ((value - istart) / (istop - istart)));
	}
	
	/**
	 * @param v1
	 * @param v2
	 * @return distance between these two values
	 */
	public static float distance(float v1, float v2) {
		return Math.max(v1, v2) - Math.min(v1, v2);
	}
	
	/**
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return distance between these two points
	 */
	public static double distance(float x1, float y1, float x2, float y2) {
		return Math.sqrt(Math.pow(distance(x1, x2), 2) + Math.pow(distance(y1, y2), 2));
	}
	
	/**
	 * @param minValue
	 * @param maxValue
	 * @param a
	 * @return lerped value
	 */
	public static float lerp(float minValue, float maxValue, float a) {
		return (maxValue - minValue) * a + minValue;
	}
	
	/**
	 * a   c
	 * - = -
	 * b   d
	 *
	 * @param a
	 * @param b
	 * @param c
	 * @return d 
	 */
	public static float crossProduct(float a, float b, float c) {
		return b*c/a;
	}
}
