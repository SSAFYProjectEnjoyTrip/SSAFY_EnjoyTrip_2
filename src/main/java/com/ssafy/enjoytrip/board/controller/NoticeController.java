package com.ssafy.enjoytrip.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.board.model.dto.BoardDto;
import com.ssafy.enjoytrip.board.model.dto.PageBean;
import com.ssafy.enjoytrip.board.service.BoardService;
import com.ssafy.enjoytrip.member.model.dto.MemberDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import springfox.documentation.annotations.ApiIgnore;

@Api(value = "Notice REST-ful위한 API")
@RestController 
@RequestMapping("/notice")
public class NoticeController {
	
	private Logger logger = LoggerFactory.getLogger(BoardRestController.class);
	private BoardService boardService;

	private static final String SUCCESS = "success";
	private static final String FAILURE = "failure";
	
	public NoticeController(BoardService boardService) {
		this.boardService = boardService;
	}

	/**
	 * ResponseEntity 응답 코드 + 응답 데이터를 전송하기 위한 객체
	 */
	@ExceptionHandler
	public ResponseEntity<String> handler(Exception e){
		e.printStackTrace();
		logger.error("notice.error...................msg:{}", e.getMessage());
		
		// 에러 메시지가 한글인 경우 깨지므로 한글 처리를 위한 설정
		HttpHeaders resHeader = new HttpHeaders();
		resHeader.add("Content-type", "application/json; charset=UTF-8");
		String errorMsg = "";
		if (e instanceof Exception)
			errorMsg = e.getMessage();
		else {
			errorMsg = "notice 처리 중 오류 발생";
		}
			return new ResponseEntity<String>(errorMsg, resHeader, HttpStatus.FAILED_DEPENDENCY);
	}
	
	@ApiOperation(value = "공지사항 리스트", notes = "공지사항 리스트")
	@ApiResponse(code = 200, message = "success")
	@GetMapping
	public ResponseEntity<?> listNotice(PageBean bean) {
		logger.debug("notice.searchAll.......................................listNotice, bean:{}", bean);
		List<BoardDto> notices = boardService.listNotice(bean);

		logger.debug("notice.searchAll.......................................notices:{}", notices);
		logger.debug("notice.searchAll.......................................bean:{}", bean);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("boards", notices);
		result.put("page", bean);

		if (notices != null && !notices.isEmpty()) {
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@ApiOperation(value = "공지사항 등록", notes = "공지사항을 등록한다.")
	@ApiResponse(code = 200, message = "success")
	@PostMapping("/insertNotice")
	public ResponseEntity<String> insertNotice(@RequestBody BoardDto bDto, @ApiIgnore HttpSession session) {

		MemberDto mDto = (MemberDto) session.getAttribute("loginUser");
		System.out.println(mDto);
		// userId, subject, content 입력
		
		// TODO : setUserId 바꾸기
		bDto.setUserId("admin");
		System.out.println(bDto);
		boardService.writeArticle(bDto);

		// return "redirect:/board/boardMain";
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	@ApiOperation(value="공지사항 수정", notes="공지사항을 수정한다.")
	@ApiResponse(code = 200, message = "success")
	@PutMapping
	public ResponseEntity<String> modifyNotice(@RequestBody BoardDto bDto){
		logger.debug("notice.update...........................bDto:{}",bDto);
		boardService.modifyArticle(bDto);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@ApiOperation(value="공지사항 글 삭제", notes="공지사항 글을 삭제한다. ")
	@ApiResponse(code = 200, message = "success")
	@DeleteMapping("/{articleNo}")
	public ResponseEntity<String> deleteNotice(@PathVariable int articleNo){
		
		logger.debug("notice.remove...........................articleNo:{}",articleNo);
		boardService.deleteArticle(articleNo);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@ApiOperation(value="공지사항 조회", notes = "articleNo에 해당하는 공지사항 세부 조회")
	@GetMapping("/{articleNo}")
	public ResponseEntity<?> detailNotice(@PathVariable int articleNo){

		BoardDto notice = boardService.getArticle(articleNo);

		if (notice != null) {
			return new ResponseEntity(notice, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	
}
