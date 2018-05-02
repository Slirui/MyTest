<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/keantag" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>要素排名</title>
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="commoncss/commoncss.css">
<script type="text/javascript" src="bootstrap/js/jquery-2.0.3.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
<script src="http://img.hcharts.cn/highcharts/highcharts.js"
	type="text/javascript"></script>
<script src="js/highchart/elements.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		$.ajax({
			type : 'post',
			url : 'getHighchartElementsTem.shtml',//请求数据的地址
			dataType : "json",
			success : function(data) {
				var length = new Array;
				var year = new Array;
				for (i = 0; i < data.length; i++) {
					year[i] = data[i].temYear;
					length[i] = data[i].temNum;
				}
				drawChart(length, year);
			},
			error : function(e) {
			},

		});

		$("#export").click(
				function() {
					var dx = $("[name='dx']").val();
					var beginDate = $("[name='beginDate']").val();
					var endDate = $("[name='endDate']").val();
					var stationName = $("[name='stationName']").val();
					location.href = "exportElementsDayPre2020.shtml?dx=" + encodeURIComponent(dx)
							+ "&beginDate=" + beginDate + "&endDate=" + endDate
							+ "&stationName=" + encodeURIComponent(stationName);
				});
	});
</script>
</head>
<body>

	<input type="hidden" name="dx" value="${dx }" />
	<input type="hidden" name="stationName" value="${stationName }" />
	<input type="hidden" name="beginDate" value="${beginDate }" />
	<input type="hidden" name="endDate" value="${endDate }" />
	<div id="temsorttab"
		style="width: 100%; margin: 10px 0; text-align: center;">
		<table class="table table-bordered"
			style="width: 800px; margin: 0 auto;">
			<tr>
				<td>排名</td>
				<td>数值</td>
				<td>日期</td>
			</tr>
			<c:forEach items="${objlist }" var="obj" begin="0" end="9"
				varStatus="status">
				<tr>
					<td>${status.index + 1 }</td>
					<td>${obj[0] }</td>
					<td title="${obj[1]}">${s:limitLength(obj[1],50)}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div align="center">
		<button id="export" type="button" class="btn btn-lg btn-primary edit">导出数据</button>
	</div>
	
	<div id="chart" style="width: 98%; height: 300px; margin: 10px 0 0 0;"></div>
</body>
</html>