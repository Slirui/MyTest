/**
 * 
 */
package com.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.entity.Precipitation;

/**
 * @author 分裂状态。
 *
 */
public interface RainTypeDao extends GenericRepository<Precipitation, Integer> {

	@Query(value = "select SUM(PRE_Time_2020),DateYear from Precipitation where Station_Name = ? and DateYear >= ? and DateYear <= ? and DateYearMonDay >= ? and DateYearMonDay <= ? group by DateYear order by SUM(PRE_Time_2020) desc", nativeQuery = true)
	// 日雨量类型3 (累计雨量)
	public List<Object[]> getRainType3SumPreDesc(String name, Integer beginYear, Integer endYear, Date beginDate,
			Date endDate);

	@Query(value = "select SUM(PRE_Time_2020),DateYear from Precipitation where Station_Name = ? and DateYear >= ? and DateYear <= ? and DateYearMonDay >= ? and DateYearMonDay <= ? group by DateYear order by SUM(PRE_Time_2020) asc", nativeQuery = true)
	// 日雨量类型3 (累计雨量)
	public List<Object[]> getRainType3SumPreAsc(String name, Integer beginYear, Integer endYear, Date beginDate,
			Date endDate);

	@Query(value = "select PRE_Time_2020,convert(varchar,DateDate,23) from Precipitation where Station_Name = ? and DateYear >= ? and DateYear <= ? and DateYearMonDay >= ? and DateYearMonDay <= ? order by PRE_Time_2020 desc", nativeQuery = true)
	// 日雨量类型3 (每日雨量)
	public List<Object[]> getRainType3Desc(String name, Integer beginYear, Integer endYear, Date beginDate,
			Date endDate);

	@Query(value = "select PRE_Time_2020,convert(varchar,DateDate,23) from Precipitation where Station_Name = ? and DateYear >= ? and DateYear <= ? and DateYearMonDay >= ? and DateYearMonDay <= ? order by PRE_Time_2020 asc", nativeQuery = true)
	// 日雨量类型3 (每日雨量)
	public List<Object[]> getRainType3Asc(String name, Integer beginYear, Integer endYear, Date beginDate,
			Date endDate);

	// 获取日雨量信息1
	@Query(value = "select * from Precipitation where Station_Name = ? and DateDate >= ? and DateDate <= ? and PRE_Time_2020 is not null and PRE_Time_2020 > 0.0 order by PRE_Time_2020 desc", nativeQuery = true)
	public List<Precipitation> getRainType1(String name, String beginDate, String endDate);

	// 获取日雨量信息2
	@Query(value = "select * from Precipitation where Station_Name = ? and DateYear >= ? and DateYear <= ? and DateYearMonDay >= ? and DateYearMonDay <= ? and PRE_Time_2020 is not null and PRE_Time_2020 > 0.0 order by PRE_Time_2020 desc", nativeQuery = true)
	public List<Precipitation> getRainType2(String name, Integer beginYear, Integer endYear, String beginDate,
			String endDate);

	@Query(value = "select PRE_Time_2020,DateDate from Precipitation where DateDate >= ?1 and DateDate <= ?2 and Station_Name = ?3 and PRE_Time_2020 is not null and PRE_Time_2020 > 0.0 order by PRE_Time_2020 desc", nativeQuery = true)
	public List<Object[]> getPre2020Result(Date beginDate, Date endDate, String stationName);

}
