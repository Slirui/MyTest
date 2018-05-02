/**
 * 
 */
package com.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dao.AnotherYearDao;
import com.entity.AnotherYear;
import com.entity.Precipitation;
import com.entity.TemDayCount;
import com.pages.QueryResult;
import com.service.AnotherYearService;

/**
 * @author 分裂状态。
 *
 */
@Service
public class AnotherYearServiceImpl implements AnotherYearService {

	@Autowired
	private AnotherYearDao anotherYearDao;

	// 获取年份
	public List<String> getYear() {
		return anotherYearDao.getYear();
	}

	// 获取最大年份
	public String getMaxYear() {
		return anotherYearDao.getMaxYear();
	}

	// 温度的年份
	public String getTemMaxYear() {
		return anotherYearDao.getTemMaxYear();
	}

	// 温度最大年份
	public List<String> getTemYear() {
		return anotherYearDao.getTemYear();
	}

	// 分页显示信息
	public QueryResult<AnotherYear> listAnotherYear(Pageable page) {
		return new QueryResult<AnotherYear>(anotherYearDao.findAll(page));
	}

	// 修改信息
	public void updateInfo(int winterDay, int Id) {
		anotherYearDao.updateInfo(winterDay, Id);
	}

	// 春夏秋冬
	public List<AnotherYear> getAll() {
		return anotherYearDao.getAll();
	}

	// 入春最早
	public List<Object> getSpringMin(String beginYear, String endYear) {
		return anotherYearDao.getSpringMin(beginYear, endYear);
	}

	// 入春最晚
	public List<Object> getSpringMax(String beginYear, String endYear) {
		return anotherYearDao.getSpringMax(beginYear, endYear);
	}

	// 入夏最早
	public List<Object> getSummerMin(String beginYear, String endYear) {
		return anotherYearDao.getSummerMin(beginYear, endYear);
	}

	// 入夏最晚
	public List<Object> getSummerMax(String beginYear, String endYear) {
		return anotherYearDao.getSummerMax(beginYear, endYear);
	}

	// 入秋最早
	public List<Object> getAutumnMin(String beginYear, String endYear) {
		return anotherYearDao.getAutumnMin(beginYear, endYear);
	}

	// 入秋最晚
	public List<Object> getAutumnMax(String beginYear, String endYear) {
		return anotherYearDao.getAutumnMax(beginYear, endYear);
	}

	// 入冬最早
	public List<Object> getWinterMin(String beginYear, String endYear) {
		return anotherYearDao.getWinterMin(beginYear, endYear);
	}

	// 入冬最晚
	public List<Object> getWinterMax(String beginYear, String endYear) {
		return anotherYearDao.getWinterMax(beginYear, endYear);
	}

	// 春季长度最长
	public List<Object[]> getSpringMaxLength(String beginYear, String endYear) {
		return anotherYearDao.getSpringMaxLength(beginYear, endYear);
	}

	// 春季长度最短
	public List<Object[]> getSpringMinLength(String beginYear, String endYear) {
		return anotherYearDao.getSpringMinLength(beginYear, endYear);
	}

	// 夏季长度最长
	public List<Object[]> getSummerMaxLength(String beginYear, String endYear) {
		return anotherYearDao.getSummerMaxLength(beginYear, endYear);
	}

	// 夏季长度最短
	public List<Object[]> getSummerMinLength(String beginYear, String endYear) {
		return anotherYearDao.getSummerMinLength(beginYear, endYear);
	}

	// 秋季长度最长
	public List<Object[]> getAutumnMaxLength(String beginYear, String endYear) {
		return anotherYearDao.getAutumnMaxLength(beginYear, endYear);
	}

	// 秋季长度最短
	public List<Object[]> getAutumnMinLength(String beginYear, String endYear) {
		return anotherYearDao.getAutumnMinLength(beginYear, endYear);
	}

	// 冬季长度最长
	public List<Object[]> getWinterMaxLength(String beginYear, String endYear) {
		return anotherYearDao.getWinterMaxLength(beginYear, endYear);
	}

	// 冬季长度最短
	public List<Object[]> getWinterMinLength(String beginYear, String endYear) {
		return anotherYearDao.getWinterMinLength(beginYear, endYear);
	}

