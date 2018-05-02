<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HightChart显示数据</title>
<script type="text/javascript" src="js/jquery-2.0.3.js"></script>
<script src="http://img.hcharts.cn/highcharts/highcharts.js"
	type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		var length = [ 3, 6, 7, 8, 10 ];
		var date = [ "2002", "2004", "2006", "2009", "2010" ];
		drawChart(length, date);
	});

	var drawChart = function(length, date) {
		$("#chart")
				.highcharts(
						{
							chart : {
								type : 'column'
							},
							credits : {
								enabled : false
							// 去水印
							},
							title : {
								text : '显示数据',
								style : {
									margin : '10px 100px 0 0' // center it
								}
							},
							subtitle : { // 副标题
								text : '',
								style : {
									margin : '0 100px 0 0' // center it
								}
							},
							xAxis : {
								categories : date,
								title : {
									text : '年份'
								}
							},
							yAxis : {
								//type : 'category',
								title : {
									text : '日期'
								},
								//tickPositions : ["5","6","8","15","25"],// 指定竖轴坐标点的值
								plotLines : [ { // 主线
									value : 0,
									width : 0,
									color : '#ff0000'
								} ]
							},
							tooltip : {
								headerFormat : '<span style="font-size:10px">{point.key}</span><table>',
								pointFormat : '<tr><td style="color:{series.color};padding:0">{series.name}: </td>'
										+ '<td style="padding:0"><b>{point.y:f}</b></td></tr>',
								footerFormat : '</table>',
								shared : true,
								useHTML : true
							},
							plotOptions : {
								column : {
									pointPadding : 0.2,
									borderWidth : 0
								}
							},
							series : [ {
								name : '排名',
								data : length,
								color : "#167ac7"
							} ]
						});
	}
</script>
</head>
<body>
	<div id="chart" style="width: 100%; height: 300px;"></div>
</body>
</html>