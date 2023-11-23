package com.ssafy.enjoytrip.hotplace.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.board.model.dto.BoardDto;
import com.ssafy.enjoytrip.hotplace.model.dto.ZzimDto;
import com.ssafy.enjoytrip.hotplace.service.ZzimService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;


@Api(value = "zzim 위한 API", description = "찜 목록")
@RestController // Controller 내에서 작성하는 모든 메서드에 기본적으로 @ResponseBody로 출력됨.
@RequestMapping("/zzim") // 요청하는 자원(Domain)명을 붙인다. ==> /book이 이미 있어서 /rest로 함

public class ZzimRestController {
	private Logger logger = LoggerFactory.getLogger(ZzimRestController.class);
	private ZzimService zzimService;

	private static final String SUCCESS = "success";
	private static final String FAILURE = "failure";

	public ZzimRestController(ZzimService zzimService) {
		this.zzimService = zzimService;
	}
	
	@ApiOperation(value="아이디에 따른 찜 목록", notes = "아이디에 따른 찜 리스트")
	@ApiResponse(code = 200, message = "success")
	@GetMapping("/{userId}")
	public ResponseEntity<?> getZzimList(@PathVariable String userId) {
		List<ZzimDto> list = zzimService.zzimList(userId);
		
		if(list != null) {
			return new ResponseEntity(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@ApiOperation(value="찜목록에 관광지 추가", notes = "찜목록에 관광지 추가")
	@ApiResponse(code = 200, message = "success")
	@PostMapping
	public ResponseEntity<?> addZzimList(@RequestBody ZzimDto zzimDto) {
		
		zzimService.addZzimList(zzimDto);
		
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@ApiOperation(value = "게시글 정보 삭제", notes = "게시글 정보를 삭제한다. ")
	@ApiResponse(code = 200, message = "success")
	@DeleteMapping("/{userId}/{contentId}")
	public ResponseEntity<String> deleteZzim(@PathVariable String userId, @PathVariable String contentId) {
		zzimService.deleteZzim(userId, Integer.parseInt(contentId));
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
}