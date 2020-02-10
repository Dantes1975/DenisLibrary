<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>create</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" type="image/png" href="<c:url value="/resources/images/icons/favicon.ico" />" >
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/vendor/bootstrap/css/bootstrap.min.css" />" >
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/fonts/font-awesome-4.7.0/css/font-awesome.min.css" />" >
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/fonts/iconic/css/material-design-iconic-font.min.css" />" >
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/vendor/animate/animate.css" />" >
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/vendor/css-hamburgers/hamburgers.min.css" />" >
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/vendor/animsition/css/animsition.min.css" />" >
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/vendor/select2/select2.min.css" />" >
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/vendor/daterangepicker/daterangepicker.css" />" >
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/util.css" />" >
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css" />" >
</head>
<body>

<div class="limiter">
    <div class="container-login100" style="background-image: url('Start/images/bg-01.jpg');">
        <div class="wrap-login100">
            <form action="<c:url value="/createBookByAdmin"/> " method="post">
					<span class="login100-form-logo">
						<i class="zmdi zmdi-landscape"></i>
					</span>

                <span class="login100-form-title p-b-34 p-t-27">
						CREATE BOOK
					</span>

                <div class="wrap-input100 validate-input" >
                    <input class="input100" type="hidden" name="id" value="${book.id}">
                    <input class="input100" type="text" name="bookname" placeholder="Bookname">
                    <input class="input100" type="hidden" name="stock" value="${book.stock}">
                    <span class="focus-input100" data-placeholder="&#xf207;"></span>
                </div>

                <div class="wrap-input100 validate-input" >
                    <input class="input100" type="text" name="author.name" placeholder="Author name">
                    <span class="focus-input100" data-placeholder="&#xf191;"></span>
                </div>

                <div class="wrap-input100 validate-input" >
                    <input class="input100" type="text" name="author.surname" placeholder="Author surname">
                    <span class="focus-input100" data-placeholder="&#xf207;"></span>
                </div>

                <div class="wrap-input100 validate-input" >
                    <input class="input100" type="text" name="genre.genrename" placeholder="Genre">
                    <span class="focus-input100" data-placeholder="&#xf207;"></span>
                </div>

                <div class="wrap-input100 validate-input" >
                    <input class="input100" type="hidden" name="id" value="${bookimage.id}">
                    <input class="input100" type="file" name="filename" placeholder="Description">
                    <span class="focus-input100" data-placeholder="&#xf207;"></span>
                </div>

                <div class="container-login100-form-btn">
                    <button class="login100-form-btn" type="submit" name="action" value="create">
                        CREATE BOOK
                    </button>
                </div>

            </form>
        </div>
    </div>
</div>


<div id="dropDownSelect1"></div>

<script src="<c:url value="/resources/vendor/jquery/jquery-3.2.1.min.js" />"></script>
<script src="<c:url value="/resources/vendor/animsition/js/animsition.min.js" />"></script>
<script src="<c:url value="/resources/vendor/bootstrap/js/popper.js" />"></script>
<script src="<c:url value="/resources/vendor/bootstrap/js/bootstrap.min.js" />"></script>
<script src="<c:url value="/resources/vendor/select2/select2.min.js" />"></script>
<script src="<c:url value="/resources/daterangepicker/moment.min.js" />"></script>
<script src="<c:url value="/resources/daterangepicker/daterangepicker.js" />"></script>
<script src="<c:url value="/resources/vendor/countdowntime/countdowntime.js" />"></script>
<script src="<c:url value="/resources/js/main.js" />"></script>

</body>
</html>
