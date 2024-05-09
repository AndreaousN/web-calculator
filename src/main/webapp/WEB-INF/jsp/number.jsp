<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Number</title>
    <jsp:include page="header.jsp"/>
</head>
<body class="container">
<div class="table-responsive">
    <caption>Number information</caption>
    <table class="table table-striped">
        <tr>
            <td><b>ID:</b></td>
            <td>${number.id}</td>
        </tr>

        <tr>
            <td><b>First Number:</b></td>
            <td>${number.number1}</td>
        </tr>

        <tr>
            <td><b>Operation:</b></td>
            <td>${number.operation}</td>
        </tr>

        <tr>
            <td><b>Second Number:</b></td>
            <td>${number.number2}</td>
        </tr>

        <tr>
            <td><b>Result</b></td>
            <td>${number.result}</td>
        </tr>
    </table>
</div>
    <br>
    <a type="button" href="${pageContext.request.contextPath}/allNumbers" class="button">Back</a>
</body>
</html>
