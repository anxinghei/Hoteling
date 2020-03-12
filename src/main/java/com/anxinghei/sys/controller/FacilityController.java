package com.anxinghei.sys.controller;

//import com.github.wxiaoqi.security.common.rest.BaseController;
//import com.anxinghei.sys.biz.FacilityBiz;
import com.anxinghei.sys.entity.Facility;
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

// extends BaseController<FacilityBiz,Facility>

@RestController
@RequestMapping("facility")
public class FacilityController  {
	

}