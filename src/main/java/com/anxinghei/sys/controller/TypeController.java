package com.anxinghei.sys.controller;

//import com.github.wxiaoqi.security.common.rest.BaseController;
//import com.anxinghei.sys.biz.TypeBiz;
import com.anxinghei.sys.entity.Type;
import com.anxinghei.sys.mapper.TypeMapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// extends BaseController<TypeBiz,Type>

@RestController
@RequestMapping("type")
public class TypeController  {
	
	@Autowired
	private TypeMapper typeMapper;
	
	@GetMapping("/getAll")
	public List getAll(){
		return typeMapper.selectAll();
	}
}