package com.anxinghei.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.anxinghei.sys.entity.Payment;

import tk.mybatis.mapper.common.Mapper;

/**
 * 支付表，收账记录
 * 
 * @author Autumn
 * @email 
 * @date 2020-03-10 20:31:52
 */
@org.apache.ibatis.annotations.Mapper
public interface PaymentMapper extends Mapper<Payment> {
	
	@Select("SELECT * FROM `payment` WHERE date LIKE #{time}")
	List<Payment> getListByTime(String time);
	
	@Select("SELECT  sum( amount ) FROM `payment` WHERE date LIKE #{time}")
	Integer getSumByTime(String time);
}
