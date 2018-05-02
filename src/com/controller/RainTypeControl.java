/**
 * 
 */
package com.controller;

import java.io.OutputStream;
import java.math.BigDecimal;
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
import com.google.gson.Gson;
import com.service.ElementSortService;
import com.service.PrecipitationService;
import com.service.RainType1Service;
import com.service.RainType2Service;
import com.service.RainTypeYearService;
import com.util.TestResult;
import com.util.Tool;

/**
 * @author 分裂状态。
 *
 */
@Controller
public class RainTypeControl {

	@Autowired
	private ElementSortService elementSortService;

	@Autowired
	private RainTypeYearService rainTypeYearService;

	@Autowired
	private PrecipitationService precipitationService;

	@Autowired
	private RainType1Service rainType1Service;

	@Autowired
	private RainType2Service rainType2Service;

	@RequestMapping("getRainType.shtml")
	public String getRainType(HttpSession session, HttpServletRequest request, Model model, String stationName,
			String stationName2, String year, String beginDate, String endDate, String beginDate2, String endDate2,
			String beginYear, String endYear) {
		List<String> stationNames = elementSortService.getStationName();
		// List<String> years = rainTypeYearService.getYear();
		List<String> beginYears = rainTypeYearService.getYear();
		List<String> endYears = rainTypeYearService.getYear();
		String page = "raintypeSlt";
		if (stationName == null) {
			stationName = "徐家汇";
		}
		if (stationName2 == null) {
			stationName2 = "徐家汇";
		}
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

	@RequestMapping("getRainTypePre1.shtml")
	public String getRainTypePre1(HttpSession session, HttpServletRequest request, Model model, String stationName,
			String beginDate, String endDate, String chaxunresult) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<String> stationNames = elementSortService.getStationName();
		List<String> beginYears = rainTypeYearService.getYear();
		List<String> endYears = rainTypeYearService.getYear();
		Date begin = null;
		Date end = null;
		List<Object[]> objlist = new ArrayList<Object[]>();
		List<Precipitation> preList = null;
		List<Object[]> lisobj = null;
		TestResult tr = null;
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
		int count = precipitationService.getPreCount(stationName, begin, end);
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
		// session保存的结果
		session.setAttribute("typedx", "日雨量（20-20）");
		session.setAttribute("typestationName", stationName);
		session.setAttribute("typebeginDate", beginDate);
		session.setAttribute("typeendDate", endDate);

		// 第一种
		model.addAttribute("precount", count);
		model.addAttribute("objlist", objlist);
		model.addAttribute("stationNames", stationNames);
		model.addAttribute("stationName", stationName);
		model.addAttribute("beginDate", beginDate);
		model.addAttribute("endDate", endDate);
		model.addAttribute("chaxunresult", chaxunresult);

		// 第二种
		model.addAttribute("beginYears", beginYears);
		model.addAttribute("endYears", endYears);
		model.addAttribute("beginYear", beginYear);
		model.addAttribute("endYear", endYear);
		model.addAttribute("beginDate2", beginDate2);
		model.addAttribute("endDate2", endDate2);
		model.addAttribute("begin", begin);
		model.addAttribute("end", end);
		return "rainTypePre_2020";
	}

