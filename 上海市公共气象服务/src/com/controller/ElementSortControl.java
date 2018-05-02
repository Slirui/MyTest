/**
 * 
 */
package com.controller;

import java.io.OutputStream;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.ElementsTem;
import com.entity.Precipitation;
import com.entity.Tem;
import com.google.gson.Gson;
import com.service.ElementSortService;
import com.service.PrecipitationService;
import com.service.RainTypeYearService;
import com.util.TestResult;
import com.util.Tool;

/**
 * 
 * @author 分裂状态。
 *
 */
@Controller
public class ElementSortControl {

	@Autowired
	private ElementSortService elementSortService;

	@Autowired
	private RainTypeYearService rainTypeYearService;

	@Autowired
	private PrecipitationService precipitationService;

	@RequestMapping("getElementConditions.shtml")
	public String getElementConditions(HttpSession session, HttpServletRequest request, Model model, String stationName,
			String stationName2, String year, String beginDate, String endDate, String beginDate2, String endDate2,
			String beginYear, String endYear) {
		List<String> stationNames = elementSortService.getStationName();
		// List<String> years = rainTypeYearService.getYear();
		List<String> beginYears = rainTypeYearService.getYear();
		List<String> endYears = rainTypeYearService.getYear();
		String page = "elementsSlt";
		if (stationName == null) {
			stationName = "徐家汇";
		}
		if (stationName2 == null) {
			stationName2 = "徐家汇";
		}
		/*
		 * if (year == null) { year = "2016"; }
		 */
		if (beginDate == null) {
			beginDate = elementSortService.getBeginDate().toString();
		}
		if (endDate == null) {
			endDate = elementSortService.getEndDate().toString();
		}
		if (beginYear == null) {
			beginYear = "1999";
		}
		if (endYear == null) {
			endYear = "2016";
		}
		if (beginDate2 == null) {
			beginDate2 = "03-01";
		}
		if (endDate2 == null) {
			endDate2 = "05-10";
		}
		model.addAttribute("stationNames", stationNames);
		model.addAttribute("stationName", stationName);

		// 条件1
		// model.addAttribute("years", years);
		// model.addAttribute("year", year);
		model.addAttribute("beginDate", beginDate);
		model.addAttribute("endDate", endDate);

		// 条件2
		model.addAttribute("beginYears", beginYears);
		model.addAttribute("endYears", endYears);
		model.addAttribute("beginYear", beginYear);
		model.addAttribute("endYear", endYear);
		model.addAttribute("beginDate2", beginDate2);
		model.addAttribute("endDate2", endDate2);
		return page;
	}

	@RequestMapping("getElementConditions2.shtml")
	public String getElementConditions2(Model model, String stationName, String dx, String beginDate, String endDate) {
		List<String> stationNames = elementSortService.getStationName();
		String page = "";
		if (stationName == null) {
			stationName = "徐家汇";
		}
		if (beginDate == null) {
			beginDate = elementSortService.getBeginDate().toString();
		}
		if (endDate == null) {
			endDate = elementSortService.getEndDate().toString();
		}
		page = "elementsSlt";

		model.addAttribute("stationNames", stationNames);
		model.addAttribute("stationName", stationName);
		model.addAttribute("beginDate", beginDate);
		model.addAttribute("endDate", endDate);
		return page;
	}

