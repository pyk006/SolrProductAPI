package com.example.tsvdemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import org.springframework.stereotype.Component;

// the core name that i created in the annotation

@SolrDocument(solrCoreName = "product")
public class Product {

    @Id
    @Indexed(name = "sku_id", type = "int")
    private int skuId;
    @Field("image")
    @Indexed(name = "image", type = "string")
    private String image;
    @Field("additional_image_link")
    private String additionalImageLink;
    @Field("style_id")
    private String styleId;
    @Field("class_id")
    private String classId;
    @Field("color")
    private String color;
    @Field("color_code")
    private String colorCode;
    @Field("color_family")
    private String colorFamily;

    @Field("size")
    @Indexed(name = "size", type = "string")
    private String size;
    @Field("size_id")
    private String sizeId;
    @Field("department_id")
    private String departmentId;
    @Field("dissection_code")
    @Indexed(name = "dissection_code", type = "string")
    private String dissectionCode;
    @Field("hazmat")
    private boolean hazmat;
    @Field("redline")
    private boolean redline;
    @Field("promoted")
    private boolean promoted;
    @Field("tax_code")
    @Indexed(name = "tax_code", type = "string")
    private String taxCode;
    @Field("vendor")
    private String vendor;
    @Field("list_price")
    private String listPrice;
    @Field("sale_price")
    @Indexed(name = "sale_price", type = "string")
    private String salePrice;
    @Field("sale_price_effective_date")
    @Indexed(name = "sale_price_effective_date", type = "string")
    private String salePriceEffectiveDate;
    @Field("currency")
    private String currency;
    @Field("shoprunner_eligible")
    private boolean shoprunnerEligible;
    @Field("title")
    private String title;
    @Field("link")
    private String link;
    @Field("prod_description")
    private String prodDescription;
    @Field("start_date")
    private String startDate;
    @Field("featured_color")
    private boolean featuredColor;
    @Field("featured_color_category")
    @Indexed(name = "featured_color_category", type = "string")
    private String featuredColorCategory;
    @Field("related_products")
    @Indexed(name = "related_products", type = "string")
    private String relatedProducts;
    @Field("pre_order")
    @Indexed(name = "pre_order", type = "string")
    private String preOrder;
    @Field("handling_code")
    @Indexed(name = "handling_code", type = "string")
    private String handlingCode;
    @Field("video")
    private String video;
    @Field("proportion")
    private String proportion;
    @Field("proportion_products")
    @Indexed(name = "proportion_products", type = "string")
    private String proportionProducts;
    @Field("master_style")
    private String masterStyle;
    @Field("cannot_return")
    private boolean cannotReturn;
    @Field("great_find")
    private boolean greatFind;
    @Field("web_exclusive")
    private boolean webExclusive;
    @Field("ny_deals")
    private String nyDeals;
    @Field("promo_tagline")
    @Indexed(name = "promo_tagline", type = "string")
    private String promoTagline;
    @Field("partially_promoted")
    private boolean partiallyPromoted;
    @Field("product_category")
    private String productCategory;
    @Field("sort_order")
    private String sortOrder;
    @Field("quantity_sold")
    private String quantitySold;
    @Field("average_rating")
    private String averageRating;

public Product() {

}
    public Product(int skuId, String image, String additionalImageLink, String styleId, String classId, String color, String colorCode, String colorFamily, String size, String sizeId, String departmentId, String dissectionCode, boolean hazmat, boolean redline, boolean promoted, String taxCode, String vendor, String listPrice, String salePrice, String salePriceEffectiveDate, String currency, boolean
            shoprunnerEligible, String title, String link, String prodDescription, String startDate, boolean featuredColor, String featuredColorCategory, String relatedProducts, String preOrder, String handlingCode, String video, String proportion, String proportionProducts, String masterStyle, boolean cannotReturn, boolean greatFind, boolean webExclusive, String nyDeals, String promoTagline, boolean partiallyPromoted, String productCategory, String sortOrder, String quantitySold, String averageRating) {
        this.skuId = skuId;
        this.image = image;
        this.additionalImageLink = additionalImageLink;
        this.styleId = styleId;
        this.classId = classId;
        this.color = color;
        this.colorCode = colorCode;
        this.colorFamily = colorFamily;
        this.size = size;
        this.sizeId = sizeId;
        this.departmentId = departmentId;
        this.dissectionCode = dissectionCode;
        this.hazmat = hazmat;
        this.redline = redline;
        this.promoted = promoted;
        this.taxCode = taxCode;
        this.vendor = vendor;
        this.listPrice = listPrice;
        this.salePrice = salePrice;
        this.salePriceEffectiveDate = salePriceEffectiveDate;
        this.currency = currency;
        this.shoprunnerEligible = shoprunnerEligible;
        this.title = title;
        this.link = link;
        this.prodDescription = prodDescription;
        this.startDate = startDate;
        this.featuredColor = featuredColor;
        this.featuredColorCategory = featuredColorCategory;
        this.relatedProducts = relatedProducts;
        this.preOrder = preOrder;
        this.handlingCode = handlingCode;
        this.video = video;
        this.proportion = proportion;
        this.proportionProducts = proportionProducts;
        this.masterStyle = masterStyle;
        this.cannotReturn = cannotReturn;
        this.greatFind = greatFind;
        this.webExclusive = webExclusive;
        this.nyDeals = nyDeals;
        this.promoTagline = promoTagline;
        this.partiallyPromoted = partiallyPromoted;
        this.productCategory = productCategory;
        this.sortOrder = sortOrder;
        this.quantitySold = quantitySold;
        this.averageRating = averageRating;
    }

