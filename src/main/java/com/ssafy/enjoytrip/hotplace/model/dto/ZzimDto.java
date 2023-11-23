package com.ssafy.enjoytrip.hotplace.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel(value="ZzimList Dto", description="찜 DTO")
@Data
public class ZzimDto {
	private int contentId;
	private String userId;
	private String firstImage;
	private String addr1;
	private String title;
	private double latitude;
	private double longitude;

	public int getContentId() {
		return contentId;
	}

	public void setContentId(int contentId) {
		this.contentId = contentId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstImage() {
		return firstImage;
	}

	public void setFirstImage(String firstImage) {
		this.firstImage = firstImage;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getLatitude(double latitude) {
		return latitude;
	}
	
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	public double getLongitude(double longitude) {
		return longitude;
	}
	
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public ZzimDto() {
		super();
	}
	

	public ZzimDto(int contentId, String userId, String firstImage, String addr1, String title, double latitude,
			double longitude) {
		super();
		this.contentId = contentId;
		this.userId = userId;
		this.firstImage = firstImage;
		this.addr1 = addr1;
		this.title = title;
		this.latitude = latitude;
		this.longitude = longitude;
	}
}