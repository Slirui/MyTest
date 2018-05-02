/**
 * 
 */
package com.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dao.PrecipitationDao;
import com.entity.Precipitation;
import com.pages.QueryResult;
import com.service.PrecipitationService;

/**
 * @author 分裂状态。
 *
 */
@Service
public class PrecipitationServiceImpl implements PrecipitationService {

	@Autowired
	private PrecipitationDao precipitationDao;

	public void savePrecipitation(Precipitation pre) {
		precipitationDao.save(pre);
	}

	public int countPre(String dateDate, Integer stationId) {
		return precipitationDao.countPre(dateDate, stationId);
	}

	public int getPreCount(String stationName, Date begin, Date end) {
		return precipitationDao.getPreCount(stationName, begin, end);
	}
	
	public int getPreCount2(String name, Integer beginYear, Integer endYear, Date beginDate, Date endDate){
		return precipitationDao.getPreCount2(name, beginYear, endYear, beginDate, endDate);
	}
	
	public int getPreCount3(String stationName){
		return precipitationDao.getPreCount3(stationName);
	}

	// 分页显示信息
	public QueryResult<Precipitation> listPrecipitation(Pageable page) {
		return new QueryResult<Precipitation>(precipitationDao.findAll(page));
	}

	public QueryResult<Precipitation> findByName(Pageable page, String name) {
		return new QueryResult<Precipitation>(precipitationDao.findByName(page, name));
	}

	public void updatePrecipitation(Double avg, Double max, Double min, Double pre2008, Double pre0820, Double pre2020,
			Double pre0808, Integer id) {
		precipitationDao.updatePrecipitation(avg, max, min, pre2008, pre0820, pre2020, pre0808, id);
	}

}
