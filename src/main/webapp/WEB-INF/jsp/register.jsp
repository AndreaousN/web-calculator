<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration</title>
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
        .error {
            color: #ff3f3f;
        }
    </style>
</head>
<body>

<div class="form-container">
    <h2>Registration</h2>
    <form:form method="post" modelAttribute="user" action="register">

        <div class="form-group">
            <label for="exampleInputUsername1">Username</label>
            <form:input name="username" value="${user.username}" type="text" class="form-control" id="exampleInputUsername1"
            placeholder="Enter username" path="username"/>
            <form:errors cssClass="error" path="username"/>
        </div>

        <div class="form-group">
            <label for="exampleInputEmail1">Email address</label>
            <form:input name="email" value="${user.email}" type="email" class="form-control" id="exampleInputEmail1"
             placeholder="Enter email" path="email"/>
            <form:errors cssClass="error" path="email"/>
        </div>

        <div class="form-group">
            <label for="exampleInputPassword1">Password</label>
            <form:input name="password" value="${user.password}" type="password" class="form-control" id="exampleInputPassword1"
             placeholder="Password" path="password"/>
            <form:errors cssClass="error" path="password"/>
        </div>

        <div class="form-group">
            <label for="exampleInputConfirmPassword1">Confirm Password</label>
            <form:input name="passwordConfirm" value="${user.passwordConfirm}" type="password" class="form-control" id="exampleInputConfirmPassword1"
             placeholder="Password" path="passwordConfirm"/>
            <form:errors cssClass="error" path="passwordConfirm"/>
        </div>

        <button type="submit" class="btn btn-primary">Register</button>
        <a type="button" href="${pageContext.request.contextPath}/login" class="button">Back to login</a>

    </form:form>
</div>

<!-- Bootstrap JS -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
