package com.anxinghei.sys.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 房间打折表
 * 
 * @author Autumn
 * @email 
 * @date 2020-03-10 20:31:52
 */
@Table(name = "band")
public class Band implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //房间类别
    @Column(name = "typeId")
    private Integer typeid;
	
	    //折扣
    @Column(name = "discount")
    private Integer discount;
	
	    //打折开始时间
    @Column(name = "startDay")
    private String startday;
	
	    //打折结束时间
    @Column(name = "endDay")
    private String endday;
	
	    //打折缘故
    @Column(name = "description")
    private String description;
	

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
	 * 设置：房间类别
	 */
	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}
	/**
	 * 获取：房间类别
	 */
	public Integer getTypeid() {
		return typeid;
	}
	/**
	 * 设置：折扣
	 */
	public void setDiscount(Integer discount) {
		this.discount = discount;
	}
	/**
	 * 获取：折扣
	 */
	public Integer getDiscount() {
		return discount;
	}
	/**
	 * 设置：打折开始时间
	 */
	public void setStartday(String startday) {
		this.startday = startday;
	}
	/**
	 * 获取：打折开始时间
	 */
	public String getStartday() {
		return startday;
	}
	/**
	 * 设置：打折结束时间
	 */
	public void setEndday(String endday) {
		this.endday = endday;
	}
	/**
	 * 获取：打折结束时间
	 */
	public String getEndday() {
		return endday;
	}
	/**
	 * 设置：打折缘故
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * 获取：打折缘故
	 */
	public String getDescription() {
		return description;
	}
}
