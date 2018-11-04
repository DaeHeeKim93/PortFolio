package com.comu.hack.Admin.Controller;


import com.comu.hack.Admin.Service.AdminServiceImpl;
import com.comu.hack.Main.Controller.MainController;
import com.comu.hack.VO.Notice;
import com.comu.hack.VO.Member;
import com.comu.hack.VO.Report;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.util.Properties;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

/*
 * 제목 : BoardController
 * 날짜 : 2018.10.05
 * 내용 : 자유게시판 글 쓰기, 수정, 리스트, 페이지, 삭제 등 Service Implement ( 구현체 )
 * */

/**
 * Handles requests for the application home page.
 */
@Controller
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);


    @Resource(name="com.comu.hack.Home.Service.AdminServiceImpl")
    private AdminServiceImpl adminServiceimpl;

    // 공지사항 시작 부분
    @RequestMapping(value = "Admin/admin.com", method = { RequestMethod.GET , RequestMethod.POST })
    public String admminPage(Locale locale, Model model) {
        logger.info("Go To the Admin >> .", locale);
        return "Admin/admin"; // 글쓰기 페이지로 이동
    }

    /* 회원관리 부분 시작 */

    // 회원 총 리스트
    @RequestMapping(value = "Admin/Member/member_list_select.com", method = { RequestMethod.GET , RequestMethod.POST })
    public String Admin_memberListSelect(Locale locale, Model model, @Param("page")String page) {

        List<Member> list_Member; // 회원 인원 수
        int paging_test = adminServiceimpl.adminMemberSelectCount(); // 회원 총 인원
        int pagecount; // 페이징이 Null일 경우

        // 페이징 카운트 계산
        if(page == null){ // 첫 페이지 실행
            pagecount = 0;
            list_Member = adminServiceimpl.adminMemberSelect(pagecount);
            model.addAttribute("MemberList", list_Member);
            model.addAttribute("page",pagecount);
            model.addAttribute("adminList",MainController.GlobalmemberSession.getStatus()); // 기관 관리자
        }

        // 페이징이 전체 카운트 보다 클 경우
        else{
            pagecount = Integer.parseInt(page);
            if (pagecount < 0 ) { // 페이징 0보다 아래일 경우
                pagecount = 0;
                list_Member = adminServiceimpl.adminMemberSelect(pagecount);
                model.addAttribute("MemberList", list_Member);
                model.addAttribute("page",pagecount);
                model.addAttribute("adminList",MainController.GlobalmemberSession.getStatus()); // 기관 관리자
                return "Admin/Member/list"; // 글 작성 후 Main으로 이동
            } // 다음 페이지를 눌렀을 때 총 글 갯수보다 많을떄
            else if( paging_test < pagecount ){
                pagecount = paging_test;
                list_Member = adminServiceimpl.adminMemberSelect(pagecount);
                model.addAttribute("MemberList", list_Member);
                model.addAttribute("page",pagecount);
                model.addAttribute("adminList",MainController.GlobalmemberSession.getStatus()); // 기관 관리자
            }
            list_Member = adminServiceimpl.adminMemberSelect(pagecount);
            model.addAttribute("MemberList", list_Member);
            model.addAttribute("page",pagecount);
            model.addAttribute("adminList",MainController.GlobalmemberSession.getStatus()); // 기관 관리자
        }
        return "Admin/Member/list"; // 글 작성 후 Main으로 이동

    }

    // 회원 상태 - 탈퇴, 불량 회원 등 변경
    @RequestMapping(value = "Admin/Member/memberisDel.com", method = { RequestMethod.GET , RequestMethod.POST })
    public String Admin_IsDelUpdate(Locale locale, Model model, @Param("member_idx") int member_idx , @Param("isDel") int isDel) {
        logger.info("Admin Member isDel Update Locate / 회원 상태 수정 >> {}.", locale);
        // 회원 상태 변경을 위한 객체 생성
        Member memberisDelObject = new Member();
        memberisDelObject.setMember_idx(member_idx);
        memberisDelObject.setIsDel(isDel);
        adminServiceimpl.adminIsDelUpdate(memberisDelObject);
        return "redirect:/Admin/Member/member_list_select.com;"; // 회원 상태 변경 후 목록
    }


    // 회원 기관 / 개인 변경
    @RequestMapping(value = "Admin/Member/status.com", method = { RequestMethod.GET , RequestMethod.POST })
    public String Admin_statusUpdate(Locale locale, Model model,  @Param("member_idx") int member_idx , @Param("status") String status) {
        logger.info("Admin Member status Update Locate / 회원 상태 수정 >> {}.", locale);
        // 회원 상태 변경을 위한 객체 생성
        Member memberisStatusObject = new Member();
        memberisStatusObject.setMember_idx(member_idx);
        memberisStatusObject.setStatus(status);
        adminServiceimpl.adminStatusUpdate(memberisStatusObject);
        return "redirect:/Admin/Member/member_list_select.com;"; // 회원 상태 변경 후 목록
    }



    /* 회원관리 부분 끝 */


    /* 공지사항 부분 시작 */
    // 글쓰기 페이지로 이동
    @RequestMapping(value = "Admin/Notice/notice.com", method = { RequestMethod.GET , RequestMethod.POST })
    public String Admin_noticePage(Locale locale, Model model) {
        logger.info("Go To the Notice / 공지사항 글쓰기 >> .", locale);
        model.addAttribute("adminList",MainController.GlobalmemberSession.getStatus()); // 기관 관리자
        return "Admin/Notice/write"; // 공지사항 글쓰기 페이지로 이동
    }

    // 글 삽입 페이지 ( 작성 후 )
    @RequestMapping(value = "Admin/Notice/notice_write.com", method = { RequestMethod.GET , RequestMethod.POST })
    public String Admin_noticeWrite(Locale locale, Model model, @Param("writeForm") Notice Notice) {
        logger.info("Notice Write Locate / 공지사항 글 삽입 >> {}.", locale);
        // 복호화 임시 주석
        // 글 작성시에 제목 및 내용 XSS 예방, SQL Injection은 추후 예방
        adminServiceimpl.noticeInsert(Notice,MainController.GlobalmemberSession);
        model.addAttribute("adminList",MainController.GlobalmemberSession.getStatus()); // 기관 관리자
        return "redirect:/Main/Main.com"; // 글 작성 후 Main으로 이동
    }

    // 글 수정 내용 불러오기  페이지
    @RequestMapping(value = "Admin/Notice/notice_select_update.com", method = { RequestMethod.GET , RequestMethod.POST })
    public String Admin_noticeSelectUpdate(Locale locale, Model model, @RequestParam("notice_idx") String notice_idx) {
        logger.info("Notice Update Select Locate / 공지사항 글 수정 내용 불러오기 >> {}.", locale);
        Notice Notice_Update = adminServiceimpl.noticeDetailSelection(Integer.parseInt(notice_idx)); // 글 수정을 위해 내용을 불러온다
        // 글 작성자인지 확인하고 아니면 그냥 그 글 그 상태로 유지
        if(Notice_Update.getMember_idx() == MainController.GlobalmemberSession.getMember_idx()){
            model.addAttribute("NoticeUpdate",Notice_Update); // 자유게시판 출력
            model.addAttribute("adminList",MainController.GlobalmemberSession.getStatus()); // 기관 관리자
            return "Admin/Notice/update"; // 글 작성 페이지로 이동
        }else{ // 해당 글 작성자가 아닌 경우
            return "redirect:Admin/Notice/select.com?notice_idx=" + Integer.parseInt(notice_idx); // 해당 글로 새로고침
        }
    }


    // 글 수정 페이지
    @RequestMapping(value = "Admin/Notice/notice_update.com", method = { RequestMethod.GET , RequestMethod.POST })
    public String Admin_noticeUpdate(Locale locale, Model model, @Param("Notice") Notice Notice) {
        logger.info("Notice Update Locate / 공지사항 글 수정 >> {}.", locale);
        // 글 수정하는 사람과 수정 당사자가 일치했을때 수정
        Notice noticeCollectObject = adminServiceimpl.noticeDetailSelection(Notice.getNotice_idx());
        if(noticeCollectObject.getMember_idx() == MainController.GlobalmemberSession.getMember_idx()) {
            adminServiceimpl.noticeUpdate(Notice, MainController.GlobalmemberSession);
        }
        model.addAttribute("adminList",MainController.GlobalmemberSession.getStatus()); // 기관 관리자
        return "redirect:/Admin/Notice/select.com?notice_idx=" + Notice.getNotice_idx(); // 글 수정 후 해당 글로 이동
    }

    // 글 삭제 페이지
    @RequestMapping(value = "Admin/Notice/notice_delete.com", method = { RequestMethod.GET , RequestMethod.POST })
    public String Admin_noticeDelete(Locale locale, Model model, @RequestParam("notice_idx") String notice_idx) {
        // 삭제 작성자와 삭제가 일치했을 떄만 삭제
        Notice NoticeObject = adminServiceimpl.noticeDetailSelection(Integer.parseInt(notice_idx));
        if(NoticeObject.getMember_idx() == MainController.GlobalmemberSession.getMember_idx()) {
            adminServiceimpl.noticeDelete(Integer.parseInt(notice_idx), MainController.GlobalmemberSession);
            model.addAttribute("adminList",MainController.GlobalmemberSession.getStatus()); // 기관 관리자
            logger.info("Notice Delete Locate / 공지사항 글 삭제 >> {}.", locale);
            return "redirect:/Main/Main.com"; // 글 작성 후 Main으로 이동
        } else{
            return "redirect:/Admin/Notice/select.com?notice_idx=" + Integer.parseInt(notice_idx); // 해당 글로 새로고침
        }

    }


    // 글 리스트 페이지
    @RequestMapping(value = "Admin/Notice/notice_list_select.com", method = { RequestMethod.GET , RequestMethod.POST })
    public String Admin_noticeListSelect(Locale locale, Model model, @Param("page")String page) {

        List<Notice> list_Notice; // 게시글 내용
        int paging_test = adminServiceimpl.noticeListCount(); // 글 총 갯수
        int pagecount; // 페이징이 Null일 경우

        // 페이징 카운트 계산
        if(page == null){ // 첫 페이지 실행
            pagecount = 0;
            list_Notice = adminServiceimpl.noticeListSelection(pagecount);
            model.addAttribute("NoticeList", list_Notice);
            model.addAttribute("page",pagecount);
            model.addAttribute("adminList",MainController.GlobalmemberSession.getStatus()); // 기관 관리자
        }

        // 페이징이 전체 카운트 보다 클 경우
        else{
            pagecount = Integer.parseInt(page);
            if (pagecount < 0 ) { // 페이징 0보다 아래일 경우
                pagecount = 0;
                list_Notice = adminServiceimpl.noticeListSelection(pagecount);
                model.addAttribute("NoticeList", list_Notice);
                model.addAttribute("page",pagecount);
                model.addAttribute("adminList",MainController.GlobalmemberSession.getStatus()); // 기관 관리자
                return "Admin/Notice/list"; // 글 작성 후 Main으로 이동
            } // 다음 페이지를 눌렀을 때 총 글 갯수보다 많을떄
            else if( paging_test <= pagecount ){
                pagecount = paging_test;
                list_Notice = adminServiceimpl.noticeListSelection(pagecount);
                model.addAttribute("NoticeList", list_Notice);
                model.addAttribute("page",pagecount);
                model.addAttribute("adminList",MainController.GlobalmemberSession.getStatus()); // 기관 관리자
            }
            list_Notice = adminServiceimpl.noticeListSelection(pagecount);
            model.addAttribute("NoticeList", list_Notice);
            model.addAttribute("page",pagecount);
            model.addAttribute("adminList",MainController.GlobalmemberSession.getStatus()); // 기관 관리자
        }
        return "Admin/Notice/list"; // 글 작성 후 Main으로 이동

    }

    // 글 조회 페이지
    @RequestMapping(value = "Admin/Notice/select.com", method = { RequestMethod.GET , RequestMethod.POST })
    public String Admin_noticeSelect(Locale locale, Model model, @Param("notice_idx")int notice_idx) {
        Notice one_Notice = adminServiceimpl.noticeDetailSelection(notice_idx);
        model.addAttribute("NoticeOne", one_Notice);
        model.addAttribute("adminList",MainController.GlobalmemberSession.getStatus()); // 기관 관리자
        return "Admin/Notice/select"; // 글 작성 후 Main으로 이동
    }
    /* 공지사항 부분 끝 */


    /* 신고 부분 시작 */

    // 글 리스트 페이지
    @RequestMapping(value = "Admin/Report/report_list_select.com", method = { RequestMethod.GET , RequestMethod.POST })
    public String reportListSelect(Locale locale, Model model, @Param("page")String page) {

        List<Report> list_Report_Kill; // 게시글 내용

        int paging_test = adminServiceimpl.reportListCount(); // 글 총 갯수
        int pagecount; // 페이징이 Null일 경우

        // 페이징 카운트 계산
        if(page == null){ // 첫 페이지 실행
            pagecount = 0;
            list_Report_Kill = adminServiceimpl.reportListSelection(pagecount);
            model.addAttribute("ReportList", list_Report_Kill);
            model.addAttribute("page",pagecount);
            model.addAttribute("adminList",MainController.GlobalmemberSession.getStatus()); // 기관 관리자

        }
        // 페이징이 전체 카운트 보다 클 경우
        else{
            pagecount = Integer.parseInt(page);
            if (pagecount < 0 ) { // 페이징 0보다 아래일 경우
                pagecount = 0;
                list_Report_Kill = adminServiceimpl.reportListSelection(pagecount);
                model.addAttribute("ReportList", list_Report_Kill);
                model.addAttribute("page",pagecount);
                model.addAttribute("adminList",MainController.GlobalmemberSession.getStatus()); // 기관 관리자
                return "Admin/Report/list"; // 글 작성 후 Main으로 이동
            } // 다음 페이지를 눌렀을 때 총 글 갯수보다 많을떄
            else if( paging_test < pagecount ){
                pagecount = paging_test;
                list_Report_Kill = adminServiceimpl.reportListSelection(pagecount);
                model.addAttribute("ReportList", list_Report_Kill);
                model.addAttribute("page",pagecount);
                model.addAttribute("adminList",MainController.GlobalmemberSession.getStatus()); // 기관 관리자
            }
            list_Report_Kill = adminServiceimpl.reportListSelection(pagecount);
            model.addAttribute("ReportList", list_Report_Kill);
            model.addAttribute("page",pagecount);
            model.addAttribute("adminList",MainController.GlobalmemberSession.getStatus()); // 기관 관리자
        }
        return "Admin/Report/list"; // 글 작성 후 Main으로 이동
    }

    // 글 조회 페이지
    @RequestMapping(value = "Admin/Report/select.com", method = { RequestMethod.GET , RequestMethod.POST })
    public String reportSelect(Locale locale, Model model, @Param("report_idx")int report_idx) {
        Report one_Report_Kill = adminServiceimpl.reportDetailSelection(report_idx);
        model.addAttribute("ReportOne", one_Report_Kill);
        model.addAttribute("adminList",MainController.GlobalmemberSession.getStatus()); // 기관 관리자
        return "Admin/Report/select"; // 글 작성 후 Main으로 이동
    }

    // 신고 해결 수정
    @RequestMapping(value = "Admin/Report/status.com", method = { RequestMethod.GET , RequestMethod.POST })
    public String Admin_report_Update(Locale locale, Model model, @Param("report_idx")int report_idx, @Param("report_status") String report_status) {
       //신고 해결 상태 수정을 위한 객체 생성 후 함수 실행
        Report one_Report_Kill =  new Report();
        one_Report_Kill.setReport_idx(report_idx);
        one_Report_Kill.setReport_status(report_status);
        one_Report_Kill.setClear_id(MainController.GlobalmemberSession.getId());
        adminServiceimpl.adminreportUpdate(one_Report_Kill);
        return "redirect:/Admin/Report/report_list_select.com"; // 글 작성 후 Main으로 이동
    }

    /* 신고 부분 종료 */


    /* 구글 메일을 통한 신고 내용 */

    @RequestMapping(value = "Admin/Mail/mail.com", method = { RequestMethod.GET , RequestMethod.POST })
    public String AdminMailForm(Locale locale, Model model) {
        return "/Admin/Mail/mailsend"; // 글 작성 후 Main으로 이동
    }

    /* 메일 내용 전송 */
    @RequestMapping(value = "Admin/Mail/mailSend.com", method = { RequestMethod.GET , RequestMethod.POST })
    public String AdminMailSend(Locale locale, Model model, HttpServletRequest request) throws MessagingException {

        String email = (String) request.getParameter("email");
        String name = (String) request.getParameter("name");
        String telphone = (String) request.getParameter("telphone");
        String context = (String) request.getParameter("context");

        
        // Google SMTP 전송 이메일
        final String host = "smtp.gmail.com";
        final String google_id = "줄 이메일";
        final String google_pw = "이메일 비밀번호";
        final int port = 465;

        
        // 해당되는 SMTP를 받을 이메일 여기서는 같으니 상관없음 
        String receive_gmail = "받을 이메일";
        String sender = email;

        // SSL 등의 옵션을 허락해 줘야함.
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.trust", host);


        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication(){
                return new javax.mail.PasswordAuthentication(google_id, google_pw);
            }
        });
        session.setDebug(true);


        // 구글 연결 설정
        Message GoogleMessage = new MimeMessage(session);
        GoogleMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(receive_gmail));

        // 내용 전송
        String text = "이름: " + name + "\n이메일: " + email + "\n전화번호: " + telphone + "\n\n내용: " + context;
        GoogleMessage.setSubject(email);
        GoogleMessage.setText(text);
        Transport.send(GoogleMessage);

        return "/Admin/admin";
    }



}
