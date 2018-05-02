/**
 * 
 */
package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ResultDao;
import com.entity.Result;
import com.service.ResultService;

/**
 * @author 分裂状态。
 *
 */
@Service
public class ResultServiceImpl implements ResultService {

	@Autowired
	private ResultDao dao;


	public List<Result> getResults(Integer year, Integer tem) {
		return dao.getResults(year, tem);
	}
	
	public void save(Result result) {
		dao.save(result);
	}
	
	public List<Object[]> getDayCountMin(Integer wd, Integer temBeginYear, Integer temEndYear) {
		return dao.getDayCountMin(wd, temBeginYear, temEndYear);
	}

	public List<Object[]> getDayCountMax(Integer wd, Integer temBeginYear, Integer temEndYear) {
		return dao.getDayCountMax(wd, temBeginYear, temEndYear);
	}

	public List<Object[]> getDayCounts(Integer wd, Integer temBeginYear, Integer temEndYear) {
		return dao.getDayCounts(wd, temBeginYear, temEndYear);
	}

	public void deleteResult(Integer year) {
		dao.deleteResult(year);
	}
}
