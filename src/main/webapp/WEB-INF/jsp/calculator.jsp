<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html lang="en">
<head>
    <title>Calculator</title>
    <style>
        .error {
            color: red;
            padding: 10px;
        }
        .box {
          border: 2px solid gray;
          border-radius: 5px;
          text-align: center;
          margin: 50px 350px;
          display: flex;
          flex-direction: column;
          align-items: center;
          height: 350px;
        }

        .box > div {
          justify-content: space-between;
        }

        .number1, .number2 {
          align-self: flex-start;
          margin: 10px;
          display: flex;
        }
        .inputLabel {
            width: 120px;
            display: flex;
            justify-content: flex-end;
            padding: 5px;
        }
        .operation, .calculate {
          padding: 5px;
        }
        #number1, #number2 {
            height: 30px;
            align-items: center;
        }

    </style>
    <jsp:include page="header.jsp"/>
</head>
    <body>
        <div class="box">
            <h2>Online calculator</h2>
            <h3>Possible operations: add, subtract, multiply, divide</h3>
                <form:form method="post" action="calculate" modelAttribute="number">
                    <div class="number1">
                        <div class="inputLabel">First number:</div> <form:input type="number" path="number1"/>
                        <form:errors path="number1" cssClass="error"/><br><br>
                    </div>
                    <div class="number2">
                        <div class="inputLabel">Second number: </div><form:input type="number" path="number2"/>
                        <form:errors path="number2" cssClass="error"/><br><br>
                    </div>
                    <div class="operation">
                    Operation:
                    <label>
                        <select name="operation">
                            <option selected="selected" value="+">Add</option>
                            <option value="-">Subtract</option>
                            <option value="*">Multiply</option>
                            <option value="/">Divide</option>
                        </select>
                    </label>
                    </div>
                    <div class="calculate">
                        <p>
                        <input type="submit" value="Calculate">
                    </div>
                </form:form>
            </div>
    </body>
</html>
