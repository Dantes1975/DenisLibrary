<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>borrows</title>
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
<form action="logout" method="post">
    <input type="submit" name="action" value="logout">
</form>

<p> Уважаемый ${sessionScope.authenticate.login} !!! </p>
<p> В Вашем пользовании находятся следующие книги </p>
<table>
    <tr>
        <th>BOOKID</th>
        <th>BORROWDATE</th>
        <th>RETURNDATE</th>
        <th>RETURN</th>
    </tr>
    <c:forEach items="${sessionScope.borrows}" var="borrow">
        <tr>
            <td> ${borrow.book.bookname} </td>
            <td> ${borrow.borrowDate} </td>
            <td> ${borrow.returnDate} </td>
            <td>
                <form action="return" method="post">
                    <input type="hidden" name="bookid" value="${borrow.book.id}"/>
                    <input type="hidden" name="userid" value="${sessionScope.user.id}"/>
                    <input type="hidden" name="days" value="0"/>
                    <input type="submit" name="action" value="return">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<form action="borrows" method="post">
    <input type="hidden" name="bookid" value="0"/>
    <input type="hidden" name="userid" value="${sessionScope.user.id}"/>
    <input type="hidden" name="days" value="0"/>
    <input type="submit" name="action" value="borrows">
</form>
</body>
</html>
