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
			+ "where room.typeId=type.id and room.bookId=book.id and book.guestId=guest.id "
			+ "and book.startDay<= #{today} and book.endDay > #{today}")
	public List<RoomVo> getBookedRooms(String today);
	
	@Select("select "
			+ "room.num as roomNum,type.name as typeName,guest.name as guestName,guest.phone as guestPhone,book.startDay as startDay,book.endDay as endDay "
			+ "from room,type,book,guest "
			+ "where room.typeId=type.id and room.bookId=book.id and book.guestId=guest.id "
			+ "and book.startDay > #{today} and book.endDay <= #{today}")
	public List<RoomVo> getBookRoomsnotIn(String today);
	
	@Select("SELECT room.num AS roomNum, type.name AS typeName"  
			+ "FROM room, TYPE "  
			+ "WHERE room.typeId = type.id AND room.bookId = 0")
	public List<RoomVo> getUnbookRooms();
}
