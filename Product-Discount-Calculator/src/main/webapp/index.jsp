<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<form method="post" action="<%= request.getContextPath()%>/product">
    <h3>mô tả sản phẩm</h3>
    <input type="text" name="ProductDescription" placeholder="ProductDescription: "/><br/>
    <h3>giá sp</h3>
    <input type="text" name="ListPrice" placeholder="ListPrice: " /><br/>
    <h3>tỉ lệ chiết khấu %</h3>
    <input type="text" name="DiscountPercent" placeholder="DiscountPercent: " /><br/>
    <input type = "submit" id = "submit" value = " Calculate Discount"/>
</form>
</body>
</html>