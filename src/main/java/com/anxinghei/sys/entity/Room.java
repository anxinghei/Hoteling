package com.anxinghei.sys.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 房间表
 * 
 * @author Autumn
 * @email 
 * @date 2020-03-10 20:31:52
 */
@Table(name = "room")
public class Room implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //订单号
    @Column(name = "bookId")
    private Integer bookid;
	
	    //房间类别
    @Column(name = "typeId")
    private Integer typeid;
	

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
}
