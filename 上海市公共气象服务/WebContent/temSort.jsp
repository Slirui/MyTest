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
<link href="bootstrap/css/bootstrap-dialog.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet" type="text/css" href="commoncss/css.css">
<script type="text/javascript" src="bootstrap/js/jquery-2.0.3.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/bootstrap-dialog.min.js"></script>
<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
<script src="http://img.hcharts.cn/highcharts/highcharts.js"
	type="text/javascript"></script>
<script src="js/highchart/elementsTem.js" type="text/javascript"></script>
<script src="js/highchart/wd.js" type="text/javascript"></script>
<script src="js/highchart/wd2.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		/*var precount = "${precount}";
		if (precount > 0) {
			BootstrapDialog.confirm({
				title : '查询提示',
				message : '<font style="color:red;font-weight:bold;">'
						+ '原始数据有' + precount + '条缺失，请补齐！' + '</font>',
				type : BootstrapDialog.TYPE_WARNING,
				closable : false,
				btnCancelLabel : '取消',
				btnOKLabel : '确定',
				btnOKClass : 'btn-warning',
				callback : function(result) {
					return;
				}
			});
		}*/

		$(".sltresult tr:even td").css("background-color", "#f9f9f9"); //改变偶数行背景色
		$("[name='beginYear']").prop("value", "${beginYear}");
		$("[name='endYear']").prop("value", "${endYear}");
		var chaxunresult = "${chaxunresult}";
		if (chaxunresult == "类型一") {
			$("#type1").css("display", "block");
			$("#type2").css("display", "none");
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

			$.ajax({
				type : 'post',
				url : 'getHighchartElementsTem2.shtml',//请求数据的地址
				dataType : "json",
				success : function(data) {
					var num = new Array;
					var date = new Array;
					for (i = 0; i < data.length; i++) {
						num[i] = parseFloat(data[i][0]);
						date[i] = data[i][1];
					}
					drawChart2(num, date);
				},
				error : function(e) {
				},

			});
		} else {
			$("#type1").css("display", "none");
			$("#type2").css("display", "block");
			$.ajax({
				type : 'post',
				url : 'getHighchartElementSort2.shtml',//请求数据的地址
				dataType : "json",
				success : function(data) {
					var num = new Array;
					var date = new Array;
					for (i = 0; i < data.length; i++) {
						num[i] = parseFloat(data[i][0]);
						date[i] = data[i][1];
					}
					drawChart3(num, date);
				},
				error : function(e) {
				},

			});
		}
		$("#export").click(
				function() {
					if (chaxunresult == "类型一") {
						var dx = $("[name='dx']").val();
						var beginDate = $("[name='beginDate']").val();
						var endDate = $("[name='endDate']").val();
						var stationName = $("[name='stationName']").val();
						location.href = "exportElementsTem.shtml?dx="
								+ encodeURIComponent(dx) + "&beginDate="
								+ beginDate + "&endDate=" + endDate
								+ "&stationName="
								+ encodeURIComponent(stationName);
					} else {
						var dx = $("[name='dx']").val();
						var stationName = $("[name='stationName2']").val();
						var beginYear = $("[name='beginYear']").val();
						var endYear = $("[name='endYear']").val();
						var beginDate2 = $("[name='beginDate2']").val();
						var endDate2 = $("[name='endDate2']").val();
						location.href = "exportElementsTem2.shtml?dx="
								+ encodeURIComponent(dx) + "&stationName="
								+ encodeURIComponent(stationName)
								+ "&beginYear=" + beginYear + "&endYear="
								+ endYear + "&beginDate2=" + beginDate2
								+ "&endDate2=" + endDate2;
					}
				});
	});

	function search() {
		var Cid = $(("#treeC"), parent.document).val();
		if (Cid == '302') {
			var dx = $(("#treeT"), parent.document).val();
		} else {
			var dx = $(("#treeC"), parent.document).val();
		}
		var chaxunresult = "类型一";
		var stationName = $("[name='stationName']").val();
		var beginDate = $('#beginDate').val();
		var endDate = $('#endDate').val();
		parent.pageGetInfo.location.href = "getElementSort.shtml?dx="
				+ encodeURIComponent(dx) + "&stationName="
				+ encodeURIComponent(stationName) + "&beginDate=" + beginDate
				+ "&endDate=" + endDate + "&chaxunresult="
				+ encodeURIComponent(chaxunresult);
	}

	function search2() {
		var Cid = $(("#treeC"), parent.document).val();
		if (Cid == '302') {
			var dx = $(("#treeT"), parent.document).val();
		} else {
			var dx = $(("#treeC"), parent.document).val();
		}
		var chaxunresult = "类型二";
		var stationName = $("[name='stationName2']").val();
		var beginYear = $("[name='beginYear']").val();
		var endYear = $("[name='endYear']").val();
		var beginDate2 = $('#beginDate2').val();
		var endDate2 = $('#endDate2').val();
		parent.pageGetInfo.location.href = "getElementSort2.shtml?dx="
				+ encodeURIComponent(dx) + "&stationName="
				+ encodeURIComponent(stationName) + "&beginYear=" + beginYear
				+ "&endYear=" + endYear + "&beginDate2=" + beginDate2
				+ "&endDate2=" + endDate2 + "&chaxunresult="
				+ encodeURIComponent(chaxunresult);
	}
