package com.project.ifhackerton.Notice.Controller;


import com.project.ifhackerton.Main.Controller.MainController;
import com.project.ifhackerton.Notice.Service.NoticeService;
import com.project.ifhackerton.VO.Notice;
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


/**
 * Handles requests for the application home page.
 */
@Controller
public class NoticeController {

    private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);

    @Resource(name="com.project.ifhackerton.Notice.Service.NoticeServiceImpl")
    private NoticeService noticeServiceimpl;

    // 글 리스트 페이지
    @RequestMapping(value = "Notice/notice_list_select.com", method = { RequestMethod.GET , RequestMethod.POST })
    public String noticeListSelect(Locale locale, Model model, @Param("page")String page) {

        List<Notice> list_Notice; // 게시글 내용
        int paging_test = noticeServiceimpl.noticeListCount(); // 글 총 갯수
        int pagecount; // 페이징이 Null일 경우

        // 페이징 카운트 계산
        if(page == null){ // 첫 페이지 실행
            pagecount = 0;
            list_Notice = noticeServiceimpl.noticeListSelection(pagecount);
            model.addAttribute("NoticeList", list_Notice);
            model.addAttribute("page",pagecount);
            model.addAttribute("adminList",MainController.GlobalmemberSession.getStatus()); // 기관 관리자
        }

        // 페이징이 전체 카운트 보다 클 경우
        else{
            pagecount = Integer.parseInt(page);
            if (pagecount < 0 ) { // 페이징 0보다 아래일 경우
                pagecount = 0;
                list_Notice = noticeServiceimpl.noticeListSelection(pagecount);
                model.addAttribute("NoticeList", list_Notice);
                model.addAttribute("page",pagecount);
                model.addAttribute("adminList",MainController.GlobalmemberSession.getStatus()); // 기관 관리자
                return "Notice/list"; // 글 작성 후 Main으로 이동noticeListCount
            } // 다음 페이지를 눌렀을 때 총 글 갯수보다 많을떄
            else if( paging_test < pagecount ){
                pagecount = paging_test;
                list_Notice = noticeServiceimpl.noticeListSelection(pagecount);
                model.addAttribute("NoticeList", list_Notice);
                model.addAttribute("page",pagecount);
                model.addAttribute("adminList",MainController.GlobalmemberSession.getStatus()); // 기관 관리자
            }
            list_Notice = noticeServiceimpl.noticeListSelection(pagecount);
            model.addAttribute("NoticeList", list_Notice);
            model.addAttribute("page",pagecount);
            model.addAttribute("adminList",MainController.GlobalmemberSession.getStatus()); // 기관 관리자
        }
        return "Notice/list"; // 글 작성 후 Main으로 이동

    }

    // 글 조회 페이지
    @RequestMapping(value = "Notice/select.com", method = { RequestMethod.GET , RequestMethod.POST })
    public String noticeSelect(Locale locale, Model model, @Param("notice_idx")int notice_idx) {
        Notice one_Notice = noticeServiceimpl.noticeDetailSelection(notice_idx);
        model.addAttribute("NoticeOne", one_Notice);
        model.addAttribute("adminList",MainController.GlobalmemberSession.getStatus()); // 기관 관리자
        return "Notice/select"; // 글 작성 후 Main으로 이동
    }

}
