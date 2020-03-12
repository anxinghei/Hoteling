package com.anxinghei.sys.transction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import com.anxinghei.sys.entity.Book;
import com.anxinghei.sys.entity.Oldbook;
import com.anxinghei.sys.mapper.BookMapper;
import com.anxinghei.sys.mapper.BookVoMapper;
import com.anxinghei.sys.mapper.OldbookMapper;

@Service
public class BookServiceImpl implements BookService{
	
	@Autowired
	private BookMapper bookMapper;
	@Autowired
	private OldbookMapper oldBookMapper;
	
	// 删除订单时
	// 1，将订单从当前订单列表删除
	// 2，将其加入到往期订单中，作为记录保存
	@Transactional
	public void deleteById(Integer bookid) {
		Book book=bookMapper.selectByPrimaryKey(bookid);
		bookMapper.deleteByPrimaryKey(bookid);
		Oldbook oldbook=new Oldbook(book.getRoomid(), book.getGuestid(), book.getCustomerid(), book.getStartday(), book.getEndday());
		oldBookMapper.insert(oldbook);
	}
	
	// 添加订单时
	// 1，判定预订者是否已存档
	// 2，判定入住者是否已存档
	// 3，生成订单
	// 4，判定是否有折扣，生成付款单
	
	
}