</script>

</head>
<body>

	<input type="hidden" name="dx" value="${dx }" />

	<div class="topselect">
		<ul>
			<li>日气温查询<img id="change"
				style="height: 60%; float: right; margin: 0px; cursor: pointer;"
				src="images/change.png" /></li>
			<li id="type1">
				<div>
					<p>站名：</p>
					<s:select list="${stationNames }" value="${stationName }"
						name="stationName"></s:select>
				</div>
				<div>
					<p>开始日期：</p>
					<input type="text" id="beginDate" name="beginDate"
						value="${beginDate }"
						onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
				</div>
				<div>
					<p>结束日期：</p>
					<input type="text" id="endDate" name="endDate" value="${endDate }"
						onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
				</div>

				<div>
					<button id="search" type="button" onclick="search()" class="bttn">查
						询</button>
				</div>
			</li>

			<li style="display: none;" id="type2">
				<div>
					<p>站名：</p>
					<s:select list="${stationNames }" value="${stationName }"
						name="stationName2" style="width: 80px;"></s:select>
				</div>
				<div>
					<p>开始年份：</p>
					<s:select list="${beginYears }" value="${beginYear }"
						name="beginYear" style="width: 80px;"></s:select>
				</div>
				<div>
					<p>结束年份：</p>
					<s:select list="${endYears }" value="${endYear }" name="endYear"
						style="width: 80px;"></s:select>
				</div>
				<div>
					<p>开始日期：</p>
					<input type="text" id="beginDate2" name="beginDate2"
						value="${beginDate2 }" style="width: 80px;"
						onfocus="WdatePicker({readOnly:true,dateFmt:'MM-dd'})" />
				</div>
				<div>
					<p>结束日期：</p>
					<input type="text" id="endDate2" name="endDate2"
						value="${endDate2 }" style="width: 80px;"
						onfocus="WdatePicker({readOnly:true,dateFmt:'MM-dd'})" />
				</div>
				<div>
					<button id="search2" type="button" onclick="search2()" class="bttn"
						style="width: 80px;">查 询</button>
				</div>
			</li>
		</ul>

	</div>

	<div class="sltresult">
		<div class="rsttitle">
			<p style="width: 25%;">查询结果</p>
			<div id="export" class="daochu">
				<img src="images/excel.png" />
				<p
					style="color: #29ba9c; margin: 0; font-size: 11px; line-height: 16px;">导出数据</p>
			</div>
		</div>
		<div class="table">
			<table width="100%" cellspacing="0" cellpadding="0">
				<tr>
					<th>排名</th>
					<th>温度</th>
					<th>日期</th>
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

	<div class="chart">
		<div class="chttitle">
			<p style="width: 25%;">图表</p>
		</div>
		<s:if test="${chaxunresult eq '类型一' }">
			<div id="chart2" style="width: 100%; height: 300px;"></div>
			<div id="chart" style="width: 100%; height: 300px;"></div>
		</s:if>
		<s:else>
			<div id="chart3" style="width: 100%; height: 300px;"></div>
		</s:else>
	</div>



	<script>
		$("#change")
				.click(
						function() {
							if ($(".topselect ul li:nth-child(3)").css(
									"display") == "none") {
								$(".topselect ul li:nth-child(3)").css(
										"display", "block");
								$(".topselect ul li:nth-child(2)").css(
										"display", "none");
							} else if ($(".topselect ul li:nth-child(2)").css(
									"display") == "none") {
								$(".topselect ul li:nth-child(2)").css(
										"display", "block");
								$(".topselect ul li:nth-child(3)").css(
										"display", "none");
							} else {
								$(".topselect ul li:nth-child(2)").css(
										"display", "block");
								$(".topselect ul li:nth-child(3)").css(
										"display", "none");
							}
						})
	</script>
</body>
</html>