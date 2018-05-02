<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/keantag" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>重要天气（四季长度）</title>
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
<script src="js/highchart/anotherYear3.js" type="text/javascript"></script>
<script src="js/highchart/anotherYear.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		$(".sltresult tr:even td").css("background-color","#f9f9f9");  //改变偶数行背景色
		
		$("[name='beginYear']").prop("value", "${beginYear}");
		$("[name='endYear']").prop("value", "${endYear}");
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
					length[i] = parseInt(data[i][0]);
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
					var beginYear = $("[name='beginYear']").val();
					var endYear = $("[name='endYear']").val();
					location.href = "exportData.shtml?dx=" + encodeURIComponent(dx) + "&beginYear="
							+ beginYear + "&endYear=" + endYear;
				});
	})
	
	function search() {
		var dx = $(("#treeC"), parent.document).val();
		var beginYear = $('#beginYear').val();
		var endYear = $('#endYear').val();
		parent.pageGetInfo.location.href = "getAnotherYears2.shtml?dx=" + encodeURIComponent(dx)
				+ "&beginYear=" + beginYear + "&endYear=" + endYear;
	}
</script>
</head>
<body>
	<input type="hidden" name="dx" value="${dx }" />
	<input type="hidden" name="beginYear" value="${beginYear }" />
	<input type="hidden" name="endYear" value="${endYear }" />
	
	<div class="topselect">
		<ul>
			<li>季节长度查询</li>
			<li>
				<div>
					<p>开始年份：</p> <s:select list="${dateYears }" id="beginYear"
					value="${beginYear }" name="beginYear"></s:select>
				</div>
				<div>
					<p>结束年份：</p> <s:select list="${dateYears }" id="endYear"
					value="${endYear }" name="endYear"></s:select>
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
					<th>长度</th>
					<th>年份</th>
				</tr>
				<c:forEach items="${objlist }" var="obj" begin="0" end="9"
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
						/* message : $('<div style="border: none;"></div>').load(
								'jimpwadd.jsp'), */
						message : $('<div style="border: none;"></div>').load(
								'saveOrUpdateAnotherYear.shtml'),
					})
				})

		$("#edit")
				.click(
						function() {
							BootstrapDialog
									.show({
										title : '编辑',
										message : $('<iframe src="listAnotherYear.shtml" width="100%" height="350px" scrolling="auto" frameborder="0"></iframe>'),
										size : 'size-wide',
									})
						})
	</script>
</body>
</html>