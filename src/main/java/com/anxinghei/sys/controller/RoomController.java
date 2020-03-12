package com.anxinghei.sys.controller;

//import com.github.wxiaoqi.security.common.rest.BaseController;
//import com.anxinghei.sys.biz.RoomBiz;
import com.anxinghei.sys.entity.Room;
import com.anxinghei.sys.mapper.RoomMapper;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// extends BaseController<RoomBiz,Room>

@RestController
@RequestMapping("room")
public class RoomController  {
	
	@Autowired
	private RoomMapper roomMapper;
	
	@GetMapping("getAllByType/{typeid}")
	public List<Room> getAllByType(@PathVariable("typeid")Integer typeid){
		Example example=new Example(Room.class);
		Criteria criteria=example.createCriteria();
		criteria.andEqualTo("typeid", typeid)
				.andEqualTo("bookid",0);
		List<Room> rooms=roomMapper.selectByExample(example);
		return rooms;
	}

}