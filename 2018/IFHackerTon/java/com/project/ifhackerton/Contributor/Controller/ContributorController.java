package com.project.ifhackerton.Contributor.Controller;


import com.project.ifhackerton.Contributor.Service.ContributorService;
import com.project.ifhackerton.Main.Controller.MainController;
import com.project.ifhackerton.VO.Contributor;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;
import java.util.Locale;


@Controller
public class ContributorController {

    private static final Logger logger = LoggerFactory.getLogger(ContributorController.class);

    @Resource(name="com.project.ifhackerton.Word.Service.ContributorServiceimpl")
    private ContributorService contributorServiceimpl;

    // 글 리스트 페이지
    @RequestMapping(value = "Contributor/contributor.com", method = { RequestMethod.GET , RequestMethod.POST })
    public String ContributorListSelect(Locale locale, Model model, @Param("page")String page) {

        List<Contributor> Contributor_board; // 게시글 내용

        int paging_test = contributorServiceimpl.ContributorListCount(); // 글 총 갯수
        int pagecount; // 페이징이 Null일 경우

        // 페이징 카운트 계산
        if(page == null){ // 첫 페이지 실행
            pagecount = 0;
            Contributor_board = contributorServiceimpl.contributeListSelection(pagecount);
            model.addAttribute("Contributorlist", Contributor_board);
            model.addAttribute("page",pagecount);
            model.addAttribute("adminList",MainController.GlobalmemberSession.getStatus()); // 기관 관리자
            return "Contributor/contributor"; // 글 작성 후 Main으로 이동
        }
        // 페이징이 전체 카운트 보다 클 경우
        else{
            pagecount = Integer.parseInt(page);
            if (pagecount < 0 ) { // 페이징 0보다 아래일 경우
                pagecount = 0;
                Contributor_board = contributorServiceimpl.contributeListSelection(pagecount);
                model.addAttribute("Contributorlist", Contributor_board);
                model.addAttribute("page",pagecount);
                model.addAttribute("adminList",MainController.GlobalmemberSession.getStatus()); // 기관 관리자
                return "Contributor/contributor"; // 글 작성 후 Main으로 이동
            } // 다음 페이지를 눌렀을 때 총 글 갯수보다 많을떄
            else if( paging_test < pagecount ){
                pagecount = paging_test;
                Contributor_board = contributorServiceimpl.contributeListSelection(pagecount);
                model.addAttribute("Contributorlist", Contributor_board);
                model.addAttribute("page",pagecount);
                model.addAttribute("adminList",MainController.GlobalmemberSession.getStatus()); // 기관 관리자
            }
            Contributor_board = contributorServiceimpl.contributeListSelection(pagecount);
            model.addAttribute("Contributorlist", Contributor_board);
            model.addAttribute("page",pagecount);
            model.addAttribute("adminList",MainController.GlobalmemberSession.getStatus()); // 기관 관리자
        }
        return "Contributor/contributor"; // 글 작성 후 Main으로 이동
    }


    // 미로그인 시에 로그인 할 수 있게 작업하는 페이지
    @RequestMapping(value = "Main/contributor.com", method = { RequestMethod.GET , RequestMethod.POST })
    public String Contributor_NoLogin_ListSelect(Locale locale, Model model, @Param("page")String page) {

        List<Contributor> Contributor_board; // 게시글 내용

        int paging_test = contributorServiceimpl.Contributor_ListCount(); // 글 총 갯수
        int pagecount; // 페이징이 Null일 경우

        // 페이징 카운트 계산
        if(page == null){ // 첫 페이지 실행
            pagecount = 0;
            Contributor_board = contributorServiceimpl.contribute_ListSelection(pagecount);
            model.addAttribute("Contributorlist", Contributor_board);
            model.addAttribute("page",pagecount);
            return "Main/contributor"; // 글 작성 후 Main으로 이동
        }
        // 페이징이 전체 카운트 보다 클 경우
        else{
            pagecount = Integer.parseInt(page);
            if (pagecount < 0 ) { // 페이징 0보다 아래일 경우
                pagecount = 0;
                Contributor_board = contributorServiceimpl.contribute_ListSelection(pagecount);
                model.addAttribute("Contributorlist", Contributor_board);
                model.addAttribute("page",pagecount);
                return "Main/contributor"; // 글 작성 후 Main으로 이동
            } // 다음 페이지를 눌렀을 때 총 글 갯수보다 많을떄
            else if( paging_test < pagecount ){
                pagecount = paging_test;
                Contributor_board = contributorServiceimpl.contribute_ListSelection(pagecount);
                model.addAttribute("Contributorlist", Contributor_board);
                model.addAttribute("page",pagecount);
            }
            Contributor_board = contributorServiceimpl.contribute_ListSelection(pagecount);
            model.addAttribute("Contributorlist", Contributor_board);
            model.addAttribute("page",pagecount);
        }
        return "Main/contributor"; // 글 작성 후 Main으로 이동
    }
}
