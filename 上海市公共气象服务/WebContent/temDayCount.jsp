<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/keantag" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>重要天气（高温总日数）</title>
<link rel="stylesheet" type="text/css" href="commoncss/css.css">
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.min.css">
<link href="bootstrap/css/bootstrap-dialog.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet" type="text/css" href="commoncss/zytq.css">
<script type="text/javascript" src="bootstrap/js/jquery-2.0.3.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/bootstrap-dialog.min.js"></script>
<script src="http://img.hcharts.cn/highcharts/highcharts.js"
	type="text/javascript"></script>
<script src="js/highchart/temDayCount.js" type="text/javascript"></script>
<script src="js/highchart/anotherYear.js" type="text/javascript"></script>
<script type="text/javascript" src="js/bootstrap.radio.js"></script>
<script type="text/javascript">
	$(function() {
		$(".sltresult tr:even td").css("background-color", "#f9f9f9"); //改变偶数行背景色

		$("[name='wd']").prop("value", "${wd == null ? 35 : wd}");
		$("[name='temBeginYear']").prop("value", "${temBeginYear}");
		$("[name='temEndYear']").prop("value", "${temEndYear}");
		var dx = $(("#treeC"), parent.document).val();
		$("[name='dx']").val(dx);

		$.ajax({
			type : 'post',
			url : 'getHighchart.shtml',//请求数据的地址
			dataType : "json",
			success : function(data) {
				var length = new Array;
				var year = new Array;
				for (i = 0; i < data.length; i++) {
					year[i] = data[i].year;
					length[i] = parseInt(data[i].length);
				}
				drawChart(length, year);
			},
			error : function(e) {
			},

		});

		$.ajax({
			type : 'post',
			url : 'getAnotherHightChart.shtml',//请求数据的地址
			dataType : "json",
			success : function(data) {
				var length = new Array;
				var year = new Array;
				for (i = 0; i < data.length; i++) {
					length[i] = parseFloat(data[i][0]);
					year[i] = data[i][1];
				}
				drawChart2(length, year);
			},
			error : function(e) {
			},

		});

		$("#export1").click(
				function() {
					var dx = $("[name='dx']").val();
					var wd = $("[name='wd']").val();
					var temBeginYear = $("[name='temBeginYear']").val();
					var temEndYear = $("[name='temEndYear']").val();
					location.href = "exportTemSumDay1.shtml?dx="
							+ encodeURIComponent(dx) + "&wd=" + wd
							+ "&temBeginYear=" + temBeginYear + "&temEndYear="
							+ temEndYear;
				});
		$("#export2").click(
				function() {
					var dx = $("[name='dx']").val();
					var wd = $("[name='wd']").val();
					var temBeginYear = $("[name='temBeginYear']").val();
					var temEndYear = $("[name='temEndYear']").val();
					location.href = "exportTemSumDay2.shtml?dx=" + dx + "&wd="
							+ wd + "&temBeginYear=" + temBeginYear
							+ "&temEndYear=" + temEndYear;
				});abcdefghijklmnopqrstuvwxyz
	});

	function search() {
		var dx = $(("#treeC"), parent.document).val();
		var temBeginYear = $('#temBeginYear').val();
		var temEndYear = $('#temEndYear').val();
		var wd = $('#gw').val();
		parent.pageGetInfo.location.href = "getAnotherYears2.shtml?dx="
				+ encodeURIComponent(dx) + "&wd=" + wd + "&temBeginYear="
				+ temBeginYear + "&temEndYear=" + temEndYear;
	}
</script>

<style>
.radiochange {
	position: absolute;
	margin: -10px 0 0 30px;
}
</style>
</head>
<body>
	<input type="hidden" name="dx" value="${dx }" />
	<input type="hidden" name="wd" value="${wd }" />
	<input type="hidden" name="temBeginYear" value="${temBeginYear }" />
	<input type="hidden" name="temEndYear" value="${temEndYear }" />
	<div class="topselect">
		<ul>
			<li>高温总日数查询</li>
			<li>
				<div>
					<p>开始年份：</p>
					<s:select list="${dateYearsTem }" id="temBeginYear"
						value="${temBeginYear }" name="temBeginYear"></s:select>
				</div>
				<div>
					<p>结束年份：</p>
					<s:select list="${dateYearsTem }" id="temEndYear"
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

	<div class="sltresult">
		<div class="rsttitle">
			<p style="width: 20%;">查询结果</p>
			<img id="change" src="images/change2.png"
				style="height: 50%; margin-top: -5px;">
			<div id="export1" class="daochu">
				<img src="images/excel.png" />
				<p
					style="color: #29ba9c; margin: 0; font-size: 11px; line-height: 16px;">导出数据</p>
			</div>
			<div id="export2" class="daochu" style="display: none;">
				<img src="images/excel.png" />
				<p
					style="color: #29ba9c; margin: 0; font-size: 11px; line-height: 16px;">导出数据</p>
			</div>
		</div>

		<div id="defaultT" class="table">
			<table width="100%" cellspacing="0" cellpadding="0">
				<tr>
					<th>排名</th>
					<th>年份</th>
					<th>日数</th>
				</tr>
				<c:forEach items="${objlist }" begin="0" end="9" var="obj"
					varStatus="status">
					<tr>
						<td>${status.index+1 }</td>
						<td title="${obj[1] }">${s:limitLength(obj[1],50) }年</td>
						<td>${obj[0] }日</td>
					</tr>
				</c:forEach>
			</table>
		</div>

		<div id="detailedT" class="table" style="display: none;">
			<table width="100%" cellspacing="0" cellpadding="0">
				<tr>
					<th>排名</th>
					<th>年份</th>
					<th>日数</th>
					<th>5月</th>
					<th>6月</th>
					<th>7月</th>
					<th>8月</th>
					<th>9月</th>
				</tr>
				<s:foreach list="${temDayCount }" var="dayCount">
					<s:if test="${dayCount.sortNum < 11 }">
						<tr>
							<td>${dayCount.sortNum }</td>
							<td>${dayCount.year }年</td>
							<td>${dayCount.sumDay }日</td>
							<td>${dayCount.may }</td>
							<td>${dayCount.june }</td>
							<td>${dayCount.july }</td>
							<td>${dayCount.august }</td>
							<td>${dayCount.sep }</td>
						</tr>
					</s:if>
				</s:foreach>
			</table>
		</div>
	</div>

	<div class="chart">
		<div class="chttitle">
			<p style="width: 25%;">图表</p>
		</div>

		<div id="chart2" style="width: 100%; height: 300px;"></div>

		<div id="chart" style="width: 100%; height: 300px;"></div>
	</div>

	<script>
		$("#change").click(
				function() {
					if ($("#export2").css("display") == "none"
							&& $("#detailedT").css("display") == "none") {
						$("#export2").css("display", "block");
						$("#detailedT").css("display", "block");
						$("#export1").css("display", "none");
						$("#defaultT").css("display", "none");
					} else {
						$("#export2").css("display", "none");
						$("#detailedT").css("display", "none");
						$("#export1").css("display", "block");
						$("#defaultT").css("display", "block");
					}
				})
	</script>
</body>
</html>