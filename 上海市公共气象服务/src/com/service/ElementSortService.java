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
public interface ElementSortService {

	// 获取站点
	public List<String> getStationName();

	// 获取开始时间
	public Object getBeginDate();

	// 获取结束时间
	public Object getEndDate();

	// 获取要素信息
	public List<Precipitation> getElementInfos(HttpServletRequest request, String name, Date beginDate, Date endDate);

	// 获取要素信息
	// public List<Precipitation> getElementInfosAsc(HttpServletRequest request,
	// String name, Date beginDate, Date endDate);

	// 日最高温度(最高)
	public List<Precipitation> getTemMax_Max(HttpServletRequest request, String name, Date beginDate, Date endDate);

	// 日最高温度(最低)
	public List<Precipitation> getTemMax_Min(HttpServletRequest request, String name, Date beginDate, Date endDate);

	// 最高温度（最高）
	public List<Precipitation> getTemMaxDesc(HttpServletRequest request, String name, Integer beginYear,
			Integer endYear, Date beginDate, Date endDate);

	// 最高温度（最高）
	public List<Precipitation> getTemMaxAsc(HttpServletRequest request, String name, Integer beginYear, Integer endYear,
			Date beginDate, Date endDate);

	// 日最低温度(最高)
	public List<Precipitation> getTemMin_Max(HttpServletRequest request, String name, Date beginDate, Date endDate);

	// 日最低温度(最低)
	public List<Precipitation> getTemMin_Min(HttpServletRequest request, String name, Date beginDate, Date endDate);

	// 最低温度(最高)
	public List<Precipitation> getTemMinDesc(HttpServletRequest request, String name, Integer beginYear,
			Integer endYear, Date beginDate, Date endDate);

	// 最低温度(最低)
	public List<Precipitation> getTemMinAsc(HttpServletRequest request, String name, Integer beginYear, Integer endYear,
			Date beginDate, Date endDate);

	// 日平均温度(最高)
	public List<Precipitation> getTemAvg_Max(HttpServletRequest request, String name, Date beginDate, Date endDate);

	// 日平均温度(最低)
	public List<Precipitation> getTemAvg_Min(HttpServletRequest request, String name, Date beginDate, Date endDate);

	// 平均温度(最高)
	public List<Precipitation> getTemAvgDesc(HttpServletRequest request, String name, Integer beginYear,
			Integer endYear, Date beginDate, Date endDate);

	// 平均温度(最低)
	public List<Precipitation> getTemAvgAsc(HttpServletRequest request, String name, Integer beginYear, Integer endYear,
			Date beginDate, Date endDate);

	// 获取所有日降水量（20-20）
	public List<Precipitation> getElementsDayPre2020(String name, String beginDate, String endDate);

	// 获取最高温度
	public List<Precipitation> getTemMaxG(String name, String beginDate, String endDate);

	// 获取最高温度
	public List<Precipitation> getTemMaxD(String name, String beginDate, String endDate);

	// 获取最低温度
	public List<Precipitation> getTemMinG(String name, String beginDate, String endDate);

	// 获取最低温度
	public List<Precipitation> getTemMinD(String name, String beginDate, String endDate);

	// 获取平均温度
	public List<Precipitation> getTemAvgG(String name, String beginDate, String endDate);

	// 获取平均温度
	public List<Precipitation> getTemAvgD(String name, String beginDate, String endDate);

	// 导出Excel
	public HSSFWorkbook exportElementsDayPre2020(List<Object[]> list);

	// 导出Excel
	public HSSFWorkbook exportElementsTem(List<Object[]> list);

	// 获取最高温度(最高)
	public List<Object[]> getTemMaxHighchartsMax(String name, Integer beginYear, Integer endYear);

	// 获取最高温度(最低)
	public List<Object[]> getTemMaxHighchartsMin(String name, Integer beginYear, Integer endYear);

	// 获取最低温度(最高)
	public List<Object[]> getTemMinHighchartsMax(String name, Integer beginYear, Integer endYear);

	// 获取最低温度(最低)
	public List<Object[]> getTemMinHighchartsMin(String name, Integer beginYear, Integer endYear);

	// 获取平均温度(最高)
	public List<Object[]> getTemAvgHighchartsMax(String name, Integer beginYear, Integer endYear);

	// 获取平均温度(最低)
	public List<Object[]> getTemAvgHighchartsMin(String name, Integer beginYear, Integer endYear);

	// 获取日降水量(20-20)
	public List<Object[]> getElementsHighcharts(String name, Integer beginYear, Integer endYear);

}
