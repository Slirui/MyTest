/**
 * 
 */
package com.entity;

import java.util.List;

/**
 * @author 分裂状态。
 *
 */
public class CommonModel {

	public CommonModel() {
		super();
	}
	
	public CommonModel(List<Precipitation> prelist) {
		super();
		this.prelist = prelist;
	}

	public List<Precipitation> prelist;

	public List<Precipitation> getPrelist() {
		return prelist;
	}

	public void setPrelist(List<Precipitation> prelist) {
		this.prelist = prelist;
	}
	
	
}
