package com.anxinghei.sys.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anxinghei.sys.entity.Band;
import com.anxinghei.sys.entity.Type;
import com.anxinghei.sys.mapper.BandMapper;
import com.anxinghei.sys.service.BandService;
import com.anxinghei.sys.vo.BookVo;

@Service
public class BandServiceImpl implements BandService{

	@Autowired
	private BandMapper bandMapper;
	
	@Override
	public int getPriceForBooking(BookVo vo) {
		Band record=new Band();
		record.setTypeid(vo.getTypeid());
		List<Band> bands=bandMapper.select(record);
		int discount=100;
		for (Band band : bands) {
			if (vo.getStartday().substring(4).compareTo(band.getStartday())>=0 && vo.getEndday().substring(4).compareTo(band.getEndday())<=0) {
				discount=band.getDiscount();
				break;
			}
		}

		return discount;
	}

	@Override
	public void deleteByPrimaryKey(int id) {
		bandMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<Band> selectList(Band band) {
		return bandMapper.select(band);
	}

	@Override
	public int insertSelective(Band band) {
		return bandMapper.insert(band);
	}

}
