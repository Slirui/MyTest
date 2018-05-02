/**
 * 
 */
package com.controller;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.entity.AnotherYear;
import com.entity.CommonAnotherYear;
import com.entity.Mod;
import com.entity.ResultColList;
import com.entity.TemDayCount;
import com.entity.TemTime;
import com.google.gson.Gson;
import com.pages.QueryResult;
import com.service.AnotherYearService;
import com.service.ModService;
import com.service.ResultService;
import com.sun.star.io.IOException;
import com.util.Pageutil;
import com.util.TestResult;
import com.util.Tool;

/**
 * 
 * @author 分裂状态。
 *
 */
@Controller
public class AnotherYearControl {

	@Autowired
	private AnotherYearService anotherYearService;

	@Autowired
	private ResultService resultService;

	@Autowired
	private ModService modService;

	@RequestMapping("getAnotherYears2.shtml")
	public String getSiji(Model model, HttpSession session, String dx, String wd, String beginYear, String endYear,
			String modBeginYear, String modEndYear, String temBeginYear, String temEndYear) throws Exception {
		// 四季
		List<String> dateYears = anotherYearService.getYear();
		// 梅雨
		List<String> dateYearsMod = modService.getYearMod();
		// 高温
		List<String> dateYearsTem = anotherYearService.getTemYear();
		// 四季结束年份
		String maxYearAhy = anotherYearService.getMaxYear();
		// 梅雨结束年份
		String maxYearMod = modService.getMaxYearMod();
		// 温度结束年份
		String maxYearTem = anotherYearService.getTemMaxYear();
		
		List<Object[]> any = new ArrayList<Object[]>();
		List<Object> col1List = new ArrayList<Object>();
		List<Object> col2List = new ArrayList<Object>();
		List<ResultColList> rcs = new ArrayList<ResultColList>();
		List<Object[]> col1 = new ArrayList<Object[]>();
		List<Object[]> col2 = new ArrayList<Object[]>();
		List<Object[]> result1 = new ArrayList<Object[]>();
		List<Object[]> result2 = new ArrayList<Object[]>();
		List<CommonAnotherYear> common = new ArrayList<CommonAnotherYear>();
		List<TemTime> temTime = new ArrayList<TemTime>();
		List<TemDayCount> temDayCount = new ArrayList<TemDayCount>();
		List<Object[]> objlist = new ArrayList<Object[]>();
		List<Object[]> lisobj = null;
		TestResult tr = null;
		String page = "";
		if (beginYear == null) {
			beginYear = "1874";
		}
		if (endYear == null) {
			endYear = maxYearAhy;
		}
		if (modBeginYear == null) {
			modBeginYear = "1875";
		}
		if (modEndYear == null) {
			modEndYear = maxYearMod;
		}
		if (temBeginYear == null) {
			temBeginYear = "1951";
		}
		if (temEndYear == null) {
			temEndYear = maxYearTem;
		}
		if (wd == null) {
			wd = "35";
		}
		session.setAttribute("dx", dx);
		session.setAttribute("wd", wd);
		session.setAttribute("beginYear", beginYear);
		session.setAttribute("endYear", endYear);
		session.setAttribute("modBeginYear", modBeginYear);
		session.setAttribute("modEndYear", modEndYear);
		session.setAttribute("temBeginYear", temBeginYear);
		session.setAttribute("temEndYear", temEndYear);
		if (dx != null) {
			if (dx.equals("入春（最早）")) {
				col1List = anotherYearService.getSpringMin(beginYear, endYear);
				for (int i = 0; i < col1List.size(); i++) {
					Object[] obj = new Object[2];
					obj[0] = col1List.get(i).toString().substring("yyyy".length() + 1, col1List.get(i).toString().length());
					obj[1] = col1List.get(i).toString().substring(0, "yyyy".length());
					any.add(obj);
				}
				lisobj = new ArrayList<Object[]>();
				for (int i = 0; i < any.size(); i++) {
					Object[] obj = new Object[2];
					obj[0] = any.get(i)[0];
					obj[1] = any.get(i)[1];
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
				page = "anotherYearZz";
			}
			if (dx.equals("入春（最晚）")) {
				col2List = anotherYearService.getSpringMax(beginYear, endYear);
				for (int i = 0; i < col2List.size(); i++) {
					Object[] obj = new Object[2];
					obj[0] = col2List.get(i).toString().substring("yyyy".length() + 1, col2List.get(i).toString().length());
					obj[1] = col2List.get(i).toString().substring(0, "yyyy".length());
					any.add(obj);
				}
				lisobj = new ArrayList<Object[]>();
				for (int i = 0; i < any.size(); i++) {
					Object[] obj = new Object[2];
					obj[0] = any.get(i)[0];
					obj[1] = any.get(i)[1];
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
				page = "anotherYearZw";
			}
			if (dx.equals("春季长度（最长）")) {
				col1 = anotherYearService.getSpringMaxLength(beginYear, endYear);
				for (int i = 0; i < col1.size(); i++) {
					CommonAnotherYear cay = new CommonAnotherYear();
					cay.setLength(col1.get(i)[0].toString());
					cay.setYear(col1.get(i)[1].toString());
					common.add(cay);
				}
				lisobj = new ArrayList<Object[]>();
				for (CommonAnotherYear c : common) {
					Object[] obj = new Object[2];
					obj[0] = c.getLength();
					obj[1] = c.getYear();
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
				page = "anotherYear";
			}
			if (dx.equals("春季长度（最短）")) {
				col2 = anotherYearService.getSpringMinLength(beginYear, endYear);
				for (int i = 0; i < col2.size(); i++) {
					CommonAnotherYear cay = new CommonAnotherYear();
					cay.setLength(col2.get(i)[0].toString());
					cay.setYear(col2.get(i)[1].toString());
					common.add(cay);
				}
				lisobj = new ArrayList<Object[]>();
				for (CommonAnotherYear c : common) {
					Object[] obj = new Object[2];
					obj[0] = c.getLength();
					obj[1] = c.getYear();
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
				page = "anotherYear";
			}
			if (dx.equals("入夏（最早）")) {
				rcs = new ArrayList<ResultColList>();
				col1List = anotherYearService.getSummerMin(beginYear, endYear);
				for (int i = 0; i < col1List.size(); i++) {
					Object[] obj = new Object[2];
					obj[0] = col1List.get(i).toString().substring("yyyy".length() + 1, col1List.get(i).toString().length());
					obj[1] = col1List.get(i).toString().substring(0, "yyyy".length());
					any.add(obj);
				}
				lisobj = new ArrayList<Object[]>();
				for (int i = 0; i < any.size(); i++) {
					Object[] obj = new Object[2];
					obj[0] = any.get(i)[0];
					obj[1] = any.get(i)[1];
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
				page = "anotherYearZz";
			}
			if (dx.equals("入夏（最晚）")) {
				rcs = new ArrayList<ResultColList>();
				col2List = anotherYearService.getSummerMax(beginYear, endYear);
				for (int i = 0; i < col2List.size(); i++) {
					Object[] obj = new Object[2];
					obj[0] = col2List.get(i).toString().substring("yyyy".length() + 1, col2List.get(i).toString().length());
					obj[1] = col2List.get(i).toString().substring(0, "yyyy".length());
					any.add(obj);
				}
				lisobj = new ArrayList<Object[]>();
				for (int i = 0; i < any.size(); i++) {
					Object[] obj = new Object[2];
					obj[0] = any.get(i)[0];
					obj[1] = any.get(i)[1];
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
				page = "anotherYearZw";
			}
			if (dx.equals("夏季长度（最长）")) {
				col1 = anotherYearService.getSummerMaxLength(beginYear, endYear);
				for (int i = 0; i < col1.size(); i++) {
					CommonAnotherYear cay = new CommonAnotherYear();
					cay.setLength(col1.get(i)[0].toString());
					cay.setYear(col1.get(i)[1].toString());
					common.add(cay);
				}
				lisobj = new ArrayList<Object[]>();
				for (CommonAnotherYear c : common) {
					Object[] obj = new Object[2];
					obj[0] = c.getLength();
					obj[1] = c.getYear();
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
				page = "anotherYear";
			}
			if (dx.equals("夏季长度（最短）")) {
				col2 = anotherYearService.getSummerMinLength(beginYear, endYear);
				for (int i = 0; i < col2.size(); i++) {
					CommonAnotherYear cay = new CommonAnotherYear();
					cay.setLength(col2.get(i)[0].toString());
					cay.setYear(col2.get(i)[1].toString());
					common.add(cay);
				}
				lisobj = new ArrayList<Object[]>();
				for (CommonAnotherYear c : common) {
					Object[] obj = new Object[2];
					obj[0] = c.getLength();
					obj[1] = c.getYear();
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
				page = "anotherYear";
			}
			if (dx.equals("入秋（最早）")) {
				rcs = new ArrayList<ResultColList>();
				col1List = anotherYearService.getAutumnMin(beginYear, endYear);
				for (int i = 0; i < col1List.size(); i++) {
					Object[] obj = new Object[2];
					obj[0] = col1List.get(i).toString().substring("yyyy".length() + 1, col1List.get(i).toString().length());
					obj[1] = col1List.get(i).toString().substring(0, "yyyy".length());
					any.add(obj);
				}
				lisobj = new ArrayList<Object[]>();
				for (int i = 0; i < any.size(); i++) {
					Object[] obj = new Object[2];
					obj[0] = any.get(i)[0];
					obj[1] = any.get(i)[1];
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
				page = "anotherYearZz";
			}
			if (dx.equals("入秋（最晚）")) {
				rcs = new ArrayList<ResultColList>();
				col2List = anotherYearService.getAutumnMax(beginYear, endYear);
				for (int i = 0; i < col2List.size(); i++) {
					Object[] obj = new Object[2];
					obj[0] = col2List.get(i).toString().substring("yyyy".length() + 1, col2List.get(i).toString().length());
					obj[1] = col2List.get(i).toString().substring(0, "yyyy".length());
					any.add(obj);
				}
				lisobj = new ArrayList<Object[]>();
				for (int i = 0; i < any.size(); i++) {
					Object[] obj = new Object[2];
					obj[0] = any.get(i)[0];
					obj[1] = any.get(i)[1];
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
				page = "anotherYearZw";
			}
			if (dx.equals("秋季长度（最长）")) {
				col1 = anotherYearService.getAutumnMaxLength(beginYear, endYear);
				for (int i = 0; i < col1.size(); i++) {
					CommonAnotherYear cay = new CommonAnotherYear();
					cay.setLength(col1.get(i)[0].toString());
					cay.setYear(col1.get(i)[1].toString());
					common.add(cay);
				}
				lisobj = new ArrayList<Object[]>();
				for (CommonAnotherYear c : common) {
					Object[] obj = new Object[2];
					obj[0] = c.getLength();
					obj[1] = c.getYear();
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
				page = "anotherYear";
			}
			if (dx.equals("秋季长度（最短）")) {
				col2 = anotherYearService.getAutumnMinLength(beginYear, endYear);
				for (int i = 0; i < col2.size(); i++) {
					CommonAnotherYear cay = new CommonAnotherYear();
					cay.setLength(col2.get(i)[0].toString());
					cay.setYear(col2.get(i)[1].toString());
					common.add(cay);
				}
				lisobj = new ArrayList<Object[]>();
				for (CommonAnotherYear c : common) {
					Object[] obj = new Object[2];
					obj[0] = c.getLength();
					obj[1] = c.getYear();
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
				page = "anotherYear";
			}
			if (dx.equals("入冬（最早）")) {
				rcs = new ArrayList<ResultColList>();
				col1List = anotherYearService.getWinterMin(beginYear, endYear);
				for (int i = 0; i < col1List.size(); i++) {
					Object[] obj = new Object[2];
					obj[0] = col1List.get(i).toString().substring("yyyy".length() + 1, col1List.get(i).toString().length());
					obj[1] = col1List.get(i).toString().substring(0, "yyyy".length());
					any.add(obj);
				}
				lisobj = new ArrayList<Object[]>();
				for (int i = 0; i < any.size(); i++) {
					Object[] obj = new Object[2];
					obj[0] = any.get(i)[0];
					obj[1] = any.get(i)[1];
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
				page = "anotherYearZz";
			}
			if (dx.equals("入冬（最晚）")) {
				rcs = new ArrayList<ResultColList>();
				col2List = anotherYearService.getWinterMax(beginYear, endYear);
				for (int i = 0; i < col2List.size(); i++) {
					Object[] obj = new Object[2];
					obj[0] = col2List.get(i).toString().substring("yyyy".length() + 1, col2List.get(i).toString().length());
					obj[1] = col2List.get(i).toString().substring(0, "yyyy".length());
					any.add(obj);
				}
				lisobj = new ArrayList<Object[]>();
				for (int i = 0; i < any.size(); i++) {
					Object[] obj = new Object[2];
					obj[0] = any.get(i)[0];
					obj[1] = any.get(i)[1];
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
				page = "anotherYearZw";
			}
			if (dx.equals("冬季长度（最长）")) {
				col1 = anotherYearService.getWinterMaxLength(beginYear, endYear);
				for (int i = 0; i < col1.size(); i++) {
					CommonAnotherYear cay = new CommonAnotherYear();
					cay.setLength(col1.get(i)[0].toString());
					cay.setYear(col1.get(i)[1].toString());
					common.add(cay);
				}
				lisobj = new ArrayList<Object[]>();
				for (CommonAnotherYear c : common) {
					Object[] obj = new Object[2];
					obj[0] = c.getLength();
					obj[1] = c.getYear();
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
				page = "anotherYear";
			}
			if (dx.equals("冬季长度（最短）")) {
				col2 = anotherYearService.getWinterMinLength(beginYear, endYear);
				for (int i = 0; i < col2.size(); i++) {
					CommonAnotherYear cay = new CommonAnotherYear();
					cay.setLength(col2.get(i)[0].toString());
					cay.setYear(col2.get(i)[1].toString());
					common.add(cay);
				}
				lisobj = new ArrayList<Object[]>();
				for (CommonAnotherYear c : common) {
					Object[] obj = new Object[2];
					obj[0] = c.getLength();
					obj[1] = c.getYear();
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
				page = "anotherYear";
			}
			if (dx.equals("入梅（最早）")) {
				rcs = new ArrayList<ResultColList>();
				col1List = anotherYearService.getModMin(modBeginYear, modEndYear);
				for (int i = 0; i < col1List.size(); i++) {
					Object[] obj = new Object[2];
					obj[0] = col1List.get(i).toString().substring("yyyy".length() + 1, col1List.get(i).toString().length());
					obj[1] = col1List.get(i).toString().substring(0, "yyyy".length());
					any.add(obj);
				}
				lisobj = new ArrayList<Object[]>();
				for (int i = 0; i < any.size(); i++) {
					Object[] obj = new Object[2];
					obj[0] = any.get(i)[0];
					obj[1] = any.get(i)[1];
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
				page = "modTimeZz";
			}
			if (dx.equals("入梅（最晚）")) {
				rcs = new ArrayList<ResultColList>();
				col2List = anotherYearService.getModMax(modBeginYear, modEndYear);
				for (int i = 0; i < col2List.size(); i++) {
					Object[] obj = new Object[2];
					obj[0] = col2List.get(i).toString().substring("yyyy".length() + 1, col2List.get(i).toString().length());
					obj[1] = col2List.get(i).toString().substring(0, "yyyy".length());
					any.add(obj);
				}
				lisobj = new ArrayList<Object[]>();
				for (int i = 0; i < any.size(); i++) {
					Object[] obj = new Object[2];
					obj[0] = any.get(i)[0];
					obj[1] = any.get(i)[1];
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
				page = "modTimeZw";
			}
			if (dx.equals("出梅（最早）")) {
				rcs = new ArrayList<ResultColList>();
				col1List = anotherYearService.getMeiMin(modBeginYear, modEndYear);
				for (int i = 0; i < col1List.size(); i++) {
					Object[] obj = new Object[2];
					obj[0] = col1List.get(i).toString().substring("yyyy".length() + 1, col1List.get(i).toString().length());
					obj[1] = col1List.get(i).toString().substring(0, "yyyy".length());
					any.add(obj);
				}
				lisobj = new ArrayList<Object[]>();
				for (int i = 0; i < any.size(); i++) {
					Object[] obj = new Object[2];
					obj[0] = any.get(i)[0];
					obj[1] = any.get(i)[1];
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
				page = "modTimeZz";
			}
			if (dx.equals("出梅（最晚）")) {
				rcs = new ArrayList<ResultColList>();
				col2List = anotherYearService.getMeiMax(modBeginYear, modEndYear);
				for (int i = 0; i < col2List.size(); i++) {
					Object[] obj = new Object[2];
					obj[0] = col2List.get(i).toString().substring("yyyy".length() + 1, col2List.get(i).toString().length());
					obj[1] = col2List.get(i).toString().substring(0, "yyyy".length());
					any.add(obj);
				}
				lisobj = new ArrayList<Object[]>();
				for (int i = 0; i < any.size(); i++) {
					Object[] obj = new Object[2];
					obj[0] = any.get(i)[0];
					obj[1] = any.get(i)[1];
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
				page = "modTimeZw";
			}
			if (dx.equals("梅雨长度（最长）")) {
				col1 = anotherYearService.getMeiyuLengthMax(modBeginYear, modEndYear);
				for (int i = 0; i < col1.size(); i++) {
					CommonAnotherYear cay = new CommonAnotherYear();
					cay.setLength(col1.get(i)[0].toString());
					cay.setYear(col1.get(i)[1].toString());
					common.add(cay);
				}
				lisobj = new ArrayList<Object[]>();
				for (CommonAnotherYear c : common) {
					Object[] obj = new Object[2];
					obj[0] = c.getLength();
					obj[1] = c.getYear();
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
				page = "modLength";
			}
			if (dx.equals("梅雨长度（最短）")) {
				col2 = anotherYearService.getMeiyuLengthMin(modBeginYear, modEndYear);
				for (int i = 0; i < col2.size(); i++) {
					CommonAnotherYear cay = new CommonAnotherYear();
					cay.setLength(col2.get(i)[0].toString());
					cay.setYear(col2.get(i)[1].toString());
					common.add(cay);
				}
				lisobj = new ArrayList<Object[]>();
				for (CommonAnotherYear c : common) {
					Object[] obj = new Object[2];
					obj[0] = c.getLength();
					obj[1] = c.getYear();
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
				page = "modLength";
			}
			if (dx.equals("梅雨量（最多）")) {
				col1 = anotherYearService.getMeiyuAmountMax(modBeginYear, modEndYear);
				for (int i = 0; i < col1.size(); i++) {
					CommonAnotherYear cay = new CommonAnotherYear();
					cay.setLength(col1.get(i)[0].toString());
					cay.setYear(col1.get(i)[1].toString());
					common.add(cay);
				}
				lisobj = new ArrayList<Object[]>();
				for (CommonAnotherYear c : common) {
					Object[] obj = new Object[2];
					obj[0] = c.getLength();
					obj[1] = c.getYear();
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
				page = "meiyuAmount";
			}
			if (dx.equals("梅雨量（最少）")) {
				col2 = anotherYearService.getMeiyuAmountMin(modBeginYear, modEndYear);
				for (int i = 0; i < col2.size(); i++) {
					CommonAnotherYear cay = new CommonAnotherYear();
					cay.setLength(col2.get(i)[0].toString());
					cay.setYear(col2.get(i)[1].toString());
					common.add(cay);
				}
				lisobj = new ArrayList<Object[]>();
				for (CommonAnotherYear c : common) {
					Object[] obj = new Object[2];
					obj[0] = c.getLength();
					obj[1] = c.getYear();
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
				page = "meiyuAmount";
			}
			if (dx.equals("高温总日数（最多）")) {
				col1 = anotherYearService.getTemCountDayDesc(Integer.parseInt(wd), temBeginYear, temEndYear);
				result1 = anotherYearService.getTemCountDayMax(Integer.parseInt(wd), temBeginYear, temEndYear);
				lisobj = new ArrayList<Object[]>();
				for (int i = 0; i < col1.size(); i++) {
					Object[] obj = new Object[2];
					obj[0] = col1.get(i)[0];
					obj[1] = col1.get(i)[1];
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
				for (int i = 0; i < result1.size(); i++) {
					TemDayCount tdc = new TemDayCount();
					tdc.setYear(Integer.parseInt(result1.get(i)[0].toString()));
					tdc.setSumDay(Integer.parseInt(result1.get(i)[1].toString()));
					tdc.setMay(Integer.parseInt(result1.get(i)[2].toString()));
					tdc.setJune(Integer.parseInt(result1.get(i)[3].toString()));
					tdc.setJuly(Integer.parseInt(result1.get(i)[4].toString()));
					tdc.setAugust(Integer.parseInt(result1.get(i)[5].toString()));
					tdc.setSep(Integer.parseInt(result1.get(i)[6].toString()));
					temDayCount.add(tdc);
				}
				Integer a = null, b = 0;
				for (TemDayCount tdc : temDayCount) {
					if (tdc.getSumDay() == a) {
						tdc.setSortNum(b);
					} else {
						a = tdc.getSumDay();
						b += 1;
						tdc.setSortNum(b);
					}
				}
				page = "temDayCount";
			}
			if (dx.equals("高温总日数（最少）")) {
				col2 = anotherYearService.getTemCountDayAsc(Integer.parseInt(wd), temBeginYear, temEndYear);
				result2 = anotherYearService.getTemCountDayMin(Integer.parseInt(wd), temBeginYear, temEndYear);
				lisobj = new ArrayList<Object[]>();
				for (int i = 0; i < col2.size(); i++) {
					Object[] obj = new Object[2];
					obj[0] = col2.get(i)[0];
					obj[1] = col2.get(i)[1];
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
				page = "temDayCount";
				for (int i = 0; i < result2.size(); i++) {
					TemDayCount tdc = new TemDayCount();
					tdc.setYear(Integer.parseInt(result2.get(i)[0].toString()));
					tdc.setSumDay(Integer.parseInt(result2.get(i)[1].toString()));
					tdc.setMay(Integer.parseInt(result2.get(i)[2].toString()));
					tdc.setJune(Integer.parseInt(result2.get(i)[3].toString()));
					tdc.setJuly(Integer.parseInt(result2.get(i)[4].toString()));
					tdc.setAugust(Integer.parseInt(result2.get(i)[5].toString()));
					tdc.setSep(Integer.parseInt(result2.get(i)[6].toString()));
					temDayCount.add(tdc);
				}
				Integer a = null, b = 0;
				for (TemDayCount tdc : temDayCount) {
					if (tdc.getSumDay() == a) {
						tdc.setSortNum(b);
					} else {
						a = tdc.getSumDay();
						b += 1;
						tdc.setSortNum(b);
					}
				}
			}
			if (dx.equals("连续高温日数（最多）")) {
				col1 = resultService.getDayCountMax(Integer.parseInt(wd), Integer.parseInt(temBeginYear),
						Integer.parseInt(temEndYear));
				for (int i = 0; i < col1.size(); i++) {
					CommonAnotherYear cay = new CommonAnotherYear();
					cay.setLength(col1.get(i)[0].toString());
					cay.setYear(col1.get(i)[1].toString());
					common.add(cay);
				}
				lisobj = new ArrayList<Object[]>();
				for (CommonAnotherYear c : common) {
					Object[] obj = new Object[2];
					obj[0] = c.getLength();
					obj[1] = c.getYear();
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
				page = "temDay";
			}
			if (dx.equals("连续高温日数（最少）")) {
				col2 = resultService.getDayCountMin(Integer.parseInt(wd), Integer.parseInt(temBeginYear),
						Integer.parseInt(temEndYear));
				for (int i = 0; i < col2.size(); i++) {
					CommonAnotherYear cay = new CommonAnotherYear();
					cay.setLength(col2.get(i)[0].toString());
					cay.setYear(col2.get(i)[1].toString());
					common.add(cay);
				}
				lisobj = new ArrayList<Object[]>();
				for (CommonAnotherYear c : common) {
					Object[] obj = new Object[2];
					obj[0] = c.getLength();
					obj[1] = c.getYear();
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
				page = "temDay";
			}
			if (dx.equals("高温出现时间（最早）")) {
				SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
				col1 = anotherYearService.getTemTimeMin(Integer.parseInt(wd), temBeginYear, temEndYear);
				for (int i = 0; i < col1.size(); i++) {
					TemTime time = new TemTime();
					time.setTemTimeMin(sdf.format(col1.get(i)[0]));
					time.setTemYearMin(col1.get(i)[1].toString());
					temTime.add(time);
				}
				lisobj = new ArrayList<Object[]>();
				for (TemTime tt : temTime) {
					Object[] obj = new Object[2];
					obj[0] = tt.getTemTimeMin();
					obj[1] = tt.getTemYearMin();
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
				page = "temTimeZz";
			}
			if (dx.equals("高温出现时间（最晚）")) {
				SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
				col2 = anotherYearService.getTemTimeMax(Integer.parseInt(wd), temBeginYear, temEndYear);
				for (int i = 0; i < col2.size(); i++) {
					TemTime time = new TemTime();
					time.setTemTimeMax(sdf.format(col2.get(i)[0]));
					time.setTemYearMax(col2.get(i)[1].toString());
					temTime.add(time);
				}
				lisobj = new ArrayList<Object[]>();
				for (TemTime tt : temTime) {
					Object[] obj = new Object[2];
					obj[0] = tt.getTemTimeMax();
					obj[1] = tt.getTemYearMax();
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
				page = "temTimeZw";
			}

		}
		model.addAttribute("rcs", rcs);
		model.addAttribute("objlist", objlist);
		model.addAttribute("common", common);
		// model.addAttribute("temTime", temTime);
		model.addAttribute("temDayCount", temDayCount);

		// 下拉列表数据
		model.addAttribute("dateYears", dateYears);
		model.addAttribute("dateYearsMod", dateYearsMod);
		model.addAttribute("dateYearsTem", dateYearsTem);

		model.addAttribute("dx", dx);
		model.addAttribute("wd", wd);
		model.addAttribute("beginYear", beginYear);
		model.addAttribute("endYear", endYear);
		model.addAttribute("modBeginYear", modBeginYear);
		model.addAttribute("modEndYear", modEndYear);
		model.addAttribute("temBeginYear", temBeginYear);
		model.addAttribute("temEndYear", temEndYear);
		return page;
	}

	@RequestMapping("getAnotherHightChartLength.shtml")
	@ResponseBody
	public String getAnotherHightChartLength(HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<ResultColList> rcs = new ArrayList<ResultColList>();
		List<Object> col1List = new ArrayList<Object>();
		List<Object> col2List = new ArrayList<Object>();

		String dx = session.getAttribute("dx").toString();
		String beginYear = session.getAttribute("beginYear").toString();
		String endYear = session.getAttribute("endYear").toString();
		String modBeginYear = session.getAttribute("modBeginYear").toString();
		String modEndYear = session.getAttribute("modEndYear").toString();
		if (dx != null) {
			if (dx.equals("入春（最早）")) {
				col1List = anotherYearService.getSpringMin(beginYear, endYear);
				for (int i = 0; i < col1List.size(); i++) {
					ResultColList rc = new ResultColList();
					rc.setCol1List(col1List.get(i).toString());
					rcs.add(rc);
				}
			}
			if (dx.equals("入春（最晚）")) {
				col2List = anotherYearService.getSpringMax(beginYear, endYear);
				for (int i = 0; i < col2List.size(); i++) {
					ResultColList rc = new ResultColList();
					rc.setCol2List(col2List.get(i).toString());
					rcs.add(rc);
				}
			}
			if (dx.equals("入夏（最早）")) {
				rcs = new ArrayList<ResultColList>();
				col1List = anotherYearService.getSummerMin(beginYear, endYear);
				for (int i = 0; i < col1List.size(); i++) {
					ResultColList rc = new ResultColList();
					rc.setCol1List(col1List.get(i).toString());
					rcs.add(rc);
				}
			}
			if (dx.equals("入夏（最晚）")) {
				rcs = new ArrayList<ResultColList>();
				col2List = anotherYearService.getSummerMax(beginYear, endYear);
				for (int i = 0; i < col2List.size(); i++) {
					ResultColList rc = new ResultColList();
					rc.setCol2List(col2List.get(i).toString());
					rcs.add(rc);
				}
			}
			if (dx.equals("入秋（最早）")) {
				rcs = new ArrayList<ResultColList>();
				col1List = anotherYearService.getAutumnMin(beginYear, endYear);
				for (int i = 0; i < col1List.size(); i++) {
					ResultColList rc = new ResultColList();
					rc.setCol1List(col1List.get(i).toString());
					rcs.add(rc);
				}
			}
			if (dx.equals("入秋（最晚）")) {
				rcs = new ArrayList<ResultColList>();
				col2List = anotherYearService.getAutumnMax(beginYear, endYear);
				for (int i = 0; i < col2List.size(); i++) {
					ResultColList rc = new ResultColList();
					rc.setCol2List(col2List.get(i).toString());
					rcs.add(rc);
				}
			}
			if (dx.equals("入冬（最早）")) {
				rcs = new ArrayList<ResultColList>();
				col1List = anotherYearService.getWinterMin(beginYear, endYear);
				for (int i = 0; i < col1List.size(); i++) {
					ResultColList rc = new ResultColList();
					rc.setCol1List(col1List.get(i).toString());
					rcs.add(rc);
				}
			}
			if (dx.equals("入冬（最晚）")) {
				rcs = new ArrayList<ResultColList>();
				col2List = anotherYearService.getWinterMax(beginYear, endYear);
				for (int i = 0; i < col2List.size(); i++) {
					ResultColList rc = new ResultColList();
					rc.setCol2List(col2List.get(i).toString());
					rcs.add(rc);
				}
			}
			if (dx.equals("入梅（最早）")) {
				rcs = new ArrayList<ResultColList>();
				col1List = anotherYearService.getModMin(modBeginYear, modEndYear);
				for (int i = 0; i < col1List.size(); i++) {
					ResultColList rc = new ResultColList();
					rc.setCol1List(col1List.get(i).toString());
					rcs.add(rc);
				}
			}
			if (dx.equals("入梅（最晚）")) {
				rcs = new ArrayList<ResultColList>();
				col2List = anotherYearService.getModMax(modBeginYear, modEndYear);
				for (int i = 0; i < col2List.size(); i++) {
					ResultColList rc = new ResultColList();
					rc.setCol2List(col2List.get(i).toString());
					rcs.add(rc);
				}
			}
			if (dx.equals("出梅（最早）")) {
				rcs = new ArrayList<ResultColList>();
				col1List = anotherYearService.getMeiMin(modBeginYear, modEndYear);
				for (int i = 0; i < col1List.size(); i++) {
					ResultColList rc = new ResultColList();
					rc.setCol1List(col1List.get(i).toString());
					rcs.add(rc);
				}
			}
			if (dx.equals("出梅（最晚）")) {
				rcs = new ArrayList<ResultColList>();
				col2List = anotherYearService.getMeiMax(modBeginYear, modEndYear);
				for (int i = 0; i < col2List.size(); i++) {
					ResultColList rc = new ResultColList();
					rc.setCol2List(col2List.get(i).toString());
					rcs.add(rc);
				}
			}
		}
		if (!(rcs.size() < 10)) {
			rcs = rcs.subList(0, 10);
		}
		map.put("list", rcs);
		String s = new Gson().toJson(map);
		String name = s.replaceAll(".*\"list\":", "").replace("]}", "]");
		return name;
	}

	@RequestMapping("getAnotherHightChart.shtml")
	@ResponseBody
	public String getAnotherHightChart(HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();

		List<Object[]> col1 = new ArrayList<Object[]>();
		List<Object[]> col2 = new ArrayList<Object[]>();
		List<Object[]> result1 = new ArrayList<Object[]>();
		List<Object[]> result2 = new ArrayList<Object[]>();
		List<CommonAnotherYear> common = new ArrayList<CommonAnotherYear>();
		List<TemTime> temTime = new ArrayList<TemTime>();
		List<TemDayCount> temDayCount = new ArrayList<TemDayCount>();
		List<Object[]> objlist = new ArrayList<Object[]>();
		List<Object[]> lisobj = null;
		TestResult tr = null;

		String dx = session.getAttribute("dx").toString();
		String wd = session.getAttribute("wd").toString();
		String beginYear = session.getAttribute("beginYear").toString();
		String endYear = session.getAttribute("endYear").toString();
		String modBeginYear = session.getAttribute("modBeginYear").toString();
		String modEndYear = session.getAttribute("modEndYear").toString();
		String temBeginYear = session.getAttribute("temBeginYear").toString();
		String temEndYear = session.getAttribute("temEndYear").toString();
		if (dx != null) {
			if (dx.equals("春季长度（最长）")) {
				col1 = anotherYearService.getSpringMaxLength(beginYear, endYear);
				for (int i = 0; i < col1.size(); i++) {
					CommonAnotherYear cay = new CommonAnotherYear();
					cay.setLength(col1.get(i)[0].toString());
					cay.setYear(col1.get(i)[1].toString());
					common.add(cay);
				}
				lisobj = new ArrayList<Object[]>();
				for (CommonAnotherYear c : common) {
					Object[] obj = new Object[2];
					obj[0] = c.getLength();
					obj[1] = c.getYear();
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
			}
			if (dx.equals("春季长度（最短）")) {
				col2 = anotherYearService.getSpringMinLength(beginYear, endYear);
				for (int i = 0; i < col2.size(); i++) {
					CommonAnotherYear cay = new CommonAnotherYear();
					cay.setLength(col2.get(i)[0].toString());
					cay.setYear(col2.get(i)[1].toString());
					common.add(cay);
				}
				lisobj = new ArrayList<Object[]>();
				for (CommonAnotherYear c : common) {
					Object[] obj = new Object[2];
					obj[0] = c.getLength();
					obj[1] = c.getYear();
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
			}
			if (dx.equals("夏季长度（最长）")) {
				col1 = anotherYearService.getSummerMaxLength(beginYear, endYear);
				for (int i = 0; i < col1.size(); i++) {
					CommonAnotherYear cay = new CommonAnotherYear();
					cay.setLength(col1.get(i)[0].toString());
					cay.setYear(col1.get(i)[1].toString());
					common.add(cay);
				}
				lisobj = new ArrayList<Object[]>();
				for (CommonAnotherYear c : common) {
					Object[] obj = new Object[2];
					obj[0] = c.getLength();
					obj[1] = c.getYear();
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
			}
			if (dx.equals("夏季长度（最短）")) {
				col2 = anotherYearService.getSummerMinLength(beginYear, endYear);
				for (int i = 0; i < col2.size(); i++) {
					CommonAnotherYear cay = new CommonAnotherYear();
					cay.setLength(col2.get(i)[0].toString());
					cay.setYear(col2.get(i)[1].toString());
					common.add(cay);
				}
				lisobj = new ArrayList<Object[]>();
				for (CommonAnotherYear c : common) {
					Object[] obj = new Object[2];
					obj[0] = c.getLength();
					obj[1] = c.getYear();
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
			}
			if (dx.equals("秋季长度（最长）")) {
				col1 = anotherYearService.getAutumnMaxLength(beginYear, endYear);
				for (int i = 0; i < col1.size(); i++) {
					CommonAnotherYear cay = new CommonAnotherYear();
					cay.setLength(col1.get(i)[0].toString());
					cay.setYear(col1.get(i)[1].toString());
					common.add(cay);
				}
				lisobj = new ArrayList<Object[]>();
				for (CommonAnotherYear c : common) {
					Object[] obj = new Object[2];
					obj[0] = c.getLength();
					obj[1] = c.getYear();
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
			}
			if (dx.equals("秋季长度（最短）")) {
				col2 = anotherYearService.getAutumnMinLength(beginYear, endYear);
				for (int i = 0; i < col2.size(); i++) {
					CommonAnotherYear cay = new CommonAnotherYear();
					cay.setLength(col2.get(i)[0].toString());
					cay.setYear(col2.get(i)[1].toString());
					common.add(cay);
				}
				lisobj = new ArrayList<Object[]>();
				for (CommonAnotherYear c : common) {
					Object[] obj = new Object[2];
					obj[0] = c.getLength();
					obj[1] = c.getYear();
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
			}
			if (dx.equals("冬季长度（最长）")) {
				col1 = anotherYearService.getWinterMaxLength(beginYear, endYear);
				for (int i = 0; i < col1.size(); i++) {
					CommonAnotherYear cay = new CommonAnotherYear();
					cay.setLength(col1.get(i)[0].toString());
					cay.setYear(col1.get(i)[1].toString());
					common.add(cay);
				}
				lisobj = new ArrayList<Object[]>();
				for (CommonAnotherYear c : common) {
					Object[] obj = new Object[2];
					obj[0] = c.getLength();
					obj[1] = c.getYear();
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
			}
			if (dx.equals("冬季长度（最短）")) {
				col2 = anotherYearService.getWinterMinLength(beginYear, endYear);
				for (int i = 0; i < col2.size(); i++) {
					CommonAnotherYear cay = new CommonAnotherYear();
					cay.setLength(col2.get(i)[0].toString());
					cay.setYear(col2.get(i)[1].toString());
					common.add(cay);
				}
				lisobj = new ArrayList<Object[]>();
				for (CommonAnotherYear c : common) {
					Object[] obj = new Object[2];
					obj[0] = c.getLength();
					obj[1] = c.getYear();
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
			}
			if (dx.equals("梅雨长度（最长）")) {
				col1 = anotherYearService.getMeiyuLengthMax(modBeginYear, modEndYear);
				for (int i = 0; i < col1.size(); i++) {
					CommonAnotherYear cay = new CommonAnotherYear();
					cay.setLength(col1.get(i)[0].toString());
					cay.setYear(col1.get(i)[1].toString());
					common.add(cay);
				}
				lisobj = new ArrayList<Object[]>();
				for (CommonAnotherYear c : common) {
					Object[] obj = new Object[2];
					obj[0] = c.getLength();
					obj[1] = c.getYear();
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
			}
			if (dx.equals("梅雨长度（最短）")) {
				col2 = anotherYearService.getMeiyuLengthMin(modBeginYear, modEndYear);
				for (int i = 0; i < col2.size(); i++) {
					CommonAnotherYear cay = new CommonAnotherYear();
					cay.setLength(col2.get(i)[0].toString());
					cay.setYear(col2.get(i)[1].toString());
					common.add(cay);
				}
				lisobj = new ArrayList<Object[]>();
				for (CommonAnotherYear c : common) {
					Object[] obj = new Object[2];
					obj[0] = c.getLength();
					obj[1] = c.getYear();
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
			}
			if (dx.equals("梅雨量（最多）")) {
				col1 = anotherYearService.getMeiyuAmountMax(modBeginYear, modEndYear);
				for (int i = 0; i < col1.size(); i++) {
					CommonAnotherYear cay = new CommonAnotherYear();
					cay.setLength(col1.get(i)[0].toString());
					cay.setYear(col1.get(i)[1].toString());
					common.add(cay);
				}
				lisobj = new ArrayList<Object[]>();
				for (CommonAnotherYear c : common) {
					Object[] obj = new Object[2];
					obj[0] = c.getLength();
					obj[1] = c.getYear();
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
			}
			if (dx.equals("梅雨量（最少）")) {
				col2 = anotherYearService.getMeiyuAmountMin(modBeginYear, modEndYear);
				for (int i = 0; i < col2.size(); i++) {
					CommonAnotherYear cay = new CommonAnotherYear();
					cay.setLength(col2.get(i)[0].toString());
					cay.setYear(col2.get(i)[1].toString());
					common.add(cay);
				}
				lisobj = new ArrayList<Object[]>();
				for (CommonAnotherYear c : common) {
					Object[] obj = new Object[2];
					obj[0] = c.getLength();
					obj[1] = c.getYear();
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
			}
			if (dx.equals("高温总日数（最多）")) {
				col1 = anotherYearService.getTemCountDayDesc(Integer.parseInt(wd), temBeginYear, temEndYear);
				result1 = anotherYearService.getTemCountDayMax(Integer.parseInt(wd), temBeginYear, temEndYear);
				lisobj = new ArrayList<Object[]>();
				for (int i = 0; i < col1.size(); i++) {
					Object[] obj = new Object[2];
					obj[0] = col1.get(i)[0];
					obj[1] = col1.get(i)[1];
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
				for (int i = 0; i < result1.size(); i++) {
					TemDayCount tdc = new TemDayCount();
					tdc.setYear(Integer.parseInt(result1.get(i)[0].toString()));
					tdc.setSumDay(Integer.parseInt(result1.get(i)[1].toString()));
					tdc.setMay(Integer.parseInt(result1.get(i)[2].toString()));
					tdc.setJune(Integer.parseInt(result1.get(i)[3].toString()));
					tdc.setJuly(Integer.parseInt(result1.get(i)[4].toString()));
					tdc.setAugust(Integer.parseInt(result1.get(i)[5].toString()));
					tdc.setSep(Integer.parseInt(result1.get(i)[6].toString()));
					temDayCount.add(tdc);
				}
				Integer a = null, b = 0;
				for (TemDayCount tdc : temDayCount) {
					if (tdc.getSumDay() == a) {
						tdc.setSortNum(b);
					} else {
						a = tdc.getSumDay();
						b += 1;
						tdc.setSortNum(b);
					}
				}
			}
			if (dx.equals("高温总日数（最少）")) {
				col2 = anotherYearService.getTemCountDayAsc(Integer.parseInt(wd), temBeginYear, temEndYear);
				result2 = anotherYearService.getTemCountDayMin(Integer.parseInt(wd), temBeginYear, temEndYear);
				lisobj = new ArrayList<Object[]>();
				for (int i = 0; i < col2.size(); i++) {
					Object[] obj = new Object[2];
					obj[0] = col2.get(i)[0];
					obj[1] = col2.get(i)[1];
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
				for (int i = 0; i < result2.size(); i++) {
					TemDayCount tdc = new TemDayCount();
					tdc.setYear(Integer.parseInt(result2.get(i)[0].toString()));
					tdc.setSumDay(Integer.parseInt(result2.get(i)[1].toString()));
					tdc.setMay(Integer.parseInt(result2.get(i)[2].toString()));
					tdc.setJune(Integer.parseInt(result2.get(i)[3].toString()));
					tdc.setJuly(Integer.parseInt(result2.get(i)[4].toString()));
					tdc.setAugust(Integer.parseInt(result2.get(i)[5].toString()));
					tdc.setSep(Integer.parseInt(result2.get(i)[6].toString()));
					temDayCount.add(tdc);
				}
				Integer a = null, b = 0;
				for (TemDayCount tdc : temDayCount) {
					if (tdc.getSumDay() == a) {
						tdc.setSortNum(b);
					} else {
						a = tdc.getSumDay();
						b += 1;
						tdc.setSortNum(b);
					}
				}
			}
			if (dx.equals("连续高温日数（最多）")) {
				col1 = resultService.getDayCountMax(Integer.parseInt(wd), Integer.parseInt(temBeginYear),
						Integer.parseInt(temEndYear));
				for (int i = 0; i < col1.size(); i++) {
					CommonAnotherYear cay = new CommonAnotherYear();
					cay.setLength(col1.get(i)[0].toString());
					cay.setYear(col1.get(i)[1].toString());
					common.add(cay);
				}
				lisobj = new ArrayList<Object[]>();
				for (CommonAnotherYear c : common) {
					Object[] obj = new Object[2];
					obj[0] = c.getLength();
					obj[1] = c.getYear();
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
			}
			if (dx.equals("连续高温日数（最少）")) {
				col2 = resultService.getDayCountMin(Integer.parseInt(wd), Integer.parseInt(temBeginYear),
						Integer.parseInt(temEndYear));
				for (int i = 0; i < col2.size(); i++) {
					CommonAnotherYear cay = new CommonAnotherYear();
					cay.setLength(col2.get(i)[0].toString());
					cay.setYear(col2.get(i)[1].toString());
					common.add(cay);
				}
				lisobj = new ArrayList<Object[]>();
				for (CommonAnotherYear c : common) {
					Object[] obj = new Object[2];
					obj[0] = c.getLength();
					obj[1] = c.getYear();
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
			}

		}
		if (!(objlist.size() < 10)) {
			objlist = objlist.subList(0, 10);
		}
		map.put("list", objlist);
		String s = new Gson().toJson(map);
		String name = s.replaceAll(".*\"list\":", "").replace("]}", "]");
		return name;
	}

	@RequestMapping("saveOrUpdateMod.shtml")
	public String saveOrUpdateMod(Model model, HttpSession session) {
		List<Mod> mods = modService.getAllMod();
		Map<Integer, Mod> map = new HashMap<Integer, Mod>();
		Mod mod = null;
		for (Mod ano : mods) {
			map.put(ano.getYear(), ano);
		}
		int year = new Date().getYear() + 1900;
		for (int i = year; i >= 1875; i--) {
			try {
				if (map.containsKey(i) && (map.get(i).getMeiDay() == null || map.get(i).getModDay() == null)) {
					mod = modService.getModById(map.get(i).getId());
					session.setAttribute("modId", mod.getId());
					session.setAttribute("saveOrUpdateMod", "修改");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		model.addAttribute("modOrMei", mod);
		return "jimyadd";
	}

	@RequestMapping("saveAndUpdateMod.shtml")
	public String saveAndUpdateMod(HttpSession session, String modDay, String meiDay, String meiyuAmount)
			throws ParseException, UnsupportedEncodingException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Mod mod = new Mod();
		Date modDate = null;
		Date meiDate = null;
		Double myl = null;
		if (session.getAttribute("saveOrUpdateMod") != null && !session.getAttribute("saveOrUpdateMod").equals("")) {
			if (modDay != null && !modDay.equals("")) {
				modDate = sdf.parse(modDay);
			}
			if (meiDay != null && !meiDay.equals("")) {
				meiDate = sdf.parse(meiDay);
			}
			if (meiyuAmount != null && !meiyuAmount.equals("")) {
				myl = Double.parseDouble(meiyuAmount);
			}
			int year = modDate.getYear() + 1900;
			long diff = (meiDate.getTime() - modDate.getTime()) / (1000 * 60 * 60 * 24);
			int days = (int) diff;
			String Id = session.getAttribute("modId").toString();
			modService.updateMod(year, modDate, meiDate, days, myl, Integer.parseInt(Id));
			session.removeAttribute("saveOrUpdateMod");
		} else {
			if (modDay != null && !modDay.equals("")) {
				modDate = sdf.parse(modDay);
				mod.setModDay(modDate);
			}
			if (meiDay != null && !meiDay.equals("")) {
				meiDate = sdf.parse(meiDay);
				mod.setMeiDay(meiDate);
			}
			if (meiyuAmount != null && !meiyuAmount.equals("")) {
				mod.setMeiyuAmount(Double.parseDouble(meiyuAmount));
			}
			int year = modDate.getYear() + 1900;
			mod.setYear(year);
			long diff = (meiDate.getTime() - modDate.getTime()) / (1000 * 60 * 60 * 24);
			int days = (int) diff;
			mod.setMeiyuLength(days);
			modService.saveMod(mod);
		}
		String name = session.getAttribute("dx").toString();
		String dx = java.net.URLEncoder.encode(name, "UTF-8");
		return "redirect:/getAnotherYears2.shtml?dx=" + dx;
	}

	@RequestMapping("saveOrUpdateAnotherYear.shtml")
	public String saveOrUpdate(Model model, HttpSession session) {
		List<AnotherYear> anotherYears = anotherYearService.getAll();
		Map<Integer, AnotherYear> map = new HashMap<Integer, AnotherYear>();
		AnotherYear ahy = null;
		for (AnotherYear ano : anotherYears) {
			map.put(ano.getSpringDate().getYear() + 1900, ano);
		}
		int year = new Date().getYear() + 1900;
		for (int i = year; i >= 1874; i--) {
			try {
				if (map.containsKey(i) && (map.get(i).getSpringDate() == null || map.get(i).getSummerDate() == null
						|| map.get(i).getAutumnDate() == null || map.get(i).getWinterDate() == null)) {
					ahy = anotherYearService.getAnotherYearById(map.get(i).getId());
					session.setAttribute("anotherId", ahy.getId());
					session.setAttribute("saveOrUpdateAhy", "修改");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		model.addAttribute("ahy", ahy);
		return "jimpwadd";
	}

	@RequestMapping("saveAndUpdateAnotherYear.shtml")
	public String saveAndUpdate(HttpSession session, String springDate, String summerDate, String autumnDate,
			String winterDate) throws ParseException, UnsupportedEncodingException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		AnotherYear anotherYear = new AnotherYear();
		if (session.getAttribute("saveOrUpdateAhy") != null && !session.getAttribute("saveOrUpdateAhy").equals("")) {
			Date spring = null;
			Date summer = null;
			Date autumn = null;
			Date winter = null;
			if (springDate != null && !springDate.equals("")) {
				spring = sdf.parse(springDate);
			}
			if (summerDate != null && !summerDate.equals("")) {
				summer = sdf.parse(summerDate);
			}
			if (autumnDate != null && !autumnDate.equals("")) {
				autumn = sdf.parse(autumnDate);
			}
			if (winterDate != null && !winterDate.equals("")) {
				winter = sdf.parse(winterDate);
			}
			String Id = session.getAttribute("anotherId").toString();
			anotherYearService.updateAnotherYear(spring, summer, autumn, winter, Integer.parseInt(Id));
			session.removeAttribute("saveOrUpdateAhy");
		} else {
			if (springDate != null && !springDate.equals("")) {
				anotherYear.setSpringDate(sdf.parse(springDate));
			}
			if (summerDate != null && !summerDate.equals("")) {
				anotherYear.setSummerDate(sdf.parse(summerDate));
			}
			if (autumnDate != null && !autumnDate.equals("")) {
				anotherYear.setAutumnDate(sdf.parse(autumnDate));
			}
			if (winterDate != null && !winterDate.equals("")) {
				anotherYear.setWinterDate(sdf.parse(winterDate));
			}
			anotherYearService.saveAnotherYear(anotherYear);
		}
		String name = session.getAttribute("dx").toString();
		String dx = java.net.URLEncoder.encode(name, "UTF-8");
		return "redirect:/getAnotherYears2.shtml?dx=" + dx;
	}

	@RequestMapping("saveAnother.shtml")
	public String saveAnother(String springDate, String summerDate, String autumnDate, String winterDate)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		AnotherYear anotherYear = new AnotherYear();
		if (springDate != null && !springDate.equals("")) {
			anotherYear.setSpringDate(sdf.parse(springDate));
		}
		if (summerDate != null && !summerDate.equals("")) {
			anotherYear.setSummerDate(sdf.parse(summerDate));
		}
		if (autumnDate != null && !autumnDate.equals("")) {
			anotherYear.setAutumnDate(sdf.parse(autumnDate));
		}
		if (winterDate != null && !winterDate.equals("")) {
			anotherYear.setWinterDate(sdf.parse(winterDate));
		}
		anotherYearService.saveAnotherYear(anotherYear);
		return "redirect:/getAnotherYears2.shtml";
	}

	@RequestMapping("saveMod.shtml")
	public String saveMod(String modDay, String meiDay, String meiyuAmount) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Mod mod = new Mod();
		Date modDate = null;
		Date meiDate = null;
		if (modDay != null && !modDay.equals("")) {
			modDate = sdf.parse(modDay);
			mod.setModDay(modDate);
		}
		if (meiDay != null && !meiDay.equals("")) {
			meiDate = sdf.parse(meiDay);
			mod.setMeiDay(meiDate);
		}
		if (meiyuAmount != null && !meiyuAmount.equals("")) {
			mod.setMeiyuAmount(Double.parseDouble(meiyuAmount));
		}
		int year = modDate.getYear() + 1900;
		mod.setYear(year);
		long diff = (meiDate.getTime() - modDate.getTime()) / (1000 * 60 * 60 * 24);
		int days = (int) diff;
		mod.setMeiyuLength(days);
		modService.saveMod(mod);
		return "redirect:/getAnotherYears2.shtml";
	}

	@RequestMapping("getAnotherYearById.shtml")
	public String getAnotherYearById(Model model, String Id) {
		AnotherYear ano = anotherYearService.getAnotherYearById(Integer.parseInt(Id));
		model.addAttribute("ano", ano);
		return "jimpwaddEdit";
	}

	@RequestMapping("getModById.shtml")
	public String getModById(Model model, String Id) {
		Mod mod = modService.getModById(Integer.parseInt(Id));
		model.addAttribute("meiyu", mod);
		return "jimyaddEdit";
	}

	@RequestMapping("updateMod.shtml")
	public String updateMod(String modDay, String meiDay, String meiyuAmount, String Id) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Mod mod = new Mod();
		Date modDate = null;
		Date meiDate = null;
		Double myl = null;
		if (modDay != null && !modDay.equals("")) {
			modDate = sdf.parse(modDay);
		}
		if (meiDay != null && !meiDay.equals("")) {
			meiDate = sdf.parse(meiDay);
		}
		if (meiyuAmount != null && !meiyuAmount.equals("")) {
			myl = Double.parseDouble(meiyuAmount);
		}
		int year = modDate.getYear() + 1900;
		long diff = (meiDate.getTime() - modDate.getTime()) / (1000 * 60 * 60 * 24);
		int days = (int) diff;
		modService.updateMod(year, modDate, meiDate, days, myl, Integer.parseInt(Id));
		return "redirect:/listMod.shtml";
	}

	@RequestMapping("updateAnother.shtml")
	public String updateAnother(String springDate, String summerDate, String autumnDate, String winterDate, String Id)
			throws ParseException {
		Date spring = null;
		Date summer = null;
		Date autumn = null;
		Date winter = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (springDate != null && !springDate.equals("")) {
			spring = sdf.parse(springDate);
		}
		if (summerDate != null && !summerDate.equals("")) {
			summer = sdf.parse(summerDate);
		}
		if (autumnDate != null && !autumnDate.equals("")) {
			autumn = sdf.parse(autumnDate);
		}
		if (winterDate != null && !winterDate.equals("")) {
			winter = sdf.parse(winterDate);
		}
		anotherYearService.updateAnotherYear(spring, summer, autumn, winter, Integer.parseInt(Id));
		return "redirect:/listAnotherYear.shtml";
	}

	@RequestMapping("deleteAnother.shtml")
	public String deleteAnother(String Id) {
		anotherYearService.deleteInfo(Integer.parseInt(Id));
		return "redirect:/listAnotherYear.shtml";
	}

	@RequestMapping("deleteMod.shtml")
	public String deleteMods(String Id) {
		modService.deleteInfo(Integer.parseInt(Id));
		return "redirect:/listMod.shtml";
	}

	@RequestMapping("listAnotherYear.shtml")
	public ModelAndView listAnotherYear(@RequestParam(defaultValue = "0") int start,
			@RequestParam(defaultValue = "10") int len) throws IOException {
		QueryResult qr = anotherYearService
				.listAnotherYear(Pageutil.getPageable(start, len, new Sort(Sort.Direction.DESC, "springDate")));
		return getModelAndView("jimpwedit", qr, "listAnotherYear.shtml", start, len);
	}

	@RequestMapping("listMod.shtml")
	public ModelAndView listMod(@RequestParam(defaultValue = "0") int start, @RequestParam(defaultValue = "10") int len)
			throws IOException {
		QueryResult qr = modService.listMod(Pageutil.getPageable(start, len, new Sort(Sort.Direction.DESC, "year")));
		return getModelAndView("jimyedit", qr, "listMod.shtml", start, len);
	}

	private ModelAndView getModelAndView(String page, QueryResult qr, String url, int start, int len) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> mparm = new HashMap<String, Object>();
		qr.processPage(url, mparm, start, len);
		map.put("qr", qr);
		return new ModelAndView(page, map);
	}

	@RequestMapping(value = "exportDateMin.shtml")
	public void exportDateExcelMin(HttpServletRequest request, HttpServletResponse response, String dx,
			String beginYear, String endYear, String modBeginYear, String modEndYear) throws Exception {
		List<ResultColList> rcs = null;
		List<Object> col1List = null;
		List<Object[]> any = new ArrayList<Object[]>();
		List<Object[]> objlist = new ArrayList<Object[]>();
		List<Object[]> lisobj = null;
		TestResult tr = null;
		rcs = new ArrayList<ResultColList>();
		if (dx != null) {
			if (dx.equals("入春（最早）")) {
				col1List = anotherYearService.getSpringMin(beginYear, endYear);
				for (int i = 0; i < col1List.size(); i++) {
					Object[] obj = new Object[2];
					obj[0] = col1List.get(i).toString().substring("yyyy".length() + 1, col1List.get(i).toString().length());
					obj[1] = col1List.get(i).toString().substring(0, "yyyy".length());
					any.add(obj);
				}
				lisobj = new ArrayList<Object[]>();
				for (int i = 0; i < any.size(); i++) {
					Object[] obj = new Object[2];
					obj[0] = any.get(i)[0];
					obj[1] = any.get(i)[1];
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
			}
			if (dx.equals("入夏（最早）")) {
				rcs = new ArrayList<ResultColList>();
				col1List = anotherYearService.getSummerMin(beginYear, endYear);
				for (int i = 0; i < col1List.size(); i++) {
					Object[] obj = new Object[2];
					obj[0] = col1List.get(i).toString().substring("yyyy".length() + 1, col1List.get(i).toString().length());
					obj[1] = col1List.get(i).toString().substring(0, "yyyy".length());
					any.add(obj);
				}
				lisobj = new ArrayList<Object[]>();
				for (int i = 0; i < any.size(); i++) {
					Object[] obj = new Object[2];
					obj[0] = any.get(i)[0];
					obj[1] = any.get(i)[1];
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
			}
			if (dx.equals("入秋（最早）")) {
				rcs = new ArrayList<ResultColList>();
				col1List = anotherYearService.getAutumnMin(beginYear, endYear);
				for (int i = 0; i < col1List.size(); i++) {
					Object[] obj = new Object[2];
					obj[0] = col1List.get(i).toString().substring("yyyy".length() + 1, col1List.get(i).toString().length());
					obj[1] = col1List.get(i).toString().substring(0, "yyyy".length());
					any.add(obj);
				}
				lisobj = new ArrayList<Object[]>();
				for (int i = 0; i < any.size(); i++) {
					Object[] obj = new Object[2];
					obj[0] = any.get(i)[0];
					obj[1] = any.get(i)[1];
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
			}
			if (dx.equals("入冬（最早）")) {
				rcs = new ArrayList<ResultColList>();
				col1List = anotherYearService.getWinterMin(beginYear, endYear);
				for (int i = 0; i < col1List.size(); i++) {
					Object[] obj = new Object[2];
					obj[0] = col1List.get(i).toString().substring("yyyy".length() + 1, col1List.get(i).toString().length());
					obj[1] = col1List.get(i).toString().substring(0, "yyyy".length());
					any.add(obj);
				}
				lisobj = new ArrayList<Object[]>();
				for (int i = 0; i < any.size(); i++) {
					Object[] obj = new Object[2];
					obj[0] = any.get(i)[0];
					obj[1] = any.get(i)[1];
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
			}
			if (dx.equals("入梅（最早）")) {
				rcs = new ArrayList<ResultColList>();
				col1List = anotherYearService.getModMin(modBeginYear, modEndYear);
				for (int i = 0; i < col1List.size(); i++) {
					Object[] obj = new Object[2];
					obj[0] = col1List.get(i).toString().substring("yyyy".length() + 1, col1List.get(i).toString().length());
					obj[1] = col1List.get(i).toString().substring(0, "yyyy".length());
					any.add(obj);
				}
				lisobj = new ArrayList<Object[]>();
				for (int i = 0; i < any.size(); i++) {
					Object[] obj = new Object[2];
					obj[0] = any.get(i)[0];
					obj[1] = any.get(i)[1];
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
			}
			if (dx.equals("出梅（最早）")) {
				rcs = new ArrayList<ResultColList>();
				col1List = anotherYearService.getMeiMin(modBeginYear, modEndYear);
				for (int i = 0; i < col1List.size(); i++) {
					Object[] obj = new Object[2];
					obj[0] = col1List.get(i).toString().substring("yyyy".length() + 1, col1List.get(i).toString().length());
					obj[1] = col1List.get(i).toString().substring(0, "yyyy".length());
					any.add(obj);
				}
				lisobj = new ArrayList<Object[]>();
				for (int i = 0; i < any.size(); i++) {
					Object[] obj = new Object[2];
					obj[0] = any.get(i)[0];
					obj[1] = any.get(i)[1];
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
			}
		}
		String fileName = Tool.dateFormat(new Date(), "yyyyMMddHHmmss");
		HSSFWorkbook wb = anotherYearService.exportDateMin(objlist);
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xls");
		OutputStream ouputStream = response.getOutputStream();
		wb.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();
	}

	@RequestMapping(value = "exportDateMax.shtml")
	public void exportDateExcelMax(HttpServletRequest request, HttpServletResponse response, String dx,
			String beginYear, String endYear, String modBeginYear, String modEndYear) throws Exception {
		List<ResultColList> rcs = null;
		List<Object> col2List = null;
		List<Object[]> any = new ArrayList<Object[]>();
		List<Object[]> objlist = new ArrayList<Object[]>();
		List<Object[]> lisobj = null;
		TestResult tr = null;
		rcs = new ArrayList<ResultColList>();
		if (dx != null) {
			if (dx.equals("入春（最晚）")) {
				col2List = anotherYearService.getSpringMax(beginYear, endYear);
				for (int i = 0; i < col2List.size(); i++) {
					Object[] obj = new Object[2];
					obj[0] = col2List.get(i).toString().substring("yyyy".length() + 1, col2List.get(i).toString().length());
					obj[1] = col2List.get(i).toString().substring(0, "yyyy".length());
					any.add(obj);
				}
				lisobj = new ArrayList<Object[]>();
				for (int i = 0; i < any.size(); i++) {
					Object[] obj = new Object[2];
					obj[0] = any.get(i)[0];
					obj[1] = any.get(i)[1];
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
			}
			if (dx.equals("入夏（最晚）")) {
				rcs = new ArrayList<ResultColList>();
				col2List = anotherYearService.getSummerMax(beginYear, endYear);
				for (int i = 0; i < col2List.size(); i++) {
					Object[] obj = new Object[2];
					obj[0] = col2List.get(i).toString().substring("yyyy".length() + 1, col2List.get(i).toString().length());
					obj[1] = col2List.get(i).toString().substring(0, "yyyy".length());
					any.add(obj);
				}
				lisobj = new ArrayList<Object[]>();
				for (int i = 0; i < any.size(); i++) {
					Object[] obj = new Object[2];
					obj[0] = any.get(i)[0];
					obj[1] = any.get(i)[1];
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
			}
			if (dx.equals("入秋（最晚）")) {
				rcs = new ArrayList<ResultColList>();
				col2List = anotherYearService.getAutumnMax(beginYear, endYear);
				for (int i = 0; i < col2List.size(); i++) {
					Object[] obj = new Object[2];
					obj[0] = col2List.get(i).toString().substring("yyyy".length() + 1, col2List.get(i).toString().length());
					obj[1] = col2List.get(i).toString().substring(0, "yyyy".length());
					any.add(obj);
				}
				lisobj = new ArrayList<Object[]>();
				for (int i = 0; i < any.size(); i++) {
					Object[] obj = new Object[2];
					obj[0] = any.get(i)[0];
					obj[1] = any.get(i)[1];
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
			}
			if (dx.equals("入冬（最晚）")) {
				rcs = new ArrayList<ResultColList>();
				col2List = anotherYearService.getWinterMax(beginYear, endYear);
				for (int i = 0; i < col2List.size(); i++) {
					Object[] obj = new Object[2];
					obj[0] = col2List.get(i).toString().substring("yyyy".length() + 1, col2List.get(i).toString().length());
					obj[1] = col2List.get(i).toString().substring(0, "yyyy".length());
					any.add(obj);
				}
				lisobj = new ArrayList<Object[]>();
				for (int i = 0; i < any.size(); i++) {
					Object[] obj = new Object[2];
					obj[0] = any.get(i)[0];
					obj[1] = any.get(i)[1];
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
			}
			if (dx.equals("入梅（最晚）")) {
				rcs = new ArrayList<ResultColList>();
				col2List = anotherYearService.getModMax(modBeginYear, modEndYear);
				for (int i = 0; i < col2List.size(); i++) {
					Object[] obj = new Object[2];
					obj[0] = col2List.get(i).toString().substring("yyyy".length() + 1, col2List.get(i).toString().length());
					obj[1] = col2List.get(i).toString().substring(0, "yyyy".length());
					any.add(obj);
				}
				lisobj = new ArrayList<Object[]>();
				for (int i = 0; i < any.size(); i++) {
					Object[] obj = new Object[2];
					obj[0] = any.get(i)[0];
					obj[1] = any.get(i)[1];
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
			}
			if (dx.equals("出梅（最晚）")) {
				rcs = new ArrayList<ResultColList>();
				col2List = anotherYearService.getMeiMax(modBeginYear, modEndYear);
				for (int i = 0; i < col2List.size(); i++) {
					Object[] obj = new Object[2];
					obj[0] = col2List.get(i).toString().substring("yyyy".length() + 1, col2List.get(i).toString().length());
					obj[1] = col2List.get(i).toString().substring(0, "yyyy".length());
					any.add(obj);
				}
				lisobj = new ArrayList<Object[]>();
				for (int i = 0; i < any.size(); i++) {
					Object[] obj = new Object[2];
					obj[0] = any.get(i)[0];
					obj[1] = any.get(i)[1];
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
			}
		}
		String fileName = Tool.dateFormat(new Date(), "yyyyMMddHHmmss");
		HSSFWorkbook wb = anotherYearService.exportDateMax(objlist);
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xls");
		OutputStream ouputStream = response.getOutputStream();
		wb.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();
	}

	@RequestMapping(value = "exportData.shtml")
	public void exportDataExcel(HttpServletRequest request, HttpServletResponse response, String dx, String beginYear,
			String endYear, String modBeginYear, String modEndYear) throws Exception {
		List<Object[]> col1 = new ArrayList<Object[]>();
		List<Object[]> col2 = new ArrayList<Object[]>();
		List<CommonAnotherYear> common = new ArrayList<CommonAnotherYear>();
		List<Object[]> objlist = new ArrayList<Object[]>();
		List<Object[]> lisobj = null;
		TestResult tr = null;
		if (dx != null) {
			if (dx.equals("春季长度（最长）")) {
				col1 = anotherYearService.getSpringMaxLength(beginYear, endYear);
				for (int i = 0; i < col1.size(); i++) {
					CommonAnotherYear cay = new CommonAnotherYear();
					cay.setLength(col1.get(i)[0].toString());
					cay.setYear(col1.get(i)[1].toString());
					common.add(cay);
				}
				lisobj = new ArrayList<Object[]>();
				for (CommonAnotherYear c : common) {
					Object[] obj = new Object[2];
					obj[0] = c.getLength();
					obj[1] = c.getYear();
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
			}
			if (dx.equals("春季长度（最短）")) {
				col2 = anotherYearService.getSpringMinLength(beginYear, endYear);
				for (int i = 0; i < col2.size(); i++) {
					CommonAnotherYear cay = new CommonAnotherYear();
					cay.setLength(col2.get(i)[0].toString());
					cay.setYear(col2.get(i)[1].toString());
					common.add(cay);
				}
				lisobj = new ArrayList<Object[]>();
				for (CommonAnotherYear c : common) {
					Object[] obj = new Object[2];
					obj[0] = c.getLength();
					obj[1] = c.getYear();
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
			}
			if (dx.equals("夏季长度（最长）")) {
				col1 = anotherYearService.getSummerMaxLength(beginYear, endYear);
				for (int i = 0; i < col1.size(); i++) {
					CommonAnotherYear cay = new CommonAnotherYear();
					cay.setLength(col1.get(i)[0].toString());
					cay.setYear(col1.get(i)[1].toString());
					common.add(cay);
				}
				lisobj = new ArrayList<Object[]>();
				for (CommonAnotherYear c : common) {
					Object[] obj = new Object[2];
					obj[0] = c.getLength();
					obj[1] = c.getYear();
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
			}
			if (dx.equals("夏季长度（最短）")) {
				col2 = anotherYearService.getSummerMinLength(beginYear, endYear);
				for (int i = 0; i < col2.size(); i++) {
					CommonAnotherYear cay = new CommonAnotherYear();
					cay.setLength(col2.get(i)[0].toString());
					cay.setYear(col2.get(i)[1].toString());
					common.add(cay);
				}
				lisobj = new ArrayList<Object[]>();
				for (CommonAnotherYear c : common) {
					Object[] obj = new Object[2];
					obj[0] = c.getLength();
					obj[1] = c.getYear();
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
			}
			if (dx.equals("秋季长度（最长）")) {
				col1 = anotherYearService.getAutumnMaxLength(beginYear, endYear);
				for (int i = 0; i < col1.size(); i++) {
					CommonAnotherYear cay = new CommonAnotherYear();
					cay.setLength(col1.get(i)[0].toString());
					cay.setYear(col1.get(i)[1].toString());
					common.add(cay);
				}
				lisobj = new ArrayList<Object[]>();
				for (CommonAnotherYear c : common) {
					Object[] obj = new Object[2];
					obj[0] = c.getLength();
					obj[1] = c.getYear();
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
			}
			if (dx.equals("秋季长度（最短）")) {
				col2 = anotherYearService.getAutumnMinLength(beginYear, endYear);
				for (int i = 0; i < col2.size(); i++) {
					CommonAnotherYear cay = new CommonAnotherYear();
					cay.setLength(col2.get(i)[0].toString());
					cay.setYear(col2.get(i)[1].toString());
					common.add(cay);
				}
				lisobj = new ArrayList<Object[]>();
				for (CommonAnotherYear c : common) {
					Object[] obj = new Object[2];
					obj[0] = c.getLength();
					obj[1] = c.getYear();
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
			}
			if (dx.equals("冬季长度（最长）")) {
				col1 = anotherYearService.getWinterMaxLength(beginYear, endYear);
				for (int i = 0; i < col1.size(); i++) {
					CommonAnotherYear cay = new CommonAnotherYear();
					cay.setLength(col1.get(i)[0].toString());
					cay.setYear(col1.get(i)[1].toString());
					common.add(cay);
				}
				lisobj = new ArrayList<Object[]>();
				for (CommonAnotherYear c : common) {
					Object[] obj = new Object[2];
					obj[0] = c.getLength();
					obj[1] = c.getYear();
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
			}
			if (dx.equals("冬季长度（最短）")) {
				col2 = anotherYearService.getWinterMinLength(beginYear, endYear);
				for (int i = 0; i < col2.size(); i++) {
					CommonAnotherYear cay = new CommonAnotherYear();
					cay.setLength(col2.get(i)[0].toString());
					cay.setYear(col2.get(i)[1].toString());
					common.add(cay);
				}
				lisobj = new ArrayList<Object[]>();
				for (CommonAnotherYear c : common) {
					Object[] obj = new Object[2];
					obj[0] = c.getLength();
					obj[1] = c.getYear();
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
			}
			if (dx.equals("梅雨长度（最长）")) {
				col1 = anotherYearService.getMeiyuLengthMax(modBeginYear, modEndYear);
				for (int i = 0; i < col1.size(); i++) {
					CommonAnotherYear cay = new CommonAnotherYear();
					cay.setLength(col1.get(i)[0].toString());
					cay.setYear(col1.get(i)[1].toString());
					common.add(cay);
				}
				lisobj = new ArrayList<Object[]>();
				for (CommonAnotherYear c : common) {
					Object[] obj = new Object[2];
					obj[0] = c.getLength();
					obj[1] = c.getYear();
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
			}
			if (dx.equals("梅雨长度（最短）")) {
				col2 = anotherYearService.getMeiyuLengthMin(modBeginYear, modEndYear);
				for (int i = 0; i < col2.size(); i++) {
					CommonAnotherYear cay = new CommonAnotherYear();
					cay.setLength(col2.get(i)[0].toString());
					cay.setYear(col2.get(i)[1].toString());
					common.add(cay);
				}
				lisobj = new ArrayList<Object[]>();
				for (CommonAnotherYear c : common) {
					Object[] obj = new Object[2];
					obj[0] = c.getLength();
					obj[1] = c.getYear();
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
			}
		}
		String fileName = Tool.dateFormat(new Date(), "yyyyMMddHHmmss");
		HSSFWorkbook wb = anotherYearService.exportData(objlist);
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xls");
		OutputStream ouputStream = response.getOutputStream();
		wb.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();
	}

	@RequestMapping(value = "exportModAmount.shtml")
	public void exportModAmountExcel(HttpServletRequest request, HttpServletResponse response, String dx,
			String modBeginYear, String modEndYear) throws Exception {
		List<Object[]> col1 = new ArrayList<Object[]>();
		List<Object[]> col2 = new ArrayList<Object[]>();
		List<CommonAnotherYear> common = new ArrayList<CommonAnotherYear>();
		List<Object[]> objlist = new ArrayList<Object[]>();
		List<Object[]> lisobj = null;
		TestResult tr = null;
		if (dx != null) {
			if (dx.equals("梅雨量（最多）")) {
				col1 = anotherYearService.getMeiyuAmountMax(modBeginYear, modEndYear);
				for (int i = 0; i < col1.size(); i++) {
					CommonAnotherYear cay = new CommonAnotherYear();
					cay.setLength(col1.get(i)[0].toString());
					cay.setYear(col1.get(i)[1].toString());
					common.add(cay);
				}
				lisobj = new ArrayList<Object[]>();
				for (CommonAnotherYear c : common) {
					Object[] obj = new Object[2];
					obj[0] = c.getLength();
					obj[1] = c.getYear();
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
			}
			if (dx.equals("梅雨量（最少）")) {
				col2 = anotherYearService.getMeiyuAmountMin(modBeginYear, modEndYear);
				for (int i = 0; i < col2.size(); i++) {
					CommonAnotherYear cay = new CommonAnotherYear();
					cay.setLength(col2.get(i)[0].toString());
					cay.setYear(col2.get(i)[1].toString());
					common.add(cay);
				}
				lisobj = new ArrayList<Object[]>();
				for (CommonAnotherYear c : common) {
					Object[] obj = new Object[2];
					obj[0] = c.getLength();
					obj[1] = c.getYear();
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
			}
		}
		String fileName = Tool.dateFormat(new Date(), "yyyyMMddHHmmss");
		HSSFWorkbook wb = anotherYearService.exportModAmount(objlist);
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xls");
		OutputStream ouputStream = response.getOutputStream();
		wb.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();
	}

	@RequestMapping(value = "exportTemSumDay1.shtml")
	public void exportTemSumDayExcel1(HttpServletRequest request, HttpServletResponse response, String dx, String wd,
			String temBeginYear, String temEndYear) throws Exception {
		List<Object[]> col1 = new ArrayList<Object[]>();
		List<Object[]> col2 = new ArrayList<Object[]>();
		List<Object[]> objlist = new ArrayList<Object[]>();
		List<Object[]> lisobj = null;
		TestResult tr = null;
		if (dx != null) {
			if (dx.equals("高温总日数（最多）")) {
				col1 = anotherYearService.getTemCountDayDesc(Integer.parseInt(wd), temBeginYear, temEndYear);
				lisobj = new ArrayList<Object[]>();
				for (int i = 0; i < col1.size(); i++) {
					Object[] obj = new Object[2];
					obj[0] = col1.get(i)[0];
					obj[1] = col1.get(i)[1];
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
			}
			if (dx.equals("高温总日数（最少）")) {
				col2 = anotherYearService.getTemCountDayAsc(Integer.parseInt(wd), temBeginYear, temEndYear);
				lisobj = new ArrayList<Object[]>();
				for (int i = 0; i < col2.size(); i++) {
					Object[] obj = new Object[2];
					obj[0] = col2.get(i)[0];
					obj[1] = col2.get(i)[1];
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
			}
		}
		String fileName = Tool.dateFormat(new Date(), "yyyyMMddHHmmss");
		HSSFWorkbook wb = anotherYearService.exportTemSumDay1(objlist);
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xls");
		OutputStream ouputStream = response.getOutputStream();
		wb.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();
	}

	@RequestMapping(value = "exportTemSumDay2.shtml")
	public void exportTemSumDayExcel2(HttpServletRequest request, HttpServletResponse response, String dx, String wd,
			String temBeginYear, String temEndYear) throws Exception {
		List<Object[]> col1 = new ArrayList<Object[]>();
		List<Object[]> col2 = new ArrayList<Object[]>();
		List<TemDayCount> temDayCount = new ArrayList<TemDayCount>();
		if (dx != null) {
			if (dx.equals("高温总日数（最多）")) {
				col1 = anotherYearService.getTemCountDayMax(Integer.parseInt(wd), temBeginYear, temEndYear);
				for (int i = 0; i < col1.size(); i++) {
					TemDayCount tdc = new TemDayCount();
					tdc.setYear(Integer.parseInt(col1.get(i)[0].toString()));
					tdc.setSumDay(Integer.parseInt(col1.get(i)[1].toString()));
					tdc.setMay(Integer.parseInt(col1.get(i)[2].toString()));
					tdc.setJune(Integer.parseInt(col1.get(i)[3].toString()));
					tdc.setJuly(Integer.parseInt(col1.get(i)[4].toString()));
					tdc.setAugust(Integer.parseInt(col1.get(i)[5].toString()));
					tdc.setSep(Integer.parseInt(col1.get(i)[6].toString()));
					temDayCount.add(tdc);
				}
				Integer a = null, b = 0;
				for (TemDayCount tdc : temDayCount) {
					if (tdc.getSumDay() == a) {
						tdc.setSortNum(b);
					} else {
						a = tdc.getSumDay();
						b += 1;
						tdc.setSortNum(b);
					}
				}
			}
			if (dx.equals("高温总日数（最少）")) {
				col2 = anotherYearService.getTemCountDayMin(Integer.parseInt(wd), temBeginYear, temEndYear);
				for (int i = 0; i < col2.size(); i++) {
					TemDayCount tdc = new TemDayCount();
					tdc.setYear(Integer.parseInt(col2.get(i)[0].toString()));
					tdc.setSumDay(Integer.parseInt(col2.get(i)[1].toString()));
					tdc.setMay(Integer.parseInt(col2.get(i)[2].toString()));
					tdc.setJune(Integer.parseInt(col2.get(i)[3].toString()));
					tdc.setJuly(Integer.parseInt(col2.get(i)[4].toString()));
					tdc.setAugust(Integer.parseInt(col2.get(i)[5].toString()));
					tdc.setSep(Integer.parseInt(col2.get(i)[6].toString()));
					temDayCount.add(tdc);
				}
				Integer a = null, b = 0;
				for (TemDayCount tdc : temDayCount) {
					if (tdc.getSumDay() == a) {
						tdc.setSortNum(b);
					} else {
						a = tdc.getSumDay();
						b += 1;
						tdc.setSortNum(b);
					}
				}
			}
		}
		String fileName = Tool.dateFormat(new Date(), "yyyyMMddHHmmss");
		HSSFWorkbook wb = anotherYearService.exportTemSumDay2(temDayCount);
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xls");
		OutputStream ouputStream = response.getOutputStream();
		wb.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();
	}

	@RequestMapping(value = "exportTemTimeMin.shtml")
	public void exportTemTimeMinExcel(HttpServletRequest request, HttpServletResponse response, String dx, String wd,
			String temBeginYear, String temEndYear) throws Exception {
		List<Object[]> col1 = new ArrayList<Object[]>();
		List<Object[]> objlist = new ArrayList<Object[]>();
		List<Object[]> lisobj = null;
		TestResult tr = null;
		List<TemTime> temTime = new ArrayList<TemTime>();
		if (dx != null) {
			if (dx.equals("高温出现时间（最早）")) {
				SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
				col1 = anotherYearService.getTemTimeMin(Integer.parseInt(wd), temBeginYear, temEndYear);
				for (int i = 0; i < col1.size(); i++) {
					TemTime time = new TemTime();
					time.setTemTimeMin(sdf.format(col1.get(i)[0]));
					time.setTemYearMin(col1.get(i)[1].toString());
					temTime.add(time);
				}
				lisobj = new ArrayList<Object[]>();
				for (TemTime tt : temTime) {
					Object[] obj = new Object[2];
					obj[0] = tt.getTemTimeMin();
					obj[1] = tt.getTemYearMin();
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);

			}
		}
		String fileName = Tool.dateFormat(new Date(), "yyyyMMddHHmmss");
		HSSFWorkbook wb = anotherYearService.exportTemTimeMin(objlist);
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xls");
		OutputStream ouputStream = response.getOutputStream();
		wb.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();
	}

	@RequestMapping(value = "exportTemTimeMax.shtml")
	public void exportTemTimeMaxExcel(HttpServletRequest request, HttpServletResponse response, String dx, String wd,
			String temBeginYear, String temEndYear) throws Exception {
		List<Object[]> col2 = new ArrayList<Object[]>();
		List<Object[]> objlist = new ArrayList<Object[]>();
		List<Object[]> lisobj = null;
		TestResult tr = null;
		List<TemTime> temTime = new ArrayList<TemTime>();
		if (dx != null) {
			if (dx.equals("高温出现时间（最晚）")) {
				SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
				col2 = anotherYearService.getTemTimeMax(Integer.parseInt(wd), temBeginYear, temEndYear);
				for (int i = 0; i < col2.size(); i++) {
					TemTime time = new TemTime();
					time.setTemTimeMax(sdf.format(col2.get(i)[0]));
					time.setTemYearMax(col2.get(i)[1].toString());
					temTime.add(time);
				}
				lisobj = new ArrayList<Object[]>();
				for (TemTime tt : temTime) {
					Object[] obj = new Object[2];
					obj[0] = tt.getTemTimeMax();
					obj[1] = tt.getTemYearMax();
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
			}
		}
		String fileName = Tool.dateFormat(new Date(), "yyyyMMddHHmmss");
		HSSFWorkbook wb = anotherYearService.exportTemTimeMax(objlist);
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xls");
		OutputStream ouputStream = response.getOutputStream();
		wb.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();
	}

	@RequestMapping(value = "exportDayCount.shtml")
	public void exportDayCountExcel(HttpServletRequest request, HttpServletResponse response, String dx, String wd,
			String temBeginYear, String temEndYear) throws Exception {
		List<Object[]> col1 = new ArrayList<Object[]>();
		List<Object[]> col2 = new ArrayList<Object[]>();
		List<Object[]> objlist = new ArrayList<Object[]>();
		List<Object[]> lisobj = null;
		TestResult tr = null;
		List<CommonAnotherYear> common = new ArrayList<CommonAnotherYear>();
		if (dx != null) {
			if (dx.equals("连续高温日数（最多）")) {
				col1 = resultService.getDayCountMax(Integer.parseInt(wd), Integer.parseInt(temBeginYear),
						Integer.parseInt(temEndYear));
				for (int i = 0; i < col1.size(); i++) {
					CommonAnotherYear cay = new CommonAnotherYear();
					cay.setLength(col1.get(i)[0].toString());
					cay.setYear(col1.get(i)[1].toString());
					common.add(cay);
				}
				lisobj = new ArrayList<Object[]>();
				for (CommonAnotherYear c : common) {
					Object[] obj = new Object[2];
					obj[0] = c.getLength();
					obj[1] = c.getYear();
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
			}
			if (dx.equals("连续高温日数（最少）")) {
				col2 = resultService.getDayCountMin(Integer.parseInt(wd), Integer.parseInt(temBeginYear),
						Integer.parseInt(temEndYear));
				for (int i = 0; i < col2.size(); i++) {
					CommonAnotherYear cay = new CommonAnotherYear();
					cay.setLength(col2.get(i)[0].toString());
					cay.setYear(col2.get(i)[1].toString());
					common.add(cay);
				}
				lisobj = new ArrayList<Object[]>();
				for (CommonAnotherYear c : common) {
					Object[] obj = new Object[2];
					obj[0] = c.getLength();
					obj[1] = c.getYear();
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
			}
		}
		String fileName = Tool.dateFormat(new Date(), "yyyyMMddHHmmss");
		HSSFWorkbook wb = anotherYearService.exportDayCount(objlist);
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xls");
		OutputStream ouputStream = response.getOutputStream();
		wb.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();
	}

	@RequestMapping("getHighchart.shtml")
	@ResponseBody
	public String highcharts(HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Object[]> anotherWeather = new ArrayList<Object[]>();
		List<CommonAnotherYear> common = new ArrayList<CommonAnotherYear>();
		String dx = session.getAttribute("dx").toString();
		String wd = session.getAttribute("wd").toString();
		String beginYear = session.getAttribute("beginYear").toString();
		String endYear = session.getAttribute("endYear").toString();
		String modBeginYear = session.getAttribute("modBeginYear").toString();
		String modEndYear = session.getAttribute("modEndYear").toString();
		String temBeginYear = session.getAttribute("temBeginYear").toString();
		String temEndYear = session.getAttribute("temEndYear").toString();
		if (dx.equals("春季长度（最长）") || dx.equals("春季长度（最短）")) {
			anotherWeather = anotherYearService.getDataHignChartSpring(beginYear, endYear);
			for (int i = 0; i < anotherWeather.size(); i++) {
				CommonAnotherYear cay = new CommonAnotherYear();
				cay.setLength(anotherWeather.get(i)[0].toString());
				cay.setYear(anotherWeather.get(i)[1].toString());
				common.add(cay);
			}
		}
		if (dx.equals("夏季长度（最长）") || dx.equals("夏季长度（最短）")) {
			anotherWeather = anotherYearService.getDataHignChartSummer(beginYear, endYear);
			for (int i = 0; i < anotherWeather.size(); i++) {
				CommonAnotherYear cay = new CommonAnotherYear();
				cay.setLength(anotherWeather.get(i)[0].toString());
				cay.setYear(anotherWeather.get(i)[1].toString());
				common.add(cay);
			}
		}
		if (dx.equals("秋季长度（最长）") || dx.equals("秋季长度（最短）")) {
			anotherWeather = anotherYearService.getDataHignChartAutumn(beginYear, endYear);
			for (int i = 0; i < anotherWeather.size(); i++) {
				CommonAnotherYear cay = new CommonAnotherYear();
				cay.setLength(anotherWeather.get(i)[0].toString());
				cay.setYear(anotherWeather.get(i)[1].toString());
				common.add(cay);
			}
		}
		if (dx.equals("冬季长度（最长）") || dx.equals("冬季长度（最短）")) {
			anotherWeather = anotherYearService.getDataHignChartWinter(beginYear, endYear);
			for (int i = 0; i < anotherWeather.size(); i++) {
				CommonAnotherYear cay = new CommonAnotherYear();
				cay.setLength(anotherWeather.get(i)[0].toString());
				cay.setYear(anotherWeather.get(i)[1].toString());
				common.add(cay);
			}
		}
		if (dx.equals("梅雨长度（最长）") || dx.equals("梅雨长度（最短）")) {
			anotherWeather = anotherYearService.getModLength(modBeginYear, modEndYear);
			for (int i = 0; i < anotherWeather.size(); i++) {
				CommonAnotherYear cay = new CommonAnotherYear();
				cay.setLength(anotherWeather.get(i)[0].toString());
				cay.setYear(anotherWeather.get(i)[1].toString());
				common.add(cay);
			}
		}
		if (dx.equals("梅雨量（最多）") || dx.equals("梅雨量（最少）")) {
			anotherWeather = anotherYearService.getModAmount(modBeginYear, modEndYear);
			for (int i = 0; i < anotherWeather.size(); i++) {
				CommonAnotherYear cay = new CommonAnotherYear();
				cay.setLength(anotherWeather.get(i)[0].toString());
				cay.setYear(anotherWeather.get(i)[1].toString());
				common.add(cay);
			}
		}
		if (dx.equals("高温总日数（最多）") || dx.equals("高温总日数（最少）")) {
			anotherWeather = anotherYearService.getTemDayCount(Integer.parseInt(wd), temBeginYear, temEndYear);
			for (int i = 0; i < anotherWeather.size(); i++) {
				CommonAnotherYear cay = new CommonAnotherYear();
				cay.setLength(anotherWeather.get(i)[1].toString());
				cay.setYear(anotherWeather.get(i)[0].toString());
				common.add(cay);
			}
		}
		if (dx.equals("连续高温日数（最多）") || dx.equals("连续高温日数（最少）")) {
			anotherWeather = resultService.getDayCounts(Integer.parseInt(wd), Integer.parseInt(temBeginYear),
					Integer.parseInt(temEndYear));
			for (int i = 0; i < anotherWeather.size(); i++) {
				CommonAnotherYear cay = new CommonAnotherYear();
				cay.setLength(anotherWeather.get(i)[0].toString());
				cay.setYear(anotherWeather.get(i)[1].toString());
				common.add(cay);
			}
		}
		map.put("list", common);
		String s = new Gson().toJson(map);
		String name = s.replaceAll(".*\"list\":", "").replace("]}", "]");
		return name;
	}

}
