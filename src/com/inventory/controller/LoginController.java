package com.inventory.controller;

import com.jfinal.aop.Before;
import com.jfinal.ext.interceptor.Restful;
import com.jfinal.plugin.activerecord.Record;
import com.inventory.dto.JsonResult;
import com.inventory.model.User;


import com.inventory.common.BaseController;
import com.inventory.service.UserService;

@Before({Restful.class})
public class LoginController extends BaseController{
  private UserService userservice = this.enhance(UserService.class);
  //登录
  public void index() {
	  String userName = getPara("userName");
	  String passWord = getPara("passWord");
	  if (userName == null || passWord == null) {
		  JsonResult result = new JsonResult("0", "fail", "用户名或密码不能为空！");
		  renderJson(result);
	  } else {
		  User user = userservice.checkPassWord(userName,passWord);
		  String password = user.getStr("password");
		  int id= user.getId();
		  if(password.equals(passWord)) {
			  JsonResult result = new JsonResult("200", "succeed", user);
			  renderJson(result);
		  } else {
			  JsonResult result = new JsonResult("0", "fail", "登录失败！");
			  renderJson(result);
		  }
	  }
  }
  //注册
  public void register() {
	  String userName = getPara("userName");
	  String passWord = getPara("passWord");
	  if(userservice.checkUserName("userName")){  //判断userName是否已经存在
		  if(userservice.addUser(userName,passWord)){
			  JsonResult result = new JsonResult("200", "注册成功！");
			  renderJson(result);
		  } else {
			  JsonResult result = new JsonResult("0", "注册失败！");
			  renderJson(result);
		  }
	  } else {
		  JsonResult result = new JsonResult("0", "fail", "用户名已存在，注册失败！");
		  renderJson(result);
	  }
  }
}
