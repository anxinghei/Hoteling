package com.anxinghei.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anxinghei.sys.entity.Facility;
import com.anxinghei.sys.mapper.FacilityMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


@RestController
@RequestMapping("facility")
public class FacilityController  {
	
	@Autowired
	private FacilityMapper facilityMapper;
	
	@GetMapping("findAll/{start}/{size}")
	PageInfo<Facility> findAll(@PathVariable("start") Integer start,@PathVariable("size") Integer size){
		PageHelper.startPage(start,size);
		List<Facility> facilitys=facilityMapper.selectAll();
		PageInfo<Facility> pageInfo=new PageInfo<Facility>(facilitys);	
		return pageInfo;
	}
	
	@DeleteMapping("deleteById/{id}")
	public String deleteById(@PathVariable("id")Integer id) {
		int isDel=facilityMapper.deleteByPrimaryKey(id);
		if (isDel==1) {
			return "success";
		}
		return "error";
	}
	
	@PostMapping("save")
	public String save(@RequestBody Facility facility) {
		int isAdded=facilityMapper.insert(facility);
		if (isAdded==1) {
			return "success";
		}
		return "error";
	}
	
	@PostMapping("update")
	public String update(@RequestBody Facility facility) {
		int isUpdated=facilityMapper.updateByPrimaryKey(facility);
		if (isUpdated==1) {
			return "success";
		}
		return "error";
	}

}