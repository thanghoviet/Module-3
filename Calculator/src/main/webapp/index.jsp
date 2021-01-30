<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Simple Calculator</title>
</head>
<body>
<form action="/calculater" method="post">
    <h1>Simple Calculator</h1>
    <h6>calculater</h6>
    <label for="first-operand">First Operand: </label>
    <input type="text" id="first-operand" name="first-operand" placeholder="First Operand"></br>
    <lable for="operator">Operator</lable>
    <select name="operator" id="operator">
        <option value="addition">addition</option>
        <option value="subtraction">subtraction</option>
        <option value="multiplication">multiplication</option>
        <option value="division">division</option>
    </select></br>
    <lable for="second-operand">second Operand</lable>
    </br>
    <input type="text" id="second-operand" name="second-operand" placeholder="second Operand">
    <input type="submit" value="Submit">
</form>
</body>
</html>