	// 入梅最早
	public List<Object> getModMin(String modBeginYear, String modEndYear) {
		return anotherYearDao.getModMin(modBeginYear, modEndYear);
	}

	// 入梅最晚
	public List<Object> getModMax(String modBeginYear, String modEndYear) {
		return anotherYearDao.getModMax(modBeginYear, modEndYear);
	}

	// 出梅最早
	public List<Object> getMeiMin(String modBeginYear, String modEndYear) {
		return anotherYearDao.getMeiMin(modBeginYear, modEndYear);
	}

	// 出梅最晚
	public List<Object> getMeiMax(String modBeginYear, String modEndYear) {
		return anotherYearDao.getMeiMax(modBeginYear, modEndYear);
	}

	// 梅雨长度最长
	public List<Object[]> getMeiyuLengthMax(String modBeginYear, String modEndYear) {
		return anotherYearDao.getMeiyuLengthMax(modBeginYear, modEndYear);
	}

	// 梅雨长度最短
	public List<Object[]> getMeiyuLengthMin(String modBeginYear, String modEndYear) {
		return anotherYearDao.getMeiyuLengthMin(modBeginYear, modEndYear);
	}

	// 梅雨量最多
	public List<Object[]> getMeiyuAmountMax(String modBeginYear, String modEndYear) {
		return anotherYearDao.getMeiyuAmountMax(modBeginYear, modEndYear);
	}

	// 梅雨量最少
	public List<Object[]> getMeiyuAmountMin(String modBeginYear, String modEndYear) {
		return anotherYearDao.getMeiyuAmountMin(modBeginYear, modEndYear);
	}

	// 高温总日数最多
	public List<Object[]> getTemCountDayMax(Integer tem, String temBeginYear, String temEndYear) {
		return anotherYearDao.getTemCountDayMax(tem, temBeginYear, temEndYear);
	}

	// 高温总日数最少
	public List<Object[]> getTemCountDayMin(Integer tem, String temBeginYear, String temEndYear) {
		return anotherYearDao.getTemCountDayMin(tem, temBeginYear, temEndYear);
	}

	/**
	 * 高温 总日数 最多
	 * 
	 * @param tem
	 * @param temBeginYear
	 * @param temEndYear
	 * @return
	 */
	public List<Object[]> getTemCountDayDesc(Integer tem, String temBeginYear, String temEndYear) {
		return anotherYearDao.getTemCountDayDesc(tem, temBeginYear, temEndYear);
	}

	/**
	 * 高温 总日数 最少
	 * 
	 * @param tem
	 * @param temBeginYear
	 * @param temEndYear
	 * @return
	 */
	public List<Object[]> getTemCountDayAsc(Integer tem, String temBeginYear, String temEndYear) {
		return anotherYearDao.getTemCountDayAsc(tem, temBeginYear, temEndYear);
	}

	// 高温出现时间最早
	public List<Object[]> getTemTimeMin(Integer tem, String temBeginYear, String temEndYear) {
		return anotherYearDao.getTemTimeMin(tem, temBeginYear, temEndYear);
	}

	// 高温出现时间最晚
	public List<Object[]> getTemTimeMax(Integer tem, String temBeginYear, String temEndYear) {
		return anotherYearDao.getTemTimeMax(tem, temBeginYear, temEndYear);
	}

	public List<Precipitation> getInfo(Integer year) {
		return anotherYearDao.getInfo(year);
	}

	public void saveAnotherYear(AnotherYear another) {
		anotherYearDao.save(another);
	}

	public void deleteInfo(Integer id) {
		anotherYearDao.delete(id);
	}

	public AnotherYear getAnotherYearById(Integer id) {
		return anotherYearDao.getAnotherYearById(id);
	}

	public void updateAnotherYear(Date springDate, Date summerDate, Date autumnDate, Date winterDate, Integer id) {
		anotherYearDao.updateAnotherYear(springDate, summerDate, autumnDate, winterDate, id);
	}

	// 春
	public List<Object[]> getDataHignChartSpring(String beginYear, String endYear) {
		return anotherYearDao.getDataHignChartSpring(beginYear, endYear);
	}

