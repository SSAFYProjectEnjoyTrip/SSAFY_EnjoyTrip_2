package com.ssafy.enjoytrip.hotplace.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.hotplace.model.dao.ZzimDao;
import com.ssafy.enjoytrip.hotplace.model.dto.ZzimDto;

@Service("ZzimService")
@Repository
public class ZzimServiceImpl implements ZzimService{
	
    private ZzimDao dao;
    public ZzimServiceImpl(ZzimDao dao) {
        this.dao = dao;
    }
	
	@Override
	public List<ZzimDto> zzimList(String userId) {
		List<ZzimDto> list = null;
		try {
			list = dao.zzimList(userId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void deleteZzim(String user_id, int content_id) {
		try {
			dao.deleteZzim(user_id, content_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addZzimList(ZzimDto zzimDto) {
		try {
			dao.addZzimList(zzimDto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