	@RequestMapping("getElementSort.shtml")
	public String getElementSort(HttpSession session, HttpServletRequest request, Model model, String dx,
			String stationName, String beginDate, String endDate, String chaxunresult) throws ParseException {
		Date begin = null;
		Date end = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<String> stationNames = elementSortService.getStationName();
		List<String> beginYears = rainTypeYearService.getYear();
		List<String> endYears = rainTypeYearService.getYear();
		List<Precipitation> temMaxList = null;
		List<Precipitation> temMinList = null;
		List<Precipitation> preList = null;
		List<Object[]> objlist = new ArrayList<Object[]>();
		List<Object[]> lisobj = null;
		TestResult tr = null;
		List<Tem> temList = null;
		String page = "";
		String beginYear = "1999";
		String endYear = "2016";
		String beginDate2 = "03-01";
		String endDate2 = "05-10";
		if (chaxunresult == null) {
			chaxunresult = "类型一";
		}
		if (stationName == null) {
			stationName = "徐家汇";
		}
		if (beginDate == null) {
			beginDate = elementSortService.getBeginDate().toString();
		}
		if (endDate == null) {
			endDate = elementSortService.getEndDate().toString();
		}
		if (beginDate != null && !beginDate.equals("")) {
			begin = sdf.parse(beginDate);
		}
		if (endDate != null && !endDate.equals("")) {
			end = sdf.parse(endDate);
		}
		session.setAttribute("dx", dx);
		session.setAttribute("stationName", stationName);
		session.setAttribute("beginDate", beginDate);
		session.setAttribute("endDate", endDate);

		int count = precipitationService.getPreCount(stationName, begin, end);
		if (dx.equals("日降水量（20-20）")) {
			preList = elementSortService.getElementInfos(request, stationName, begin, end);
			lisobj = new ArrayList<Object[]>();
			for (Precipitation pr : preList) {
				Object[] obj = new Object[2];
				obj[0] = pr.getPreTime2020();
				obj[1] = sdf.format(pr.getDateDate());
				lisobj.add(obj);
			}
			tr = new TestResult();
			objlist = tr.order(lisobj);
			page = "elementSort";
		}
		if (dx.equals("日最高气温（最高）")) {
			// 日最高温度(最高)
			temMaxList = elementSortService.getTemMax_Max(request, stationName, begin, end);
			temList = new ArrayList<Tem>();
			for (int i = 0; i < temMaxList.size(); i++) {
				Tem t = new Tem();
				t.setTemNum(temMaxList.get(i).getTemMax());
				t.setTemDate(temMaxList.get(i).getDateDate());
				temList.add(t);
			}
			lisobj = new ArrayList<Object[]>();
			for (Tem m : temList) {
				Object[] obj = new Object[2];
				obj[0] = m.getTemNum();
				obj[1] = sdf.format(m.getTemDate());
				lisobj.add(obj);
			}
			tr = new TestResult();
			objlist = tr.order(lisobj);
			page = "temSort";
		}
		if (dx.equals("日最高气温（最低）")) {
			// 日最高温度(最低)
			temMinList = elementSortService.getTemMax_Min(request, stationName, begin, end);
			temList = new ArrayList<Tem>();
			for (int i = 0; i < temMinList.size(); i++) {
				Tem t = new Tem();
				t.setTemNum(temMinList.get(i).getTemMax());
				t.setTemDate(temMinList.get(i).getDateDate());
				temList.add(t);
			}
			lisobj = new ArrayList<Object[]>();
			for (Tem m : temList) {
				Object[] obj = new Object[2];
				obj[0] = m.getTemNum();
				obj[1] = sdf.format(m.getTemDate());
				lisobj.add(obj);
			}
			tr = new TestResult();
			objlist = tr.order(lisobj);
			page = "temSort";
		}
		if (dx.equals("日最低气温（最高）")) {
			// 日最低温度(最高)
			temMaxList = elementSortService.getTemMin_Max(request, stationName, begin, end);
			temList = new ArrayList<Tem>();
			for (int i = 0; i < temMaxList.size(); i++) {
				Tem t = new Tem();
				t.setTemNum(temMaxList.get(i).getTemMin());
				t.setTemDate(temMaxList.get(i).getDateDate());
				temList.add(t);
			}
			lisobj = new ArrayList<Object[]>();
			for (Tem m : temList) {
				Object[] obj = new Object[2];
				obj[0] = m.getTemNum();
				obj[1] = sdf.format(m.getTemDate());
				lisobj.add(obj);
			}
			tr = new TestResult();
			objlist = tr.order(lisobj);
			page = "temSort";
		}
		if (dx.equals("日最低气温（最低）")) {
			// 日最低温度(最低)
			temMinList = elementSortService.getTemMin_Min(request, stationName, begin, end);
			temList = new ArrayList<Tem>();
			for (int i = 0; i < temMinList.size(); i++) {
				Tem t = new Tem();
				t.setTemNum(temMinList.get(i).getTemMin());
				t.setTemDate(temMinList.get(i).getDateDate());
				temList.add(t);
			}
			lisobj = new ArrayList<Object[]>();
			for (Tem m : temList) {
				Object[] obj = new Object[2];
				obj[0] = m.getTemNum();
				obj[1] = sdf.format(m.getTemDate());
				lisobj.add(obj);
			}
			tr = new TestResult();
			objlist = tr.order(lisobj);
			page = "temSort";
		}
		if (dx.equals("日平均气温（最高）")) {
			// 日平均温度(最高)
			temMaxList = elementSortService.getTemAvg_Max(request, stationName, begin, end);
			temList = new ArrayList<Tem>();
			for (int i = 0; i < temMaxList.size(); i++) {
				Tem t = new Tem();
				t.setTemNum(temMaxList.get(i).getTemAvg());
				t.setTemDate(temMaxList.get(i).getDateDate());
				temList.add(t);
			}
			lisobj = new ArrayList<Object[]>();
			for (Tem m : temList) {
				Object[] obj = new Object[2];
				obj[0] = m.getTemNum();
				obj[1] = sdf.format(m.getTemDate());
				lisobj.add(obj);
			}
			tr = new TestResult();
			objlist = tr.order(lisobj);
			page = "temSort";
		}
		if (dx.equals("日平均气温（最低）")) {
			// 日平均温度(最低)
			temMinList = elementSortService.getTemAvg_Min(request, stationName, begin, end);
			temList = new ArrayList<Tem>();
			for (int i = 0; i < temMinList.size(); i++) {
				Tem t = new Tem();
				t.setTemNum(temMinList.get(i).getTemAvg());
				t.setTemDate(temMinList.get(i).getDateDate());
				temList.add(t);
			}
			lisobj = new ArrayList<Object[]>();
			for (Tem m : temList) {
				Object[] obj = new Object[2];
				obj[0] = m.getTemNum();
				obj[1] = sdf.format(m.getTemDate());
				lisobj.add(obj);
			}
			tr = new TestResult();
			objlist = tr.order(lisobj);
			page = "temSort";
		}

		model.addAttribute("precount", count);
		model.addAttribute("objlist", objlist);
		model.addAttribute("beginYears", beginYears);
		model.addAttribute("endYears", endYears);
		model.addAttribute("beginYear", beginYear);
		model.addAttribute("endYear", endYear);
		model.addAttribute("beginDate2", beginDate2);
		model.addAttribute("endDate2", endDate2);
		model.addAttribute("stationNames", stationNames);
		model.addAttribute("stationName", stationName);
		model.addAttribute("beginDate", beginDate);
		model.addAttribute("endDate", endDate);
		model.addAttribute("dx", dx);
		model.addAttribute("chaxunresult", chaxunresult);
		return page;
	}

