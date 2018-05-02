/**
 * 
 */
package com.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entity.Precipitation;
import com.service.ElementSortService;
import com.service.RainType2Service;
import com.service.RainTypeYearService;

/**
 * @author 分裂状态。
 *
 */
@Controller
public class RainType2Control {

	@Autowired
	private ElementSortService elementSortService;

	@Autowired
	private RainTypeYearService rainTypeYearService;

	@Autowired
	private RainType2Service rainType2Service;

	@RequestMapping("getRainType2.shtml")
	public String getRainType1(HttpServletRequest request, Model model, String stationName, String beginYear,
			String endYear, String beginDate, String orderby) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
		Calendar cal = Calendar.getInstance();
		Integer month = null;
		List<String> stationNames = elementSortService.getStationName();
		List<Precipitation> list = null;
		if (stationName == null) {
			stationName = "徐家汇";
		}
		if (beginYear == null) {
			beginYear = "1999";
		}
		if (endYear == null) {
			endYear = "2016";
		}
		if (beginDate == null) {
			beginDate = "03-10";
		}
		if (beginDate != null && !beginDate.equals("")) {
			Date date = sdf.parse(beginDate);
			cal.setTime(date);
			month = cal.get(Calendar.MONTH) + 1;
		}
		List<String> beginYears = rainTypeYearService.getYear();
		List<String> endYears = rainTypeYearService.getYear();
		if (orderby == null || (orderby.equals("desc") && orderby != null) || orderby.equals("")) {
			//list = rainType2Service.getRainType2Desc(request, stationName, Integer.parseInt(beginYear),
			//		Integer.parseInt(endYear), month);
		}
		if (orderby != null && orderby.equals("asc")) {
			//list = rainType2Service.getRainType2Asc(request, stationName, Integer.parseInt(beginYear),
			//		Integer.parseInt(endYear), month);
		}
		model.addAttribute("list", list);
		model.addAttribute("stationNames", stationNames);
		model.addAttribute("stationName", stationName);
		model.addAttribute("beginYears", beginYears);
		model.addAttribute("endYears", endYears);
		model.addAttribute("beginYear", beginYear);
		model.addAttribute("endYear", endYear);
		model.addAttribute("beginDate", beginDate);
		model.addAttribute("month", month);
		model.addAttribute("orderby", orderby);
		return "rainType2";
	}
}
