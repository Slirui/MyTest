/**
 * 
 */
package com.controller;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.FileOperate;
import com.entity.Warn;
import com.google.gson.Gson;
import com.util.FileComparator;
import com.util.FileParse;

/**
 * @author 分裂状态。
 *
 */
@Controller
public class TypicalCaseControl {

	@SuppressWarnings("static-access")
	@RequestMapping("getFiles.shtml")
	public String getFiles(HttpSession session, HttpServletRequest request, Model model, String node, String root)
			throws UnsupportedEncodingException {
		String path = "";
		String zjcl = "";
		String pscl = "";
		if (node != null && root != null) {
			path = request.getServletContext().getRealPath("/doc" + root + "/" + node);
			zjcl = 服务经验(path + "/服务经验.txt");
			pscl = 批示材料(path + "/批示材料.txt");
			session.setAttribute("root", root);
		}
		if (node != null && root == null) {
			path = request.getServletContext().getRealPath("/doc/" + node);
			zjcl = 服务经验(path + "/服务经验.txt");
			pscl = 批示材料(path + "/批示材料.txt");
			session.removeAttribute("root");
		}
		session.setAttribute("path", path);
		session.setAttribute("node", node);
		List<FileOperate> zb = new ArrayList<FileOperate>();
		List<FileOperate> kb = new ArrayList<FileOperate>();
		// Map<String, Map<String, Warn>> yj = new HashMap<String, Map<String,
		// Warn>>();
		Map<String, ArrayList<Warn>> yj = new TreeMap<String, ArrayList<Warn>>();
		List<FileOperate> fwzj = new ArrayList<FileOperate>();
		// List<String> other = new ArrayList<String>();
		List<FileOperate> other = new ArrayList<FileOperate>();
		File file = new File(path);
		File[] tempList = file.listFiles();
		FileComparator comp = new FileComparator();
		FileParse fp = new FileParse(request);
		Arrays.sort(tempList);
		for (File f : tempList) {
			if (f.getName().startsWith("zb")) {
				zb.add(fp.parse(f));
				// 调用排序方法
				Collections.sort(zb, comp);
			} else if (f.getName().startsWith("kb")) {
				kb.add(fp.parse(f));
				// 调用排序方法
				Collections.sort(kb, comp);
			} else if (f.getName().startsWith("yj") && f.getName().endsWith(".txt")) {
				// fp.getType(yj, f);
				fp.getType2(yj, f);
			} else if (f.getName().startsWith("fwzj")) {
				fwzj.add(fp.parse(f));
				// 调用排序方法
				Collections.sort(fwzj, comp);
			} else {
				other.add(fp.recOther(f, request, session));
				// 调用排序方法
				Collections.sort(other, comp);
			}
		}
		model.addAttribute("zjcl", zjcl);
		model.addAttribute("pscl", pscl);
		model.addAttribute("zb", zb);
		model.addAttribute("kb", kb);
		model.addAttribute("yj", yj);
		model.addAttribute("fwzj", fwzj);
		model.addAttribute("other", other);
		model.addAttribute("title", node);
		return "chanpin";

	}

	@RequestMapping("editDxalZjcl.shtml")
	public String editZjcl(Model model, HttpSession session, String name) {
		String path = session.getAttribute("path").toString();
		File file = new File(path);
		String zjName = "";
		for (File s : file.getParentFile().listFiles()) {
			if (s.getName().contains("服务经验")) {
				zjName = s.getName();
				session.setAttribute("zjName", zjName);
			}
		}
		String zjcl = 服务经验(path + "/" + zjName);
		String dxal = name;
		session.setAttribute("dxal", dxal);
		model.addAttribute("zjcl", zjcl);
		return "dxaltexteditzj";
	}

	@RequestMapping("editDxalPscl.shtml")
	public String editPscl(Model model, HttpSession session, String name) {
		String path = session.getAttribute("path").toString();
		File file = new File(path);
		String psName = "";
		for (File s : file.listFiles()) {
			if (s.getName().contains("批示材料")) {
				psName = s.getName();
				session.setAttribute("psName", psName);
			}
		}
		String pscl = 批示材料(path + "/" + psName);
		String dxal = name;
		session.setAttribute("dxal", dxal);
		model.addAttribute("pscl", pscl);
		return "dxaltexteditps";
	}

