package com.ssafy.enjoytrip.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
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
	
	@ApiOperation(value="게시글 댓글 정보 조회", notes = "articleNo에 해당하는 정보 조회")
//	@ApiResponse(code = 200, message = "success")
	@GetMapping("/{articleNo}")
	public ResponseEntity<?> searchComment(@PathVariable String articleNo){

		//articleNo 받아서 관련된 모든 댓글을 긁어옴
		List<CommentDto> list = commentService.listComment(Integer.parseInt(articleNo));
		
		Map<String, List<CommentDto>> result = new HashMap<String, List<CommentDto>>();
		result.put("comments", list);
			
		if (list != null) {
			return new ResponseEntity(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@ApiOperation(value="게시글 댓글 등록", notes = "cDto 댓글 정보 등록")
	@ApiResponse(code = 200, message = "success")
	@PostMapping
	public ResponseEntity<?> writeComment(@RequestBody CommentDto cDto, @ApiIgnore HttpSession session){
		MemberDto mDto = (MemberDto) session.getAttribute("loginUser");
//		logger.debug("등록할 때 userId 뜨나? : {})", mDto.getUserId());
		// TODO : setUserId 바꾸기
		cDto.setUserId("admin");
		// cDto.setCommentNo(commentService.listComment(cDto.getArticleNo()).size()+1);
		
		commentService.writeComment(cDto);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@ApiOperation(value="댓글 정보 변경", notes = "해당하는 댓글 덮어쓰기")
	@ApiResponse(code = 200, message = "success")
	@PutMapping
	public ResponseEntity<?> updateComment(@RequestBody CommentDto cDto){
		logger.debug("BEFORE comment update......................cDto: {}", cDto);
		commentService.updateComment(cDto);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	//TODO : 필요한 인자만 받아오기?
	@ApiOperation(value="댓글 정보 삭제", notes = "해당하는 댓글 삭제하기")
	@ApiResponse(code = 200, message = "success")
	@DeleteMapping
	public ResponseEntity<?> deleteComment(@RequestBody CommentDto cDto){
		logger.debug("comment delete......................cDto: {}", cDto);
		commentService.deleteComment(cDto);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@ApiOperation(value="댓글 정보 조회", notes = "해당하는 댓글 조회하기")
	@ApiResponse(code = 200, message = "success")
	@GetMapping("/{articleNo}/{commentNo}")
	public ResponseEntity<?> searchComment(@PathVariable String articleNo, @PathVariable String commentNo){
//		logger.debug("comment search......................articleNo: {}", articleNo);
//		logger.debug("comment search......................commentNo: {}", commentNo);
		Map<String, Integer> param = new HashMap<String, Integer>();
		param.put("articleNo", Integer.parseInt(articleNo));
		param.put("commentNo", Integer.parseInt(commentNo));
		
		CommentDto cDto = commentService.searchComment(param);
//		logger.debug("comment search.....................cDto: {}", cDto);
		
		if (cDto != null) {
			return new ResponseEntity(cDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}

}
