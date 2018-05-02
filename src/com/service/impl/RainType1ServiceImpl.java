/**
 * 
 */
package com.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.dao.QueryKword;
import com.dao.RainTypeDao;
import com.entity.Precipitation;
import com.service.RainType1Service;
import com.util.Pageutil;

/**
 * @author 分裂状态。
 *
 */
@Service
public class RainType1ServiceImpl implements RainType1Service {

	@Autowired
	private RainTypeDao rainTypeDao;

	// 日雨量类型1 开始时间和结束时间都不为空
	public List<Precipitation> getRainType1Desc(HttpServletRequest request, String name, Date beginDate, Date endDate) {
		List<QueryKword> qkv = new ArrayList<QueryKword>();
		if (name != null) {
			qkv.add(QueryKword.equalTo("stationName", name));
		}
		if (beginDate != null) {
			qkv.add(QueryKword.greaterThanOrEqualTo("dateDate", beginDate));
		}
		if (endDate != null) {
			qkv.add(QueryKword.lessThanOrEqualTo("dateDate", endDate));
		}
		qkv.add(QueryKword.isNotNULL("preTime2020"));
		qkv.add(QueryKword.greaterThan("preTime2020", 0.0));
		return rainTypeDao
				.list(request, qkv, Pageutil.getPageable(0, 100, new Sort(Sort.Direction.DESC, "preTime2020")))
				.getList();
	}

	// 日雨量类型1 开始时间和结束时间为空
	public List<Precipitation> getRainType1NoDateDesc(HttpServletRequest request, String name, Integer year) {
		List<QueryKword> qkv = new ArrayList<QueryKword>();
		if (name != null) {
			qkv.add(QueryKword.equalTo("stationName", name));
		}
		if (year != null) {
			qkv.add(QueryKword.equalTo("dateYear", year));
		}
		return rainTypeDao
				.list(request, qkv, Pageutil.getPageable(0, 100, new Sort(Sort.Direction.DESC, "preTime2020")))
				.getList();
	}

	// 日雨量类型1 开始时间和结束时间都不为空
	public List<Precipitation> getRainType1Asc(HttpServletRequest request, String name, Date beginDate, Date endDate) {
		List<QueryKword> qkv = new ArrayList<QueryKword>();
		if (name != null) {
			qkv.add(QueryKword.equalTo("stationName", name));
		}
		if (beginDate != null) {
			qkv.add(QueryKword.greaterThanOrEqualTo("dateDate", beginDate));
		}
		if (endDate != null) {
			qkv.add(QueryKword.lessThanOrEqualTo("dateDate", endDate));
		}
		qkv.add(QueryKword.isNotNULL("preTime2020"));
		qkv.add(QueryKword.greaterThan("preTime2020", 0.0));
		return rainTypeDao.list(request, qkv, Pageutil.getPageable(0, 100, new Sort(Sort.Direction.ASC, "preTime2020")))
				.getList();
	}

	// 日雨量类型1 开始时间和结束时间为空
	public List<Precipitation> getRainType1NoDateAsc(HttpServletRequest request, String name, Integer year) {
		List<QueryKword> qkv = new ArrayList<QueryKword>();
		if (name != null) {
			qkv.add(QueryKword.equalTo("stationName", name));
		}
		if (year != null) {
			qkv.add(QueryKword.equalTo("dateYear", year));
		}
		return rainTypeDao.list(request, qkv, Pageutil.getPageable(0, 100, new Sort(Sort.Direction.ASC, "preTime2020")))
				.getList();
	}

	// 获取日雨量信息1
	public List<Precipitation> getRainType1(String name, String beginDate, String endDate) {
		return rainTypeDao.getRainType1(name, beginDate, endDate);
	}
	
	public List<Object[]> getPre2020Result(Date beginDate, Date endDate, String stationName){
		return rainTypeDao.getPre2020Result(beginDate, endDate, stationName);
	}
	

	// 导出Excel
	public HSSFWorkbook exportRainType1(List<Object[]> list) {
		String[] excelHeader = { "排名", "降水量", "日期" };
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("日雨量");
		HSSFRow row = sheet.createRow(0);
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		HSSFFont headerFont = wb.createFont(); // 标题字体
		headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headerFont.setFontHeightInPoints((short) 11);
		style.setFont(headerFont);
		row.setRowStyle(style);
		HSSFCellStyle contentStyle = wb.createCellStyle(); // 内容样式
		contentStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		contentStyle.setWrapText(true);// 设置自动换行
		short width = 225 * 30, height = 25 * 20;
		for (int i = 0; i < excelHeader.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(excelHeader[i]);
			cell.setCellStyle(style);
			sheet.autoSizeColumn(i);
			sheet.setDefaultColumnStyle(i, contentStyle);
			sheet.setColumnWidth(i, width);
		}
		sheet.getRow(0).setHeight(height);
		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow(i + 1);
			Object[] obj = list.get(i);
			sheet.setDefaultColumnStyle(i, contentStyle);
			row.createCell((short) 0).setCellValue(i + 1);
			row.createCell((short) 1).setCellValue(obj[0].toString());
			row.createCell((short) 2).setCellValue(obj[1].toString());
		}
		return wb;
	}

}
