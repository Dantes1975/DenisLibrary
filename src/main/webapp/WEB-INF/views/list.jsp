<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>list</title>
    <style>
        table {
            width: 90%;
            background: white;
            color: white;
            border-spacing: 1px;
        }

        td, th {
            background: olivedrab;
            padding: 5px;
        }
    </style>
</head>
<body>
<form action="<c:url value="/logout"/> " method="get">
    <input type="submit" name="action" value="logout">
</form>

<h1> Вы находитесь на гостевой странице </h1> <br>


<h1> Список книг нашей библиотеки </h1> <br>

<table>
    <tr>
        <th> ID</th>
        <th> BOOK NAME</th>
        <th> AUTHOR</th>
        <th> GENRE</th>
        <th> STOCK</th>
    </tr>
    <c:forEach items="${listbooks}" var="book">
        <tr>
            <td>${book.id} </td>
            <td>${book.bookname} </td>
            <td>${book.author.name} ${book.author.surname}</td>
            <td>${book.genre.genrename}</td>
            <td>${book.stock}</td>
        </tr>
    </c:forEach>

</table>

<br>
<p><a href="<c:url value="/registration"/> "> Registration </a></p>
</body>
</html>
