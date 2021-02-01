<%--
  Created by IntelliJ IDEA.
  User: Thang.HoViet
  Date: 1/30/2021
  Time: 10:27 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View customer</title>
</head>
<body>
<h1>Customer details</h1>
<p>
    <a href="/customers">Back to customer list</a>
</p>
<table>
    <tr>
        <td>Name: </td>
        <td>${requestScope["customer"].name}</td>
    </tr>
    <tr>
        <td>Email: </td>
        <td>${requestScope["customer"].email}</td>
    </tr>
    <tr>
        <td>Address: </td>
        <td>${requestScope["customer"].address}</td>
    </tr>
</table>
</body>
</html>
