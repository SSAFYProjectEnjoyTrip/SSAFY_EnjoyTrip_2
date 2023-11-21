package com.ssafy.enjoytrip.hotplace.model.dao;


import com.ssafy.enjoytrip.hotplace.model.dto.AttractionInfoDto;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AttractionDao {

	List<AttractionInfoDto> attractionList(AttractionInfoDto attractionInfoDto)  throws SQLException;

	List<AttractionInfoDto> searchByTitle(String title, int sidoCode)  throws SQLException;

//	List<SidoDto> stateList();
//
//	List<GugunDto> cityList(int stateCode);

}
