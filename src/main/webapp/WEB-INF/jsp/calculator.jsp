<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html lang="en">
    <head>
        <title>Calculate</title>
        <jsp:include page="header.jsp"/>
        <style>
            .error{color:red}
        </style>
    </head>
    <body>
        <h2>Online calculator. Possible operations: addition, subtraction, multiplication, division.</h2>
            <h3>The calculator is sensitive to negative numbers.</h3>
            <form:form method="post" action="calculate" modelAttribute="number">
                First number: <form:input type="number" path="number1"/>
                              <form:errors path="number1" cssClass="error"/><br><br>
                Second number: <form:input type="number" path="number2"/>
                               <form:errors path="number2" cssClass="error"/><br><br>
                Operation:
                <select name="operation">
                    <option selected="selected" value="+">Addition</option>
                    <option value="-">Subtraction</option>
                    <option value="*">Multiplication</option>
                    <option value="/">Division</option>
                </select><p>
                <input type="submit" value="Calculate">
        </form>
        </form:form>
    </body>
</html>
