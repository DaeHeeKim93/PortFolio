<%-- 목록 페이지--%>
<%@ page language="java" contentType="text/HTML;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/include/include.jsp" %>
<html>
    <head>
        <title>글 리스트 페이지</title>
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

        <!-- 리스트  CSS-->
        <link href="/resources/bootstrap/statistics/css/font-face.css" rel="stylesheet" media="all">
        <link href="/resources/bootstrap/statistics/vendor/font-awesome-4.7/css/font-awesome.min.css" rel="stylesheet" media="all">
        <link href="/resources/bootstrap/statistics/vendor/font-awesome-5/css/fontawesome-all.min.css" rel="stylesheet" media="all">
        <link href="/resources/bootstrap/statistics/vendor/mdi-font/css/material-design-iconic-font.min.css" rel="stylesheet" media="all">

        <!-- Bootstrap CSS-->
        <link href="/resources/bootstrap/statistics/vendor/bootstrap-4.1/bootstrap.min.css" rel="stylesheet" media="all">

        <!-- Vendor CSS-->
        <link href="/resources/bootstrap/statistics/vendor/animsition/animsition.min.css" rel="stylesheet" media="all">
        <link href="/resources/bootstrap/statistics/vendor/bootstrap-progressbar/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet" media="all">
        <link href="/resources/bootstrap/statistics/vendor/wow/animate.css" rel="stylesheet" media="all">
        <link href="/resources/bootstrap/statistics/vendor/css-hamburgers/hamburgers.min.css" rel="stylesheet" media="all">
        <link href="/resources/bootstrap/statistics/vendor/slick/slick.css" rel="stylesheet" media="all">
        <link href="/resources/bootstrap/statistics/vendor/select2/select2.min.css" rel="stylesheet" media="all">
        <link href="/resources/bootstrap/statistics/vendor/perfect-scrollbar/perfect-scrollbar.css" rel="stylesheet" media="all">

        <!-- Main CSS-->
        <link href="/resources/bootstrap/statistics/css/theme.css" rel="stylesheet" media="all">

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

        <script src="/resources/bootstrap/statistics/vendor/jquery-3.2.1.min.js"></script>


        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script type="text/javascript">
            var list = new Array();
            google.charts.load('current', {'packages':['corechart']});
            google.charts.setOnLoadCallback(drawChart);
            <c:forEach var="item" items="${LoginDatelist}" varStatus="status">
                list.push(${item});
            </c:forEach>

            function drawChart() {
            var data = google.visualization.arrayToDataTable([
            ['Day', '접속 횟수'],
            ['7 Days Ago',  0 ],
            ['6 Days Ago',  0 ],
            ['5 Days Ago',  0 ],
            ['4 Days Ago',  0 ],
            ['3 Days Ago',  0 ],
            ['YesterDay',   list[1] ],
            ['Today',  list[0]]
            ]);

            var options = {
            title: '사용자 접속 횟수 ',
            width: 900,
            curveType: 'function',
            legend: { position: 'bottom' }
            };

            var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));

            chart.draw(data, options);
            }
        </script>
        <script type="text/javascript">
            google.charts.load("current", {packages:["corechart"]});
            google.charts.setOnLoadCallback(drawChart);
            var list1 = new Array();
            var list2 = new Array();

            <c:forEach var="item" items="${MessageCount}" varStatus="status">
                list1.push(${item});
            </c:forEach>

            <c:forEach var="item" items="${Message}" varStatus="status">
                list2.push("${item}");
            </c:forEach>

            function drawChart() {
            var data = google.visualization.arrayToDataTable([
            ["단어", "사용량", { role: "style" } ],
            [list2[0], list1[0], "#b87333"],
            [list2[1], list1[1], "silver"],
            [list2[2], list1[2], "gold"],
            [list2[3], list1[3], "color: #e5e4e2"]
            ]);

            var view = new google.visualization.DataView(data);
            view.setColumns([0, 1,
            { calc: "stringify",
            sourceColumn: 1,
            type: "string",
            role: "annotation" },
            2]);

            var options = {
            title: "단어 검색 건수",
            width: 900,
            height: 400,
            bar: {groupWidth: "95%"},
            legend: { position: "none" },
            };
            var chart = new google.visualization.BarChart(document.getElementById("barchart_values"));
            chart.draw(view, options);
            }
        </script>
    </head>
    <body>
        <!-- Navigation -->
        <!-- GNB 메뉴 -->
        <%@ include file="/WEB-INF/views/navigation/navigator.jsp" %>
        <br/>
        <br/>
        <!-- Navigation End -->
        <div class="container">
            <!-- Markup -->
            <div id="curve_chart"  style="width: 900px; height: 400px;"></div>
            <div id="barchart_values" style="width: 900px; height: 400px;"></div>
        </div>
    </body>
</html>