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
public interface ElementSortDao extends GenericRepository<Precipitation, Integer> {

	@Query("select DISTINCT stationName from Precipitation order by stationName desc")
	// 获取站点
	public List<String> getStationName();

	@Query(value = "select MIN(CONVERT(varchar,DateDate,23)) beginDate from Precipitation", nativeQuery = true)
	// 获取开始时间
	public Object getBeginDate();

	@Query(value = "select MAX(CONVERT(varchar,DateDate,23)) endDate from Precipitation", nativeQuery = true)
	// 获取结束时间
	public Object getEndDate();

	// 获取所有日降水量（20-20）
	@Query(value = "select * from Precipitation where Station_Name = ? and DateDate >= ? and DateDate <= ? and PRE_Time_2020 > 0.0 order by PRE_Time_2020 desc", nativeQuery = true)
	public List<Precipitation> getElementsDayPre2020(String name, String beginDate, String endDate);

	// 获取最高温度
	@Query(value = "select * from Precipitation where Station_Name = ? and DateDate >= ? and DateDate <= ? order by Tem_Max desc", nativeQuery = true)
	public List<Precipitation> getTemMaxG(String name, String beginDate, String endDate);

	// 获取最高温度
	@Query(value = "select * from Precipitation where Station_Name = ? and DateDate >= ? and DateDate <= ? order by Tem_Max asc", nativeQuery = true)
	public List<Precipitation> getTemMaxD(String name, String beginDate, String endDate);

	// 获取最低温度
	@Query(value = "select * from Precipitation where Station_Name = ? and DateDate >= ? and DateDate <= ? order by Tem_Min desc", nativeQuery = true)
	public List<Precipitation> getTemMinG(String name, String beginDate, String endDate);

	// 获取最低温度
	@Query(value = "select * from Precipitation where Station_Name = ? and DateDate >= ? and DateDate <= ? order by Tem_Min asc", nativeQuery = true)
	public List<Precipitation> getTemMinD(String name, String beginDate, String endDate);

	// 获取平均温度
	@Query(value = "select * from Precipitation where Station_Name = ? and DateDate >= ? and DateDate <= ? order by Tem_Avg desc", nativeQuery = true)
	public List<Precipitation> getTemAvgG(String name, String beginDate, String endDate);

	// 获取平均温度
	@Query(value = "select * from Precipitation where Station_Name = ? and DateDate >= ? and DateDate <= ? order by Tem_Avg asc", nativeQuery = true)
	public List<Precipitation> getTemAvgD(String name, String beginDate, String endDate);

	// 获取最高温度(最高)
	@Query(value = "select Max(TEM_Max),DateYear from Precipitation where Station_Name = ? and DateYear >= ? and DateYear <= ? group by DateYear order by DateYear asc", nativeQuery = true)
	public List<Object[]> getTemMaxHighchartsMax(String name, Integer beginYear, Integer endYear);

	// 获取最高温度(最低)
	@Query(value = "select Min(TEM_Max),DateYear from Precipitation where Station_Name = ? and DateYear >= ? and DateYear <= ? group by DateYear order by DateYear asc", nativeQuery = true)
	public List<Object[]> getTemMaxHighchartsMin(String name, Integer beginYear, Integer endYear);

	// 获取最低温度(最高)
	@Query(value = "select Max(TEM_Min),DateYear from Precipitation where Station_Name = ? and DateYear >= ? and DateYear <= ? group by DateYear order by DateYear asc", nativeQuery = true)
	public List<Object[]> getTemMinHighchartsMax(String name, Integer beginYear, Integer endYear);

	// 获取最低温度(最低)
	@Query(value = "select Min(TEM_Min),DateYear from Precipitation where Station_Name = ? and DateYear >= ? and DateYear <= ? group by DateYear order by DateYear asc", nativeQuery = true)
	public List<Object[]> getTemMinHighchartsMin(String name, Integer beginYear, Integer endYear);

	// 获取平均温度(最高)
	@Query(value = "select Max(TEM_Avg),DateYear from Precipitation where Station_Name = ? and DateYear >= ? and DateYear <= ? group by DateYear order by DateYear asc", nativeQuery = true)
	public List<Object[]> getTemAvgHighchartsMax(String name, Integer beginYear, Integer endYear);

	// 获取平均温度(最低)
	@Query(value = "select Min(TEM_Avg),DateYear from Precipitation where Station_Name = ? and DateYear >= ? and DateYear <= ? group by DateYear order by DateYear asc", nativeQuery = true)
	public List<Object[]> getTemAvgHighchartsMin(String name, Integer beginYear, Integer endYear);

	// 获取日降水量(20-20)
	@Query(value = "select Max(PRE_Time_2020),DateYear from Precipitation where Station_Name = ? and DateYear >= ? and DateYear <= ? group by DateYear order by DateYear asc", nativeQuery = true)
	public List<Object[]> getElementsHighcharts(String name, Integer beginYear, Integer endYear);
}
