package com.ssafy.enjoytrip.myplan.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.enjoytrip.myplan.model.dto.MyPlanDto;

public interface MyPlanService {
	List<MyPlanDto> getPlanList(String userId);
	
	void addPlan(MyPlanDto mpDto);
}
