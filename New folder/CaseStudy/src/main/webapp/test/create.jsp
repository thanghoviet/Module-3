<%--
  Created by IntelliJ IDEA.
  User: Thang.HoViet
  Date: 2/22/2021
  Time: 2:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add the note</title>
    <link rel="icon" href=
            "https://media.geeksforgeeks.org/wp-content/cdn-uploads/gfg_200X200.png"
          type="image/x-icon">
</head>
<body>
<h1>Thêm Mới Ghi Chú</h1>
<form action="" method="post">
    <table border="1" cellpadding="5">
        <caption>
            <h2>Add new Note</h2>
        </caption>
        <tr>
            <th>Tiêu đề :</th>
            <td>
                <input type="text" name="title" id="title" size="20">
            </td>
        </tr>
        <tr>
            <th>Nội dung :</th>
            <td>
                <input type="text" name="content" id="content" style="height: 150px">
            </td>
        </tr>
        <tr>

        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="submit" value="Hủy" >
                <input type="submit" value="Lưu" >
            </td>
        </tr>
    </table>
</form>
</body>
</html>
