/**
 * 
 */
package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.entity.Result;

/**
 * @author 分裂状态。
 *
 */
public interface ResultDao extends GenericRepository<Result, Integer> {
	
	@Query(value = "select * from Result where DateYear = ? and Tem = ?", nativeQuery = true)
	public List<Result> getResults(Integer year,Integer tem);

	@Query(value = "select DayCount as dayCount,DateYear as DateYear from Result where Tem = ? and DateYear >= ? and DateYear <= ? order by dayCount asc", nativeQuery = true)
	public List<Object[]> getDayCountMin(Integer wd, Integer temBeginYear, Integer temEndYear);

	@Query(value = "select DayCount as dayCount,DateYear as DateYear from Result where Tem = ? and DateYear >= ? and DateYear <= ? order by dayCount desc", nativeQuery = true)
	public List<Object[]> getDayCountMax(Integer wd, Integer temBeginYear, Integer temEndYear);

	@Query(value = "select DayCount as dayCount,DateYear as DateYear from Result where Tem = ? and DateYear >= ? and DateYear <= ? order by DateYear asc", nativeQuery = true)
	public List<Object[]> getDayCounts(Integer wd, Integer temBeginYear, Integer temEndYear);

	@Modifying
	@Query("delete from Result where DateYear = ?")
	public void deleteResult(Integer year);
}
