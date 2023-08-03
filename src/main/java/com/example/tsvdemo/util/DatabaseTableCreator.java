package com.example.tsvdemo.util;

import com.example.tsvdemo.exception.DatabaseException;
import com.example.tsvdemo.exception.StatementException;

import java.sql.*;
import java.util.*;
public class DatabaseTableCreator {
    private Connection connection;

    public DatabaseTableCreator(String jdbcUrl, String username, String password) throws SQLException {
        this.connection = DriverManager.getConnection(jdbcUrl, username, password);
    }

    public void createTable(String[] arrayOfColumnNames) {
        String dropTableSQL = "DROP TABLE IF EXISTS products";
        String createTableSQL = "CREATE TABLE IF NOT EXISTS products (" + arrayOfColumnNames[0] + " INT PRIMARY KEY";
        //String concat for our create statement
        for (int i = 1; i < arrayOfColumnNames.length; i++) {
            if (arrayOfColumnNames[i].contains("-")) {
                arrayOfColumnNames[i] = arrayOfColumnNames[i].replace("-", "_");
            }
            createTableSQL += ", " + "`" + arrayOfColumnNames[i] + "`" + " TEXT DEFAULT NULL";

        }
        createTableSQL += ")";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(dropTableSQL);
            //Currently executes a createTableSql that concatenates all the array columns and sets them as type text and default null (could potentially be better to use a preparedstatement with placeholders for each column name)
            statement.executeUpdate(createTableSQL);
        } catch (SQLException e) {
            throw new DatabaseException("Failed to execute table create/drop", e);
        }
    }

    public void insertInto(String[] arrayOfColumnNames, List<String[]> data) {
        // insert table data from the data List ignoring the first index because it's the table create sql
        String columnNamesForInsert = "";
        for (String column : data.get(0)) {
            //dissection-code naming convention will cause problems so I opted to just change it to the underscore naming convention
            if (column.contains("-")) {
                column = column.replace("-", "_");
            }
            columnNamesForInsert += column  + ",";
        }
        //removing my trailing comma
        if (columnNamesForInsert.endsWith(",")) {
            columnNamesForInsert = columnNamesForInsert.substring(0, columnNamesForInsert.length() - 1);
        }
        //Used ignore to make sure duplicate sku_ids are ignored
        //Insert using the concatenated columnNamesForInsert
        String insertSQL = "INSERT IGNORE INTO products  (" + columnNamesForInsert + ") VALUES (?" + ", ?".repeat(arrayOfColumnNames.length - 1) + ")";

        //Opting to use prepared statement to handle special characters inside of the tsv values, tried without it and had an error
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);

            //Loops through data list which holds on information read from the TSV file split as individual lines representing a single product (starts at index of 1 because index 0 is the column names)
            for (int i = 1; i < data.size(); i++) {
                String[] rowData = data.get(i);
                for (int j = 0; j < rowData.length; j++) {
                    //Looping through and inserting the place holder values (?) 1-index in sql so adding j + 1
                    //rowData array will contain the data for each products information like sku_id, image, additional_image_link
                    preparedStatement.setString(j + 1, rowData[j]);
                }
                preparedStatement.executeUpdate();
            }
            System.out.println("Table has been created!");
        } catch (SQLException e) {
            throw new StatementException("Error occurred with statement", e);
        }
    }

}
