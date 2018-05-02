/**
 * 
 */
package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.entity.ImpWeather;

/**
 * @author 分裂状态。
 *
 */
public interface ImpWeatherDao extends GenericRepository<ImpWeather, Integer> {

	@Query(value = "select * from ImpWeather", nativeQuery = true)
	public List<ImpWeather> getImpWeather();

	@Query(value = "select * from ImpWeather where Title = ?", nativeQuery = true)
	public List<ImpWeather> getImpWeatherByTitle(String title);

	@Query(value = "select * from ImpWeather where Erji = ?", nativeQuery = true)
	public List<ImpWeather> getImpWeatherByErji(String erji);

	@Query(value = "select * from ImpWeather where Sanji = ?", nativeQuery = true)
	public List<ImpWeather> getImpWeatherBySanji(String sanji);

	@Query(value = "select * from ImpWeather where Contents like ?", nativeQuery = true)
	public List<ImpWeather> getImpWeatherByLike(String content);

	@Modifying
	@Query("update ImpWeather set contents = ? where title = ?")
	public void updateContentByTitle(String contents, String node);

	@Modifying
	@Query("update ImpWeather set contents = ? where erJi = ?")
	public void updateContentByErJi(String contents, String node);

	@Modifying
	@Query("update ImpWeather set contents = ? where sanJi = ?")
	public void updateContentBySanJi(String contents, String node);

	@Modifying
	@Query("update ImpWeather set contents = null where title = ?")
	public void updateContentByTitleIsNull(String node);

	@Modifying
	@Query("update ImpWeather set contents = null where erJi = ?")
	public void updateContentByErJiIsNull(String node);
	
	@Modifying
	@Query("update ImpWeather set contents = null where erJi = ?")
	public void updateContentBySanJiIsNull(String node);

	@Modifying
	@Query("update ImpWeather set Title = ? where Id = ?")
	public void updateImpWeatherYiJiNode(String node, Integer id);
	
	@Modifying
	@Query("update ImpWeather set Title = ? where Title = ?")
	public void updateImpWeatherYiJiNodeByYiji(String node, String title);
	
	

	@Modifying
	@Query("update ImpWeather set Erji = ? where Id = ?")
	public void updateImpWeatherErJiNode(String node, Integer id);
	
	@Modifying
	@Query("update ImpWeather set Erji = ? where Erji = ?")
	public void updateImpWeatherErJiNodeByErji(String node, String erji);

	@Modifying
	@Query("update ImpWeather set Sanji = ? where Id = ?")
	public void updateImpWeatherSanJiNode(String node, Integer id);
	
	
	@Modifying
	@Query("update ImpWeather set Sanji = ? where Sanji = ?")
	public void updateImpWeatherSanJiNodeBySanji(String node, String sanji);

	
	@Modifying
	@Query("delete from ImpWeather where Title = ?")
	public void deleteImpWeatherByTitle(String title);
	
	@Modifying
	@Query("delete from ImpWeather where Erji = ?")
	public void deleteImpWeatherByErji(String erji);
	
	@Modifying
	@Query("delete from ImpWeather where Sanji = ?")
	public void deleteImpWeatherBySanji(String sanji);
	
	
}
