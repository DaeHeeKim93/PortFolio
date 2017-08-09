package com.project.dbboard.Service.impl;

import com.project.dbboard.Mapper.BoardMapper;
import com.project.dbboard.Service.interf.BoardService;
import com.project.dbboard.VO.BoardVO;
import com.project.dbboard.VO.CommentVO;
import com.project.dbboard.VO.ReplyVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("com.project.dbboard.Service.impl.BoardServiceImpl")
public class BoardServiceImpl implements BoardService{
    @Autowired
    private BoardMapper boardMapper;

    @Override
    public List<BoardVO> insertSelectBoard(int page){
        return boardMapper.selectinformation(page);
    }

    @Override
    public int NumberSelectBoard() { return boardMapper.numberboard();}

    @Override
    public void insertboardVO(BoardVO boardVO) {
        boardMapper.insertboard(boardVO);
    }

    @Override
    public BoardVO selectboardVO(int idx) {
        return boardMapper.selectlist(idx);
    }

    @Override
    public void deleteboardVO(int idx){ boardMapper.deleteboard(idx);}

    @Override
    public void updateBoardCountVO(@Param("board_count") int board_count, @Param("idx") int idx){ boardMapper.updateBoardCount(board_count,idx);};

    @Override
    public void updateBoardDataVO(@Param("title") String title, @Param("content") String content, @Param("idx") int idx){boardMapper.updateBoardData(title,content,idx);};

    @Override
    public void insertcommentVO(CommentVO commentVO){
        boardMapper.insertcomment(commentVO);
    }

    @Override
    public List<CommentVO> selectcommentVO(int board_idx){
        return boardMapper.selectcomment(board_idx);
    }

    @Override
    public void deletecommentVO(int comment_idx){ boardMapper.deletecomment(comment_idx); };

    @Override
    public void deleteallcommentVO(int board_idx) {
        boardMapper.deleteallcomment(board_idx);
    }


    @Override
    public void replyinsertcommentVO(ReplyVO replyVO){ boardMapper.replyinsertcomment(replyVO);}

    @Override
    public void deletereplycommentVO(int re_idx){ boardMapper.deletereplycomment(re_idx);};

    @Override
    public void deletereplyallcommentVO(int re_board_idx){boardMapper.deletereplyallcomment(re_board_idx);}

    @Override
    public List<ReplyVO> replyselectcommentVO(int re_board_idx){ return boardMapper.replyselectcomment(re_board_idx);}
}
