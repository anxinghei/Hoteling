package com.anxinghei.sys.controller;

//import com.github.wxiaoqi.security.common.rest.BaseController;
//import com.anxinghei.sys.biz.PaymentBiz;
import com.anxinghei.sys.entity.Payment;
import com.anxinghei.sys.mapper.PaymentMapper;
import com.anxinghei.sys.mapper.PaymentVoMapper;
import com.anxinghei.sys.util.DateUtils;
import com.anxinghei.sys.util.DocumentHandler;
import com.anxinghei.sys.vo.PaymentVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	
	@RequestMapping("exportWord")
	public String freemaker(HttpServletRequest req,HttpServletResponse resp) {
		
		Map<String,Object> map=new HashMap<String,Object>();

		String dateString=DateUtils.getDataforBook();
		map.put("dating", dateString);
		map.put("datting", dateString);

		map.put("num", 1);
		map.put("price", 1);

		List<Map<String, Object>> paymentList=new ArrayList<Map<String,Object>>();
		Map<String, Object> map2=new HashMap<String, Object>();
		map2.put("room", 101);
		map2.put("guest", "张三");
		map2.put("amount", 200);
		map2.put("date", 20200419);
		paymentList.add(map2);
		map2=new HashMap<String, Object>();
		map2.put("room", 102);
		map2.put("guest", "李四");
		map2.put("amount", 300);
		map2.put("date", 20200420);
		paymentList.add(map2);

		map.put("paymentList",paymentList);
		
		boolean b =new DocumentHandler().exportDoc("outer", "exportWord"+dateString, map, resp);
		System.out.println(b);
		if (b) {
			return "success";
		}
		return "failed";
	}
}