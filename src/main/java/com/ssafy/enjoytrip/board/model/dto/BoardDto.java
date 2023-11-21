package com.ssafy.enjoytrip.board.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;


@ApiModel(value="Board Dto" ,description="게시판을 구성하는 DTO")
@Setter
@NoArgsConstructor
@Data
public class BoardDto {
	
	@ApiModelProperty(value="게시글 번호", example="0")
	private int articleNo;
	@ApiModelProperty(value="사용자 ID", example="ssafy")
	private String userId;
	@ApiModelProperty(value="제목",example="테스트 제목")
	private String subject;
	@ApiModelProperty(value="글 내용", example="테스트 내용")
	private String content;
	@ApiModelProperty(value="조회수", example="0")
	private int hit;
	@ApiModelProperty(value="작성 시각", example="1999-01-01")
	private String registerTime;
	
	@ApiModelProperty(value="리뷰 사진 파일",example="리뷰 사진 파일")
	private String rfile;
	@ApiModelProperty(value="별점",example="리뷰 별점")
	private int star;
	
	public int getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(int articleNo) {
		this.articleNo = articleNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}
	
	@Override
	public String toString() {
		return "BoardDto [articleNo=" + articleNo + ", userId=" + userId + ", rfile=" + rfile + ", star=" + star
				+ ", subject=" + subject + ", content=" + content + ", hit=" + hit + ", registerTime=" + registerTime
				+ "]";
	}
	public BoardDto(int articleNo, String userId, String rfile, int star, String subject, String content, int hit,
			String registerTime) {
		super();
		this.articleNo = articleNo;
		this.userId = userId;
		this.rfile = rfile;
		this.star = star;
		this.subject = subject;
		this.content = content;
		this.hit = hit;
		this.registerTime = registerTime;
	}
	
	
	
	
}
