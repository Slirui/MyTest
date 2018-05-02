/**
 * 
 */
package com.service;

import com.entity.AdminUser;

/**
 * @author 分裂状态。
 *
 */
public interface AdminUserService {

	public AdminUser doLogin(String userName, String userPwd);

	
	public void addUser(AdminUser user);
	
	public void updateUser(String userPwd, String userName);
}
