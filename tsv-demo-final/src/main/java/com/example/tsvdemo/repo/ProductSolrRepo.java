package com.example.tsvdemo.repo;

import com.example.tsvdemo.model.Product;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Qualifier("productSolrRepo")
public interface ProductSolrRepo extends SolrCrudRepository<Product, String> {

    List<Product> findByColor(String color);

    Optional<Product> findBySkuId(String skuId);

    List<Product> findByProdDescription(String description);

    List<Product> findByVendor(String title);



    //Custom query using placeholder ?0 the query can have multiple fields if needed or just one
    @Query(value = "?0")
    List<Product> findByCustomQuery(String searchTerm);

}
