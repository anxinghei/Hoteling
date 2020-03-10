package com.anxinghei.sys.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 支付表，收账记录
 * 
 * @author Autumn
 * @email 
 * @date 2020-03-10 20:31:52
 */
@Table(name = "payment")
public class Payment implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //支付金额
    @Column(name = "amount")
    private Integer amount;
	
	    //订单号
    @Column(name = "bookId")
    private Integer bookid;
	
	    //是否支付，0否1是
    @Column(name = "isPayed")
    private Integer ispayed;
	

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
	 * 设置：订单号
	 */
	public void setBookid(Integer bookid) {
		this.bookid = bookid;
	}
	/**
	 * 获取：订单号
	 */
	public Integer getBookid() {
		return bookid;
	}
	/**
	 * 设置：是否支付，0否1是
	 */
	public void setIspayed(Integer ispayed) {
		this.ispayed = ispayed;
	}
	/**
	 * 获取：是否支付，0否1是
	 */
	public Integer getIspayed() {
		return ispayed;
	}
}
