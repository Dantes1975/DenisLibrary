<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> update </title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="Start/images/icons/favicon.ico"/>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="Start/vendor/bootstrap/css/bootstrap.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="Start/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="Start/fonts/iconic/css/material-design-iconic-font.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="Start/vendor/animate/animate.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="Start/vendor/css-hamburgers/hamburgers.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="Start/vendor/animsition/css/animsition.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="Start/vendor/select2/select2.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="Start/vendor/daterangepicker/daterangepicker.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="Start/css/util.css">
    <link rel="stylesheet" type="text/css" href="Start/css/main.css">
    <!--===============================================================================================-->
</head>
<body>
<h1> Введите новые данные </h1><br>
<div class="limiter">
    <div class="container-login100" style="background-image: url('Start/images/bg-01.jpg');">
        <div class="wrap-login100">
            <form action="<c:url value="/update"/> " method="post" class="login100-form validate-form">
					<span class="login100-form-logo">
						<i class="zmdi zmdi-landscape"></i>
					</span>

                <span class="login100-form-title p-b-34 p-t-27">
						UPDATE
					</span>

                <div class="wrap-input100 validate-input" data-validate="Enter login and password">
                    <input class="input100" type="text" name="login" placeholder="Login">
                    <span class="focus-input100" data-placeholder="&#xf207;"></span>
                </div>

                <div class="wrap-input100 validate-input" data-validate="Enter password">
                    <input class="input100" type="password" name="password" placeholder="Password">
                    <span class="focus-input100" data-placeholder="&#xf191;"></span>
                </div>

                <div class="wrap-input100 validate-input" data-validate="Enter login and password">
                    <input class="input100" type="text" name="name" placeholder="Name">
                    <span class="focus-input100" data-placeholder="&#xf207;"></span>
                </div>

                <div class="wrap-input100 validate-input" data-validate="Enter login and password">
                    <input class="input100" type="text" name="surname" placeholder="Surname">
                    <span class="focus-input100" data-placeholder="&#xf207;"></span>
                </div>

                <div class="wrap-input100 validate-input" data-validate="Enter login and password">
                    <input class="input100" type="text" name="age" placeholder="Age">
                    <span class="focus-input100" data-placeholder="&#xf207;"></span>
                </div>

                <div class="wrap-input100 validate-input" data-validate="Enter login and password">
                    <input class="input100" type="email" name="email" placeholder="Email">
                    <span class="focus-input100" data-placeholder="&#xf207;"></span>
                </div>

                <div>
                    <input class="input100" type="hidden" name="id" value="${user.id}">
                </div>

                <div>
                    <input class="input100" type="hidden" name="profile"
                           value="${authenticate.profile_enable}">
                </div>

                <div class="container-login100-form-btn">
                    <button class="login100-form-btn" type="submit" name="action" value="update">
                        UPDATE
                    </button>
                </div>

            </form>
        </div>
    </div>
</div>


<div id="dropDownSelect1"></div>

<!--===============================================================================================-->
<script src="Start/vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
<script src="Start/vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
<script src="Start/vendor/bootstrap/js/popper.js"></script>
<script src="Start/vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
<script src="Start/vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
<script src="Start/vendor/daterangepicker/moment.min.js"></script>
<script src="Start/vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
<script src="Start/vendor/countdowntime/countdowntime.js"></script>
<!--===============================================================================================-->
<script src="Start/js/main.js"></script>


<br>
</body>
</html>
