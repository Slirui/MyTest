/**
 * 
 */
package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.entity.SiteMonthAvg;

/**
 * 
 * @author 分裂状态。
 *
 */
public interface SiteMonthAvgDao extends JpaRepository<SiteMonthAvg, Integer> {
	
	@Query(value = "select DISTINCT s.site from SiteMonthAvg s")
	public List<String> getSite();

	@Query(value = "select d from SiteMonthAvg d where site = ?")
	public List<SiteMonthAvg> getResultByName(String name);
}
