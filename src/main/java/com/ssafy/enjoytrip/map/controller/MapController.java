package com.ssafy.enjoytrip.map.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.map.model.dto.GugunDto;
import com.ssafy.enjoytrip.map.model.dto.MarkerDto;
import com.ssafy.enjoytrip.map.model.dto.SidoDto;
import com.ssafy.enjoytrip.map.model.dto.SidoGugunCodeDto;
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
		
		List<SidoGugunCodeDto> sidoList = null;
		try {
			sidoList = mapService.getSido();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		logger.debug("map.getSido...................sidoList:{}", sidoList);
		
		if (sidoList != null && !sidoList.isEmpty()) {
			return new ResponseEntity<List<SidoGugunCodeDto>>(sidoList, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@ApiOperation(value="구군 정보 조회")
	@GetMapping("/getGugun")
	public ResponseEntity<?> getGugun(@RequestParam int sidoCode) {
		
		List<GugunDto> gugunList = mapService.getGugun(sidoCode);
		
		logger.debug("map.getGugun...................gugunList:{}", gugunList);

		if (gugunList != null && !gugunList.isEmpty()) {
			return new ResponseEntity<List<GugunDto>>(gugunList, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@ApiOperation(value="마커 정보 조회")
	@GetMapping("/getMarker")
	public ResponseEntity<?> getMarker(@RequestParam int contentTypeId, @RequestParam int sidoCode, @RequestParam int gugunCode) {
		List<MarkerDto> markerList = mapService.getMarker(contentTypeId, sidoCode, gugunCode);
		
		logger.debug("map.getMarker...................markerList:{}", markerList);

		if (markerList != null && !markerList.isEmpty()) {
			return new ResponseEntity<List<MarkerDto>>(markerList, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@ApiOperation(value = "시도 정보", notes = "전국의 시도를 반환한다.", response = List.class)
	@GetMapping("/sido")
	public ResponseEntity<List<SidoGugunCodeDto>> sido() throws Exception {
		logger.info("sido - 호출");
		return new ResponseEntity<List<SidoGugunCodeDto>>(mapService.getSido(), HttpStatus.OK);
	}

	@ApiOperation(value = "구군 정보", notes = "시도에 속한 구군을 반환한다.", response = List.class)
	@GetMapping("/gugun")
	public ResponseEntity<List<SidoGugunCodeDto>> gugun(
			@RequestParam("sido") @ApiParam(value = "시도코드.", required = true) String sido) throws Exception {
		logger.info("gugun - 호출");
		return new ResponseEntity<List<SidoGugunCodeDto>>(mapService.getGugunInSido(sido), HttpStatus.OK);
	}

	
}
