/**
 * 
 */
package com.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.Holiday;
import com.google.gson.Gson;
import com.service.HolidayService;

/**
 * @author 分裂状态。
 *
 */
@Controller
public class HolidayControl {

	@Autowired
	private HolidayService holidayService;

	@RequestMapping("getHolidayTitle.shtml")
	@ResponseBody
	public String getHolidayTitle(Model model) {
		List<TreeNode> list = AddChildNode();
		return new Gson().toJson(list);
		// List<String> holidayTitle = holidayService.getHolidayTitle();
		// model.addAttribute("holidayTitle", holidayTitle);
		// return "holidayTitle";
	}

//	@RequestMapping("getHolidayTitle.shtml")
//	public String getHolidayTitle(Model model) {
//		List<String> holidayTitle = holidayService.getHolidayTitle();
//		model.addAttribute("holidayTitle", holidayTitle);
//		return "holidayTitle";
//	}

	public List<TreeNode> AddChildNode() {
		List<String> holidayTitle = holidayService.getHolidayTitle();
		// Map<String, TreeNode> map = new HashMap<String, TreeNode>();
		List<TreeNode> nodes = new ArrayList<TreeNode>();
		for (int i = 0; i < holidayTitle.size(); i++) {
			TreeNode cNode = new TreeNode(i, holidayTitle.get(i));
			nodes.add(cNode);
		}
		List<TreeNode> root = new ArrayList<TreeNode>();
		TreeNode td = new TreeNode(holidayTitle.size() + 1, "国定假日");
		root.add(td);
		td.children = nodes;
		return root;
	}

	@RequestMapping("getHoliday.shtml")
	public String getHoliday(HttpSession session, Model model, String title, String content) {
		List<Holiday> holiday = null;
		if (title == null) {
			title = "春节";
		}
		session.setAttribute("title", title);
		holiday = holidayService.getHolidayByTitle(title);

		List<Holiday> holidays = new ArrayList<Holiday>();
		if (content != null && !content.equals("")) {
			holiday = holidayService.getHoliday("%" + content + "%");
			if (holiday.size() > 0) {
				for (Holiday day : holiday) {
					if (day.getContents().contains(content)) {
						String contents = "<span style='background-color:red;'>" + content + "</span>";
						day.setContents(day.getContents().replace(content, contents));
						holidays.add(day);
					} else {
						holidays.add(day);
					}
				}
			}
			holiday = holidays;
		}
		model.addAttribute("holiday", holiday);
		model.addAttribute("content", content);
		model.addAttribute("title", title);
		return "holiday2";
	}

	@RequestMapping("getHolidayEditInfo.shtml")
	public String getEditInfo(HttpSession session, Model model) {
		String title = session.getAttribute("title").toString();
		Holiday holiday = holidayService.getHolidayInfoByTitle(title);
		model.addAttribute("holiday", holiday);
		return "editHoliday";
	}

	@RequestMapping("updateHoliday.shtml")
	public String updateHoliday(HttpSession session) throws UnsupportedEncodingException {
		String title = session.getAttribute("title").toString();
		String contents = session.getAttribute("result").toString();
		holidayService.updateContent(contents, title);
		title = java.net.URLEncoder.encode(title, "UTF-8");
		return "redirect:/getHoliday.shtml?title=" + title;
	}

	@RequestMapping("saveHolidayUrlParam.shtml")
	@ResponseBody
	public Map<String, Object> saveUrlParam(HttpSession session, String contents) {
		Map<String, Object> map = new HashMap<String, Object>();
		String result = contents;
		session.setAttribute("result", result);
		map.put("result", result);
		return map;
	}

	// 构建的树节点
	public class TreeNode {
		public TreeNode() {
			children = new ArrayList<TreeNode>();
		}

		public TreeNode(Integer id, String text) {
			this.id = id;
			this.text = text;
			children = new ArrayList<TreeNode>();
		}

		public Integer id; // 绑定到节点的标识值
		public String text;// 显示的文字
		public List<TreeNode> children;// 定义了一些子节点的节点数组
	}

}
