<%--
  Created by IntelliJ IDEA.
  User: Thang.HoViet
  Date: 2/2/2021
  Time: 9:14 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>view product</title>
</head>
<body>
<h1>Product details</h1>
<p>
    <a href="${pageContext.request.contextPath}/product">Back to customer list</a>
</p>
<table>
    <tr>
        <td>id </td>
    <td>${requestScope["product"].id}</td>
    </tr>
    <tr>
        <td>Name</td>
        <td>${requestScope["product"].name}</td>
    </tr>
    <tr>
        <td>Price</td>
        <td>${requestScope["product"].price}</td>
    </tr>
    <tr>
        <td>Origin</td>
        <td>${requestScope["product"].quantityVailable}</td>
    </tr>
</table>
</body>
</html>
