<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    </style>
</head>
<body>

<div class="form-container">
    <h2>Login</h2>
    <form:form action="loginUser" modelAttribute="user" method="post" autocomplete="false">

        <div class="form-group">
            <label for="username">Username</label>
            <form:input path="username" type="text" class="form-control" id="username" placeholder="Enter username"/>
        </div>

        <div class="form-group">
            <label for="password">Password</label>
            <form:input path="password" type="password" class="form-control" id="password" placeholder="Password"/>
        </div>

        <!-- Error message -->
        <%
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (errorMessage != null && !errorMessage.isEmpty()) {
        %>
        <div class="alert alert-danger" role="alert">
            <%= errorMessage %>
        </div>
        <%
            }
        %>

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
