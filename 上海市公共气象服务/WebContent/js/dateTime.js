function datetime(time) {
		// debugger;
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
		return nn + "-" + totwo(yy) + "-" + totwo(rr);
	}

	function totwo(n) {
		if (n <= 9) {
			return n = "0" + n;
		} else {
			return n = "" + n;
		}
	}