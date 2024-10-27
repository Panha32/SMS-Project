package cls;

import db.DBConnection;

import java.sql.Connection;

public class TestDBConnection {
    public static void main(String[] args) {
        Connection con = DBConnection.getConnection();

        if (con != null) {
            System.out.println("Database connection successful!");
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Failed to connect to the database.");
        }
    }
}
