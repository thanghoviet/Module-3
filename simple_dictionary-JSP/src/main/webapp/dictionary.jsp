<%--
  Created by IntelliJ IDEA.
  User: Thang.HoViet
  Date: 1/28/2021
  Time: 2:03 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dictonary</title>
</head>
<body>
<%!    Map<String, String> dic = new HashMap<>();%>
<%    dic.put("hello", "Xin chào");
    dic.put("how", "Thế nào");
    dic.put("book", "Quyển vở");
    dic.put("computer", "Máy tính");

    String search = request.getParameter("search");
    String result = dic.get(search);
    if (result == null)
        result = "Not found";
%>
<p>Word: <%=search%></p>
<p>Result: <%=result%></p>
</body>
</html>
