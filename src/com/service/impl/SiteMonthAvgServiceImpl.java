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
 * @author ����״̬��
 *
 */
@Service
public class SiteMonthAvgServiceImpl implements SiteMonthAvgService {

	@Autowired
	private SiteMonthAvgDao dao;
	
	// ��ȡվ��
	public List<String> getSite() {
		return dao.getSite();
	}
	
	// ��ȡ��Ϣ
	public List<SiteMonthAvg> getResultByName(String name) {
		return dao.getResultByName(name);
	}
}
