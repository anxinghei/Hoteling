package com.anxinghei.sys.controller;

import com.anxinghei.sys.entity.Book;
//import com.github.wxiaoqi.security.common.rest.BaseController;
//import com.anxinghei.sys.biz.RoomBiz;
import com.anxinghei.sys.entity.Room;
import com.anxinghei.sys.mapper.BookMapper;
import com.anxinghei.sys.mapper.RoomMapper;
import com.anxinghei.sys.util.DateUtils;
import com.anxinghei.sys.vo.BookVo;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// extends BaseController<RoomBiz,Room>

@RestController
@RequestMapping("room")
public class RoomController  {
	
	@Autowired
	private RoomMapper roomMapper;
	@Autowired
	private BookMapper bookMapper;
	
	@GetMapping("getAllByType/{typeid}")
	public List<Room> getAllByType(@PathVariable("typeid")Integer typeid){
		Example example=new Example(Room.class);
		Criteria criteria=example.createCriteria();
		criteria.andEqualTo("typeid", typeid);
		List<Room> rooms=roomMapper.selectByExample(example);
		return rooms;
	}
	
	@PostMapping("isBooked")
	public String isBooked(@RequestBody BookVo vo) {
		System.out.println(vo);
		// 得到所有与该房间相关的订单
		int roomNum;
		if (vo.getRoomNum()==null) {
			roomNum=vo.getRoom().getNum();
		}else {
			roomNum=vo.getRoomNum();
		}
		Book roomBook=new Book();
		roomBook.setRoomNum(roomNum);
		List<Book> books=bookMapper.select(roomBook);
		System.out.println(books);
		if (books.isEmpty()) {
			return "success";
		}
		// 查看是否时间冲突
		boolean canBook=true;
		for (Book book : books) {
			canBook=DateUtils.isBooked(vo.getStartday(), vo.getEndday(), book.getStartday(), book.getEndday());
			if (canBook) {
				return "success";
			}
		}
		return "failed";
	}
	
	@PostMapping("getAllByBookid")
	public List<Room> getRooms(@RequestBody BookVo vo){
		System.out.println(vo);
		// 查到订单对应的房间
		Room record=new Room();
		record.setNum(vo.getRoomNum());
		record=roomMapper.selectOne(record);
		// 查到该房间对应类别的所有房间
		Room room=new Room();
		room.setTypeid(record.getTypeid());
		List<Room> rooms=roomMapper.select(room);
		// 找到时间不冲突的所有房间
		List<Room> roomList=new ArrayList<>();
		roomList.add(record);
		List<Book> books=new ArrayList<>();
		Book book=new Book();
		for (Room room2 : rooms) {
			if (room2.getBookid()==0) {
				roomList.add(room2);
			}else {
				// 得到所有与该房间相关的订单
				book.setRoomNum(room2.getNum());
				books=bookMapper.select(book);
				// 查看是否时间冲突
				boolean canBook=true;
				for (int i=0;i<books.size();i++) {
					canBook=DateUtils.isBooked(vo.getStartday(), vo.getEndday(), books.get(i).getStartday(), books.get(i).getEndday());
					if (canBook) {
						// 最后一个订单也是不冲突的
						if (i==books.size()) {
							roomList.add(room2);
						}
					}else {
						break;
					}
				}
			}
		}
		return roomList;
	}

}