	@RequestMapping("getElementSort2.shtml")
	public String getElementSort2(HttpSession session, HttpServletRequest request, Model model, String dx,
			String stationName, String beginYear, String endYear, String beginDate2, String endDate2,
			String chaxunresult) throws ParseException {
		SimpleDateFormat dateDate = new SimpleDateFormat("yyyy-MM-dd");
		List<String> stationNames = elementSortService.getStationName();
		List<String> beginYears = rainTypeYearService.getYear();
		List<String> endYears = rainTypeYearService.getYear();
		List<Precipitation> list = null;
		List<Object[]> objlist = new ArrayList<Object[]>();
		List<Object[]> lisobj = null;
		TestResult tr = null;
		Date begin = null;
		Date end = null;
		if (beginDate2 != null && !beginDate2.equals("")) {
			begin = dateDate.parse("2016" + "-" + beginDate2);
		}
		if (endDate2 != null && !endDate2.equals("")) {
			end = dateDate.parse("2016" + "-" + endDate2);
		}
		int count = precipitationService.getPreCount2(stationName, Integer.parseInt(beginYear), Integer.parseInt(endYear), begin,
				end);
		if (dx.equals("日最高气温（最高）")) {
			// 日最高温度(最高)
			list = elementSortService.getTemMaxDesc(request, stationName, Integer.parseInt(beginYear),
					Integer.parseInt(endYear), begin, end);
			lisobj = new ArrayList<Object[]>();
			for (Precipitation pre : list) {
				Object[] obj = new Object[2];
				obj[0] = pre.getTemMax();
				obj[1] = dateDate.format(pre.getDateDate());
				lisobj.add(obj);
			}
			tr = new TestResult();
			objlist = tr.order(lisobj);
		}
		if (dx.equals("日最高气温（最低）")) {
			// 日最高温度(最低)
			list = elementSortService.getTemMaxAsc(request, stationName, Integer.parseInt(beginYear),
					Integer.parseInt(endYear), begin, end);
			lisobj = new ArrayList<Object[]>();
			for (Precipitation pre : list) {
				Object[] obj = new Object[2];
				obj[0] = pre.getTemMax();
				obj[1] = dateDate.format(pre.getDateDate());
				lisobj.add(obj);
			}
			tr = new TestResult();
			objlist = tr.order(lisobj);
		}
		if (dx.equals("日最低气温（最高）")) {
			// 日最低温度(最高)
			list = elementSortService.getTemMinDesc(request, stationName, Integer.parseInt(beginYear),
					Integer.parseInt(endYear), begin, end);
			lisobj = new ArrayList<Object[]>();
			for (Precipitation pre : list) {
				Object[] obj = new Object[2];
				obj[0] = pre.getTemMin();
				obj[1] = dateDate.format(pre.getDateDate());
				lisobj.add(obj);
			}
			tr = new TestResult();
			objlist = tr.order(lisobj);
		}
		if (dx.equals("日最低气温（最低）")) {
			// 日最低温度(最低)
			list = elementSortService.getTemMinAsc(request, stationName, Integer.parseInt(beginYear),
					Integer.parseInt(endYear), begin, end);
			lisobj = new ArrayList<Object[]>();
			for (Precipitation pre : list) {
				Object[] obj = new Object[2];
				obj[0] = pre.getTemMin();
				obj[1] = dateDate.format(pre.getDateDate());
				lisobj.add(obj);
			}
			tr = new TestResult();
			objlist = tr.order(lisobj);
		}
		if (dx.equals("日平均气温（最高）")) {
			// 日平均温度(最高)
			list = elementSortService.getTemAvgDesc(request, stationName, Integer.parseInt(beginYear),
					Integer.parseInt(endYear), begin, end);
			lisobj = new ArrayList<Object[]>();
			for (Precipitation pre : list) {
				Object[] obj = new Object[2];
				obj[0] = pre.getTemAvg();
				obj[1] = dateDate.format(pre.getDateDate());
				lisobj.add(obj);
			}
			tr = new TestResult();
			objlist = tr.order(lisobj);
		}
		if (dx.equals("日平均气温（最低）")) {
			// 日平均温度(最低)
			list = elementSortService.getTemAvgAsc(request, stationName, Integer.parseInt(beginYear),
					Integer.parseInt(endYear), begin, end);
			lisobj = new ArrayList<Object[]>();
			for (Precipitation pre : list) {
				Object[] obj = new Object[2];
				obj[0] = pre.getTemAvg();
				obj[1] = dateDate.format(pre.getDateDate());
				lisobj.add(obj);
			}
			tr = new TestResult();
			objlist = tr.order(lisobj);
		}
		session.setAttribute("wddx", dx);
		session.setAttribute("wdstationName", stationName);
		session.setAttribute("wdbeginYear", beginYear);
		session.setAttribute("wdendYear", endYear);
		session.setAttribute("wdbeginDate", beginDate2);
		session.setAttribute("wdendDate", endDate2);

		
		model.addAttribute("precount", count);
		model.addAttribute("objlist", objlist);
		model.addAttribute("stationNames", stationNames);
		model.addAttribute("stationName", stationName);
		model.addAttribute("beginYears", beginYears);
		model.addAttribute("endYears", endYears);
		model.addAttribute("beginYear", beginYear);
		model.addAttribute("endYear", endYear);
		model.addAttribute("beginDate2", beginDate2);
		model.addAttribute("endDate2", endDate2);
		model.addAttribute("dx", dx);
		model.addAttribute("chaxunresult", chaxunresult);
		return "temSort";
	}

