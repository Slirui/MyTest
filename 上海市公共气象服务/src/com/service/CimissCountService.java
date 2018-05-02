/**
 * 
 */
package com.service;

import java.util.List;

import com.entity.CimissCount;

/**
 * @author 分裂状态。
 *
 */
public interface CimissCountService {

	public List<CimissCount> listCimissCount();
	
	public List<CimissCount> listCimissCountAll();
	
	public void saveCimissCount(CimissCount count);
	
	public void updateCimissCount(boolean isActive,Integer id);
}