    public int getSkuId() {
        return skuId;
    }

    public void setSkuId(int skuId) {
        this.skuId = skuId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAdditionalImageLink() {
        return additionalImageLink;
    }

    public void setAdditionalImageLink(String additionalImageLink) {
        this.additionalImageLink = additionalImageLink;
    }

    public String getStyleId() {
        return styleId;
    }

    public void setStyleId(String styleId) {
        this.styleId = styleId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public String getColorFamily() {
        return colorFamily;
    }

    public void setColorFamily(String colorFamily) {
        this.colorFamily = colorFamily;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSizeId() {
        return sizeId;
    }

    public void setSizeId(String sizeId) {
        this.sizeId = sizeId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDissectionCode() {
        return dissectionCode;
    }

    public void setDissectionCode(String dissectionCode) {
        this.dissectionCode = dissectionCode;
    }

    public boolean getHazmat() {
        return hazmat;
    }

    public void setHazmat(boolean hazmat) {
        this.hazmat = hazmat;
    }

    public boolean getRedline() {
        return redline;
    }

    public void setRedline(boolean redline) {
        this.redline = redline;
    }

    public boolean getPromoted() {
        return promoted;
    }

    public void setPromoted(boolean promoted) {
        this.promoted = promoted;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getListPrice() {
        return listPrice;
    }

    public void setListPrice(String listPrice) {
        this.listPrice = listPrice;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public String getSalePriceEffectiveDate() {
        return salePriceEffectiveDate;
    }

    public void setSalePriceEffectiveDate(String salePriceEffectiveDate) {
        this.salePriceEffectiveDate = salePriceEffectiveDate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public boolean isShoprunnerEligible() {
        return shoprunnerEligible;
    }

    public void setShoprunnerEligible(boolean shoprunnerEligible) {
        this.shoprunnerEligible = shoprunnerEligible;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getProdDescription() {
        return prodDescription;
    }

    public void setProdDescription(String prodDescription) {
        this.prodDescription = prodDescription;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public boolean getFeaturedColor() {
        return featuredColor;
    }

    public void setFeaturedColor(boolean featuredColor) {
        this.featuredColor = featuredColor;
    }

    public String getFeaturedColorCategory() {
        return featuredColorCategory;
    }

    public void setFeaturedColorCategory(String featuredColorCategory) {
        this.featuredColorCategory = featuredColorCategory;
    }

    public String getRelatedProducts() {
        return relatedProducts;
    }

    public void setRelatedProducts(String relatedProducts) {
        this.relatedProducts = relatedProducts;
    }

    public String getPreOrder() {
        return preOrder;
    }

    public void setPreOrder(String preOrder) {
        this.preOrder = preOrder;
    }

    public String getHandlingCode() {
        return handlingCode;
    }

    public void setHandlingCode(String handlingCode) {
        this.handlingCode = handlingCode;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getProportion() {
        return proportion;
    }

    public void setProportion(String proportion) {
        this.proportion = proportion;
    }

    public String getProportionProducts() {
        return proportionProducts;
    }

    public void setProportionProducts(String proportionProducts) {
        this.proportionProducts = proportionProducts;
    }

    public String getMasterStyle() {
        return masterStyle;
    }

    public void setMasterStyle(String masterStyle) {
        this.masterStyle = masterStyle;
    }

    public boolean getCannotReturn() {
        return cannotReturn;
    }

    public void setCannotReturn(boolean cannotReturn) {
        this.cannotReturn = cannotReturn;
    }

    public boolean getGreatFind() {
        return greatFind;
    }

    public void setGreatFind(boolean greatFind) {
        this.greatFind = greatFind;
    }

    public boolean getWebExclusive() {
        return webExclusive;
    }

    public void setWebExclusive(boolean webExclusive) {
        this.webExclusive = webExclusive;
    }

    public String getNyDeals() {
        return nyDeals;
    }

    public void setNyDeals(String nyDeals) {
        this.nyDeals = nyDeals;
    }

    public String getPromoTagline() {
        return promoTagline;
    }

    public void setPromoTagline(String promoTagline) {
        this.promoTagline = promoTagline;
    }

    public boolean getPartiallyPromoted() {
        return partiallyPromoted;
    }

    public void setPartiallyPromoted(boolean partiallyPromoted) {
        this.partiallyPromoted = partiallyPromoted;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(String quantitySold) {
        this.quantitySold = quantitySold;
    }

    public String getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(String averageRating) {
        this.averageRating = averageRating;
    }
    @Override
    public String toString() {
        return "Product{" +
                "skuId=" + skuId +
                ", salePrice='" + salePrice + '\'' +

                '}';
    }
}
