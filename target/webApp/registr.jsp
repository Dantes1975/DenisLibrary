
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@page import="bean.User" %>--%>
<%--<%@page import="bean.Roles" %>--%>
<%--<%@page import="LogServlet" %>--%>
<%--<%@page import="LogoutServlet" %>--%>
<%--<%@page import="repository.UserDAO" %>--%>
<html>
<head>
    <title> Registre page</title>
</head>
<body>
<h1>Вход в систему</h1> <br>
<h1> Введите имя и пароль </h1> <br>

<form action="registr" method="post">

    <input type="text" required placeholder="login" name="login"><br>
    <input type="password" required placeholder="password" name="password"><br><br>
    <input class="button" type="submit" name="action" value="registr">

</form>
</body>
</html>
