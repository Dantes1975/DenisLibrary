<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 10.02.2020
  Time: 19:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Loggin</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <style>
        body {
            background: #fff;
            width: 100%;
            margin: 0;
        }

        .header_container {
            width: 100%;
            min-height: 50px;
            background-color: #76CA41;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .footer_container {
            flex: 0 0 auto;
            min-height: 50px;
            width: 100%;
            background-color: #76CA41;
        }


        .header_title {
            color: rgb(248, 249, 250);
            margin: 0;
        }

        .body-container {
            position: relative;
        }

        .form_container {
            max-width: 300px;
            height: 100%;
            margin: 0;
        }

        .btn-message {
            border: 2px solid #76CA41;
            color: #76CA41;
            width: 100px;
        }

        .btn-message:hover {
            background-color: #76CA41;
            border: 2px solid #76CA41;
            color: #fff;
        }

        .btn-create {
            border: 2px solid #76CA41;
            color: #76CA41;
            width: 150px;
        }

        .btn-create:hover {
            background-color: #76CA41;
            border: 2px solid #76CA41;
            color: #fff;
        }

        .btn-press {
            background-color: #6BBC49;
            border: 2px solid #fff;
            color: #fff;
        }

        .btn-press:hover {
            background-color: #fff;
            border: 2px solid #6BBC49;
            color: #6BBC49;
        }

        .form-input {
            max-width: 300px;
        }

        .form-input:hover {
            border: 2px solid #76CA41;
        }

        .form-control:focus {
            border: 2px solid #76CA41;
            box-shadow: 0 0 0 0.01rem #76CA41;
        }

        .div-container {
            background-color: #F9F9F9;
            padding: 15px;
        }

        .table-title {
            margin-bottom: 15px;
        }

        .form_modified {
            margin: 0;
        }
    </style>
</head>
<body>
<c:if test="${error != null}">
    <h3 style="color:red;">* ${error}</h3>
</c:if>

<div class="card-body body-container">
    <div class="div-container">
        <h2> Login </h2>
        <form action="<c:url value="/registration" /> " method="post">
            <div class="form-group">
                <input class="input100" type="text" name="login" placeholder="Login">
            </div>
            <div class="form-group">
                <input class="input100" type="password" name="password" placeholder="Password">
            </div>
            <div class="form-group">
                <input class="input100" type="text" name="name" placeholder="Name">
            </div>
            <div class="form-group">
                <input class="input100" type="text" name="surname" placeholder="Surname">
            </div>
            <div class="form-group">
                <input class="input100" type="text" name="age" placeholder="Age">
            </div>
            <div class="form-group">
                <input class="input100" type="email" name="email" placeholder="Email">
            </div>
            <button type="submit" class="btn btn-light btn-message" name="action" value="login">
                LOGIN
            </button>
        </form>
    </div>

</div>
</body>
</html>
