<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Employee</title>
    <link rel="stylesheet" href="css/styles.css">
    <script src="js/main.js"></script>
</head>
<body>
    <div class="container">
        <h1>Edit Employee</h1>
        <div class="form-container">
            <form action="employees" method="post">
                <input type="hidden" name="id" value="${employee.id}">
                <input type="hidden" name="_method" value="PUT">
                <input type="text" name="name" value="${employee.name}" placeholder="Employee Name" required>
                <input type="number" name="salary" value="${employee.salary}" placeholder="Salary" step="0.01" required>
                <input type="submit" value="Update Employee">
                <a href="employees" class="btn-cancel">Cancel</a>
            </form>
        </div>
    </div>
</body>
</html>