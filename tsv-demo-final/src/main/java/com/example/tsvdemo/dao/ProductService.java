package com.example.tsvdemo.dao;

import java.io.IOException;
import java.util.*;

import com.example.tsvdemo.model.Product;
import com.example.tsvdemo.repo.ProductSolrRepo;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final SolrTemplate solrTemplate;

    private final JdbcTemplate jdbcTemplate;
    private final ProductSolrRepo productSolrRepo;

    @Autowired
    public ProductService(SolrTemplate solrTemplate, ProductSolrRepo productSolrRepo, JdbcTemplate jdbcTemplate) {
        this.solrTemplate = solrTemplate;
        this.productSolrRepo = productSolrRepo;
        this.jdbcTemplate = jdbcTemplate;

    }

    public List<Product> getAllProductsByColor(String color) {
        return productSolrRepo.findByColor(color);
    }

    public List<Product> getAllProductsByDescription(String description) {
        return productSolrRepo.findByProdDescription(description);
    }

    public List<Product> getAllProductsFromDatabase() {
        List<Product> productList = new ArrayList<>();
        String sql = "SELECT * FROM products";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

            while (results.next()) {
                productList.add(mapRowToProduct(results));

            }
        } catch (Exception e) {
            System.out.println("There was an error!");

        }
        return productList;
    }


    //Used this as an alternative to loading the data
    public void indexProducts(List<Product> products) {
        List<SolrInputDocument> documents = new ArrayList<>();
        //Used fields to make sure the specific information is being sent to the solr core
        for (Product product : products) {
            SolrInputDocument document = new SolrInputDocument();
            document.addField("sku_id", product.getSkuId());
            document.addField("image", product.getImage());
            document.addField("additional_image_link", product.getAdditionalImageLink());
            document.addField("style_id", product.getStyleId());
            document.addField("class_id", product.getClassId());
            document.addField("color", product.getColor());
            document.addField("color_code", product.getColorCode());
            document.addField("color_family", product.getColorFamily());
            document.addField("size", product.getSize());
            document.addField("size_id", product.getSizeId());
            document.addField("department_id", product.getDepartmentId());
            document.addField("dissection_code", product.getDissectionCode());
            document.addField("hazmat", product.getHazmat());
            document.addField("redline", product.getRedline());
            document.addField("promoted", product.getPromoted());
            document.addField("tax_code", product.getTaxCode());
            document.addField("vendor", product.getVendor());
            document.addField("list_price", product.getListPrice());
            document.addField("sale_price", product.getSalePrice());
            document.addField("sale_price_effective_date", product.getSalePriceEffectiveDate());
            document.addField("currency", product.getCurrency());
            document.addField("shoprunner_eligible", product.isShoprunnerEligible());
            document.addField("title", product.getTitle());
            document.addField("link", product.getLink());
            document.addField("prod_description", product.getProdDescription());
            document.addField("start_date", product.getStartDate());
            document.addField("featured_color", product.getFeaturedColor());
            document.addField("featured_color_category", product.getFeaturedColorCategory());
            document.addField("related_products", product.getRelatedProducts());
            document.addField("pre_order", product.getPreOrder());
            document.addField("handling_code", product.getHandlingCode());
            document.addField("video", product.getVideo());
            document.addField("proportion", product.getProportion());
            document.addField("proportion_products", product.getProportionProducts());
            document.addField("master_style", product.getMasterStyle());
            document.addField("cannot_return", product.getCannotReturn());
            document.addField("great_find", product.getGreatFind());
            document.addField("web_exclusive", product.getWebExclusive());
            document.addField("ny_deals", product.getNyDeals());
            document.addField("promo_tagline", product.getPromoTagline());
            document.addField("partially_promoted", product.getPartiallyPromoted());
            document.addField("product_category", product.getProductCategory());
            document.addField("sort_order", product.getSortOrder());
            document.addField("quantity_sold", product.getQuantitySold());
            document.addField("average_rating", product.getAverageRating());

            documents.add(document);
        }
        solrTemplate.saveDocuments("product", documents);
        solrTemplate.commit("product");
    }

    public void loadProductsIntoSolr(List<Product> products) {
        productSolrRepo.saveAll(products);
        solrTemplate.commit("product");
    }

    //Creating the product object with sql database info
    private Product mapRowToProduct(SqlRowSet result) {
        Product product = new Product();
        product.setSkuId(result.getInt("sku_id"));
        product.setImage(result.getString("image"));
        product.setAdditionalImageLink(result.getString("additional_image_link"));
        product.setStyleId(result.getString("style_id"));
        product.setClassId(result.getString("class_id"));
        product.setColor(result.getString("color"));
        product.setColorCode(result.getString("color_code"));
        product.setColorFamily(result.getString("color_family"));
        product.setSize(result.getString("size"));
        product.setSizeId(result.getString("size_id"));
        product.setDepartmentId(result.getString("department_id"));
        product.setDissectionCode(result.getString("dissection_code"));
        product.setHazmat(Boolean.parseBoolean(result.getString("hazmat")));
        product.setRedline(Boolean.parseBoolean(result.getString("redline")));
        product.setPromoted(Boolean.parseBoolean(result.getString("promoted")));
        product.setTaxCode(result.getString("tax_code"));
        product.setVendor(result.getString("vendor"));
        product.setListPrice(result.getString("list_price"));
        product.setSalePrice(result.getString("sale_price"));
        product.setSalePriceEffectiveDate(result.getString("sale_price_effective_date"));
        product.setCurrency(result.getString("currency"));
        product.setShoprunnerEligible(Boolean.parseBoolean(result.getString("shoprunner_eligible")));
        product.setTitle(result.getString("title"));
        product.setLink(result.getString("link"));
        product.setProdDescription(result.getString("prod_description"));
        product.setStartDate(result.getString("start_date"));
        product.setFeaturedColor(Boolean.parseBoolean(result.getString("featured_color")));
        product.setFeaturedColorCategory(result.getString("featured_color_category"));
        product.setRelatedProducts(result.getString("related_products"));
        product.setPreOrder(result.getString("pre_order"));
        product.setHandlingCode(result.getString("handling_code"));
        product.setVideo(result.getString("video"));
        product.setProportion(result.getString("proportion"));
        product.setProportionProducts(result.getString("proportion_products"));
        product.setMasterStyle(result.getString("master_style"));
        product.setCannotReturn(Boolean.parseBoolean(result.getString("cannot_return")));
        product.setGreatFind(Boolean.parseBoolean(result.getString("great_find")));
        product.setWebExclusive(Boolean.parseBoolean(result.getString("web_exclusive")));
        product.setNyDeals(result.getString("ny_deals"));
        product.setPromoTagline(result.getString("promo_tagline"));
        product.setPartiallyPromoted(Boolean.parseBoolean(result.getString("partially_promoted")));
        product.setProductCategory(result.getString("product_category"));
        product.setSortOrder(result.getString("sort_order"));
        product.setQuantitySold(result.getString("quantity_sold"));
        product.setAverageRating(result.getString("average_rating"));

        return product;
    }

}