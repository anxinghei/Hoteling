package com.anxinghei.sys.transction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.unit.DataUnit;
import org.springframework.web.bind.annotation.PathVariable;

import com.anxinghei.sys.entity.Band;
import com.anxinghei.sys.entity.Book;
import com.anxinghei.sys.entity.Customer;
import com.anxinghei.sys.entity.Guest;
import com.anxinghei.sys.entity.Oldbook;
import com.anxinghei.sys.entity.Payment;
import com.anxinghei.sys.entity.Room;
import com.anxinghei.sys.mapper.BandMapper;
import com.anxinghei.sys.mapper.BookMapper;
import com.anxinghei.sys.mapper.BookVoMapper;
import com.anxinghei.sys.mapper.CustomerMapper;
import com.anxinghei.sys.mapper.GuestMapper;
import com.anxinghei.sys.mapper.OldbookMapper;
import com.anxinghei.sys.mapper.PaymentMapper;
import com.anxinghei.sys.mapper.RoomMapper;
import com.anxinghei.sys.mapper.TypeMapper;
import com.anxinghei.sys.util.DateUtils;
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
	@Autowired
	private BandMapper bandMapper;
	@Autowired
	private TypeMapper typeMapper;
	@Autowired
	private RoomMapper RoomMapper;
	@Autowired
	private PaymentMapper paymentMapper;
	
	@Transactional
	public void deleteById(Integer bookid) {
		// 删除订单时
		// 1，将订单从当前订单列表删除
		Book book=bookMapper.selectByPrimaryKey(bookid);
		bookMapper.deleteByPrimaryKey(bookid);
		// 2，将其加入到往期订单中，作为记录保存
		Oldbook oldbook=new Oldbook(book.getRoomNum(), book.getGuestid(), book.getCustomerid(), book.getStartday(), book.getEndday(),book.getDate());
		oldBookMapper.insert(oldbook);
		// 3,将对应房间的bookid重置
		Room record=new Room();
		record.setNum(book.getRoomNum());
		record=RoomMapper.selectOne(record);
		record.setBookid(0);
		RoomMapper.updateByPrimaryKeySelective(record);
	}
	

	@Transactional
	public void addBook(BookVo vo) {
		// 添加订单时
		// 1，判定预订者是否已存档
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
		// 2，判定入住者是否已存档
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
		// 3，判定是否有折扣
		List<Band> bands=bandMapper.selectAll();
		int discount=100;
		for (Band band : bands) {
			if (DateUtils.isBand(band.getStartday(), band.getEndday())) {
				discount=band.getDiscount();
				break;
			}
		}
		int price=typeMapper.selectByPrimaryKey(vo.getTypeid()).getPrice()*discount/100;
		// 4，生成订单
		Book book=new Book(vo.getRoom().getNum(),
				guestMapper.selectOne(guest).getId(), 
				customerMapper.selectOne(customer).getId(), 
				vo.getStartday(),
				vo.getEndday(),
				DateUtils.getDataforBook());
		bookMapper.insert(book);
		// 5，修改房间属性
		Room room=vo.getRoom();
		room.setBookid(bookMapper.selectOne(book).getId());
		RoomMapper.updateByPrimaryKey(room);
		// 6，生成付款单
		Payment payment=new Payment(guest.getName(), price, DateUtils.getDataforBook(),vo.getRoom().getNum());
		paymentMapper.insert(payment);
	}
	
	@Transactional
	public int updateBook(BookVo bookVo) {
		System.out.println("---"+bookVo);
		// 如果换房，typeid 暂存旧房号
		if (bookVo.getRoomNum()!=bookVo.getTypeid()) {
			// 重置旧房间的bookid
			Room oldRoom=new Room();
			oldRoom.setNum(bookVo.getTypeid());
			oldRoom=RoomMapper.selectOne(oldRoom);
			oldRoom.setBookid(0);
			RoomMapper.updateByPrimaryKeySelective(oldRoom);
			// 修改新房间的bookid
			Room newRoom=new Room();
			newRoom.setNum(bookVo.getRoomNum());
			newRoom=RoomMapper.selectOne(newRoom);
			newRoom.setBookid(bookVo.getBookid());
			RoomMapper.updateByPrimaryKeySelective(newRoom);
		}
		Book book=bookMapper.selectByPrimaryKey(bookVo.getBookid());
		book.setRoomNum(bookVo.getRoomNum());
		book.setStartday(bookVo.getStartday());
		book.setEndday(bookVo.getEndday());
		return bookMapper.updateByPrimaryKey(book);
	}
	
}
