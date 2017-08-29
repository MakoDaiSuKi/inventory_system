package com.inventory.model;


import com.inventory.model.base.*;
@SuppressWarnings("serial")
public class User extends BaseUser<User> {
	
	public static final User dao = new User().dao();
	/**
	 * 所有 sql 与业务逻辑写在 Service 中，在此放在 Model 中仅为示例，
	 * 不要写在 Controller 中，养成好习惯，有利于大型项目的开发与维护
	 */
	public User getPasswordByUsername(String userName) {
		String sql = "select * from sys_user where username = ?";
		User user = User.dao.findFirst(sql, userName);
		return  user;
	}
	public User getUserName(String userName) {
		String sql = "select * from sys_user where username = ?";
		User user = User.dao.findFirst(sql, userName);
		return  user;
	}
}
