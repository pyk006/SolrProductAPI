package com.example.tsvdemo.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

import com.example.tsvdemo.dao.ProductService;
import com.example.tsvdemo.model.Product;
import com.example.tsvdemo.repo.ProductSolrRepo;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.http.server.RequestPath;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriUtils;

@RestController
public class ProductController {

    private final ProductSolrRepo productSolrRepo;
    private final ProductService productService;
    @Autowired
    private SolrTemplate solrTemplate;

    @Autowired
    public ProductController(ProductSolrRepo productSolrRepo, ProductService productService) {
        this.productSolrRepo = productSolrRepo;
        this.productService = productService;
    }

    @GetMapping("/id/{skuId}")
    public Optional<Product> getProductBySkuId(@PathVariable String skuId) {
        //Using the solrcrudrepository for the search query by sku id
        return productSolrRepo.findBySkuId(skuId);
    }

    @GetMapping("/color/{color}")
    public List<Product> getProductsByColor(@PathVariable String color) {
        //Using the solrcrudrepository but adding an extra layer of abstraction by using a method in ProductService to access the repo
        return productService.getAllProductsByColor(color.toUpperCase());
    }

    @GetMapping("/description/{description}")
    public List<Product> getProductsByDescription(@PathVariable String description) {
        //Using the solrcrudrepository but adding an extra layer of abstraction by using a method in ProductService to access the repo
        return productService.getAllProductsByDescription(description);
    }

    @GetMapping("vendor/{vendor}")
    public List<Product> getProductsByVendor(@PathVariable String vendor) {
        //Using the solrcrudrepository for the search query by vendor
        return productSolrRepo.findByVendor(vendor);
    }

    @GetMapping("/search")
    public List<Product> searchProducts(@RequestParam Map<String, String> searchTerms) {
        //The endpoint will create a Map with key of FieldName and value of FieldValue for the search query ex : color=blue size=small prod_description=jacket
        //The map will then be used in a createQueryString method
        String dynamicQuery = createQueryString(searchTerms);
        //the dynamic query will then be used as the query for the findByCustomQuery query placeholder
        return productSolrRepo.findByCustomQuery(dynamicQuery);
    }
    private String createQueryString(Map<String, String> searchTerms) {
        String solrQuery = "";
    //This method will create a concatenated String as we loop through the map and using the AND keyword if theres multiple queries
        for (String fieldName : searchTerms.keySet()) {
            String fieldValue = searchTerms.get(fieldName);

            if (solrQuery.length() > 0) {
                solrQuery += " AND ";
            }
            //Creating the solr query and concatenating with AND if multiple
            //Using the contains wildcard by adding the stars in between
            String queryWithFields = fieldName + ":" + "*" + fieldValue + "*";
            solrQuery += queryWithFields;
        }
        // so if i used color, size, prod_description it would show the correct solr query with color:BLUE AND size:SMALL AND prod_description:jacket
        return solrQuery;
    }


}
