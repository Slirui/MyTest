<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/keantag" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>重要天气（梅雨量）</title>
<link rel="stylesheet" type="text/css" href="commoncss/css.css">
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.min.css">
<link href="bootstrap/css/bootstrap-dialog.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="bootstrap/js/jquery-2.0.3.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/bootstrap-dialog.min.js"></script>
<script src="http://img.hcharts.cn/highcharts/highcharts.js"
	type="text/javascript"></script>
<script src="js/highchart/modAmount.js" type="text/javascript"></script>
<script src="js/highchart/myl.js" type="text/javascript"></script>

<script type="text/javascript">
	$(function() {
		$(".sltresult tr:even td").css("background-color","#f9f9f9");  //改变偶数行背景色
		
		$("[name='modBeginYear']").prop("value", "${modBeginYear}");
		$("[name='modEndYear']").prop("value", "${modEndYear}");
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
					length[i] = data[i].length / 10;
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
					length[i] = parseFloat(data[i][0] / 10);
					year[i] = data[i][1];
				}
				drawChart2(length, year);
			},
			error : function(e) {
			},

		});

		$("#export").click(
				function() {
					var dx = $("[name='dx']").val();
					var modBeginYear = $("[name='modBeginYear']").val();
					var modEndYear = $("[name='modEndYear']").val();
					location.href = "exportModAmount.shtml?dx="
							+ encodeURIComponent(dx) + "&modBeginYear="
							+ modBeginYear + "&modEndYear=" + modEndYear;
				});

	});
	
	function search() {
		var dx = $(("#treeC"), parent.document).val();
		var modBeginYear = $('#modBeginYear').val();
		var modEndYear = $('#modEndYear').val();
		parent.pageGetInfo.location.href = "getAnotherYears2.shtml?dx=" + encodeURIComponent(dx)
				+ "&modBeginYear=" + modBeginYear + "&modEndYear=" + modEndYear;
	}
</script>

</head>
<body>
	<input type="hidden" name="dx" value="${dx }" />
	<input type="hidden" name="modBeginYear" value="${modBeginYear }" />
	<input type="hidden" name="modEndYear" value="${modEndYear }" />

	
	<div class="topselect">
		<ul>
			<li>梅雨量查询</li>
			<li>
				<div>
					<p>开始年份：</p> <s:select list="${dateYearsMod }" id="modBeginYear"
					value="${modBeginYear }" name="modBeginYear"></s:select>
				</div>
				<div>
					<p>结束年份：</p> <s:select list="${dateYearsMod }" id="modEndYear"
					value="${modEndYear }" name="modEndYear"></s:select>
				</div>
				
				<div>
					<button type="button" onclick="search()" class="bttn">查 询</button>
				</div>
			</li>
		</ul>
	</div>
	
	<div class="sltresult">
		<div class="rsttitle">
			<p style="width: 25%;">查询结果</p>
			<div id="export" class="daochu">
				<img src="images/excel.png"/>
				<p style="color: #29ba9c; margin: 0; font-size: 11px; line-height: 16px;">导出数据</p>
			</div>
			<div id="edit" class="daochu" style="${sessionScope.userName == 'admin'?'':'display:none;'}">
				<img src="images/edit.png"/>
				<p style="color: #29ba9c; margin: 0; font-size: 11px; line-height: 16px;">编辑</p>
			</div>
			<div id="add" class="daochu" style="${sessionScope.userName == 'admin'?'':'display:none;'}">
				<img src="images/add.png"/>
				<p style="color: #29ba9c; margin: 0; font-size: 11px; line-height: 16px;">新增</p>
			</div>
		</div>
		
		<div class="table">
			<table width="100%" cellspacing="0" cellpadding="0">
				<tr>
					<th>排名</th>
					<th>梅雨量</th>
					<th>年份</th>
				</tr>
				<c:forEach items="${objlist }" var="obj" begin="0" end="9"
					varStatus="status">
					<tr>
						<td>${status.index+1 }</td>
						<td>${obj[0] / 10 }</td>
						<td title="${obj[1] }">${s:limitLength(obj[1],50) }</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	
<!-- 	<div align="center" style="width: 800px;"> -->
<!-- 		<button id="add" type="button" class="btn btn-lg btn-primary add">新增</button> -->
<!-- 		<button id="edit" type="button" class="btn btn-lg btn-primary edit">编辑</button> -->
<!-- 		<button id="export" type="button" class="btn btn-lg btn-primary edit">导出数据</button> -->
<!-- 	</div> -->


	<div class="chart">
		<div class="chttitle">
			<p style="width: 25%;">图表</p>
		</div>
		
		<div id="chart2" style="width: 100%; height: 300px;"></div>
	
		<div id="chart" style="width: 100%; height: 300px;"></div>
	</div>
	
	<script>
		$("#add").click(
				function() {
					BootstrapDialog.show({
						title : '新增',
						message : $('<div style="border: none;"></div>').load(
								'saveOrUpdateMod.shtml'),
					})
				})

		$("#edit")
				.click(
						function() {
							BootstrapDialog
									.show({
										title : '编辑',
										message : $('<iframe src="listMod.shtml" width="100%" height="350px" scrolling="auto" frameborder="0"></iframe>'),
										size : 'size-wide',
									})
						})
	</script>
</body>
</html>