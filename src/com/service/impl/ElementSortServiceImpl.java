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

import com.dao.ElementSortDao;
import com.dao.QueryKword;
import com.entity.Precipitation;
import com.service.ElementSortService;
import com.util.Pageutil;

/**
 * @author 分裂状态。
 *
 */
@Service
public class ElementSortServiceImpl implements ElementSortService {

	@Autowired
	private ElementSortDao elementSortDao;

	// 获取站点
	public List<String> getStationName() {
		return elementSortDao.getStationName();
	}

	// 获取开始时间
	public Object getBeginDate() {
		return elementSortDao.getBeginDate();
	}

	// 获取结束时间
	public Object getEndDate() {
		return elementSortDao.getEndDate();
	}

	// 最高温度（最高）
	public List<Precipitation> getTemMaxDesc(HttpServletRequest request, String name, Integer beginYear,
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
		qkv.add(QueryKword.isNotNULL("temMax"));
		return elementSortDao.list(request, qkv, Pageutil.getPageable(0, 100, new Sort(Direction.DESC, "temMax")))
				.getList();
	}

	// 最高温度（最低）
	public List<Precipitation> getTemMaxAsc(HttpServletRequest request, String name, Integer beginYear, Integer endYear,
			Date beginDate, Date endDate) {
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
		qkv.add(QueryKword.isNotNULL("temMax"));
		return elementSortDao.list(request, qkv, Pageutil.getPageable(0, 1000, new Sort(Direction.ASC, "temMax")))
				.getList();
	}

	// 最低温度(最高)
	public List<Precipitation> getTemMinDesc(HttpServletRequest request, String name, Integer beginYear,
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
		qkv.add(QueryKword.isNotNULL("temMin"));
		return elementSortDao.list(request, qkv, Pageutil.getPageable(0, 100, new Sort(Direction.DESC, "temMin")))
				.getList();
	}

	// 最低温度(最低)
	public List<Precipitation> getTemMinAsc(HttpServletRequest request, String name, Integer beginYear, Integer endYear,
			Date beginDate, Date endDate) {
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
		qkv.add(QueryKword.isNotNULL("temMin"));
		return elementSortDao.list(request, qkv, Pageutil.getPageable(0, 1000, new Sort(Direction.ASC, "temMin")))
				.getList();
	}

	// 平均温度(最高)
	public List<Precipitation> getTemAvgDesc(HttpServletRequest request, String name, Integer beginYear,
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
		qkv.add(QueryKword.isNotNULL("temAvg"));
		return elementSortDao.list(request, qkv, Pageutil.getPageable(0, 100, new Sort(Direction.DESC, "temAvg")))
				.getList();
	}

	// 平均温度(最低)
	public List<Precipitation> getTemAvgAsc(HttpServletRequest request, String name, Integer beginYear, Integer endYear,
			Date beginDate, Date endDate) {
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
		qkv.add(QueryKword.isNotNULL("temAvg"));
		return elementSortDao.list(request, qkv, Pageutil.getPageable(0, 1000, new Sort(Direction.ASC, "temAvg")))
				.getList();
	}

	// 获取要素信息
	public List<Precipitation> getElementInfos(HttpServletRequest request, String name, Date beginDate, Date endDate) {
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
		return elementSortDao
				.list(request, qkv, Pageutil.getPageable(0, 100, new Sort(Sort.Direction.DESC, "preTime2020")))
				.getList();
	}

	// 日最高温度(最高)
	public List<Precipitation> getTemMax_Max(HttpServletRequest request, String name, Date beginDate, Date endDate) {
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
		qkv.add(QueryKword.isNotNULL("temMax"));
		return elementSortDao.list(request, qkv, Pageutil.getPageable(0, 100, new Sort(Sort.Direction.DESC, "temMax")))
				.getList();
	}

	// 日最高温度(最低)
	public List<Precipitation> getTemMax_Min(HttpServletRequest request, String name, Date beginDate, Date endDate) {
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
		qkv.add(QueryKword.isNotNULL("temMax"));
		return elementSortDao.list(request, qkv, Pageutil.getPageable(0, 1000, new Sort(Sort.Direction.ASC, "temMax")))
				.getList();
	}

	// 日最低温度(最高)
	public List<Precipitation> getTemMin_Max(HttpServletRequest request, String name, Date beginDate, Date endDate) {
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
		qkv.add(QueryKword.isNotNULL("temMin"));
		return elementSortDao.list(request, qkv, Pageutil.getPageable(0, 100, new Sort(Sort.Direction.DESC, "temMin")))
				.getList();
	}

