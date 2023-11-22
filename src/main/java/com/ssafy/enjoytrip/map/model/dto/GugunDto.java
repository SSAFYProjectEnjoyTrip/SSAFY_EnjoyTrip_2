package com.ssafy.enjoytrip.map.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@ApiModel(value = "Gugun Dto : 구군 정보", description = "구군의 번호와 이름을 나타낸다.")
public class GugunDto {
	@ApiModelProperty(value = "구군코드")
	private String gugun_code;
	@ApiModelProperty(value = "구군이름")
	private String gugun_name;
	@ApiModelProperty(value = "시도코드")
	private int sido_code;
}
