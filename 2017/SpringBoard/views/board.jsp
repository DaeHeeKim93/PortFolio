<%--글 작성 페이지--%>
<%@ page language="java" contentType="text/HTML;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/header/header.jsp" %>
<%@ page session="false" %>

<!DOCTYPE html>
<html>
<head>
    <title>Summernote</title>
            <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet"/>
            <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.4/jquery.js"></script>
            <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>
            <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" />
            <link rel="stylesheet" href="/resources/boostrap/summernote/dist/summernote.css"/>
            <script src="/resources/boostrap/summernote/dist/summernote.js"></script>

             <link rel="stylesheet" href="/resources/css/button.css"/>
            <link rel="stylesheet" href="/resources/css/board.css"/>

</head>
    <body>
        <div class="container">
            <%--로고 이미지 부분--%>
            <div class="row" id="logo">

            </div>
                <%--제목 부분--%>
            <div class="row">
                <div class="col-md-2">

                </div>
                <div class="col-md-7" id="up_editor">
                    <h4> <i class="note-icon-summernote"></i> Summernote Editor
                    </h4>

                </div>
                <div class="col-md-3">

                </div>
            </div>
                    <%--섬머노트 작성--%>
            <div class="row">
                <div class="col-md-2">

                </div>
                <div class="col-md-7" id="down_editor">
                        <form action="/submitboard" method="post" accept-charset="UTF-8">
                        제목 <input type="text" id="title" name="title" maxlength="200"></input>
                        <textarea id="summernote" name="content">

                        </textarea>
                        <div class="row" id="submit_row">
                            <div class="col-md-5"></div>
                            <div class="col-md-2">
                                <input class="btn btn-warning" id="submit_button" type="submit" value="게시글 작성"></input>
                            </div>
                            <div class="col-md-5"></div>
                        </div>
                        <input type="hidden" id="board_id" name="board_id" value="${ID}"></input>
                        <input type="hidden" id="board_nickname" name="board_nickname" value="${Nickname}" ></input>
                    </form>
                </div>
                <div class="col-md-3">

                </div>
            </div>
        </div>
    </body>
     <%--섬머노트 사용--%>
    <script type="text/javascript">
        $(document).ready(function() {
            $('#summernote').summernote()
        });
    </script>
</html>
