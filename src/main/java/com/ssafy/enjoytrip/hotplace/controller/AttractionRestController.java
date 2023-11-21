package com.ssafy.enjoytrip.hotplace.controller;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.board.model.dto.BoardDto;
import com.ssafy.enjoytrip.hotplace.model.dao.AttractionDao;
import com.ssafy.enjoytrip.hotplace.model.dto.AttractionInfoDto;
import com.ssafy.enjoytrip.hotplace.service.AttractionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@Api(value = "관광지 REST-ful위한 API", description = "관광지 게시판")
@RestController // Controller 내에서 작성하는 모든 메서드에 기본적으로 @ResponseBody로 출력됨.
@RequestMapping("/hotplace") // 요청하는 자원(Domain)명을 붙인다. ==> /book이 이미 있어서 /rest로 함

public class AttractionRestController {
	private Logger logger = LoggerFactory.getLogger(AttractionRestController.class);
	private AttractionService attractionService;

	private static final String SUCCESS = "success";
	private static final String FAILURE = "failure";

	public AttractionRestController(AttractionService attractionService) {
		this.attractionService = attractionService;
	}
	
	/**
	 * ResponseEntity 응답 코드 + 응답 데이터를 전송하기 위한 객체
	 */
	@ExceptionHandler
	public ResponseEntity<String> handler(Exception e) {
		e.printStackTrace();
		logger.error("hotplace.error...................msg:{}", e.getMessage());

		// 에러 메시지가 한글인 경우 깨지므로 한글 처리를 위한 설정
		HttpHeaders resHeader = new HttpHeaders();
		resHeader.add("Content-type", "application/json; charset=UTF-8");
		String errorMsg = "";
		if (e instanceof Exception)
			errorMsg = e.getMessage();
		else {
			errorMsg = "Hotplace 처리 중 오류 발생";
		}
		return new ResponseEntity<String>(errorMsg, resHeader, HttpStatus.FAILED_DEPENDENCY);
	}
	
	@ApiOperation(value = "위치에 따른 여행 정보 리스트", notes = "여행 정보 리스트")
	@ApiResponse(code = 200, message = "success")
	@GetMapping("/{areaCode}/{sigunguCode}")
	public ResponseEntity<?> searchAttractionList(@PathVariable String areaCode, @PathVariable String sigunguCode) {
		
		List<AttractionInfoDto> boards = attractionService.attractionList(Integer.parseInt(areaCode), Integer.parseInt(sigunguCode));
		
		if (boards != null) {
			return new ResponseEntity(boards, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@ApiOperation(value="위치와 타입에 따른 여행 정보 리스트", notes = "타입에 따른 여행 정보 리스트")
	@ApiResponse(code = 200, message = "success")
	@GetMapping("/{areaCode}/{sigunguCode}/{contentTypeId}")
	public ResponseEntity<?> searchAttractionListByType(@PathVariable String areaCode, @PathVariable String sigunguCode, @PathVariable String contentTypeId) {
		List<AttractionInfoDto> boards = attractionService.attractionListByType(Integer.parseInt(areaCode), Integer.parseInt(sigunguCode), Integer.parseInt(contentTypeId));
		
		if(boards != null) {
			return new ResponseEntity(boards, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@ApiOperation(value="지역과 제목에 따른 여행 정보 리스트", notes = "지역과 제목에 따른 여행 정보 리스트")
	@ApiResponse(code = 200, message = "success")
	@GetMapping("/{areaCode}/{sigunguCode}/{title}")
	public ResponseEntity<?> searchByTitle(@PathVariable String title, @PathVariable String areaCode, @PathVariable String sigunguCode) {
		List<AttractionInfoDto> boards = attractionService.searchByTitle(title, Integer.parseInt(areaCode), Integer.parseInt(sigunguCode));
		
		if(boards != null) {
			return new ResponseEntity(boards, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}

}
