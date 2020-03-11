package com.anxinghei.sys.controller;

//import com.github.wxiaoqi.security.common.rest.BaseController;
//import com.anxinghei.sys.biz.BookBiz;
import com.anxinghei.sys.entity.Book;
import com.anxinghei.sys.entity.Oldbook;
import com.anxinghei.sys.mapper.BookMapper;
import com.anxinghei.sys.mapper.BookVoMapper;
import com.anxinghei.sys.mapper.OldbookMapper;
import com.anxinghei.sys.service.BookService;
import com.anxinghei.sys.vo.BookVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// extends BaseController<BookBiz,Book>

@RestController
@RequestMapping("book")
public class BookController  {

	@Autowired
	private BookMapper bookMapper;
	@Autowired
	private BookVoMapper bookVoMapper;
	@Autowired
	private BookService bookService;
	
	@GetMapping("findAll/{start}/{size}")
	PageInfo<BookVo> findAll(@PathVariable("start") Integer start,@PathVariable("size") Integer size){
		PageHelper.startPage(start,size);
		List<BookVo> bookVo=bookVoMapper.findAll();
		PageInfo<BookVo> pageInfo=new PageInfo<BookVo>(bookVo);	
		return pageInfo;
	}
	
	@GetMapping("findById/{id}")
	BookVo findById(@PathVariable("id")Integer bookId) {
		return bookVoMapper.selectByroomid(bookId);
	}
	
	@PutMapping("update")
	String update(@RequestBody BookVo bookVo) {
		Book book=bookMapper.selectByPrimaryKey(bookVo.getBookid());
		book.setRoomid(bookVo.getRoomid());
		book.setEndday(bookVo.getEndday());
		int isUpdated=bookMapper.updateByPrimaryKey(book);
		if (isUpdated!=0) {
			return "success";
		}
		return "error";
	}
	
	@DeleteMapping("deleteById/{bookid}")
	void deleteById(@PathVariable("bookid")Integer bookid) {
		bookService.deleteById(bookid);
	}

}