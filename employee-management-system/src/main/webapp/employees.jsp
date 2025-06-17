<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Employee Management System</title>
    <link rel="stylesheet" href="css/styles.css">
    <script src="js/main.js"></script>
</head>
<body>
    <div class="container">
        <h1>Employee Management System</h1>
        <!-- Add Employee Form -->
        <div class="form-container">
            <form action="employees" method="post">
                <input type="text" name="name" placeholder="Employee Name" required>
                <input type="number" name="salary" placeholder="Salary" step="0.01" required>
                <input type="submit" value="Add Employee">
            </form>
        </div>
        <!-- Employee Table -->
        <table>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Salary</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="employee" items="${employees}">
                <tr>
                    <td>${employee.id}</td>
                    <td>${employee.name}</td>
                    <td>${employee.salary}</td>
                    <td>
                        <a href="employees?id=${employee.id}" class="btn btn-edit">Edit</a>
                        <form action="employees" method="post" style="display:inline;">
                            <input type="hidden" name="id" value="${employee.id}">
                            <input type="hidden" name="_method" value="DELETE">
                            <input type="submit" value="Delete" class="btn btn-delete">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>