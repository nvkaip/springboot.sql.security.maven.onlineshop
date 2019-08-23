<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration page</title>
</head>
<body>
<div style="text-align: center;">
    <h4>
        ${error}
    </h4>
    <form action="${action}" method="post">
        <p> Email <input name="email" type="email"
                         value="${email}"></p>
        <p> Access name <select data-selected="${name}" name="accessRole" >
                <option value="ROLE_MANAGER">manager</option>
                <option value="ROLE_CUSTOMER">customer</option>
                <option value="ROLE_ADMIN">admin</option>
        </select></p>
        <p> Password <input name="password" type="password"></p>
        <p> Re-enter password <input name="rpassword" type="password"></p>
        <input type="hidden" name="userId" value="${userId}">
        <input type="submit" value="Submit">
        <button><a href="/admin/users">Users List</a></button>
    </form>
</div>
</body>
</html>
