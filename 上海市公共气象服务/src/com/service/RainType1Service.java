/**
 * 
 */
package com.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.entity.Precipitation;

/**
 * @author 分裂状态。
 *
 */
public interface RainType1Service {

	// 日雨量类型1 开始时间和结束时间都不为空
	public List<Precipitation> getRainType1Desc(HttpServletRequest request, String name, Date beginDate, Date endDate);

	// 日雨量类型1 开始时间和结束时间为空
	public List<Precipitation> getRainType1NoDateDesc(HttpServletRequest request, String name, Integer year);

	// 日雨量类型1 开始时间和结束时间都不为空
	public List<Precipitation> getRainType1Asc(HttpServletRequest request, String name, Date beginDate, Date endDate);

	// 日雨量类型1 开始时间和结束时间为空
	public List<Precipitation> getRainType1NoDateAsc(HttpServletRequest request, String name, Integer year);

	// 获取日雨量信息1
	public List<Precipitation> getRainType1(String name, String beginDate, String endDate);
	
	
	public List<Object[]> getPre2020Result(Date beginDate, Date endDate, String stationName);

	// 导出Excel
	public HSSFWorkbook exportRainType1(List<Object[]> list);
}
