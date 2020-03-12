package com.anxinghei.sys.transction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import com.anxinghei.sys.entity.Book;
import com.anxinghei.sys.entity.Customer;
import com.anxinghei.sys.entity.Guest;
import com.anxinghei.sys.entity.Oldbook;
import com.anxinghei.sys.mapper.BookMapper;
import com.anxinghei.sys.mapper.BookVoMapper;
import com.anxinghei.sys.mapper.CustomerMapper;
import com.anxinghei.sys.mapper.GuestMapper;
import com.anxinghei.sys.mapper.OldbookMapper;
import com.anxinghei.sys.vo.BookVo;

@Service
public class BookServiceImpl implements BookService{
	
	@Autowired
	private BookMapper bookMapper;
	@Autowired
	private OldbookMapper oldBookMapper;
	@Autowired
	private CustomerMapper customerMapper;
	@Autowired
	private GuestMapper guestMapper;
	
	// 删除订单时
	// 1，将订单从当前订单列表删除
	// 2，将其加入到往期订单中，作为记录保存
	@Transactional
	public void deleteById(Integer bookid) {
		Book book=bookMapper.selectByPrimaryKey(bookid);
		bookMapper.deleteByPrimaryKey(bookid);
		Oldbook oldbook=new Oldbook(book.getRoomNum(), book.getGuestid(), book.getCustomerid(), book.getStartday(), book.getEndday());
		oldBookMapper.insert(oldbook);
	}
	
	// 添加订单时
	// 1，判定预订者是否已存档
	// 2，判定入住者是否已存档
	// 3，生成订单
	// 4，判定是否有折扣，生成付款单
	@Transactional
	public void addBook(BookVo vo) {
		Customer customer=new Customer();
		if (vo.getCustomername()!=null) {
			customer.setName(vo.getCustomername());
		}
		if (vo.getCustomerphone()!=null) {
			customer.setPhone(vo.getCustomerphone());
		}
		customer.setSex(0);
		if (customerMapper.selectOne(customer)==null) {
			customerMapper.insert(customer);
		}
		
		Guest guest=new Guest();
		if (vo.getGuestname()!=null) {
			guest.setName(vo.getGuestname());
		}
		if (vo.getGuestphone()!=null) {
			guest.setPhone(vo.getGuestphone());
		}
		guest.setSex(0);
		if (guestMapper.selectOne(guest)==null) {
			guestMapper.insert(guest);
		}
	}
	
}
