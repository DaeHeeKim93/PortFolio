package com.project.dbboard.Service.interf;

import com.project.dbboard.VO.BoardVO;
import com.project.dbboard.VO.CommentVO;
import com.project.dbboard.VO.ReplyVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BoardService {
    public List<BoardVO> insertSelectBoard(int page);
    public int NumberSelectBoard();
    public void insertboardVO(BoardVO boardVO);
    public BoardVO selectboardVO(int idx);
    public void deleteboardVO(int idx);
    public void updateBoardCountVO(@Param("board_count") int board_count, @Param("idx") int idx);
    public void updateBoardDataVO(@Param("title") String title, @Param("content") String content, @Param("idx") int idx);
    //  댓글
    public void insertcommentVO(CommentVO commentVO);
    public List<CommentVO> selectcommentVO(int board_idx);
    public void deletecommentVO(@Param("comment_idx") int comment_idx, @Param("comment_id") String comment_id);
    public void deleteallcommentVO(int board_idx);


// 답글
    public void deletereplycommentVO(@Param("re_idx")int re_idx,@Param("re_com_id")String re_com_id);
    public void replyinsertcommentVO(ReplyVO replyVO);
    public List<ReplyVO> replyselectcommentVO(int re_board_idx);
    public void deletereplyallcommentVO(int re_board_idx);
}
