/**
 * 
 */
package com.service;

import java.util.List;

import com.entity.SiteMonthAvg;

/**
 * @author ����״̬��
 *
 */
public interface SiteMonthAvgService {

	// 获取站点
	public List<String> getSite();

	// 获取平均信息
	public List<SiteMonthAvg> getResultByName(String name);
}
