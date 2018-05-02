/**
 * 
 */
package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.dao.ImpWeatherDao;
import com.entity.ImpWeather;
import com.service.ImpWeatherService;

/**
 * @author 分裂状态。
 *
 */
@Service
public class ImpWeatherServiceImpl implements ImpWeatherService {

	@Autowired
	private ImpWeatherDao impWeatherDao;

	public List<ImpWeather> getImpWeather() {
		return impWeatherDao.getImpWeather();
	}

	public List<ImpWeather> getImpWeatherByTitle(String title) {
		return impWeatherDao.getImpWeatherByTitle(title);
	}

	public List<ImpWeather> getImpWeatherByErji(String erji) {
		return impWeatherDao.getImpWeatherByErji(erji);
	}

	public List<ImpWeather> getImpWeatherBySanji(String sanji) {
		return impWeatherDao.getImpWeatherBySanji(sanji);
	}

	public List<ImpWeather> getImpWeatherByLike(String content) {
		return impWeatherDao.getImpWeatherByLike(content);
	}

	public void updateContentByTitle(String contents, String node) {
		impWeatherDao.updateContentByTitle(contents, node);
	}

	public void updateContentByErJi(String contents, String node) {
		impWeatherDao.updateContentByErJi(contents, node);
	}

	public void updateContentBySanJi(String contents, String node) {
		impWeatherDao.updateContentBySanJi(contents, node);
	}

	public void updateContentByTitleIsNull(String node) {
		impWeatherDao.updateContentByTitleIsNull(node);
	}

	public void updateContentByErJiIsNull(String node) {
		impWeatherDao.updateContentByErJiIsNull(node);
	}

	public void updateContentBySanJiIsNull(String node) {
		impWeatherDao.updateContentBySanJiIsNull(node);
	}

	// 添加节点
	public void saveNode(ImpWeather impWeather) {
		impWeatherDao.save(impWeather);
	}

	// 修改一级节点
	public void updateImpWeatherYiJiNode(String node, Integer id) {
		impWeatherDao.updateImpWeatherYiJiNode(node, id);
	}

	public void updateImpWeatherYiJiNodeByYiji(String node, String title) {
		impWeatherDao.updateImpWeatherYiJiNodeByYiji(node, title);
	}

	// 修改二级节点
	public void updateImpWeatherErJiNode(String node, Integer id) {
		impWeatherDao.updateImpWeatherErJiNode(node, id);
	}

	public void updateImpWeatherErJiNodeByErji(String node, String erji) {
		impWeatherDao.updateImpWeatherErJiNodeByErji(node, erji);
	}

	// 修改三级节点
	public void updateImpWeatherSanJiNode(String node, Integer id) {
		impWeatherDao.updateImpWeatherSanJiNode(node, id);
	}

	public void updateImpWeatherSanJiNodeBySanji(String node, String sanji) {
		impWeatherDao.updateImpWeatherSanJiNodeBySanji(node, sanji);
	}

	// 删除节点
	public void deleteNode(Integer id) {
		impWeatherDao.delete(id);
	}

	public void deleteImpWeatherByTitle(String title) {
		impWeatherDao.deleteImpWeatherByTitle(title);
	}

	public void deleteImpWeatherByErji(String erji) {
		impWeatherDao.deleteImpWeatherByErji(erji);
	}

	public void deleteImpWeatherBySanji(String sanji) {
		impWeatherDao.deleteImpWeatherBySanji(sanji);
	}

}
