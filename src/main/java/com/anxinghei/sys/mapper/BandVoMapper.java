package com.anxinghei.sys.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.anxinghei.sys.entity.Band;
import com.anxinghei.sys.vo.BandVo;

import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
public interface BandVoMapper extends Mapper<Band> {
	
	@Select("SELECT band . * , type.name AS typename FROM band JOIN TYPE ON band.typeid = type.id ORDER BY band.startDay")
	List<BandVo> getAll();
}
