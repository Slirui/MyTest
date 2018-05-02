/**
 * 
 */
package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.entity.Precipitation;

/**
 * @author 分裂状态。
 *
 */
public interface TestDao extends GenericRepository<Precipitation, Integer> {

	@Query(value = "select count(*) as counts,DateYear as DateYear,DateMon as DateMon from Precipitation where Station_Name = '徐家汇' and TEM_Max >= 35 group by DateYear,DateMon", nativeQuery = true)
	public List<Object[]> getTemDayCount();

	@Query(value = "select COUNT(*) as counts,DateYear as DateYear from Precipitation where Station_Name = '徐家汇' and TEM_Max >= 35 group by DateYear order by counts desc", nativeQuery = true)
	public List<Object[]> getTemDayCounts();

	@Query(value = "select count(*) as counts,DateYear as DateYear,DateMon as DateMon from Precipitation where Station_Name = '徐家汇' and TEM_Max >= 35 group by DateYear,DateMon order by counts desc", nativeQuery = true)
	public List<Object[]> getTemCounts();
}
