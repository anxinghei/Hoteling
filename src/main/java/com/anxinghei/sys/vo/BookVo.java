package com.anxinghei.sys.vo;

import com.anxinghei.sys.entity.Room;

public class BookVo {
	
	private Integer bookid;
	// 房间，从前端获取，用于订单的添加
	private Room room;
	// 房间号，展示用
	private Integer roomNum;
	private String customername;
	private String customerphone;
	private String guestname;
	private String guestphone;
	private String startday;
	private String endday;
	// 房间名
	private String roomtype;
	// 房间类型
	private Integer typeid;
	
	
	public Integer getTypeid() {
		return typeid;
	}
	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}

	public String getRoomtype() {
		return roomtype;
	}
	public void setRoomtype(String roomtype) {
		this.roomtype = roomtype;
	}
	public String getCustomername() {
		return customername;
	}
	public void setCustomername(String customername) {
		this.customername = customername;
	}
	public String getCustomerphone() {
		return customerphone;
	}
	public void setCustomerphone(String customerphone) {
		this.customerphone = customerphone;
	}
	public String getGuestphone() {
		return guestphone;
	}
	public void setGuestphone(String guestphone) {
		this.guestphone = guestphone;
	}
	public Integer getBookid() {
		return bookid;
	}
	public void setBookid(Integer bookid) {
		this.bookid = bookid;
	}
	public Integer getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(Integer roomNum) {
		this.roomNum = roomNum;
	}
	public String getGuestname() {
		return guestname;
	}
	public void setGuestname(String guestname) {
		this.guestname = guestname;
	}
	public String getStartday() {
		return startday;
	}
	public void setStartday(String startday) {
		this.startday = startday;
	}
	public String getEndday() {
		return endday;
	}
	public void setEndday(String endday) {
		this.endday = endday;
	}
	@Override
	public String toString() {
		return "BookVo [bookid=" + bookid + ", room=" + room + ", roomNum=" + roomNum + ", customername=" + customername
				+ ", customerphone=" + customerphone + ", guestname=" + guestname + ", guestphone=" + guestphone
				+ ", startday=" + startday + ", endday=" + endday + ", roomtype=" + roomtype + ", typeid=" + typeid
				+ "]";
	}

}
