<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product database page</title>
</head>
<body>
<div style="text-align: center">
    <p>
    <form method="get">
        <security:authorize access="hasAnyRole('ADMIN', 'MANAGER')">
            <input type="submit" value="Add product" formaction="/product/add">
        </security:authorize>
        <security:authorize access="hasRole('ADMIN')">
            <input type="submit" value="Users List" formaction="/admin/users">
        </security:authorize>
        <security:authorize access="hasRole('CUSTOMER')">
            <input type="submit" value="To cart" formaction="/buy/basket">
        </security:authorize>
        <input type="submit" value="Sign out" formaction="/signout">
    </form>
    </p>
    <table align="center" border="1">
        <tr>
            <th>Name</th>
            <th>Description</th>
            <th>Price</th>
        </tr>
        <c:forEach var="product" items="${productList}">
            <tr>
                <td>${product.name}</td>
                <td>${product.description}</td>
                <td>${product.price}</td>
                <td>
                    <security:authorize access="hasAnyRole('ADMIN', 'MANAGER')">
                        <form action="/product/edit" method="get">
                            <input type="hidden" name="productId" value="${product.id}">
                            <input type="submit" value="edit">
                        </form>
                    </security:authorize>
                    <security:authorize access="hasRole('CUSTOMER')">
                        <form action="/buy/add/product" method="post">
                            <input type="hidden" name="productId" value="${product.id}">
                            <input type="submit" value="add to cart">
                        </form>
                    </security:authorize>
                </td>
                <td>
                    <security:authorize access="hasAnyRole('ADMIN', 'MANAGER')">
                        <form action="/product/delete" method="post">
                            <input type="hidden" name="productId" value="${product.id}">
                            <input type="submit" value="delete">
                        </form>
                    </security:authorize>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
