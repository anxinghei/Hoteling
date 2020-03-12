package com.anxinghei.sys.transction;

import com.anxinghei.sys.vo.BookVo;

public interface BookService {
	public void deleteById(Integer bookid);
	public void addBook(BookVo vo);
}
