/**
 * 
 */
package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.CimissCountDao;
import com.entity.CimissCount;
import com.service.CimissCountService;

/**
 * @author 分裂状态。
 *
 */
@Service
public class CimissCountServiceImpl implements CimissCountService {

	@Autowired
	private CimissCountDao dao;
	
	public List<CimissCount> listCimissCount(){
		return dao.listCimissCount();
	}

	public List<CimissCount> listCimissCountAll(){
		return dao.listCimissCount();
	}
	
	public void saveCimissCount(CimissCount count) {
		dao.save(count);
	}
	
	public void updateCimissCount(boolean isActive,Integer id){
		dao.updateCimissCount(isActive, id);
	}
}
