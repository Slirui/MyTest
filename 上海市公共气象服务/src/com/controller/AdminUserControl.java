/**
 * 
 */
package com.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.AdminUser;
import com.service.AdminUserService;

/**
 * @author 分裂状态。
 *
 */
@Controller
public class AdminUserControl {

	@Autowired
	private AdminUserService service;

	@RequestMapping("login.shtml")
	public String login(HttpSession session, Model model, String userName, String userPwd) {
		String page = null;
		AdminUser user = service.doLogin(userName, userPwd);
		if (user != null) {
			page = "redirect:weather.jsp";
			session.setAttribute("userName", user.getUserName());
			session.setAttribute("userPwd", user.getUserPwd());
			session.setMaxInactiveInterval(3600 * 2);
		} else {
			page = "login";
			String errorMsg = "用户名或密码错误";
			model.addAttribute("errorMsg", errorMsg);
		}
		return page;
	}

	@RequestMapping("register.shtml")
	@ResponseBody
	public Map<Object, Object> register(Model model, String userName, String userPwd) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		AdminUser user = new AdminUser();
		user.setUserName(userName);
		user.setUserPwd(userPwd);
		service.addUser(user);
		map.put("resultCode", 200);
		return map;
	}

	@RequestMapping("updatePwd.shtml")
	@ResponseBody
	public Map<Object, Object> updatePwd(Model model, String userName, String userPwd) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		service.updateUser(userPwd, userName);
		map.put("resultCode", 200);
		return map;
	}

	@RequestMapping("loginout.shtml")
	public String loginout(HttpSession session) {
		session.removeAttribute("userName");
		session.removeAttribute("userPwd");
		return "redirect:login.jsp";
	}
}
