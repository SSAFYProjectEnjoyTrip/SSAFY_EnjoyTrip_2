package com.ssafy.enjoytrip.member.service;

import java.sql.SQLException;

import com.ssafy.enjoytrip.member.model.dto.MemberDto;

public interface MemberService {

	int idCheck(String userId);
	int joinMember(MemberDto memberDto);
	MemberDto getMemberById(String userId);
	String getPwdById(String curId);
	void updatePwdById(String curId, String newPwd);
	void updateEmailById(String userId, String newEmailId, String newEmailDomain);
	void deleteUserById(String userId);
	
	MemberDto login(MemberDto memberDto) throws Exception;
	MemberDto userInfo(String userId) throws Exception;
	void saveRefreshToken(String userId, String refreshToken) throws Exception;
	Object getRefreshToken(String userId) throws Exception;
	void deleRefreshToken(String userId) throws Exception;
	
}
