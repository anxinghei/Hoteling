package com.anxinghei.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.anxinghei.sys.vo.PaymentVo;
import com.anxinghei.sys.vo.RoomVo;

@Mapper
public interface PaymentVoMapper {
	
	@Select("select payment.roomNum as roomNum,payment.guest as guestname,payment.amount as amount,payment.date as date,"
			+ "book.startDay as startday,book.endDay as endday ,book.guestId as guestid "  
			+ "from payment , book,guest " 
			+  "where payment.date=book.date and payment.roomNum=book.roomNum and payment.guest=guest.name and book.guestId =guest.id")
	List<PaymentVo> getPaymentVos();
}
