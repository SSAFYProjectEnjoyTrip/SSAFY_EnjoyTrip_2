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
    public List<AttractionInfoDto> attractionList(int areaCode, int sigunguCode) {
    	List<AttractionInfoDto> list = null;
        try {
			list = dao.attractionList(areaCode, sigunguCode);
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        return list;
    }

    @Override
    public List<AttractionInfoDto> searchByTitle(String title, int areaCode, int sigunguCode) {
    	List<AttractionInfoDto> list = null;
        try {
        	list = dao.searchByTitle(title, areaCode, sigunguCode);
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return list;
    }

	@Override
	public List<AttractionInfoDto> attractionListByType(int areaCode, int sigunguCode, int contentTypeId) {
		List<AttractionInfoDto> list = null;
		
		try {
			list = dao.attractionListByType(areaCode, sigunguCode, contentTypeId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
}

