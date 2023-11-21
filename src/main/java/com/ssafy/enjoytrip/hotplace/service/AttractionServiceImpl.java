package com.ssafy.enjoytrip.hotplace.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.enjoytrip.hotplace.model.dao.AttractionDao;
import com.ssafy.enjoytrip.hotplace.model.dto.AttractionInfoDto;


import java.util.List;

public class AttractionServiceImpl implements AttractionService {
    private AttractionDao dao;
    public AttractionServiceImpl(AttractionDao dao) {
        this.dao = dao;
    }

    @Override
    public List<AttractionInfoDto> attractionList(AttractionInfoDto attractionInfoDto) {
    	List<AttractionInfoDto> list = null;
        try {
			list = dao.attractionList(attractionInfoDto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        return list;
    }

    @Override
    public List<AttractionInfoDto> searchByTitle(String title, int sidoCode) {
    	List<AttractionInfoDto> list = null;
        try {
        	list = dao.searchByTitle(title, sidoCode);
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return list;
    }

//    @Override
//    public List<SidoDto> stateList() {
//        return dao.stateList();
//    }
//
//    @Override
//    public List<GugunDto> cityList(int stateCode) {
//        return dao.cityList(stateCode);
//    }
}

