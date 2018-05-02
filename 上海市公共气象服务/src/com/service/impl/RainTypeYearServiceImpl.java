/**
 * 
 */
package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.RainTypeYearDao;
import com.service.RainTypeYearService;

/**
 * @author 分裂状态。
 *
 */
@Service
public class RainTypeYearServiceImpl implements RainTypeYearService {

	@Autowired
	private RainTypeYearDao rainTypeYearDao;

	// 获取年
	public List<String> getYear() {
		return rainTypeYearDao.getYear();
	}

}
