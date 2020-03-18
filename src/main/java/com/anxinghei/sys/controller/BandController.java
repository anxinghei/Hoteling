package com.anxinghei.sys.controller;

//import com.github.wxiaoqi.security.common.rest.BaseController;
//import com.anxinghei.sys.biz.BandBiz;
import com.anxinghei.sys.entity.Band;
import com.anxinghei.sys.mapper.BandMapper;
import com.anxinghei.sys.mapper.BandVoMapper;
import com.anxinghei.sys.vo.BandVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	@Autowired
	private BandVoMapper bandVoMapper;

	@GetMapping("findAll/{start}/{size}")
	PageInfo<BandVo> findAll(@PathVariable("start") Integer start,@PathVariable("size") Integer size){
		PageHelper.startPage(start,size);
		List<BandVo> bands=bandVoMapper.getAll();
		PageInfo<BandVo> pageInfo=new PageInfo<BandVo>(bands);	
		return pageInfo;
	}
	
	@DeleteMapping("deleteById/{id}")
	public void deleteById(@PathVariable("id")Integer id) {
		bandMapper.deleteByPrimaryKey(id);
	}
}