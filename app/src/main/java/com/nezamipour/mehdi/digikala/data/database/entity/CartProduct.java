package com.nezamipour.mehdi.digikala.data.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "cart_product")
public class CartProduct {

    @PrimaryKey(autoGenerate = true)
    public int mId;
    @ColumnInfo(name = "productId")
    public int mProductId;
    @ColumnInfo(name = "color")
    public String mColor;
    @ColumnInfo(name = "count")
    public int mCount;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getProductId() {
        return mProductId;
    }

    public void setProductId(int productId) {
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
    public CartProduct(int productId, String color, int count) {
        mProductId = productId;
        mColor = color;
        mCount = count;
    }

    public CartProduct() {
    }
}
