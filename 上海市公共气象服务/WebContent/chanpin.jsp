<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/keantag" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>典型案例</title>
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.min.css">
<link href="bootstrap/css/bootstrap-dialog.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet" type="text/css" href="commoncss/css.css">
<script type="text/javascript" src="bootstrap/js/jquery-2.0.3.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/bootstrap-dialog.min.js"></script>
<script src="bootstrap/js/bootstrap-contextmenu.js"></script>
<script type="text/javascript">
	$(function() {
		var o = $(".doc");
		var arr = new Array();
		arr[0] = $(".doc ul li:nth-child(1)").height();
		arr[1] = $(".doc ul li:nth-child(2)").height();
		arr[2] = $(".doc ul li:nth-child(3)").height();
		var max = Math.max.apply(null, arr);
		o.css("height", max);
		//预警ul
		$(".yjwarn ul:even").css("background-color", "#1e56a2"); //改变奇数行背景色
		$(".yjwarn ul:even").css("float", "left");
		//$(".warn ul:odd").css("background-color","#29ba9c");  //改变奇数行背景色
		var w_even = $(".yjwarn ul:even").width();
		var w_odd = $(".yjwarn ul:odd").width();
		var totalwidth = w_even + w_odd + 10;
		$("#yjul").css("width", totalwidth);
		$("#yjul").css("height", "100%");

		//p
		$(".context").mouseover(function() {
			$(this).css("color", "#1e56a2");
		})

		$(".context").mouseout(function() {
			$(this).css("color", "#000");
		})
		var imgObj = document.getElementsByClassName("desaturate");
		var i;
		for (i = 0; i < imgObj.length; i++) {
			imgObj[i].src = gray(imgObj[i]);
		}
		$(".doc ul li").mouseover(function() {
			$(this).css("box-shadow", "3px 3px 15px #cecece");
		});
		$(".doc ul li").mouseout(function() {
			$(this).css("box-shadow", "0 0 0 #f0f0f0");
		})
		$("#delDxalFile").click(
				function() {
					var name = $("#deleteDxalFileName").val();
					if (confirm("确认要删除此文件？")) {
						location.href = "deleteDxalFileName.shtml?name="
								+ encodeURIComponent(name);
					}
				});

	});

	function gray(imgObj) {
		var canvas = document.createElement('canvas');
		var canvasContext = canvas.getContext('2d');
		var imgW = imgObj.width;
		var imgH = imgObj.height;
		canvas.width = imgW;
		canvas.height = imgH;
		canvasContext.drawImage(imgObj, 0, 0, imgW, imgH);
		var imgPixels = canvasContext.getImageData(0, 0, imgW, imgH);
		for (var y = 0; y < imgPixels.height; y++) {
			for (var x = 0; x < imgPixels.width; x++) {
				var i = (y * 4) * imgPixels.width + x * 4;
				var avg = (imgPixels.data[i] + imgPixels.data[i + 1] + imgPixels.data[i + 2]) / 3;
				imgPixels.data[i] = avg;
				imgPixels.data[i + 1] = avg;
				imgPixels.data[i + 2] = avg;
			}
		}
		canvasContext.putImageData(imgPixels, 0, 0, 0, 0, imgPixels.width,
				imgPixels.height);
		return canvas.toDataURL();
	}

	function preview(name) {
		document.getElementById("fileName").value = name;
		document.getElementById("myForm").submit();
	}
	function mousedown(name) {
		$("#deleteDxalFileName").val(name);
	}
</script>
</head>

