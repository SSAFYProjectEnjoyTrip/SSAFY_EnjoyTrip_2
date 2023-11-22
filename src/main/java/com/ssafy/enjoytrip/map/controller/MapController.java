package com.ssafy.enjoytrip.map.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.map.model.dto.GugunDto;
import com.ssafy.enjoytrip.map.model.dto.MarkerDto;
import com.ssafy.enjoytrip.map.model.dto.SidoDto;
import com.ssafy.enjoytrip.map.service.MapService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "핫플찾기 API", description = "/hotPlace API")
@RestController
@RequestMapping("/map")
public class MapController {
	private Logger logger = LoggerFactory.getLogger(MapController.class);
	private MapService mapService;
	
	private static final String SUCCESS = "success";
	
	public MapController(MapService mapService) {
		this.mapService = mapService;
	}
	
	@ApiOperation(value="시도 정보 조회")
	@GetMapping("/getSido")
	public ResponseEntity<?> getSido() {
		
		List<SidoDto> sidoList = null;
		try {
			sidoList = mapService.getSido();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.debug("map.getSido...................sidoList:{}", sidoList);
		
		if (sidoList != null && !sidoList.isEmpty()) {
			return new ResponseEntity<List<SidoDto>>(sidoList, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@ApiOperation(value="구군 정보 조회")
	@GetMapping("/getGugun/{sidoCode}")
	public ResponseEntity<?> getGugun(@PathVariable String sidoCode) {
		logger.debug("여기까지 잘 왔어???: {}", sidoCode);
		List<GugunDto> gugunList = null;
		try {
			logger.debug(mapService.getGugun(Integer.parseInt(sidoCode)).toString());
			gugunList = mapService.getGugun(Integer.parseInt(sidoCode));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.debug("map.getGugun...................sidoList:{}", gugunList);
		
		if (gugunList != null && !gugunList.isEmpty()) {
			return new ResponseEntity<List<GugunDto>>(gugunList, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
		
}
