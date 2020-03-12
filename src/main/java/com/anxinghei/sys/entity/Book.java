package com.anxinghei.sys.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 订单表
 * 
 * @author Autumn
 * @email 
 * @date 2020-03-10 20:31:52
 */
@Table(name = "book")
public class Book implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //房间号
    @Column(name = "roomNum")
    private Integer roomNum;
	
	    //入住客人
    @Column(name = "guestId")
    private Integer guestid;
	
	    //顾客
    @Column(name = "customerId")
    private Integer customerid;
	
	    //开始入住时间
    @Column(name = "startDay")
    private String startday;
	
	    //住店结束时间
    @Column(name = "endDay")
    private String endday;
	

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
	 * 设置：房间号
	 */
	public void setRoomNum(Integer roomNum) {
		this.roomNum = roomNum;
	}
	/**
	 * 获取：房间号
	 */
	public Integer getRoomNum() {
		return roomNum;
	}
	/**
	 * 设置：入住客人
	 */
	public void setGuestid(Integer guestid) {
		this.guestid = guestid;
	}
	/**
	 * 获取：入住客人
	 */
	public Integer getGuestid() {
		return guestid;
	}
	/**
	 * 设置：顾客
	 */
	public void setCustomerid(Integer customerid) {
		this.customerid = customerid;
	}
	/**
	 * 获取：顾客
	 */
	public Integer getCustomerid() {
		return customerid;
	}
	/**
	 * 设置：开始入住时间
	 */
	public void setStartday(String startday) {
		this.startday = startday;
	}
	/**
	 * 获取：开始入住时间
	 */
	public String getStartday() {
		return startday;
	}

	public String getEndday() {
		return endday;
	}
	public void setEndday(String endday) {
		this.endday = endday;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", roomNum=" + roomNum + ", guestid=" + guestid + ", customerid=" + customerid
				+ ", startday=" + startday + ", endday=" + endday + "]";
	}
	
}
