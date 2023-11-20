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
	public List<CommentDto> listComment(int articleNo) {
		List<CommentDto> list = null;
		try {
			list = commentDao.listComment(articleNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void writeComment(CommentDto commentDto) {
		try {
			commentDao.writeComment(commentDto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateComment(CommentDto commentDto) {
		try {
			commentDao.updateComment(commentDto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteComment(CommentDto commentDto) {
		try {
			commentDao.deleteComment(commentDto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public CommentDto searchComment(Map<String, Integer> map) {
		CommentDto cDto = null;
		try {
			cDto = commentDao.searchComment(map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cDto;
	}

}
