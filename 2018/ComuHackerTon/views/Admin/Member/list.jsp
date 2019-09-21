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
            <h2 class="title-1 m-b-10">회원관리</h2>
            <br/>
            <br/>
            <div clas="row">
                <div class="col-md-12">
                    <table class="table table-bordered table-striped table-hover">
                        <thead>
                            <tr>
                                <th align="center">번호</th>
                                <th align="center">아이디</th>
                                <th align="center">이메일</th>
                                <th align="center">이름</th>
                                <th align="center">전화번호</th>
                                <th align="center">비상전화번호</th>
                                <th align="center">개인/기관</th>
                                <th align="center">회원상태</th>
                                <th align="center">회원상태변경</th>
                                <th align="center">기관/개인 상태변경</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${MemberList}" var="MemberContents">
                                <tr class="bo_notice">
                                    <td align="center">${MemberContents.member_idx}</td>
                                    <td align="center">${MemberContents.id}</td>
                                    <td align="center">${MemberContents.email}</td>
                                    <td align="center">${MemberContents.name}</td>
                                    <td align="center">${MemberContents.tel}</td>
                                    <td align="center">${MemberContents.emergency_tel}</td>
                                    <td align="center">
                                        <c:if test="${MemberContents.status eq '기관'}">
                                            기관
                                        </c:if>
                                        <c:if test="${MemberContents.status eq '개인'}">
                                            개인
                                        </c:if>
                                    </td>
                                    <td align="center">
                                        <c:if test="${MemberContents.isDel eq 0}">
                                            일반회원
                                        </c:if>
                                        <c:if test="${MemberContents.isDel eq 1}">
                                            탈퇴회원
                                        </c:if>
                                        <c:if test="${MemberContents.isDel eq 2}">
                                            불량회원
                                        </c:if>
                                    </td>
                                    <!-- 회원 상태 변경 -->
                                    <td align="center">
                                        <a class="btn btn-warning" id="select_button1"  style="font-size : 7px" href="/Admin/Member/memberisDel.com?member_idx=${MemberContents.member_idx}&amp;isDel=0">정상회원</a>
                                        <a class="btn btn-danger" id="select_button2"  style="font-size : 7px" href="/Admin/Member/memberisDel.com?member_idx=${MemberContents.member_idx}&amp;isDel=1">탈퇴회원</a>
                                        <a class="btn btn-primary" id="select_button3"  style="font-size : 7px" href="/Admin/Member/memberisDel.com?member_idx=${MemberContents.member_idx}&amp;isDel=2">불량회원</a>
                                    </td>
                                    <!-- 회원 기관 개인 변경 -->
                                    <td align="center">
                                        <a class="btn btn-success" id="status-button1"  style="font-size : 7px" href="/Admin/Member/status.com?member_idx=${MemberContents.member_idx}&amp;status=개인">개인</a>
                                        <a class="btn btn-primary" id="status-button2"  style="font-size : 7px" href="/Admin/Member/status.com?member_idx=${MemberContents.member_idx}&amp;status=기관">기관</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <br/>
                    <br/>
                    <div class="row">
                        <div class="col-md-4"></div>
                        <div class="col-md-4">
                            <input type="submit" class="au-btn au-btn-load js-load-btn"  onclick="location.href='/Admin/Member/member_list_select.com?page=${page-1}'" value="이전페이지"></input>
                            <input type="submit" class="au-btn au-btn-load js-load-btn"  onclick="location.href='/Admin/Member/member_list_select.com?page=${page+1}'" value="다음페이지"></input>
                        </div>
                        <div class="col-md-4"></div>
                    </div>
            </div>
        </div>
        </div>
    </body>
</html>