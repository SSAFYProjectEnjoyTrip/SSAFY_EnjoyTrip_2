package com.ssafy.enjoytrip.board.model.dao;

import java.sql.SQLException;
import java.util.*;
import org.apache.ibatis.annotations.Mapper;
import com.ssafy.enjoytrip.board.model.dto.BoardDto;
import com.ssafy.enjoytrip.board.model.dto.CommentDto;
import com.ssafy.enjoytrip.board.model.dto.PageBean;

@Mapper
public interface CommentDao {

	List<CommentDto> listComment(int articleNo) throws SQLException;
	CommentDto searchComment(Map<String, Integer> map) throws SQLException;
	void writeComment(CommentDto commentDto) throws SQLException;
	void updateComment(CommentDto commentDto) throws SQLException;
	void deleteComment(CommentDto commentDto) throws SQLException;
	
}
