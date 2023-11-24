package com.ssafy.enjoytrip.myplan.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.myplan.model.dto.MyPlanDto;
import com.ssafy.enjoytrip.myplan.service.MyPlanService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@Api(value ="MyPlan API", description = "My Plan 확 게시판")
@RestController // Controller 내에서 작성하는 모든 메서드에 기본적으로 @ResponseBody로 출력됨.
@RequestMapping("/myplan") // 요청하는 자원(Domain)명을 붙인다. ==> /book이 이미 있어서 /rest로 함
public class MyPlanRestController {
	private Logger logger = LoggerFactory.getLogger(MyPlanRestController.class);
	private MyPlanService myPlanService;

	private static final String SUCCESS = "success";
	private static final String FAILURE = "failure";

	public MyPlanRestController(MyPlanService myPlanService) {
		this.myPlanService = myPlanService;
	}
	
	@ApiOperation(value = "마이플랜 리스트 ", notes = "마이플랜 리스트")
	@ApiResponse(code = 200, message = "success")
	@GetMapping("/{userId}")
	public ResponseEntity<?> getPlanList(@PathVariable String userId) {
		String isReal = "aa";
		List<MyPlanDto> list = myPlanService.getPlanList(isReal);

		
		if (list != null && !list.isEmpty()) {
			return new ResponseEntity(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@ApiOperation(value="마이플랜  등록", notes = "마이플랜 등록")
	@ApiResponse(code = 200, message = "success")
	@PostMapping
	public ResponseEntity<?> addPlan(@RequestBody MyPlanDto mpDto){
		mpDto.setUserId("aa");
		myPlanService.addPlan(mpDto);
		
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
}
