package edu.daw;

import com.zaxxer.hikari.HikariDataSource;
import edu.daw.JDBC.JDBC_POOL;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;

import java.sql.*;
import java.time.LocalDate;


public class JDBC_POOL_Test {
    private static HikariDataSource dataSource;

    @Test
    void createUserTest() {
        assertDoesNotThrow(() -> JDBC_POOL.createUser("John", "Doe", "john.doe@gmail.com", LocalDate.of(1990, 1, 1)));
    }

    @Test
    void readUserTest() {
        assertDoesNotThrow(JDBC_POOL::readUser);
    }
    @Test
    void testUpdateData() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO user (first_name, last_name, email, birthday) VALUES (?, ?, ?, ?)")) {
                statement.setString(1, "John");
                statement.setString(2, "Doe");
                statement.setString(3, "john.doe@example.com");
                statement.setDate(4, Date.valueOf(LocalDate.of(1980, 1, 1)));
                statement.executeUpdate();
            }
        }
        JDBC_POOL.updateData("John", "newemail@gmai.com");
    }

    @Test
    void testDeleteData() throws SQLException {
        assertDoesNotThrow(() -> JDBC_POOL.deleteData("John"));
    }
}