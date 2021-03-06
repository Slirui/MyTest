// 数据作图
var drawChart3 = function(num, date) {
	var dx = $("[name='dx']").val();
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
							text : dx,
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
								text : '温度'
							},
							plotLines : [ { // 主线
								value : 0,
								width : 0,
								color : '#ff0000'
							} ]
						},
						tooltip : {
							headerFormat : '<span style="font-size:10px">{point.key}</span><table>',
							pointFormat : '<tr><td style="color:{series.color};padding:0">{series.name}: </td>'
									+ '<td style="padding:0"><b>{point.y:f}℃</b></td></tr>',
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
							name : '温度',
							data : num,
							color : "#167ac7"
						} ]
					});
}