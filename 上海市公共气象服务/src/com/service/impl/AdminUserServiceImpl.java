/**
 * 
 */
package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.AdminUserDao;
import com.entity.AdminUser;
import com.service.AdminUserService;

/**
 * @author 分裂状态。
 *
 */
@Service
public class AdminUserServiceImpl implements AdminUserService {

	@Autowired
	private AdminUserDao dao;

	public AdminUser doLogin(String userName, String userPwd) {
		return dao.doLogin(userName, userPwd);
	}

	public void addUser(AdminUser user) {
		dao.save(user);
	}

	public void updateUser(String userPwd, String userName) {
		dao.updateUser(userPwd, userName);
	}

}
