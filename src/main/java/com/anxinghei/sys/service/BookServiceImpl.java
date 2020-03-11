package com.anxinghei.sys.service;

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
	
	@Transactional
	public void deleteById(Integer bookid) {
		Book book=bookMapper.selectByPrimaryKey(bookid);
		bookMapper.deleteByPrimaryKey(bookid);
		Oldbook oldbook=new Oldbook(book.getRoomid(), book.getGuestid(), book.getCustomerid(), book.getStartday(), book.getEndday());
		oldBookMapper.insert(oldbook);
	}
}
