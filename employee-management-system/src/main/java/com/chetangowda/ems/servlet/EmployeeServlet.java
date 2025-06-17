package com.chetangowda.ems.servlet;

import com.chetangowda.model.Employee;
import com.chetangowda.util.DBUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/employees")
public class EmployeeServlet extends HttpServlet {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        if (idParam != null) {
            // GET /employees?id=1 (Get single employee)
            getEmployeeById(req, resp, Integer.parseInt(idParam));
        } else if (req.getHeader("Accept").contains("application/json")) {
            // GET /employees (Get all employees as JSON for REST API)
            getAllEmployeesAsJson(resp);
        } else {
            // GET /employees (Get all employees for JSP)
            getAllEmployeesForJsp(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // POST /employees (Add new employee)
        String name = req.getParameter("name");
        double salary = Double.parseDouble(req.getParameter("salary"));
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO employees (name, salary) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, name);
            stmt.setDouble(2, salary);
            stmt.executeUpdate();
            resp.sendRedirect("employees");
        } catch (SQLException e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // PUT /employees (Update employee)
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        double salary = Double.parseDouble(req.getParameter("salary"));
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement("UPDATE employees SET name = ?, salary = ? WHERE id = ?")) {
            stmt.setString(1, name);
            stmt.setDouble(2, salary);
            stmt.setInt(3, id);
            stmt.executeUpdate();
            resp.sendRedirect("employees");
        } catch (SQLException e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // DELETE /employees?id=1 (Delete employee)
        int id = Integer.parseInt(req.getParameter("id"));
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM employees WHERE id = ?")) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            resp.sendRedirect("employees");
        } catch (SQLException e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
        }
    }

    private void getAllEmployeesForJsp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Employee> employees = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM employees")) {
            while (rs.next()) {
                employees.add(new Employee(rs.getInt("id"), rs.getString("name"), rs.getDouble("salary")));
            }
            req.setAttribute("employees", employees);
            req.getRequestDispatcher("/employees.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
        }
    }

    private void getAllEmployeesAsJson(HttpServletResponse resp) throws IOException {
        List<Employee> employees = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM employees")) {
            while (rs.next()) {
                employees.add(new Employee(rs.getInt("id"), rs.getString("name"), rs.getDouble("salary")));
            }
            resp.setContentType("application/json");
            objectMapper.writeValue(resp.getOutputStream(), employees);
        } catch (SQLException e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
        }
    }

    private void getEmployeeById(HttpServletRequest req, HttpServletResponse resp, int id) throws ServletException, IOException {
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM employees WHERE id = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Employee employee = new Employee(rs.getInt("id"), rs.getString("name"), rs.getDouble("salary"));
                req.setAttribute("employee", employee);
                req.getRequestDispatcher("/edit-employee.jsp").forward(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Employee not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
        }
    }
}