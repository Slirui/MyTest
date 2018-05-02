/**
 * 
 */
package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.SiteMonthAvgDao;
import com.entity.SiteMonthAvg;
import com.service.SiteMonthAvgService;

/**
 * @author 分裂状态。
 *
 */
@Service
public class SiteMonthAvgServiceImpl implements SiteMonthAvgService {

	@Autowired
	private SiteMonthAvgDao dao;
	
	// 获取站点
	public List<String> getSite() {
		return dao.getSite();
	}
	
	// 获取信息
	public List<SiteMonthAvg> getResultByName(String name) {
		return dao.getResultByName(name);
	}
}
