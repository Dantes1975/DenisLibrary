<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>books</title>
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
<div class="card-header header_container">
    <h3 class="header_title"> Hello ${sessionScope.authenticate.login} </h3>
    <form class="form_container" action="<c:url value="/home"/> " method="get">
        <input type="submit" class=" btn btn-light" name="action" value="home">
    </form>
    <form class="form_container" action="<c:url value="/message"/> " method="get">
        <input type="submit" class=" btn btn-light" name="action" value="messages">
    </form>
    <form class="form_container" action="<c:url value="/userpage"/> " method="get">
        <input type="submit" class=" btn btn-light" name="action" value="kabinet">
    </form>
    <form class="form_container" action="<c:url value="/logout"/> " method="get">
        <input type="submit" class=" btn btn-light" name="action" value="logout">
    </form>
</div>

<div class="card-body body-container">
    <div class="div-container">
        <c:if test="${sessionScope.authenticate.profile_enable == 'ON'}">
            <h3 class="table-title"> Список книг нашей библиотеки</h3>
            <table class="table table-bordered">
                <thead class="thead-light">
                <tr>
                    <th>ID</th>
                    <th>BOOK NAME</th>
                    <th>AUTHOR</th>
                    <th>GENRE</th>
                    <th>STOCK</th>
                    <th>DESCRIPTION</th>
                    <th>TAKE</th>
                </tr>
                </thead>
                <c:forEach items="${listbooks}" var="book">
                    <tr>
                        <td>${book.id} </td>
                        <td>${book.bookname} </td>
                        <td>${book.author.name} ${book.author.surname}</td>
                        <td>${book.genre.genrename}</td>
                        <td>${book.stock}</td>
                        <td>
                            <form class="form_modified" action="<c:url value="/description"/> " method="post">
                                <input type="hidden" name="bookid" value="${book.id}">
                                <input type="submit" class=" btn btn-press" name="action" value="description">
                            </form>
                        </td>
                        <td>
                            <form class="form_modified" action="<c:url value="/takebook"/> " method="post">
                                <input type="hidden" name="bookid" value="${book.id}"/>
                                <input type="hidden" name="userid" value="${sessionScope.authenticate.id}"/>
                                days
                                <select name="days" class="btn btn-press">
                                    <option>3</option>
                                    <option>7</option>
                                    <option>10</option>
                                </select>
                                <input type="submit" class=" btn btn-press" name="action" value="take"/>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </div>

    <c:if test="${sessionScope.authenticate.profile_enable == 'OFF'}">
        <h3> Вы заблокированы, обратитесь к администратору </h3>
    </c:if>
    <br>

</div>

<div class="card-footer footer_container"></div>

</body>
</html>