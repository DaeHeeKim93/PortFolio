package com.project.dbboard.Controller;


import com.nhncorp.lucy.security.xss.XssPreventer;
import com.project.dbboard.Service.interf.BoardService;
import com.project.dbboard.VO.BoardVO;
import com.project.dbboard.VO.CommentVO;
import com.project.dbboard.VO.ReplyVO;
import org.jcp.xml.dsig.internal.dom.XMLDSigRI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
/*
* 게시물에 관련된 Controller
* */

@Controller
public class BoardController {

    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

    private static int page = 0;

    private static String path = "/webpp/resources/resource";

    @Resource(name = "com.project.dbboard.Service.impl.BoardServiceImpl")
    private BoardService boardServiceimpl;

    //파일 업로드 처리
    public MultipartResolver multipartResolver;

    private void initMultipartResolver(ApplicationContext context) {
        try {
            this.multipartResolver = ((MultipartResolver) context.getBean("multipartResolver", MultipartResolver.class));
            if (this.logger.isDebugEnabled()) {
                this.logger.debug("Using MultipartResolver [" + this.multipartResolver + "]");
            }
        } catch (NoSuchBeanDefinitionException ex) {
            this.multipartResolver = null;
            if (this.logger.isDebugEnabled())
                this.logger.debug("Unable to locate MultipartResolver with name 'multipartResolver': no multipart request handling provided");
        }
    }

    //글쓰기 리스트 화면을 보여줍니다.{
    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public String list(Locale locale, Model model, HttpSession session) {
        String Security = session.getAttribute("Security").toString();
        try {
            if (Security.equals("Login")) {
                try {
                    page = 0;
                    List<BoardVO> boardVO = boardServiceimpl.insertSelectBoard(page);
                    int PageLength = boardServiceimpl.NumberSelectBoard();
                    String ID = session.getAttribute("ID").toString();
                    String Nickname = session.getAttribute("Nickname").toString();
                    model.addAttribute("ID", ID);
                    model.addAttribute("Nickname", Nickname);
                    model.addAttribute("Page", page);
                    model.addAttribute("PageLength", PageLength);
                    model.addAttribute("boardinformation", boardVO);
                    logger.info("리스트 화면을 보여줍니다!.", locale);
                    return "list";
                } catch (NullPointerException e) {
                    logger.info("아무것도 없습니다.", locale);
                    return "home";
                }
            } else {
                logger.info("로그인한 적이 없습니다.", locale);
                return "home";
            }
        }
        catch (NullPointerException e) {
                logger.info("아무것도 없습니다.", locale);
                return "home";
        }
    }

    //다음 페이지 이동
    @RequestMapping(value = "/next", method = RequestMethod.POST)
    public String nextpage(Locale locale, Model model, @RequestParam int page, HttpSession session) {
        this.page = page;
        String ID = session.getAttribute("ID").toString();
        List<BoardVO> boardVO = boardServiceimpl.insertSelectBoard(this.page);
        model.addAttribute("ID", ID);
        int PageLength = boardServiceimpl.NumberSelectBoard();
        String Nickname = session.getAttribute("Nickname").toString();
        model.addAttribute("Nickname", Nickname);
        model.addAttribute("Page", this.page);
        model.addAttribute("PageLength", PageLength);
        model.addAttribute("boardinformation", boardVO);
        return "ajaxlist";
    }

    //이전 페이지 이동
    @RequestMapping(value = "/pre", method = RequestMethod.POST)
    public String prePage(Locale locale, Model model, @RequestParam int page, HttpSession httpSession) {
        this.page = page;
        int PageLength = boardServiceimpl.NumberSelectBoard();
        String ID = httpSession.getAttribute("ID").toString();
        model.addAttribute("ID", ID);
        List<BoardVO> boardVO = boardServiceimpl.insertSelectBoard(this.page);
        String Nickname = httpSession.getAttribute("Nickname").toString();
        model.addAttribute("Nickname", Nickname);
        model.addAttribute("Page", this.page);
        model.addAttribute("PageLength", PageLength);
        model.addAttribute("boardinformation", boardVO);
        return "ajaxlist";
    }