	@RequestMapping("getHighchartElementSort2.shtml")
	@ResponseBody
	public String getHighchartElementSort2(HttpSession session, HttpServletRequest request) throws ParseException {
		Map<String, Object> map = new HashMap<String, Object>();
		SimpleDateFormat dateDate = new SimpleDateFormat("yyyy-MM-dd");
		List<Precipitation> list = null;
		List<Object[]> objlist = new ArrayList<Object[]>();
		List<Object[]> lisobj = null;
		TestResult tr = null;
		Date begin = null;
		Date end = null;
		String dx = session.getAttribute("wddx").toString();
		String stationName = session.getAttribute("wdstationName").toString();
		String beginYear = session.getAttribute("wdbeginYear").toString();
		String endYear = session.getAttribute("wdendYear").toString();
		String beginDate2 = session.getAttribute("wdbeginDate").toString();
		String endDate2 = session.getAttribute("wdendDate").toString();
		begin = dateDate.parse("2016" + "-" + beginDate2);
		end = dateDate.parse("2016" + "-" + endDate2);
		if (dx.equals("日最高气温（最高）")) {
			// 日最高温度(最高)
			list = elementSortService.getTemMaxDesc(request, stationName, Integer.parseInt(beginYear),
					Integer.parseInt(endYear), begin, end);
			lisobj = new ArrayList<Object[]>();
			for (Precipitation pre : list) {
				Object[] obj = new Object[2];
				obj[0] = pre.getTemMax();
				obj[1] = dateDate.format(pre.getDateDate());
				lisobj.add(obj);
			}
			tr = new TestResult();
			objlist = tr.order(lisobj);
		}
		if (dx.equals("日最高气温（最低）")) {
			// 日最高温度(最低)
			list = elementSortService.getTemMaxAsc(request, stationName, Integer.parseInt(beginYear),
					Integer.parseInt(endYear), begin, end);
			lisobj = new ArrayList<Object[]>();
			for (Precipitation pre : list) {
				Object[] obj = new Object[2];
				obj[0] = pre.getTemMax();
				obj[1] = dateDate.format(pre.getDateDate());
				lisobj.add(obj);
			}
			tr = new TestResult();
			objlist = tr.order(lisobj);
		}
		if (dx.equals("日最低气温（最高）")) {
			// 日最低温度(最高)
			list = elementSortService.getTemMinDesc(request, stationName, Integer.parseInt(beginYear),
					Integer.parseInt(endYear), begin, end);
			lisobj = new ArrayList<Object[]>();
			for (Precipitation pre : list) {
				Object[] obj = new Object[2];
				obj[0] = pre.getTemMin();
				obj[1] = dateDate.format(pre.getDateDate());
				lisobj.add(obj);
			}
			tr = new TestResult();
			objlist = tr.order(lisobj);
		}
		if (dx.equals("日最低气温（最低）")) {
			// 日最低温度(最低)
			list = elementSortService.getTemMinAsc(request, stationName, Integer.parseInt(beginYear),
					Integer.parseInt(endYear), begin, end);
			lisobj = new ArrayList<Object[]>();
			for (Precipitation pre : list) {
				Object[] obj = new Object[2];
				obj[0] = pre.getTemMin();
				obj[1] = dateDate.format(pre.getDateDate());
				lisobj.add(obj);
			}
			tr = new TestResult();
			objlist = tr.order(lisobj);
		}
		if (dx.equals("日平均气温（最高）")) {
			// 日平均温度(最高)
			list = elementSortService.getTemAvgDesc(request, stationName, Integer.parseInt(beginYear),
					Integer.parseInt(endYear), begin, end);
			lisobj = new ArrayList<Object[]>();
			for (Precipitation pre : list) {
				Object[] obj = new Object[2];
				obj[0] = pre.getTemAvg();
				obj[1] = dateDate.format(pre.getDateDate());
				lisobj.add(obj);
			}
			tr = new TestResult();
			objlist = tr.order(lisobj);
		}
		if (dx.equals("日平均气温（最低）")) {
			// 日平均温度(最低)
			list = elementSortService.getTemAvgAsc(request, stationName, Integer.parseInt(beginYear),
					Integer.parseInt(endYear), begin, end);
			lisobj = new ArrayList<Object[]>();
			for (Precipitation pre : list) {
				Object[] obj = new Object[2];
				obj[0] = pre.getTemAvg();
				obj[1] = dateDate.format(pre.getDateDate());
				lisobj.add(obj);
			}
			tr = new TestResult();
			objlist = tr.order(lisobj);
		}
		if (!(objlist.size() < 10)) {
			objlist = objlist.subList(0, 10);
		}
		map.put("list", objlist);
		String s = new Gson().toJson(map);
		String name = s.replaceAll(".*\"list\":", "").replace("]}", "]");
		return name;

	}

