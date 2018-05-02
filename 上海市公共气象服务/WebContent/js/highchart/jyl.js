// 数据作图
var drawChart3 = function(num, result) {
	var type = $("[name='type']").val();
	var name = "";
	if(type == "逐日"){
		name = "日期";
	}else{
		name = "年份"
	}
	$("#chart3")
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
							text : type + '降水量（20-20）',
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
							categories : result,
							title : {
								text : name
							},
							labels : {
								formatter : function() {
									if(type == "逐日"){
										return this.value.substring(0, 10);
									}else{
										return this.value;
									}
								}
							}
						},
						yAxis : {
							title : {
								text : '降水量'
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
							data : num,
							color : "#167ac7"
						} ]
					});
}