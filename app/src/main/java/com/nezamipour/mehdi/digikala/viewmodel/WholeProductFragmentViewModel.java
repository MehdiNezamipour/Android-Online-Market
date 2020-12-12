package com.nezamipour.mehdi.digikala.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nezamipour.mehdi.digikala.data.model.product.Product;
import com.nezamipour.mehdi.digikala.data.repository.ProductRepository;
import com.nezamipour.mehdi.digikala.util.enums.ConnectionState;

import java.util.List;

public class WholeProductFragmentViewModel extends ViewModel {

    private final ProductRepository mProductRepository;


    public WholeProductFragmentViewModel() {
        mProductRepository = ProductRepository.getInstance();
    }


    // for orderBy
    public void fetchOrderByProducts(String orderBy) {
        switch (orderBy) {
            case "onSale":
                mProductRepository.fetchOnSale();
                break;
            case "date":
                mProductRepository.fetchLatest();
                break;
            case "popularity":
                mProductRepository.fetchPopular();
                break;
            case "rating":
                mProductRepository.fetchTopRating();
                break;
            default:
                break;
        }
    }

    public LiveData<List<Product>> getOnSaleProducts() {
        return mProductRepository.getOnSaleProductsLiveData();
    }

    public LiveData<List<Product>> getLatestProducts() {
        return mProductRepository.getLatestProductsLiveData();
    }

    public LiveData<List<Product>> getPopularProducts() {
        return mProductRepository.getPopularProductsLiveData();
    }

    public LiveData<List<Product>> getTopRatingProducts() {
        return mProductRepository.getTopRatingProductsLiveData();
    }

    // for searching
    public void fetchSearchProducts(String search) {
        mProductRepository.fetchProductsBySearch(search);
    }

    public void searchWithSorting(String search, String orderBy, String order) {
        mProductRepository.searchWithSorting(search, orderBy, order);
    }

    public void sortCategoryProducts(Integer categoryId, String orderBy, String order) {
        mProductRepository.sortCategoryProducts(categoryId, orderBy, order);
    }


    public LiveData<List<Product>> getProductSearchLiveData() {
        return mProductRepository.getProductSearchLiveData();
    }


    //fetching category products
    public void fetchCategoryProducts(Integer categoryId) {
        mProductRepository.fetchCategoryProducts(categoryId);
    }

    public LiveData<List<Product>> getCategoryProducts() {
        return mProductRepository.getCategoryProductsLiveData();
    }

    public MutableLiveData<ConnectionState> getConnectionStateLiveData() {
        return mProductRepository.getConnectionStateLiveData();
    }
}
