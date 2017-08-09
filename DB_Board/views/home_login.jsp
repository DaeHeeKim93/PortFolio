<%--메인 페이지 (로그인시 회원가입 창 나오지 않음 )/ Boostrap free template 사용--%>

<%@ page language="java" contentType="text/HTML;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/header/header.jsp" %>
<%@ page session="false" %>

<html>
    <head>
            <%--메인 페이지 라이브러리 링크--%>
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <meta name="description" content=""/>
        <meta name="author" content=""/>

        <title>Creative - Start Bootstrap Theme</title>

        <!-- Bootstrap Core CSS -->
        <link href="/resources/boostrap/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>

        <!-- Custom Fonts -->
        <link href="/resources/boostrap/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
        <link href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'/>
        <link href='https://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic,900,900italic' rel='stylesheet' type='text/css'/>

        <!-- Plugin CSS -->
        <link href="/resources/boostrap/vendor/magnific-popup/magnific-popup.css" rel="stylesheet"/>

        <!-- Theme CSS -->
        <link href="/resources/boostrap/css/creative.min.css" rel="stylesheet"/>
        <script src="/resources/jquery-3.2.1.js"></script>
        <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
        <!-- jQuery -->

        <title>Home</title>
    </head>
    <body>
        <nav id="mainNav" class="navbar navbar-default navbar-fixed-top">
            <div class="container-fluid">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                        <span class="sr-only">Toggle navigation</span> Menu <i class="fa fa-bars"></i>
                    </button>
                        <%--시작되는 DB Lab Project 로고--%>
                    <a class="navbar-brand page-scroll" href="#page-top">DataBase Lab Project</a>
                </div>

                <!-- 기본적으로 이동하는 Sign UP Navigation Bar -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <a class="page-scroll" href="<spring:url value="/logout"/>">LOGOUT</a>
                        </li>
                        <li>
                            <a class="page-scroll" href="#services">Services</a>
                        </li>
                        <li>
                            <a class="page-scroll" href="#Category">Category</a>
                        </li>
                        <li>
                            <a class="page-scroll" href="#contact">Contact</a>
                        </li>
                        <li>
                            <a class="page-scroll" href="<spring:url value="/withdraw"/>">Withdraw</a>
                        </li>
                    </ul>
                </div>
                <!-- /.navbar-collapse -->
            </div>
            <!-- /.container-fluid -->
        </nav>


            <%--기본적인 프로젝트 환영 인사--%>
        <header>
            <div class="header-content" id="page-top">
                <div class="header-content-inner">
                    <h1 id="homeHeading">Welcome to the new site.</h1>
                    <hr/>
                    <p>
                        You've come to the site where you can get new movie information.</p>
                    <a href="#Category" class="btn btn-primary btn-xl page-scroll">More information</a>
                </div>
            </div>
        </header>





        <section class="no-padding" id="Category">
            <div class="container-fluid">
                <div class="row no-gutter popup-gallery">
                    <div class="col-lg-4 col-sm-6">
                        <a href="<spring:url value="/list"/>" class="portfolio-box">
                            <img src="/resources/boostrap/img/portfolio/thumbnails/1.jpg" class="img-responsive" alt=""/>
                            <div class="portfolio-box-caption">
                                <div class="portfolio-box-caption-content">
                                    <div class="project-category text-faded">
                                        Category
                                    </div>
                                    <div class="project-name">
                                        Board
                                    </div>
                                </div>
                            </div>
                        </a>
                    </div>
                    <div class="col-lg-4 col-sm-6">
                        <a href="/resources/boostrap/img/portfolio/fullsize/2.jpg" class="portfolio-box">
                            <img src="/resources/boostrap/img/portfolio/thumbnails/2.jpg" class="img-responsive" alt="">
                            <div class="portfolio-box-caption">
                                <div class="portfolio-box-caption-content">
                                    <div class="project-category text-faded">
                                        Category
                                    </div>
                                    <div class="project-name">
                                        Image
                                    </div>
                                </div>
                            </div>
                        </a>
                    </div>
                    <div class="col-lg-4 col-sm-6">
                        <a href="/resources/boostrap/img/portfolio/fullsize/3.jpg" class="portfolio-box">
                            <img src="/resources/boostrap/img/portfolio/thumbnails/3.jpg" class="img-responsive" alt="">
                            <div class="portfolio-box-caption">
                                <div class="portfolio-box-caption-content">
                                    <div class="project-category text-faded">
                                        Category
                                    </div>
                                    <div class="project-name">
                                        Video
                                    </div>
                                </div>
                            </div>
                        </a>
                    </div>
                </div>
            </div>
        </section>

        <aside class="bg-dark">
            <div class="container text-center">
                <div class="call-to-action">
                    <h2> Above image Click the Pictures ! Go To Service !</h2>
                    <br/>
                </div>
            </div>
        </aside>

            <%--홈페이지에 대한 설명--%>
        <section id="services">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <h2 class="section-heading">At Your Service</h2>
                        <hr class="primary">
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-lg-3 col-md-6 text-center">
                        <div class="service-box">
                            <i class="fa fa-4x fa-list-alt text-primary sr-icons"></i>
                            <h3>Look Board</h3>
                            <p class="text-muted">We will post the information you want to know.</p>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6 text-center">
                        <div class="service-box">
                            <i class="fa fa-4x fa-pencil text-primary sr-icons"></i>
                            <h3>Writing Image</h3>
                            <p class="text-muted">We use information from images to find the information you want!</p>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6 text-center">
                        <div class="service-box">
                            <i class="fa fa-4x fa-film text-primary sr-icons"></i>
                            <h3>Writing Video</h3>
                            <p class="text-muted">We use the information in the video to find the information you want!</p>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6 text-center">
                        <div class="service-box">
                            <i class="fa fa-4x fa-heart text-primary sr-icons"></i>
                            <h3>More Service...</h3>
                            <p class="text-muted">We are preparing more services!</p>
                        </div>
                    </div>
                </div>
            </div>
        </section>



            <%--부가적인 정보나 개발자들에 대한 정보--%>
        <section id="contact">
            <div class="container">
                <div class="row">
                    <div class="col-lg-8 col-lg-offset-2 text-center">
                        <h2 class="section-heading">Contact Developer</h2>
                        <hr class="primary"/>
                        <p>Thank you for visiting our homepage! I would be grateful if you could send any errors or suggestions to the developer.</p>
                    </div>
                    <div class="col-lg-4 text-center">
                    </div>
                    <div class="col-lg-4 text-center">
                        <i class="fa fa-envelope-o fa-3x sr-contact"></i>
                        <p><a href="mailto:your-email@your-domain.com">anyozcall@gmail.com</a></p>
                    </div>
                </div>
            </div>
        </section>


    </body>
</html>
