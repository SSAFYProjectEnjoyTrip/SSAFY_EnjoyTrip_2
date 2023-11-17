package com.ssafy.enjoytrip.map.service;

import java.util.List;

import com.ssafy.enjoytrip.map.model.dto.GugunDto;
import com.ssafy.enjoytrip.map.model.dto.MarkerDto;
import com.ssafy.enjoytrip.map.model.dto.SidoDto;
import com.ssafy.enjoytrip.map.model.dto.SidoGugunCodeDto;

public interface MapService {
	
//	List<SidoDto> getSido();
	List<GugunDto> getGugun(int sidoCode);
	List<MarkerDto> getMarker(int contentTypeId, int sidoCode, int gugunCode);
	
	List<SidoGugunCodeDto> getSido() throws Exception;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;
}
