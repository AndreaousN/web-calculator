<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f8f9fa;
            font-size: 1rem;
        }

        .form-container {
            max-width: 700px;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.1);
        }

        h2 {
            margin-bottom: 40px;
        }

        a {
            margin-left: 25px;
        }
        .errors {
            color: #ff3f3f;
        }
    </style>
</head>
<body>

<div class="form-container">
    <h2>Login</h2>
    <c:if test="${not empty error}">
        <div class="alert alert-danger">
            Invalid username or password.
        </div>
    </c:if>
    <form:form method="post" modelAttribute="user" action="${pageContext.request.contextPath}/loginUser">

        <div class="form-group">
            <label for="exampleInputUsername1">Username</label>
            <form:input name="username" value="${user.username}" type="text" class="form-control" id="exampleInputUsername1" placeholder="Enter username" path="username" required="true"/>

        </div>

        <div class="form-group">
            <label for="exampleInputPassword1">Password</label>
            <form:input name="password" value="${user.password}" type="password" class="form-control" id="exampleInputPassword1" placeholder="Password" path="password" required="true"/>

        </div>

        <button type="submit" class="btn btn-primary">Login</button>
        <a type="button" href="${pageContext.request.contextPath}/register" class="button">Register</a>

    </form:form>
</div>

<!-- Bootstrap JS -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