    // 처음 페이지 이동
    @RequestMapping(value = "/first", method = RequestMethod.POST)
    public String firstpage(Locale locale, Model model, @RequestParam int page, HttpSession httpSession) {
        int PageLength = boardServiceimpl.NumberSelectBoard();
        String ID = httpSession.getAttribute("ID").toString();
        model.addAttribute("ID", ID);
        this.page = 0;
        List<BoardVO> boardVO = boardServiceimpl.insertSelectBoard(this.page);
        String Nickname = httpSession.getAttribute("Nickname").toString();
        model.addAttribute("Nickname", Nickname);
        model.addAttribute("Page", this.page);
        model.addAttribute("PageLength", PageLength);
        model.addAttribute("boardinformation", boardVO);
        return "ajaxlist";
    }

    //마지막 페이지 이동
    @RequestMapping(value = "/last", method = RequestMethod.POST)
    public String lastpage(Locale locale, Model model, @RequestParam int page, HttpSession httpSession) {
        int PageLength = boardServiceimpl.NumberSelectBoard();
        String ID = httpSession.getAttribute("ID").toString();
        model.addAttribute("ID", ID);
        this.page = page;
        List<BoardVO> boardVO = boardServiceimpl.insertSelectBoard(this.page);
        String Nickname = httpSession.getAttribute("Nickname").toString();
        model.addAttribute("Nickname", Nickname);
        model.addAttribute("Page", this.page);
        model.addAttribute("PageLength", PageLength);
        model.addAttribute("boardinformation", boardVO);
        return "ajaxlist";
    }

    //글 작성 페이지 이동
    @RequestMapping(value = "/write", method = RequestMethod.GET)
    public String write(Locale locale, Model model, HttpSession httpSession) {

        String Security = httpSession.getAttribute("Security").toString();
        try {
            if (Security.equals("Login")) {
                int PageLength = boardServiceimpl.NumberSelectBoard();
                this.page = 0;
                logger.info("글 작성을 합니다 !.", locale);
                String Nickname = httpSession.getAttribute("Nickname").toString();
                String ID = httpSession.getAttribute("ID").toString();
                model.addAttribute("Nickname", Nickname);
                model.addAttribute("ID", ID);
                return "board";
            } else {
                logger.info("로그인한 적이 없습니다.", locale);
                return "home";
            }
        }
        catch (NullPointerException e) {
            logger.info("아무것도 없습니다.", locale);
            return "home";
        }
    }

