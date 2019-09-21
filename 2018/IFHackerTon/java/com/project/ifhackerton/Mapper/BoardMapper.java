package com.project.ifhackerton.Mapper;

import com.project.ifhackerton.VO.Board;
import com.project.ifhackerton.VO.Comment;
import com.project.ifhackerton.VO.Paging;

import java.util.List;


public interface BoardMapper {

	// 글 삽입 ( 게시글 내용, 게시글 작성자 - 세션에서 받아옴 )
	public void boardInsert(Board Board);

	// 글 삭제 ( 게시글 내용, 게시글 작성자 - 세션에서 받아옴 )
	public void boardDelete(int board_idx);

	// 글 수정 ( 게시글 내용, 게시글 작성자 - 세션에서 받아옴 )
	public void boardUpdate(Board Board);

	// 글 페이지 카운트 ( 글 총 갯수, 이전페이지, 다음 페이지 사용 )
	public int boardListCount(int member_idx);

	// 글 내용 세부 조회
	public Board boardDetailSelection(int board_idx);

	// 글 리스트 조회
	public List<Board> boardListSelection(Paging paging);

	// 댓글 삽입
	public void commentInsert(Comment Comment);

	// 댓글 확인
	public List<Comment> commentListSelection(int board_idx);

	// 댓글 삭제
	public void commentDelete(int comment_idx);

	// 댓글 상세 조회
	public Comment commentDetailSelect(int comment_idx);
}
