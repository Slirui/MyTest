/**
 * 
 */
package com.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.RainTypeDao;
import com.service.RainType3Service;

/**
 * @author 分裂状态。
 *
 */
@Service
public class RainType3ServiceImpl implements RainType3Service {

	@Autowired
	private RainTypeDao rainTypeDao;

	// 日雨量类型3 (累计雨量)
	public List<Object[]> getRainType3SumPreDesc(String name, Integer beginYear, Integer endYear, Date beginDate,
			Date endDate) {
		return rainTypeDao.getRainType3SumPreDesc(name, beginYear, endYear, beginDate, endDate);
	}

	// 日雨量类型3 (累计雨量)
	public List<Object[]> getRainType3SumPreAsc(String name, Integer beginYear, Integer endYear, Date beginDate,
			Date endDate) {
		return rainTypeDao.getRainType3SumPreAsc(name, beginYear, endYear, beginDate, endDate);
	}

	// 日雨量类型3 (每日雨量)
	public List<Object[]> getRainType3Desc(String name, Integer beginYear, Integer endYear, Date beginDate,
			Date endDate) {
		return rainTypeDao.getRainType3Desc(name, beginYear, endYear, beginDate, endDate);
	}

	// 日雨量类型3 (每日雨量)
	public List<Object[]> getRainType3Asc(String name, Integer beginYear, Integer endYear, Date beginDate,
			Date endDate) {
		return rainTypeDao.getRainType3Asc(name, beginYear, endYear, beginDate, endDate);
	}

}
