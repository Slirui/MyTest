/**
 * 
 */
package com.service;

import java.util.Date;

import org.springframework.data.domain.Pageable;

import com.entity.Precipitation;
import com.pages.QueryResult;

/**
 * @author 分裂状态。
 *
 */
public interface PrecipitationService {

	public void savePrecipitation(Precipitation pre);

	public int countPre(String dateDate, Integer stationId);
	
	public int getPreCount(String stationName,Date begin,Date end);
	
	public int getPreCount2(String name, Integer beginYear, Integer endYear, Date beginDate, Date endDate);

	public int getPreCount3(String stationName);
	
	// 分页显示信息
	public QueryResult<Precipitation> listPrecipitation(Pageable page);

	public QueryResult<Precipitation> findByName(Pageable page,String name);
	
	public void updatePrecipitation(Double avg, Double max, Double min, Double pre2008, Double pre0820, Double pre2020,
			Double pre0808, Integer id);
	
}
