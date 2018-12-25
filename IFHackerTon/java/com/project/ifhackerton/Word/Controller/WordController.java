package com.project.ifhackerton.Word.Controller;



import com.project.ifhackerton.Main.Controller.MainController;

import com.project.ifhackerton.VO.MemberLog;
import com.project.ifhackerton.VO.Word;
import com.project.ifhackerton.VO.Word_Search;
import com.project.ifhackerton.Word.Service.WordService;
import com.sun.deploy.net.HttpResponse;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;


@Controller
public class WordController {

    private static final Logger logger = LoggerFactory.getLogger(WordController.class);

    @Resource(name="com.project.ifhackerton.Word.Service.WordServiceImpl")
    private WordService wordServiceimpl;

    // 글쓰기 페이지로 이동
    @RequestMapping(value = "Word/Word.com", method = { RequestMethod.GET , RequestMethod.POST })
    public String wordPage(Locale locale, Model model) {
        logger.info("Go To the Word >> .", locale);
        return "Word/write"; // 글쓰기 페이지로 이동
    }

    // 글 삽입 페이지 ( 작성 후 )
    @RequestMapping(value = "Word/Word_write.com", method = { RequestMethod.GET , RequestMethod.POST })
    public String wordWrite(Locale locale, Model model, @Param("writeForm") Word word) {
        logger.info("Word Write Locate >> {}.", locale);
        // 복호화 임시 주석
        // 글 작성시에 제목 및 내용 XSS 예방, SQL Injection은 추후 예방
        String replace_North_Word = word.getNorth_word().replaceAll("<p>","");
        replace_North_Word = replace_North_Word.replaceAll("</p>","");

        String replace_South_Word = word.getSouth_word().replaceAll("<p>","");
        replace_South_Word = replace_South_Word.replaceAll("</p>","");

        word.setNorth_word(replace_North_Word);
        word.setSouth_word(replace_South_Word);

        wordServiceimpl.wordInsert(word,MainController.GlobalmemberSession);
        model.addAttribute("adminList",MainController.GlobalmemberSession.getStatus()); // 기관 관리자
        return "redirect:/Main/Main.com"; // 글 작성 후 Main으로 이동
    }


    // 글 검색 페이지 ( 작성 후 )
    @RequestMapping(value = "Word/word_list_select.com", method = { RequestMethod.GET , RequestMethod.POST })
    public String wordSelect(Locale locale, Model model) {
        logger.info("Word Select Locate >> {}.", locale);
        model.addAttribute("adminList",MainController.GlobalmemberSession.getStatus()); // 기관 관리자
        return "Word/select"; // 글쓰기 페이지로 이동
    }

    // 북한말 -> 남한말 검색
    @RequestMapping(value = "Word/north_select_com", method= RequestMethod.POST , produces = "application/text; charset=utf8")
    @ResponseBody
    public String north_select(Locale locale,@RequestParam("north_textarea") String north_textarea ){
        logger.info("Word North Locate >> {}.",locale);
        // 북한말 -> 남한말 문자로 출력
        if(north_textarea != "") {
            List<Word> SouthList = wordServiceimpl.northSearchWord(north_textarea);
            String SouthListWord = "";
            for (int i = 0; i < SouthList.size(); i++) {
                SouthListWord += SouthList.get(i).getSouth_word();
                if (i != SouthList.size() - 1)
                    SouthListWord += ",";
            }

            // 로그 ( 로그아웃 부분 담기 )
            HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
            String ip = req.getHeader("X-FORWARDED-FOR");
            if (ip == null)
                ip = req.getRemoteAddr();
            // 검색 시 검색 로그 담아놓기
            // 로그인 시
            try{
                for (int i = 0; i < SouthList.size(); i++) {
                    Word_Search word_search = new Word_Search();
                    word_search.setMember_idx(MainController.GlobalmemberSession.getMember_idx());
                    word_search.setWord_idx(SouthList.get(i).getWord_idx());
                    word_search.setSearch_ip(ip);
                    wordServiceimpl.SearchInsert(word_search);
                }

            }catch(NullPointerException e){ // 비로그인 시
                for (int i = 0; i < SouthList.size(); i++) {
                    Word_Search word_search = new Word_Search();
                    word_search.setMember_idx(0);
                    word_search.setWord_idx(SouthList.get(i).getWord_idx());
                    word_search.setSearch_ip(ip);
                    wordServiceimpl.SearchInsert(word_search);
                }
            }

            return SouthListWord;
        }else{
            return "";
        }

    }

    // 남한말 -> 북한말 검색
    @RequestMapping(value = "Word/south_select_com", method= RequestMethod.POST , produces = "application/text; charset=utf8")
    @ResponseBody
    public String south_select(Locale locale, @RequestParam("south_textarea") String south_textarea , HttpServletResponse response){
        logger.info("Word South Locate >> {}.",locale);
        // 남한말 -> 북한말 문자로 출력

        if(south_textarea != "") {
            List<Word> NorthList = wordServiceimpl.southSearchWord(south_textarea);
            String NorthListWord = "";
            for (int i = 0; i < NorthList.size(); i++) {
                NorthListWord += NorthList.get(i).getNorth_word();
                if (i != NorthList.size() - 1)
                    NorthListWord += ",";
            }

            // 로그 ( 로그아웃 부분 담기 )
            HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
            String ip = req.getHeader("X-FORWARDED-FOR");
            if (ip == null)
                ip = req.getRemoteAddr();
            // 검색 시 검색 로그 담아놓기
            // 로그인 시
            try{
                for (int i = 0; i < NorthList.size(); i++) {
                    Word_Search word_search = new Word_Search();
                    word_search.setMember_idx(MainController.GlobalmemberSession.getMember_idx());
                    word_search.setWord_idx(NorthList.get(i).getWord_idx());
                    word_search.setSearch_ip(ip);
                    wordServiceimpl.SearchInsert(word_search);
                }

            }catch(NullPointerException e){ // 비로그인 시
                for (int i = 0; i < NorthList.size(); i++) {
                    Word_Search word_search = new Word_Search();
                    word_search.setMember_idx(0);
                    word_search.setWord_idx(NorthList.get(i).getWord_idx());
                    word_search.setSearch_ip(ip);
                    wordServiceimpl.SearchInsert(word_search);
                }
            }

            return NorthListWord;
        }else{
            return "";
        }
    }
}
