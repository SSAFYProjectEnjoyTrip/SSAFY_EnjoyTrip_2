package com.ssafy.enjoytrip.myplan.service;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.myplan.model.dao.MyPlanDao;
import com.ssafy.enjoytrip.myplan.model.dto.MyPlanDto;

@Service("MyPlanService")
@Repository
public class MyPlanServiceImpl implements MyPlanService {

	@Autowired
	private MyPlanDao myPlanDao;
	private Logger logger = LoggerFactory.getLogger(MyPlanServiceImpl.class);

	public MyPlanServiceImpl(MyPlanDao myPlanDao) {
		super();
		this.myPlanDao = myPlanDao;
	}
	
	@Override
	public List<MyPlanDto> getPlanList(String userId) {
		List<MyPlanDto> list = null;
		try {
			list = myPlanDao.getPlanList(userId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void addPlan(MyPlanDto mpDto) {
		try {
			myPlanDao.addPlan(mpDto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
