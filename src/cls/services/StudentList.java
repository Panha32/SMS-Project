package cls.services;

import db.DBConnection;

import java.sql.*;

public class StudentList {

    public ResultSet fetchAllStudents() {
        Connection con = DBConnection.getConnection();
        ResultSet rs = null;

        if (con != null) {
            try {
                Statement stmt = con.createStatement();
                String query = "SELECT " +
                        "students.student_id, " +
                        "students.first_name, " +
                        "students.last_name, " +
                        "students.gender, " +
                        "students.date_of_birth, " +
                        "students.email, " +
                        "students.phone_number, " +
                        "departments.department_name, " +
                        "classes.class_name " +
                        "FROM sms.students " +
                        "LEFT JOIN sms.departments ON sms.students.department_id = sms.departments.department_id " +
                        "LEFT JOIN sms.classes ON sms.students.class_id = sms.classes.class_id";
                rs = stmt.executeQuery(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rs;
    }
    public Student fetchStudentById(String studentId) {
        Connection con = DBConnection.getConnection();
        Student student = null;

        if (con != null) {
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            try {
                String query = "SELECT " +
                        "students.student_id, " +
                        "students.first_name, " +
                        "students.last_name, " +
                        "students.gender, " +
                        "students.date_of_birth, " +
                        "students.email, " +
                        "students.phone_number, " +
                        "departments.department_name, " +
                        "classes.class_name " +
                        "FROM sms.students " +
                        "LEFT JOIN sms.departments ON sms.students.department_id = sms.departments.department_id " +
                        "LEFT JOIN sms.classes ON sms.students.class_id = sms.classes.class_id " +
                        "WHERE students.student_id = ?";

                pstmt = con.prepareStatement(query);
                pstmt.setString(1, studentId);
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    student = new Student();
                    student.setId(rs.getString("student_id"));
                    student.setFirstName(rs.getString("first_name"));
                    student.setLastName(rs.getString("last_name"));
                    student.setGender(rs.getString("gender"));
                    student.setDob(rs.getDate("date_of_birth"));
                    student.setEmail(rs.getString("email"));
                    student.setPhone(rs.getString("phone_number"));
                    student.setDepartment(rs.getString("department_name"));
                    student.setClassName(rs.getString("class_name"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (rs != null) rs.close();
                    if (pstmt != null) pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return student;
    }
}