	@RequestMapping("getHighchartElementsTem2.shtml")
	@ResponseBody
	public String highchartsElementsTem2(HttpSession session, HttpServletRequest request) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<Precipitation> temMaxList = null;
		List<Precipitation> temMinList = null;
		List<Object[]> objlist = new ArrayList<Object[]>();
		List<Object[]> lisobj = null;
		TestResult tr = null;
		List<Tem> temList = null;
		Map<String, Object> map = new HashMap<String, Object>();
		String dx = session.getAttribute("dx").toString();
		String stationName = session.getAttribute("stationName").toString();
		String beginDate = session.getAttribute("beginDate").toString();
		String endDate = session.getAttribute("endDate").toString();
		Date begin = sdf.parse(beginDate);
		Date end = sdf.parse(endDate);
		if (dx.equals("日最高气温（最高）")) {
			// 日最高温度(最高)
			temMaxList = elementSortService.getTemMax_Max(request, stationName, begin, end);
			temList = new ArrayList<Tem>();
			for (int i = 0; i < temMaxList.size(); i++) {
				Tem t = new Tem();
				t.setTemNum(temMaxList.get(i).getTemMax());
				t.setTemDate(temMaxList.get(i).getDateDate());
				temList.add(t);
			}
			lisobj = new ArrayList<Object[]>();
			for (Tem m : temList) {
				Object[] obj = new Object[2];
				obj[0] = m.getTemNum();
				obj[1] = sdf.format(m.getTemDate());
				lisobj.add(obj);
			}
			tr = new TestResult();
			objlist = tr.order(lisobj);
		}
		if (dx.equals("日最高气温（最低）")) {
			// 日最高温度(最低)
			temMinList = elementSortService.getTemMax_Min(request, stationName, begin, end);
			temList = new ArrayList<Tem>();
			for (int i = 0; i < temMinList.size(); i++) {
				Tem t = new Tem();
				t.setTemNum(temMinList.get(i).getTemMax());
				t.setTemDate(temMinList.get(i).getDateDate());
				temList.add(t);
			}
			lisobj = new ArrayList<Object[]>();
			for (Tem m : temList) {
				Object[] obj = new Object[2];
				obj[0] = m.getTemNum();
				obj[1] = sdf.format(m.getTemDate());
				lisobj.add(obj);
			}
			tr = new TestResult();
			objlist = tr.order(lisobj);
		}
		if (dx.equals("日最低气温（最高）")) {
			// 日最低温度(最高)
			temMaxList = elementSortService.getTemMin_Max(request, stationName, begin, end);
			temList = new ArrayList<Tem>();
			for (int i = 0; i < temMaxList.size(); i++) {
				Tem t = new Tem();
				t.setTemNum(temMaxList.get(i).getTemMin());
				t.setTemDate(temMaxList.get(i).getDateDate());
				temList.add(t);
			}
			lisobj = new ArrayList<Object[]>();
			for (Tem m : temList) {
				Object[] obj = new Object[2];
				obj[0] = m.getTemNum();
				obj[1] = sdf.format(m.getTemDate());
				lisobj.add(obj);
			}
			tr = new TestResult();
			objlist = tr.order(lisobj);
		}
		if (dx.equals("日最低气温（最低）")) {
			// 日最低温度(最低)
			temMinList = elementSortService.getTemMin_Min(request, stationName, begin, end);
			temList = new ArrayList<Tem>();
			for (int i = 0; i < temMinList.size(); i++) {
				Tem t = new Tem();
				t.setTemNum(temMinList.get(i).getTemMin());
				t.setTemDate(temMinList.get(i).getDateDate());
				temList.add(t);
			}
			lisobj = new ArrayList<Object[]>();
			for (Tem m : temList) {
				Object[] obj = new Object[2];
				obj[0] = m.getTemNum();
				obj[1] = sdf.format(m.getTemDate());
				lisobj.add(obj);
			}
			tr = new TestResult();
			objlist = tr.order(lisobj);
		}
		if (dx.equals("日平均气温（最高）")) {
			// 日平均温度(最高)
			temMaxList = elementSortService.getTemAvg_Max(request, stationName, begin, end);
			temList = new ArrayList<Tem>();
			for (int i = 0; i < temMaxList.size(); i++) {
				Tem t = new Tem();
				t.setTemNum(temMaxList.get(i).getTemAvg());
				t.setTemDate(temMaxList.get(i).getDateDate());
				temList.add(t);
			}
			lisobj = new ArrayList<Object[]>();
			for (Tem m : temList) {
				Object[] obj = new Object[2];
				obj[0] = m.getTemNum();
				obj[1] = sdf.format(m.getTemDate());
				lisobj.add(obj);
			}
			tr = new TestResult();
			objlist = tr.order(lisobj);
		}
		if (dx.equals("日平均气温（最低）")) {
			// 日平均温度(最低)
			temMinList = elementSortService.getTemAvg_Min(request, stationName, begin, end);
			temList = new ArrayList<Tem>();
			for (int i = 0; i < temMinList.size(); i++) {
				Tem t = new Tem();
				t.setTemNum(temMinList.get(i).getTemAvg());
				t.setTemDate(temMinList.get(i).getDateDate());
				temList.add(t);
			}
			lisobj = new ArrayList<Object[]>();
			for (Tem m : temList) {
				Object[] obj = new Object[2];
				obj[0] = m.getTemNum();
				obj[1] = sdf.format(m.getTemDate());
				lisobj.add(obj);
			}
			tr = new TestResult();
			objlist = tr.order(lisobj);
		}
		if (!(objlist.size() < 10)) {
			objlist = objlist.subList(0, 10);
		}
		map.put("list", objlist);
		String s = new Gson().toJson(map);
		String name = s.replaceAll(".*\"list\":", "").replace("]}", "]");
		return name;
	}

	@RequestMapping("getHighchartElementsTem.shtml")
	@ResponseBody
	public String highchartsElementsTem(HttpSession session, HttpServletRequest request) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Map<String, Object> map = new HashMap<String, Object>();
		List<ElementsTem> list = null;
		List<Object[]> elementList = null;
		String dx = session.getAttribute("dx").toString();
		String stationName = session.getAttribute("stationName").toString();
		String beginDate = session.getAttribute("beginDate").toString();
		String endDate = session.getAttribute("endDate").toString();
		Date begin = sdf.parse(beginDate);
		int beginYear = begin.getYear() + 1900;
		Date end = sdf.parse(endDate);
		int endYear = end.getYear() + 1900;
		if (dx.equals("日降水量（20-20）")) {
			list = new ArrayList<ElementsTem>();
			elementList = elementSortService.getElementsHighcharts(stationName, beginYear, endYear);
			for (int i = 0; i < elementList.size(); i++) {
				ElementsTem t = new ElementsTem();
				t.setTemNum(Double.parseDouble(elementList.get(i)[0].toString()));
				t.setTemYear(elementList.get(i)[1].toString());
				list.add(t);
			}
		}
		if (dx.equals("日最高气温（最高）")) {
			list = new ArrayList<ElementsTem>();
			elementList = elementSortService.getTemMaxHighchartsMax(stationName, beginYear, endYear);
			for (int i = 0; i < elementList.size(); i++) {
				ElementsTem t = new ElementsTem();
				t.setTemNum(Double.parseDouble(elementList.get(i)[0].toString()));
				t.setTemYear(elementList.get(i)[1].toString());
				list.add(t);
			}
		}
		if (dx.equals("日最高气温（最低）")) {
			list = new ArrayList<ElementsTem>();
			elementList = elementSortService.getTemMaxHighchartsMin(stationName, beginYear, endYear);
			for (int i = 0; i < elementList.size(); i++) {
				ElementsTem t = new ElementsTem();
				t.setTemNum(Double.parseDouble(elementList.get(i)[0].toString()));
				t.setTemYear(elementList.get(i)[1].toString());
				list.add(t);
			}
		}
		if (dx.equals("日最低气温（最高）")) {
			list = new ArrayList<ElementsTem>();
			elementList = elementSortService.getTemMinHighchartsMax(stationName, beginYear, endYear);
			for (int i = 0; i < elementList.size(); i++) {
				ElementsTem t = new ElementsTem();
				t.setTemNum(Double.parseDouble(elementList.get(i)[0].toString()));
				t.setTemYear(elementList.get(i)[1].toString());
				list.add(t);
			}
		}
		if (dx.equals("日最低气温（最低）")) {
			list = new ArrayList<ElementsTem>();
			elementList = elementSortService.getTemMinHighchartsMin(stationName, beginYear, endYear);
			for (int i = 0; i < elementList.size(); i++) {
				ElementsTem t = new ElementsTem();
				t.setTemNum(Double.parseDouble(elementList.get(i)[0].toString()));
				t.setTemYear(elementList.get(i)[1].toString());
				list.add(t);
			}
		}
		if (dx.equals("日平均气温（最高）")) {
			list = new ArrayList<ElementsTem>();
			elementList = elementSortService.getTemAvgHighchartsMax(stationName, beginYear, endYear);
			for (int i = 0; i < elementList.size(); i++) {
				ElementsTem t = new ElementsTem();
				t.setTemNum(Double.parseDouble(elementList.get(i)[0].toString()));
				t.setTemYear(elementList.get(i)[1].toString());
				list.add(t);
			}
		}
		if (dx.equals("日平均气温（最低）")) {
			list = new ArrayList<ElementsTem>();
			elementList = elementSortService.getTemAvgHighchartsMin(stationName, beginYear, endYear);
			for (int i = 0; i < elementList.size(); i++) {
				ElementsTem t = new ElementsTem();
				t.setTemNum(Double.parseDouble(elementList.get(i)[0].toString()));
				t.setTemYear(elementList.get(i)[1].toString());
				list.add(t);
			}
		}
		map.put("list", list);
		String s = new Gson().toJson(map);
		String name = s.replaceAll(".*\"list\":", "").replace("]}", "]");
		return name;
	}

	@RequestMapping("exportElementsDayPre2020.shtml")
	public void exportElementsDayPre2020(HttpServletResponse response, String dx, String stationName, String beginDate,
			String endDate) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<Precipitation> preList = null;
		List<Object[]> objlist = new ArrayList<Object[]>();
		List<Object[]> lisobj = null;
		TestResult tr = null;
		if (dx != null) {
			if (dx.equals("日降水量（20-20）")) {
				preList = elementSortService.getElementsDayPre2020(stationName, beginDate, endDate);
				lisobj = new ArrayList<Object[]>();
				for (Precipitation p : preList) {
					Object[] obj = new Object[2];
					if (p.getPreTime2020() == null) {
						obj[0] = "";
					} else {
						obj[0] = p.getPreTime2020();
					}
					obj[1] = sdf.format(p.getDateDate());
					lisobj.add(obj);
				}
				tr = new TestResult();
				objlist = tr.order(lisobj);
			}

		}
		if (!(objlist.size() < 10)) {
			objlist = objlist.subList(0, 10);
		}
		String fileName = Tool.dateFormat(new Date(), "yyyyMMddHHmmss");
		HSSFWorkbook wb = elementSortService.exportElementsDayPre2020(objlist);
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xls");
		OutputStream ouputStream = response.getOutputStream();
		wb.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();
	}

	@RequestMapping("exportElementsTem.shtml")
	public void exportElementsTem(HttpServletRequest request, HttpServletResponse response, String dx,
			String stationName, String beginDate, String endDate) throws Exception {
		Date begin = null;
		Date end = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<Precipitation> temMaxList = null;
		List<Precipitation> temMinList = null;
		List<Object[]> objlist = new ArrayList<Object[]>();
		List<Object[]> lisobj = null;
		TestResult tr = null;
		List<Tem> temList = null;
		if (beginDate != null && !beginDate.equals("")) {
			begin = sdf.parse(beginDate);
		}
		if (endDate != null && !endDate.equals("")) {
			end = sdf.parse(endDate);
		}
		if (dx.equals("日最高气温（最高）")) {
			// 日最高温度(最高)
			temMaxList = elementSortService.getTemMax_Max(request, stationName, begin, end);
			temList = new ArrayList<Tem>();
			for (int i = 0; i < temMaxList.size(); i++) {
				Tem t = new Tem();
				t.setTemNum(temMaxList.get(i).getTemMax());
				t.setTemDate(temMaxList.get(i).getDateDate());
				temList.add(t);
			}
			lisobj = new ArrayList<Object[]>();
			for (Tem m : temList) {
				Object[] obj = new Object[2];
				obj[0] = m.getTemNum();
				obj[1] = sdf.format(m.getTemDate());
				lisobj.add(obj);
			}
			tr = new TestResult();
			objlist = tr.order(lisobj);
		}
		if (dx.equals("日最高气温（最低）")) {
			// 日最高温度(最低)
			temMinList = elementSortService.getTemMax_Min(request, stationName, begin, end);
			temList = new ArrayList<Tem>();
			for (int i = 0; i < temMinList.size(); i++) {
				Tem t = new Tem();
				t.setTemNum(temMinList.get(i).getTemMax());
				t.setTemDate(temMinList.get(i).getDateDate());
				temList.add(t);
			}
			lisobj = new ArrayList<Object[]>();
			for (Tem m : temList) {
				Object[] obj = new Object[2];
				obj[0] = m.getTemNum();
				obj[1] = sdf.format(m.getTemDate());
				lisobj.add(obj);
			}
			tr = new TestResult();
			objlist = tr.order(lisobj);
		}
		if (dx.equals("日最低气温（最高）")) {
			// 日最低温度(最高)
			temMaxList = elementSortService.getTemMin_Max(request, stationName, begin, end);
			temList = new ArrayList<Tem>();
			for (int i = 0; i < temMaxList.size(); i++) {
				Tem t = new Tem();
				t.setTemNum(temMaxList.get(i).getTemMin());
				t.setTemDate(temMaxList.get(i).getDateDate());
				temList.add(t);
			}
			lisobj = new ArrayList<Object[]>();
			for (Tem m : temList) {
				Object[] obj = new Object[2];
				obj[0] = m.getTemNum();
				obj[1] = sdf.format(m.getTemDate());
				lisobj.add(obj);
			}
			tr = new TestResult();
			objlist = tr.order(lisobj);
		}
		if (dx.equals("日最低气温（最低）")) {
			// 日最低温度(最低)
			temMinList = elementSortService.getTemMin_Min(request, stationName, begin, end);
			temList = new ArrayList<Tem>();
			for (int i = 0; i < temMinList.size(); i++) {
				Tem t = new Tem();
				t.setTemNum(temMinList.get(i).getTemMin());
				t.setTemDate(temMinList.get(i).getDateDate());
				temList.add(t);
			}
			lisobj = new ArrayList<Object[]>();
			for (Tem m : temList) {
				Object[] obj = new Object[2];
				obj[0] = m.getTemNum();
				obj[1] = sdf.format(m.getTemDate());
				lisobj.add(obj);
			}
			tr = new TestResult();
			objlist = tr.order(lisobj);
		}
		if (dx.equals("日平均气温（最高）")) {
			// 日平均温度(最高)
			temMaxList = elementSortService.getTemAvg_Max(request, stationName, begin, end);
			temList = new ArrayList<Tem>();
			for (int i = 0; i < temMaxList.size(); i++) {
				Tem t = new Tem();
				t.setTemNum(temMaxList.get(i).getTemAvg());
				t.setTemDate(temMaxList.get(i).getDateDate());
				temList.add(t);
			}
			lisobj = new ArrayList<Object[]>();
			for (Tem m : temList) {
				Object[] obj = new Object[2];
				obj[0] = m.getTemNum();
				obj[1] = sdf.format(m.getTemDate());
				lisobj.add(obj);
			}
			tr = new TestResult();
			objlist = tr.order(lisobj);
		}
		if (dx.equals("日平均气温（最低）")) {
			// 日平均温度(最低)
			temMinList = elementSortService.getTemAvg_Min(request, stationName, begin, end);
			temList = new ArrayList<Tem>();
			for (int i = 0; i < temMinList.size(); i++) {
				Tem t = new Tem();
				t.setTemNum(temMinList.get(i).getTemAvg());
				t.setTemDate(temMinList.get(i).getDateDate());
				temList.add(t);
			}
			lisobj = new ArrayList<Object[]>();
			for (Tem m : temList) {
				Object[] obj = new Object[2];
				obj[0] = m.getTemNum();
				obj[1] = sdf.format(m.getTemDate());
				lisobj.add(obj);
			}
			tr = new TestResult();
			objlist = tr.order(lisobj);
		}
		if (!(objlist.size() < 10)) {
			objlist = objlist.subList(0, 10);
		}
		String fileName = Tool.dateFormat(new Date(), "yyyyMMddHHmmss");
		HSSFWorkbook wb = elementSortService.exportElementsTem(objlist);
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xls");
		OutputStream ouputStream = response.getOutputStream();
		wb.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();
	}

	@RequestMapping("exportElementsTem2.shtml")
	public void exportElementsTem2(HttpServletRequest request, HttpServletResponse response, String dx,
			String stationName, String beginYear, String endYear, String beginDate2, String endDate2) throws Exception {
		SimpleDateFormat dateDate = new SimpleDateFormat("yyyy-MM-dd");
		List<Precipitation> list = null;
		List<Object[]> objlist = new ArrayList<Object[]>();
		List<Object[]> lisobj = null;
		TestResult tr = null;
		Date begin = null;
		Date end = null;
		if (beginDate2 != null && !beginDate2.equals("")) {
			begin = dateDate.parse("2016" + "-" + beginDate2);
		}
		if (endDate2 != null && !endDate2.equals("")) {
			end = dateDate.parse("2016" + "-" + endDate2);
		}
		if (dx.equals("日最高气温（最高）")) {
			// 日最高温度(最高)
			list = elementSortService.getTemMaxDesc(request, stationName, Integer.parseInt(beginYear),
					Integer.parseInt(endYear), begin, end);
			lisobj = new ArrayList<Object[]>();
			for (Precipitation pre : list) {
				Object[] obj = new Object[2];
				obj[0] = pre.getTemMax();
				obj[1] = dateDate.format(pre.getDateDate());
				lisobj.add(obj);
			}
			tr = new TestResult();
			objlist = tr.order(lisobj);
		}
		if (dx.equals("日最高气温（最低）")) {
			// 日最高温度(最低)
			list = elementSortService.getTemMaxAsc(request, stationName, Integer.parseInt(beginYear),
					Integer.parseInt(endYear), begin, end);
			lisobj = new ArrayList<Object[]>();
			for (Precipitation pre : list) {
				Object[] obj = new Object[2];
				obj[0] = pre.getTemMax();
				obj[1] = dateDate.format(pre.getDateDate());
				lisobj.add(obj);
			}
			tr = new TestResult();
			objlist = tr.order(lisobj);
		}
		if (dx.equals("日最低气温（最高）")) {
			// 日最低温度(最高)
			list = elementSortService.getTemMinDesc(request, stationName, Integer.parseInt(beginYear),
					Integer.parseInt(endYear), begin, end);
			lisobj = new ArrayList<Object[]>();
			for (Precipitation pre : list) {
				Object[] obj = new Object[2];
				obj[0] = pre.getTemMin();
				obj[1] = dateDate.format(pre.getDateDate());
				lisobj.add(obj);
			}
			tr = new TestResult();
			objlist = tr.order(lisobj);
		}
		if (dx.equals("日最低气温（最低）")) {
			// 日最低温度(最低)
			list = elementSortService.getTemMinAsc(request, stationName, Integer.parseInt(beginYear),
					Integer.parseInt(endYear), begin, end);
			lisobj = new ArrayList<Object[]>();
			for (Precipitation pre : list) {
				Object[] obj = new Object[2];
				obj[0] = pre.getTemMin();
				obj[1] = dateDate.format(pre.getDateDate());
				lisobj.add(obj);
			}
			tr = new TestResult();
			objlist = tr.order(lisobj);
		}
		if (dx.equals("日平均气温（最高）")) {
			// 日平均温度(最高)
			list = elementSortService.getTemAvgDesc(request, stationName, Integer.parseInt(beginYear),
					Integer.parseInt(endYear), begin, end);
			lisobj = new ArrayList<Object[]>();
			for (Precipitation pre : list) {
				Object[] obj = new Object[2];
				obj[0] = pre.getTemAvg();
				obj[1] = dateDate.format(pre.getDateDate());
				lisobj.add(obj);
			}
			tr = new TestResult();
			objlist = tr.order(lisobj);
		}
		if (dx.equals("日平均气温（最低）")) {
			// 日平均温度(最低)
			list = elementSortService.getTemAvgAsc(request, stationName, Integer.parseInt(beginYear),
					Integer.parseInt(endYear), begin, end);
			lisobj = new ArrayList<Object[]>();
			for (Precipitation pre : list) {
				Object[] obj = new Object[2];
				obj[0] = pre.getTemAvg();
				obj[1] = dateDate.format(pre.getDateDate());
				lisobj.add(obj);
			}
			tr = new TestResult();
			objlist = tr.order(lisobj);
		}
		if (!(objlist.size() < 10)) {
			objlist = objlist.subList(0, 10);
		}
		String fileName = Tool.dateFormat(new Date(), "yyyyMMddHHmmss");
		HSSFWorkbook wb = elementSortService.exportElementsTem(objlist);
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xls");
		OutputStream ouputStream = response.getOutputStream();
		wb.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();
	}
}
