package com.nezamipour.mehdi.digikala.data.database.entitiy;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CardProduct {

    @PrimaryKey
    private Integer id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "short_description")
    public String shortDescription;

    @ColumnInfo(name = "price")
    public String price;

    @ColumnInfo(name = "regular_price")
    public String regularPrice;

    @ColumnInfo(name = "sale_price")
    public String salePrice;


    public CardProduct(Integer id, String name, String shortDescription, String price, String regularPrice, String salePrice) {
        this.id = id;
        this.name = name;
        this.shortDescription = shortDescription;
        this.price = price;
        this.regularPrice = regularPrice;
        this.salePrice = salePrice;
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

}
