<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Product</title>
</head>
<body>
<h1>Edit Product</h1>
<p>
    <a href="/product">Back to customer list</a>
</p>
<p>
    <c:if test='${requestScope["message"] != null}'>
        <span class="message">${requestScope["message"]}</span>
    </c:if>
</p>
<form method="post">
<fieldset>
    <legend>Customer information</legend>
    <table>
        <tr>
            <td>Name: </td>
            <td><input type="text" name="name" id="name" value="${requestScope["product"].name}"></td>
        </tr>
        <tr>
            <td>Price: </td>
            <td><input type="text" name="price" id="price" value="${requestScope["product"].price}"></td>
        </tr>
        <tr>
            <td>origin: </td>
            <td><input type="text" name="origin" id="origin" value="${requestScope["product"].origin}"></td>
        </tr>
        <tr>
            <td>Quantity Vailable :</td>
            <td>
                <input type="text" name="quantityVailable" id="quantityVailable" value="${requestScope["product"].quantityVailable}">
            </td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Update customer"></td>
        </tr>
    </table>
</fieldset>
</form>

</body>
</html>
