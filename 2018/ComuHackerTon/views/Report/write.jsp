<%--글 작성 페이지--%>
<%@ page language="java" contentType="text/HTML;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/include/include.jsp" %>
<html>
    <head>
        <title>글 작성 페이지</title>
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
        <div class="tbl_frm01 tbl_wrap">
            <form name="writeForm" id="writeForm" action="/Report/report_write.com" method="post">
                <table>
                    <tbody>
                        <tr>
                            <th scope="row">
                                <label for="wr_name">제목
                                    <strong class="sound_only">필수</strong>
                                </label>
                            </th>
                            <td>
                                <div id="autosave_wrapper">
                                    <input type="text" class="frm_input required" id="report_title" name="report_title" maxlength="255"/>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">범죄유형</th>
                            <td>
                                <select>
                                    <option>범죄유형1</option>
                                    <option>범죄유형2</option>
                                    <option>범죄유형3</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">
                                <label for="wr_content">내용
                                    <strong class="sound_only">필수</strong>
                                </label>
                            </th>
                            <td class="wr_content">
                                <textarea name="report_context" id="report_context"></textarea>
                            </td>
                        </tr>
                        <!--관리자 확인여부 체크 -->
                        <tr>
                            <th scope="row"><label for="wr_link1">비밀글</label></th>
                            <td><input type="checkbox" id="isSecret" name="isSecret" value="1" class="frm_input" size="50"/></td>
                        </tr>
                    </tbody>
                </table>
                <div class="down">
                    <div class="col-md-5"></div>
                    <div class="col-md-2">
                        <input class="btn btn-warning" id="submit_button" type="submit" style="font-size : 15px"  value="게시글 작성"></input>
                    </div>
                    <div class="col-md=5"></div>
                </div>
            </form>
        </div>
        <!--/////////// -->
        <script>
            $(document).ready(function() {
            $('#report_context').summernote({
            height: 600,
            minHeight: 400,
            maxHeight: 700
            });
            });
        </script>
    </body>
</html>
