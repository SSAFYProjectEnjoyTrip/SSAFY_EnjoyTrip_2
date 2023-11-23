package com.ssafy.enjoytrip.myplan.model.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.map.model.dto.MarkerDto;
import com.ssafy.enjoytrip.map.model.dto.SidoDto;
import com.ssafy.enjoytrip.myplan.model.dto.MyPlanDto;
import com.ssafy.enjoytrip.map.model.dto.GugunDto;

@Mapper
public interface MyPlanDao {
	List<MyPlanDto> getPlanList(String userId) throws SQLException;
	
	void addPlan(MyPlanDto mpDto) throws SQLException;
	
}
