/**
 * 
 */
package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.TestDao;
import com.service.TestService;

/**
 * 
 * @author 分裂状态。
 *
 */
@Service
public class TestServiceImpl implements TestService {

	@Autowired
	private TestDao dao;

	public List<Object[]> getTemDayCount() {
		return dao.getTemDayCount();
	}

	public List<Object[]> getTemDayCounts() {
		return dao.getTemDayCounts();
	}

	public List<Object[]> getTemCounts() {
		return dao.getTemCounts();
	}

}
