/**
 * 
 */
package com.entity;

/**
 * @author 分裂状态。
 *
 */
public class ListSort {

	private String length;

	private String year;

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return ((ListSort) obj).length == this.length;
	}

}
