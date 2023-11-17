package com.ssafy.enjoytrip.board.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(value = "Comment Dto", description = "댓글을 구성하는 DTO")
@Setter
@NoArgsConstructor
@Data
public class CommentDto {
	@ApiModelProperty(value = "댓글 번호", example = "0")
	private int commentNo;
	@ApiModelProperty(value = "게시글 번호", example = "0")
	private int articleNo;
	@ApiModelProperty(value = "사용자 ID", example = "ssafy")
	private String userId;
	@ApiModelProperty(value = "글 내용", example = "테스트 내용")
	private String commentContent;
	@ApiModelProperty(value = "작성 시각", example = "1999-01-01")
	private String registerTime;

	public CommentDto(int commentNo, int articleNo, String userId, String commentContent, String registerTime) {
		super();
		this.commentNo = commentNo;
		this.articleNo = articleNo;
		this.userId = userId;
		this.commentContent = commentContent;
		this.registerTime = registerTime;
	}

	@Override
	public String toString() {
		return "CommentDto [commentNo=" + commentNo + ", articleNo=" + articleNo + ", userId=" + userId
				+ ", commentContent=" + commentContent + ", registerTime=" + registerTime + "]";
	}

	int getCommentNo() {
		return commentNo;
	}

	void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}

	int getArticleNo() {
		return articleNo;
	}

	void setArticleNo(int articleNo) {
		this.articleNo = articleNo;
	}

	String getUserId() {
		return userId;
	}

	void setUserId(String userId) {
		this.userId = userId;
	}

	String getCommentContent() {
		return commentContent;
	}

	void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	String getRegisterTime() {
		return registerTime;
	}

	void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

}
