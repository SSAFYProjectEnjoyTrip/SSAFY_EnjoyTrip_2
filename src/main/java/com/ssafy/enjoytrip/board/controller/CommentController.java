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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.ssafy.enjoytrip.board.model.dto.CommentDto;
import com.ssafy.enjoytrip.board.model.dto.PageBean;
import com.ssafy.enjoytrip.board.service.BoardService;
import com.ssafy.enjoytrip.board.service.CommentService;
import com.ssafy.enjoytrip.member.model.dto.MemberDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import springfox.documentation.annotations.ApiIgnore;

@Api(value = "Board REST-ful위한 API", description = "댓글")
@RestController // Controller 내에서 작성하는 모든 메서드에 기본적으로 @ResponseBody로 출력됨.
//@Controller
@RequestMapping("/comment")
public class CommentController {
	private Logger logger = LoggerFactory.getLogger(CommentController.class);
	private CommentService commentService;

	private static final String SUCCESS = "success";
	private static final String FAILURE = "failure";
	
	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}

	/**
	 * ResponseEntity 응답 코드 + 응답 데이터를 전송하기 위한 객체
	 */
	@ExceptionHandler
	public ResponseEntity<String> handler(Exception e){
		e.printStackTrace();
		logger.error("Comment.error...................msg:{}", e.getMessage());
		
		// 에러 메시지가 한글인 경우 깨지므로 한글 처리를 위한 설정
		HttpHeaders resHeader = new HttpHeaders();
		resHeader.add("Content-type", "application/json; charset=UTF-8");
		String errorMsg = "";
		if (e instanceof Exception)
			errorMsg = e.getMessage();
		else {
			errorMsg = "Comment 처리 중 오류 발생";
		}
			return new ResponseEntity<String>(errorMsg, resHeader, HttpStatus.FAILED_DEPENDENCY);
	}
	
	@ApiOperation(value="게시글 정보 조회", notes = "articleNo에 해당하는 정보 조회")
//	@ApiResponse(code = 200, message = "success")
	@GetMapping("/{articleNo}")
	public ResponseEntity<?> searchComment(@PathVariable String articleNo){

		//articleNo 받아서 관련된 모든 댓글을 긁어옴
		List<CommentDto> list = commentService.searchComment(Integer.parseInt(articleNo));
		
		Map<String, List<CommentDto>> result = new HashMap<String, List<CommentDto>>();
		result.put("comments", list);
		
		logger.debug("왜 안나오는거야 list : {}", list);
		logger.debug("그럼 result는 어케 나오는데!!! : {}", result.get("comments"));
		
		//여기서는 list 잘 나오는데.. 왜 vue에서는 undefined라고 뜨지????????????
		
//		if (list != null && !list.isEmpty()) {
//			return new ResponseEntity<Map<String, List<CommentDto>>>(result, HttpStatus.OK);
//		} else {
//			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//		}
		
		if (list != null) {
			logger.debug("안에서는 어케 되나? : {}", list);
			logger.debug("야! : {}", new ResponseEntity(list, HttpStatus.OK));
			return new ResponseEntity(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}

}
