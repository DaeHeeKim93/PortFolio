<%--글 작성 페이지--%>
<%@ page language="java" contentType="text/HTML;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/include/include.jsp" %>
<html>
    <head>
        <title>단어 작성 페이지</title>
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
        <!-- GNB 메뉴 -->
        <%@ include file="/WEB-INF/views/navigation/navigator.jsp" %>


        <div class="tbl_frm01 tbl_wrap">
            <form name="writeForm" id="writeForm" action="/Word/Word_write.com" method="post">
                <table>
                    <tbody>
                        <tr>
                            <th scope="row">
                                <label for="wr_content">북한말
                                    <strong class="sound_only">필수</strong>
                                </label>
                            </th>
                            <td class="wr_content">
                                <textarea name="north_word" id="north_word"></textarea>
                            </td>
                        </tr>

                        <tr>
                            <th scope="row">
                                <label for="wr_content">남한말
                                    <strong class="sound_only">필수</strong>
                                </label>
                            </th>
                            <td class="wr_content">
                                <textarea name="south_word" id="south_word"></textarea>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <div class="down">
                    <div class="col-md-5"></div>
                    <div class="col-md-2">
                        <input class="btn btn-warning" id="submit_button" type="submit" style="font-size : 15px" value="단어 작성"></input>
                    </div>
                    <div class="col-md=5"></div>
                </div>
            </form>
        </div>
        <!--/////////// -->
        <script>
            $(document).ready(function() {
                $('#north_word').summernote({
                    height: 300,
                    minHeight: 200,
                    maxHeight: 450
                });
                $('#south_word').summernote({
                    height: 300,
                    minHeight: 200,
                    maxHeight: 450
                });
            });
        </script>
    </body>
</html>
