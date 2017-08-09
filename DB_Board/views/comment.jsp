<%-- 댓글 ajax 처리 페이지 --%>
<%@ page language="java" contentType="text/HTML;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/header/header.jsp" %>
<%@ page session="false" %>

<html>
<head>
    <title>Title</title>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <title>Blog Post - Start Bootstrap Template</title>
    <!-- Bootstrap Core CSS -->
    <link href="/resources/boostrap/boardtype/startbootstrap-blog-post-gh-pages/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="/resources/boostrap/boardtype/startbootstrap-blog-post-gh-pages/css/blog-post.css" rel="stylesheet">
    <link rel="stylesheet" href="/resources/css/button.css"/>
    <link rel="stylesheet" href="/resources/css/board.css"/>
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script src="/resources/jquery-3.2.1.js"></script>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="/resources/javascript/comment.js"></script>
</head>
<body>
    <div class="row" name="commenting" id="commenting">

        <!-- 댓글 작성 확인창 -->
        <c:forEach items="${commentinformation}" var="CommentVO">
            <div class="media" id="comment_view" name="comment_view">

                <a class="pull-left" href="#">

                </a>
                <div class="media-body">

                    <h4 class="media-heading">${CommentVO.comment_nickname}
                        <small>${CommentVO.comment_redegate}</small>
                        <button class="comment" onclick="reply_comment('${CommentVO.comment_idx}','${board_idx}')"><font size="2px">답글</font></button>
                        <button class="comment" onclick="delete_comment('${CommentVO.comment_idx}', '${board_idx}')"><font size="2px">삭제</font></button>
                    </h4>
                        ${CommentVO.comment_content}
                    <input type="hidden" id="add${CommentVO.comment_idx}" name="add${CommentVO.comment_idx}" value="0"/>
                    <input type="hidden" id="id${CommentVO.comment_idx}" name="id${CommentVO.comment_idx}"  value="${CommentVO.comment_id}"></input>

                    <c:forEach items="${replyinformation}" var="ReplyVO">
                        <div class="row">
                            <div class="col-xs-1">
                            </div>
                            <div class="col-md-10">
                                <c:if test="${ReplyVO.comment_idx eq CommentVO.comment_idx}">
                                <h4 class="media-heading">${ReplyVO.re_com_nick}
                                    <small>${ReplyVO.re_com_regedate}</small>
                                    <button class="comment" onclick="reply_delete('${ReplyVO.re_idx}','${CommentVO.comment_idx}','${board_idx}')"><font size="2px">삭제</font></button>
                                </h4>
                                    ${ReplyVO.re_content}
                                </c:if>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                    <%-- 답글 처리 부분 --%>
            </div>
        </c:forEach>
        <input type="hidden" id="id${board_idx}" name="id${board_idx}"  value="${board_idx}"></input>
    </div>
    <!-- jQuery -->
    <script src="/resources/boostrap/boardtype/startbootstrap-blog-post-gh-pages/js/jquery.js"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="/resources/boostrap/boardtype/startbootstrap-blog-post-gh-pages/js/bootstrap.min.js"></script>
</body>
</html>
