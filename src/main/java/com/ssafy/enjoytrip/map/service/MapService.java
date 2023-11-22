package com.ssafy.enjoytrip.map.service;

import java.util.List;

import com.ssafy.enjoytrip.map.model.dto.GugunDto;
import com.ssafy.enjoytrip.map.model.dto.MarkerDto;
import com.ssafy.enjoytrip.map.model.dto.SidoDto;

public interface MapService {
	
	List<MarkerDto> getMarker(int contentTypeId, int sidoCode, int gugunCode);
	
	List<SidoDto> getSido() throws Exception;
	List<GugunDto> getGugun(int sidoCode) throws Exception;
}
