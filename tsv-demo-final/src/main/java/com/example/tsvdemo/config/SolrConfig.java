package com.example.tsvdemo.config;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

@Configuration
@EnableSolrRepositories(basePackages = "com.example.tsvdemo.repo")
@ComponentScan
public class SolrConfig {

    @Bean
    public HttpSolrClient solrClient() {
        return new HttpSolrClient.Builder("http://localhost:8983/solr").build();
    }
    @Bean
    public SolrTemplate solrTemplate() {
        SolrTemplate solrTemplate = new SolrTemplate(solrClient());
        return solrTemplate;
    }



}