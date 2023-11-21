package com.ssafy.enjoytrip.hotplace.service;


import java.util.List;

import com.ssafy.enjoytrip.hotplace.model.dto.AttractionInfoDto;

public interface AttractionService {

    List<AttractionInfoDto> attractionList(AttractionInfoDto attractionInfoDto);

    List<AttractionInfoDto> searchByTitle(String title, int sidoCode);

//    List<SidoDto> stateList();
//
//    List<GugunDto> cityList(int stateCode);
}

