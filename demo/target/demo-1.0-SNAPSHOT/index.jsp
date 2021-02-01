<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h3>MENU</h3>
<ul>
    <li>Trang chu</li>
    <li>San Pham</li>

    <c:forEach items='${requestScope["studentList"]}' var="student">
    <li>
        <a href="${pageContext.request.contextPath}/view?id=${student.id}">
                ${student.name}
        </a>
    </li>
    </c:forEach>



</ul>
</body>
</html>