	// 夏
	public List<Object[]> getDataHignChartSummer(String beginYear, String endYear) {
		return anotherYearDao.getDataHignChartSummer(beginYear, endYear);
	}

	// 秋
	public List<Object[]> getDataHignChartAutumn(String beginYear, String endYear) {
		return anotherYearDao.getDataHignChartAutumn(beginYear, endYear);
	}

	// 冬
	public List<Object[]> getDataHignChartWinter(String beginYear, String endYear) {
		return anotherYearDao.getDataHignChartWinter(beginYear, endYear);
	}

	// 梅雨量
	public List<Object[]> getModAmount(String modBeginYear, String modEndYear) {
		return anotherYearDao.getModAmount(modBeginYear, modEndYear);
	}

	// 梅雨长度
	public List<Object[]> getModLength(String modBeginYear, String modEndYear) {
		return anotherYearDao.getModLength(modBeginYear, modEndYear);
	}

	// 高温总日数
	public List<Object[]> getTemDayCount(Integer tem, String temBeginYear, String temEndYear) {
		return anotherYearDao.getTemDayCount(tem, temBeginYear, temEndYear);
	}

	public HSSFWorkbook exportDateMin(List<Object[]> list) {
		String[] excelHeader = { "排名", "最早" };
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("重要天气");
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
			row.createCell((short) 1).setCellValue(obj[0].toString() + "（"+ obj[1].toString() +"）");
		}
		return wb;
	}

	public HSSFWorkbook exportDateMax(List<Object[]> list) {
		String[] excelHeader = { "排名", "最晚" };
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("重要天气");
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
			row.createCell((short) 1).setCellValue(obj[0].toString() + "（"+ obj[1].toString() +"）");
		}
		return wb;
	}

	// 导出Excel
	public HSSFWorkbook exportData(List<Object[]> list) {
		String[] excelHeader = { "排名", "长度", "年份" };
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("重要天气");
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

	// 导出Excel
	public HSSFWorkbook exportModAmount(List<Object[]> list) {
		String[] excelHeader = { "排名", "梅雨量", "年份" };
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("重要天气");
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

	// 导出Excel
	public HSSFWorkbook exportTemSumDay1(List<Object[]> list) {
		String[] excelHeader = { "排名", "年份", "日数" };
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("高温");
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
			row.createCell((short) 1).setCellValue(obj[1] + "年");
			row.createCell((short) 2).setCellValue(obj[0] + "天");
		}
		return wb;
	}

	public HSSFWorkbook exportTemSumDay2(List<TemDayCount> list) {
		String[] excelHeader = { "排名", "年份", "日数", "5月", "6月", "7月", "8月", "9月" };
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("高温");
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
			TemDayCount tdc = list.get(i);
			sheet.setDefaultColumnStyle(i, contentStyle);
			row.createCell((short) 0).setCellValue(tdc.getSortNum());
			row.createCell((short) 1).setCellValue(tdc.getYear() + "年");
			row.createCell((short) 2).setCellValue(tdc.getSumDay() + "天");
			row.createCell((short) 3).setCellValue(tdc.getMay() + "天");
			row.createCell((short) 4).setCellValue(tdc.getJune() + "天");
			row.createCell((short) 5).setCellValue(tdc.getJune() + "天");
			row.createCell((short) 6).setCellValue(tdc.getAugust() + "天");
			row.createCell((short) 7).setCellValue(tdc.getSep() + "天");
		}
		return wb;
	}

	// 导出Excel
	public HSSFWorkbook exportTemTimeMin(List<Object[]> list) {
		String[] excelHeader = { "排名", "最早日期", "年份" };
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("高温");
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
			sheet.setDefaultColumnStyle(i, contentStyle);
		}
		return wb;
	}

	// 导出Excel
	public HSSFWorkbook exportTemTimeMax(List<Object[]> list) {
		String[] excelHeader = { "排名", "最晚日期", "年份" };
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("高温");
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

	// 导出Excel
	public HSSFWorkbook exportDayCount(List<Object[]> list) {
		String[] excelHeader = { "排名", "日数", "年份" };
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("高温");
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
			row.createCell((short) 1).setCellValue(obj[0] + "日");
			row.createCell((short) 2).setCellValue(obj[1] + "年");
		}
		return wb;
	}

}
