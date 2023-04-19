package edu.daw.JDBC;

import java.sql.*;

public class JDBC {
    public static void main(String[] args) {
        String url = "jdbc:mariadb://localhost:3306/library";
        String user = "root";
        String password = "1234";

//   statement
        Connection conn = null;
        Statement stmt = null;

        try {
            // conexion con la base de datos
            conn = DriverManager.getConnection(url, user, password);

            // Creación del statement
            stmt = conn.createStatement();

            // consulta SQL
            String sql ="INSERT INTO user (first_name, last_name, email, birthday) values ('jouad', 'el ouardi', 'ola@gmial.com', '1984-07-10') "; // Consulta para crear una tabla
            stmt.executeUpdate(sql); // Ejecución de la consulta

            stmt.close();
            conn.close();

            System.out.println("Insert realizado con éxito");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
