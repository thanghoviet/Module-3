<%--
  Created by IntelliJ IDEA.
  User: Thang.HoViet
  Date: 2/22/2021
  Time: 3:16 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>List Note</title>
    <link rel="icon" href=
            "https://media.geeksforgeeks.org/wp-content/cdn-uploads/gfg_200X200.png"
          type="image/x-icon">
</head>
<body>
<h1> Các Ghi Chú </h1>

<from action="" method="post">
    <select name="Category" id="Category">
        <option value=""></option>
    </select>
    <input type="search" value="Tiêu đề ">
    <input type="submit" value="Tìm kiếm">
    <table border="1" cellpadding="5">
        <tr>
            <th>STT</th>
            <th>Tiêu Đề</th>
            <th>Phân Loại</th>
            <th>Thời Gian</th>
        </tr>

        <c:forEach var="note" items="${listNote}"  >
            <tr>
                <td><c:out value="${note.id}"/></td>
                <td><c:out value="${note.title}"/></td>
                <td><c:out value="${note.content}"/></td>
                <td><c:out value="${note.datetime}"/></td>
                <td>
                    <a href="${pageContext.request.contextPath}/note?action=edit&id=${note.id}">Edit</a>
                    <a href="${pageContext.request.contextPath}/note?action=delete&id=${note.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</from>
</body>
</html>
