<%--메인 페이지--%>
<%@ page language="java" contentType="text/HTML;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/include/include.jsp" %>
<!DOCTYPE html>
<html>
<head>
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
                <a class="navbar-brand" href="/Main/Main.com">Emergency Service</a>
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
                            <a class="nav-link" href="#">지역별 서비스</a>
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

        <!-- Page Content -->
        <div class="container">
            <br/>
            <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-6" style="text-align:center;"><h2  style="text-align:center;">관리자 및 기관을 위한 관리자 사이트 입니다.</h2></div>
                <div class="col-md-3"></div>
            </div>
            <br/>
            <br/>
            <div class="row" style="height: 150px;">
            </div>
            <!-- 회원관리 / 공지사항 -->
            <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-6">
                    <a class="btn btn-primary" id="select_button"  style="font-size : 50px; height:200px; width:300px; text-align:center; " href="/Admin/Member/member_list_select.com">회원관리</a>
                    <a class="btn btn-success" id="select_button"  style="font-size : 50px; height:200px; width:300px; text-align:center; " href="/Admin/Notice/notice_list_select.com">공지사항</a>
                </div>
                <div class="col-md-3"></div>
            </div>
            <br/>
            <br/>
            <!-- 신고관리 / Contact US -->
            <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-6">
                    <a class="btn btn-danger" id="select_button"   style="font-size : 50px; height:200px; width:300px; text-align:center; " href="/Admin/Report/report_list_select.com">신고관리</a>
                    <a class="btn btn-warning" id="select_button"  style="font-size : 50px; height:200px; width:300px; text-align:center; " href="/Admin/Mail/mail.com">Contact US</a>
                </div>
                <div class="col-md-3o"></div>
            </div>
            <br/>
            <div class="row" style="height: 250px;">
            </div>
            <br/>
        </div>
        <!-- /.container -->

        <!-- Footer -->
        <footer class="py-5 bg-dark">
            <div class="container">
                <p class="m-0 text-center text-white">Copyright &copy; Your Website 2018</p>
            </div>
            <!-- /.container -->
        </footer>

        <!-- Bootstrap core JavaScript -->
        <script src="/resources/bootstrap/main/vendor/jquery/jquery.min.js"></script>
        <script src="/resources/bootstrap/main/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>



        <!-- Jquery JS-->
        <script src="/resources/bootstrap/statistics/vendor/jquery-3.2.1.min.js"></script>
        <!-- Bootstrap JS-->
        <script src="/resources/bootstrap/statistics/vendor/bootstrap-4.1/popper.min.js"></script>
        <script src="/resources/bootstrap/statistics/vendor/bootstrap-4.1/bootstrap.min.js"></script>
        <!-- Vendor JS       -->
        <script src="/resources/bootstrap/statistics/vendor/slick/slick.min.js">
        </script>
        <script src="/resources/bootstrap/statistics/vendor/wow/wow.min.js"></script>
        <script src="/resources/bootstrap/statistics/vendor/animsition/animsition.min.js"></script>
        <script src="/resources/bootstrap/statistics/vendor/bootstrap-progressbar/bootstrap-progressbar.min.js">
        </script>
        <script src="/resources/bootstrap/statistics/vendor/counter-up/jquery.waypoints.min.js"></script>
        <script src="/resources/bootstrap/statistics/vendor/counter-up/jquery.counterup.min.js">
        </script>
        <script src="/resources/bootstrap/statistics/vendor/circle-progress/circle-progress.min.js"></script>
        <script src="/resources/bootstrap/statistics/vendor/perfect-scrollbar/perfect-scrollbar.js"></script>
        <script src="/resources/bootstrap/statistics/vendor/chartjs/Chart.bundle.min.js"></script>
        <script src="/resources/bootstrap/statistics/vendor/select2/select2.min.js">
        </script>

        <!-- Main JS-->
        <script src="/resources/bootstrap/statistics/js/main.js"></script>

    </body>
</html>
