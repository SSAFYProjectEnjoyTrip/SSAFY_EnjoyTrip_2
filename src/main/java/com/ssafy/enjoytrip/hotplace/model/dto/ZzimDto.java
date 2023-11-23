package com.ssafy.enjoytrip.hotplace.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel(value="ZzimList Dto", description="찜 DTO")
@Data
public class ZzimDto {
	private int content_id;
	private String user_id;
	private String first_image;
	private String addr1;
	private String title;
	
	public ZzimDto(int content_id, String user_id, String first_image, String addr1, String title) {
		super();
		this.content_id = content_id;
		this.user_id = user_id;
		this.first_image = first_image;
		this.addr1 = addr1;
		this.title = title;
	}
}