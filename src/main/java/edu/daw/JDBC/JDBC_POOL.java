package edu.daw.JDBC;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC_POOL {
    public static void main(String[] args) throws SQLException {
        // Configuración del pool de conexiones
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/delete_me");
        dataSource.setUsername("root");
        dataSource.setPassword("1234");

        // Obtener conexión del pool
        Connection conn = dataSource.getConnection();

        // Ejecutar una consulta
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM example");

        // Recorrer los resultados
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            System.out.println(id + ", " + name);
        }

        // Insertar un nuevo registro
        String insertQuery = "INSERT INTO example (name) VALUES ('John')";
        int rowsAffected = stmt.executeUpdate(insertQuery);
        System.out.println("Filas afectadas: " + rowsAffected);

        // Cerrar recursos y conexión
        rs.close();
        stmt.close();
        conn.close();
    }
}
