
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Create new product</title>
    <style>
        .message{
            color:green;
        }
    </style>
</head>
<body>
<h1>Create new product</h1>
<p>
    <c:if test='${requestScope["message"] != null}'>
        <span class="message">${requestScope["message"]}</span>
    </c:if>
</p>
<p>
    <a href="${pageContext.request.contextPath}/product">Back to product list</a>
</p>
<form method="post">
<fieldset>
<legend>Product information</legend>
<table>
<tr>
<td>name :</td>
<td><input type="text" name="name" id="name"></td>
</tr>
<tr>
    <td>Price :</td>
    <td><input type="text" name="price" id="price"></td>
</tr>
<tr>
    <td>Origin :</td>
    <td><input type="text" name="origin" id="origin"></td>
</tr>
<tr>
    <td>QuantityVailable :</td>
    <td><input type="text" name="quantityVailable" id="quantityVailable"></td>
</tr>
</table>
    <input type="submit" value="add" placeholder="ADD" >
</fieldset>
</form>
</body>
</html>
