// 数据作图
//var dateDate = [ "2017-01-17", "2017-05-22", "2017-05-23", "2017-05-24",
//		"2017-05-26", "2017-05-27", "2017-05-28", "2017-05-29", "2017-05-30",
//		"2017-05-31" ];
//数据作图
//var dateDate = [ "01-17", "05-22", "05-23", "05-24",
//		"05-26", "05-27", "05-28", "05-29", "05-30",
//		"05-31" ];
var drawChart = function(length, date) {
	var dx = $("[name='dx']").val();
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
								text : '年份'
							}
//							labels : {
//								formatter : function() {
//									return this.value.substring(0, 4);
//									// return datetime(this.value);
//								}
//							}
						},
						yAxis : {
							//type:'datetime',
							title : {
								text : '日期'
							},
//							labels : {
//								formatter : function() {
//									// return this.value.substring(0, 4);
//									for (var i = 0; i < dateDate.length; i++) {
//										return datetime(dateDate[i]);
//									}
//								}
//							},
							//tickPositions: dateDate,// 指定竖轴坐标点的值
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
function datetime(time) {
	//debugger;
	var today = new Date(time);
	// 获取年
	var nn = today.getFullYear();
	// 获取月
	var yy = today.getMonth() + 1;
	// 获取日
	var rr = today.getDate();
	// 获取每一天
	var dd = today.getDay();
	// 获取小时
	var hh = today.getHours();
	// 获取分钟
	var mm = today.getMinutes();
	// 获取秒
	var ss = today.getSeconds();
	if (hh > 12) {
		mc = "PM";
	} else {
		mc = "AM";
	}
	switch (dd) {
	case 0:
		dd = "星期日";
		break;
	case 1:
		dd = "星期一";
		break;
	case 2:
		dd = "星期二";
		break;
	case 3:
		dd = "星期三";
		break;
	case 4:
		dd = "星期四";
		break;
	case 5:
		dd = "星期五";
		break;
	case 6:
		dd = "星期六";
		break;
	}
	return totwo(yy) + "-" + totwo(rr);
}

function totwo(n) {
	if (n <= 9) {
		return n = "0" + n;
	} else {
		return n = "" + n;
	}
}
