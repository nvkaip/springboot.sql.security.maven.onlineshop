<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login page</title>
</head>
<body>
<div style="text-align: center">
    <p>Just welcome ))</p>
    ${message}
    <form action="/login" method="post" modelAttribute="user">
        <p>Email <input name="email" type="email"></p>
        <p>Password <input name="password" type="password"></p>
        <button type="submit">Submit</button>
    </form>
</div>
</body>
</html>
