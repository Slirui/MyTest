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

import com.entity.ImpWeather;
import com.google.gson.Gson;
import com.service.ImpWeatherService;

/**
 * @author 分裂状态。
 *
 */
@Controller
public class ImpWeatherControl {
	@Autowired
	private ImpWeatherService impWeatherService;

	@RequestMapping("impWeatherIndex.shtml")
	public String impIndex(Model model) {
		model.addAttribute("result", "重要天气");
		return "weather";
	}

	@RequestMapping("getImpWeather.shtml")
	@ResponseBody
	public String getImpWeather(Model model) {
		List<TreeNode> list = AddChildNode();
		return new Gson().toJson(list);
	}

	/**
	 * 添加一级节点
	 * 
	 * @param impWeather
	 * @return
	 */
	@RequestMapping("addImpWeatherOneNode.shtml")
	@ResponseBody
	public Map<String, Object> addOneNode(String yiji) {
		Map<String, Object> map = new HashMap<String, Object>();
		ImpWeather impWeather = new ImpWeather();
		impWeather.setTitle(yiji);
		impWeatherService.saveNode(impWeather);
		String isOK = "成功";
		map.put("isOK", isOK);
		return map;
	}

	/**
	 * 添加二级节点
	 * 
	 * @param impWeather
	 * @return
	 */
	@RequestMapping("addImpWeatherTwoNode.shtml")
	@ResponseBody
	public Map<String, Object> addTwoNode(String check, String erji) {
		Map<String, Object> map = new HashMap<String, Object>();
		ImpWeather impWeather = new ImpWeather();
		impWeather.setTitle(check);
		impWeather.setErJi(erji);
		impWeatherService.saveNode(impWeather);
		String isOK = "成功";
		map.put("isOK", isOK);
		return map;
	}

	/**
	 * 添加三级节点
	 * 
	 * @param impWeather
	 * @return
	 */
	@RequestMapping("addImpWeatherThreeNode.shtml")
	@ResponseBody
	public Map<String, Object> addThreeNode(String check, String erji, String sanji) {
		Map<String, Object> map = new HashMap<String, Object>();
		ImpWeather impWeather = new ImpWeather();
		impWeather.setTitle(check);
		impWeather.setErJi(erji);
		impWeather.setSanJi(sanji);
		impWeatherService.saveNode(impWeather);
		String isOK = "成功";
		map.put("isOK", isOK);
		return map;
	}

	/**
	 * 修改重要天气的节点信息
	 * 
	 * @param node
	 * @param level
	 * @param id
	 * @return
	 */
	@RequestMapping("updateImpWeatherNode.shtml")
	public String updateImpWeatherNode(HttpSession session, Model model, String node, String level, String id) {
		if (level.equals("1")) {
			// impWeatherService.updateImpWeatherYiJiNode(node,
			// Integer.valueOf(id));
			impWeatherService.updateImpWeatherYiJiNodeByYiji(node, id);
		}
		if (level.equals("2")) {
			// impWeatherService.updateImpWeatherErJiNode(node,
			// Integer.valueOf(id));
			impWeatherService.updateImpWeatherErJiNodeByErji(node, id);
		}
		if (level.equals("3")) {
			// impWeatherService.updateImpWeatherSanJiNode(node,
			// Integer.valueOf(id));
			impWeatherService.updateImpWeatherSanJiNodeBySanji(node, id);
		}
		model.addAttribute("result", "重要天气");
		session.setAttribute("id", node);
		return "redirect:/weather.jsp";
	}

	@RequestMapping("deleteImpWeather.shtml")
	public String deleteImpWeather(Model model, String id, String name) {
		if (id.equals("1")) {
			impWeatherService.deleteImpWeatherByTitle(name);
		}
		if (id.equals("2")) {
			impWeatherService.deleteImpWeatherByErji(name);
		}
		if (id.equals("3")) {
			impWeatherService.deleteImpWeatherBySanji(name);
		}
		// impWeatherService.deleteNode(Integer.valueOf(id));
		model.addAttribute("result", "重要天气");
		return "redirect:/weather.jsp";
	}

