package com.ssafy.enjoytrip.hotplace.controller;

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
		logger.error("book.error...................msg:{}", e.getMessage());

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
	
	@ApiOperation(value = "게시글 리스트", notes = "여행 정보 리스트")
	@ApiResponse(code = 200, message = "success")
	@GetMapping("/{areaCode}/{sigunguCode}")
	public ResponseEntity<?> search(@PathVariable String areaCode, @PathVariable String sigunguCode) {
		AttractionInfoDto board = attractionService.attractionList(attractionInfoDto));

		if (board != null) {
			return new ResponseEntity(board, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
//	private void getTripList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		int areaCode = Integer.parseInt(!request.getParameter("areaCode").isEmpty() ? request.getParameter("areaCode") : "0");
//		int sigunguCode = Integer.parseInt(!request.getParameter("sigunguCode").isEmpty()? request.getParameter("sigunguCode") : "0");
//		int contentTypeId = Integer.parseInt(!request.getParameter("contentTypeId").isEmpty() ? request.getParameter("contentTypeId") : "0");
//		int numOfRows = Integer.parseInt(!request.getParameter("numOfRows").isEmpty() ? request.getParameter("numOfRows") : "10");
//		int pageNo = Integer.parseInt(!request.getParameter("pageNo").isEmpty() ? request.getParameter("pageNo") : "1");
//
//
//		AttractionInfoDto dto = new AttractionInfoDto();
//		dto.setSidoCode(areaCode);
//		dto.setGugunCode(sigunguCode);
//		dto.setContentTypeId(contentTypeId);
//
//		System.out.println(dto.toString());
//
//		// 리스트로 변환
//		List<AttractionInfoDto> pageData = service.attractionList(dto).stream()
//				.skip((pageNo - 1) * numOfRows) // 스킵할 요소의 수 계산
//				.limit(numOfRows) // 원하는 개수만큼 가져오기
//				.collect(Collectors.toList());
//
//		System.out.println(pageData.toString());
//
//		BiFunction<AttractionInfoDto, ObjectMapper, ObjectNode> mapper = (i, o) -> {
//			ObjectNode item = o.createObjectNode();
//
//			item.put("addr1", i.getAddr1());
//			item.put("addr2", i.getAddr2());
//			item.put("areacode", i.getSidoCode());
//			item.put("contentid", i.getContentId());
//			item.put("contenttypeid", i.getContentTypeId());
//			item.put("firstimage", i.getFirstImage());
//			item.put("firstimage2", i.getFirstImage2());
//			item.put("mapx", i.getLongitude());
//			item.put("mapy", i.getLatitude());
//			item.put("mlevel", i.getMlevel());
//			item.put("sigungucode", i.getGugunCode());
//			item.put("tel", i.getTel());
//			item.put("title", i.getTitle());
//			item.put("zipcode", i.getZipcode());
//
//			return item;
//		};
//
//		makeJsonResponse(pageData, mapper, request, response);
//	}

}
