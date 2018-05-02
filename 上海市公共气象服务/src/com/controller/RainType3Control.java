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

import com.service.ElementSortService;
import com.service.RainType3Service;
import com.service.RainTypeYearService;

/**
 * @author 分裂状态。
 *
 */
@Controller
public class RainType3Control {

	@Autowired
	private ElementSortService elementSortService;

	@Autowired
	private RainTypeYearService rainTypeYearService;

	@Autowired
	private RainType3Service rainType3Service;

	@RequestMapping("getRainType3.shtml")
	public String getRainType1(HttpServletRequest request, Model model, String stationName, String beginYear,
			String endYear, String beginDate, String endDate, String type, String orderby) throws ParseException {
		String col1 = "";
		String col2 = "";
		List<String> stationNames = elementSortService.getStationName();
		List<Object[]> list = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date begin = null;
		Date end = null;
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
			beginDate = "03-01";
		}
		if (endDate == null) {
			endDate = "03-10";
		}
		if (type == null) {
			type = "1";
		}
		if (beginDate != null && !beginDate.equals("")) {
			begin = sdf.parse("2016" + "-" + beginDate);
		}
		if (endDate != null && !endDate.equals("")) {
			end = sdf.parse("2016" + "-" + endDate);
		}
		List<String> beginYears = rainTypeYearService.getYear();
		List<String> endYears = rainTypeYearService.getYear();
		if (orderby == null || (orderby.equals("desc") && orderby != null) || orderby.equals("")) {
			list = rainType3Service.getRainType3SumPreDesc(stationName, Integer.parseInt(beginYear),
					Integer.parseInt(endYear), begin, end);
		}
		if (orderby != null && orderby.equals("asc")) {
			list = rainType3Service.getRainType3SumPreAsc(stationName, Integer.parseInt(beginYear),
					Integer.parseInt(endYear), begin, end);
		}
		col1 = "年份";
		col2 = "累积降水量（单位：毫米）";
		if (type.equals("2")) {
			if (orderby == null || (orderby.equals("desc") && orderby != null)) {
				list = rainType3Service.getRainType3Desc(stationName, Integer.parseInt(beginYear),
						Integer.parseInt(endYear), begin, end);
			}
			if (orderby != null && orderby.equals("asc")) {
				list = rainType3Service.getRainType3Asc(stationName, Integer.parseInt(beginYear),
						Integer.parseInt(endYear), begin, end);
			}
			col1 = "日期";
			col2 = "降水量（单位：毫米）";
		}
		model.addAttribute("list", list);
		model.addAttribute("stationNames", stationNames);
		model.addAttribute("stationName", stationName);
		model.addAttribute("beginYears", beginYears);
		model.addAttribute("endYears", endYears);
		model.addAttribute("beginYear", beginYear);
		model.addAttribute("endYear", endYear);
		model.addAttribute("beginDate", beginDate);
		model.addAttribute("endDate", endDate);
		model.addAttribute("begin", begin);
		model.addAttribute("end", end);
		model.addAttribute("type", type);
		model.addAttribute("col1", col1);
		model.addAttribute("col2", col2);

		model.addAttribute("orderby", orderby);
		return "rainType3";
	}
}
