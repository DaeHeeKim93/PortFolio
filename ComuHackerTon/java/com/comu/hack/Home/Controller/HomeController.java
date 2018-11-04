package com.comu.hack.Home.Controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/*
 * 제목 : HomeController
 * 날짜 : 2018.10.05
 * 내용 : 메인 페이지 Controller
 * */

@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);


    // 시작 페이지 호출
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Locale locale, Model model) {
        logger.info("Start Page Open ( 홈페이지 시작 )>>> ", locale);
        return "Home/home";
    }

}
