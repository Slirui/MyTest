/**
 * 
 */
package com.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entity.Precipitation;
import com.service.ElementSortService;
import com.service.RainType1Service;
import com.service.RainTypeYearService;

/**
 * @author 分裂状态。
 *
 */
@Controller
public class RainType1Control {

	@Autowired
	private ElementSortService elementSortService;

	@Autowired
	private RainTypeYearService rainTypeYearService;

	@Autowired
	private RainType1Service rainType1Service;

	@RequestMapping("getRainType1.shtml")
	public String getRainType1(HttpServletRequest request, Model model, String stationName, String year,
			String beginDate, String endDate, String orderby) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String page = "";
		Date begin = null;
		Date end = null;
		Integer nian = null;
		List<String> stationNames = elementSortService.getStationName();
		List<String> years = rainTypeYearService.getYear();
		List<Precipitation> list = null;
		if (stationName == null) {
			stationName = "徐家汇";
		}
		if (year == null) {
			year = "2016";
		}
		if (beginDate == null) {
			beginDate = "03-01";
		}
		if (endDate == null) {
			endDate = "05-10";
		}
		if (beginDate != null && !beginDate.equals("")) {
			begin = sdf.parse(year + "-" + beginDate);
		}
		if (endDate != null && !endDate.equals("")) {
			end = sdf.parse(year + "-" + endDate);
		}
		if (orderby == null || (orderby.equals("desc") && orderby != null) || orderby.equals("")) {
			list = rainType1Service.getRainType1Desc(request, stationName, begin, end);
		}
		if (orderby != null && orderby.equals("asc")) {
			list = rainType1Service.getRainType1Asc(request, stationName, begin, end);
		}
		page = "rainType1";
		/*
		 * if (beginDate.equals("") && endDate.equals("")) { nian =
		 * Integer.parseInt(year); if (orderby == null ||
		 * (orderby.equals("desc") && orderby != null)) { list =
		 * rainType1Service.getRainType1NoDateDesc(request, stationName, nian);
		 * } if (orderby != null && orderby.equals("asc")) { } list =
		 * rainType1Service.getRainType1NoDateAsc(request, stationName, nian);
		 * page = "rainType1NoDate"; }
		 */
		model.addAttribute("list", list);
		model.addAttribute("stationNames", stationNames);
		model.addAttribute("stationName", stationName);
		model.addAttribute("years", years);
		model.addAttribute("year", year);
		model.addAttribute("beginDate", beginDate);
		model.addAttribute("endDate", endDate);
		model.addAttribute("begin", begin);
		model.addAttribute("end", end);
		model.addAttribute("orderby", orderby);
		return page;
	}
}
