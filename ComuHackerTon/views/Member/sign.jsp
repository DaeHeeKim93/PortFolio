<%--회원가입 페이지--%>
<%@ page language="java" contentType="text/HTML;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/include/include.jsp" %>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1"/>
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<meta name="keywords" content="Flat Trendy Signup Forms Responsive Templates, Iphone Widget Template, Smartphone login forms,Login form, Widget Template, Responsive Templates, a Ipad 404 Templates, Flat Responsive Templates" />
<link href="/resources/bootstrap/sign_login/css/style.css" rel='stylesheet' type='text/css' />
<!--webfonts-->
<link href='http://fonts.googleapis.com/css?family=PT+Sans:400,700,400italic,700italic|Oswald:400,300,700' rel='stylesheet' type='text/css'/>
<link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,400,300,700,800' rel='stylesheet' type='text/css'/>
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
<!--SIGN UP-->
    <div class="login-form">

        <div class="close"></div>
        <div class="head">
        </div>
        <div class="head-info">
            <h1>회원 가입하기</h1>
        </div>

        <!--///// -->
        <div class="join_wrap">
            <form method="POST" name="signForm" id="signForm" action="/Member/Sign.com">
                <fieldset>
                    <div class="join_box join_step2">
                        <div class="join_tb">
                            <table class="join_table">
                                <colgroup>
                                    <col style="width:15%;">
                                    <col style="width:85%;">
                                </colgroup>
                                <tbody>
                                    <tr>
                                        <th scope="row">
                                            <label for="reg_mb_id"><span class="red">*</span>아이디
                                            </label>
                                        </th>
                                        <td>
                                            <input type="text" name="id" value="" id="id" required=""
                                                   class="frm_input required " minlength="3" maxlength="20">
                                        </td>
                                    </tr>
                                    <tr>
                                        <th scope="row">
                                            <label for="reg_mb_password"><span class="red">*</span>비밀번호
                                            </label>
                                        </th>
                                        <td>
                                            <input type="password" name="password" id="password" required=""
                                                   class="frm_input required" minlength="3" maxlength="20">
                                        </td>
                                    </tr>
                                    <tr>
                                        <th scope="row">
                                            <label for="reg_mb_name"><span class="red">*</span>이름
                                            </label>
                                        </th>
                                        <td>
                                            <input type="text" id="name" name="name" value="" required=""
                                                   class="frm_input required " size="10"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th scope="row">
                                            <label for="reg_mb_email"><span class="red">*</span>E-mail
                                            </label>
                                        </th>
                                        <td>
                                            <input type="text" name="email" value="" id="email" required=""
                                                   class="frm_input email required" size="70" maxlength="100"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th scope="row" id="reg_mb_3_th">
                                            <label for="reg_mb_3"><span class="red">*</span>핸드폰
                                            </label>
                                        </th>
                                        <td>
                                            <input type="text" name="tel"  value="" id="tel" required=""
                                                   class="frm_input" size="70" maxlength="11"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th scope="row" id="reg_mb_3_th">
                                            <label for="reg_mb_3"><span class="red">*</span>보호자 전화번호
                                            </label>
                                        </th>
                                        <td>
                                            <input type="text" name="emergency_tel" id="emergency_tel" value="" id="reg_mb_3" required=""
                                                   class="frm_input" size="70" maxlength="11"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th scope="row">
                                            <label for="reg_mb_name"><span class="red">*</span>우편번호
                                            </label>
                                        </th>
                                        <td>
                                            <input type="text" id="zipcode" name="zipcode" value="" required=""
                                                   class="frm_input required " size="10"/>
                                        </td>
                                    </tr>
                                    <br/>
                                    <tr>
                                        <th scope="row" id="reg_mb_3_th">
                                            <label for="reg_mb_3"><span class="red">*</span>멤버상태
                                            </label>
                                        </th>
                                        <td>
                                            <input name="status" type="checkbox" id="status" value="개인"/>
                                            <span>개인</span>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </fieldset>
                 <br/>
                 <br/>
                <div class="p-container">
                    <input type="submit" value="SIGN UP"/>
                    <div class="clear"></div>
                </div>
            </form>

        </div>
    </div>
</body>
</html>