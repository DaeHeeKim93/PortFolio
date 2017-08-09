<%--사진 페이지--%>
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
    <link href="/resources/boostrap/boardtype/startbootstrap-blog-post-gh-pages/css/blog-post.css" rel="stylesheet"/>
    <link rel="stylesheet" href="/resources/css/button.css"/>
    <link rel="stylesheet" href="/resources/css/image.css"/>
    <script src="/resources/javascript/image.js"></script>

    <!-- jQuery -->
    <script src="/resources/boostrap/vendor/jquery/jquery.min.js"></script>
    <script src="/resources/jquery-3.2.1.js"></script>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script>
        $(function(){
        $("#file_button").change(function(){
        readURL(this);
        })
        });
        function readURL(input){
        if(input.files && input.files[0]){
        var reader = new FileReader();
        reader.onload = function(e){;
        $('#imageBoard').attr('src', e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
        }
        }
    </script>
    <script src="/resources/javascript/video_update.js"></script>
</head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-xs-2"></div>
                <div class="col-xs-8">
                    <image id="imageBoard" name="imageBoard" width="640" height="480" style="overflow:scroll">

                    </image>
                </div>
                <div class="col-xs-2"></div>
            </div>
            <div class="row">
                <form id="testform" method="post" enctype="multipart/form-data">
                    <div class="col-xs-3"></div>
                    <div class="col-xs-3"><input type="file" id="file_button" name="file_button" multipleclass='file_input_hidden'/></div>
                    <div class="col-xs-2"><input type="button" onclick="on_submit(document.getElementById('imageBoard').src)" value="제출"/></div>
                    <div class="col-xs-5"></div>
                    <!--  이미지첨부 버튼 클릭시 showImageHandler() 실행 -->
                </form>
            </div>
        </div>
    </body>
</html>
