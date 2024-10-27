package cls.services;

import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultFetcher {

    private Connection connection;

    public ResultFetcher() {
        this.connection = DBConnection.getConnection();
    }

    public ResultSet fetchExamResults() {
        String query = "WITH StudentScores AS ("
                + "    SELECT "
                + "        s.student_id, "
                + "        CONCAT(s.first_name, ' ', s.last_name) AS name, "
                + "        s.gender, "
                + "        FORMAT(MAX(CASE WHEN c.course_id = 1 THEN sc.score END), 0) AS Java, "
                + "        FORMAT(MAX(CASE WHEN c.course_id = 2 THEN sc.score END), 0) AS HTML5, "
                + "        FORMAT(MAX(CASE WHEN c.course_id = 3 THEN sc.score END), 0) AS JS, "
                + "        FORMAT(MAX(CASE WHEN c.course_id = 4 THEN sc.score END), 0) AS Python, "
                + "        FORMAT(MAX(CASE WHEN c.course_id = 5 THEN sc.score END), 0) AS DSA, "
                + "        FORMAT(SUM(sc.score), 0) AS total_score, "
                + "        FORMAT((AVG(sc.score)/2), 2) AS average, "
                + "        CASE "
                + "            WHEN (AVG(sc.score)/2) >= 45 THEN 'A' "
                + "            WHEN (AVG(sc.score)/2) >= 40 THEN 'B' "
                + "            WHEN (AVG(sc.score)/2) >= 35 THEN 'C' "
                + "            WHEN (AVG(sc.score)/2) >= 30 THEN 'D' "
                + "            WHEN (AVG(sc.score)/2) >= 25 THEN 'E' "
                + "            ELSE 'F' "
                + "        END AS grade "
                + "    FROM "
                + "        sms.students s "
                + "    LEFT JOIN sms.scores sc ON s.student_id = sc.student_id "
                + "    LEFT JOIN sms.courses c ON sc.course_id = c.course_id "
                + "    GROUP BY s.student_id, s.first_name, s.last_name, s.gender "
                + "), "
                + "RankedScores AS ("
                + "    SELECT *, "
                + "    ROW_NUMBER() OVER (ORDER BY total_score DESC) AS orderNum "
                + "    FROM StudentScores "
                + ") "
                + "SELECT "
                + "    orderNum, "
                + "    student_id, "
                + "    name, "
                + "    gender, "
                + "    Java, "
                + "    HTML5, "
                + "    JS, "
                + "    Python, "
                + "    DSA, "
                + "    total_score, "
                + "    average, "
                + "    grade,"
                + "    orderNum AS Rankstu "
                + "FROM RankedScores "
                + "ORDER BY orderNum ";

        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            return stmt.executeQuery();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getTotalStudentCount() {
        String query = "SELECT COUNT(*) AS total FROM sms.students";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getTotalWomenCount() {
        String query = "SELECT COUNT(*) AS total FROM sms.students WHERE gender = 'Female'";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public int getGradeCount(String grade) {
        String query = "SELECT COUNT(*) AS total FROM ("
                + "    SELECT s.student_id, "
                + "           AVG(sc.score)/2 AS total_total "
                + "    FROM sms.students s "
                + "    LEFT JOIN sms.scores sc ON s.student_id = sc.student_id "
                + "    LEFT JOIN sms.courses c ON sc.course_id = c.course_id "
                + "    GROUP BY s.student_id "
                + ") AS student_scores "
                + "WHERE total_total BETWEEN ? AND ?";

        int lowerBound, upperBound;

        if (grade.equals("A")) {
            lowerBound = 45;
            upperBound = Integer.MAX_VALUE;
        } else if (grade.equals("B")) {
            lowerBound = 40;
            upperBound = 45;
        } else if (grade.equals("C")) {
            lowerBound = 35;
            upperBound = 40;
        } else if (grade.equals("D")) {
            lowerBound = 30;
            upperBound = 35;
        } else if (grade.equals("E")) {
            lowerBound = 25;
            upperBound = 30;
        } else if (grade.equals("F")) {
            lowerBound = 0;
            upperBound = 25;
        } else {
            return 0;
        }

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, lowerBound);
            stmt.setInt(2, upperBound);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("total");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
