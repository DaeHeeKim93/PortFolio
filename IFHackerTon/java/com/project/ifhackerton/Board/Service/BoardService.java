package com.project.ifhackerton.Board.Service;


import com.project.ifhackerton.VO.Board;
import com.project.ifhackerton.VO.Comment;
import com.project.ifhackerton.VO.MemberSession;

import java.util.List;

public interface BoardService {

	// 글 삽입 ( 게시글 내용, 게시글 작성자 - 세션에서 받아옴 )
	public void boardInsert(Board Board, MemberSession memberSession);

	// 글 삭제 ( 게시글 내용, 게시글 작성자 - 세션에서 받아옴 )
	public void boardDelete(int Board, MemberSession memberSession);

	// 글 수정 ( 게시글 내용, 게시글 작성자 - 세션에서 받아옴 )
	public void boardUpdate(Board Board, MemberSession memberSession);

	// 글 페이지 카운트 ( 글 총 갯수, 이전페이지, 다음 페이지 사용 )
	public int boardListCount();

	// 글 세부 내용 조회 ( 글 한개)
	public Board boardDetailSelection(int board_idx);

	// 글 리스트 조회
	public List<Board> boardListSelection(int page);

	// 댓글 삽입
	public void commentInsert(Comment Comment, MemberSession memberSession);

	// 댓글 확인
	public List<Comment> commentListSelection(int board_idx);

	// 댓글 삭제
	public void commentDelete(int comment_idx, MemberSession memberSession);

	// 댓글 상세조회
	public Comment commentDetailSelect(int comment_idx);
}
