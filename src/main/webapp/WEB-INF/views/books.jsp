<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>books</title>
    <style>
        table {
            width: 90%; /* Ширина таблицы */
            background: white; /* Цвет фона таблицы */
            color: white; /* Цвет текста */
            border-spacing: 1px; /* Расстояние между ячейками */
        }

        td, th {
            background: olivedrab; /* Цвет фона ячеек */
            padding: 5px; /* Поля вокруг текста */
        }
    </style>
</head>
<body>
<form action="logout" method="post">
    <input type="submit" name="action" value="logout">
</form>

<h1> ${sessionScope.authenticate.login} </h1> <br>
<h1> YOUR STATUS is ${sessionScope.authenticate.profile_enable} </h1>

<h1> MESSAGE </h1>
<form action="<c:url value="" /> " method="post">
    <input type="hidden" name="sender" value="${sessionScope.user.id}">
    Введите адресата (admin 1 or 2) <input type="text" name="recipient">
    Введите текст <input type="text" name="text">
    <input type="hidden" name="id" value="0">
    <button type="submit" name="action" value="send">SEND</button>
</form>

<form action="message" method="post">
    <input type="hidden" name="sender" value="0">
    <input type="hidden" name="recipient" value="${sessionScope.user.id}">
    <input type="hidden" name="id" value="0">
    <button type="submit" name="action" value="messages">MESSAGES</button>
</form>

<c:if test="${sessionScope.mymessages != null}">
    <table>
        <tr>
            <th> From</th>
            <th> Text</th>
            <th> Delete</th>
        </tr>
        <c:forEach items="${sessionScope.mymessages}" var="message">
            <tr>
                <td> ${message.sender} </td>
                <td> ${message.text} </td>
                <td>
                    <form action="deleteServlet" method="post">
                        <input type="hidden" name="sender" value="0">
                        <input type="hidden" name="recipient" value="${sessionScope.user.id}">
                        <input type="hidden" name="text" value="message">
                        <input type="hidden" name="id" value="${message.id}"/>
                        <input type="submit" name="action" value="delete"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<c:if test="${sessionScope.authenticate.profile_enable == 'ON'}">
    <h1> Список книг нашей библиотеки </h1> <br>

    <table>
        <tr>
            <th>ID</th>
            <th>BOOK NAME</th>
            <th>AUTHOR</th>
            <th>GENRE</th>
            <th>STOCK</th>
            <th>DESCRIPTION</th>
            <th>TAKE</th>
        </tr>
        <c:forEach items="${sessionScope.listbooks}" var="book">
            <tr>
                <td>${book.id} </td>
                <td>${book.bookname} </td>
                <td>${book.author.name} ${book.author.surname}</td>
                <td>${book.genre.genrename}</td>
                <td>${book.stock}</td>
                <td>
                    <form action="<c:url value="/description"/> " method="post">
                        <input type="hidden" name="bookid" value="${book.id}">
                        <input type="submit" name="action" value="description">
                    </form>
                </td>
                <td>
                    <form action="<c:url value="/takebook"/> " method="post">
                        <input type="hidden" name="bookid" value="${book.id}"/>
                        <input type="hidden" name="userid" value="${user.id}"/>
                        days <select name="days">
                        <option>3</option>
                        <option>7</option>
                        <option>10</option>
                    </select>
                        <input type="submit" name="action" value="take"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

    <br>
    <form action="<c:url value="/listbooks"/> " method="post">
        <input type="hidden" name="bookid" value="0"/>
        <input type="hidden" name="userid" value="${sessionScope.user.id}"/>
        <input type="hidden" name="days" value="0"/>
        <input type="submit" name="action" value="books">
    </form>

    <c:if test="${sessionScope.borrows != null}">
       <p> В Вашем пользовании находятся следующие книги </p>
        <table>
            <tr>
                <th>BOOKMAME</th>
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
                        <form action="<c:url value="/returnbook"/> " method="post">
                            <input type="hidden" name="bookid" value="${borrow.book.id}"/>
                            <input type="hidden" name="userid" value="${user.id}"/>
                            <input type="hidden" name="days" value="0"/>
                            <input type="submit" name="action" value="return">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
<%--    <form action="borrows" method="post">--%>
    <%--        <input type="hidden" name="bookid" value="0"/>--%>
    <%--        <input type="hidden" name="userid" value="${sessionScope.user.id}"/>--%>
    <%--        <input type="hidden" name="days" value="0"/>--%>
    <%--        <input type="submit" name="action" value="borrows">--%>
    <%--    </form>--%>
</c:if>

<c:if test="${sessionScope.authenticate.profile_enable == 'OFF'}">
    <p> Вы заблокированы, обратитесь к администратору </p>
</c:if>

<br>
<p><a href="<c:url value="/update"/> "> Update </a></p>
</body>
</html>
