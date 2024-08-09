package com.example.servlet;

import com.example.model.Student;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/students")
public class StudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/student_management";
    private static final String JDBC_USER = "admin";
    private static final String JDBC_PASSWORD = "admin";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC driver not found", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            switch (action) {
                case "add":
                    addStudent(request, response);
                    break;
                case "update":
                    updateStudent(request, response);
                    break;
                case "delete":
                    deleteStudent(request, response);
                    break;
                default:
                    response.sendRedirect("index.jsp");
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException("Database error while processing request", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            switch (action) {
                case "view":
                    viewStudents(request, response);
                    break;
                case "search":
                    searchStudents(request, response);
                    break;
                case "edit":
                    showUpdateForm(request, response);
                    break;
                default:
                    response.sendRedirect("index.jsp");
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException("Database error while processing request", e);
        }
    }

    private void addStudent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String email = request.getParameter("email");
        String course = request.getParameter("course");

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(
                 "INSERT INTO students (student_id, name, age, email, course) VALUES (?, ?, ?, ?, ?)")) {
            stmt.setString(1, id);
            stmt.setString(2, name);
            stmt.setInt(3, age);
            stmt.setString(4, email);
            stmt.setString(5, course);
            stmt.executeUpdate();
        }

        response.sendRedirect("/viewStudents.jsp");
    }

    private void updateStudent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String email = request.getParameter("email");
        String course = request.getParameter("course");

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(
                 "UPDATE students SET name = ?, age = ?, email = ?, course = ? WHERE student_id = ?")) {
            stmt.setString(1, name);
            stmt.setInt(2, age);
            stmt.setString(3, email);
            stmt.setString(4, course);
            stmt.setString(5, id);
            stmt.executeUpdate();
        }

        response.sendRedirect("/viewStudents.jsp");
    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String id = request.getParameter("id");

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(
                 "DELETE FROM students WHERE student_id = ?")) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        }

        response.sendRedirect("/viewStudents.jsp");
    }

    protected void viewStudents(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Student> students = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM students")) {
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getString("student_id"));
                student.setName(rs.getString("name"));
                student.setAge(rs.getInt("age"));
                student.setEmail(rs.getString("email"));
                student.setCourse(rs.getString("course"));
                students.add(student);
            }
        } catch (SQLException e) {
            throw new ServletException("Database error while retrieving students", e);
        }

        request.setAttribute("students", students);
        request.getRequestDispatcher("/viewstudents.jsp").forward(request, response); // Ensure this path is correct
    }



    private void searchStudents(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String name = request.getParameter("name");
        String course = request.getParameter("course");

        List<Student> students = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(
                 "SELECT * FROM students WHERE name LIKE ? OR course LIKE ?")) {
            stmt.setString(1, "%" + name + "%");
            stmt.setString(2, "%" + course + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getString("student_id"));
                student.setName(rs.getString("name"));
                student.setAge(rs.getInt("age"));
                student.setEmail(rs.getString("email"));
                student.setCourse(rs.getString("course"));
                students.add(student);
            }
        }

        request.setAttribute("students", students);
        request.getRequestDispatcher("/viewStudents.jsp").forward(request, response);
    }

    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String id = request.getParameter("id");
        Student student = null;

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(
                 "SELECT * FROM students WHERE student_id = ?")) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                student = new Student();
                student.setId(rs.getString("student_id"));
                student.setName(rs.getString("name"));
                student.setAge(rs.getInt("age"));
                student.setEmail(rs.getString("email"));
                student.setCourse(rs.getString("course"));
            }
        }

        request.setAttribute("student", student);
        request.getRequestDispatcher("/updateStudent.jsp").forward(request, response);
    }
}
