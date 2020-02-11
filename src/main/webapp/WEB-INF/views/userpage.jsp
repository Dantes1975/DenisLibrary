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
    <c:if test="${borrows != null}">
        <h3 class="table-title"> В Вашем пользовании находятся следующие книги </h3>
        <table class="table table-bordered">
            <thead class="thead-light">
            <tr>
                <th>BOOKNAME</th>
                <th>BORROWDATE</th>
                <th>RETURNDATE</th>
                <th>RETURN</th>
            </tr>
            </thead>
            <c:forEach items="${borrows}" var="borrow">
                <tr>
                    <td> ${borrow.book.bookname} </td>
                    <td> ${borrow.borrowDate} </td>
                    <td> ${borrow.returnDate} </td>
                    <td>
                        <form class="form_modified" action="<c:url value="/returnbook"/> " method="post">
                            <input type="hidden" name="bookid" value="${borrow.book.id}"/>
                            <input type="submit" class=" btn btn-press" name="action" value="return">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>

<c:if test="${sessionScope.authenticate.user.role == 'ADMIN'}">
    <br>
    <p><a class="btn btn-create" href="<c:url value="/createBookByAdmin"/> "> CREATE BOOK </a></p>
    <p><a class="btn btn-create" href="<c:url value="/create" /> "> CREATE USER </a></p>
    <br>
    <div class="form-message">
        <table class="table table-bordered">
            <thead class="thead-light">
            <tr>
                <th> ID</th>
                <th> LOGIN</th>
                <th> PROFILE ENABLE</th>
                <th> BLOCK/UNBLOCK</th>
                <th> DELETE USER</th>
            </tr>
            </thead>
            <c:forEach items="${authenticates}" var="authntic">
                <tr>
                    <c:if test="${authntic.id != sessionScope.authenticate.id}">
                        <td> ${authntic.id} </td>
                        <td> ${authntic.login} </td>
                        <td> ${authntic.profile_enable} </td>
                        <td>
                            <c:if test="${authntic.profile_enable=='ON'}">
                                <form class="form_modified" action="<c:url value="/off"/> " method="post">
                                    <input type="hidden" name="id" value="${authntic.id}"/>
                                    <input type="submit" class=" btn btn-press" name="action" value="Block">
                                </form>
                            </c:if>
                            <c:if test="${authntic.profile_enable=='OFF'}">
                                </form class="form_modified">
                                <form action="<c:url value="/on"/> " method="post">
                                    <input type="hidden" name="id" value="${authntic.id}"/>
                                    <input type="submit" class=" btn btn-press" name="action" value="Unblock"/>
                                </form>
                            </c:if>
                        </td>
                        <td>
                            <form class="form_modified" action="<c:url value="/delete"/> " method="post">
                                <input type="hidden" name="id" value="${authntic.id}"/>
                                <input type="hidden" name="adminid" value="${sessionScope.authenticate.id}"/>
                                <input type="submit" class=" btn btn-press" name="action" value="Delete"/>
                            </form>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
    </div>
</c:if>
<br>
<p><a class=" btn btn-create" href="<c:url value="/update"/> "> Update </a></p>
</div>

<div class="card-footer footer_container"></div>

</body>
</html>
