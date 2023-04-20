package edu.daw.JDBC;

import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class JDBC_POOL {
    private static HikariDataSource dataSource;

    public static void main(String[] args) throws SQLException {
        initDatabaseConnectionPool();
        try {
            boolean exit = false;
            Scanner sc = new Scanner(System.in);

            while (!exit) {
                System.out.println("\n=== Menu ===");
                System.out.println("1. Add user");
                System.out.println("2. Update email");
                System.out.println("3. Delete user");
                System.out.println("4. Show users");
                System.out.println("0. Exit");
                System.out.println("Select an option");
                int option = sc.nextInt();

                switch (option) {
                    case 1:
                        System.out.println("Enter first name: ");
                        String first_name = sc.next();
                        System.out.println("Enter last name: ");
                        String last_name = sc.next();
                        System.out.println("Enter email: ");
                        String email = sc.next();
                        System.out.println("Enter birthday (yyyy-MM-dd: )");
                        String birthdayStr = sc.next();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        LocalDate birthday = LocalDate.parse(birthdayStr, formatter);
                        createUser(first_name,last_name,email, birthday);
                        break;
                    case 2:
                        System.out.println("Enter the first name of the user to update: ");
                        String nameToUpdate = sc.next();
                        System.out.println("Enter the new email: ");
                        String newEmail = sc.next();
                        updateData(nameToUpdate,newEmail);
                        break;
                    case 3:
                        System.out.println("Enter the first name of the user to delete: ");
                        String nameToDelete = sc.next();
                        deleteData(nameToDelete);
                        break;
                    case 4:
                        readUser();
                        break;
                    case 0:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid option, please try again.");
                }
            }
            sc.close();
        } finally {
            closeDatabaseConnectionPool();
        }
    }
    public static void createUser(String first_name, String last_name, String email, LocalDate birthday) throws SQLException {
        System.out.println("Creating user...");
        int rowsInserted;
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO user (first_name, last_name, email, birthday) VALUES (?, ?, ?, ?)"))
            {
                statement.setString(1, first_name);
                statement.setString(2, last_name);
                statement.setString(3, email);
                statement.setDate(4, Date.valueOf(birthday));
                rowsInserted = statement.executeUpdate();
            }
        }
        System.out.println("Files inserted: " + rowsInserted);
    }

    public static void readUser() throws SQLException {
        System.out.println("Reading all users...");
        try  (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM user")) {
                ResultSet resultSet = statement.executeQuery();
                boolean empty = true;
                while (resultSet.next()) {
                    int user_id = resultSet.getInt(1);
                    String first_name = resultSet.getString(2);
                    String last_name = resultSet.getString(3);
                    String email = resultSet.getString(4);
                    LocalDate birthday = resultSet.getObject(5, LocalDate.class);
                    System.out.println("\t> User id:" + user_id + ", fist name: " + first_name + ", last name: " + last_name + ", email: " + email + ", birthday: " + birthday);
                    empty = false;
                }
                if (empty) {
                    System.out.println("\t (no data)");
                }
            }
        }
    }

    public static void updateData(String first_name, String newEmail) throws SQLException {
        System.out.println("Data update...");
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(" UPDATE user SET email = ? WHERE first_name = ?"))
            {
                statement.setString(1, newEmail);
                statement.setString(2, first_name);;
                int rowsUpdated = statement.executeUpdate();
                System.out.println("Rows updated: " + rowsUpdated);

            }
        }
    }

    public static void deleteData(String nameExpression) throws SQLException {
        System.out.println("Deleting data...");
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("DELETE FROM user WHERE first_name LIKE ?"))
            {
                statement.setString(1, nameExpression);
                int rowsDeleted = statement.executeUpdate();
                System.out.println("Deleted rows: " + rowsDeleted);
            }
        }

    }

    public static void initDatabaseConnectionPool()  {
        dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:mariadb://localhost:3306/library");
        dataSource.setUsername("root");
        dataSource.setPassword("1234");
    }

    public static void closeDatabaseConnectionPool() {
        dataSource.close();

    }

}
