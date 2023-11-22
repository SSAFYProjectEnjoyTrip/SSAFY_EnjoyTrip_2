package com.ssafy.enjoytrip.hotplace.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel(value="SearchByTitle Dto" ,description="이름 별로 관광지 조회  DTO")
@Data
public class SearchByTitleDto {

	private String title;
	private int sidoCode;
	private int gugunCode;
	
	public SearchByTitleDto(String title, int sidoCode, int gugunCode) {
		super();
		this.title = title;
		this.sidoCode = sidoCode;
		this.gugunCode = gugunCode;
	}
	
	

}
