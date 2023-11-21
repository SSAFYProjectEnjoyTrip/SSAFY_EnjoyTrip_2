package com.ssafy.enjoytrip.hotplace.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="AttractionDetail Dto", description="지도 상세 조회 DTO")
@Data
public class AttractionDetailDto {

	@ApiModelProperty(value="관광지 번호", example="0")
	private int contentId;
	
	@ApiModelProperty(value="cat1", example="0")
	private String cat1;
	
	@ApiModelProperty(value="cat2", example="0")
	private String cat2;
	
	@ApiModelProperty(value="cat3", example="0")
	private String cat3;
	
	@ApiModelProperty(value="생성시각", example="0")
	private String createdTime;
	
	@ApiModelProperty(value="변경시각", example="0")
	private String modifiedTime;
	
	@ApiModelProperty(value="예약 투어", example="0")
	private String booktour;
	
	public int getContentId() {
		return contentId;
	}

	public void setContentId(int contentId) {
		this.contentId = contentId;
	}

	public String getCat1() {
		return cat1;
	}

	public void setCat1(String cat1) {
		this.cat1 = cat1;
	}

	public String getCat2() {
		return cat2;
	}

	public void setCat2(String cat2) {
		this.cat2 = cat2;
	}

	public String getCat3() {
		return cat3;
	}

	public void setCat3(String cat3) {
		this.cat3 = cat3;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(String modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public String getBooktour() {
		return booktour;
	}

	public void setBooktour(String booktour) {
		this.booktour = booktour;
	}

	@Override
	public String toString() {
		return "AttractionDetailDto [contentId=" + contentId + ", cat1=" + cat1 + ", cat2=" + cat2 + ", cat3=" + cat3
				+ ", createdTime=" + createdTime + ", modifiedTime=" + modifiedTime + ", booktour=" + booktour + "]";
	}

	public AttractionDetailDto(int contentId, String cat1, String cat2, String cat3, String createdTime,
			String modifiedTime, String booktour) {
		super();
		this.contentId = contentId;
		this.cat1 = cat1;
		this.cat2 = cat2;
		this.cat3 = cat3;
		this.createdTime = createdTime;
		this.modifiedTime = modifiedTime;
		this.booktour = booktour;
	}

}
