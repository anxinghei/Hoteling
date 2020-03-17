package com.anxinghei.sys.controller;

//import com.github.wxiaoqi.security.common.rest.BaseController;
//import com.anxinghei.sys.biz.CustomerBiz;
import com.anxinghei.sys.entity.Customer;
import com.anxinghei.sys.entity.Guest;
import com.anxinghei.sys.mapper.CustomerMapper;
import com.anxinghei.sys.mapper.GuestMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// extends BaseController<CustomerBiz,Customer>

@RestController
@RequestMapping("customer")
public class CustomerController  {

	@Autowired
	private CustomerMapper customerMapper;

	@GetMapping("findAll/{start}/{size}")
	PageInfo<Customer> findAll(@PathVariable("start") Integer start,@PathVariable("size") Integer size){
		PageHelper.startPage(start,size);
		List<Customer> guests=customerMapper.selectAll();
		PageInfo<Customer> pageInfo=new PageInfo<Customer>(guests);	
		return pageInfo;
	}
}