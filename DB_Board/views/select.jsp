<%--게시판 조회 페이지--%>
<%@ page language="java" contentType="text/HTML;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/header/header.jsp" %>
<%@ page session="false" %>

<html>
<head>

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
    <script src="/resources/javascript/comment.js"></script>
    <script src="/resources/jquery-3.2.1.js"></script>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>

</head>
    <body>


        <%--네비게이터 바 처리--%>
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="<spring:url value="/"/>">DATABASE LAB PROJECT</a>
                </div>
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li>
                            <a href="<spring:url value="/homelogin"/>">Home</a>
                        </li>
                        <li>
                            <a href="<spring:url value="/list"/>">List</a>
                        </li>
                    </ul>
                </div>
                <!-- /.navbar-collapse -->
            </div>
            <!-- /.container -->
        </nav>

        <!-- Page Content -->
        <div class="container">

            <div class="row">

                <!-- Blog Post Content Column -->
                <div class="col-lg-8">
                    <!-- Title -->
                    <h1>${BoardVO.title}</h1>
                    <!-- Author -->
                    <p class="lead">
                        by <a href="#">${BoardVO.board_nickname}</a>
                    </p>
                    <hr/>
                    <!-- Date/Time -->
                    <p><span class="glyphicon glyphicon-time"></span>${BoardVO.redegate}</p>
                    <hr/>
                    <%--내용 처리 --%>
                    <div class="row" id = "board_container">
                        <div class="col-xs-1"></div>
                        <div class="col-xs-11">
                                ${BoardVO.content}
                        </div>

                    </div>
                    <!-- Blog Comments -->

                    <!-- 댓글 창 처리 -->
                    <div class="well">
                        <h4>Leave a Comment:</h4>
                        <form name="comment_form" id="comment_form" method="post">
                            <div class="form-group" >
                                <textarea class="form-control" name="comment_content" id = "comment_content" rows="3"></textarea>
                            </div>
                            <input type="hidden" id="board_idx" name="board_idx" value="${BoardVO.idx}"/>
                            <input type="hidden" id="comment_nickname" name="comment_nickname" value="${BoardVO.board_nickname}"/>
                            <input type="hidden" id="comment_id" name="comment_id" value="${BoardVO.board_id}"/>
                            <input type="hidden" id="comment_redegate" name="comment_redegate" value="${BoardVO.redegate}"/>
                        </form>
                        <button class="btn btn-primary" name="comment_button" id="comment_button" onclick="comment_insert()">Submit</button>
                    </div>

                    <hr/>
                    <div class="row" name="commenting" id="commenting">

                            <!-- 댓글 작성 확인창 -->
                            <c:forEach items="${commentinformation}" var="CommentVO">
                                <div class="media" id="comment_view" name="comment_view">

                                    <a class="pull-left" href="#">

                                    </a>
                                    <div class="media-body">

                                        <h4 class="media-heading">${CommentVO.comment_nickname}
                                            <small>${CommentVO.comment_redegate}</small>
                                            <button class="comment" onclick="reply_comment('${CommentVO.comment_idx}','${BoardVO.idx}')"><font size="2px">답글</font></button>
                                            <button class="comment" onclick="delete_comment('${CommentVO.comment_idx}','${BoardVO.idx}')"><font size="2px">삭제</font></button>
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
                                                        <button class="comment" onclick="reply_delete('${ReplyVO.re_idx}','${CommentVO.comment_idx}','${BoardVO.idx}')"><font size="2px">삭제</font></button>
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
                        </div>

                </div>

                <!-- Blog Sidebar Widgets Column -->
                <div class="col-md-4">

                    <!-- Blog Search Well -->
                    <div class="well">
                        <h4>Blog Search</h4>
                        <div class="input-group">
                            <input type="text" class="form-control"/>
                            <span class="input-group-btn">
                                <button class="btn btn-default" type="button">
                                    <span class="glyphicon glyphicon-search"></span>
                                </button>
                            </span>
                        </div>
                        <!-- /.input-group -->
                    </div>

                    <!-- 게시판 카테고리 -->
                    <div class="well">
                        <h4>Blog Categories</h4>
                        <div class="row">
                            <ul class="list-unstyled">
                                    <%--<li><a href="#">Category Name</a>--%>
                                    <%--</li>--%>
                            </ul>
                        </div>
                        <div class="col-lg-6">
                            <ul class="list-unstyled">
                                    <%--<li><a href="#">Category Name</a>--%>
                                    <%--</li>--%>
                            </ul>
                        </div>
                    </div>
                    <!-- /.row -->

                <!-- 사이드 위젯 -->
                    <div class="well">
                        <h4>Side Widget Well</h4>
                        <p>Test Side Widget</p>
                    </div>
                    </div>
            </div>
            <!-- /.row -->

            <hr/>
            <!-- Footer -->
            <footer>
                <div class="row">
                    <div class="col-lg-2">
                        <p>Copyright &copy; My Side 2017</p>
                    </div>
                    <div class="col-lg-3">

                    </div>
                    <div class="col-lg-3">

                        <form name="update_form" id="update_form" method="post" action="/update">
                            <input type="hidden" id="board_collect" name="board_collect"
                                   value="${BoardVO.idx}"/>
                            <input type="button" class="btn btn-danger" onclick="location.href='/list.htm'" value="목록으로 복귀"></input>
                            <input type="button" class="btn btn-success" onclick="delete_board(document.getElementById('board_idx').value)" value="게시글 삭제"></input>
                            <input type="submit" class="btn btn-warning" value="게시글 수정"></input>
                        </form>
                    </div>
                    <div class="col-lg-4">

                </div>
                </div>
                <!-- /.row -->
            </footer>
        </div>
        <!-- /.container -->

        <!-- jQuery -->
        <script src="/resources/boostrap/boardtype/startbootstrap-blog-post-gh-pages/js/jquery.js"></script>
        <!-- Bootstrap Core JavaScript -->
        <script src="/resources/boostrap/boardtype/startbootstrap-blog-post-gh-pages/js/bootstrap.min.js"></script>
    </body>
</html>
