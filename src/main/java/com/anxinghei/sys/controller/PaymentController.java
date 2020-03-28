package com.anxinghei.sys.controller;

//import com.github.wxiaoqi.security.common.rest.BaseController;
//import com.anxinghei.sys.biz.PaymentBiz;
import com.anxinghei.sys.entity.Payment;
import com.anxinghei.sys.mapper.PaymentMapper;
import com.anxinghei.sys.mapper.PaymentVoMapper;
import com.anxinghei.sys.util.DateUtils;
import com.anxinghei.sys.vo.PaymentVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// extends BaseController<PaymentBiz,Payment>

@RestController
@RequestMapping("payment")
public class PaymentController  {
	
	@Autowired
	private PaymentVoMapper PaymentVoMapper;
	@Autowired
	private PaymentMapper PaymentMapper;
	
	@GetMapping("findAll/{start}/{size}")
	public PageInfo<PaymentVo> getPaymentVos(@PathVariable("start") Integer start,@PathVariable("size") Integer size){
		PageHelper.startPage(start,size);
		List<PaymentVo> list= PaymentVoMapper.getPaymentVos();
		return new PageInfo<>(list);
	}

	@GetMapping("getTotal")
	public PaymentVo getTotal() {
		PaymentVo paymentVo=new PaymentVo();
		// 今日统计
		String time=DateUtils.getDataforBook();
		paymentVo.setDayPayment(PaymentMapper.getListByTime(time).size());
		paymentVo.setDaySum(PaymentMapper.getSumByTime(time)==null?0:PaymentMapper.getSumByTime(time));
		// 本月统计
		time=DateUtils.getMonth();
		time+='%';
		paymentVo.setMonthPayment(PaymentMapper.getListByTime(time).size());
		paymentVo.setMonthSum(PaymentMapper.getSumByTime(time)==null?0:PaymentMapper.getSumByTime(time));
		// 今年统计
		time=DateUtils.getYear();
		time+='%';
		paymentVo.setYearPayment(PaymentMapper.getListByTime(time).size());
		paymentVo.setYearSum(PaymentMapper.getSumByTime(time)==null?0:PaymentMapper.getSumByTime(time));
		return paymentVo;
	}
}