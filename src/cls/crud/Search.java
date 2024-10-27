package cls.crud;

import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Search {

    public ResultSet searchStudents(String studentId, String studentName) {
        Connection con = DBConnection.getConnection();
        ResultSet rs = null;
        String query =
                "SELECT students.student_id, students.first_name, students.last_name, " +
                        "students.gender, students.date_of_birth, students.email, " +
                        "students.phone_number, departments.department_name, classes.class_name " +
                        "FROM sms.students " +
                        "LEFT JOIN sms.departments ON sms.students.department_id = sms.departments.department_id " +
                        "LEFT JOIN sms.classes ON sms.students.class_id = sms.classes.class_id " +
                        "WHERE 1=1";

        if (studentId != null && !studentId.isEmpty()) {
            query += " AND students.student_id = ?";
        }
        if (studentName != null && !studentName.isEmpty()) {
            query += " AND (students.first_name LIKE ? OR students.last_name LIKE ?)";
        }

        try {
            PreparedStatement pstmt = con.prepareStatement(query);

            int index = 1;
            if (studentId != null && !studentId.isEmpty()) {
                pstmt.setInt(index++, Integer.parseInt(studentId));
            }
            if (studentName != null && !studentName.isEmpty()) {
                pstmt.setString(index++, "%" + studentName + "%");
                pstmt.setString(index, "%" + studentName + "%");
            }

            rs = pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
}
