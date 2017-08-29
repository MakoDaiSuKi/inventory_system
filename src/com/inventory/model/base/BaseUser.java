package com.inventory.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseUser <M extends BaseUser<M>> extends Model<M> implements IBean {
	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}

	public java.lang.Integer getId() {
		return get("id");
	}
	
	public M setUserName(java.lang.String username) {
		set("username", username);
		return (M)this;
	}
	
	public java.lang.String getUsername() {
		return get("username");
	}

	public M setPassWord(java.lang.String password) {
		set("password", password);
		return (M)this;
	}

	public java.lang.String getPassWord() {
		return get("password");
	}
	
	public M setPower(java.lang.Integer power) {
		set("power", power);
		return (M)this;
	}

	public java.lang.Integer getPower() {
		return get("power");
	}
}
