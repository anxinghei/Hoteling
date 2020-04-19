package com.anxinghei.sys.service;

import java.util.List;

import com.anxinghei.sys.entity.Band;
import com.anxinghei.sys.vo.BookVo;

public interface BandService {

	int getPriceForBooking(BookVo vo);
	void deleteByPrimaryKey(int id);
	List<Band> selectList(Band band);
	int insertSelective(Band band);
}
