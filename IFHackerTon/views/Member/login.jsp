<%--로그인 페이지--%>
<%@ page language="java" contentType="text/HTML;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/include/include.jsp" %>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1".>
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<meta name="keywords" content="Flat Trendy Signup Forms Responsive Templates, Iphone Widget Template, Smartphone login forms,Login form, Widget Template, Responsive Templates, a Ipad 404 Templates, Flat Responsive Templates" />
<link href="/resources/bootstrap/sign_login/css/style.css" rel='stylesheet' type='text/css' />
<!--webfonts-->
<link href='http://fonts.googleapis.com/css?family=PT+Sans:400,700,400italic,700italic|Oswald:400,300,700' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,400,300,700,800' rel='stylesheet' type='text/css'>
<!--//webfonts-->
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
</head>
<body>
<script>$(document).ready(function(c) {
    $('.close').on('click', function(c){
        $('.login-form').fadeOut('slow', function(c){
            $('.login-form').remove();
        });
    });
});
</script>
<!--로그인 폼-->
<div class="login-form">
    <div class="close"> </div>
    <div class="head">
    </div>
    <div class="head-info">
        <h1>Login UP</h1>
        <h2>LoginCheck</h2>
    </div>
    <!-- 로그인 폼 -->
    <form  method="POST" name="LoginForm" action="/Member/Login.com">
        <li>
            <input type="text" class="text" name="id" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Name';}" /><a href="#" class=" icon user"></a>
        </li>
        <li>
            <input type="password" name="password" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Password';}"/><a href="#" class=" icon lock"></a>
        </li>
        <div class="p-container">
            <input type="submit"  value="SIGN UP" />
            <div class="clear"> </div>
        </div>
    </form>
</div>
</body>
</html>