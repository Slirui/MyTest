<%@page import="java.io.IOException"%>
<%@page import="org.apache.commons.io.IOUtils"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.*,com.zhuozhengsoft.pageoffice.*"%>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po"%>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	PageOfficeCtrl poCtrl = new PageOfficeCtrl(request);
	//设置服务器页面
	poCtrl.setServerPage(request.getContextPath() + "/poserver.zz");
	//打开Word文档
	String filename = request.getParameter("name");
	String root = "";
	String node = session.getAttribute("node").toString();
	String contextpath = "";
	if (session.getAttribute("root") != null && !session.getAttribute("root").equals("")) {
		root = session.getAttribute("root").toString();
		contextpath = "doc" + root + "/" + node + "/" + filename;
	} else {
		contextpath = "doc" + "/" + node + "/" + filename;
	}
	System.out.println(contextpath);
	String uuid = UUID.randomUUID().toString().replaceAll("-", "");
	application.setAttribute(uuid, contextpath);
	System.out.println(filename);
	contextpath = path + "/downloadfile.shtml?fileName=" + uuid;
	System.out.println(contextpath);
	if (filename.endsWith(".docx") || filename.endsWith(".doc")) {
		poCtrl.webOpen(contextpath, OpenModeType.docNormalEdit, "");
	}
	if (filename.endsWith(".xlsx") || filename.endsWith(".xls")) {
		poCtrl.webOpen(contextpath, OpenModeType.docNormalEdit, "");
	}
	poCtrl.setTitlebar(false);
	poCtrl.setCustomToolbar(false);
	poCtrl.setOfficeToolbars(false);
	poCtrl.setAllowCopy(true);
	poCtrl.setMenubar(false);
	poCtrl.setTagId("PageOfficeCtrl1");//此行必需
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>显示</title>
</head>
<body>
	<%
		if (filename.contains(".txt")) {
			String path2 = session.getAttribute("path").toString();
			System.out.println(path2);
			InputStream is = null;
			File myFile = new File(path2 + "/" + URLDecoder.decode(filename, "UTF-8"));
			is = new FileInputStream(myFile);
			byte[] data = new byte[3000];
			String str = new String(data);
			try {
				IOUtils.read(is, data);
			} catch (IOException e) {
				e.printStackTrace();
			}
			str = new String(data, "GBK");
			out.append(str);
		} else {
	%>
	<div style="width: 100%; height: 650px;">
		<po:PageOfficeCtrl id="PageOfficeCtrl1"></po:PageOfficeCtrl>
	</div>
	<%
		}
	%>
</body>
</html>