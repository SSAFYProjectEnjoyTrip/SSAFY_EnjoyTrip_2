package com.ssafy.enjoytrip.board.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.enjoytrip.board.model.dto.BoardDto;
import com.ssafy.enjoytrip.board.model.dto.PageBean;
import com.ssafy.enjoytrip.util.PageNavigation;

public interface BoardService {

	List<BoardDto> listArticle(PageBean bean);
	//PageNavigation makePageNavigation(Map<String, String> map);
	BoardDto getArticle(int articleNo);
	void updateHit(int articleNo);
	
	void modifyArticle(BoardDto boardDto);
	void deleteArticle(int articleNo);
	void writeArticle(BoardDto boardDto);
	
	// 공지사항 따로, admin만 있어야함
	List<BoardDto> listNotice(PageBean bean);
	
}
