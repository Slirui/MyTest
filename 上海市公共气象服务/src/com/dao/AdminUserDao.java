/**
 * 
 */
package com.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.entity.AdminUser;

/**
 * @author 分裂状态。
 *
 */
public interface AdminUserDao extends GenericRepository<AdminUser, Integer> {

	@Query("select a from AdminUser a where userName = ?1 and userPwd = ?2")
	public AdminUser doLogin(String userName, String userPwd);

	@Modifying
	@Query("update AdminUser set userPwd = ? where userName = ?")
	public void updateUser(String userPwd, String userName);

}
