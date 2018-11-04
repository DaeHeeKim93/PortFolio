<%-- 글 세부 페이지--%>
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
        <!-- Navigation End -->
        <!-- MAIN CONTENT-->
        <div class="main-content">
            <div class="section__content section__content--p30">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card">
                                <div class="card-header">
                                    <strong class="card-title">${BoardOne.board_title}</strong>
                                </div>

                                <div class="card-body">
                                    <div class="typo-articles">
                                            ${BoardOne.board_context}
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- 댓글 -->
                <div class="col-md-12">
                    <div class="col-md-12">
                        <form name="Comment" id="Comment" action="/Board/commentCreate.com" method="post">
                            <input type="hidden" id="board_idx" name="board_idx" value="${BoardOne.board_idx}" />
                            <div class="row">
                                <div class="col-md-11"><textarea style=" border: 1px solid #DCDCDC; width:100%;height:100; " id="comment_context" name="comment_context"></textarea></div>
                                <div class="col-md-1"><input class="btn btn-warning" id="submit_button" type="submit" style="font-size : 20px; font-weight: bold; " value="댓글 작성"></input></div>
                            </div>
                        </form>
                    </div>
                    <div class="col-md-1">
                    </div>
                </div>
                <div class="col-md-12"><br/></div>
                <h3 style="color:black;">댓글</h3>
                <br/>
                <!-- 댓글 출력 -->
                    <div class="col-md-12" style="border-style:solid; border-color: #DCDCDC; border-width: thin;">
                        <c:forEach items="${CommentList}" var="CommentListContents">
                            <div class="row" style="border-style:solid; border-color: #DCDCDC; border-width: thin;">
                                <div class="col-md-1"></div>
                                <div class="col-md-6">${CommentListContents.comment_id} &nbsp; ${CommentListContents.comment_date} </div>
                                <div class="col-md-4"><a class="btn btn-danger" href="/Board/commentDelete.com?comment_idx=${CommentListContents.comment_idx}&amp;board_idx=${BoardOne.board_idx}">댓글 삭제</a></div>
                                <div class="col-md-1"></div>
                            </div>
                            <br/>
                            <div class="row">
                                <div class="col-md-1"></div>
                                <div class="col-md-6">${CommentListContents.comment_context}</div>
                                <div class="col-md-1"></div>
                            </div>
                        </c:forEach>
                    </div>
                <div class="col-md-12"><br></br></div>
                <div class="col-md-4"></div>
                <div class="col-md-4">
                    <a class="au-btn au-btn-load" href="/Board/board_list_select.com">목록</a>
                    <a class="au-btn au-btn-load" href="/Board/board_select_update.com?board_idx=${BoardOne.board_idx}">글 수정</a>
                    <a class="au-btn au-btn-load" href="/Board/board_delete.com?board_idx=${BoardOne.board_idx}">글 삭제</a>
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
