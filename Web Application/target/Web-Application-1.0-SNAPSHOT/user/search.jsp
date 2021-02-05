<%--
  Created by IntelliJ IDEA.
  User: Thang.HoViet
  Date: 2/4/2021
  Time: 9:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Search by country</title>
</head>
<body>
<h6>Enter the country you want to find</h6>

<form action="/users">
    <input type="hidden" name="action" value="search">
    <input type="text" name="countrys" id="countrys" size="20">
    <input type="submit" value="Search">
</form>
<div align="center">
    <c:if test="${mess != null}">
    <table border="1" cellpadding="5">
        <caption><h2>${mess}</h2></caption>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="user" items="${listUser}">
                <tr>
                    <td><c:out value="${user.id}"/></td>
                    <td><c:out value="${user.name}"/></td>
                    <td><c:out value="${user.email}"/></td>

                    <td>
                        <a href="${pageContext.request.contextPath}/users?action=edit&id=${user.id}">Edit</a>
                        <a href="${pageContext.request.contextPath}/users?action=delete&id=${user.id}">Delete</a>
                    </td>
                </tr>
        </c:forEach>
    </table>
    </c:if>
</div>
</body>
</html>