	@RequestMapping("saveDxalZjcl.shtml")
	public String saveZjcl(Model model, HttpSession session) throws UnsupportedEncodingException {
		String path = session.getAttribute("path").toString();
		// String zjpath = session.getAttribute("zjpath").toString();
		String zjName = session.getAttribute("zjName").toString();
		String content = session.getAttribute("result").toString();
		String node = session.getAttribute("node").toString();
		String root = "";
		if (session.getAttribute("root") != null && !session.getAttribute("root").equals("")) {
			root = session.getAttribute("root").toString();
		}
		OutputStream os = null;
		try {
			// 1、指明路径，创建文件对象
			// File myFile = new File(path + "/_" + new Date() + "_服务经验.txt");
			File myFile = new File(path + "/" + zjName);
			// 2、创建字节输出流
			os = new FileOutputStream(myFile);
			// 3、输出流写内容
			os.write(content.getBytes("GBK"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		node = java.net.URLEncoder.encode(node, "UTF-8");
		root = java.net.URLEncoder.encode(root, "UTF-8");
		if (node != null && !node.equals("") && root != null && !root.equals("")) {
			return "redirect:/getFiles.shtml?node=" + node + "&root=" + root;
		} else {
			return "redirect:/getFiles.shtml?node=" + node;
		}

	}

	@RequestMapping("saveDxalPscl.shtml")
	public String savePscl(Model model, HttpSession session) throws UnsupportedEncodingException {
		String path = session.getAttribute("path").toString();
		// String pspath = session.getAttribute("pspath").toString();
		String psName = session.getAttribute("psName").toString();
		String content = session.getAttribute("result").toString();
		String node = session.getAttribute("node").toString();
		String root = "";
		if (session.getAttribute("root") != null && !session.getAttribute("root").equals("")) {
			root = session.getAttribute("root").toString();
		}
		OutputStream os = null;
		try {
			// 1、指明路径，创建文件对象
			// File myFile = new File(path + "/批示材料.txt");
			File myFile = new File(path + "/" + psName);
			// 2、创建字节输出流
			os = new FileOutputStream(myFile);
			// 3、输出流写内容
			os.write(content.getBytes("GBK"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		node = java.net.URLEncoder.encode(node, "UTF-8");
		root = java.net.URLEncoder.encode(root, "UTF-8");
		if (node != null && !node.equals("") && root != null && !root.equals("")) {
			return "redirect:/getFiles.shtml?node=" + node + "&root=" + root;
		} else {
			return "redirect:/getFiles.shtml?node=" + node;
		}
	}

	// 服务经验
	public String 服务经验(String path) {
		try {
			File myFile = new File(path);
			for (File s : myFile.getParentFile().listFiles()) {
				if (s.getName().contains("服务经验.txt")) {
					return FileUtils.readFileToString(s, "GBK");
				}
			}
			if (!myFile.exists()) {
				myFile.createNewFile();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	// 批示材料
	public String 批示材料(String path) {
		try {
			File myFile = new File(path);
			for (File s : myFile.getParentFile().listFiles()) {
				if (s.getName().contains("批示材料.txt")) {
					return FileUtils.readFileToString(s, "GBK");
				}
			}
			if (!myFile.exists()) {
				myFile.createNewFile();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	public TreeNode getTreeNode(File[] files, TreeNode tNode) {
		if (files.length == 0) {
			return null;
		} else {
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					TreeNode cNode = new TreeNode(i, files[i].getName().toString(), files[i].getAbsolutePath());
					tNode.children.add(cNode);
					tNode.attributes = false;
					getTreeNode(files[i].listFiles(new FileFilter() {
						@Override
						public boolean accept(File pathname) {
							return pathname.isDirectory();
						}
					}), cNode);
				}
				// if (files[i].isFile()) {
				// TreeNode cNode = new TreeNode(i,
				// files[i].getName().toString(), files[i].getAbsolutePath());
				// tNode.children.add(cNode);
				// tNode.attributes = false;
				// }
			}
		}
		return tNode;

	}

	@RequestMapping("getFolders.shtml")
	@ResponseBody
	public String ds(HttpServletRequest request, HttpSession session) {
		session.removeAttribute("nodeId");
		String path = request.getServletContext().getRealPath("/doc");
		File file = new File(path);
		File[] files = file.listFiles();
		TreeNode tNode = new TreeNode();
		getTreeNode(files, tNode);
		return new Gson().toJson(tNode.children);
	}

	@RequestMapping("saveDxalUrlParam.shtml")
	@ResponseBody
	public Map<String, Object> saveUrlParam(HttpSession session, String contents) {
		Map<String, Object> map = new HashMap<String, Object>();
		String dxal = session.getAttribute("dxal").toString();
		Integer z = Integer.parseInt(dxal);
		String zjorps = "";
		if (z == 0) {
			zjorps = "服务经验";
		} else {
			zjorps = "批示材料";
		}
		String result = contents;
		session.setAttribute("result", result);
		map.put("result", result);
		map.put("zjorps", zjorps);
		return map;
	}

	@RequestMapping("deleteDxalFileName.shtml")
	public String deleteDxalFileName(HttpSession session, String name) throws UnsupportedEncodingException {
		String path = session.getAttribute("path").toString();
		String node = session.getAttribute("node").toString();
		String root = "";
		if (session.getAttribute("root") != null && !session.getAttribute("root").equals("")) {
			root = session.getAttribute("root").toString();
		}
		deleteDxalFileName(path + "/" + name);
		node = java.net.URLEncoder.encode(node, "UTF-8");
		root = java.net.URLEncoder.encode(root, "UTF-8");
		if (node != null && !node.equals("") && root != null && !root.equals("")) {
			return "redirect:/getFiles.shtml?node=" + node + "&root=" + root;
		} else {
			return "redirect:/getFiles.shtml?node=" + node;
		}
	}

	@RequestMapping("getDxalFolder.shtml")
	public String copyDxalFile(HttpServletRequest request) {
		String path = request.getServletContext().getRealPath("/doc");
		copyDxalFile(path);
		return "folder";
	}

	public void copyDxalFile(String path) {
		File path1 = new File("E:\\典型案例");
		File path2 = new File(path);
		try {
			FileUtils.copyDirectory(path1, path2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteDxalFileName(String path) {
		File file = new File(path);
		if (file.exists()) {
			file.delete();
		}
	}

	// HashMap<String, TreeNode> treeMap = new HashMap<String, TreeNode>();
	// LinkedHashMap<String, String> treeIdMap = new LinkedHashMap<String,
	// String>();
	// public static Integer nodeId = 0;

	// 构建的树节点
	public class TreeNode {

		public TreeNode() {
			children = new ArrayList<TreeNode>();
		}

		public TreeNode(Integer id, String text, String filePath) {
			this.id = filePath.hashCode();
			this.text = text;
			this.filePath = filePath;
			children = new ArrayList<TreeNode>();
			// treeMap.put(filePath, this);
			// treeIdMap.put(this.id.toString(), filePath);
		}

		public boolean attributes = true;
		public Integer id;// 绑定到节点的标识值
		public String text;// 显示的文字
		public String filePath;
		public List<TreeNode> children;// 定义了一些子节点的节点数组
	}

}
