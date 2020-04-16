package com.anxinghei.sys.vo;

import java.util.List;

import com.anxinghei.sys.entity.Permission;

public class PermissionVo {
	
	private String name;
	private List<Permission> permissions;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Permission> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}
	public PermissionVo(String name, List<Permission> permissions) {
		super();
		this.name = name;
		this.permissions = permissions;
	}
	
	
}
