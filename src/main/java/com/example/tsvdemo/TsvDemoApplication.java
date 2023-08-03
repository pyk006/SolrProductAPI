package com.example.tsvdemo;

import com.example.tsvdemo.exception.DatabaseException;
import com.example.tsvdemo.util.DatabaseInitializer;
import com.example.tsvdemo.util.DatabaseTableCreator;
import com.example.tsvdemo.util.SolrCoreCleaner;
import com.example.tsvdemo.util.TsvReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.*;

//Create the database first, I called it products_tsv in the application.properties
//Please make sure username, password matches your MySQL database info before running program and create solr core called product
@SpringBootApplication
public class TsvDemoApplication {
    //Using Spring's injection so that I can use application.properties file instead of having it inputted manually like I had before (Please fill in this information in the application.properties)
    @Value("${spring.datasource.database}")
    private String database;
    @Value("${spring.datasource.url}")
    private String jdbcUrl;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.sqlUrl}")
    private String sqlUrl;


    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(TsvDemoApplication.class, args);
        //Using the bean to access the instance variables jdbcUrl, username, password, database
        TsvDemoApplication tsvDemoApplication = context.getBean(TsvDemoApplication.class);

        // Creating an object of the TsvReader class that will read the Product_feed.tsv file using .readTsv method
        TsvReader tsvReader = new TsvReader();
        File tsvFile = new File("Product_feed.tsv");
        List<String[]> data = tsvReader.readTsv(tsvFile);
        //Create the Database using the databaseInitializer object and inputting in the sqlUrl(localhost url without the database name), username, and password for the MySQL database
        try {
            DatabaseInitializer databaseInitializer = new DatabaseInitializer(tsvDemoApplication.sqlUrl, tsvDemoApplication.username, tsvDemoApplication.password);
			//Drops the database if it exists to start fresh (Might not need this alternatively)
            databaseInitializer.dropDatabase(tsvDemoApplication.database);
			//Creates the database if it does not exist
            databaseInitializer.createDatabase(tsvDemoApplication.database);

			//Create an object of the DatabaseTableCreator object to create the tables for products_tsv database
            DatabaseTableCreator databaseTableCreator = new DatabaseTableCreator(tsvDemoApplication.jdbcUrl, tsvDemoApplication.username, tsvDemoApplication.password);

            String[] arrayOfColumnNames = data.get(0); //represents the column names for create sql statement ex (sku_id, image, additional_image_link, style_id, class_id, color, etc)

            // using databaseInitializer method createTable will first drop table if it exists then execute the createTable sql statement
            databaseTableCreator.createTable(arrayOfColumnNames);
            // using databaseInitializer method insertInto will insert data into the new table by executing a preparedstatement sql
            databaseTableCreator.insertInto(arrayOfColumnNames, data);
        } catch (SQLSyntaxErrorException e) {
            throw new DatabaseException("Failed to access database:", e);
        } catch (SQLException e) {
            throw new DatabaseException("Failed to create database/tables", e);
        }


        String solrUrl = "http://localhost:8983/solr/product";
        SolrCoreCleaner solrCoreCleaner = new SolrCoreCleaner();
        //Cleaning the Solr core by using a deleteByQuery with select all using the object solrCoreCleaner with a cleanLoad method,
        //the method will load the product data from the database into the solr core too
        try {
            solrCoreCleaner.cleanLoad(solrUrl, context);
            System.out.println("Database information loaded into Solr successfully!");
        } catch (IOException e) {
            throw new DatabaseException("Error occurred while loading in data to solr: ", e);
        }

    }
}
