package com.crypto.domain;

public class CalcResult {

	private Double average;

	private Double stdDev;

	private String encryptedAverage;

	private String encryptedStdDev;

	public CalcResult(Double average, Double stdDev) {
		this.average = average;
		this.stdDev = stdDev;
	}

	public CalcResult(String average, String stdDev) {
		this.encryptedAverage = average;
		this.encryptedStdDev = stdDev;
	}

	public Double getAverage() {
		return average;
	}

	public void setAverage(Double average) {
		this.average = average;
	}

	public Double getStdDev() {
		return stdDev;
	}

	public void setStdDev(Double stdDev) {
		this.stdDev = stdDev;
	}

	public String getEncryptedAverage() {
		return encryptedAverage;
	}

	public void setEncryptedAverage(String encryptedAverage) {
		this.encryptedAverage = encryptedAverage;
	}

	public String getEncryptedStdDev() {
		return encryptedStdDev;
	}

	public void setEncryptedStdDev(String encryptedStdDev) {
		this.encryptedStdDev = encryptedStdDev;
	}

	@Override
	public String toString() {
		return "CalcResult [average=" + average + ", stdDev=" + stdDev + ", encryptedAverage=" + encryptedAverage
				+ ", encryptedStdDev=" + encryptedStdDev + "]";
	}
}
