package com.anxinghei.sys.controller;

import com.anxinghei.sys.entity.Facility;
import com.anxinghei.sys.entity.Guest;
import com.anxinghei.sys.entity.Type;
import com.anxinghei.sys.mapper.FacilityMapper;
import com.anxinghei.sys.mapper.GuestMapper;
import com.anxinghei.sys.mapper.TypeMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// extends BaseController<TypeBiz,Type>

@RestController
@RequestMapping("type")
public class TypeController  {
	
	@Autowired
	private TypeMapper typeMapper;
	@Autowired
	private FacilityMapper facilityMapper;
	
	@GetMapping("/getAll")
	public List getAll(){
		return typeMapper.selectAll();
	}

	@GetMapping("findAll/{start}/{size}")
	PageInfo<Type> findAll(@PathVariable("start") Integer start,@PathVariable("size") Integer size){
		PageHelper.startPage(start,size);
		List<Type> types=typeMapper.selectAll();
		PageInfo<Type> pageInfo=new PageInfo<Type>(types);	
		return pageInfo;
	}
	
	@GetMapping("findById/{id}")
	public Type findById(@PathVariable("id")Integer id) {
		return typeMapper.selectByPrimaryKey(id);
	}
	
	@PutMapping("update")
	public String update(@RequestBody Type type) {
		int isUpdate=typeMapper.updateByPrimaryKey(type);
		if (isUpdate!=0) {
			return "success";
		}
		return "error";
	}
	
	@GetMapping("getFacilities/{typeid}")
	public StringBuilder getFacilitiesofType(@PathVariable("typeid") Integer typeid){
		StringBuilder facilities=new StringBuilder();
		// 查找当前房间类别的所有设施
		String ids=typeMapper.selectByPrimaryKey(typeid).getFacilities();
		// 查到对应的设施，加入列表中
		String[] idList=ids.split(",");
		for (String id : idList) {
			facilities.append(','+facilityMapper.selectByPrimaryKey(id).getName());
		}
		return facilities;
	}
	
	@GetMapping("getFacilitiyIds/{typeid}")
	public List<Integer> getFacilitiyIds(@PathVariable("typeid") Integer typeid){
		List<Integer> ids=new ArrayList<Integer>();
		String idString=typeMapper.selectByPrimaryKey(typeid).getFacilities();
		String[] idList=idString.split(",");
		for (String id : idList) {
			ids.add(Integer.valueOf(id));
		}
		return ids;
	}
	
	@GetMapping("getNameById/{typeid}")
	public String getNameById(@PathVariable("typeid") Integer typeid) {
		return typeMapper.selectByPrimaryKey(typeid).getName();
	}
	
	@PutMapping("save/{typeid}")
	public String updateFacilities(@PathVariable("typeid") Integer typeid,@RequestBody List<Integer> ids) {
		System.out.println(ids);
		String idString="";
		for (int i = 0; i < ids.size(); i++) {
			idString=idString+","+ids.get(i);
		}
		idString=idString.substring(1);
		
		System.out.println(idString);
		Type record=typeMapper.selectByPrimaryKey(typeid);
		record.setFacilities(idString);
		int isOK=typeMapper.updateByPrimaryKey(record);
		if (isOK==1) {
			return "success";
		}
		return "error";
	}
	
}