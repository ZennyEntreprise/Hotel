package com.game.zenny.zh.util;

public class ZennyMath {

	public static float map(float value, float istart, float istop, float ostart, float ostop) {
		return ostart + (ostop - ostart) * ((value - istart) / (istop - istart));
	}

	public static float distance(float v1, float v2) {
		return Math.max(v1, v2) - Math.min(v1, v2);
	}
	
	public static double distance(float x1, float y1, float x2, float y2) {
		return Math.sqrt(Math.pow(distance(x1, x2), 2) + Math.pow(distance(y1, y2), 2));
	}
	
	public static float lerp(float minValue, float maxValue, float a) {
		return (maxValue - minValue) * a + minValue;
	}
}
