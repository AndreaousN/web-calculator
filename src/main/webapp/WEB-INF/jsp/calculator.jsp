<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html lang="en">
<head>
    <title>Calculator</title>
    <style>
        .error {
            color: red
        }
    </style>
    <jsp:include page="header.jsp"/>
</head>
<body>
<h2>Online calculator. Possible operations: add, subtract, multiply, divide.</h2>

<form:form method="post" action="calculate" modelAttribute="number">

First number: <form:input type="number" path="number1"/>
<form:errors path="number1" cssClass="error"/><br><br>

Second number: <form:input type="number" path="number2"/>
<form:errors path="number2" cssClass="error"/><br><br>

Operation:
<label>
    <select name="operation">
        <option selected="selected" value="+">Add</option>
        <option value="-">Subtract</option>
        <option value="*">Multiply</option>
        <option value="/">Divide</option>
    </select>
</label>
<p>
    <input type="submit" value="Calculate">

    </form:form>
</body>
</html>
