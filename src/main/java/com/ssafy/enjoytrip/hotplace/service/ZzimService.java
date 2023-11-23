package com.ssafy.enjoytrip.hotplace.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.enjoytrip.hotplace.model.dto.ZzimDto;

public interface ZzimService {
	List<ZzimDto> zzimList(String userId);
	
	void deleteZzim(String user_id, int content_id);

	void addZzimList(ZzimDto zzimDto);
}
