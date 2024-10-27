package cls.services;

import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TotalDepartments {
    public int getTotalDepartments() {
        int totalDepartments = 0;
        String query = "SELECT COUNT(*) AS total FROM departments";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {

            if (rs.next()) {
                totalDepartments = rs.getInt("total");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return totalDepartments;
    }
}