    // 글 수정 페이지
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Locale locale, Model model, HttpSession httpSession, @RequestParam String board_collect) {
        String Security = httpSession.getAttribute("Security").toString();
        try {
            if (Security.equals("Login")) {
                    int comment_board_number_idx = Integer.parseInt(board_collect);
                    String ID = httpSession.getAttribute("ID").toString();
                    String board_id = boardServiceimpl.selectboardVO(comment_board_number_idx).getBoard_id();
                    if (ID.equals(board_id)) {
                        String Content = boardServiceimpl.selectboardVO(comment_board_number_idx).getContent();
                        String title = boardServiceimpl.selectboardVO(comment_board_number_idx).getTitle();
                        model.addAttribute("idx", comment_board_number_idx);
                        model.addAttribute("Content", Content);
                        model.addAttribute("title", title);
                        return "update";
                    } else {
                        int PageLength = boardServiceimpl.NumberSelectBoard();
                        page = 0;
                        List<BoardVO> board = boardServiceimpl.insertSelectBoard(page);
                        String Nickname = boardServiceimpl.selectboardVO(comment_board_number_idx).getBoard_nickname();
                        model.addAttribute("Nickname", Nickname);
                        model.addAttribute("Page", this.page);
                        model.addAttribute("PageLength", PageLength);
                        model.addAttribute("boardinformation", board);
                        return "list";
                    }
            } else {
                logger.info("로그인한 적이 없습니다.", locale);
                return "home";
            }
        }
        catch (NullPointerException e) {
            logger.info("아무것도 없습니다.", locale);
            return "home";
        }
    }

    // 글 수정을 Update하는 페이지
    @RequestMapping(value = "/updateboard", method = RequestMethod.POST)
    public String update_submit(Locale locale, Model model, BoardVO boardVO, HttpSession httpSession,String idx) {
        String Security = httpSession.getAttribute("Security").toString();
        try {
            if (Security.equals("Login")) {
                String Title = boardVO.getTitle();
                String Content = boardVO.getContent();
                int update_Number = Integer.parseInt(idx);
                boardServiceimpl.updateBoardDataVO(Title, Content, update_Number);
                page = 0;
                int PageLength = boardServiceimpl.NumberSelectBoard();
                List<BoardVO> listBoard = boardServiceimpl.insertSelectBoard(page);
                String Nickname = httpSession.getAttribute("Nickname").toString();
                model.addAttribute("Nickname", Nickname);
                model.addAttribute("Page", this.page);
                model.addAttribute("PageLength", PageLength);
                model.addAttribute("boardinformation", listBoard);
                return "list";
            }
            else {
                        logger.info("로그인한 적이 없습니다.", locale);
                        return "home";
                 }
         }
        catch (NullPointerException e) {
                logger.info("아무것도 없습니다.", locale);
                return "home";
       }
    }


    // 글 삭제 페이지
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(Locale locale, Model model, HttpSession httpSession, @RequestParam String board_number) {
        String Security = httpSession.getAttribute("Security").toString();
        try {

            if (Security.equals("Login")) {
                int board_idx = Integer.parseInt(board_number);
                boardServiceimpl.deleteallcommentVO(board_idx);
                boardServiceimpl.deleteboardVO(board_idx);
                boardServiceimpl.deletereplyallcommentVO(board_idx);
                int PageLength = boardServiceimpl.NumberSelectBoard();
                page = 0;
                List<BoardVO> board = boardServiceimpl.insertSelectBoard(page);
                String ID = httpSession.getAttribute("ID").toString();
                String Nickname = httpSession.getAttribute("Nickname").toString();
                model.addAttribute("ID", ID);
                model.addAttribute("Nickname", Nickname);
                model.addAttribute("Page", page);
                model.addAttribute("PageLength", PageLength);
                model.addAttribute("boardinformation", board);
                return "list";
            }
            else {
                logger.info("로그인한 적이 없습니다.", locale);
                return "home";
            }
        }
        catch (NullPointerException e) {
            logger.info("아무것도 없습니다.", locale);
            return "home";
        }
    }

    // 글 작성 후 조회로 업데이트 하는 페이지
    @RequestMapping(value = "/submitboard", method = RequestMethod.POST)
    public String submit_list(Locale locale, Model model, HttpSession httpSession, BoardVO boardVO) {
        String Security = httpSession.getAttribute("Security").toString();
        try {

            if (Security.equals("Login")) {
                    int PageLength = boardServiceimpl.NumberSelectBoard();
                    this.page = 0;

                    // 글 제목 및 내용 처리
                    String title  =  XssPreventer.escape(boardVO.getTitle());
                    boardVO.setTitle(title);
                    //날짜는 Java에서 처리
                    long time = System.currentTimeMillis();
                    SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd");
                    String regedate = dayTime.format(new Date(time));
                    boardVO.setRedegate(regedate);
                    boardServiceimpl.insertboardVO(boardVO);
                    logger.info("글 작성이 완료되었습니다 !", locale);

                    page = 0;
                    List<BoardVO> board = boardServiceimpl.insertSelectBoard(page);
                    String ID = httpSession.getAttribute("ID").toString();
                    String Nickname = httpSession.getAttribute("Nickname").toString();
                    model.addAttribute("ID", ID);
                    model.addAttribute("Nickname", Nickname);
                    model.addAttribute("Page", page);
                    model.addAttribute("PageLength", PageLength);
                    model.addAttribute("boardinformation", board);
                    return "list";
            }
            else {
                logger.info("로그인한 적이 없습니다.", locale);
                return "home";
            }
        }
        catch (NullPointerException e) {
                logger.info("아무것도 없습니다.", locale);
                return "home";
        }
    }

    // 글 조회창을 띄우는 페이지
    @RequestMapping(value = "/select", method = RequestMethod.GET)
    public String select(Locale locale, Model model, @RequestParam String idx) {
        this.page = 0;
        int idx_int = Integer.parseInt(idx);
        BoardVO boardVO = boardServiceimpl.selectboardVO(idx_int);
        int count = boardVO.getBoard_count();
        count +=1;
        boardVO.setBoard_count(count);
        boardServiceimpl.updateBoardCountVO(count,idx_int);
        List<CommentVO> commentVO = boardServiceimpl.selectcommentVO(idx_int);
        List<ReplyVO> replyVO = boardServiceimpl.replyselectcommentVO(idx_int);
        model.addAttribute("BoardVO", boardVO);
        model.addAttribute("commentinformation", commentVO);
        model.addAttribute("replyinformation",replyVO);
        logger.info("글을 조회하고 있습니다.", locale);
        return "select";
    }

    // 홈페이지로 돌아오기 ( 로그인 상태 )

    @RequestMapping(value = "/homelogin", method = RequestMethod.GET)
    public String home_login(Locale locale, Model model) {
        return "home_login";
    }

    //댓글 삽입 페이지
    @RequestMapping(value = "/commentinsert", method = RequestMethod.POST)
    public String comment_insert(Locale locale, Model model, HttpSession httpSession, CommentVO commentVO, int board_idx) {
        //날짜는 Java에서 처리
        long time = System.currentTimeMillis();
        SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String regedate = dayTime.format(new Date(time));
        commentVO.setComment_redegate(regedate);
        String ID = httpSession.getAttribute("ID").toString();
        String Nickname = httpSession.getAttribute("Nickname").toString();
        commentVO.setComment_id(ID);
        commentVO.setComment_nickname(Nickname);
        boardServiceimpl.insertcommentVO(commentVO);
        List<CommentVO> comment = boardServiceimpl.selectcommentVO(Integer.parseInt(commentVO.getBoard_idx()));
        List<ReplyVO> replyVO = boardServiceimpl.replyselectcommentVO(Integer.parseInt(commentVO.getBoard_idx()));
        model.addAttribute("board_idx", board_idx);
        model.addAttribute("commentinformation", comment);
        model.addAttribute("replyinformation",replyVO);
        logger.info(" 댓글을 삽입합니다. ", locale);
        return "comment";

    }

    // 댓글 삭제 페이지
    @RequestMapping(value = "/commentdelete", method = RequestMethod.POST)
    public String comment_delete( HttpSession httpSession, Locale locale, Model model,int comment_idx,int board_idx) {
        String ID = httpSession.getAttribute("ID").toString();
        boardServiceimpl.deletecommentVO(comment_idx,ID);
        List<CommentVO> comment = boardServiceimpl.selectcommentVO(board_idx);
        List<ReplyVO> replyVO = boardServiceimpl.replyselectcommentVO(board_idx);
        model.addAttribute("board_idx", board_idx);
        model.addAttribute("commentinformation", comment);
        model.addAttribute("replyinformation",replyVO);
        return "comment";
    }


    // 답글 삽입 페이지
    @RequestMapping(value = "/replyinsert", method = RequestMethod.POST)
    public String reply_insert(Locale locale, Model model, HttpSession httpSession,ReplyVO replyVO) {
        replyVO.setRe_com_nick(httpSession.getAttribute("Nickname").toString());
        replyVO.setRe_com_id(httpSession.getAttribute("ID").toString());

        long time = System.currentTimeMillis();
        SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String regedate = dayTime.format(new Date(time));
        replyVO.setRe_com_regedate(regedate);
        boardServiceimpl.replyinsertcommentVO(replyVO);
        String ID = httpSession.getAttribute("ID").toString();
        String Nickname = httpSession.getAttribute("Nickname").toString();
        replyVO.setRe_com_id(ID);
        replyVO.setRe_com_nick(Nickname);
        //다시 답글 출력
        List<CommentVO> comment = boardServiceimpl.selectcommentVO(Integer.parseInt(replyVO.getRe_board_idx()));
        List<ReplyVO> reply = boardServiceimpl.replyselectcommentVO(Integer.parseInt(replyVO.getRe_board_idx()));
        model.addAttribute("board_idx", replyVO.getRe_board_idx());
        model.addAttribute("commentinformation", comment);
        model.addAttribute("replyinformation",reply);
        return "comment";
    }

    // 답글 삭제 페이지
    @RequestMapping(value = "/replydelete", method = RequestMethod.POST)
    public String reply_delete(Locale locale, Model model,HttpSession httpSession,int re_idx,int comment_idx,int re_board_idx) {
        String ID = httpSession.getAttribute("ID").toString();
        boardServiceimpl.deletereplycommentVO(re_idx,ID);
        List<CommentVO> comment = boardServiceimpl.selectcommentVO(re_board_idx);
        List<ReplyVO> replyVO = boardServiceimpl.replyselectcommentVO(re_board_idx);
        model.addAttribute("board_idx", re_board_idx);
        model.addAttribute("commentinformation", comment);
        model.addAttribute("replyinformation",replyVO);
        return "comment";
    }




}
