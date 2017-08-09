<%--
  시작일자 : 2017.07.10
  회원가입 및 로그인 페이지
--%>
<%@ page language="java" contentType="text/HTML;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/header/header.jsp" %>
<%@ page session="false" %>
<%
    request.setCharacterEncoding("UTF-8");
%>

<html>
<head>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css"/>
    <link rel='stylesheet prefetch' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'/>
    <link rel="stylesheet" href="/resources/boostrap/login-sign-in/css/style.css"/>

</head>
<body>

    <%--회원가입 및 로그인 창 --%>
    <div class="logmod">
        <div class="logmod__wrapper">
            <%--위치를 잡기 위한 블록--%>
            <div class="logmod__container">
                <ul class="logmod__tabs">
                    <li data-tabtar="lgm-2"><a href="#">Login</a></li>
                    <li data-tabtar="lgm-1"><a href="#">Sign Up</a></li>
                </ul>
                <div class="logmod__tab-wrapper">
                    <%--회원가입 페이지--%>
                    <div class="logmod__tab lgm-1">
                        <div class="logmod__heading">
                            <span class="logmod__heading-subtitle">Enter your personal details <strong>to create an acount</strong></span>
                        </div>
                        <div class="logmod__form">
                            <form action="/sign_up" method="post" accept-charset="utf-8" spring:htmlEscapedefaultHtmlEscape="true" path="someFormField" htmlEscape="true" >
                                <div class="sminputs">
                                    <div class="input full">
                                        <label >ID</label>
                                        <input maxlength="255" id="id" name="id" placeholder="ID" size="50" />
                                    </div>
                                </div>
                                <div class="sminputs">
                                    <div class="input string optional">
                                        <label >Password</label>
                                        <input maxlength="255" id="password" name="password" placeholder="Password" type="password" size="20" />
                                    </div>
                                    <div class="input string optional">
                                        <label >Nickname</label>
                                        <input maxlength="255" id="nickname" name="nickname" placeholder="Nickname" type="text" size="50" />
                                    </div>
                                </div>
                                <div class="simform__actions">
                                    <input contenteditable="false" class="sumbit" name="commit" type="submit" value="Create Account" />
                                    <span class="simform__actions-sidetext">50자 이내로 써주세요.<a class="special" href="#" target="_blank" role="link">Terms & Privacy</a></span>
                                </div>
                            </form>
                        </div>
                        <div class="logmod__alter">
                            <div class="logmod__alter-container">
                                <a href="#" class="connect facebook">
                                    <div class="connect__icon">
                                        <i class="fa fa-facebook"></i>
                                    </div>
                                    <div class="connect__context">
                                        <span>Create an account with <strong>Facebook</strong></span>
                                    </div>
                                </a>

                                <a href="#" class="connect googleplus">
                                    <div class="connect__icon">
                                        <i class="fa fa-google-plus"></i>
                                    </div>
                                    <div class="connect__context">
                                        <span>Create an account with <strong>Google+</strong></span>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </div>

                    <%--로그인 페이지--%>
                    <div class="logmod__tab lgm-2">
                        <div class="logmod__heading">
                            <span class="logmod__heading-subtitle">Enter your email and password <strong>to sign in</strong></span>
                        </div>
                        <div class="logmod__form">
                            <form  action="/login"  method = "post"  accept-charset="utf-8" spring:htmlEscapedefaultHtmlEscape="true" path="someFormField" htmlEscape="true" >
                                <div class="sminputs">
                                    <div class="input full">
                                        <label class="string optional" >ID</label>
                                        <input class="string optional"  maxlength="255" id="id" name="id" placeholder="ID" size="50" />
                                    </div>
                                </div>
                                <div class="sminputs">
                                    <div class="input full">
                                        <label class="string optional" >Password</label>
                                        <input class="string optional" maxlength="255" id="password" name="password" placeholder="Password" type="password" size="20" />
                                        <span class="hide-password">Show</span>
                                    </div>
                                </div>
                                <div class="simform__actions">
                                    <input class="sumbit" name="commit" type="submit" value="Log In" />
                                    <span class="simform__actions-sidetext"><a class="special" role="link" href="#">Forgot your password?<br>Click here</a></span>
                                </div>
                            </form>
                        </div>
                        <div class="logmod__alter">
                            <div class="logmod__alter-container">
                                <a href="#" class="connect facebook">
                                    <div class="connect__icon">
                                        <i class="fa fa-facebook"></i>
                                    </div>
                                    <div class="connect__context">
                                        <span>Sign in with <strong>Facebook</strong></span>
                                    </div>
                                </a>
                                <a href="#" class="connect googleplus">
                                    <div class="connect__icon">
                                        <i class="fa fa-google-plus"></i>
                                    </div>
                                    <div class="connect__context">
                                        <span>Sign in with <strong>Google+</strong></span>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>

<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src="/resources/boostrap/login-sign-in/js/index.js"></script>
</body>
</html>
