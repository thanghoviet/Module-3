<%@ page import="com.example.list_Of_Customers.Customer" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <style>
         td {
            border: 1px solid red;
        }
    </style>
</head>
<body>


<table >
    <caption>Danh Sách Khách Hàng</caption>
    <tr>
        <th>Tên</th>
        <th>Ngày Sinh</th>
        <th>Địa chỉ</th>
        <th>Ảnh</th>
    </tr>
    <c:forEach items='${requestScope["arrayList"]}' var = "i" >
        <tr>
            <td>${i.name}</td>
            <td>${i.birthday}</td>
            <td>${i.andress}</td>
            <td>
                <img src="<%=request.getContextPath()%>/image/${i.image}" width="50px" height="50px">
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>