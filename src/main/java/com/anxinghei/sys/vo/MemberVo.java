package com.anxinghei.sys.vo;

import java.util.List;

import com.anxinghei.sys.entity.Permission;
import com.anxinghei.sys.entity.Sysuser;

public class MemberVo {

	private static Sysuser sysuser=null;
	private Sysuser user;
	private List<Permission> firstRules;
	private List<Permission> secondRules;
	
	
	
	public Sysuser getUser() {
		return user;
	}
	public void setUser(Sysuser user) {
		this.user = user;
	}
	public static Sysuser getSysuser() {
		return sysuser;
	}
	public static void setSysuser(Sysuser user) {
		sysuser = user;
	}
	public  List<Permission> getFirstRules() {
		return firstRules;
	}
	public  void setFirstRules(List<Permission> Rules) {
		firstRules = Rules;
	}
	public  List<Permission> getSecondRules() {
		return secondRules;
	}
	public  void setSecondRules(List<Permission> Rules) {
		secondRules = Rules;
	}
	@Override
	public String toString() {
		return "MemberVo [sysuser=" + sysuser + ", firstRules=" + firstRules + ", secondRules=" + secondRules + "]";
	}
	
	
}
