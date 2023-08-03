# TsvDemo Application

TsvDemo is a Java Spring / Apache Solr application for indexing/searching a database with solr queries.

## Features
1. Data from the TSV file will be used to create a database and it's tables
2. The program will load data from the database into solr
3. Access information about products using the API Endpoints using solr queries

## API Endpoints

    - /id/{skuId}
    - /color/{color}
    - /description/{description}
    - /vendor/{vendor}
    - /search?{fieldName}={fieldValue}  
    Add as many fields using "&" with same params


## Dependencies
- Java 17
- org.springframework.boot:spring-boot-starter (Version: 2.5.5)
- org.springframework.boot:spring-boot-starter-test (Version: 2.5.5)
- org.springframework.boot:spring-boot-starter-web (Version: 2.5.5)
- org.springframework.boot:spring-boot-starter-data-solr (Version: 2.4.12)
- org.springframework.data:spring-data-solr (Version: 4.3.14)
- org.apache.solr:solr-solrj (Version: 8.11.2)
- org.springframework.boot:spring-boot-starter-jdbc (Version: 2.5.5)
- mysql:mysql-connector-java (Version: 8.0.33)

## How to Setup

1. Use the managed-schema.xml file provided (replace for configsets )

Here's an example of where configsets is found:
```bash
solr-9.2.1\server\solr\configsets
```

2. Open up cmd and change to solr bin directory. Create the solr core called: product.

```bash
cd yourdirectory\solr-9.2.1\bin
```
```bash 
solr.cmd create -c product
```

2. Start the solr server in the same directory.
```bash
solr.cmd start
```
3. Open up the TsvDemo Project using the pom.xml

4. In the resources folder, navigate to the application.properties file
 
    Input username and password for your MySQL server

        spring.datasource.username=your_username

        spring.datasource.password=your_password

5. Run using TSVDemoApplication.java
