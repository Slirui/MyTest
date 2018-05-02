/**
 * 
 */
package com.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.entity.Mod;
import com.pages.QueryResult;

/**
 * @author 分裂状态。
 *
 */
public interface ModService {

	public List<String> getYearMod();
	
	public String getMaxYearMod();
	
	public List<Mod> getAllMod();
	
	// 分页显示梅雨信息
	public QueryResult<Mod> listMod(Pageable page);

	public void saveMod(Mod mod);
	
	public Mod getModById(Integer id);
	
	public void updateMod(Integer year, Date modDay, Date meiDay, Integer meiyuLength, Double meiyuAmount, Integer id);

	public void deleteInfo(Integer id);
	
}
