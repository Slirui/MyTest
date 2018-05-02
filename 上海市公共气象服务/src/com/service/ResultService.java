/**
 * 
 */
package com.service;

import java.util.List;

import com.entity.Result;

/**
 * @author 分裂状态。
 *
 */
public interface ResultService {

	public void save(Result result);

	public List<Result> getResults(Integer year, Integer tem);

	public List<Object[]> getDayCountMin(Integer wd, Integer temBeginYear, Integer temEndYear);

	public List<Object[]> getDayCountMax(Integer wd, Integer temBeginYear, Integer temEndYear);
	
	public List<Object[]> getDayCounts(Integer wd, Integer temBeginYear, Integer temEndYear);
	
	public void deleteResult(Integer year);

}
