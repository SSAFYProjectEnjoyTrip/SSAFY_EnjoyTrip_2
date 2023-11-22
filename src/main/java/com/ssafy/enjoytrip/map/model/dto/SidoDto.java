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
@ApiModel(value = "Sido Dto : 시도 정보", description = "시도의 번호와 이름을 나타낸다.")
public class SidoDto {

	@ApiModelProperty(value = "시도코드")
	private String sido_code;
	@ApiModelProperty(value = "시도이름")
	private String sido_name;
	
}
