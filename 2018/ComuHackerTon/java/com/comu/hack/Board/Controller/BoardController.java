package com.comu.hack.Board.Controller;


import com.comu.hack.Board.Service.BoardService;
import com.comu.hack.Main.Controller.MainController;
import com.comu.hack.VO.Board;
import com.comu.hack.VO.Comment;
import com.comu.hack.VO.Notice;
import com.comu.hack.VO.Report;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;
import java.util.Locale;

import static com.nhncorp.lucy.security.xss.XssPreventer.*;

/*
 * 제목 : BoardController
 * 날짜 : 2018.10.05
 * 내용 : 자유게시판 글 쓰기, 수정, 리스트, 페이지, 삭제 등 Service Implement ( 구현체 )
 * */

/**
 * Handles requests for the application home page.
 */
@Controller
public class BoardController {

    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

    @Resource(name="com.comu.hack.Home.Service.BoardServiceImpl")
    private BoardService boardServiceimpl;

    // 글쓰기 페이지로 이동
    @RequestMapping(value = "Board/board.com", method = { RequestMethod.GET , RequestMethod.POST })
    public String boardPage(Locale locale, Model model) {
        logger.info("Go To the Board >> .", locale);
        return "Board/write"; // 글쓰기 페이지로 이동
    }

    // 글 삽입 페이지 ( 작성 후 )
    @RequestMapping(value = "Board/board_write.com", method = { RequestMethod.GET , RequestMethod.POST })
    public String boardWrite(Locale locale, Model model, @Param("writeForm") Board Board) {
        logger.info("Board Write Locate >> {}.", locale);
        // 복호화 임시 주석
        // 글 작성시에 제목 및 내용 XSS 예방, SQL Injection은 추후 예방
        boardServiceimpl.boardInsert(Board,MainController.GlobalmemberSession);
        model.addAttribute("adminList",MainController.GlobalmemberSession.getStatus()); // 기관 관리자
        return "redirect:/Main/Main.com"; // 글 작성 후 Main으로 이동
    }

    // 글 수정 내용 불러오기  페이지
    @RequestMapping(value = "Board/board_select_update.com", method = { RequestMethod.GET , RequestMethod.POST })
    public String boardSelectUpdate(Locale locale, Model model, @RequestParam("board_idx") String Board) {
        logger.info("Board Update Select Locate >> {}.", locale);
        Board Board_Update = boardServiceimpl.boardDetailSelection(Integer.parseInt(Board)); // 글 수정을 위해 내용을 불러온다.
        // 글 작성자인지 확인하고 아니면 그냥 그 글 그 상태로 유지
        if(Board_Update.getMember_idx() == MainController.GlobalmemberSession.getMember_idx()){
            model.addAttribute("BoardUpdate",Board_Update); // 자유게시판 출력
            model.addAttribute("adminList",MainController.GlobalmemberSession.getStatus()); // 기관 관리자
            return "Board/update"; // 글 작성 페이지로 이동
        }else{ // 해당 글 작성자가 아닌 경우
            return "redirect:/Board/select.com?board_idx=" + Integer.parseInt(Board); // 해당 글로 새로고침
        }
    }


    // 글 수정 페이지
    @RequestMapping(value = "Board/board_update.com", method = { RequestMethod.GET , RequestMethod.POST })
    public String boardUpdate(Locale locale, Model model, @Param("board") Board Board) {
        logger.info("Board Update Locate >> {}.", locale);
        Board BoardCollectObject = boardServiceimpl.boardDetailSelection(Board.getBoard_idx());
        // 글 수정하는 사람과 수정 당사자가 일치했을때 수정
        if(BoardCollectObject.getMember_idx() == MainController.GlobalmemberSession.getMember_idx()) {
            boardServiceimpl.boardUpdate(Board, MainController.GlobalmemberSession);
        }
        model.addAttribute("adminList",MainController.GlobalmemberSession.getStatus()); // 기관 관리자
        return "redirect:/Board/select.com?board_idx=" + Board.getBoard_idx();  // 글 수정 후 해당 글로 이동
    }

