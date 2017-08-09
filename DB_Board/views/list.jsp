<%-- 목록 확인 및 게시글 이동 페이지--%>

<%@ page language="java" contentType="text/HTML;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/header/header.jsp" %>
<%@ page session="false" %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/resources/boostrap/table/css/style.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/prefixfree/1.0.7/prefixfree.min.js"></script>
    <link rel="stylesheet" href="/resources/boostrap/price-tags/css/style.css"/>
    <link rel="stylesheet" href="/resources/css/button.css"/>
    <script src="/resources/javascript/paging.js"></script>
    <!-- jQuery -->
    <script src="/resources/boostrap/vendor/jquery/jquery.min.js"></script>
    <script src="/resources/javascript/comment.js"></script>
    <script src="/resources/jquery-3.2.1.js"></script>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
</head>
<body>
    <div class="container">
            <div class="starter-template">
                    <%--로고 삽입 부분--%>
                    <div class="table_first">
                    </div>
                    <%--게시물 공간 부분--%>
                    <div class="row" name="paging" id="paging">
                        <div class="col-md-1"></div>
                        <div class="col-md-7">


                            <table class="table-fill">
                                <thead>
                                    <tr>
                                        <th class="text-center">번호</th>
                                        <th class="text-center">제목</th>
                                        <th class="text-center">작성자</th>
                                        <th class="text-center">작성날짜</th>
                                        <th class="text-center">조회수</th>
                                        <th class="text-center">조회</th>
                                    </tr>
                                </thead>
                                <tbody class="table-hover">
                                    <c:forEach items="${boardinformation}" var="BoardVO">
                                            <tr>
                                                <td class="text-center" width="10%">${BoardVO.idx}</td>
                                                <td class="text-center" width="20%">${BoardVO.title}</td>
                                                <td class="text-center" width="30%">${BoardVO.board_nickname}</td>
                                                <td class="text-center" width="20%">${BoardVO.redegate}</td>
                                                <td class="text-center" width="10%">${BoardVO.board_count}</td>
                                                <td class="text-center" witdh="10%"><input class= "white" type="button" onclick="location.href='/select.?idx=${BoardVO.idx}'" value="조회"></input></td>
                                            </tr>

                                    </c:forEach>
                                </tbody>
                                <input type="hidden" id="pagemax" value="${PageLength}"></input>
                                <input type="hidden" id="page" value="${Page}"></input>
                            </table>


                        </div>

                        <div class="col-md-1"></div>
                        <div class="col-md-3">
                            <div class="row"></div>
                            <%--로그인 정보 확인--%>
                            <div class="row">
                                <table>
                                    <div class="event">
                                    <span>Hi</span>
                                    <div class="price">
                                        <br/>
                                         ${Nickname}
                                    </div>
                                    </div>
                                </table>
                            </div>
                            <div class="row"></div>
                        </div>
                    </div>


                        <%--검색 기능 페이지--%>
                    <div class="row" id = "search">
                        <div class="form-group row" >
                            <div class="col-md-2"></div>
                            <div class="col-md-6">
                            </div>
                        </div>
                            <%--페이지 이동 , 전 페이지--%>
                        <div class="row" id="movepage">
                            <div class="col-md-3">

                            </div>
                            <div class="col-md-4">
                                <input type="submit" class="btn btn-warning" onclick="FirstMovePage(document.getElementById('page').value)" value="처음페이지"></input>
                                <input type="submit" class="btn btn-info" onclick="loadPreviousPage(document.getElementById('page').value)" value="이전페이지"></input>
                                <input type="submit" class="btn btn-danger" onclick="loadNextPage(document.getElementById('page').value,document.getElementById('pagemax').value)" value="다음페이지"></input>
                                <input type="submit" class="btn btn-success" onclick="LastMovePage(document.getElementById('page').value,document.getElementById('pagemax').value)" value="마지막페이지"></input>
                                <a class="btn btn-primary" href="<spring:url value="/write"/>">글 작성</a>
                            </div>
                            <div class="col-md-5">

                            </div>
                        </div>

</div>
    </div>
    </div>
</body>
</html>
