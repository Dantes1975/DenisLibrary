<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 01.12.2019
  Time: 18:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>image</title>
    <style>
        body {
            background: #c7b39b url(Start/images/bg-01.jpg);
        }
    </style>
</head>
<body>
<div style="background-image: url('Start/images/bg-01.jpg');">
    <p align="center">
        <img src="<c:url value="/loadImage/${book.id}"/> "
             width="30%"
             height="90%"
             border="3">
    </p>
</div>
</body>
</html>
