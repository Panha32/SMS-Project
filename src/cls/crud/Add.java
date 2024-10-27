package cls.crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import db.DBConnection; // Adjust this import if your DBConnection class is in a different package

public class Add {

    public boolean addStudent(String firstName, String lastName, String gender, String dateOfBirth, String email, String phoneNumber, String departmentName, String className) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection(); // Assuming you have a method to get a DB connection

            // Insert department_name into departments table
            String deptQuery = "INSERT INTO departments (department_name) VALUES (?) ON DUPLICATE KEY UPDATE department_name = department_name";
            stmt = conn.prepareStatement(deptQuery, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, departmentName);
            stmt.executeUpdate();

            int departmentId;
            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                departmentId = rs.getInt(1);
            } else {
                // If the department already exists, fetch its id
                rs.close();
                stmt.close();

                String fetchDeptIdQuery = "SELECT department_id FROM departments WHERE department_name = ?";
                stmt = conn.prepareStatement(fetchDeptIdQuery);
                stmt.setString(1, departmentName);
                rs = stmt.executeQuery();

                if (rs.next()) {
                    departmentId = rs.getInt("department_id");
                } else {
                    throw new SQLException("Failed to retrieve department ID");
                }
            }

            rs.close();
            stmt.close();

            // Insert class_name into classes table
            String classQuery = "INSERT INTO classes (class_name) VALUES (?) ON DUPLICATE KEY UPDATE class_name = class_name";
            stmt = conn.prepareStatement(classQuery, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, className);
            stmt.executeUpdate();

            int classId;
            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                classId = rs.getInt(1);
            } else {
                // If the class already exists, fetch its id
                rs.close();
                stmt.close();

                String fetchClassIdQuery = "SELECT class_id FROM classes WHERE class_name = ?";
                stmt = conn.prepareStatement(fetchClassIdQuery);
                stmt.setString(1, className);
                rs = stmt.executeQuery();

                if (rs.next()) {
                    classId = rs.getInt("class_id");
                } else {
                    throw new SQLException("Failed to retrieve class ID");
                }
            }

            rs.close();
            stmt.close();

            // Insert student into students table
            String studentQuery = "INSERT INTO students (first_name, last_name, gender, date_of_birth, email, phone_number, department_id, class_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(studentQuery);
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, gender);
            stmt.setString(4, dateOfBirth);
            stmt.setString(5, email);
            stmt.setString(6, phoneNumber);
            stmt.setInt(7, departmentId);
            stmt.setInt(8, classId);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
