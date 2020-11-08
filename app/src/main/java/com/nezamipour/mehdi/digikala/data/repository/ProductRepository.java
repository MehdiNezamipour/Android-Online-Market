package com.nezamipour.mehdi.digikala.data.repository;

import com.nezamipour.mehdi.digikala.data.model.product.Category;
import com.nezamipour.mehdi.digikala.data.model.product.Product;

import java.util.List;

public class ProductRepository {

    //singleton

    private static ProductRepository sRepository;
    private List<Product> mAllProducts;
    private List<Product> mOfferedProducts;
    private List<Product> mLatestProducts;
    private List<Product> mTopRatingProducts;
    private List<Product> mPopularProducts;

    private List<Category> mAllCategories;



    private ProductRepository() {
    }

    public static ProductRepository getInstance() {
        if (sRepository == null) {
            sRepository = new ProductRepository();
        }
        return sRepository;
    }


    public List<Product> getAllProducts() {
        return mAllProducts;
    }

    public void setAllProducts(List<Product> allProducts) {
        mAllProducts = allProducts;
    }

    public List<Product> getOfferedProducts() {
        return mOfferedProducts;
    }

    public void setOfferedProducts(List<Product> offeredProducts) {
        mOfferedProducts = offeredProducts;
    }

    public List<Product> getLatestProducts() {
        return mLatestProducts;
    }

    public void setLatestProducts(List<Product> latestProducts) {
        mLatestProducts = latestProducts;
    }

    public List<Product> getTopRatingProducts() {
        return mTopRatingProducts;
    }

    public void setTopRatingProducts(List<Product> bestProducts) {
        mTopRatingProducts = bestProducts;
    }

    public List<Product> getPopularProducts() {
        return mPopularProducts;
    }

    public void setPopularProducts(List<Product> popularProducts) {
        mPopularProducts = popularProducts;
    }

    public List<Category> getAllCategories() {
        return mAllCategories;
    }

    public void setAllCategories(List<Category> allCategories) {
        mAllCategories = allCategories;
    }
}
