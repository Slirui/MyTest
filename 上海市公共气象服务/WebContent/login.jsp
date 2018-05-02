<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上海决策气象服务知识库登录页面</title>
<link rel="stylesheet" type="text/css" href="commoncss/login.css">
<script type="text/javascript">
	var errorMsg = "${errorMsg}";
	if (window.top.location.href.indexOf("login.jsp") == -1 && errorMsg == "") {
		window.top.location.href = "login.jsp";
	}
</script>
</head>
<body style="background-color: #098a6f">
	<!-- style="background-color: #098a6f" -->
	<form action="login.shtml" method="post">
		<div class="head">
			<img class="logo" src="images/title.png" />
		</div>

		<img class="loginbg" src="images/login_bg.png" />

		<div id="login" class="login">
			<p class="logintit">账号登陆</p>
			<div class="zhdl">
				<ul>
					<li>
						<div class="zhdl_up">用户</div>
						<div>
							<input name="userName" id="userName" value="admin">
						</div>
					</li>
					<li>
						<div class="zhdl_up">密码</div>
						<div>
							<input name="userPwd" type="password" id="password" value="admin">
						</div>
					</li>
					<li>
						<div class="zhdl_btn">
							<button type="submit" class="submitForm">账号登陆</button>
						</div>
					</li>
					<li><div class="zhdl_btn" style="color: red;">${errorMsg }</div></li>
				</ul>
			</div>
			<div class="loginfoot">
				<img src="images/danger.png" />
				<p>如需帐户登录，请联系管理员admin</p>
			</div>
		</div>
		<div style="width: 100%; height: 80px; background-color: #fff;"></div>

	</form>
</body>
</html>