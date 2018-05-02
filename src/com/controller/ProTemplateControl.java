/**
 * 
 */
package com.controller;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

/**
 * @author 分裂状态。
 *
 */
@Controller
public class ProTemplateControl {

	@RequestMapping("cpmbFolder.shtml")
	@ResponseBody
	public String ds(HttpServletRequest request, HttpSession session) {
		String path = request.getServletContext().getRealPath("/产品模板");
		File file = new File(path);
		File[] files = file.listFiles();
		TreeNode tNode = new TreeNode();
		getTreeNode(files, tNode);
		return new Gson().toJson(tNode.children);
	}

	@RequestMapping("cpmbFiles.shtml")
	public String getFiles(HttpSession session, HttpServletRequest request, Model model, String node, String root,
			String nodes) {
		String path = "";
		if (root == null) {
			root = "常用表格";
		}
		if (node == null) {
			node = "台风动态信息统计表";
		}
		if (nodes == null) {
			nodes = "2013年10月6日1323号台风动态.docx";
		}
		if (node != null && root != null) {
			path = request.getServletContext().getRealPath("/产品模板/" + root + "/" + node);
			File file = new File(path + "/" + nodes);
			if (!file.exists() && (nodes.endsWith(".docx"))) {
				nodes = nodes.replace(".docx", ".doc");
				file = new File(path + "/" + nodes);
				if(!file.exists() && (nodes.endsWith(".doc"))){
					nodes = nodes.replace(".doc", ".docx");
					file = new File(path + "/" + nodes);
					if(!file.exists() && (nodes.endsWith(".docx"))){
						nodes = nodes.replace(".docx", ".xlsx");
						file = new File(path + "/" + nodes);
						if(!file.exists() && (nodes.endsWith(".xlsx"))){
							nodes = nodes.replace(".xlsx", ".xls");
							file = new File(path + "/" + nodes);
							if(!file.exists()){
								nodes = nodes.substring(0,nodes.indexOf("."));
								nodes = nodes + ".jpg";
								//file = new File(path + "/" + nodes);
							}
						}
					}
				}
			}
			session.setAttribute("root", root);
		}
		if (node != null && root == null) {
			path = request.getServletContext().getRealPath("/产品模板/" + node);
			session.removeAttribute("root");
		}
		session.setAttribute("path", path);
		session.setAttribute("node", node);
		session.setAttribute("name", nodes);
		// model.addAttribute("fileNames", fileNames);
		return "cpmbpreview";
	}

	public TreeNode getTreeNode(File[] files, TreeNode tNode) {
		if (files.length == 0) {
			return null;
		} else {
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					TreeNode cNode = new TreeNode(i, files[i].getName().toString(), "文件夹");
					tNode.children.add(cNode);
					getTreeNode(files[i].listFiles(new FileFilter() {
						@Override
						public boolean accept(File pathname) {
							// return pathname.isDirectory();
							return true;
						}
					}), cNode);
				}
				if (files[i].isFile()) {
					TreeNode cNode = new TreeNode(i,
							files[i].getName().toString().contains(".")
									? (files[i].getName().substring(0, files[i].getName().indexOf(".")).toString())
									: files[i].getName().toString(),
							"文件");
					// TreeNode cNode = new TreeNode(i,
					// files[i].getName().toString(), "文件");
					tNode.children.add(cNode);
				}
			}
		}
		return tNode;

	}

	// 构建的树节点
	public class TreeNode {
		public TreeNode() {
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
		public List<TreeNode> children;// 定义了一些子节点的节点数组
	}
}
