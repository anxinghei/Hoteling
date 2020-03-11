package com.anxinghei.sys.controller;

//import com.github.wxiaoqi.security.common.rest.BaseController;
//import com.anxinghei.sys.biz.BookBiz;
import com.anxinghei.sys.entity.Book;
import com.anxinghei.sys.mapper.BookMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// extends BaseController<BookBiz,Book>

@RestController
@RequestMapping("book")
public class BookController  {
	
	@Autowired
	private BookMapper bookMapper;
	
	@GetMapping("findAll/{start}/{size}")
	PageInfo<Book> findAll(@PathVariable("start") Integer start,@PathVariable("size") Integer size){
		PageHelper.startPage(start,size);
		List<Book> books=bookMapper.selectAll();
		PageInfo<Book> pageInfo=new PageInfo<Book>(books);
		return pageInfo;
	}

}