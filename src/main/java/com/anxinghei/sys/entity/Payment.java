package com.anxinghei.sys.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 支付表，收账记录
 * 
 * @author Autumn
 * @email 
 * @date 2020-03-12 23:51:13
 */
@Table(name = "payment")
public class Payment implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //入住者
    @Column(name = "guest")
    private String guest;
	
	    //支付金额
    @Column(name = "amount")
    private Integer amount;
	
	    //付款时间
    @Column(name = "date")
    private String date;
	
	@Column(name = "roomNum")
	private Integer roomNum;
	
	
    
	public Payment() {
		super();
	}

	public Payment(Integer id, String guest, Integer amount, String date, Integer roomNum) {
		super();
		this.id = id;
		this.guest = guest;
		this.amount = amount;
		this.date = date;
		this.roomNum = roomNum;
	}

	public Payment(String guest, Integer amount, String date, Integer roomNum) {
		super();
		this.guest = guest;
		this.amount = amount;
		this.date = date;
		this.roomNum = roomNum;
	}

	public Integer getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(Integer roomNum) {
		this.roomNum = roomNum;
	}

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
	 * 设置：入住者
	 */
	public void setGuest(String guest) {
		this.guest = guest;
	}
	/**
	 * 获取：入住者
	 */
	public String getGuest() {
		return guest;
	}
	/**
	 * 设置：支付金额
	 */
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	/**
	 * 获取：支付金额
	 */
	public Integer getAmount() {
		return amount;
	}
	/**
	 * 设置：付款时间
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * 获取：付款时间
	 */
	public String getDate() {
		return date;
	}


	
}
