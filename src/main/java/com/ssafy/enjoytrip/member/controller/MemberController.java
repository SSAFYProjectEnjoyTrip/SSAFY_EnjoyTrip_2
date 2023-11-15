package com.ssafy.enjoytrip.member.controller;

import java.lang.reflect.Member;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ssafy.enjoytrip.member.model.dto.MemberDto;
import com.ssafy.enjoytrip.member.service.MemberService;
import com.ssafy.enjoytrip.util.JWTUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@Api(value = "회원 관리 API", description = "/user API")
//@CrossOrigin(origins = { "*" }) // 다른 서버에서 Ajax 요청이 와도 서비스 되도록 설정
@RestController
@RequestMapping("/user")
public class MemberController {

	private Logger logger = LoggerFactory.getLogger(MemberController.class);
	private MemberService memberService;
	private JWTUtil jwtUtil;

	public MemberController(MemberService memberService, JWTUtil jwtUtil) {
		super();
		this.memberService = memberService;
		this.jwtUtil = jwtUtil;
	}

	private static final String SUCCESS = "success";
	private static final String FAILURE = "failure";
	/*
	 * @Autowired PasswordEncoder passwordEncoder;
	 */

//	@Inject
//	private BCryptPasswordEncoder passwordEncoder;
	// 비밀번호 암호화 처리
	// user.setPwd(passwordEncoder.encode(user.getPwd()));
	// 암호화된 비밀번호 일치여부 체크
	// passwordEncoder.matches(pwd, user.getPwd());

	// 회원가입 페이지로 이동

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
			errorMsg = "처리 중 오류 발생";
		}
		return new ResponseEntity<String>(errorMsg, resHeader, HttpStatus.FAILED_DEPENDENCY);
	}

	@PostMapping("/register")
	@ApiOperation(value = "회원가입", notes = "유저 정보를 등록")
	public ResponseEntity<?> userRegister(@RequestBody MemberDto member) {
		logger.debug("member.login......................... Member:{}", member.getUserId());
		logger.debug("member.login......................... service:{}", memberService);
		// return new ResponseEntity<Void>(HttpStatus.OK);
		// int num = service.idCheck(member.getUserId());
		memberService.joinMember(member);
		return new ResponseEntity<Void>(HttpStatus.OK);
//		if(num == 1) {
//			
//			
//		}
//		else {
//			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//		}		
	}

	@ApiOperation(value = "로그인", notes = "아이디와 비밀번호를 이용하여 로그인 처리.")
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(
			@RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) MemberDto memberDto) {
		logger.debug("login user : {}", memberDto);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			MemberDto loginUser = memberService.login(memberDto);
			if (loginUser != null) {
				String accessToken = jwtUtil.createAccessToken(loginUser.getUserId());
				String refreshToken = jwtUtil.createRefreshToken(loginUser.getUserId());
				logger.debug("access token : {}", accessToken);
				logger.debug("refresh token : {}", refreshToken);

//				발급받은 refresh token을 DB에 저장.
				memberService.saveRefreshToken(loginUser.getUserId(), refreshToken);

//				JSON으로 token 전달.
				resultMap.put("access-token", accessToken);
				resultMap.put("refresh-token", refreshToken);

				status = HttpStatus.CREATED;
			} else {
				resultMap.put("message", "아이디 또는 패스워드를 확인해주세요.");
				status = HttpStatus.UNAUTHORIZED;
			}

		} catch (Exception e) {
			logger.debug("로그인 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "회원인증", notes = "회원 정보를 담은 Token을 반환한다.", response = Map.class)
	@GetMapping("/info/{userId}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("userId") @ApiParam(value = "인증할 회원의 아이디.", required = true) String userId,
			HttpServletRequest request) {
//		logger.debug("userId : {} ", userId);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtUtil.checkToken(request.getHeader("Authorization"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				MemberDto memberDto = memberService.userInfo(userId);
				resultMap.put("userInfo", memberDto);
				status = HttpStatus.OK;
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/{userId}")
	@ApiOperation(value = "유저 정보 조회", notes = "어드민이 유저 정보를 조회할때 사용합니다.")
	public ResponseEntity<?> findMember(@PathVariable String userId) {
		logger.debug("member.findMember............................ Id:{}", userId);
		MemberDto find = memberService.getMemberById(userId);
		logger.debug("member.findMember......................... Member:{}", find);
		if (find != null) {
			return new ResponseEntity<MemberDto>(find, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}

	@ApiOperation(value = "로그아웃", notes = "회원 정보를 담은 Token을 제거한다.", response = Map.class)
	@GetMapping("/logout/{userId}")
	public ResponseEntity<?> removeToken(
			@PathVariable("userId") @ApiParam(value = "로그아웃할 회원의 아이디.", required = true) String userId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			memberService.deleRefreshToken(userId);
			status = HttpStatus.OK;
		} catch (Exception e) {
			logger.error("로그아웃 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);

	}

	@ApiOperation(value = "Access Token 재발급", notes = "만료된 access token을 재발급받는다.", response = Map.class)
	@PostMapping("/refresh")
	public ResponseEntity<?> refreshToken(@RequestBody MemberDto memberDto, HttpServletRequest request)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String token = request.getHeader("refreshToken");
		logger.debug("token : {}, memberDto : {}", token, memberDto);
		if (jwtUtil.checkToken(token)) {
			if (token.equals(memberService.getRefreshToken(memberDto.getUserId()))) {
				String accessToken = jwtUtil.createAccessToken(memberDto.getUserId());
				logger.debug("token : {}", accessToken);
				logger.debug("정상적으로 액세스토큰 재발급!!!");
				resultMap.put("access-token", accessToken);
				status = HttpStatus.CREATED;
			}
		} else {
			logger.debug("리프레쉬토큰도 사용불가!!!!!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@DeleteMapping("/{userId}")
	@ApiOperation(value = "유저 삭제", notes = "유저 아이디로 유저 정보 삭제")
	public ResponseEntity<?> removeUser(@RequestParam String userId) {
		logger.debug("member.delete............................ Id:{}", userId);
		memberService.deleteUserById(userId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PutMapping("/updateEmail")
	@ApiOperation(value = "이메일 수정", notes = "유저의 이메일을 수정")
	public ResponseEntity<?> updateEmail(@RequestParam String userId, @RequestParam String newEmailDomain,
			@RequestParam String newEmailId) {
		logger.debug("member.updateEmail............................ Id:{}", userId);
		logger.debug("member.updateEmail............................ Domain:{}", newEmailDomain);
		logger.debug("member.updateEmail............................ EmailId:{}", newEmailId);

		memberService.updateEmailById(userId, newEmailId, newEmailDomain);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);

	}

	@PutMapping("/updatePwd")
	@ApiOperation(value = "이메일 수정", notes = "유저의 이메일을 수정")
	public ResponseEntity<?> updatePwd(@RequestParam String userId, @RequestParam String newPwd) {
		logger.debug("member.updatePwd............................ Id:{}", userId);
		logger.debug("member.updatePwd............................ Pwd:{}", newPwd);

		memberService.updatePwdById(userId, newPwd);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);

	}
}
