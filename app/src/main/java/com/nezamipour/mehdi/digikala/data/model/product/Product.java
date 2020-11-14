
package com.nezamipour.mehdi.digikala.data.model.product;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

@Entity (tableName = "product")
public class Product implements Serializable {

    @SerializedName("id")
    @Expose
    @PrimaryKey
    public Integer id;
    @SerializedName("name")
    @Expose
    @ColumnInfo(name = "name")
    public String name;
    @SerializedName("slug")
    @Expose
    @Ignore
    private String slug;
    @SerializedName("type")
    @Expose
    @Ignore
    private String type;
    @SerializedName("status")
    @Expose
    @Ignore
    private String status;
    @SerializedName("featured")
    @Expose
    @Ignore
    private Boolean featured;
    @SerializedName("description")
    @Expose
    @ColumnInfo(name = "description")
    public String description;
    @SerializedName("short_description")
    @Expose
    @ColumnInfo(name = "short_description")
    public String shortDescription;
    @SerializedName("price")
    @Expose
    @Ignore
    private String price;
    @SerializedName("regular_price")
    @Expose
    @Ignore
    private String regularPrice;
    @SerializedName("sale_price")
    @Expose
    @ColumnInfo(name = "sale_price")
    public String salePrice;
    @SerializedName("on_sale")
    @Expose
    @Ignore
    private Boolean onSale;
    @SerializedName("purchasable")
    @Expose
    @Ignore
    private Boolean purchasable;
    @SerializedName("total_sales")
    @Expose
    @Ignore
    private Integer totalSales;

    @SerializedName("average_rating")
    @Expose
    @Ignore
    private String averageRating;
    @SerializedName("rating_count")
    @Expose
    @Ignore
    private Integer ratingCount;
    @SerializedName("related_ids")
    @Expose
    @Ignore
    private List<Integer> relatedIds = null;

    @SerializedName("categories")
    @Expose
    @Ignore
    private List<Category> categories = null;
    @SerializedName("tags")
    @Expose
    @Ignore
    private List<Tag> tags = null;
    @SerializedName("images")
    @Expose
    @Ignore
    private List<Image> images = null;
    @SerializedName("attributes")
    @Expose
    @Ignore
    private List<Attribute> attributes = null;
    @SerializedName("default_attributes")
    @Expose
    @Ignore
    private List<Attribute> defaultAttributes = null;

    private final static long serialVersionUID = -5140714000554515510L;


    public Product(Integer id, String name, String description, String shortDescription, String salePrice) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.shortDescription = shortDescription;
        this.salePrice = salePrice;
    }

    public Product() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getFeatured() {
        return featured;
    }

    public void setFeatured(Boolean featured) {
        this.featured = featured;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }


    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRegularPrice() {
        return regularPrice;
    }

    public void setRegularPrice(String regularPrice) {
        this.regularPrice = regularPrice;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public Boolean getOnSale() {
        return onSale;
    }

    public void setOnSale(Boolean onSale) {
        this.onSale = onSale;
    }

    public Boolean getPurchasable() {
        return purchasable;
    }

    public void setPurchasable(Boolean purchasable) {
        this.purchasable = purchasable;
    }

    public Integer getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(Integer totalSales) {
        this.totalSales = totalSales;
    }

    public String getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(String averageRating) {
        this.averageRating = averageRating;
    }

    public Integer getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(Integer ratingCount) {
        this.ratingCount = ratingCount;
    }

    public List<Integer> getRelatedIds() {
        return relatedIds;
    }

    public void setRelatedIds(List<Integer> relatedIds) {
        this.relatedIds = relatedIds;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public List<Attribute> getDefaultAttributes() {
        return defaultAttributes;
    }

    public void setDefaultAttributes(List<Attribute> defaultAttributes) {
        this.defaultAttributes = defaultAttributes;
    }


}
