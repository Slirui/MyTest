/**
 * 
 */
package com.service;

import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.data.domain.Pageable;

import com.entity.AnotherYear;
import com.entity.Precipitation;
import com.entity.ResultColList;
import com.entity.TemDayCount;
import com.pages.QueryResult;

/**
 * @author 分裂状态。
 *
 */
public interface AnotherYearService {

	// 获取最大的年份
	public String getMaxYear();

	// 获取年份
	public List<String> getYear();

	// 温度的年份
	public String getTemMaxYear();

	// 温度最大年份
	public List<String> getTemYear();

	// 分页显示信息
	public QueryResult<AnotherYear> listAnotherYear(Pageable page);

	// 春夏秋冬
	public List<AnotherYear> getAll();

	// 修改信息
	public void updateInfo(int winterDay, int Id);

	// 入春最早
	public List<Object> getSpringMin(String beginYear, String endYear);

	// 入春最晚
	public List<Object> getSpringMax(String beginYear, String endYear);

	// 入夏最早
	public List<Object> getSummerMin(String beginYear, String endYear);

	// 入夏最晚
	public List<Object> getSummerMax(String beginYear, String endYear);

	// 入秋最早
	public List<Object> getAutumnMin(String beginYear, String endYear);

	// 入秋最晚
	public List<Object> getAutumnMax(String beginYear, String endYear);

	// 入冬最早
	public List<Object> getWinterMin(String beginYear, String endYear);

	// 入冬最晚
	public List<Object> getWinterMax(String beginYear, String endYear);

	// 春季长度最长
	public List<Object[]> getSpringMaxLength(String beginYear, String endYear);

	// 春季长度最短
	public List<Object[]> getSpringMinLength(String beginYear, String endYear);

	// 夏季长度最长
	public List<Object[]> getSummerMaxLength(String beginYear, String endYear);

	// 夏季长度最短
	public List<Object[]> getSummerMinLength(String beginYear, String endYear);

	// 秋季长度最长
	public List<Object[]> getAutumnMaxLength(String beginYear, String endYear);

	// 秋季长度最短
	public List<Object[]> getAutumnMinLength(String beginYear, String endYear);

	// 冬季长度最长
	public List<Object[]> getWinterMaxLength(String beginYear, String endYear);

	// 冬季长度最短
	public List<Object[]> getWinterMinLength(String beginYear, String endYear);

	// 入梅最早
	public List<Object> getModMin(String modBeginYear, String modEndYear);

	// 入梅最晚
	public List<Object> getModMax(String modBeginYear, String modEndYear);

	// 出梅最早
	public List<Object> getMeiMin(String modBeginYear, String modEndYear);

	// 出梅最晚
	public List<Object> getMeiMax(String modBeginYear, String modEndYear);

	// 梅雨长度最长
	public List<Object[]> getMeiyuLengthMax(String modBeginYear, String modEndYear);

	// 梅雨长度最短
	public List<Object[]> getMeiyuLengthMin(String modBeginYear, String modEndYear);

	// 梅雨量最多
	public List<Object[]> getMeiyuAmountMax(String modBeginYear, String modEndYear);

	// 梅雨量最少
	public List<Object[]> getMeiyuAmountMin(String modBeginYear, String modEndYear);

	// 高温总日数最多
	public List<Object[]> getTemCountDayMax(Integer tem, String temBeginYear, String temEndYear);

	// 高温总日数最少
	public List<Object[]> getTemCountDayMin(Integer tem, String temBeginYear, String temEndYear);

	/**
	 * 高温 总日数 最多
	 * 
	 * @param tem
	 * @param temBeginYear
	 * @param temEndYear
	 * @return
	 */
	public List<Object[]> getTemCountDayDesc(Integer tem, String temBeginYear, String temEndYear);

	/**
	 * 高温 总日数 最少
	 * 
	 * @param tem
	 * @param temBeginYear
	 * @param temEndYear
	 * @return
	 */
	public List<Object[]> getTemCountDayAsc(Integer tem, String temBeginYear, String temEndYear);

	// 高温出现时间最早
	public List<Object[]> getTemTimeMin(Integer tem, String temBeginYear, String temEndYear);

	// 高温出现时间最晚
	public List<Object[]> getTemTimeMax(Integer tem, String temBeginYear, String temEndYear);

	public List<Precipitation> getInfo(Integer year);

	public void saveAnotherYear(AnotherYear another);

	// 删除信息
	public void deleteInfo(Integer id);

	public AnotherYear getAnotherYearById(Integer id);

	public void updateAnotherYear(Date springDate, Date summerDate, Date autumnDate, Date winterDate, Integer id);

	// 春
	public List<Object[]> getDataHignChartSpring(String beginYear, String endYear);

	// 夏
	public List<Object[]> getDataHignChartSummer(String beginYear, String endYear);

	// 秋
	public List<Object[]> getDataHignChartAutumn(String beginYear, String endYear);

	// 冬
	public List<Object[]> getDataHignChartWinter(String beginYear, String endYear);

	// 梅雨长度
	public List<Object[]> getModLength(String modBeginYear, String modEndYear);

	// 梅雨量
	public List<Object[]> getModAmount(String modBeginYear, String modEndYear);

	// 高温总日数
	public List<Object[]> getTemDayCount(Integer tem, String temBeginYear, String temEndYear);

	// 导出Excel
	public HSSFWorkbook exportDateMin(List<Object[]> list);

	// 导出Excel
	public HSSFWorkbook exportDateMax(List<Object[]> list);

	// 导出Excel
	public HSSFWorkbook exportData(List<Object[]> list);

	// 导出Excel
	public HSSFWorkbook exportModAmount(List<Object[]> list);

	// 导出Excel
	public HSSFWorkbook exportTemSumDay1(List<Object[]> list);

	// 导出Excel
	public HSSFWorkbook exportTemSumDay2(List<TemDayCount> list);

	// 导出Excel
	public HSSFWorkbook exportTemTimeMin(List<Object[]> list);

	// 导出Excel
	public HSSFWorkbook exportTemTimeMax(List<Object[]> list);

	// 导出Excel
	public HSSFWorkbook exportDayCount(List<Object[]> list);

}
