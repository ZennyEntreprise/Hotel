package com.game.zenny.zh.client.util;

public class LinearInterpolation {

	private float minValue, maxValue, maxStep, step;
	private boolean finished;

	/**
	 * @param minValue
	 * @param maxValue
	 * @param maxStep
	 */
	public LinearInterpolation(float minValue, float maxValue, float maxStep) {
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.maxStep = maxStep;
		this.step = 0;
		this.finished = false;
	}

	public float interpolate() {
		float i = ZennyMath.lerp(minValue, maxValue, ZennyMath.map(step, 0, maxStep, 0, 1));
		step++;

		if (step > maxStep) {
			step--;
			finished = true;
		}

		return i;
	}

	/**
	 * @return the minValue
	 */
	public float getMinValue() {
		return minValue;
	}

	/**
	 * @param minValue
	 *            the minValue to set
	 */
	public void setMinValue(float minValue) {
		this.minValue = minValue;
	}

	/**
	 * @return the maxValue
	 */
	public float getMaxValue() {
		return maxValue;
	}

	/**
	 * @param maxValue
	 *            the maxValue to set
	 */
	public void setMaxValue(float maxValue) {
		this.maxValue = maxValue;
	}

	/**
	 * @return the maxStep
	 */
	public float getMaxStep() {
		return maxStep;
	}

	/**
	 * @param maxStep
	 *            the maxStep to set
	 */
	public void setMaxStep(float maxStep) {
		this.maxStep = maxStep;
	}

	/**
	 * @return the step
	 */
	public float getStep() {
		return step;
	}

	/**
	 * @param step
	 *            the step to set
	 */
	public void setStep(float step) {
		this.step = step;
	}

	/**
	 * @return the finished
	 */
	public boolean isFinished() {
		return finished;
	}

	/**
	 * @param finished
	 *            the finished to set
	 */
	public void setFinished(boolean finished) {
		this.finished = finished;
	}

}
