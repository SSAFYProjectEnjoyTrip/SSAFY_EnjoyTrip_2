package com.ssafy.enjoytrip.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

@Api(value = "Board REST-ful위한 API", description = "정보 공유 게시판")
@RestController // Controller 내에서 작성하는 모든 메서드에 기본적으로 @ResponseBody로 출력됨.
@RequestMapping("/board") // 요청하는 자원(Domain)명을 붙인다. ==> /book이 이미 있어서 /rest로 함
public class BoardRestController {
	private Logger logger = LoggerFactory.getLogger(BoardRestController.class);
	private BoardService boardService;

	private static final String SUCCESS = "success";
	private static final String FAILURE = "failure";

	public BoardRestController(BoardService boardService) {
		this.boardService = boardService;
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
			errorMsg = "Board 처리 중 오류 발생";
		}
		return new ResponseEntity<String>(errorMsg, resHeader, HttpStatus.FAILED_DEPENDENCY);
	}

	@ApiOperation(value = "게시글 리스트", notes = "여행 정보 리스트")
	@ApiResponse(code = 200, message = "success")
	@GetMapping
	public ResponseEntity<?> listArticle(PageBean bean) {
		logger.debug("board.searchAll.......................................bean:{}", bean);
		List<BoardDto> boards = boardService.listArticle(bean);

		logger.debug("board.searchAll.......................................books:{}", boards);
		logger.debug("board.searchAll.......................................bean:{}", bean);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("boards", boards);
		result.put("page", bean);

		if (boards != null && !boards.isEmpty()) {
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}

	// ${root}/board/insertReview
	@ApiOperation(value = "정보 공유 등록", notes = "여행 정보 리뷰를 등록한다.")
	@ApiResponse(code = 200, message = "success")
	@PostMapping("/insertReview")
	public ResponseEntity<String> insertReviewBoard(@ModelAttribute BoardDto bDto, @ApiIgnore HttpSession session) {
		System.out.println("들어오나요?^^");
		MemberDto mDto = (MemberDto) session.getAttribute("loginUser");
		System.out.println(mDto);
		// userId, subject, content 입력

		// 1. 파일 업로드 처리 - [1] 업로드 디렉토리 절대경로 얻기(resources/board_upload)
		ServletContext app = session.getServletContext();
		String upDir = app.getRealPath("/resources/review");
		File dir = new File(upDir);

		if (!dir.exists()) {
			dir.mkdirs();// 업로드 디렉토리 생성
		}
//
//		if (!bDto.getRfile().isEmpty()) {// 첨부파일이 있다면
//
//			// [2] 업로드한 파일명과 파일크기 알아내기==>setFilename(파일명), setFilesize(파일크기)
//			String fname = bDto.getRfile().getOriginalFilename();// 원본파일명
//			long fsize = bDto.getRfile().getSize();// 파일크기
//
//			UUID uid = UUID.randomUUID();
//			String rfile = uid.toString() + "_" + fname;
//
//			rb.set(rfile);
//
//			// [3] 업로드 처리
//			try {
//				mf.transferTo(new File(upDir, rfile));
//				log.info("upDir: " + upDir);
//
//			} catch (IOException e) {
//				log.error("파일업로드 에러: " + e);
//			}
//
//		}

		System.out.println("파일이름" + bDto.getRfile());
		System.out.println("별점" + bDto.getStar());

		// TODO : setUserId 바꾸기
		bDto.setUserId("ssafy");
		System.out.println(bDto);
		boardService.writeArticle(bDto);

		// return "redirect:/board/boardMain";
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	@ApiOperation(value = "게시글 수정", notes = "게시글 정보를 수정한다. ")
	@ApiResponse(code = 200, message = "success")
	@PutMapping
	public ResponseEntity<String> modifyArticle(@RequestBody BoardDto bDto) {
		logger.debug("bDto.update...........................bDto:{}", bDto);
		boardService.modifyArticle(bDto);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	@ApiOperation(value = "게시글 정보 삭제", notes = "게시글 정보를 삭제한다. ")
	@ApiResponse(code = 200, message = "success")
	@DeleteMapping("/{articleNo}")
	public ResponseEntity<String> deleteArticle(@PathVariable int articleNo) {
		logger.debug("bDto.remove...........................articleNo:{}", articleNo);
		// int articleNoInt = Integer.parseInt(articleNo);
		boardService.deleteArticle(articleNo);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	@ApiOperation(value = "게시글 정보 조회", notes = "articleNo에 해당하는 정보 조회")
	@GetMapping("/{articleNo}")
	public ResponseEntity<?> search(@PathVariable String articleNo) {

		BoardDto board = boardService.getArticle(Integer.parseInt(articleNo));

		if (board != null) {
			return new ResponseEntity(board, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
//	@ApiOperation(value="게시글 정보 조회", notes = "articleNo에 해당하는 정보 조회")
//	@GetMapping("/{articleNo}")
//	public ResponseEntity<?> searchBy(@PathVariable String articleNo){
//
//		BoardDto board = boardService.getArticle(Integer.parseInt(articleNo));
//
//		if (board != null) {
//			return new ResponseEntity(board, HttpStatus.OK);
//		} else {
//			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//		}
//	}


}
