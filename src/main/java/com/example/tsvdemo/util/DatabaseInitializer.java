package com.example.tsvdemo.util;


import com.example.tsvdemo.exception.DatabaseException;

import java.sql.*;

public class DatabaseInitializer {
    private Connection connection;

    public DatabaseInitializer(String url, String username, String password) throws SQLException {
        this.connection = DriverManager.getConnection(url, username, password);
    }

    public void dropDatabase(String databaseName) {
        String sqlDrop = "DROP DATABASE IF EXISTS " + databaseName;

        try (Statement statement = connection.createStatement()) {
            // Execute the SQL statement to drop the database
            statement.executeUpdate(sqlDrop);
            System.out.println("Database has been dropped");
        } catch (SQLException e) {
            //Opted not to end program on error and instead continue program
            throw new DatabaseException("Failed to execute drop database", e);
        }
    }

    public void createDatabase(String databaseName) {

        //Creates database with databaseName parameter
        String sql = "CREATE DATABASE IF NOT EXISTS " + databaseName;

        try (Statement statement = connection.createStatement()) {
            // sql statement will be executed and will be successful if the status is not 0
            int status = statement.executeUpdate(sql);
            //check to make sure the database is created (if status is 0 then it hasn't been)
            if (status > 0) {
                System.out.println("Database has been created");
            }
            statement.execute("USE " + databaseName);
        } catch (SQLException e) {
            throw new DatabaseException("Failed to execute create", e);
        }
    }
}
