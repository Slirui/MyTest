/**
 * 
 */
package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entity.SiteMonthAvg;
import com.service.SiteMonthAvgService;

/**
 * 
 * @author 分裂状态。
 *
 */
@Controller
public class SiteMonthAvgControl {

	@Autowired
	private SiteMonthAvgService dataQueryService;

	// @RequestMapping("getSiteName.shtml")
	// public String getSiteName(Model model, String site) {
	// List<String> siteNames = dataQueryService.getSite();
	// if (site == null) {
	// site = "徐家汇";
	// }
	// // list = dataQueryService.getResultByName(site);
	// model.addAttribute("siteNames", siteNames);
	// model.addAttribute("site", site);
	// return "siteMonthAvg";
	// }

	@RequestMapping("getInfo.shtml")
	public String getInfo(Model model, String site) {
		List<String> siteNames = dataQueryService.getSite();
		List<SiteMonthAvg> list = null;
		if (site == null) {
			site = "徐家汇";
		}
		list = dataQueryService.getResultByName(site);
		model.addAttribute("list", list);
		model.addAttribute("site", site);
		model.addAttribute("siteNames", siteNames);
		return "index";
	}
}