	@RequestMapping("getRainTypeCon2.shtml")
	public String getRainTypeCon2(HttpSession session, HttpServletRequest request, Model model, String stationName,
			String beginYear, String endYear, String beginDate2, String endDate2, String type, String chaxunresult)
			throws ParseException {
		SimpleDateFormat dateDate = new SimpleDateFormat("yyyy-MM-dd");
		List<String> stationNames = elementSortService.getStationName();
		List<String> beginYears = rainTypeYearService.getYear();
		List<String> endYears = rainTypeYearService.getYear();
		List<Precipitation> list = null;
		List<Object[]> listObj = null;
		List<Object[]> objlist = new ArrayList<Object[]>();
		List<Object[]> lisobj = null;
		TestResult tr = null;
		Date begin = null;
		Date end = null;
		String beginDate = elementSortService.getBeginDate().toString();
		String endDate = elementSortService.getEndDate().toString();
		if (beginDate2 != null && !beginDate2.equals("")) {
			begin = dateDate.parse("2016" + "-" + beginDate2);
		}
		if (endDate2 != null && !endDate2.equals("")) {
			end = dateDate.parse("2016" + "-" + endDate2);
		}
		if (type.equals("逐日")) {
			list = rainType2Service.getRainType2Desc(request, stationName, Integer.parseInt(beginYear),
					Integer.parseInt(endYear), begin, end);
			lisobj = new ArrayList<Object[]>();
			for (Precipitation pre : list) {
				Object[] obj = new Object[2];
				obj[0] = pre.getPreTime2020();
				obj[1] = dateDate.format(pre.getDateDate());
				lisobj.add(obj);
			}
			tr = new TestResult();
			objlist = tr.order(lisobj);
		} else {
			listObj = rainType2Service.getRainType2SumPreDesc(stationName, Integer.parseInt(beginYear),
					Integer.parseInt(endYear), begin, end);
			for (int i = 0; i < listObj.size(); i++) {
				Object[] obj = new Object[2];
				Double f = Double.parseDouble(listObj.get(i)[0].toString());
				BigDecimal b = new BigDecimal(f);
				Double f1 = b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
				obj[0] = f1;
				obj[1] = listObj.get(i)[1];
				objlist.add(obj);
			}
		}
		int count = precipitationService.getPreCount2(stationName, Integer.parseInt(beginYear),
				Integer.parseInt(endYear), begin, end);
		
		session.setAttribute("ylstationName", stationName);
		session.setAttribute("ylbeginYear", beginYear);
		session.setAttribute("ylendYear", endYear);
		session.setAttribute("ylbeginDate", beginDate2);
		session.setAttribute("ylendDate", endDate2);
		session.setAttribute("yltype", type);

		// 第一种
		model.addAttribute("beginDate", beginDate);
		model.addAttribute("endDate", endDate);
		// 个数统计
		model.addAttribute("precount", count);
		// 第二种
		model.addAttribute("objlist", objlist);
		model.addAttribute("stationNames", stationNames);
		model.addAttribute("stationName", stationName);
		model.addAttribute("beginYears", beginYears);
		model.addAttribute("endYears", endYears);
		model.addAttribute("beginYear", beginYear);
		model.addAttribute("endYear", endYear);
		model.addAttribute("beginDate2", beginDate2);
		model.addAttribute("endDate2", endDate2);
		model.addAttribute("type", type);
		model.addAttribute("chaxunresult", chaxunresult);
		return "rainTypePre_2020";
	}

