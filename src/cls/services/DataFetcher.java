package cls.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataFetcher {

    private Connection connection;

    public DataFetcher(Connection connection) {
        this.connection = connection;
    }

    public ResultSet fetchAttendanceData() throws SQLException {
        String query = "SELECT "
                + "s.student_id, "
                + "CONCAT(s.first_name, ' ', s.last_name) AS student_name, "
                + "a.attendance_date, "
                + "MAX(CASE WHEN c.course_name = 'Java' THEN "
                + "CASE a.status "
                + "WHEN 'Present' THEN 'P' "
                + "WHEN 'Late' THEN 'L' "
                + "WHEN 'Excused' THEN 'E' "
                + "WHEN 'Absent' THEN 'A' "
                + "ELSE a.status "
                + "END "
                + "END) AS Java, "
                + "MAX(CASE WHEN c.course_name = 'HTML/CSS' THEN "
                + "CASE a.status "
                + "WHEN 'Present' THEN 'P' "
                + "WHEN 'Late' THEN 'L' "
                + "WHEN 'Excused' THEN 'E' "
                + "WHEN 'Absent' THEN 'A' "
                + "ELSE a.status "
                + "END "
                + "END) AS HTML_CSS, "
                + "MAX(CASE WHEN c.course_name = 'JavaScript' THEN "
                + "CASE a.status "
                + "WHEN 'Present' THEN 'P' "
                + "WHEN 'Late' THEN 'L' "
                + "WHEN 'Excused' THEN 'E' "
                + "WHEN 'Absent' THEN 'A' "
                + "ELSE a.status "
                + "END "
                + "END) AS JavaScript, "
                + "MAX(CASE WHEN c.course_name = 'Python' THEN "
                + "CASE a.status "
                + "WHEN 'Present' THEN 'P' "
                + "WHEN 'Late' THEN 'L' "
                + "WHEN 'Excused' THEN 'E' "
                + "WHEN 'Absent' THEN 'A' "
                + "ELSE a.status "
                + "END "
                + "END) AS Python, "
                + "MAX(CASE WHEN c.course_name = 'DSA' THEN "
                + "CASE a.status "
                + "WHEN 'Present' THEN 'P' "
                + "WHEN 'Late' THEN 'L' "
                + "WHEN 'Excused' THEN 'E' "
                + "WHEN 'Absent' THEN 'A' "
                + "ELSE a.status "
                + "END "
                + "END) AS DSA, "
                + "SUM(CASE WHEN a.status = 'Late' THEN 1 ELSE 0 END) AS total_late, "
                + "SUM(CASE WHEN a.status = 'Absent' THEN 1 ELSE 0 END) AS total_absent, "
                + "SUM(CASE WHEN a.status = 'Excused' THEN 1 ELSE 0 END) AS total_excused "
                + "FROM sms.attendance a "
                + "JOIN sms.students s ON a.student_id = s.student_id "
                + "JOIN sms.courses c ON a.course_id = c.course_id "
                + "GROUP BY s.student_id, s.first_name, s.last_name, a.attendance_date "
                + "ORDER BY s.student_id, a.attendance_date";

        Statement stmt = connection.createStatement();
        return stmt.executeQuery(query);
    }
}
