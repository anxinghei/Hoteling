package com.anxinghei.sys.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.anxinghei.sys.entity.Payment;
import com.anxinghei.sys.mapper.PaymentMapper;

public class baiscData {
	
// 判断数字是否在数字数组内
	public static boolean in_array(int id,int[] ids) {
		int i = 0;
		for (; i < ids.length; i++) {
			if (id==ids[i]) {
				return true;
			}
		}
		if (i<ids.length) {
			return true;
		}else {
			return false;
		}
	}
// 切分权限集
	public static int[] splitString(String authString) {
//		authString=authString.substring(1);
		String[] ruleStrings=authString.split(",");
		int[] rulesInt=new int[ruleStrings.length];
		for (int i = 0; i < ruleStrings.length; i++) {
			rulesInt[i]=Integer.parseInt(ruleStrings[i]);
//			System.out.print(Integer.parseInt(ruleStrings[i]));
		}
		return rulesInt;
	}
	
	public static Map<String,Object> iniMap(String time,List<Payment> payments) {
		Map<String,Object> map=new HashMap<String,Object>();
		
		map.put("dating", time);
		map.put("datting", time);

		int totalAmount=0;
		List<Map<String, Object>> paymentList=new ArrayList<Map<String,Object>>();
		Map<String, Object> innerMap=null;
		Payment payment=new Payment();
		
		// 将数据添加到List 中
		for (int i = 0; i < payments.size(); i++) {
			payment=payments.get(i);
			innerMap=new HashMap<String, Object>();
			innerMap.put("room",payment.getRoomNum());
			innerMap.put("guest", payment.getGuest());
			innerMap.put("amount", payment.getAmount());
			innerMap.put("date", payment.getDate());
			paymentList.add(innerMap);
			
			// 得到总的付款金额
			totalAmount+=payment.getAmount();
		}		
		map.put("paymentList",paymentList);
		
		map.put("num", payments.size());
		map.put("price", totalAmount);
		
		return map;
	}
}
