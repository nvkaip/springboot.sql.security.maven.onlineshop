<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User database page</title>
</head>
<body>
<div style="text-align: center">
    <p>
        <button onclick="location.href='/admin/add/user'"> Add user</button>
        <button onclick="location.href='/products'"> Product List</button>
        <button onclick="location.href='/signout'"> Sign out</button>
    </p>
    <table align="center" border="1">
        <tr>
            <th>Email</th>
            <th>Password</th>
        </tr>
        <c:forEach var="user" items="${userList}">
            <tr>
                <td>${user.email}</td>
                <td>${user.password}</td>
                <td>
                    <form action="/admin/edit/user" method="get">
                        <input type="hidden" name="userId" value="${user.id}">
                        <button type="submit">edit</button>
                    </form>
                </td>
                <td>
                    <form action="/admin/delete/user" method="post">
                        <input type="hidden" name="userId" value="${user.id}">
                        <button type="submit">delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
