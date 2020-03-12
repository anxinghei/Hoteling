package com.anxinghei.sys.vo;

public class BookVo {
	
	private Integer bookid;
	private Integer roomNum;
	private String customername;
	private String customerphone;
	private String guestname;
	private String guestphone;
	private String startday;
	private String endday;
	private Integer roomtype;
	


	public Integer getRoomtype() {
		return roomtype;
	}
	public void setRoomtype(Integer roomtype) {
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
		return "BookVo [bookid=" + bookid + ", roomNum=" + roomNum + ", customername=" + customername
				+ ", customerphone=" + customerphone + ", guestname=" + guestname + ", guestphone=" + guestphone
				+ ", startday=" + startday + ", endday=" + endday + "]";
	}
	
}
