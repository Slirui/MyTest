<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/keantag" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>数据查询</title>
<link rel="stylesheet" type="text/css" href="commoncss/css.css">
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script src="http://img.hcharts.cn/highcharts/highcharts.js"
	type="text/javascript"></script>

<script type="text/javascript">
	function search() {
		var site = $("[name='site']").val();
		parent.pageGetInfo.location.href = "getInfo.shtml?site=" + encodeURIComponent(site);
	}
	
	$(function(){
		$(".sltresult tr:even td").css("background-color","#f9f9f9");  //改变偶数行背景色
	})
</script>

</head>

<body>
	<div class="topselect">
		<ul>
			<li>平均值查询</li>
			<li>
				<div>
					<form action="getInfo.shtml" id="form">
						<p>站名：</p>
						<s:select list="${siteNames }" value="${site }" name="site"></s:select>
					</form>
				</div>
				
				<div>
					<button type="button" onclick="search()" class="bttn">查 询</button>
				</div>
			</li>
		</ul>
	</div>
	
	<div class="sltresult" style="width: 100%;">
		<div class="rsttitle">
			<p>查询结果</p>
		</div>
		<div align="center"
			style="line-height: 30px; width: 100%; font-family: '楷体'; font-size: 20px;">${site }气温、降雨量常年（1981-2010）旬月平均统计
		</div>

		<div class="table">
			<table width="100%" cellspacing="0" cellpadding="0">
				<tr>
					<th rowspan="2" id="siteName" style="font-weight: bold;">
						${site }<br />(1981-2010)
					</th>
					<th colspan="4">气温（℃）</th>
					<th colspan="4">降雨量（mm）</th>
				</tr>
				<tr>
					<th>上旬</th>
					<th>中旬</th>
					<th>下旬</th>
					<th>月</th>
					<th>上旬</th>
					<th>中旬</th>
					<th>下旬</th>
					<th>月</th>
				</tr>
				<c:forEach items="${list }" var="result">
					<tr>
						<td>${result.month }</td>
						<td>${result.atEarly }</td>
						<td>${result.atMid }</td>
						<td>${result.atLate }</td>
						<td>${result.atMonth }</td>
						<td>${result.rfEarly }</td>
						<td>${result.rfMid }</td>
						<td>${result.rfLate }</td>
						<td>${result.rfMonth }</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>