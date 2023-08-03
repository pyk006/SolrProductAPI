package com.example.tsvdemo.util;

import com.example.tsvdemo.exception.FileException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TsvReader {
    public static List<String[]> readTsv(File test2) {
        List<String[]> tsvData = new ArrayList<>();
        try (BufferedReader TSVReader = new BufferedReader(new FileReader(test2))) {
            String eachLine;
            //Read thru each line of the TSV file looping through as long as there is a line to read
            while ((eachLine = TSVReader.readLine()) != null) {
                //Split each line using the tab delimiter (Each line will be an array that will represent one single product ex: lineItems[0] will be sku_id: 30098820,lineItems[1] will be image: "url.com", etc)
                String[] lineItems = eachLine.split("\t");
                // Each product will then be added to the List tsvData which will hold multiple product arrays
                tsvData.add(lineItems);
            }
        } catch (FileNotFoundException e) {
            //more specific error catches with custom exception
            throw new FileException("File not found, please make sure the path is correct", e);
        } catch (IOException e) {
            throw new FileException("File can't be read", e);
        } catch (Exception e) {
            //Generic error catch
            System.out.println("Error occurred with reading the file: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        return tsvData;
    }
}
