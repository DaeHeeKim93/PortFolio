<%-- 단어 검색 페이지--%>
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
        <link href="/resources/bootstrap/main/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet"></link>
        <!-- Custom styles for this template -->
        <link href="/resources/bootstrap/main/css/small-business.css" rel="stylesheet"></link>
        <link href="/resources/bootstrap/main/css/common.css" rel="stylesheet"></link>
        <link href="/resources/bootstrap/main/css/animation.css" rel="stylesheet"></link>
        <link href="/resources/bootstrap/main/css/fontello.css" rel="stylesheet"></link>
        <link href="/resources/bootstrap/main/css/fontello-codes.css" rel="stylesheet"></link>
        <link href="/resources/bootstrap/main/css/fontello-embedded.css" rel="stylesheet"></link>
        <link href="/resources/bootstrap/main/css/fontello-ie7.css" rel="stylesheet"></link>
        <link href="/resources/bootstrap/main/css/fontello-ie7-codes.css" rel="stylesheet"></link>
        <link href="/resources/bootstrap/board/css/board.css" rel="stylesheet"></link>

        <!-- 링크 내용 -->

        <!-- Fontfaces CSS-->
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
        <style>
            textarea {
                width: 100%;
                height: 150px;
            }
        </style>
        <script type="text/javascript">
            // 북한말 검색 기능
            function north_search(){
                var form =  document.getElementById("north_textarea").value;
                $.ajax({
                    url:'/Word/north_select_com',
                    type : "POST",
                    contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                    data:({north_textarea:form}),
                    success: function (data) {
                    $("#south_textarea").val(data);
                    },
                    error: function () {

                    }
                });
            }
            // 남한말 검색 기능
            function south_search(){
                var form =  document.getElementById("south_textarea").value;
                $.ajax({
                    url:'/Word/south_select_com',
                    type : "POST",
                    contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                    data:({south_textarea:form}),
                    success: function (data) {
                    $("#north_textarea").val(data);
                    },
                    error: function () {
                    }
                });
            }
        </script>
    </head>
    <body>
        <!-- GNB 메뉴 -->
        <%@ include file="/WEB-INF/views/navigation/navigator.jsp" %>
        <!-- MAIN CONTENT-->
        <div class="main-content">
            <div class="section__content section__content--p30">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card">
                                <div class="card-header">
                                    <strong class="card-title">북한말</strong>
                                </div>

                                <div class="card-body">
                                    <div class="typo-articles">
                                        <form name="north_form" id="north_form"  method="post">
                                            <textarea id="north_textarea" name="north_textarea"></textarea>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card">
                                <div class="card-header">
                                    <strong class="card-title">남한말</strong>
                                </div>

                                <div class="card-body">
                                    <form name="south_form" id="south_form"  method="post">
                                        <textarea id="south_textarea" name="south_textarea"></textarea>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-4"></div>
                <div class="col-md-4">
                    <a class="au-btn au-btn--blue" href="javascript:north_search();">북한말 검색</a>
                    <a class="au-btn au-btn--blue2" href="javascript:south_search()">남한말 검색</a>
                </div>
                <div class="col-md-4"></div>
            </div>
        </div>

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
