<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Student details</h1>

<table>
    <tr>
        <td>id :</td>
        <td>${requestScope["students"].id}</td>
    </tr>
    <tr>
        <td>Name :</td>
        <td>${requestScope["students"].name}</td>
    </tr>
    <tr>
        <td>Email :</td>
        <td>${requestScope["students"].email}</td>
    </tr>
    <tr>
        <td>Phone :</td>
        <td>${requestScope["students"].phoneNumber}</td>
    </tr>
    <tr>
        <td>Age :</td>
        <td>${requestScope["students"].age}</td>
    </tr>
</table>
</body>
</html>
