<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/keantag" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>显示所有</title>
<style type="text/css">
#tab {
	border-collapse: collapse;
	border: 1px solid black;
	text-align: center;
	margin-top: 50px;
}

#tab tr, th, td {
	padding: 2px;
	border: 1px solid silver;
}
</style>
</head>
<body>
	<table id="tab">
		<tr>
			<th>序号</th>
			<th>年份</th>
			<th>入春时间</th>
			<th>入夏时间</th>
			<th>入秋时间</th>
			<th>入冬时间</th>
			<th>操&nbsp;作</th>
		</tr>
		<s:foreach list="${qr.list }" var="t" status="st">
			<tr>
				<td>${qr.start+st.index+1 }</td>
				<td>${s:formatDate(t.springDate,'yyyy') }</td>
				<td>${s:formatDate(t.springDate,'yyyy-MM-dd') }</td>
				<td>${s:formatDate(t.summerDate,'yyyy-MM-dd') }</td>
				<td>${s:formatDate(t.autumnDate,'yyyy-MM-dd') }</td>
				<td>${s:formatDate(t.winterDate,'yyyy-MM-dd') }</td>
				<td><a href="javascript:void(0)">编辑</a> <a
					href="javascript:void(0)">删除</a></td>
			</tr>
		</s:foreach>
	</table>
	<div
		style="float: left; margin-top: 20px; height: 34px; vertical-align: middle; line-height: 34px;">
		共有${qr.total}条 &nbsp;每页显示${qr.len}条 &nbsp; &nbsp; <a
			href="${qr.firstPageURL }">首页</a> <a href="${qr.previousPageURL }">上一页</a>
		第 ${qr.page } 页/共 ${qr.totalpage } 页 <a href="${qr.nextPageURL }">下一页</a>
		<a href="${qr.moWeiPageURL }">末页</a>
	</div>
</body>
</html>