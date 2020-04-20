package com.anxinghei.sys.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.anxinghei.sys.entity.Type;
import com.anxinghei.sys.mapper.TypeMapper;
import com.anxinghei.sys.service.TypeService;

public class TypeServiceImpl implements TypeService{
	
	@Autowired
	private TypeMapper typeMapper;

	@Override
	public Type selectByPrimaryKey(int id) {
		return typeMapper.selectByPrimaryKey(id);
	}

}
