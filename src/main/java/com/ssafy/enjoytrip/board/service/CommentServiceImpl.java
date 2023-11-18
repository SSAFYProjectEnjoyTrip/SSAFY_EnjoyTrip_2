package com.ssafy.enjoytrip.board.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.MyException;
import com.ssafy.enjoytrip.board.model.dao.BoardDao;
import com.ssafy.enjoytrip.board.model.dao.CommentDao;
import com.ssafy.enjoytrip.board.model.dto.BoardDto;
import com.ssafy.enjoytrip.board.model.dto.CommentDto;
import com.ssafy.enjoytrip.board.model.dto.PageBean;
import com.ssafy.enjoytrip.util.PageNavigation;
import com.ssafy.enjoytrip.util.PageUtility;
import com.ssafy.enjoytrip.util.SizeConstant;

@Service("CommentService")
@Repository
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDao commentDao;

	public CommentServiceImpl(CommentDao commentDao) {
		this.commentDao = commentDao;
	}

	@Override
	public List<CommentDto> searchComment(int articleNo) {
		List<CommentDto> list = null;
		try {
			list = commentDao.searchComment(articleNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
