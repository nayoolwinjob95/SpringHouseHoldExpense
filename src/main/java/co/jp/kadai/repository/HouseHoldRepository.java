package co.jp.kadai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.jp.kadai.dto.HouseHoldListDto;
import co.jp.kadai.entity.HouseHold;

public interface HouseHoldRepository extends JpaRepository<HouseHold, Integer> {

	@Query(value = "select date_part('YEAR', h.date) as year, date_part('MONTH', h.date) as month, sum(h.price) as total from household h group by year, month order by year, month desc", nativeQuery = true)
	List<HouseHoldListDto> findAllByYearMonth();

	@Query(value = "select * from household h where date_part('YEAR', h.date) = :year and date_part('MONTH', h.date) = :month", nativeQuery = true)
	List<HouseHold> getDetails(int year, int month);

	@Query(value = "select sum(h.price) from household h where date_part('YEAR', h.date) = :year and date_part('MONTH', h.date) = :month", nativeQuery = true)
	Integer getDetailTotal(int year, int month);

}
