package com.crypto.util;

import com.crypto.domain.CalcResult;

public class CalculationUtil {

	private int count = 0;
	private double average = 0.0;
	private double stdDev = 0.0;

	/**
	 * Incoming values used to calculate the running mean & standard deviation using WELFORD'S Algorithm
	 * 
	 * @param value
	 */
	public synchronized CalcResult pushAndRecalculate(Double value) {

		count++;
		double newAverage = average + (value - average) / count;
		double newStdDev = stdDev + (value - average) * (value - newAverage);

		average = newAverage;
		stdDev = newStdDev;
		double stdDeviationCalc = Math.sqrt(stdDev / (count));
		return new CalcResult(average,
				((stdDeviationCalc == 1) || Double.isNaN(stdDeviationCalc)) ? 0.0 : stdDeviationCalc);
	}

}
