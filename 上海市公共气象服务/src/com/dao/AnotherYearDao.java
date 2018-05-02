/**
 * 
 */
package com.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.entity.AnotherYear;
import com.entity.Precipitation;

/**
 * @author 分裂状态。
 *
 */
public interface AnotherYearDao extends GenericRepository<AnotherYear, Integer> {

	@Query(value = "select max(Year(Spring_Date)) from AnotherYear", nativeQuery = true)
	public String getMaxYear();

	@Query(value = "select Year(Spring_Date) from AnotherYear", nativeQuery = true)
	public List<String> getYear();

	@Query(value = "select Max(DateYear) from Precipitation", nativeQuery = true)
	public String getTemMaxYear();

	@Query(value = "select distinct DateYear from Precipitation order by DateYear asc", nativeQuery = true)
	public List<String> getTemYear();

	@Query(value = "select * from AnotherYear", nativeQuery = true)
	// 春夏秋冬
	public List<AnotherYear> getAll();

	@Modifying
	@Query("update AnotherYear set Winter_Day = ? where Id = ?")
	// 修改信息
	public void updateInfo(int winterDay, int Id);

	@Query(value = "select CONVERT(varchar,Spring_Date,23) from AnotherYear where Spring_Date is not null and YEAR(Spring_Date) >= ? and YEAR(Spring_Date) <= ? order by MONTH(Spring_Date) asc,DAY(Spring_Date) asc", nativeQuery = true)
	// 入春最早
	public List<Object> getSpringMin(String beginYear, String endYear);

	@Query(value = "select CONVERT(varchar,Spring_Date,23) from AnotherYear where Spring_Date is not null and YEAR(Spring_Date) >= ? and YEAR(Spring_Date) <= ? order by MONTH(Spring_Date) desc,DAY(Spring_Date) desc", nativeQuery = true)
	// 入春最晚
	public List<Object> getSpringMax(String beginYear, String endYear);

	@Query(value = "select CONVERT(varchar,Summer_Date,23) from AnotherYear where Summer_Date is not null and YEAR(Summer_Date) >= ? and YEAR(Summer_Date) <= ? order by MONTH(Summer_Date) asc,DAY(Summer_Date) asc", nativeQuery = true)
	// 入夏最早
	public List<Object> getSummerMin(String beginYear, String endYear);

	@Query(value = "select CONVERT(varchar,Summer_Date,23) from AnotherYear where Summer_Date is not null and YEAR(Summer_Date) >= ? and YEAR(Summer_Date) <= ? order by MONTH(Summer_Date) desc,DAY(Summer_Date) desc", nativeQuery = true)
	// 入夏最晚
	public List<Object> getSummerMax(String beginYear, String endYear);

	@Query(value = "select CONVERT(varchar,Autumn_Date,23) from AnotherYear where Autumn_Date is not null and YEAR(Autumn_Date) >= ? and YEAR(Autumn_Date) <= ? order by MONTH(Autumn_Date) asc,DAY(Autumn_Date) asc", nativeQuery = true)
	// 入秋最早
	public List<Object> getAutumnMin(String beginYear, String endYear);

	@Query(value = "select CONVERT(varchar,Autumn_Date,23) from AnotherYear where Autumn_Date is not null and YEAR(Autumn_Date) >= ? and YEAR(Autumn_Date) <= ? order by MONTH(Autumn_Date) desc,DAY(Autumn_Date) desc", nativeQuery = true)
	// 入秋最晚
	public List<Object> getAutumnMax(String beginYear, String endYear);

	@Query(value = "select CONVERT(varchar,Winter_Date,23) from AnotherYear where Winter_Date is not null and YEAR(Winter_Date) >= ? and YEAR(Winter_Date) <= ? order by MONTH(Winter_Date) asc,DAY(Winter_Date) asc", nativeQuery = true)
	// 入冬最早
	public List<Object> getWinterMin(String beginYear, String endYear);

	@Query(value = "select CONVERT(varchar,Winter_Date,23) from AnotherYear where Winter_Date is not null and YEAR(Winter_Date) >= ? and YEAR(Winter_Date) <= ? order by MONTH(Winter_Date) desc,DAY(Winter_Date) desc", nativeQuery = true)
	// 入冬最晚
	public List<Object> getWinterMax(String beginYear, String endYear);

