<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product adding page</title>
</head>
<body>
<div style="text-align: center;">
    <h4>
        ${error}
    </h4>
    <form action="${action}" method="post">
        <p> Name <input name="name" type="text"
                        value="${name}"></p>
        <p> Description <input name="description" type="text"
                        value="${description}"></p>
        <p> Price <input name="price" type="number"
                        step="0.01" min="0.01" value="${price}"></p>
        <input type="hidden" name="productId" value="${productId}">
        <input type="submit" value="Submit">
        <button><a href="/products">Product List</a></button>
    </form>
</div>
</body>
</html>
