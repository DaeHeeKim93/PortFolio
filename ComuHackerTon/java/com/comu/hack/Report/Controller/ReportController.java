package com.comu.hack.Report.Controller;



import com.comu.hack.Main.Controller.MainController;
import com.comu.hack.Report.Service.ReportService;

import com.comu.hack.VO.Board;
import com.comu.hack.VO.Notice;
import com.comu.hack.VO.Report;
import com.nhncorp.lucy.security.xss.XssPreventer;
import org.apache.ibatis.annotations.Param;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;

import static com.nhncorp.lucy.security.xss.XssPreventer.*;

/**
 * Handles requests for the application home page.
 */
@Controller
public class ReportController {

    private static final Logger logger = LoggerFactory.getLogger(ReportController.class);

    @Resource(name="com.comu.hack.Home.Service.ReportServiceImpl")
    private ReportService ReportServiceimpl;

    // 글쓰기 페이지로 이동
    @RequestMapping(value = "Report/report.com", method = { RequestMethod.GET , RequestMethod.POST })
    public String reportPage(Locale locale, Model model) {
        model.addAttribute("adminList",MainController.GlobalmemberSession.getStatus()); // 기관 관리자
        logger.info("Go To the Report >> .", locale);
        return "Report/write"; // 글쓰기 페이지로 이동
    }

    // 글 삽입 페이지 ( 작성 후 )
    @RequestMapping(value = "Report/report_write.com", method = { RequestMethod.GET , RequestMethod.POST })
    public String reportWrite(Locale locale, Model model, @Param("writeForm") Report Report) {
        logger.info("Report Write Locate >> {}.", locale);
        // 복호화 임시 주석
        // 글 작성시에 제목 및 내용 XSS 예방, SQL Injection은 추후 예방
        ReportServiceimpl.reportInsert(Report,MainController.GlobalmemberSession);
        model.addAttribute("adminList",MainController.GlobalmemberSession.getStatus()); // 기관 관리자
        return "redirect:/Main/Main.com"; // 글 작성 후 Main으로 이동
    }

    // 글 수정 내용 불러오기  페이지
    @RequestMapping(value = "Report/report_select_update.com", method = { RequestMethod.GET , RequestMethod.POST })
    public String reportSelectUpdate(Locale locale, Model model, @RequestParam("report_idx") String write_report_Kill){
        logger.info("Report Update Select Locate >> {}.", locale);
        Report Report_Update = ReportServiceimpl.reportDetailSelection(Integer.parseInt(write_report_Kill)); // 글 수정을 위해 내용을 불러온다
        if(Report_Update.getMember_idx() == MainController.GlobalmemberSession.getMember_idx()){
            model.addAttribute("adminList",MainController.GlobalmemberSession.getStatus()); // 기관 관리자
            model.addAttribute("ReportUpdate",Report_Update); // 신고게시판 출력
            return "Report/update"; // 글 작성 페이지로 이동
        }else{ // 해당 글 작성자가 아닌 경우
            return "redirect:/Report/select.com?report_idx=" + Integer.parseInt(write_report_Kill); // 해당 글로 새로고침
        }
    }


    // 글 수정 페이지
    @RequestMapping(value = "Report/report_update.com", method = { RequestMethod.GET , RequestMethod.POST })
    public String reportUpdate(Locale locale, Model model, @Param("report_Kill") Report write_report_Kill) {
        logger.info("Report Update Locate >> {}.", locale);
        // 글 수정하는 사람과 수정 당사자가 일치했을때 수정
        Report reportCollectObject = ReportServiceimpl.reportDetailSelection(write_report_Kill.getReport_idx());
        if(reportCollectObject.getMember_idx() == MainController.GlobalmemberSession.getMember_idx()) {
            ReportServiceimpl.reportUpdate(write_report_Kill, MainController.GlobalmemberSession);
        }
        model.addAttribute("adminList",MainController.GlobalmemberSession.getStatus()); // 기관 관리자
        return "redirect:/Report/select.com?report_idx=" + write_report_Kill.getReport_idx(); // 글 수정 후 해당 글로 이동
    }

