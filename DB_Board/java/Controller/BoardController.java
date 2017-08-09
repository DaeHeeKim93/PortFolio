package com.project.dbboard.Controller;


import com.project.dbboard.Service.interf.BoardService;
import com.project.dbboard.VO.BoardVO;
import com.project.dbboard.VO.CommentVO;
import com.project.dbboard.VO.ReplyVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
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

    //글쓰기 리스트 화면을 보여줍니다.
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Locale locale, Model model, HttpSession session) {
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
        int PageLength = boardServiceimpl.NumberSelectBoard();
        this.page = 0;
        logger.info("글 작성을 합니다 !.", locale);
        String Nickname = httpSession.getAttribute("Nickname").toString();
        String ID = httpSession.getAttribute("ID").toString();
        model.addAttribute("Nickname", Nickname);
        model.addAttribute("ID", ID);
        return "board";
    }

    // 글 수정 페이지
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Locale locale, Model model, HttpSession httpSession, @RequestParam String board_collect) {
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
    }

    // 글 수정을 Update하는 페이지
    @RequestMapping(value = "/updateboard", method = RequestMethod.POST)
    public String update_submit(Locale locale, Model model, BoardVO boardVO, HttpSession httpSession,String idx) {
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


    // 글 삭제 페이지
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(Locale locale, Model model, HttpSession httpSession, @RequestParam String board_number) {
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

    // 글 작성 후 조회로 업데이트 하는 페이지
    @RequestMapping(value = "/submitboard", method = RequestMethod.POST)
    public String submit_list(Locale locale, Model model, HttpSession httpSession, BoardVO boardVO) {
        int PageLength = boardServiceimpl.NumberSelectBoard();
        this.page = 0;

        //날짜는 Java에서 처리
        String regedate = new String();
        Calendar c = Calendar.getInstance();
        String YEAR = Integer.toString(c.get(Calendar.YEAR));
        int Test = c.get(Calendar.MONTH) + 1;
        String Month = Integer.toString(Test);
        Test = c.get(Calendar.DATE);
        String Date = Integer.toString(Test);
        if (Integer.parseInt(Month) < 10) {
            int temp = c.get(Calendar.MONTH) + 1;
            Month = "-0" + Integer.toString(temp);
        } else {
            int temp = c.get(Calendar.MONTH) + 1;
            Month = "-" + Integer.toString(temp);
        }
        if (Integer.parseInt(Date) < 10) {
            int temp = c.get(Calendar.DATE);
            Date = "-0" + Integer.toString(temp);
        } else {
            int temp = c.get(Calendar.DATE);
            Date = "-" + Integer.toString(temp);
        }

        regedate += YEAR + Month + Date;
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

    // 이미지 팝업창을 띄우는 페이지
    @RequestMapping(value = "/picture", method = RequestMethod.GET)
    public String picture(Locale locale, Model model) {
        int PageLength = boardServiceimpl.NumberSelectBoard();
        this.page = 0;
        logger.info("이미지 업로드 중입니다", locale);
        return "picture";
    }

    // 동영상 팝업창을 띄우는 페이지
    @RequestMapping(value = "/video", method = RequestMethod.GET)
    public void movie(Locale locale, Model model, HttpSession httpSession) {
        logger.info(" 동영상 업데이트 확인하다. ", locale);
    }

    // 동영상 업로드 및 파일 표시 처리 ( web url 경로로 치환할지  , 더 좋은 방법이 있을지 생각할것 ) - 미정 .
    @RequestMapping(value = "/uploaded", method = RequestMethod.POST)
    public String movieupload(Locale locale, Model model, HttpSession httpSession, @RequestParam("find_button") MultipartFile file, HttpServletRequest request) throws IOException {
        String name = file.getOriginalFilename();
        String realpath = "";

        UUID randomuuid = UUID.randomUUID();
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                // Creating the directory to store file
                String rootPath = request.getSession().getServletContext().getRealPath("/");
                File dir = new File(rootPath + File.separator + "upload");
                if (!dir.exists())
                    dir.mkdirs();
                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath() + File.separator + randomuuid + "_" + name);
                realpath = dir.getAbsolutePath() + File.separator + name;
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);

                stream.close();

            } catch (Exception e) {
            }
        } else {

        }

        logger.info(" 동영상 업데이트 확인하다. ", locale);
        return "video";
    }


    //댓글 삽입 페이지
    @RequestMapping(value = "/commentinsert", method = RequestMethod.POST)
    public String comment_insert(Locale locale, Model model, HttpSession httpSession, CommentVO commentVO, int board_idx) {
        //날짜는 Java에서 처리
        String regedate = new String();
        Calendar c = Calendar.getInstance();
        String YEAR = Integer.toString(c.get(Calendar.YEAR));
        int Test = c.get(Calendar.MONTH) + 1;
        String Month = Integer.toString(Test);
        Test = c.get(Calendar.DATE);
        String Date = Integer.toString(Test);
        if (Integer.parseInt(Month) < 10) {
            int temp = c.get(Calendar.MONTH) + 1;
            Month = "-0" + Integer.toString(temp);
        } else {
            int temp = c.get(Calendar.MONTH) + 1;
            Month = "-" + Integer.toString(temp);
        }
        if (Integer.parseInt(Date) < 10) {
            int temp = c.get(Calendar.DATE);
            Date = "-0" + Integer.toString(temp);
        } else {
            int temp = c.get(Calendar.DATE);
            Date = "-" + Integer.toString(temp);
        }
        regedate += YEAR + Month + Date;
        commentVO.setComment_redegate(regedate);
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
    public String comment_delete(Locale locale, Model model,int comment_idx,int board_idx) {
        boardServiceimpl.deletecommentVO(comment_idx);
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


        String regedate = new String();
        Calendar c = Calendar.getInstance();
        String YEAR = Integer.toString(c.get(Calendar.YEAR));
        int Test = c.get(Calendar.MONTH) + 1;
        String Month = Integer.toString(Test);
        Test = c.get(Calendar.DATE);
        String Date = Integer.toString(Test);
        if (Integer.parseInt(Month) < 10) {
            int temp = c.get(Calendar.MONTH) + 1;
            Month = "-0" + Integer.toString(temp);
        } else {
            int temp = c.get(Calendar.MONTH) + 1;
            Month = "-" + Integer.toString(temp);
        }
        if (Integer.parseInt(Date) < 10) {
            int temp = c.get(Calendar.DATE);
            Date = "-0" + Integer.toString(temp);
        } else {
            int temp = c.get(Calendar.DATE);
            Date = "-" + Integer.toString(temp);
        }
        regedate += YEAR + Month + Date;
        replyVO.setRe_com_regedate(regedate);

        boardServiceimpl.replyinsertcommentVO(replyVO);
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
    public String reply_delete(Locale locale, Model model,int re_idx,int comment_idx,int re_board_idx) {
        boardServiceimpl.deletereplycommentVO(re_idx);
        List<CommentVO> comment = boardServiceimpl.selectcommentVO(re_board_idx);
        List<ReplyVO> replyVO = boardServiceimpl.replyselectcommentVO(re_board_idx);
        model.addAttribute("board_idx", re_board_idx);
        model.addAttribute("commentinformation", comment);
        model.addAttribute("replyinformation",replyVO);
        return "comment";
    }
}
