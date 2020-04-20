package com.anxinghei.sys.controller;

import com.anxinghei.sys.entity.Band;
import com.anxinghei.sys.entity.Type;
import com.anxinghei.sys.mapper.BandVoMapper;
import com.anxinghei.sys.service.BandService;
import com.anxinghei.sys.service.TypeService;
import com.anxinghei.sys.vo.BandVo;
import com.anxinghei.sys.vo.BookVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("band")
public class BandController  {

	@Autowired
	private BandVoMapper bandVoMapper;
	@Autowired
	private TypeService typeService;
	@Autowired
	private BandService bandService;

	@GetMapping("findAll/{start}/{size}")
	PageInfo<BandVo> findAll(@PathVariable("start") Integer start,@PathVariable("size") Integer size){
		PageHelper.startPage(start,size);
		List<BandVo> bands=bandVoMapper.getAll();
		PageInfo<BandVo> pageInfo=new PageInfo<BandVo>(bands);	
		return pageInfo;
	}
	
	@DeleteMapping("deleteById/{id}")
	public void deleteById(@PathVariable("id")Integer id) {
		bandService.deleteByPrimaryKey(id);
	}
	
	@PostMapping("isBanded")
	public String isBanded(@RequestBody BandVo bandVo) {
		System.out.println(bandVo);
		// 查找所有同类别的折扣
		Band band=new Band();
		band.setTypeid(bandVo.getTypeid());
		List<Band> bands=bandService.selectList(band);
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
		int isOK=bandService.insertSelective(band);
		if (isOK==1) {
			return "success";
		}
		return "error";
	}
	
	@PostMapping("getPrice")
	public int getPrice(@RequestBody BookVo vo) {
		int discount=bandService.getPriceForBooking(vo);
		Type type=typeService.selectByPrimaryKey(vo.getTypeid());
		return type.getPrice()*discount/100;
	}
}