package com.ssafy.enjoytrip.map.model.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.map.model.dto.MarkerDto;
import com.ssafy.enjoytrip.map.model.dto.SidoDto;
import com.ssafy.enjoytrip.map.model.dto.GugunDto;

@Mapper
public interface MapDao {
	List<MarkerDto> getMarker(int contentTypeId, int sidoCode, int gugunCode) throws SQLException;
	
	List<SidoDto> getSido() throws SQLException;
	List<GugunDto> getGugun(int sidoCode) throws SQLException;
}
