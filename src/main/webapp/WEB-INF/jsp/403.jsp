<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<html lang="en">
<head>
    <title>Denied access</title>

    <style>
        .text {
            color: red;
            height: 80%;
            width: 100%;
            display: flex;
            position: fixed;
            align-items: center;
            justify-content: center;
        }
    </style>
    <jsp:include page="header.jsp"/>
</head>
    <body>
        <div class="form-container">
            <div class="text">
                <h2>You do not have permission to access these features!</h2>
            </div>
        </div>
    </body>
</html>