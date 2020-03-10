package com.anxinghei.sys.mapper;


import com.anxinghei.sys.entity.Customer;

import tk.mybatis.mapper.common.Mapper;

/**
 * 顾客表，打电话预订的人
 * 
 * @author Autumn
 * @email 
 * @date 2020-03-10 20:31:53
 */
@org.apache.ibatis.annotations.Mapper
public interface CustomerMapper extends Mapper<Customer> {
	
}
