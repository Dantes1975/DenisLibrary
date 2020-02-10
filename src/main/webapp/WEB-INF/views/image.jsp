<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>image</title>
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
    <form class="form_container" action="<c:url value="/home"/> " method="get">
        <input type="submit" class=" btn btn-light" name="action" value="home">
    </form>
</div>
<div class="card-body body-container">
    <p align="center">
        <img src="<c:url value="/loadImage/${book.id}"/> "
             width="15%"
             height="45%"
             border="3">
    </p>
    <c:if test="${sessionScope.authenticate.user.role == 'ADMIN'}">
        <c:if test="${borrows != null}">
            <h3 class="table-title"> Данная книга находится в пользовании </h3>
            <table class="table table-bordered">
                <thead class="thead-light">
                <tr>
                    <th>USER</th>
                    <th>BORROWDATE</th>
                    <th>RETURNDATE</th>
                </tr>
                </thead>
                <c:forEach items="${borrows}" var="borrow">
                    <tr>
                        <td> ${borrow.user} </td>
                        <td> ${borrow.borrowDate} </td>
                        <td> ${borrow.returnDate} </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>

        </form class="form_modified">
        <form action="<c:url value="/updateBook/${book.id}"/> " method="get">
            <input type="submit" class=" btn btn-press" name="action" value="Update"/>
        </form>
    </c:if>
</div>
</body>
</html>
