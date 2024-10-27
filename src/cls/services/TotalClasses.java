package cls.services;

import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TotalClasses {

    public int getTotalClasses() {
        int totalClasses = 0;
        String query = "SELECT COUNT(*) AS total_classes FROM classes";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                totalClasses = rs.getInt("total_classes");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalClasses;
    }
}