    // 글 삭제 페이지
    @RequestMapping(value = "Board/board_delete.com", method = { RequestMethod.GET , RequestMethod.POST })
    public String boardDelete(Locale locale, Model model, @RequestParam("board_idx") String Board_idx) {
        // 삭제 작성자와 삭제가 일치했을 떄만board_list_select 삭제
        Board BoardObject = boardServiceimpl.boardDetailSelection(Integer.parseInt(Board_idx));
        if(BoardObject.getMember_idx() == MainController.GlobalmemberSession.getMember_idx()) {
            boardServiceimpl.boardDelete(Integer.parseInt(Board_idx), MainController.GlobalmemberSession);
            model.addAttribute("adminList",MainController.GlobalmemberSession.getStatus()); // 기관 관리자
            logger.info("Board Delete Locate >> {}.", locale);
            return "redirect:/Main/Main.com"; // 글 작성 후 Main으로 이동
        } else{
            return "redirect:/Board/select.com?board_idx=" + Integer.parseInt(Board_idx); // 해당 글로 새로고침
        }
    }


    // 글 리스트 페이지
    @RequestMapping(value = "Board/board_list_select.com", method = { RequestMethod.GET , RequestMethod.POST })
    public String boardListSelect(Locale locale, Model model, @Param("page")String page) {

        List<Board> list_Board; // 게시글 내용

        int paging_test = boardServiceimpl.boardListCount(); // 글 총 갯수
        int pagecount; // 페이징이 Null일 경우

        // 페이징 카운트 계산
        if(page == null){ // 첫 페이지 실행
            pagecount = 0;
            list_Board = boardServiceimpl.boardListSelection(pagecount);
            model.addAttribute("BoardList", list_Board);
            model.addAttribute("page",pagecount);
            model.addAttribute("adminList",MainController.GlobalmemberSession.getStatus()); // 기관 관리자

        }
        // 페이징이 전체 카운트 보다 클 경우
        else{
            pagecount = Integer.parseInt(page);
            if (pagecount < 0 ) { // 페이징 0보다 아래일 경우
                pagecount = 0;
                list_Board = boardServiceimpl.boardListSelection(pagecount);
                model.addAttribute("BoardList", list_Board);
                model.addAttribute("page",pagecount);
                model.addAttribute("adminList",MainController.GlobalmemberSession.getStatus()); // 기관 관리자
                return "Board/list"; // 글 작성 후 Main으로 이동
            } // 다음 페이지를 눌렀을 때 총 글 갯수보다 많을떄
            else if( paging_test < pagecount ){
                pagecount = paging_test;
                list_Board = boardServiceimpl.boardListSelection(pagecount);
                model.addAttribute("BoardList", list_Board);
                model.addAttribute("page",pagecount);
                model.addAttribute("adminList",MainController.GlobalmemberSession.getStatus()); // 기관 관리자
            }
            list_Board = boardServiceimpl.boardListSelection(pagecount);
            model.addAttribute("BoardList", list_Board);
            model.addAttribute("page",pagecount);
            model.addAttribute("adminList",MainController.GlobalmemberSession.getStatus()); // 기관 관리자
        }
        return "Board/list"; // 글 작성 후 Main으로 이동
    }

    // 글 조회 페이지
    @RequestMapping(value = "Board/select.com", method = { RequestMethod.GET , RequestMethod.POST })
    public String boardSelect(Locale locale, Model model, @Param("board_idx")int board_idx) {
        Board oneselectBoard = boardServiceimpl.boardDetailSelection(board_idx);
        List<Comment> CommentList = boardServiceimpl.commentListSelection(board_idx);
        // 글 , 댓글 출력
        model.addAttribute("adminList",MainController.GlobalmemberSession.getStatus()); // 기관 관리자
        model.addAttribute("BoardOne", oneselectBoard);
        model.addAttribute("CommentList", CommentList);
        return "Board/select"; // 글 작성 후 Main으로 이동
    }

    // 댓글 작성 페이지
    @RequestMapping(value = "Board/commentCreate.com", method = { RequestMethod.GET , RequestMethod.POST })
    public String boardCommentCreate(Locale locale, Model model, @Param("Comment")Comment Comment) {
        boardServiceimpl.commentInsert(Comment,MainController.GlobalmemberSession);
        return "redirect:/Board/select.com?board_idx=" + Comment.getBoard_idx(); // 댓글 작성 후 페이지로 이동
    }

    // 댓글 삭제 페이지
    @RequestMapping(value = "Board/commentDelete.com", method = { RequestMethod.GET , RequestMethod.POST })
    public String boardCommentDelete(Locale locale, Model model,  @Param("board_idx")int board_idx,  @Param("comment_idx")int comment_idx) {
        boardServiceimpl.commentDelete(comment_idx,MainController.GlobalmemberSession);
        return "redirect:/Board/select.com?board_idx=" + board_idx; // 댓글 작성 후 페이지로 이동
    }

}
