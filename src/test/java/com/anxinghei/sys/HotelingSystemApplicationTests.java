package com.anxinghei.sys;

import org.junit.jupiter.api.Test;
import org.mybatis.generator.codegen.mybatis3.model.ExampleGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.anxinghei.sys.entity.Book;
import com.anxinghei.sys.mapper.BookMapper;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@SpringBootTest
class HotelingSystemApplicationTests {
	
	@Autowired
	BookMapper bookMapper;
	
	@Test
	void contextLoads() {
		Book book=bookMapper.selectByPrimaryKey(1);
		System.out.println(book.getName());
		
	}

}
