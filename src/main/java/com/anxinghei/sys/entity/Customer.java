package com.anxinghei.sys.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 顾客表，打电话预订的人
 * 
 * @author Autumn
 * @email 
 * @date 2020-03-10 20:31:53
 */
@Table(name = "customer")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //顾客姓名
    @Column(name = "name")
    private String name;
	
	    //顾客性别，0男1女
    @Column(name = "sex")
    private Integer sex;
	
	    //顾客电话
    @Column(name = "phone")
    private String phone;
	

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：顾客姓名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：顾客姓名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：顾客性别，0男1女
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	/**
	 * 获取：顾客性别，0男1女
	 */
	public Integer getSex() {
		return sex;
	}
	/**
	 * 设置：顾客电话
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取：顾客电话
	 */
	public String getPhone() {
		return phone;
	}
}
