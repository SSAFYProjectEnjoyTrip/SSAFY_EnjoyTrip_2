package com.ssafy.enjoytrip.hotplace.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.enjoytrip.hotplace.model.dto.ZzimDto;

public interface ZzimService {
	List<ZzimDto> zzimList(String userId);
	
	void deleteZzim(String userId, int contentId);

	void addZzimList(ZzimDto zzimDto);
}
