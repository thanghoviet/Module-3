<%--
  Created by IntelliJ IDEA.
  User: Thang.HoViet
  Date: 2/2/2021
  Time: 11:39 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DELETE Product</title>
</head>
<body>
<h1>Delete Product</h1>
<p>
    <a href="/product">Back to product list</a>
</p>
<form method="post">
    <h3>Are you sure?</h3>
    <fieldset>
        <legend>Product information</legend>
        <table>
            <tr>
                <td>Name :</td>
                <td>${requestScope["product"].name}</td>
            </tr>
            <tr>
                <td>Price :</td>
                <td>${requestScope["product"].price}</td>
            </tr>
            <tr>
                <td>Origin :</td>
                <td>${requestScope["product"].origin}</td>
            </tr>
            <tr>
                <td>Quantity Vailable</td>
                <td>${requestScope["product"].quantityVailable}</td>
            </tr>
            <tr>
                <td><input type="submit" value="Delete customer"></td>
                <td><a href="/product">Back to customer list</a></td>
            </tr>
        </table>
    </fieldset>

</form>
</body>
</html>
