package com.project.dbboard.Mapper;

import com.project.dbboard.VO.BoardVO;
import com.project.dbboard.VO.CommentVO;
import com.project.dbboard.VO.ReplyVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface BoardMapper {
    public List<BoardVO>  selectinformation(int page);
    public int numberboard();
    public void insertboard(BoardVO boardVO);
    public void deleteboard(int idx);
    public BoardVO selectlist(int idx);
    public void updateBoardCount(@Param("board_count") int board_count, @Param("idx") int idx); // 글 조회수 증가 페이지
    public void updateBoardData(@Param("title") String title, @Param("content") String content, @Param("idx") int idx); // 글 수정 함수
    public void insertcomment(CommentVO commentVO);
    public void deletecomment(@Param("comment_idx") int comment_idx, @Param("comment_id") String comment_id);
    public void deleteallcomment(int board_idx);
    public List<CommentVO> selectcomment(int board_idx);
    public void replyinsertcomment(ReplyVO replyVO);
    public void deletereplycomment(@Param("re_idx")int re_idx,@Param("re_com_id")String re_com_id);
    public List<ReplyVO> replyselectcomment(int re_board_idx);
    public void deletereplyallcomment(int re_board_idx);
  }
