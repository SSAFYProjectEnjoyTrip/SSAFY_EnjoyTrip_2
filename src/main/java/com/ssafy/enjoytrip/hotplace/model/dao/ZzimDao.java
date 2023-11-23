package com.ssafy.enjoytrip.hotplace.model.dao;


import com.ssafy.enjoytrip.hotplace.model.dto.ZzimDto;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestParam;

@Mapper
public interface ZzimDao {
	List<ZzimDto> zzimList(String userId) throws SQLException;
	
	void addZzimList(ZzimDto zzimDto) throws SQLException;

	void deleteZzim(String userId, int contentId) throws SQLException;
}