	@RequestMapping("getImpWeatherContent.shtml")
	public String getImpWeatherContent(HttpSession session, Model model, String node, String content) {
		List<ImpWeather> list = null;
		List<TreeNode> treeNode = AddChildNode();
		if (node == null) {
			String node1 = "";
			if (session.getAttribute("id") != null && !session.getAttribute("id").equals("")) {
				node1 = session.getAttribute("id").toString();
				list = impWeatherService.getImpWeatherByTitle(node1);
				if (list.size() > 0) {
					node = node1;
				}
			} else {
				list = impWeatherService.getImpWeatherByTitle("大雾");
				if (list.size() > 0) {
					node = "大雾";
				}
			}
		}
		session.setAttribute("node", node);
		for (TreeNode tr : treeNode) {
			for (TreeNode nodes : tr.children) {
				if (node.equals(nodes.text)) {
					list = impWeatherService.getImpWeatherByTitle(node);
				}
			}
		}
		for (TreeNode tr : treeNode) {
			for (TreeNode nodes : tr.children) {
				for (TreeNode tNode : nodes.children) {
					if (node.equals(tNode.text)) {
						list = impWeatherService.getImpWeatherByErji(node);
					}
				}
			}
		}

		for (TreeNode tr : treeNode) {
			for (TreeNode nodes : tr.children) {
				for (TreeNode tNode : nodes.children) {
					for (TreeNode sNode : tNode.children) {
						if (node.equals(sNode.text)) {
							list = impWeatherService.getImpWeatherBySanji(node);
						}
					}
				}
			}
		}
		List<ImpWeather> listWeather = new ArrayList<ImpWeather>();
		if (content != null && !content.equals("")) {
			list = impWeatherService.getImpWeatherByLike("%" + content + "%");
			if (list.size() > 0) {
				for (ImpWeather imp : list) {
					if (imp.getContents().contains(content)) {
						String contents = "<span style='background-color:red;'>" + content + "</span>";
						imp.setContents(imp.getContents().replace(content, contents));
						listWeather.add(imp);
					} else {
						listWeather.add(imp);
					}
				}
			}
			list = listWeather;
		}
		model.addAttribute("content", content);
		model.addAttribute("list", list);
		return "impWeatherContent2";
	}

	@RequestMapping("getEditImpWeatherInfo.shtml")
	public String getEditImpWeatherInfo(HttpSession session, Model model) {
		String node = session.getAttribute("node").toString(); // 这就获取到了，保存成功之后调回到页面，
		List<ImpWeather> list = null;
		List<TreeNode> treeNode = AddChildNode();
		if (node == null) {
			node = "大雾";
		}
		for (TreeNode tr : treeNode) {
			for (TreeNode nodes : tr.children) {
				if (node.equals(nodes.text)) {
					list = impWeatherService.getImpWeatherByTitle(node);
				}
			}
		}
		for (TreeNode tr : treeNode) {
			for (TreeNode nodes : tr.children) {
				for (TreeNode tNode : nodes.children) {
					if (node.equals(tNode.text)) {
						list = impWeatherService.getImpWeatherByErji(node);
					}
				}
			}
		}
		for (TreeNode tr : treeNode) {
			for (TreeNode nodes : tr.children) {
				for (TreeNode tNode : nodes.children) {
					for (TreeNode cNode : tNode.children) {
						if (node.equals(cNode.text)) {
							list = impWeatherService.getImpWeatherBySanji(node);
						}
					}
				}
			}
		}
		model.addAttribute("list", list);
		return "editImpWeather";
	}