    // 글 삭제 페이지
    @RequestMapping(value = "Report/report_delete.com", method = { RequestMethod.GET , RequestMethod.POST })
    public String reportDelete(Locale locale, Model model, @RequestParam("report_idx") String write_report_Kill) {
        // 삭제 작성자와 삭제가 일치했을 떄만 삭제
        Report ReportObject = ReportServiceimpl.reportDetailSelection(Integer.parseInt(write_report_Kill));
        if(ReportObject.getMember_idx() == MainController.GlobalmemberSession.getMember_idx()) {
            ReportServiceimpl.reportDelete(Integer.parseInt(write_report_Kill), MainController.GlobalmemberSession);
            model.addAttribute("adminList",MainController.GlobalmemberSession.getStatus()); // 기관 관리자
            logger.info("Report Delete Locate >> {}.", locale);
            return "redirect:/Main/Main.com"; // 글 작성 후 Main으로 이동
        }else{
            return "redirect:/Report/select.com?report_idx=" + Integer.parseInt(write_report_Kill); // 해당 글로 새로고침
        }

    }


    // 글 리스트 페이지
    @RequestMapping(value = "Report/report_list_select.com", method = { RequestMethod.GET , RequestMethod.POST })
    public String reportListSelect(Locale locale, Model model, @Param("page")String page) {

        List<Report> list_Report_Kill; // 게시글 내용

        int paging_test = ReportServiceimpl.reportListCount(); // 글 총 갯수
        int pagecount; // 페이징이 Null일 경우

        // 페이징 카운트 계산
        if(page == null){ // 첫 페이지 실행
            pagecount = 0;
            list_Report_Kill = ReportServiceimpl.reportListSelection(pagecount);
            model.addAttribute("ReportList", list_Report_Kill);
            model.addAttribute("page",pagecount);
            model.addAttribute("adminList",MainController.GlobalmemberSession.getStatus()); // 기관 관리자

        }
        // 페이징이 전체 카운트 보다 클 경우
        else{
            pagecount = Integer.parseInt(page);
            if (pagecount < 0 ) { // 페이징 0보다 아래일 경우
                pagecount = 0;
                list_Report_Kill = ReportServiceimpl.reportListSelection(pagecount);
                model.addAttribute("ReportList", list_Report_Kill);
                model.addAttribute("page",pagecount);
                model.addAttribute("adminList",MainController.GlobalmemberSession.getStatus()); // 기관 관리자
                return "Report/list"; // 글 작성 후 Main으로 이동
            } // 다음 페이지를 눌렀을 때 총 글 갯수보다 많을떄
            else if( paging_test < pagecount ){
                pagecount = paging_test;
                list_Report_Kill = ReportServiceimpl.reportListSelection(pagecount);
                model.addAttribute("ReportList", list_Report_Kill);
                model.addAttribute("page",pagecount);
                model.addAttribute("adminList",MainController.GlobalmemberSession.getStatus()); // 기관 관리자
            }
            list_Report_Kill = ReportServiceimpl.reportListSelection(pagecount);
            model.addAttribute("ReportList", list_Report_Kill);
            model.addAttribute("page",pagecount);
            model.addAttribute("adminList",MainController.GlobalmemberSession.getStatus()); // 기관 관리자
        }
        return "Report/list"; // 글 작성 후 Main으로 이동
    }

    // 글 조회 페이지
    @RequestMapping(value = "Report/select.com", method = { RequestMethod.GET , RequestMethod.POST })
    public String reportSelect(Locale locale, Model model, @Param("report_idx")int report_idx) {
        Report one_Report_Kill = ReportServiceimpl.reportDetailSelection(report_idx);
        model.addAttribute("ReportOne", one_Report_Kill);
        model.addAttribute("adminList",MainController.GlobalmemberSession.getStatus()); // 기관 관리자
        return "Report/select"; // 글 작성 후 Main으로 이동
    }


}
