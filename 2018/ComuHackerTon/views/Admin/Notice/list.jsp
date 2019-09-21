<%-- 목록 페이지--%>
<%@ page language="java" contentType="text/HTML;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/include/include.jsp" %>
<html>
    <head>
        <title>글 리스트 페이지</title>
        <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
        <!-- 섬머노트 -->
        <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet"/>
        <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.4/jquery.js"></script>
        <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" />
        <link rel="stylesheet" href="/resources/summernote/dist/summernote.css"/>
        <script src="/resources/summernote/dist/summernote.js"></script>
        <!-- 기타 함수 -->
        <!-- Bootstrap core CSS -->

        <!-- 리스트  CSS-->
        <link href="/resources/bootstrap/statistics/css/font-face.css" rel="stylesheet" media="all">
        <link href="/resources/bootstrap/statistics/vendor/font-awesome-4.7/css/font-awesome.min.css" rel="stylesheet" media="all">
        <link href="/resources/bootstrap/statistics/vendor/font-awesome-5/css/fontawesome-all.min.css" rel="stylesheet" media="all">
        <link href="/resources/bootstrap/statistics/vendor/mdi-font/css/material-design-iconic-font.min.css" rel="stylesheet" media="all">

        <!-- Bootstrap CSS-->
        <link href="/resources/bootstrap/statistics/vendor/bootstrap-4.1/bootstrap.min.css" rel="stylesheet" media="all">

        <!-- Vendor CSS-->
        <link href="/resources/bootstrap/statistics/vendor/animsition/animsition.min.css" rel="stylesheet" media="all">
        <link href="/resources/bootstrap/statistics/vendor/bootstrap-progressbar/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet" media="all">
        <link href="/resources/bootstrap/statistics/vendor/wow/animate.css" rel="stylesheet" media="all">
        <link href="/resources/bootstrap/statistics/vendor/css-hamburgers/hamburgers.min.css" rel="stylesheet" media="all">
        <link href="/resources/bootstrap/statistics/vendor/slick/slick.css" rel="stylesheet" media="all">
        <link href="/resources/bootstrap/statistics/vendor/select2/select2.min.css" rel="stylesheet" media="all">
        <link href="/resources/bootstrap/statistics/vendor/perfect-scrollbar/perfect-scrollbar.css" rel="stylesheet" media="all">

        <!-- Main CSS-->
        <link href="/resources/bootstrap/statistics/css/theme.css" rel="stylesheet" media="all">

        <!-- 메인화면 CSS -->
        <!-- Bootstrap core CSS -->
        <link href="/resources/bootstrap/main/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
        <!-- Custom styles for this template -->
        <link href="/resources/bootstrap/main/css/small-business.css" rel="stylesheet"/>
        <link href="/resources/bootstrap/main/css/common.css" rel="stylesheet"/>
        <link href="/resources/bootstrap/main/css/animation.css" rel="stylesheet"/>
        <link href="/resources/bootstrap/main/css/fontello.css" rel="stylesheet"/>
        <link href="/resources/bootstrap/main/css/fontello-codes.css" rel="stylesheet"/>
        <link href="/resources/bootstrap/main/css/fontello-embedded.css" rel="stylesheet"/>
        <link href="/resources/bootstrap/main/css/fontello-ie7.css" rel="stylesheet"/>
        <link href="/resources/bootstrap/main/css/fontello-ie7-codes.css" rel="stylesheet"/>

        <!--chart CSS , JS-->

        <!-- Fontfaces CSS-->
        <link href="/resources/bootstrap/statistics/css/font-face.css" rel="stylesheet" media="all"/>
        <link href="/resources/bootstrap/statistics/vendor/font-awesome-4.7/css/font-awesome.min.css" rel="stylesheet" media="all"/>
        <link href="/resources/bootstrap/statistics/vendor/font-awesome-5/css/fontawesome-all.min.css" rel="stylesheet" media="all"/>
        <link href="/resources/bootstrap/statistics/vendor/mdi-font/css/material-design-iconic-font.min.css" rel="stylesheet" media="all"/>
        <!-- Bootstrap CSS-->
        <link href="/resources/bootstrap/statistics/vendor/bootstrap-4.1/bootstrap.min.css" rel="stylesheet" media="all"/>
        <!-- Vendor CSS-->
        <link href="/resources/bootstrap/statistics/vendor/animsition/animsition.min.css" rel="stylesheet" media="all"/>
        <link href="/resources/bootstrap/statistics/vendor/bootstrap-progressbar/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet" media="all"/>
        <link href="/resources/bootstrap/statistics/vendor/wow/animate.css" rel="stylesheet" media="all"/>
        <link href="/resources/bootstrap/statistics/vendor/css-hamburgers/hamburgers.min.css" rel="stylesheet" media="all"/>
        <link href="/resources/bootstrap/statistics/vendor/slick/slick.css" rel="stylesheet" media="all"/>
        <link href="/resources/bootstrap/statistics/vendor/select2/select2.min.css" rel="stylesheet" media="all"/>
        <link href="/resources/bootstrap/statistics/vendor/perfect-scrollbar/perfect-scrollbar.css" rel="stylesheet" media="all"/>
        <!-- Main CSS-->
        <link href="/resources/bootstrap/statistics/css/theme.css" rel="stylesheet" media="all"/>

        <script src="/resources/bootstrap/statistics/vendor/jquery-3.2.1.min.js"></script>

    </head>
    <body>
        <!-- Navigation -->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
            <div class="container m_container">
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item active">
                            <a class="nav-link" href="/Main/Main.com">홈으로
                                <span class="sr-only">(current)</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/Member/LogOut.com">로그아웃</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/Member/SignOut.com">회원탈퇴</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <header>
            <div class="header-container">
                <div class="container">
                    <div class="top-logo">
                        <a href="/Main/Main.com">
                            <img src="/resources/bootstrap/main/image/security_icon.png" width="100" height="50"/>
                        </a>
                    </div>
                    <div class="gnb-container">
                        <ul class="gnb">
                            <li><a href="/Report/report.com">신고하기</a></li>
                            <li><a href="/Report/report_list_select.com">신고내역</a></li>
                            <li><a href="/Notice/notice_list_select.com">공지사항</a></li>
                            <li><a href="/Board/board_list_select.com">자유게시판</a></li>
                            <c:if test="${adminList eq '기관'}">
                                <li><a href="/Admin/admin.com">관리자</a></li>
                            </c:if>
                        </ul>
                    </div>
                </div>
            </div>
        </header>
        <br/>
        <br/>
        <!-- Navigation End -->
        <div class="container">
            <h2 class="title-1 m-b-10">공지사항</h2>
            <br/>
            <br/>
            <div clas="row">
                <div class="col-md-1"></div>
                <div class="col-md-10">
                    <table class="table table-bordered table-striped table-hover">
                        <thead>
                            <tr>
                                <th align="center">번호</th>
                                <th align="center">제목</th>
                                <th align="center">작성자</th>
                                <th align="center">날짜</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${NoticeList}" var="NoticesContents">
                                <tr class="bo_notice">
                                    <td class="td_num">${NoticesContents.notice_idx}</td>
                                    <td class="td_subject">
                                        <a href="/Admin/Notice/select.com?notice_idx=${NoticesContents.notice_idx}"/>${NoticesContents.notice_title}
                                    </td>
                                    <td class="td_name sv_use"><span class="sv_member">${NoticesContents.notice_id}</span></td>
                                    <td class="td_date">${NoticesContents.notice_date}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <br/>
                    <br/>
                    <div class="row">
                        <div class="col-md-3"></div>
                        <div class="col-md-6">
                            <input type="submit" class="au-btn au-btn-load js-load-btn"  onclick="location.href='/Admin/Notice/notice_list_select.com?page=${page-1}'" value="이전페이지"></input>
                            <input type="submit" class="au-btn au-btn-load js-load-btn"  onclick="location.href='/Admin/Notice/notice_list_select.com?page=${page+1}'" value="다음페이지"></input>
                            <input type="submit" class="au-btn au-btn-load js-load-btn"  onclick="location.href='/Admin/Notice/notice.com'" value="글 작성"></input>
                        </div>
                        <div class="col-md-3"></div>
                    </div>
                </div>
                <div class="col-md-1"></div>
            </div>
        </div>

    </body>
</html>