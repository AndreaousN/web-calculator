<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>

<head>
    <title>All operations</title>
    <jsp:include page="header.jsp"/>
</head>

<body class="container">
<div class="table-responsive">
    <table class="table table-striped">
        <tr>
            <th>First number</th>
            <th>Operation</th>
            <th>Second number</th>
            <th>Result</th>
            <th>Actions</th>
        </tr>
        <!-- Iterate through number list -->
        <c:forEach var="number" items="${numbers}">

            <!-- Construct address for number updating by id -->
            <c:url var="updateNumber" value="/updateNumber">
                <c:param name="id" value="${number.id}"/>
            </c:url>

            <!-- Construct number deletion address by id -->
            <c:url var="delete" value="/delete">
                <c:param name="id" value="${number.id}"/>
            </c:url>

            <!-- Construct number show address by id -->
            <c:url var="showNum" value="/showNum">
                <c:param name="id" value="${number.id}"/>
            </c:url>

            <tr>
                <td>${number.number1}</td>
                <td>${number.operation}</td>
                <td>${number.number2}</td>
                <td>${number.result}</td>


                <td>

                    <!-- Show updating address --> <a href="${updateNumber}">Change</a>
                    | <a href="${delete}"
                         onclick="if (!(confirm('Are you sure you want to delete this entry?'))) return false">Delete</a>
                    | <!-- Show number address --> <a href="${showNum}">Show</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>

</html>
