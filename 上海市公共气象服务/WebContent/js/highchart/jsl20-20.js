// 数据作图
var drawChart = function(length, year) {
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
							text : '日降水量（20-20）',
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
							categories : year,
							title : {
								text : '年份'
							}
						},
						yAxis : {
							title : {
								text : '数值'
							},
							// tickPositions : [ -20, -5, 0, 5, 25, 45 ],//
							// 指定竖轴坐标点的值
							plotLines : [ { // 主线
								value : 0,
								width : 0,
								color : '#ff0000'
							} ]
						},
						tooltip : {
							headerFormat : '<span style="font-size:10px">{point.key}</span><table>',
							pointFormat : '<tr><td style="color:{series.color};padding:0">{series.name}: </td>'
									+ '<td style="padding:0"><b>{point.y:.1f}mm</b></td></tr>',
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
							name : '降水量',
							data : length,
							color : "#167ac7"
						} ]
					});
}

// 数据作图
var drawChart2 = function(num, date) {
	$("#chart2")
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
							text : '日降水量（20-20）',
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
								text : '日期'
							},
							labels : {
								formatter : function() {
									return this.value.substring(0, 10);
								}
							}
						},
						yAxis : {
							title : {
								text : '降水量'
							},
							// tickPositions : [ 0,100,200 ],// 指定竖轴坐标点的值
							plotLines : [ { // 主线
								value : 0,
								width : 0,
								color : '#ff0000'
							} ]
						},
						tooltip : {
							headerFormat : '<span style="font-size:10px">{point.key}</span><table>',
							pointFormat : '<tr><td style="color:{series.color};padding:0">{series.name}: </td>'
									+ '<td style="padding:0"><b>{point.y:.1f}mm</b></td></tr>',
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
							name : '降水量',
							data : num,
							color : "#167ac7"
						} ]
					});
}