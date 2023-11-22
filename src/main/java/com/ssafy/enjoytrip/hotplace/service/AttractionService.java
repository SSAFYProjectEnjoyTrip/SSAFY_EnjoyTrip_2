package com.ssafy.enjoytrip.hotplace.service;


import java.sql.SQLException;
import java.util.List;

import com.ssafy.enjoytrip.hotplace.model.dto.AttractionListDto;
import com.ssafy.enjoytrip.hotplace.model.dto.AttractionInfoDto;

public interface AttractionService {

    List<AttractionInfoDto> attractionList(AttractionListDto aDto);
    
    List<AttractionInfoDto> attractionListByType(int areaCode, int sigunguCode, int contentTypeId);
    
    List<AttractionInfoDto> searchByTitle(String title, int areaCode, int sigunguCode);

}

