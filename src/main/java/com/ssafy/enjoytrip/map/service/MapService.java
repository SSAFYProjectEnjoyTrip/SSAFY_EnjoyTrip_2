package com.ssafy.enjoytrip.map.service;

import java.util.List;

import com.ssafy.enjoytrip.map.model.dto.MarkerDto;
import com.ssafy.enjoytrip.map.model.dto.SidoGugunCodeDto;

public interface MapService {
	
	List<MarkerDto> getMarker(int contentTypeId, int sidoCode, int gugunCode);
	
	List<SidoGugunCodeDto> getSido() throws Exception;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;
}
