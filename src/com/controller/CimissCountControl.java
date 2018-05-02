/**
 * 
 */
package com.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.CimissCount;
import com.service.CimissCountService;

/**
 * @author 分裂状态。
 *
 */
@Controller
public class CimissCountControl {

	@Autowired
	private CimissCountService service;

	@RequestMapping("cimissCount.shtml")
	@ResponseBody
	public Map<String, List<CimissCount>> cimissCount() {
		Map<String, List<CimissCount>> map = new HashMap<String, List<CimissCount>>();
		List<CimissCount> list = service.listCimissCount();
		map.put("list", list);
		return map;
	}

	@RequestMapping("updateCimissCount.shtml")
	@ResponseBody
	public Map<String, String> updateCimissCount(String id) {
		Map<String, String> map = new HashMap<String, String>();
		service.updateCimissCount(true, Integer.parseInt(id));
		return map;
	}

}
