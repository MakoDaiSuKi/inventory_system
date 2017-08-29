package com.inventory.service;

import java.util.List;

import com.inventory.model.User;
import com.jfinal.plugin.activerecord.*;

public class UserService {
    /**
     * 查询用户名和密码是否正确
     * @return true or false
     */
  public User checkPassWord(String userName,String passWord) {
	  User user = User.dao.getPasswordByUsername(userName);
	  return user;
	  
  }
  public boolean checkUserName(String userName){
	  try{
		  if(User.dao.getUserName(userName)==null){
			  return true;
		  } else {
			  return false;
		  }
	  }catch(Exception e){
		  System.out.println(e);
		  return false;
	  }
  }
  public boolean addUser(String userName, String passWord){
	  try{
		  Record user = new Record().set("username", userName).set("password", passWord).set("power", 0);
		  Db.save("sys_user", user);
		  return true;
	  }catch(Exception e){
		  System.out.println(e);
		  return false;
	  }
  }
  public boolean changePowerById(Integer id,Integer power){
	  try{
		  Record user = Db.findById("sys_user", id).set("power", power);
		  Db.update("user", user);
		  return true;
	  }catch(Exception e){
		  System.out.println(e);
		  return false;
	  }
  }
  public boolean changePasswordById(Integer id,String passWord){
	  try{
		  Record user = Db.findById("sys_user", id).set("password", passWord);
		  Db.update("user", user);
		  return true;
	  }catch(Exception e){
		  System.out.println(e);
		  return false;
	  }
  }
}
