<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>
        Admin page
    </title>
    <style>
        table {
            width: 90%; /* Ширина таблицы */
            background: white; /* Цвет фона таблицы */
            color: white; /* Цвет текста */
            border-spacing: 1px; /* Расстояние между ячейками */
        }

        td, th {
            background: green; /* Цвет фона ячеек */
            padding: 5px; /* Поля вокруг текста */
        }
    </style>

</head>
<body>

<form action="<c:url value="/logout"/> " method="post">
    <input type="submit" name="action" value="logout">
</form>

<h1> ${authenticate.login} </h1> <br>

<h1> MESSAGE </h1>
<form action="message" method="post">
    <input type="hidden" name="sender" value="${user.id}">
    Введите адресата (admin 1 or 2) <input type="text" name="recipient">
    Введите текст <input type="text" name="text">
    <input type="hidden" name="id" value="0">
    <button type="submit" name="action" value="send">SEND</button>
</form>

<form action="message" method="post">
    <input type="hidden" name="sender" value="0">
    <input type="hidden" name="recipient" value="${user.id}">
    <input type="hidden" name="id" value="0">
    <button type="submit" name="action" value="messages">MESSAGES</button>
</form>
<p> Messages </p>
<table>
    <tr>
        <th> From</th>
        <th> Text</th>
        <th> Delete</th>
    </tr>
    <c:forEach items="${mymessages}" var="message">
        <tr>
            <td> ${message.sender} </td>
            <td> ${message.text} </td>
            <td>
                <form action="delete" method="post">
                    <input type="hidden" name="sender" value="0">
                    <input type="hidden" name="type" value="0">
                    <input type="hidden" name="id" value="${message.id}"/>
                    <input type="submit" name="action" value="delete"/>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>


<p><a href="<c:url value="/createBookByAdmin"/> "> CREATE BOOK </a></p>
<p><a href="<c:url value="/create" /> "> CREATE USER </a></p>
<br>

<table>
    <tr>
        <th> ID</th>
        <th> LOGIN</th>
        <th> PASSWORD</th>
        <th> PROFILE ENABLE</th>
        <th> OFF</th>
        <th> ON</th>
        <th> DEL</th>
    </tr>
    <c:forEach items="${authenticates}" var="authntic">
        <tr>
            <td> ${authntic.id} </td>
            <td> ${authntic.login} </td>
            <td> ${authntic.password} </td>
            <td> ${authntic.profile_enable} </td>
            <td>
                <form action="<c:url value="/off"/> " method="post">
                    <input type="hidden" name="id" value="${authntic.id}"/>
                    <input type="hidden" name="sender" value="${sessionScope.authenticate.id}"/>
                    <select name="type">
                        <option>block</option>
                        <option>off</option>
                    </select>
                    <input type="submit" name="action" value="off">
                </form>
            </td>
            <td>
                </form>
                <form action="<c:url value="/on"/> " method="post">
                    <input type="hidden" name="id" value="${authntic.id}"/>
                    <input type="hidden" name="sender" value="${sessionScope.authenticate.id}"/>
                    <input type="submit" name="action" value="on"/>
                </form>
            </td>
            <td>
                <form action="<c:url value="/delete"/> " method="post">
                    <input type="hidden" name="id" value="${authntic.id}"/>
                    <input type="hidden" name="sender" value="${authenticate.id}"/>
                    <input type="hidden" name="type" value="0">
                    <input type="submit" name="action" value="delete"/>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<br>

</body>
</html>