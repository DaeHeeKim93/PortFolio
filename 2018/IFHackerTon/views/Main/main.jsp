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
            <!-- Heading Row -->
            <div class="row my-3">
                <div class="col-lg-3 report-container">
                    <div class="report-box">
                        <div class="sSlogan">
                        새로운 단어를 등록해주세요
                        </div>
                        <div class="sHeader">
                            <div class="icon"><img src="/resources/bootstrap/start_page/image/register_word.png" width="84" height="80" alt="신고하기" title="신고하기"/></div>
                            <div class="title">단어등록</div>
                        </div>
                        <div class="sReport">
                            <button class="btn btn-trans" style="width:228px;" onclick="location.href='/Word/Word.com'"><i class="icon-edit" style="font-size:21px;"></i><br /> 새로운 단어 등록하기</button>
                        </div>
                    </div>

                    <div class="service-box">


                            <div class="sSlogan sSlogan2">
                                알쓸웨어 단어등록 기여자 리스트
                            </div>
                            <div class="sHeader">
                                <div class="icon"><img src="/resources/bootstrap/main/image/trophy.png" width="84" height="80" alt="안심콜 서비스" title="안심콜 서비스"/></div>
                                <div class="title">기여자 리스트</div>
                            </div>

                            <div class="sLogin">
                                <form action="/Contributor/contributor.com">
                                    <button class="btn btn-trans btn-Blue" type="submit"><i class="icon-user-o"></i><br/>단어등록 기여자 리스트<br/>알아보기</button>
                                </form>
                            </div>

                    </div>


                </div>
                <!-- /.col-lg-8 -->
                <div class="col-md-9"  id="admin_image" name="admin_image" style="display:flex;">
                    <img src="/resources/bootstrap/main/image/sub19_img1.jpg" style="flex:1;" />
                </div>
                <div class="col-lg-1" >

                </div>
                <!-- /.col-md-4 -->
            </div>
            <!-- /.row -->

            <!-- Call to Action Well -->
            <div class="card text-white bg-secondary my-4 text-center">
                <div class="card-body">
                    <p class="text-white m-0">알쓸웨어는 남북의 언어의 소통을 이해하기 위해 우리나라말과 북한말 단어비교 서비스를 제공합니다</p>
                </div>
            </div>

            <!-- Content Row -->
            <div class="row">
                <div class="col-md-4 mb-4">
                    <div class="card h-100">
                        <div class="card-body">
                            <h2 class="card-title"><i class="icon-docs"></i>공지사항</h2>
                            <ul class="articles">
                                wordList
                                <c:forEach items="${noticeList}" var="noticeList">
                                    <li>
                                        <a class="textEllipsis"
                                           href="/Notice/select.com?notice_idx=${noticeList.notice_idx}">
                                                ${noticeList.notice_title}
                                        </a>
                                        <span class="f_R">${noticeList.notice_date}</span>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>

                    </div>
                </div>
                <!-- /.col-md-4 -->
                <!-- 단어 목록 -->
                <div class="col-md-4 mb-4">
                    <div class="card h-100">
                        <div class="card-body">
                            <h2 class="card-title"><i class="icon-lightbulb"></i>단어목록</h2>
                            <ul class="articles">
                                <c:forEach items="${wordList}" var="wordList">
                                    <li>
                                        <a class="textEllipsis"
                                           href="">
                                                ${wordList.north_word}&nbsp;
                                        </a>


                                        <span class="f_R">${wordList.word_date}</span>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>

                    </div>
                </div>
                <!-- /.col-md-4 -->
                <div class="col-md-4 mb-4">
                    <div class="card h-100">
                        <div class="card-body">
                            <h2 class="card-title"><i class="icon-mic"></i>자유게시판</h2>
                            <ul class="articles">
                                <c:forEach items="${boardList}" var="boardList">
                                    <li>
                                        <a class="textEllipsis"
                                           href="/Board/select.com?board_idx=${boardList.board_idx}">
                                                ${boardList.board_title}
                                        </a>
                                        <span class="f_R">${boardList.board_date}</span>
                                    </li>
                                </c:forEach>
                            </ul>
                                <%--<p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Rem magni quas ex numquam, maxime minus quam molestias corporis quod, ea minima accusamus.</p>--%>
                        </div>

                    </div>
                </div>
                <!-- /.col-md-4 -->

            </div>
            <!-- /.row -->

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
