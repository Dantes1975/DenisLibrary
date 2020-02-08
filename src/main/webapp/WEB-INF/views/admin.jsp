<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>
        Admin page
    </title>
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

        .body-container {
            position: relative;
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

        .form-message {
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
    <h3 class="header_title"> ${sessionScope.authenticate.login} </h3>
    <form class="form_container" action="<c:url value="/logout"/> " method="get">
        <input type="submit" class=" btn btn-light" name="action" value="logout">
    </form>
</div>

<div class="card-body" class="body-container">
    <div>
        <div class="form-message">
            <h2> Message </h2>
            <form action="<c:url value="/message"/> " method="post">
                <div class="form-group">
                    <input type="hidden" class="form-control form-input" name="sender" value="${sessionScope.authenticate.id}">
                </div>
                <div class="form-group">
                    <label for="message_recipient">Введите адресата (admin 1 or 2)</label>
                    <input type="text" class="form-control form-input" name="recipient" id="message_recipient">
                </div>
                <div class="form-group">
                    <label for="message_text">Введите текст</label>
                    <input type="text" class="form-control form-input" name="text" id="message_text">
                </div>
                <button type="submit" class="btn btn-light btn-message" name="action" value="send">
                    SEND
                </button>
            </form>
        </div>
        <br>
        <c:if test="${mymessages != null}">
            <div class="form-message">
                <h2 class="table-title"> Messages </h2>
                <table class="table table-bordered">
                    <thead class="thead-light">
                    <tr>
                        <th> From</th>
                        <th> Text</th>
                        <th> Delete</th>
                    </tr>
                    </thead>
                    <c:forEach items="${mymessages}" var="message">
                        <tr>
                            <td> ${message.sender} </td>
                            <td> ${message.text} </td>
                            <td>
                                <form class="form_modified" action="<c:url value="/deleteMessage"/> " method="post">
                                    <input type="hidden" name="recipient" value="${sessionScope.authenticate.id}">
                                    <input type="hidden" name="id" value="${message.id}"/>
                                    <input type="submit" name="action" class=" btn btn-press" value="delete"/>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </c:if>
    </div>
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
                <th> PASSWORD</th>
                <th> PROFILE ENABLE</th>
                <th> OFF</th>
                <th> ON</th>
                <th> DEL</th>
            </tr>
            </thead>
            <c:forEach items="${authenticates}" var="authntic">
                <tr>
                    <td> ${authntic.id} </td>
                    <td> ${authntic.login} </td>
                    <td> ${authntic.password} </td>
                    <td> ${authntic.profile_enable} </td>
                    <td>

                            <form class="form_modified" action="<c:url value="/off"/> " method="post">
                                <input type="hidden" name="id" value="${authntic.id}"/>
                                <input type="hidden" name="adminid" value="${sessionScope.authenticate.id}"/>
                                <select name="type" class=" btn btn-press">
                                    <option>block</option>
                                    <option>off</option>
                                </select>
                                <input type="submit" class=" btn btn-press" name="action" value="off">
                            </form>

                    </td>
                    <td>
                        </form class="form_modified">
                        <form action="<c:url value="/on"/> " method="post">
                            <input type="hidden" name="id" value="${authntic.id}"/>
                            <input type="hidden" name="adminid" value="${sessionScope.authenticate.id}"/>
                            <input type="submit" class=" btn btn-press" name="action" value="on"/>
                        </form>
                    </td>
                    <td>
                        <form class="form_modified" action="<c:url value="/delete"/> " method="post">
                            <input type="hidden" name="id" value="${authntic.id}"/>
                            <input type="hidden" name="adminid" value="${sessionScope.authenticate.id}"/>
                            <input type="hidden" name="type" value="0">
                            <input type="submit" class=" btn btn-press" name="action" value="delete"/>
                        </form>
                    </td>

                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<div class="card-footer footer_container"></div>

</body>
</html>
