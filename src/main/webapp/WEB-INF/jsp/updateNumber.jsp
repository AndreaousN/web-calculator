<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>

<head>
    <title>Number Update</title>
    <jsp:include page="header.jsp"/>
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .form-container {
            max-width: 400px;
            margin: auto;
            padding: 20px;
        }

        .form-control {
            width: 100%;
        }
    </style>
</head>

<body>
<div class="container-fluid">
    <div class="row justify-content-center">
        <div class="col">
            <div class="form-container">
                <h2 class="text-center">Number Update</h2>

                <form:form modelAttribute="number" action="updateNum" method="post" class="needs-validation">

                    <div class="form-group">
                        <label for="id">Number ID:</label>
                        <input type="number" class="form-control" id="id" name="id" value="${number.id}" required>
                        <div class="invalid-feedback">Please provide a valid number ID.</div>
                    </div>

                    <div class="form-group">
                        <label for="number1">First Number:</label>
                        <input type="number" class="form-control" id="number1" name="number1" value="${number.number1}" required>
                        <div class="invalid-feedback">Please provide a valid number.</div>
                    </div>

                    <div class="form-group">
                        <label for="operation">Operation:</label>
                        <select class="form-control" id="operation" name="operation" required>
                            <option value="">Select operation...</option>
                            <option value="+">Addition</option>
                            <option value="-">Subtraction</option>
                            <option value="*">Multiplication</option>
                            <option value="/">Division</option>
                        </select>
                        <div class="invalid-feedback">Please select an operation.</div>
                    </div>

                    <div class="form-group">
                        <label for="number2">Second Number:</label>
                        <input type="number" class="form-control" id="number2" name="number2" value="${number.number2}" required>
                        <div class="invalid-feedback">Please provide a valid number.</div>
                    </div>

                    <div class="form-group">
                        <label for="result">Result:</label>
                        <input type="number" class="form-control" id="result" name="result" value="${number.result}" required>
                        <div class="invalid-feedback">Please provide a valid result.</div>
                    </div>

                    <button type="submit" class="btn btn-primary btn-block">Update Number</button>

                </form:form>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>

</html>
