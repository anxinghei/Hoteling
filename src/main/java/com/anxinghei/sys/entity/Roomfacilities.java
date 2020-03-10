package com.anxinghei.sys.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 房间的设施表
 * 
 * @author Autumn
 * @email 
 * @date 2020-03-10 20:31:52
 */
@Table(name = "roomfacilities")
public class Roomfacilities implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //房间号
    @Column(name = "roomId")
    private Integer roomid;
	
	    //设施
    @Column(name = "facilityId")
    private Integer facilityid;
	
	    //
    @Id
    private Integer id;
	

	/**
	 * 设置：房间号
	 */
	public void setRoomid(Integer roomid) {
		this.roomid = roomid;
	}
	/**
	 * 获取：房间号
	 */
	public Integer getRoomid() {
		return roomid;
	}
	/**
	 * 设置：设施
	 */
	public void setFacilityid(Integer facilityid) {
		this.facilityid = facilityid;
	}
	/**
	 * 获取：设施
	 */
	public Integer getFacilityid() {
		return facilityid;
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
}
