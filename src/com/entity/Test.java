/**
 * 
 */
package com.entity;

/**
 * @author 分裂状态。
 *
 */
public class Test {
	private Integer maxYear;
	private Integer maxMay;
	private Integer maxJune;
	private Integer maxJuly;
	private Integer maxAugust;
	private Integer maxSep;
	private Integer maxSumDay;

	
	private Integer minYear;
	private Integer minMay;
	private Integer minJune;
	private Integer minJuly;
	private Integer minAugust;
	private Integer minSep;
	private Integer minSumDay;

	public Integer getMaxYear() {
		return maxYear;
	}

	public void setMaxYear(Integer maxYear) {
		this.maxYear = maxYear;
	}

	public Integer getMaxMay() {
		return maxMay;
	}

	public void setMaxMay(Integer maxMay) {
		this.maxMay = maxMay;
	}

	public Integer getMaxJune() {
		return maxJune;
	}

	public void setMaxJune(Integer maxJune) {
		this.maxJune = maxJune;
	}

	public Integer getMaxJuly() {
		return maxJuly;
	}

	public void setMaxJuly(Integer maxJuly) {
		this.maxJuly = maxJuly;
	}

	public Integer getMaxAugust() {
		return maxAugust;
	}

	public void setMaxAugust(Integer maxAugust) {
		this.maxAugust = maxAugust;
	}

	public Integer getMaxSep() {
		return maxSep;
	}

	public void setMaxSep(Integer maxSep) {
		this.maxSep = maxSep;
	}

	public Integer getMaxSumDay() {
		return maxSumDay;
	}

	public void setMaxSumDay(Integer maxSumDay) {
		this.maxSumDay = maxSumDay;
	}

	public Integer getMinYear() {
		return minYear;
	}

	public void setMinYear(Integer minYear) {
		this.minYear = minYear;
	}

	public Integer getMinMay() {
		return minMay;
	}

	public void setMinMay(Integer minMay) {
		this.minMay = minMay;
	}

	public Integer getMinJune() {
		return minJune;
	}

	public void setMinJune(Integer minJune) {
		this.minJune = minJune;
	}

	public Integer getMinJuly() {
		return minJuly;
	}

	public void setMinJuly(Integer minJuly) {
		this.minJuly = minJuly;
	}

	public Integer getMinAugust() {
		return minAugust;
	}

	public void setMinAugust(Integer minAugust) {
		this.minAugust = minAugust;
	}

	public Integer getMinSep() {
		return minSep;
	}

	public void setMinSep(Integer minSep) {
		this.minSep = minSep;
	}

	public Integer getMinSumDay() {
		return minSumDay;
	}

	public void setMinSumDay(Integer minSumDay) {
		this.minSumDay = minSumDay;
	}

	public void setMonthValue(Integer month, Integer value) {
		if (month == 5) {
			this.maxMay = value;
		}
		if (month == 6) {
			this.maxJune = value;
		}
		if (month == 7) {
			this.maxJuly = value;
		}
		if (month == 8) {
			this.maxAugust = value;
		}
		if (month == 9) {
			this.maxSep = value;
		}
	}
}