	@Query(value = "select DATEDIFF(day,Spring_Date,Summer_Date) as day,YEAR(Spring_Date) as year from AnotherYear where Spring_Date is not null and Summer_Date is not null and Year(Spring_Date) >= ? and Year(Summer_Date) <= ? order by DATEDIFF(day,Spring_Date,Summer_Date) desc", nativeQuery = true)
	// 春季长度最长
	public List<Object[]> getSpringMaxLength(String beginYear, String endYear);

	@Query(value = "select DATEDIFF(day,Spring_Date,Summer_Date) as day,YEAR(Spring_Date) as year from AnotherYear where Spring_Date is not null and Summer_Date is not null and Year(Spring_Date) >= ? and Year(Summer_Date) <= ? order by DATEDIFF(day,Spring_Date,Summer_Date) asc", nativeQuery = true)
	// 春季长度最短
	public List<Object[]> getSpringMinLength(String beginYear, String endYear);

	@Query(value = "select DATEDIFF(day,Summer_Date,Autumn_Date) as day,YEAR(Summer_Date) as year from AnotherYear where Summer_Date is not null and Autumn_Date is not null and Year(Summer_Date) >= ? and Year(Autumn_Date) <= ? order by DATEDIFF(day,Summer_Date,Autumn_Date) desc", nativeQuery = true)
	// 夏季长度最长
	public List<Object[]> getSummerMaxLength(String beginYear, String endYear);

	@Query(value = "select DATEDIFF(day,Summer_Date,Autumn_Date) as day,YEAR(Summer_Date) as year from AnotherYear where Summer_Date is not null and Autumn_Date is not null and Year(Summer_Date) >= ? and Year(Autumn_Date) <= ? order by DATEDIFF(day,Summer_Date,Autumn_Date) asc", nativeQuery = true)
	// 夏季长度最短
	public List<Object[]> getSummerMinLength(String beginYear, String endYear);

	@Query(value = "select DATEDIFF(day,Autumn_Date,Winter_Date) as day,YEAR(Autumn_Date) as year from AnotherYear where Autumn_Date is not null and Winter_Date is not null and Year(Autumn_Date) >= ? and Year(Winter_Date) <= ? order by DATEDIFF(day,Autumn_Date,Winter_Date) desc", nativeQuery = true)
	// 秋季长度最长
	public List<Object[]> getAutumnMaxLength(String beginYear, String endYear);

	@Query(value = "select DATEDIFF(day,Autumn_Date,Winter_Date) as day,YEAR(Autumn_Date) as year from AnotherYear where Autumn_Date is not null and Winter_Date is not null and Year(Autumn_Date) >= ? and Year(Winter_Date) <= ? order by DATEDIFF(day,Autumn_Date,Winter_Date) asc", nativeQuery = true)
	// 秋季长度最短
	public List<Object[]> getAutumnMinLength(String beginYear, String endYear);

	@Query(value = "select Winter_Day as day,YEAR(Winter_Date) as year from AnotherYear where Winter_Day is not null and Year(Winter_Date) >= ? and Year(Winter_Date) <= ? order by Winter_Day desc", nativeQuery = true)
	// 冬季长度最长
	public List<Object[]> getWinterMaxLength(String beginYear, String endYear);

	@Query(value = "select Winter_Day as day,YEAR(Winter_Date) as year from AnotherYear where Winter_Day is not null and Year(Winter_Date) >= ? and Year(Winter_Date) <= ? order by Winter_Day asc", nativeQuery = true)
	// 冬季长度最短
	public List<Object[]> getWinterMinLength(String beginYear, String endYear);

	@Query(value = "select CONVERT(varchar,ModDay,23) from [Mod] where [Year] >= ? and [Year] <= ? order by MONTH(ModDay) asc,DAY(ModDay) asc", nativeQuery = true)
	// 入梅最早
	public List<Object> getModMin(String modBeginYear, String modEndYear);

