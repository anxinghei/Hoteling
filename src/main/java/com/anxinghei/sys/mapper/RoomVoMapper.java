package com.anxinghei.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.anxinghei.sys.vo.RoomVo;

@Mapper
public interface RoomVoMapper {

	@Select("select "
			+ "room.num as roomNum,type.name as typeName,guest.name as guestName,guest.phone as guestPhone,book.startDay as startDay,book.endDay as endDay "
			+ "from room,type,book,guest "
			+ "where room.typeId=type.id and room.num=book.roomNum and book.guestId=guest.id "
			+ "and book.startDay<= #{today} and book.endDay > #{today}")
	public List<RoomVo> getInRooms(String today);
	
	
	@Select("SELECT room.num AS roomNum, type.name AS typeName " + 
			"FROM room JOIN TYPE ON room.typeId = type.id " + 
			"WHERE room.bookId =0 " + 
			"UNION " + 
			"SELECT room.num AS roomNum, type.name AS typeName " + 
			"FROM room, TYPE , book, guest " + 
			"WHERE room.typeId = type.id AND room.num = book.roomNum AND book.guestId = guest.id AND " + 
			"( book.startDay >#{today} OR book.endDay <=#{today})" + 
			"GROUP BY roomNum ORDER BY roomNum")
	public List<RoomVo> getNotinRooms(String today);
}
