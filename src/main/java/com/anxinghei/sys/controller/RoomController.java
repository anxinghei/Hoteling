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
			System.out.println(canBook);
			if (canBook) {
				return "success";
			}
		}
		return "failed";
		
	}

}