package com.anxinghei.sys.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.anxinghei.sys.vo.BookVo;

import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
public interface BookVoMapper{

	@Select("select book.id as bookid,roomNum,guest.name as guestname,guest.phone as guestphone,startday,endday "
			+ "from book join guest on book.guestid=guest.id order by endday")
	public List<BookVo> findAll();
	
	@Select("select book.id as bookid,roomNum,guest.name as guestname, guest.phone as guestphone,startday,endday "
			+ "from book join guest on book.guestid=guest.id where book.id=#{id}")
	public BookVo selectByroomid(int id);
}
