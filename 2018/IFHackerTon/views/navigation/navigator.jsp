    <%@ page language="java" contentType="text/HTML;charset=UTF-8" pageEncoding="UTF-8" %>
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
                        <img src="/resources/bootstrap/start_page/image/logo.png" width="80" />
                    </a>
                </div>
                <div class="gnb-container">
                    <ul class="gnb">
                        <li><a href="/Notice/notice_list_select.com">공지사항</a></li>
                        <li><a href="/Board/board_list_select.com">자유게시판</a></li>
                        <li><a href="/Word/word_list_select.com">단어검색</a></li>
                        <c:if test="${adminList eq '기관'}">
                            <li><a href="/Admin/admin.com">관리자</a></li>
                        </c:if>
                    </ul>
                </div>
            </div>
        </div>
    </header>

