package co.jp.kadai.dto;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class HouseHoldDto {

	private String id;
	@NotBlank(message = "項目を入力してください。")
	private String item;
	@NotBlank(message = "価格を入力してください。")
	@Pattern(regexp="^(0|[1-9][0-9]*)$", message = "数字だけ入力してください。")
	private String price;
	@NotBlank(message = "日付を入力してください。")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String date;

}