	@RequestMapping("getHighchartRainType2.shtml")
	@ResponseBody
	public String getHighchartRainType2(HttpSession session, HttpServletRequest request) throws ParseException {
		SimpleDateFormat dateDate = new SimpleDateFormat("yyyy-MM-dd");
		Map<String, Object> map = new HashMap<String, Object>();
		List<Precipitation> list = null;
		List<Object[]> listObj = null;
		List<Object[]> objlist = new ArrayList<Object[]>();
		List<Object[]> lisobj = null;
		TestResult tr = null;
		Date begin = null;
		Date end = null;
		String stationName = session.getAttribute("ylstationName").toString();
		String beginYear = session.getAttribute("ylbeginYear").toString();
		String endYear = session.getAttribute("ylendYear").toString();
		String beginDate2 = session.getAttribute("ylbeginDate").toString();
		String endDate2 = session.getAttribute("ylendDate").toString();
		String type = session.getAttribute("yltype").toString();
		begin = dateDate.parse("2016" + "-" + beginDate2);
		end = dateDate.parse("2016" + "-" + endDate2);
		if (type.equals("逐日")) {
			list = rainType2Service.getRainType2Desc(request, stationName, Integer.parseInt(beginYear),
					Integer.parseInt(endYear), begin, end);
			lisobj = new ArrayList<Object[]>();
			for (Precipitation pre : list) {
				Object[] obj = new Object[2];
				obj[0] = pre.getPreTime2020();
				obj[1] = dateDate.format(pre.getDateDate());
				lisobj.add(obj);
			}
			tr = new TestResult();
			objlist = tr.order(lisobj);
		} else {
			listObj = rainType2Service.getRainType2SumPreDesc(stationName, Integer.parseInt(beginYear),
					Integer.parseInt(endYear), begin, end);
			for (int i = 0; i < listObj.size(); i++) {
				Object[] obj = new Object[2];
				Double f = Double.parseDouble(listObj.get(i)[0].toString());
				BigDecimal b = new BigDecimal(f);
				Double f1 = b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
				obj[0] = f1;
				obj[1] = listObj.get(i)[1];
				objlist.add(obj);
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

	@RequestMapping("getHighchartPre2020.shtml")
	@ResponseBody
	public String getHighchartPre20202(HttpSession session, HttpServletRequest request) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Map<String, Object> map = new HashMap<String, Object>();
		List<Object[]> preList = null;
		List<ElementsTem> list = null;
		// 日降水量（20-20）
		String typestationName = session.getAttribute("typestationName").toString();
		String typebeginDate = session.getAttribute("typebeginDate").toString();
		String typeendDate = session.getAttribute("typeendDate").toString();
		Date typebegin = sdf.parse(typebeginDate);
		int typebeginYear = typebegin.getYear() + 1900;
		Date typeend = sdf.parse(typeendDate);
		int typeendYear = typeend.getYear() + 1900;
		list = new ArrayList<ElementsTem>();
		preList = elementSortService.getElementsHighcharts(typestationName, typebeginYear, typeendYear);
		for (int i = 0; i < preList.size(); i++) {
			ElementsTem t = new ElementsTem();
			t.setTemNum(Double.parseDouble(preList.get(i)[0].toString()));
			t.setTemYear(preList.get(i)[1].toString());
			list.add(t);
		}
		map.put("list", list);
		String s = new Gson().toJson(map);
		String name = s.replaceAll(".*\"list\":", "").replace("]}", "]");
		return name;
	}

	@RequestMapping("getHighchartPre20202.shtml")
	@ResponseBody
	public String getHighchartPre2020(HttpSession session, HttpServletRequest request) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Map<String, Object> map = new HashMap<String, Object>();
		Date begin = null;
		Date end = null;
		List<Object[]> objlist = new ArrayList<Object[]>();
		List<Precipitation> preList = null;
		List<Object[]> lisobj = null;
		TestResult tr = null;
		String stationName = session.getAttribute("typestationName").toString();
		String beginDate = session.getAttribute("typebeginDate").toString();
		String endDate = session.getAttribute("typeendDate").toString();
		begin = sdf.parse(beginDate);
		end = sdf.parse(endDate);
		preList = elementSortService.getElementInfos(request, stationName, begin, end);
		lisobj = new ArrayList<Object[]>();
		for (Precipitation pr : preList) {
			Object[] obj = new Object[2];
			Double f = Double.parseDouble(pr.getPreTime2020().toString());
			BigDecimal bd = new BigDecimal(f);
			Double f1 = bd.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
			obj[0] = f1;
			obj[1] = sdf.format(pr.getDateDate());
			lisobj.add(obj);
		}
		tr = new TestResult();
		objlist = tr.order(lisobj);
		if (!(objlist.size() < 10)) {
			objlist = objlist.subList(0, 10);
		}
		map.put("list", objlist);
		String s = new Gson().toJson(map);
		String name = s.replaceAll(".*\"list\":", "").replace("]}", "]");
		return name;
	}

	@RequestMapping(value = "exportRainType1.shtml")
	public void exportRainType1(HttpServletResponse response, String stationName, String year, String beginDate,
			String endDate) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<Precipitation> list = null;
		List<Object[]> objlist = new ArrayList<Object[]>();
		List<Object[]> lisobj = null;
		TestResult tr = null;
		String begin = "";
		String end = "";
		if (beginDate != null && !beginDate.equals("")) {
			begin = year + "-" + beginDate;
		}
		if (endDate != null && !endDate.equals("")) {
			end = year + "-" + endDate;
		}
		list = rainType1Service.getRainType1(stationName, begin, end);
		lisobj = new ArrayList<Object[]>();
		for (Precipitation pre : list) {
			Object[] obj = new Object[2];
			obj[0] = pre.getPreTime2020();
			obj[1] = sdf.format(pre.getDateDate());
			lisobj.add(obj);
		}
		tr = new TestResult();
		objlist = tr.order(lisobj);
		if (!(objlist.size() < 10)) {
			objlist = objlist.subList(0, 10);
		}
		String fileName = Tool.dateFormat(new Date(), "yyyyMMddHHmmss");
		HSSFWorkbook wb = rainType1Service.exportRainType1(objlist);
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xls");
		OutputStream ouputStream = response.getOutputStream();
		wb.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();
	}

