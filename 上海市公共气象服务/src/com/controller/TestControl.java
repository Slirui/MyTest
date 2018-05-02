/**
 * 
 */
package com.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entity.Test;
import com.service.TestService;

/**
 * 
 * @author 分裂状态。
 *
 */
@Controller
public class TestControl {

	@Autowired
	private TestService service;

	@RequestMapping("getTest.shtml")
	public String test(Model model) {
		List<Object[]> results = service.getTemDayCount();
		Map<Integer, Test> map = new HashMap<Integer, Test>();
		List<Test> list = new ArrayList<Test>();
		Test ts = null;
		for (int i = 0; i < results.size(); i++) {
			ts = new Test();
			int year = Integer.parseInt(results.get(i)[1].toString());
			ts.setMaxYear(year);
			if (!map.containsKey(year)) {
				ts = map.get(year);
				int month = Integer.parseInt(results.get(i)[2].toString());
				int value = Integer.parseInt(results.get(i)[0].toString());
				map.put(year, ts);
				System.out.println("年：" + map.get(year) + "\t月：" + month + "\t日：" + value);
			} else {
				int month = Integer.parseInt(results.get(i)[2].toString());
				int value = Integer.parseInt(results.get(i)[0].toString());
				map.get(year).setMonthValue(month, value); // 这是原来的
				map.put(year, ts);//
			}

		}
		model.addAttribute("map", map);

		return "test";
	}

	@RequestMapping("getTestInfo.shtml")
	public String testInfo(Model model) {
		List<Object[]> counts = service.getTemDayCounts();
		List<Object[]> results = service.getTemDayCount();
		Map<Integer, Test> map = new HashMap<Integer, Test>();
		List<Test> list = new ArrayList<Test>();
		Test ts = null;
		for (int i = 0; i < results.size(); i++) {
			ts = new Test();
			int year = Integer.parseInt(results.get(i)[1].toString());
			ts.setMaxYear(year);
			if (!map.containsKey(year)) {
				map.put(year, ts);
			}
			int month = Integer.parseInt(results.get(i)[2].toString());
			int value = Integer.parseInt(results.get(i)[0].toString());
			map.get(year).setMonthValue(month, value);
		}
		for (int i = 0; i < counts.size(); i++) {
			ts = map.get(Integer.parseInt(counts.get(i)[1].toString()));
			int num = Integer.parseInt(counts.get(i)[0].toString());
			ts.setMaxSumDay(num);
			list.add(ts);
		}

		model.addAttribute("list", list);
		return "test";

		/*
		 * for (Integer i : map.keySet()) {
		 * System.out.println(map.get(i).getMaxYear() + "年" + "\t五月：" +
		 * map.get(i).getMaxMay() + "天" + "\t六月：" + map.get(i).getMaxJune() +
		 * "天" + "\t七月：" + map.get(i).getMaxJuly() + "天" + "\t八月：" +
		 * map.get(i).getMaxAugust() + "天" + "\t九月：" + map.get(i).getMaxSep() +
		 * "天"); }
		 */

	}
}
