package com.anxinghei.sys.controller;

import com.anxinghei.sys.entity.Facility;
//import com.github.wxiaoqi.security.common.rest.BaseController;
//import com.anxinghei.sys.biz.RoomfacilitiesBiz;
import com.anxinghei.sys.entity.Roomfacilities;
import com.anxinghei.sys.mapper.FacilityMapper;
import com.anxinghei.sys.mapper.RoomfacilitiesMapper;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// extends BaseController<RoomfacilitiesBiz,Roomfacilities>

@RestController
@RequestMapping("roomfacilities")
public class RoomfacilitiesController  {


	@Autowired
	private FacilityMapper facilityMapper;
	@Autowired
	private RoomfacilitiesMapper roomfacilitiesMapper;
	
	@GetMapping("GetOnes/{typeid}")
	public StringBuilder getFacilitiesofType(@PathVariable("typeid") Integer typeid){
		// 查找当前房间类别的所有设施
		Example example=new Example(Roomfacilities.class);
		Criteria criteria=example.createCriteria();
		criteria.andEqualTo("typeId", typeid);
		List<Roomfacilities> facilityids=roomfacilitiesMapper.selectByExample(example);
		// 查到对应的设施，加入列表中
		StringBuilder facilities=new StringBuilder();
		Facility facility=new Facility();
		for (Roomfacilities roomfacilities : facilityids) {
			facility=facilityMapper.selectByPrimaryKey(roomfacilities.getFacilityid());
			facilities.append("，"+facility.getName());
		}
		return facilities;
	}
}