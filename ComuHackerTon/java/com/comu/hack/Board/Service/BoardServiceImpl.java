package com.comu.hack.Board.Service;

import com.comu.hack.Main.Controller.MainController;
import com.comu.hack.Mapper.BoardMapper;
import com.comu.hack.VO.Board;
import com.comu.hack.VO.Comment;
import com.comu.hack.VO.MemberSession;
import com.comu.hack.VO.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;
/*
 * 제목 : BoardServiceImpl
 * 날짜 : 2018.10.05
 * 내용 : 자유게시판 글 쓰기, 수정, 리스트, 페이지, 삭제 등 Service Implement ( 구현체 )
 * */

@Service("com.comu.hack.Home.Service.BoardServiceImpl")
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper boardMapper; // HomeMapper Class 객체
    public static int pageCount = 10; // 전체 List 갯수



	// 글 삽입 ( 게시글 내용, 게시글 작성자 - 세션에서 받아옴 )
	public void boardInsert(Board Board, MemberSession memberSession){

        Board.setMember_idx(memberSession.getMember_idx()); // 멤버 ID
        Board.setBoard_id(memberSession.getId()); // 신고자 ID
        boardMapper.boardInsert(Board);

	};

	// 글 삭제 ( 게시글 내용, 게시글 작성자 - 세션에서 받아옴 )
	public void boardDelete(int board_idx, MemberSession memberSession){

	    // 내용 삭제 작업
        Board BoardDeleteObject  = boardMapper.boardDetailSelection(board_idx); // 한개의 값을 가져옵니다.

        // 세션값과 글 작성자를 비교하여 확인하여 삭제한다.
        if(BoardDeleteObject.getMember_idx() == memberSession.getMember_idx()){
            boardMapper.boardDelete(board_idx);
        }

	};

	// 글 수정 ( 게시글 내용, 게시글 작성자 - 세션에서 받아옴 )
	public void boardUpdate(Board Board, MemberSession memberSession){

	    // 글 수정시 작성자와 일치하는지 확인
        int Board_Number = Board.getBoard_idx(); // 글 수정 작성자가 글 수정을 하는지 확인을 위해 Idx를 가져온다.
        Board BoardUpdateObject = boardMapper.boardDetailSelection(Board_Number);

        // 세션값과 글 작성자를 비교하여 확인하여 수정한다.
        if(BoardUpdateObject.getMember_idx() == memberSession.getMember_idx()){
            boardMapper.boardUpdate(Board);
        }

	};

    // 글 카운트 조회  ( 글 총 갯수, 이전페이지, 다음 페이지 사용 )
    public int boardListCount(){

        int page_count = boardMapper.boardListCount(MainController.GlobalmemberSession.getMember_idx()); // 카운트 계산
        page_count = page_count / pageCount; // 총 페이지 계산
        return page_count;

    };

	// 글 세부 내용 조회 ( 글 한개 수정, 삭제 등의 작업)
    public Board boardDetailSelection(int board_idx){

        // 글 조회해서 글 한개 가져오기
        Board BoardOneObject = boardMapper.boardDetailSelection(board_idx);
        return BoardOneObject;

    };

    // 글 리스트 조회 함수
    // 페이징 방식을 이용하여 글 10개를 가져온다.
    public List<Board> boardListSelection(int page){

        // 이전 페이지 , 다음 페이지
        int pre_page   = page *  pageCount;
        int next_page  = (page+1) * pageCount - 1;
        Paging paging = new Paging();
        paging.setPrePage(pre_page);
        paging.setNextPage(next_page);
        paging.setMember_idx(MainController.GlobalmemberSession.getMember_idx());
        // 글 10개 단위의 글 객체를 가져옴
        List<Board> BoardGetListObject = boardMapper.boardListSelection(paging);
        return BoardGetListObject;

    };

    // 댓글 삽입
    public void commentInsert(Comment Comment, MemberSession memberSession ){
        Comment.setMember_idx(memberSession.getMember_idx()); // 멤버 ID
        Comment.setComment_id(memberSession.getId()); // 신고자 ID
        boardMapper.commentInsert(Comment);
    };

    // 댓글 확인
    public List<Comment> commentListSelection(int board_idx){
       List<Comment> CommentListObject = boardMapper.commentListSelection(board_idx);
       return CommentListObject;
    };

    // 댓글 삭제
    public void commentDelete(int comment_idx, MemberSession memberSession){
        // 댓글 작성자와 삭제 누른 사람이 일치하는지 확인
        Comment commentDelete  = boardMapper.commentDetailSelect(comment_idx);
        if(commentDelete.getMember_idx() == memberSession.getMember_idx()){
            boardMapper.commentDelete(comment_idx);
        }
    }
    public Comment commentDetailSelect(int comment_idx){
        Comment commentDetailObject = boardMapper.commentDetailSelect(comment_idx);
        return commentDetailObject;
    }
}
