<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/keantag" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>重要天气（高温出现时间）</title>
<link rel="stylesheet" type="text/css" href="commoncss/css.css">
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.min.css">
<link href="bootstrap/css/bootstrap-dialog.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="bootstrap/js/jquery-2.0.3.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/bootstrap-dialog.min.js"></script>
<script type="text/javascript">
	$(function() {
		$(".sltresult tr:even td").css("background-color","#f9f9f9");  //改变偶数行背景色
		
		$("[name='wd']").prop("value", "${wd == null ? 35 : wd}");
		$("[name='temBeginYear']").prop("value", "${temBeginYear}");
		$("[name='temEndYear']").prop("value", "${temEndYear}");
		var dx = $(("#treeC"), parent.document).val();
		$("[name='dx']").val(dx);
		
		$("#export").click(
				function() {
					var dx = $("[name='dx']").val();
					var wd = $("[name='wd']").val();
					var temBeginYear = $("[name='temBeginYear']").val();
					var temEndYear = $("[name='temEndYear']").val();
					location.href = "exportTemTimeMax.shtml?dx=" + encodeURIComponent(dx) + "&wd="
							+ wd + "&temBeginYear=" + temBeginYear
							+ "&temEndYear=" + temEndYear;
				});
	});
	
	function search() {
		var dx = $(("#treeC"), parent.document).val();
		var temBeginYear = $('#temBeginYear').val();
		var temEndYear = $('#temEndYear').val();
		var wd = $('#gw').val();
		parent.pageGetInfo.location.href = "getAnotherYears2.shtml?dx=" + encodeURIComponent(dx)
				+ "&wd=" + wd + "&temBeginYear=" + temBeginYear
				+ "&temEndYear=" + temEndYear;
	}
</script>

</head>
<body>
	<input type="hidden" name="dx" value="${dx }" />
	<input type="hidden" name="wd" value="${wd }" />
	<input type="hidden" name="temBeginYear" value="${temBeginYear }" />
	<input type="hidden" name="temEndYear" value="${temEndYear }" />
	<div class="topselect">
		<ul>
			<li>高温出现时间查询</li>
			<li>
				<div>
					<p>开始年份：</p> <s:select list="${dateYearsTem }" id="temBeginYear"
					value="${temBeginYear }" name="temBeginYear"></s:select>
				</div>
				<div>
					<p>结束年份：</p> <s:select list="${dateYearsTem }" id="temEndYear"
					value="${temEndYear }" name="temEndYear"></s:select>
				</div>
				
				<div id="warm">
					<p>温度：</p> 
					<select name="wd" id="gw">
						<option value="35" selected="selected">≥35</option>
						<option value="36">≥36</option>
						<option value="37">≥37</option>
						<option value="38">≥38</option>
						<option value="39">≥39</option>
						<option value="40">≥40</option>
					</select>
				</div>
				
				<div>
					<button type="button" onclick="search()" class="bttn">查 询</button>
				</div>
			</li>
		</ul>
	</div>
	
	<div class="sltresult" style="width: 100%;">
		<div class="rsttitle">
			<p style="width: 20%;">查询结果</p>
			<div id="export" class="daochu" style="margin-top: -1.3%;">
				<img src="images/excel.png"/>
				<p style="color: #29ba9c; margin: 0; font-size: 11px; line-height: 16px;">导出数据</p>
			</div>
		</div>
		
		<div class="table">
			<table width="100%" cellspacing="0" cellpadding="0">
				<tr>
					<th>排名</th>
					<th>最晚日期</th>
					<th>年份</th>
				</tr>
				<c:forEach items="${objlist }" begin="0" end="9" var="obj"
					varStatus="status">
					<tr>
						<td>${status.index+1 }</td>
						<td>${obj[0] }</td>
						<td title="${obj[1] }">${s:limitLength(obj[1],50) }</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	
<!-- 	<div align="right" style="width: 100%; text-align: center;"> -->
<!-- 		<button id="export" type="button" class="btn btn-lg btn-primary edit">导出数据</button> -->
<!-- 	</div> -->

</body>
</html>