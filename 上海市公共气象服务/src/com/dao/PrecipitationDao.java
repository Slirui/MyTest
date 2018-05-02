/**
 * 
 */
package com.dao;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.entity.Precipitation;

/**
 * @author 分裂状态。
 *
 */
public interface PrecipitationDao extends GenericRepository<Precipitation, Integer> {

	// 是否有数据
	@Query(value = "select count(1) from Precipitation where DateDate = ? and Station_Id_d = ?", nativeQuery = true)
	public int countPre(String dateDate, Integer stationId);

	// @Query("select t from Precipitation t where t.stationName = ?1 and
	// (t.temAvg is null or t.temMax is null or t.temMin is null or
	// t.preTime0808 is null or t.preTime2008 is null or t.preTime2020 is null
	// or t.preTime0820 is null) and dateDate <= '2017-12-31'")
	// public Page<Precipitation> findByName(Pageable page, String name);

	@Query("select t from Precipitation t where t.stationName = ?1 and (t.temAvg is null or t.temMax is null or t.temMin is null or t.preTime0808 is null or t.preTime2008 is null or t.preTime2020 is null or t.preTime0820 is null)")
	public Page<Precipitation> findByName(Pageable page, String name);

	// 检查缺失数据
	@Query(value = "select COUNT(*) from Precipitation where Station_Name = ? and (TEM_Avg is null or TEM_Max is null or TEM_Min is null or PRE_Time_0808 is null or PRE_Time_2008 is null or PRE_Time_2020 is null or PRE_Time_0820 is null) and DateDate >= ? and DateDate <= ?", nativeQuery = true)
	public int getPreCount(String stationName, Date begin, Date end);

	@Query(value = "select COUNT(*) from Precipitation where Station_Name = ? and (TEM_Avg is null or TEM_Max is null or TEM_Min is null or PRE_Time_0808 is null or PRE_Time_2008 is null or PRE_Time_2020 is null or PRE_Time_0820 is null) and DateYear >= ? and DateYear <= ? and DateYearMonDay >= ? and DateYearMonDay <= ?", nativeQuery = true)
	public int getPreCount2(String name, Integer beginYear, Integer endYear, Date beginDate, Date endDate);

	@Query(value = "select COUNT(*) from Precipitation where Station_Name = ? and (TEM_Avg is null or TEM_Max is null or TEM_Min is null or PRE_Time_0808 is null or PRE_Time_2008 is null or PRE_Time_2020 is null or PRE_Time_0820 is null)", nativeQuery = true)
	public int getPreCount3(String stationName);

	@Modifying
	@Query("update Precipitation set temAvg = ?1,temMax = ?2,temMin = ?3,preTime2008 = ?4,preTime0820 = ?5,preTime2020 = ?6,preTime0808 = ?7 where id = ?8")
	public void updatePrecipitation(Double avg, Double max, Double min, Double pre2008, Double pre0820, Double pre2020,
			Double pre0808, Integer id);
}
