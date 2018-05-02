$(function() {
	$("#impWeatherBDialog1").bind("click", function() {
		BootstrapDialog.show({
			title : '添加一级节点',
			message : $('<input style="width: 100%;" id="yiji" />'),
			size : 'size-small',
			closable : false,
			buttons : [ {
				label : '确定',
				cssClass : 'btn-primary',
				action : function(dialogItself) {
					var node = $("#yiji").val();
					$.ajax({
						url : 'addImpWeatherOneNode.shtml',
						type : 'POST',
						data : {
							"yiji" : node
						},
						dataType : 'json',
						success : function(data) {
							if (data.isOK == "成功") {
								location.href = "impWeatherIndex.shtml";
								// $("iframe").attr("src", "demo.jsp");
								// $("#d1").hide();
								// $("#d2").show();
								dialogItself.close();
							}
						}
					});
				}
			}, {
				label : '取消',
				action : function(dialogItself) {
					dialogItself.close();
				}
			} ]
		})
	})

	$("#impWeatherBDialog2").bind("click", function() {
		BootstrapDialog.show({
			title : '添加二级节点',
			message : $('<input style="width: 100%;" id="erji2" />'),
			size : 'size-small',
			closable : false,
			buttons : [ {
				label : '确定',
				cssClass : 'btn-primary',
				action : function(dialogItself) {
					var erji = $("#erji2").val();
					var check = $("#check").val();
					$.ajax({
						url : 'addImpWeatherTwoNode.shtml',
						type : 'POST',
						data : {
							"erji" : erji,
							"check" : check
						},
						dataType : 'json',
						success : function(data) {
							if (data.isOK == "成功") {
								location.href = "impWeatherIndex.shtml";
								dialogItself.close();
							}
						}
					});
				}
			}, {
				label : '取消',
				action : function(dialogItself) {
					dialogItself.close();
				}
			} ]
		})
	})

	$("#impWeatherBDialog3").bind("click", function() {
		BootstrapDialog.show({
			title : '添加三级节点',
			message : $('<input style="width: 100%;" id="sanji" />'),
			size : 'size-small',
			closable : false,
			buttons : [ {
				label : '确定',
				cssClass : 'btn-primary',
				action : function(dialogItself) {
					var sanji = $("#sanji").val();
					var check = $("#check").val();
					var erji = $("#erji").val();
					$.ajax({
						url : 'addImpWeatherThreeNode.shtml',
						type : 'POST',
						data : {
							"sanji" : sanji,
							"check" : check,
							"erji" : erji
						},
						dataType : 'json',
						success : function(data) {
							if (data.isOK == "成功") {
								location.href = "impWeatherIndex.shtml";
								// $("#d1").hide();
								// $("#d2").show();
								dialogItself.close();
							}
						}
					});
				}
			}, {
				label : '取消',
				action : function(dialogItself) {
					dialogItself.close();
				}
			} ]
		})
	})
});