package com.nezamipour.mehdi.digikala.data.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "cart_product")
public class CartProduct {

    @PrimaryKey
    private Integer mProductId;
    @ColumnInfo(name = "color")
    private String mColor;
    @ColumnInfo(name = "count")
    private int mCount;


    public Integer getProductId() {
        return mProductId;
    }

    public void setProductId(Integer productId) {
        mProductId = productId;
    }

    public String getColor() {
        return mColor;
    }

    public void setColor(String color) {
        mColor = color;
    }

    public int getCount() {
        return mCount;
    }

    public void setCount(int count) {
        mCount = count;
    }

    @Ignore
    public CartProduct(Integer productId, String color, int count) {
        mProductId = productId;
        mColor = color;
        mCount = count;
    }


    public CartProduct() {
    }
}
