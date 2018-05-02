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
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.dao.QueryKword;
import com.dao.RainTypeDao;
import com.entity.Precipitation;
import com.service.RainType2Service;
import com.util.Pageutil;

/**
 * @author 分裂状态。
 *
 */
@Service
public class RainType2ServiceImpl implements RainType2Service {

	@Autowired
	private RainTypeDao rainTypeDao;

	// 日雨量类型2
	public List<Precipitation> getRainType2Desc(HttpServletRequest request, String name, Integer beginYear,
			Integer endYear, Date beginDate, Date endDate) {
		List<QueryKword> qkv = new ArrayList<QueryKword>();
		if (name != null) {
			qkv.add(QueryKword.equalTo("stationName", name));
		}
		if (beginYear != null) {
			qkv.add(QueryKword.greaterThanOrEqualTo("dateYear", beginYear));
		}
		if (endYear != null) {
			qkv.add(QueryKword.lessThanOrEqualTo("dateYear", endYear));
		}
		if (beginDate != null) {
			qkv.add(QueryKword.greaterThanOrEqualTo("dateYearMonDay", beginDate));
		}
		if (endDate != null) {
			qkv.add(QueryKword.lessThanOrEqualTo("dateYearMonDay", endDate));
		}
		qkv.add(QueryKword.isNotNULL("preTime2020"));
		qkv.add(QueryKword.greaterThan("preTime2020", 0.0));
		return rainTypeDao.list(request, qkv, Pageutil.getPageable(0, 100, new Sort(Direction.DESC, "preTime2020")))
				.getList();
	}

	// 日雨量类型2
	public List<Precipitation> getRainType2Asc(HttpServletRequest request, String name, Integer beginYear,
			Integer endYear, Date beginDate, Date endDate) {
		List<QueryKword> qkv = new ArrayList<QueryKword>();
		if (name != null) {
			qkv.add(QueryKword.equalTo("stationName", name));
		}
		if (beginYear != null) {
			qkv.add(QueryKword.greaterThanOrEqualTo("dateYear", beginYear));
		}
		if (endYear != null) {
			qkv.add(QueryKword.lessThanOrEqualTo("dateYear", endYear));
		}
		if (beginDate != null) {
			qkv.add(QueryKword.greaterThanOrEqualTo("dateYearMonDay", beginDate));
		}
		if (endDate != null) {
			qkv.add(QueryKword.lessThanOrEqualTo("dateYearMonDay", endDate));
		}
		qkv.add(QueryKword.isNotNULL("preTime2020"));
		qkv.add(QueryKword.greaterThan("preTime2020", 0.0));
		return rainTypeDao.list(request, qkv, Pageutil.getPageable(0, 100, new Sort(Direction.ASC, "preTime2020")))
				.getList();
	}

	// 累计雨量
	public List<Object[]> getRainType2SumPreDesc(String name, Integer beginYear, Integer endYear, Date beginDate,
			Date endDate) {
		return rainTypeDao.getRainType3SumPreDesc(name, beginYear, endYear, beginDate, endDate);
	}

	// 获取日雨量信息2
	public List<Precipitation> getRainType2(String name, Integer beginYear, Integer endYear, String beginDate,
			String endDate) {
		return rainTypeDao.getRainType2(name, beginYear, endYear, beginDate, endDate);
	}

	// 导出Excel
	public HSSFWorkbook exportRainType2(List<Object[]> list) {
		String[] excelHeader = { "排名", "降水量", "日期" };
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("逐日雨量");
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

	// 导出Excel(累计雨量)
	public HSSFWorkbook exportRainType2SumPre(List<Object[]> list) {
		String[] excelHeader = { "排名", "降水量", "年份" };
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("累计雨量");
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
