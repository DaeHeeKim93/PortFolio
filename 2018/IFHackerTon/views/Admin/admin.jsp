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
        <!-- GNB 메뉴 -->
        <%@ include file="/WEB-INF/views/navigation/navigator.jsp" %>
        <!-- Page Content -->
        <div class="container">
            <br/>
            <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-6" style="text-align:center;"><h2  style="text-align:center;">Admin Service Site</h2></div>
                <div class="col-md-3"></div>
            </div>
            <br/>
            <br/>
            <div class="row" style="height: 150px;">
            </div>
            <!-- 회원관리 / 공지사항 -->
            <div class="row">
                <div class="col-md-1"></div>
                <div class="col-md-10">
                    <a class="btn btn-info " id="select_button"  style="font-size : 50px; height:200px; width:300px; text-align:center; " href="/Admin/Member/member_list_select.com">회원관리</a>
                    <a class="btn btn-primary active" id="select_button"  style="font-size : 50px; height:200px; width:300px; text-align:center; " href="/Admin/Notice/notice_list_select.com">공지사항</a>
                    <a class="btn btn-warning active" id="select_button"  style="font-size : 50px; height:200px; width:300px; text-align:center; " href="/Admin/contributor.com">기여자</a>
                </div>
                <div class="col-md-1"></div>
            </div>
            <br/>
            <br/>
            <!-- 신고관리 / Contact US -->
            <div class="row">
                <div class="col-md-1"></div>
                <div class="col-md-10">
                    <a class="btn btn-success active" id="select_button"   style="font-size : 50px; height:200px; width:300px; text-align:center; " href="/Admin/Word/word_list_select.com">단어관리</a>
                    <a class="btn btn-success " id="select_button"  style="font-size : 50px; height:200px; width:300px; text-align:center; " href="/Admin/statistics.com">통계관리</a>
                    <a class="btn btn-danger active " id="select_button"  style="font-size : 50px; height:200px; width:300px; text-align:center; " href="/Admin/Mail/mail.com">Contact US</a>
                </div>
                <div class="col-md-1"></div>
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
