package com.anxinghei.sys.controller;

//import com.github.wxiaoqi.security.common.rest.BaseController;
//import com.anxinghei.sys.biz.BandBiz;
import com.anxinghei.sys.entity.Band;
import com.anxinghei.sys.mapper.BandMapper;
import com.anxinghei.sys.mapper.BandVoMapper;
import com.anxinghei.sys.vo.BandVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	@PostMapping("isBanded")
	public String isBanded(@RequestBody BandVo bandVo) {
		System.out.println(bandVo);
		// 查找所有同类别的折扣
		Band band=new Band();
		band.setTypeid(bandVo.getTypeid());
		List<Band> bands=bandMapper.select(band);
		// 判定是否是包含关系
		for (Band band2 : bands) {
			if ((bandVo.getStartday().compareTo(band2.getStartday())>=0 && bandVo.getEndday().compareTo(band2.getEndday())<=0)) {
				return "error";
			}
		}		
		return "success";
	}
	
	@PostMapping("save")
	public String save(@RequestBody BandVo bandVo) {
		Band band=new Band();
		band.setTypeid(bandVo.getTypeid());
		band.setStartday(bandVo.getStartday());
		band.setEndday(bandVo.getEndday());
		band.setDiscount(bandVo.getDiscount());
		if (bandVo.getDescription()!=null) {
			band.setDescription(bandVo.getDescription());
		}
		int isOK=bandMapper.insert(band);
		if (isOK==1) {
			return "success";
		}
		return "error";
	}
}