<body>
	<div style="width: 100%; height: 100%; overflow-y: auto;">
		<div class="topname">${title }</div>
		<form action="dxalpreview.jsp" method="post" id="myForm"
			target="_blank">
			<input type="hidden" name="name" id="fileName" value="" />
			<div class="yjwarn">
				<div id="yjul">
					<c:forEach items="${yj }" var="item">
						<ul style="padding: 15px 0; height: 100%; display: inline-block;">
							<c:forEach items="${item.value }" var="it">
								<s:if test="${!fn:contains(it.warnname,'解除') }">
									<div style="width: 100px; float: left;">
										<p class="yjstatues">${it.warntitle }</p>
										<div style="text-align: center;">
											<a href="javascript:void(0)"
												onclick="preview('${it.warnname }')"><img
												style="width: 50px;"
												src="warnicon/icon/${fn:replace(it.warnicon,'-','_') }" />
											</a>
										</div>
										<p class="yjtime">${s:formatDate(it.warndate,'MM/dd/HH:mm') }</p>
									</div>
									<div class="line">---</div>
								</s:if>
								<s:else>
									<div style="width: 100px; float: left;">
										<div class="yjstatues">${it.warntitle }</div>
										<div style="text-align: center;">
											<a href="javascript:void(0)"
												onclick="preview('${it.warnname }')"> <img
												src="warnicon/icon/${fn:replace(it.warnicon,'-','_') }"
												id="imgToGray" class="desaturate" style="width: 50px;" />
											</a>
										</div>
										<div class="yjtime">${s:formatDate(it.warndate,'MM/dd/HH:mm') }</div>
									</div>
								</s:else>
							</c:forEach>
						</ul>
						<!-- <div style="margin-top: 150px;"></div> -->
					</c:forEach>
				</div>
			</div>
			<!--  style="${kb.size() > 0 ? '' : 'display:none;' }" -->
			<div class="doc"
				style="${kb.size() > 0 || zb.size() > 0 || fwzj.size() > 0 ? '' : 'display:none;'}">
				<ul>
					<li style="${kb.size() > 0 ? '' : 'display:none;' }">
						<div class="dochead">
							<img src="images/doc.png" class="docbg">
							<p>快报</p>
							<img src="images/kb.png" class="logo">
						</div>
						<div class="doccontent">
							<s:foreach list="${kb }" var="kb_">
								<div style="width: 100%">
									<font style="font-size: 11px; padding-left: 10%;">${s:formatDate(kb_.date,"MM/dd/HH:mm") }</font>
									<p
										style="width: 60%; float: right; cursor: pointer; text-decoration: none; font-size: 11px; color: black;"
										href="javascript:void(0)" class="context"
										data-toggle="context" data-target="#context-menu"
										onclick="preview('${kb_.fileName }')"
										onmousedown="mousedown('${kb_.fileName}')"
										title="${kb_.title }">${s:limitLength(kb_.title,42) }</p>
								</div>
								<br />
							</s:foreach>
						</div>
					</li>
					<li style="${zb.size() > 0 ? '' : 'display:none;' }">
						<div class="dochead">
							<img src="images/doc.png" class="docbg">
							<p>专报</p>
							<img src="images/zb.png" class="logo">
						</div>
						<div class="doccontent">
							<s:foreach list="${zb }" var="zb_">
								<div style="width: 100%">
									<font style="font-size: 11px; padding-left: 10%;">${s:formatDate(zb_.date,"MM/dd/HH:mm") }</font>
									<p
										style="width: 60%; float: right; cursor: pointer; text-decoration: none; font-size: 11px; color: black;"
										href="javascript:void(0)" class="context"
										data-toggle="context" data-target="#context-menu"
										onclick="preview('${zb_.fileName }')"
										onmousedown="mousedown('${zb_.fileName}')"
										title="${zb_.title }">${s:limitLength(zb_.title,42) }</p>
								</div>
								<br />
							</s:foreach>
						</div>
					</li>
					<li style="${fwzj.size() > 0 ? '' : 'display:none;' }">
						<div class="dochead">
							<img src="images/doc.png" class="docbg">
							<p>服务总结</p>
							<img src="images/fwzj.png" class="logo">
						</div>
						<div class="doccontent">
							<s:foreach list="${fwzj }" var="fwzj_">
								<div style="width: 100%">
									<font style="font-size: 11px; padding-left: 10%;">${s:formatDate(fwzj_.date,"MM/dd/HH:mm") }</font>
									<p
										style="width: 60%; float: right; cursor: pointer; text-decoration: none; font-size: 11px; color: black;"
										href="javascript:void(0)" class="context"
										data-toggle="context" data-target="#context-menu"
										onclick="preview('${fwzj_.fileName }')"
										onmousedown="mousedown('${fwzj_.fileName}')"
										title="${fwzj_.title }">${s:limitLength(fwzj_.title,42) }
									</p>
								</div>
								<br />
							</s:foreach>
						</div>
					</li>
				</ul>
			</div>
			<div class="other">
				<p style="font-weight: bold;">其他：</p>
				<div>
					<s:foreach list="${other }" var="other_">
						<s:if
							test="${fn:contains(other_.fileName,'批示材料') || fn:contains(other_.fileName,'服务经验') }">
						</s:if>
						<s:else>
							<div style="width: 100%">
								<font style="font-size: 11px;">${s:formatDate(other_.date,"MM/dd/HH:mm") }</font>
								<p
									style="width: 88%; float: right; cursor: pointer; text-decoration: none; font-size: 11px; color: black;"
									href="javascript:void(0)" class="context" data-toggle="context"
									data-target="#context-menu"
									onclick="preview('${other_.fileName }')"
									onmousedown="mousedown('${other_.fileName}')"
									title="${other_.title }">${other_.title }</p>
							</div>
							<br />
						</s:else>
					</s:foreach>
				</div>
			</div>
		</form>
		<div class="pscl" style="${pscl != '' ? '' : 'display:none;'}">
			<p style="font-weight: bold;">批示材料:</p>
			${pscl }
			<!-- 			<button type="button" class="bttn-1" style="float: left;" -->
			<!-- 				onclick="edit('editDxalPscl.shtml?name=1')">修改</button> -->
		</div>

		<div class="fwjy" style="${zjcl != '' ? '' : 'display:none;'}">
			<p style="font-weight: bold;">服务经验:</p>
			${zjcl }
			<!-- 			<button type="button" class="bttn-1" style="float: left;" -->
			<!-- 				onclick="edit('editDxalPscl.shtml?name=1')">修改</button> -->
		</div>
	</div>
	<script type="text/javascript">
		$(function() {
			var array = new Array();
			<c:forEach items="${other}" var="other_">//js中可以使用此标签，将EL表达式中的值push到数组中  
			if ("${other_.fileName}".indexOf("批示材料") != -1
					|| "${other_.fileName}".indexOf("服务经验") != -1) {
			} else {
				array.push("${other_.fileName}");
			}
			</c:forEach>
			if(array.length > 0){
				$(".other").show();
			}else{
				$(".other").hide();
			}
		});
	</script>
	<input type="hidden" id="deleteDxalFileName" value="" />
	<div id="context-menu">
		<ul class="dropdown-menu" role="menu">
			<li><a href="javascript:void(0)" id="delDxalFile">删除</a></li>
		</ul>
	</div>

	<script type="text/javascript">
		function edit(url) {
			BootstrapDialog
					.show({
						title : '编辑',
						message : $('<iframe src="'
								+ url
								+ '" width="100%" height="170px" name="dxalEdit" frameborder="0" scrolling="yes"></iframe>'),
						buttons : [ {
							label : '确定',
							cssClass : 'btn-primary',
							action : function(dialogItself) {
								var content = dxalEdit.dxalfroalaEditor();
								$
										.ajax({
											url : 'saveDxalUrlParam.shtml',
											type : 'POST',
											data : {
												"contents" : content
											},
											dataType : 'json',
											success : function(data) {
												if (data.zjorps == "总结材料") {
													location.href = "saveDxalZjcl.shtml";
												} else {
													location.href = "saveDxalPscl.shtml";
												}
											}
										});
								dialogItself.close();
							}
						} ]
					})
		}
	</script>
</body>
</html>