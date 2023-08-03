package com.example.tsvdemo.util;

import com.example.tsvdemo.dao.ProductService;
import com.example.tsvdemo.model.Product;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.request.UpdateRequest;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.util.List;

public class SolrCoreCleaner {
    public void cleanLoad(String solrUrl, ConfigurableApplicationContext context) throws IOException {
        SolrClient solrClient = new HttpSolrClient.Builder(solrUrl).build();
        try {
            //Using the select all query *:* to delete everything inside of the solr core currently, so it will be clean when the program runs again
            UpdateRequest updateRequest = new UpdateRequest();
            updateRequest.deleteByQuery("*:*");
            updateRequest.setCommitWithin(1000);
            updateRequest.process(solrClient);
            System.out.println("Solr core cleaned successfully.");
        } catch (Exception e) {
            System.out.println("Error occurred while cleaning the Solr index: " + e.getMessage());
        } finally {
            solrClient.close();
        }
        ProductService productService = context.getBean(ProductService.class);
        //Get all products from the database
        List<Product> products = productService.getAllProductsFromDatabase();
        //Load all the products from the database into solr
        productService.loadProductsIntoSolr(products);

    }
}