	@RequestMapping("updateImpWeather.shtml")
	public String updateImpWeather(HttpSession session, Model model) throws UnsupportedEncodingException {
		String node = session.getAttribute("node").toString();// 这是修改的方法
		String contents = session.getAttribute("result").toString();
		List<TreeNode> treeNode = AddChildNode();
		if (node == null) {
			node = "大雾";
		}
		for (TreeNode tr : treeNode) {
			for (TreeNode nodes : tr.children) {
				if (node.equals(nodes.text)) {
					if (contents != null && !contents.equals("")) {
						impWeatherService.updateContentByTitle(contents, node);
					} else {
						impWeatherService.updateContentByTitleIsNull(node);
					}
				}
			}
		}
		for (TreeNode tr : treeNode) {
			for (TreeNode nodes : tr.children) {
				for (TreeNode tNode : nodes.children) {
					if (node.equals(tNode.text)) {
						if (contents != null && !contents.equals("")) {
							impWeatherService.updateContentByErJi(contents, node);
						} else {
							impWeatherService.updateContentByErJiIsNull(node);
						}
					}
				}
			}
		}
		for (TreeNode tr : treeNode) {
			for (TreeNode nodes : tr.children) {
				for (TreeNode tNode : nodes.children) {
					for (TreeNode cNode : tNode.children) {
						if (node.equals(cNode.text)) {
							if (contents != null && !contents.equals("")) {
								impWeatherService.updateContentBySanJi(contents, node);
							} else {
								impWeatherService.updateContentBySanJiIsNull(node);
							}
						}
					}

				}
			}
		}
		node = java.net.URLEncoder.encode(node, "UTF-8");
		return "redirect:/getImpWeatherContent.shtml?node=" + node;
	}

	public List<TreeNode> AddChildNode() {
		List<ImpWeather> imps = impWeatherService.getImpWeather();
		Map<String, TreeNode> map = new HashMap<String, TreeNode>();
		List<TreeNode> nodes = new ArrayList<TreeNode>();
		List<TreeNode> root = new ArrayList<TreeNode>();
		TreeNode td = new TreeNode(0, "重要天气", "0", "open");
		root.add(td);
		td.children = nodes;
		// 一级
		for (ImpWeather imp : imps) {
			TreeNode cNode = new TreeNode(imp.getId(), imp.getTitle(), "1");
			if (imp.getErJi() == null) {
				map.put(imp.getTitle(), cNode);
				nodes.add(cNode);
			}
		}

		// 二级
		for (TreeNode treeNode : nodes) {
			for (ImpWeather imp : imps) {
				if (imp.getErJi() != null && imp.getSanJi() == null) {
					if (imp.getTitle().equals(treeNode.text)) {
						TreeNode cNode = new TreeNode(imp.getId(), imp.getErJi(), "2");
						treeNode.children.add(cNode);
					}
				}
			}
		}

		// 三级
		for (TreeNode no : nodes) {
			for (TreeNode treeNode : no.children) {
				for (ImpWeather imp : imps) {
					if (imp.getErJi() != null && imp.getSanJi() != null) {
						if (imp.getErJi().equals(treeNode.text)) {
							TreeNode cNode = new TreeNode(imp.getId(), imp.getSanJi(), "3");
							treeNode.children.add(cNode);
						}
					}
				}
			}
		}
		// nodes.add(new TreeNode(imp.getId(), imp.getSanJi(), "3");)
		return root;

	}

	@RequestMapping("saveImpWeatherUrlParam.shtml")
	@ResponseBody
	public Map<String, Object> saveUrlParam(HttpSession session, String contents) {
		Map<String, Object> map = new HashMap<String, Object>();
		String result = contents;
		session.setAttribute("result", result);
		map.put("result", result);
		return map;
	}

	@RequestMapping("getImpWeatherCheckedValue.shtml")
	@ResponseBody
	public Map<String, Object> getImpWeatherCheckedValue(String name) {
		Map<String, Object> map = new HashMap<String, Object>();
		String result = name;
		map.put("result", result);
		return map;
	}

	// 构建的树节点
	public class TreeNode {
		public TreeNode() {
			children = new ArrayList<TreeNode>();
		}

		public TreeNode(Integer id, String text, String attributes, String state) {
			this.id = id;
			this.text = text;
			this.attributes = attributes;
			this.state = state;
			children = new ArrayList<TreeNode>();
		}

		public TreeNode(Integer id, String text, String attributes) {
			this.id = id;
			this.text = text;
			this.attributes = attributes;
			children = new ArrayList<TreeNode>();
		}

		public Integer id; // 绑定到节点的标识值
		public String text;// 显示的文字
		public String attributes;// 节点等级
		public String state;
		public List<TreeNode> children;// 定义了一些子节点的节点数组
	}

}
