package com.project.ifhackerton.Main.Controller;

import java.util.List;
import java.util.Locale;


import com.project.ifhackerton.Mapper.MemberMapper;
import com.project.ifhackerton.VO.Board;
import com.project.ifhackerton.VO.MemberSession;
import com.project.ifhackerton.VO.Notice;
import com.project.ifhackerton.VO.Word;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;



@Controller
public class MainController {

    // 세션 관리 전역 변수
    public static MemberSession GlobalmemberSession;

    // 로그 관리 변수
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    // SQL Mapper 연결 객체
    @Autowired
    private MemberMapper memberMapper;

    // 신고하기 Controller
    // 미로그인, 로그인에 따라 신고하기 기능이 달라진다.
    @RequestMapping(value = "Main/Report.com", method = { RequestMethod.GET , RequestMethod.POST })
    public String mainReport(HttpSession httpSession, Locale locale, Model model) {
        logger.info("Main/ Report.com Moving >> {}.", locale);
        // 로그인 여부 검사, 로그인 시 -> 메인 접속 / 아닐시 ->  로그인 창 연결
        try{
            // 세션 검사하여, 세션이 없거나, ID가 맞지 않으면 로그인 창으로 이동
            GlobalmemberSession = (MemberSession)httpSession.getAttribute("memberSession");
            if(GlobalmemberSession.getId() != null){
                 logger.info("Write Page Moving / 신고 기능 페이지 이동 >> {}.", locale);
                 return "redirect:/Report/report.com";  // 로그인 확인시 신고 기능
            }else{
                logger.info("Login Page Moving / 신고 기능 -> 로그인 창으로 이동 >> {}.", locale);
                return "Member/login"; // 로그인 창으로 연결
            }
        }
        catch(NullPointerException e){
              logger.info("Login Page Moving / 신고 기능 -> 로그인 창으로 이동 >> {}.", locale);
              return "Member/login"; // 로그인 창으로 연결
        }
    }

    // 회원가입 기능
    @RequestMapping(value = "Main/Sign.com", method = { RequestMethod.GET , RequestMethod.POST })
    public String mainMemberJoin(Locale locale, Model model) {
        logger.info("Welcome Member Sign / 회원가입 기능으로 이동 >> .", locale);
        return "Member/sign"; // 회원가입 쪽으로 연결
    }

    // 메인 페이지 연결 기능 -> 처음에 로그인 안되있을 것이므로 로그인으로 연결
    @RequestMapping(value = "Main/Main.com", method = { RequestMethod.GET , RequestMethod.POST })
    public String mainPage(HttpSession httpSession, Locale locale, Model model) {
        logger.info("Welcome Main Page / 메인페이지 이동 >>", locale);
        // 로그인 여부 검사, 로그인 시 -> 메인 접속 / 아닐시 -> 로그인 창 연결
        // 세션 검사하여, 세션이 없거나, ID가 맞지 않으면 로그인 창으로 이동
        try{
            GlobalmemberSession = (MemberSession)httpSession.getAttribute("memberSession");
            if(GlobalmemberSession.getId() != null){

                 // 공지사항 내역, 신고내역, 자유게시판 출력
                 List<Board> boardList = memberMapper.board_ListSelection(MainController.GlobalmemberSession.getMember_idx());
                 List<Notice> noticeList = memberMapper.Notice_ListSelection(MainController.GlobalmemberSession.getMember_idx());
                 List<Word> wordList = memberMapper.Word_ListSelection();
                 model.addAttribute("boardList",boardList); // 자유게시판 출력
                 model.addAttribute("noticeList",noticeList); // 공지사항 출력
                 model.addAttribute("wordList",wordList); // 단어 출력
                 model.addAttribute("adminList",MainController.GlobalmemberSession.getStatus()); // 기관 관리자
                 logger.info("Welcome Main Page / 메인페이지 이동 성공 >>", locale);
                return "Main/main"; // 메인으로 이동
            }else{
                logger.info("Login Page Moving / 메인페이지 -> 로그인 창으로 이동 >>", locale);
                return "Member/login"; // 로그인 창으로 연결
            }
        }catch(NullPointerException e){ // 아무도 접속하지 않아 로그가 없을 때
            logger.info("Login Page Moving / 메인페이지 -> 로그인 창으로 이동 >>", locale);
            return "Member/login"; // 로그인 창으로 연결
        }
    }


    // 글 검색 페이지 ( 작성 후 )
    @RequestMapping(value = "Main/word_list_select.com", method = { RequestMethod.GET , RequestMethod.POST })
    public String wordSelect(Locale locale, Model model) {
        logger.info("Main Word Select Locate >> {}.", locale);
        return "Main/select"; // 글쓰기 페이지로 이동
    }
}
