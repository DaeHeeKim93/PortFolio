package com.project.dbboard.Controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.project.dbboard.Service.interf.UserService;
import com.project.dbboard.VO.UserVO;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/*
* User를 관리하는 Controller
* */
@Controller
public class HomeController {


	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);


	@Resource(name="com.project.dbboard.Service.impl.UserServiceImpl")
	private UserService testServiceimpl;
	/**
	 * Simply selects the home view to render by returning its name.
	 */

	// 메인 페이지 이동동
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("프로젝트의 작동이 시작되었습니다 !.", locale);
		return "home";
	}

	// 로그인 및 회원가입 페이지 이동
	@RequestMapping(value = "/sign", method = RequestMethod.GET)
	public String sign(Locale locale, Model model) {
		logger.info("회원가입 및 로그인 창으로 이동 중입니다 !.", locale);
		return "sign";
	}

	//회원가입 처리 페이지
	@RequestMapping(value = "/sign_up" ,method = RequestMethod.POST)
	public String sign_up(Locale locale, Model model,UserVO userVO)
	{

	    //회원가입된 아이디가 있으므로 다시 회원가입 창으로
         List<UserVO> uservo = testServiceimpl.selectUserVO();
           //아이디 중복
           for(UserVO vo : uservo){
                if(userVO.getId().equals(vo.getId())){
                        logger.info("아이디가 중복 되었습니다!.", locale);
                        return "home";
                }
            }
        testServiceimpl.insertUserVO(userVO);
        logger.info("회원가입 되었습니다!.", locale);
		return "home";
	}

	//로그인 처리 페이지
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, Locale locale, Model model, UserVO userVO)
	{
	    // 아이디가 존재한다면 유사한지 확인 .
	    try {

            List<UserVO> uservo = testServiceimpl.selectUserVO();
            uservo.get(0);
            for(UserVO vo : uservo){
                if(userVO.getId().equals(vo.getId())){
                   if(userVO.getPassword().equals(vo.getPassword())) {
                       logger.info("로그인 되었습니다!.", locale);
                       request.getSession().setAttribute("ID",vo.getId());
                       request.getSession().setAttribute("Nickname",vo.getNickname());
					   request.getSession().setAttribute("Security","Login");
                       return "home_login";
                   }
                }
            }
        }
        //가입된 아이디가 없으므로 다시 로그인창
        catch (IndexOutOfBoundsException f)
        {
            logger.info("아무것도 없습니다.",locale);
            return "sign";
        }

        logger.info("로그인이 되지 않았습니다..",locale);
		return "home";
	}

	//로그아웃 처리 페이지
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, Locale locale,HttpSession httpSession)
	{
		httpSession.invalidate(); // 세션 삭제
		return "home";
	}

	// 회원탈퇴 관리 페이지
	@RequestMapping(value="/withdraw", method = RequestMethod.GET)
	public String withdraw(HttpServletRequest request, Locale locale,HttpSession httpSession)
	{

		String ID =  httpSession.getAttribute("ID").toString();
		httpSession.invalidate(); // 세션 삭제
		testServiceimpl.deleteuserVO(ID);
		return "home";
	}

}
