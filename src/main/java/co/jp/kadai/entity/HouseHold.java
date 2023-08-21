package co.jp.kadai.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "household")
@Data
public class HouseHold {

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "item", nullable = false)
	private String item;
	@Column(name = "price", nullable = false)
	private int price;
	@Column(name = "date", nullable = false)
	private Date date;

}
