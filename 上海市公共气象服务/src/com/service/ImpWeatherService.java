/**
 * 
 */
package com.service;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.entity.ImpWeather;

/**
 * @author 分裂状态。
 *
 */
public interface ImpWeatherService {

	public List<ImpWeather> getImpWeather();

	public List<ImpWeather> getImpWeatherByTitle(String title);

	public List<ImpWeather> getImpWeatherByErji(String erji);

	public List<ImpWeather> getImpWeatherBySanji(String sanji);

	public List<ImpWeather> getImpWeatherByLike(String content);

	public void updateContentByTitle(String contents, String node);

	public void updateContentByErJi(String contents, String node);

	public void updateContentBySanJi(String contents, String node);

	public void updateContentByTitleIsNull(String node);

	public void updateContentByErJiIsNull(String node);

	public void updateContentBySanJiIsNull(String node);

	// 添加节点
	public void saveNode(ImpWeather impWeather);

	// 修改一级节点
	public void updateImpWeatherYiJiNode(String node, Integer id);

	public void updateImpWeatherYiJiNodeByYiji(String node, String title);

	// 修改二级节点
	public void updateImpWeatherErJiNode(String node, Integer id);

	public void updateImpWeatherErJiNodeByErji(String node, String erji);

	// 修改三级节点
	public void updateImpWeatherSanJiNode(String node, Integer id);

	public void updateImpWeatherSanJiNodeBySanji(String node, String sanji);

	// 删除节点
	public void deleteNode(Integer id);

	
	public void deleteImpWeatherByTitle(String title);

	public void deleteImpWeatherByErji(String erji);

	public void deleteImpWeatherBySanji(String sanji);
}