	@Query(value = "select CONVERT(varchar,ModDay,23) from [Mod] where [Year] >= ? and [Year] <= ? order by MONTH(ModDay) desc,DAY(ModDay) desc", nativeQuery = true)
	// 入梅最晚
	public List<Object> getModMax(String modBeginYear, String modEndYear);

	@Query(value = "select CONVERT(varchar,MeiDay,23) from [Mod] where [Year] >= ? and [Year] <= ? order by MONTH(MeiDay) asc,DAY(MeiDay) asc", nativeQuery = true)
	// 出梅最早
	public List<Object> getMeiMin(String modBeginYear, String modEndYear);

	@Query(value = "select CONVERT(varchar,MeiDay,23) from [Mod] where [Year] >= ? and [Year] <= ? order by MONTH(MeiDay) desc,DAY(MeiDay) desc", nativeQuery = true)
	// 出梅最晚
	public List<Object> getMeiMax(String modBeginYear, String modEndYear);

	@Query(value = "select MeiyuLength as mod,Year as year from [Mod] where [Year] >= ? and [Year] <= ? order by MeiyuLength desc", nativeQuery = true)
	// 梅雨长度最长
	public List<Object[]> getMeiyuLengthMax(String modBeginYear, String modEndYear);

	@Query(value = "select MeiyuLength as mod,Year as year from [Mod] where [Year] >= ? and [Year] <= ? order by MeiyuLength asc", nativeQuery = true)
	// 梅雨长度最短
	public List<Object[]> getMeiyuLengthMin(String modBeginYear, String modEndYear);

	@Query(value = "select MeiyuAmount as mod,[Year] as year from [Mod] where [Year] >= ? and [Year] <= ? order by MeiyuAmount desc", nativeQuery = true)
	// 梅雨量最多
	public List<Object[]> getMeiyuAmountMax(String modBeginYear, String modEndYear);

	@Query(value = "select MeiyuAmount as mod,[Year] as year from [Mod] where [Year] >= ? and [Year] <= ? order by MeiyuAmount asc", nativeQuery = true)
	// 梅雨量最少
	public List<Object[]> getMeiyuAmountMin(String modBeginYear, String modEndYear);

	// 高温总日数最多
	@Query(value = "select DateYear as dateYear,count(DateMon) as counts,count(case when DateMon=5 then DateDay end) as may,count(case when DateMon=6 then DateDay end) as june,count(case when DateMon=7 then DateDay end) as july,count(case when DateMon=8 then DateDay end) as august,count(case when DateMon=9 then DateDay end) as sep from Precipitation where Station_Name = '徐家汇' and TEM_Max >= ? and DateYear >= ? and DateYear <= ? group by DateYear order by counts desc", nativeQuery = true)
	public List<Object[]> getTemCountDayMax(Integer tem, String temBeginYear, String temEndYear);

	// 高温总日数最少
	@Query(value = "select DateYear as dateYear,count(DateMon) as counts,count(case when DateMon=5 then DateDay end) as may,count(case when DateMon=6 then DateDay end) as june,count(case when DateMon=7 then DateDay end) as july,count(case when DateMon=8 then DateDay end) as august,count(case when DateMon=9 then DateDay end) as sep from Precipitation where Station_Name = '徐家汇' and TEM_Max >= ? and DateYear >= ? and DateYear <= ? group by DateYear order by counts asc", nativeQuery = true)
	public List<Object[]> getTemCountDayMin(Integer tem, String temBeginYear, String temEndYear);

	/**
	 * 高温 总日数 最多
	 * @param tem
	 * @param temBeginYear
	 * @param temEndYear
	 * @return
	 */
	@Query(value = "select count(DateMon),DateYear from Precipitation where Station_Name = '徐家汇' and TEM_Max >= ? and DateYear >= ? and DateYear <= ? group by DateYear order by count(DateMon) desc", nativeQuery = true)
	public List<Object[]> getTemCountDayDesc(Integer tem, String temBeginYear, String temEndYear);

