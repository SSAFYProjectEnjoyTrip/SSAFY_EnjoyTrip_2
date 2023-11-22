package com.ssafy.enjoytrip.hotplace.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel(value="AttracionList Dto", description="관광지 조회 DTO")
@Data
public class AttractionListDto {
	private int sidoCode;
	private int gugunCode;
	
	public AttractionListDto(int sidoCode, int gugunCode) {
		super();
		this.sidoCode = sidoCode;
		this.gugunCode = gugunCode;
	}

	public int getSidoCode() {
		return sidoCode;
	}

	public void setSidoCode(int sidoCode) {
		this.sidoCode = sidoCode;
	}

	public int getGugunCode() {
		return gugunCode;
	}

	public void setGugunCode(int gugunCode) {
		this.gugunCode = gugunCode;
	}
	
	
}
