package com.anxinghei.sys.vo;

public class LoginVo {
	
	private String username;
	private String password;
	private String identify;
	private boolean rememberMe;
	
	
	@Override
	public String toString() {
		return "LoginVo [username=" + username + ", password=" + password + ", identify=" + identify + ", rememberMe="
				+ rememberMe + "]";
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIdentify() {
		return identify;
	}
	public void setIdentify(String identify) {
		this.identify = identify;
	}
	public boolean getRememberMe() {
		return rememberMe;
	}
	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}
	
	

}
