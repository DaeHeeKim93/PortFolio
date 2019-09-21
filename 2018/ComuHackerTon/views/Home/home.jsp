<%--글 시작 페이지--%>
<%@ page language="java" contentType="text/HTML;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/include/include.jsp" %>
<!DOCTYPE html>
    <html>
    <head>
        <script src="http://code.jquery.com/jquery-latest.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"></script>
        <!-- Bootstrap core CSS -->
        <link href="/resources/bootstrap/start_page/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
        <!-- Custom styles for this template -->
        <link href="/resources/bootstrap/start_page/css/heroic-features.css" rel="stylesheet"/>
        <script type="text/javascript">
            // 신고하기
                function goReport(){
                    $("#goReportForm").submit();
                }
            // 회원 가입
                function goSign(){
                    $("#goSignForm").submit();
                }
            // 메인 이동
                function goMain(){
                    $("#goMainForm").submit();
                }
        </script>
    </head>
    <body>

        <!-- Page Content -->
        <div class="container">
            <!-- Page Features -->
            <div class="row text-center">
                <!-- 신고하기, 회원가입, 메인 -->
                <div class="col-lg-4 col-md-4 mb-4">
                    <div class="card">
                        <form id="goReportForm" class="goReportForm" method="POST" action="Main/Report.com">
                            <img class="card-img-top" src="/resources/bootstrap/start_page/image/alarm.png" onclick="goReport()"/>
                        </form>
                    </div>
                </div>
                <div class="col-lg-4 col-md-4 mb-4">
                    <div class="card">
                        <form id="goSignForm" class="goSignForm" method="POST"  action="Main/Sign.com">
                          <img class="card-img-top" src="/resources/bootstrap/start_page/image/sign.png" onclick="goSign()"/>
                        </form>
                    </div>
                </div>

                <div class="col-lg-4 col-md-4 mb-4">
                    <div class="card">
                        <form id="goMainForm" class="goMainForm"  method="POST"  action="Main/Main.com">
                             <img class="card-img-top" src="/resources/bootstrap/start_page/image/main.png" onclick="goMain()"/>
                        </form>
                    </div>
                </div>
            </div>
            <!-- /.row -->
        </div>
        <!-- /.container -->

        <!-- Bootstrap core JavaScript -->
        <script src="/resources/bootstrap/start_page/vendor/jquery/jquery.min.js"></script>
        <script src="/resources/bootstrap/start_page/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    </body>

</html>
