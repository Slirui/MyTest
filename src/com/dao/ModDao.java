/**
 * 
 */
package com.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.entity.Mod;

/**
 * @author 分裂状态。
 *
 */
public interface ModDao extends GenericRepository<Mod, Integer> {

	@Query("select m.year from Mod m")
	public List<String> getYearMod();

	@Query(value = "select MAX(Year) from [Mod]", nativeQuery = true)
	public String getMaxYearMod();

	@Query("select m from Mod m")
	public List<Mod> getAllMod();

	@Query("select m from Mod m where id = ?")
	public Mod getModById(Integer id);

	@Modifying
	@Query("update Mod set year = ?,modDay = ?,meiDay = ?,meiyuLength = ?,meiyuAmount = ? where id = ? ")
	public void updateMod(Integer year, Date modDay, Date meiDay, Integer meiyuLength, Double meiyuAmount, Integer id);

}