	// 日最低温度(最低)
	public List<Precipitation> getTemMin_Min(HttpServletRequest request, String name, Date beginDate, Date endDate) {
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
		qkv.add(QueryKword.isNotNULL("temMin"));
		return elementSortDao.list(request, qkv, Pageutil.getPageable(0, 1000, new Sort(Sort.Direction.ASC, "temMin")))
				.getList();
	}

	// 日平均温度(最高)
	public List<Precipitation> getTemAvg_Max(HttpServletRequest request, String name, Date beginDate, Date endDate) {
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
		qkv.add(QueryKword.isNotNULL("temAvg"));
		return elementSortDao.list(request, qkv, Pageutil.getPageable(0, 100, new Sort(Sort.Direction.DESC, "temAvg")))
				.getList();
	}

	// 日平均温度(最低)
	public List<Precipitation> getTemAvg_Min(HttpServletRequest request, String name, Date beginDate, Date endDate) {
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
		qkv.add(QueryKword.isNotNULL("temAvg"));
		return elementSortDao.list(request, qkv, Pageutil.getPageable(0, 1000, new Sort(Sort.Direction.ASC, "temAvg")))
				.getList();
	}

	// 获取所有日降水量（20-20）
	public List<Precipitation> getElementsDayPre2020(String name, String beginDate, String endDate) {
		return elementSortDao.getElementsDayPre2020(name, beginDate, endDate);
	}

	// 获取最高温度
	public List<Precipitation> getTemMaxG(String name, String beginDate, String endDate) {
		return elementSortDao.getTemMaxG(name, beginDate, endDate);
	}

	// 获取最高温度
	public List<Precipitation> getTemMaxD(String name, String beginDate, String endDate) {
		return elementSortDao.getTemMaxD(name, beginDate, endDate);
	}

	// 获取最低温度
	public List<Precipitation> getTemMinG(String name, String beginDate, String endDate) {
		return elementSortDao.getTemMinG(name, beginDate, endDate);
	}

	// 获取最低温度
	public List<Precipitation> getTemMinD(String name, String beginDate, String endDate) {
		return elementSortDao.getTemMinD(name, beginDate, endDate);
	}

	// 获取平均温度
	public List<Precipitation> getTemAvgG(String name, String beginDate, String endDate) {
		return elementSortDao.getTemAvgG(name, beginDate, endDate);
	}

	// 获取平均温度
	public List<Precipitation> getTemAvgD(String name, String beginDate, String endDate) {
		return elementSortDao.getTemAvgD(name, beginDate, endDate);
	}

	public HSSFWorkbook exportElementsDayPre2020(List<Object[]> list) {
		String[] excelHeader = { "排名", "数值", "日期" };
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("日降水量（20-20）");
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

	public HSSFWorkbook exportElementsTem(List<Object[]> list) {
		String[] excelHeader = { "排名", "温度", "日期" };
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("温度");
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

	// 获取最高温度(最高)
	public List<Object[]> getTemMaxHighchartsMax(String name, Integer beginYear, Integer endYear) {
		return elementSortDao.getTemMaxHighchartsMax(name, beginYear, endYear);
	}

	// 获取最高温度(最低)
	public List<Object[]> getTemMaxHighchartsMin(String name, Integer beginYear, Integer endYear) {
		return elementSortDao.getTemMaxHighchartsMin(name, beginYear, endYear);
	}

	// 获取最低温度(最高)
	public List<Object[]> getTemMinHighchartsMax(String name, Integer beginYear, Integer endYear) {
		return elementSortDao.getTemMinHighchartsMax(name, beginYear, endYear);
	}

	// 获取最低温度(最低)
	public List<Object[]> getTemMinHighchartsMin(String name, Integer beginYear, Integer endYear) {
		return elementSortDao.getTemMinHighchartsMin(name, beginYear, endYear);
	}

	// 获取平均温度(最高)
	public List<Object[]> getTemAvgHighchartsMax(String name, Integer beginYear, Integer endYear) {
		return elementSortDao.getTemAvgHighchartsMax(name, beginYear, endYear);
	}

	// 获取平均温度(最低)
	public List<Object[]> getTemAvgHighchartsMin(String name, Integer beginYear, Integer endYear) {
		return elementSortDao.getTemAvgHighchartsMin(name, beginYear, endYear);
	}

	// 获取日降水量(20-20)
	public List<Object[]> getElementsHighcharts(String name, Integer beginYear, Integer endYear) {
		return elementSortDao.getElementsHighcharts(name, beginYear, endYear);
	}

}
