package edu.daw.JDBC;

import java.sql.*;

public class JDBC {
    public static void main(String[] args) {
        String url = "jdbc:mariadb://localhost:3306/library";
        String user = "root";
        String password = "1234";

        Connection conn = null;
        Statement stmt = null;

        try {
            // conexion con la base de datos
            conn = DriverManager.getConnection(url, user, password);

            stmt = conn.createStatement();

            // consulta SQL
            String sql ="INSERT INTO user (first_name, last_name, email, birthday) values ('jouad', 'el ouardi', 'ola@gmial.com', '1984-07-10') ";
            stmt.executeUpdate(sql);

            stmt.close();
            conn.close();

            System.out.println("Insert with success");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
