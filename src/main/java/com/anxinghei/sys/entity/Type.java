package com.anxinghei.sys.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 房间类别表
 * 
 * @author Autumn
 * @email 
 * @date 2020-03-10 20:31:52
 */
@Table(name = "type")
public class Type implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //可住人数
    @Column(name = "lives")
    private Integer lives;
	
	    //价格
    @Column(name = "price")
    private Integer price;
	
	    //房间大小
    @Column(name = "area")
    private Integer area;
	

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
	 * 设置：可住人数
	 */
	public void setLives(Integer lives) {
		this.lives = lives;
	}
	/**
	 * 获取：可住人数
	 */
	public Integer getLives() {
		return lives;
	}
	/**
	 * 设置：价格
	 */
	public void setPrice(Integer price) {
		this.price = price;
	}
	/**
	 * 获取：价格
	 */
	public Integer getPrice() {
		return price;
	}
	/**
	 * 设置：房间大小
	 */
	public void setArea(Integer area) {
		this.area = area;
	}
	/**
	 * 获取：房间大小
	 */
	public Integer getArea() {
		return area;
	}
}