	@RequestMapping(value = "exportRainType2.shtml")
	public void exportRainType2(HttpServletResponse response, String stationName, String beginYear, String endYear,
			String beginDate2, String endDate2, String type) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<Precipitation> list = null;
		List<Object[]> objlist = new ArrayList<Object[]>();
		List<Object[]> lisobj = null;
		TestResult tr = null;
		String begin = "";
		String end = "";
		Date b = null;
		Date e = null;
		String fileName = "";
		HSSFWorkbook wb = null;
		if (beginDate2 != null && !beginDate2.equals("")) {
			begin = "2016" + "-" + beginDate2;
		}
		if (endDate2 != null && !endDate2.equals("")) {
			end = "2016" + "-" + endDate2;
		}
		b = sdf.parse(begin);
		e = sdf.parse(end);
		if (type.equals("逐日")) {
			list = rainType2Service.getRainType2(stationName, Integer.parseInt(beginYear), Integer.parseInt(endYear),
					begin, end);
			lisobj = new ArrayList<Object[]>();
			for (Precipitation pre : list) {
				Object[] obj = new Object[2];
				obj[0] = pre.getPreTime2020();
				obj[1] = sdf.format(pre.getDateDate());
				lisobj.add(obj);
			}
			tr = new TestResult();
			objlist = tr.order(lisobj);
			fileName = Tool.dateFormat(new Date(), "yyyyMMddHHmmss");
			if (!(objlist.size() < 10)) {
				objlist = objlist.subList(0, 10);
			}
			wb = rainType2Service.exportRainType2(objlist);
		} else {
			lisobj = rainType2Service.getRainType2SumPreDesc(stationName, Integer.parseInt(beginYear),
					Integer.parseInt(endYear), b, e);
			for (int i = 0; i < lisobj.size(); i++) {
				Object[] obj = new Object[2];
				Double f = Double.parseDouble(lisobj.get(i)[0].toString());
				BigDecimal bd = new BigDecimal(f);
				Double f1 = bd.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
				obj[0] = f1;
				obj[1] = lisobj.get(i)[1];
				objlist.add(obj);
			}
			fileName = Tool.dateFormat(new Date(), "yyyyMMddHHmmss");
			if (!(objlist.size() < 10)) {
				objlist = objlist.subList(0, 10);
			}
			wb = rainType2Service.exportRainType2SumPre(objlist);
		}
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xls");
		OutputStream ouputStream = response.getOutputStream();
		wb.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();
	}

	@RequestMapping("exportRainTypePre2020.shtml")
	public void exportRainTypePre2020(HttpServletResponse response, String stationName, String beginDate,
			String endDate) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<Precipitation> preList = null;
		List<Object[]> objlist = new ArrayList<Object[]>();
		List<Object[]> lisobj = null;
		TestResult tr = null;
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
		if (!(objlist.size() < 10))

		{
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
}
