package co.jp.kadai.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import co.jp.kadai.dto.HouseHoldDto;
import co.jp.kadai.dto.HouseHoldListDto;
import co.jp.kadai.entity.HouseHold;
import co.jp.kadai.repository.HouseHoldRepository;

@Service
public class HouseHoldService {

	@Autowired
	HouseHoldRepository houseHoldRepository;

	public void register(HouseHoldDto houseHoldDto) {
		System.out.println(houseHoldDto.toString());
		HouseHold household = this.convertToHouseHold(houseHoldDto);
		try {
			houseHoldRepository.save(household);
		} catch (Exception e) {
			TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
		}
	}

	private HouseHold convertToHouseHold(HouseHoldDto houseHoldDto) {
		HouseHold household = new HouseHold();
		if (houseHoldDto.getId() != null) {
			household.setId(Integer.valueOf(houseHoldDto.getId()));
		}

		household.setItem(houseHoldDto.getItem());
		household.setPrice(Integer.valueOf(houseHoldDto.getPrice()));

		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date parsed = format.parse(houseHoldDto.getDate());
			java.sql.Date sqlDate = new java.sql.Date(parsed.getTime());
			household.setDate(sqlDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return household;
	}

	public List<HouseHoldListDto> getAllByYearMonth() {
		return houseHoldRepository.findAllByYearMonth();
	}

	public List<HouseHold> getDetails(int year, int month) {
		return houseHoldRepository.getDetails(year, month);
	}

	public Integer getDetailTotal(int year, int month) {
		return houseHoldRepository.getDetailTotal(year, month);
	}

	public HouseHold getHouseHold(int id) {
		return houseHoldRepository.getReferenceById(id);
	}
}
