package com.project.ifhackerton.Member.Controller;


import com.project.ifhackerton.Main.Controller.MainController;
import com.project.ifhackerton.Member.Service.MemberService;
import com.project.ifhackerton.VO.Member;
import com.project.ifhackerton.VO.MemberLog;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;


@Controller
public class MemberController {

    // Logger 작업
    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

    // AdminService Resource 객체
    @Resource(name="com.project.ifhackerton.Service.MemberServiceImpl")
    private MemberService MemberServiceimpl;

    // 회원가입
    @RequestMapping(value = "Member/Sign.com", method = { RequestMethod.GET , RequestMethod.POST })
    public String memberSign(Locale locale, Model model,@Param("signForm") Member joinMember) {
        int overlap_count = MemberServiceimpl.overlap_member(joinMember.getId());
        if(overlap_count == 0) {
            MemberServiceimpl.insert_member(joinMember); // 회원가입 정보 입력
            logger.info("Sign To Controller / 회원가입 작업 >> .", locale);
            return "redirect:/"; // 회원가입 확인시 메인으로 연결
        }else{
             // 중복 아이디 가입시 막아버림
            return "redirect:/Main/Sign.com";
        }
    }

    // 회원탈퇴
    @RequestMapping(value = "Member/SignOut.com", method = { RequestMethod.GET , RequestMethod.POST })
    public String memberSignOut(HttpServletRequest request,Locale locale, Model model) {
        HttpSession session = request.getSession();
        session.removeAttribute("memberSession");
        MemberServiceimpl.delete_member(MainController.GlobalmemberSession.getMember_idx());
        logger.info("Sign To Controller / 회원탈퇴 작업 >> .", locale);
        return "redirect:/"; // 회원가입 확인시 메인으로 연결

    }

    // 로그아웃
    @RequestMapping(value = "Member/LogOut.com", method = { RequestMethod.GET , RequestMethod.POST })
    public String memberLoginOut(HttpServletRequest request,Locale locale, Model model) {
        HttpSession session = request.getSession();

        // 로그 ( 로그아웃 부분 담기 )
        HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
        String ip = req.getHeader("X-FORWARDED-FOR");
        if (ip == null)
            ip = req.getRemoteAddr();


        // 해당 로그아웃 정보 로그 테이블에 입력
        MemberLog memberLog = new MemberLog();
        memberLog.setLogin_ip(ip);
        memberLog.setMember_id(MainController.GlobalmemberSession.getId());
        memberLog.setMember_idx(MainController.GlobalmemberSession.getMember_idx());
        memberLog.setStatus("O");
        MemberServiceimpl.MemberLog(memberLog);

        session.removeAttribute("memberSession");
        logger.info("Sign To Controller / 로그아웃 작업 >> .", locale);
        return "redirect:/"; // 로그아웃 시 메인으로 연결
    }

    // 로그인 여부 => 단순하게 비밀번호 검사 후 세션 입력 작업 - Session 넣어놓기
    @RequestMapping(value = "Member/Login.com", method = { RequestMethod.GET , RequestMethod.POST })
    public String memberLogin(HttpServletRequest request, Locale locale, Model model,
     @RequestParam(value = "id") String id,
     @RequestParam(value = "password") String password) {

        //ID , 비밀번호 검사
        int check_Member = MemberServiceimpl.check_member_password(id,password);

        // 로그인 여부 판단
        if(check_Member == 0){ // 아이디나 비밀번호가 일치하지 않을 경우

            logger.info("Not ID & Paswword / 비밀번호나 아이디가 일치하지 않습니다. >> .", locale);
            return "redirect:/"; // 회원가입 확인시 메인으로 연결

        }
        else { // 일치 하는 경우Boardidx
               // 실제 로그인 처리 작업
               // Session 객체 생성

            HttpSession session = request.getSession();
            // 세션에 담을 정보 가져오기
            MainController.GlobalmemberSession = MemberServiceimpl.create_session(id,password);
            // 회원 Session을 객체에 담음
            session.setAttribute("memberSession",  MainController.GlobalmemberSession);

            // 로그 ( 로그인 부분 담기 )
            HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
            String ip = req.getHeader("X-FORWARDED-FOR");
            if (ip == null)
                ip = req.getRemoteAddr();


            // 해당 로그인 정보 로그 테이블에 입력
            MemberLog memberLog = new MemberLog();
            memberLog.setLogin_ip(ip);
            memberLog.setMember_id(MainController.GlobalmemberSession.getId());
            memberLog.setMember_idx(MainController.GlobalmemberSession.getMember_idx());
            memberLog.setStatus("L");
            MemberServiceimpl.MemberLog(memberLog);

            logger.info("Go TO Login  / 회원이 로그인 하였습니다. >> .", locale);
            return "redirect:/Main/Main.com";  // 회원가입 확인시 메인으로 연결

        }

    }

}