	/**
	 * 高温 总日数 最少
	 * @param tem
	 * @param temBeginYear
	 * @param temEndYear
	 * @return
	 */
	@Query(value = "select count(DateMon),DateYear from Precipitation where Station_Name = '徐家汇' and TEM_Max >= ? and DateYear >= ? and DateYear <= ? group by DateYear order by count(DateMon) asc", nativeQuery = true)
	public List<Object[]> getTemCountDayAsc(Integer tem, String temBeginYear, String temEndYear);

	// 高温出现时间最早
	@Query(value = "select Min(DateYearMonDay) as Date,DateYear as DateYear from Precipitation where Station_Name = '徐家汇' and TEM_Max >= ? and DateYear >= ? and DateYear <= ? group by DateYear order by Date asc", nativeQuery = true)
	public List<Object[]> getTemTimeMin(Integer tem, String temBeginYear, String temEndYear);

	// 高温出现时间最晚
	@Query(value = "select Max(DateYearMonDay) as Date,DateYear as DateYear from Precipitation where Station_Name = '徐家汇' and TEM_Max >= ? and DateYear >= ? and DateYear <= ? group by DateYear order by Date desc", nativeQuery = true)
	public List<Object[]> getTemTimeMax(Integer tem, String temBeginYear, String temEndYear);

	@Query("select p from Precipitation p where stationName = '徐家汇' and temMax is not null and dateYear = ? order by dateMon,dateDate asc")
	public List<Precipitation> getInfo(Integer year);

	@Query("select a from AnotherYear a where id = ?")
	public AnotherYear getAnotherYearById(Integer id);

	@Modifying
	@Query("update AnotherYear set springDate = ?,summerDate = ?,autumnDate = ?,winterDate = ? where id = ? ")
	public void updateAnotherYear(Date springDate, Date summerDate, Date autumnDate, Date winterDate, Integer id);

	// 春
	@Query(value = "select DATEDIFF(day,Spring_Date,Summer_Date) as day,YEAR(Spring_Date) as year from AnotherYear where Spring_Date is not null and Summer_Date is not null and Year(Spring_Date) >= ? and Year(Spring_Date) <= ? order by year asc", nativeQuery = true)
	public List<Object[]> getDataHignChartSpring(String beginYear, String endYear);

	// 夏
	@Query(value = "select DATEDIFF(day,Summer_Date,Autumn_Date) as day,YEAR(Summer_Date) as year from AnotherYear where Summer_Date is not null and Autumn_Date is not null and Year(Summer_Date) >= ? and Year(Summer_Date) <= ? order by year asc", nativeQuery = true)
	public List<Object[]> getDataHignChartSummer(String beginYear, String endYear);

	// 秋
	@Query(value = "select DATEDIFF(day,Autumn_Date,Winter_Date) as day,YEAR(Autumn_Date) as year from AnotherYear where Autumn_Date is not null and Winter_Date is not null and Year(Autumn_Date) >= ? and Year(Autumn_Date) <= ? order by year asc", nativeQuery = true)
	public List<Object[]> getDataHignChartAutumn(String beginYear, String endYear);

	// 冬
	@Query(value = "select Winter_Day as day,YEAR(Winter_Date) as year from AnotherYear where Winter_Day is not null and Year(Winter_Date) >= ? and Year(Winter_Date) <= ? order by year asc", nativeQuery = true)
	public List<Object[]> getDataHignChartWinter(String beginYear, String endYear);

	// 梅雨长度
	@Query(value = "select MeiyuLength as mod,Year as year from [Mod] where [Year] >= ? and [Year] <= ? order by year asc", nativeQuery = true)
	public List<Object[]> getModLength(String modBeginYear, String modEndYear);

	// 梅雨量
	@Query(value = "select MeiyuAmount as mod,[Year] as year from [Mod] where [Year] >= ? and [Year] <= ? order by year asc", nativeQuery = true)
	public List<Object[]> getModAmount(String modBeginYear, String modEndYear);

	// 高温总日数
	@Query(value = "select DateYear as dateYear,count(DateMon) as counts from Precipitation where Station_Name = '徐家汇' and TEM_Max >= ? and DateYear >= ? and DateYear <= ? group by dateYear order by dateYear asc", nativeQuery = true)
	public List<Object[]> getTemDayCount(Integer tem, String temBeginYear, String temEndYear);
}
