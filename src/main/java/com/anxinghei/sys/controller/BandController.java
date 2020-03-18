package com.anxinghei.sys.controller;

//import com.github.wxiaoqi.security.common.rest.BaseController;
//import com.anxinghei.sys.biz.BandBiz;
import com.anxinghei.sys.entity.Band;
import com.anxinghei.sys.mapper.BandMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// extends BaseController<BandBiz,Band>

@RestController
@RequestMapping("band")
public class BandController  {

	@Autowired
	private BandMapper bandMapper;

	@GetMapping("findAll/{start}/{size}")
	PageInfo<Band> findAll(@PathVariable("start") Integer start,@PathVariable("size") Integer size){
		PageHelper.startPage(start,size);
		List<Band> bands=bandMapper.selectAll();
		PageInfo<Band> pageInfo=new PageInfo<Band>(bands);	
		return pageInfo;
	}
}