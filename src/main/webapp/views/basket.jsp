<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User basket</title>
</head>
<body>
<table align="center" border="1">
    <tr>
        <th>Name</th>
        <th>Price</th>
        <th>Quantity</th>
        <th>Sum</th>
    </tr>
    <c:forEach var="product" items="${productList}" varStatus="loop">
        <tr>
            <td>${product.name}</td>
            <td>${product.price}</td>
            <td></td>
            <td></td>
            <td>
                <form action="/buy/remove/product" method="post">
                    <input type="hidden" name="productId" value="${product.id}">
                    <input type="submit" value="remove">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<div style="text-align: center">
    <form method="get">
        <input type="submit" value="Make order" formaction="/order/make">
        <input type="submit" value="Sign out" formaction="/signout">
    </form>
</div>
</body>
</html>
