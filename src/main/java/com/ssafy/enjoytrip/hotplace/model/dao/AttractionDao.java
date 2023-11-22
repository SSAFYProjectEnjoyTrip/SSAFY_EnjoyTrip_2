package com.ssafy.enjoytrip.hotplace.model.dao;


import com.ssafy.enjoytrip.hotplace.model.dto.AttractionListDto;
import com.ssafy.enjoytrip.hotplace.model.dto.AttractionInfoDto;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AttractionDao {

	List<AttractionInfoDto> attractionList(AttractionListDto aDto)  throws SQLException;
	
	List<AttractionInfoDto> attractionListByType(@Param("areaCode") int areaCode, @Param("sigunguCode") int sigunguCode, @Param("areaCode") int contentTypeId) throws SQLException;

	List<AttractionInfoDto> searchByTitle(@Param("title") String title, @Param("areaCode") int areaCode, @Param("sigunguCode") int sigunguCode)  throws SQLException;

}
