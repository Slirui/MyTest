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
public interface RainType2Service {

	// 日雨量类型2
	public List<Precipitation> getRainType2Desc(HttpServletRequest request, String name, Integer beginYear,
			Integer endYear, Date beginDate, Date endDate);

	// 日雨量类型2
	public List<Precipitation> getRainType2Asc(HttpServletRequest request, String name, Integer beginYear,
			Integer endYear, Date beginDate, Date endDate);

	// 累计雨量
	public List<Object[]> getRainType2SumPreDesc(String name, Integer beginYear, Integer endYear, Date beginDate,
			Date endDate);

	// 获取日雨量信息2
	public List<Precipitation> getRainType2(String name, Integer beginYear, Integer endYear, String beginDate,
			String endDate);

	// 导出Excel
	public HSSFWorkbook exportRainType2(List<Object[]> list);

	// 导出Excel(累计雨量)
	public HSSFWorkbook exportRainType2SumPre(List<Object[]> list);
}
