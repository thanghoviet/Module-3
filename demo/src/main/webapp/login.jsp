<%--
  Created by IntelliJ IDEA.
  User: Thang.HoViet
  Date: 2/1/2021
  Time: 11:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/login" method="post" >
    <input type="text" name="name" placeholder="name">
    <input type="password" name="password" placeholder="password">
    <input type="submit" value="login">
</form>
</body>
</html>
