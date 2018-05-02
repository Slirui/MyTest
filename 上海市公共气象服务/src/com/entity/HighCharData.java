/**
 * 
 */
package com.entity;

/**
 * @author 分裂状态。
 *
 */
public class HighCharData {

	public HighCharData() {
		super();
	}
	
	public HighCharData(String xData, String yData) {
		super();
		this.xData = xData;
		this.yData = yData;
	}

	private String xData;

	private String yData;

	public String getxData() {
		return xData;
	}

	public void setxData(String xData) {
		this.xData = xData;
	}

	public String getyData() {
		return yData;
	}

	public void setyData(String yData) {
		this.yData = yData;
	}
}
