package com.ssafy.enjoytrip.board.service;

import java.sql.SQLException;
import java.util.*;

import com.ssafy.enjoytrip.board.model.dto.CommentDto;
import com.ssafy.enjoytrip.board.model.dto.PageBean;

public interface CommentService {
	List<CommentDto> listComment(int articleNo);
	CommentDto searchComment(Map<String, Integer> map);
	void writeComment(CommentDto commentDto);
	void updateComment(CommentDto commentDto);
	void deleteComment(CommentDto commentDto);
}
