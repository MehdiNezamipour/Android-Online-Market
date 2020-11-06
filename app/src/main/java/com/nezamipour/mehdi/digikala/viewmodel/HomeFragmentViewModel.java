package com.nezamipour.mehdi.digikala.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nezamipour.mehdi.digikala.data.model.product.Product;
import com.nezamipour.mehdi.digikala.data.repository.ProductRepository;

import java.util.List;

public class HomeFragmentViewModel extends ViewModel {

    private final MutableLiveData<List<Product>> mOfferedProductsLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<Product>> mLatestProductsLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<Product>> mTopRatingProductsLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<Product>> mPopularProductsLiveData = new MutableLiveData<>();


    private final ProductRepository mProductRepository;


    public HomeFragmentViewModel() {
        mProductRepository = ProductRepository.getInstance();
    }


    public void fetchDataFromRepository() {
        fetchOfferedProductsFromRepository();
        fetchLatestProductsFromRepository();
        fetchPopularProductsFromRepository();
        fetchTopRatingProductsFromRepository();
    }

    public void fetchOfferedProductsFromRepository() {
        mOfferedProductsLiveData.setValue(mProductRepository.getOfferedProducts());
    }

    public void fetchLatestProductsFromRepository() {
        mLatestProductsLiveData.setValue(mProductRepository.getLatestProducts());
    }

    public void fetchTopRatingProductsFromRepository() {
        mTopRatingProductsLiveData.setValue(mProductRepository.getTopRatingProducts());
    }

    public void fetchPopularProductsFromRepository() {
        mPopularProductsLiveData.setValue(mProductRepository.getPopularProducts());
    }


    public LiveData<List<Product>> getOfferedProductsLiveData() {
        return mOfferedProductsLiveData;
    }

    public LiveData<List<Product>> getLatestProductsLiveData() {
        return mLatestProductsLiveData;
    }

    public LiveData<List<Product>> getTopRatingProductsLiveData() {
        return mTopRatingProductsLiveData;
    }

    public LiveData<List<Product>> getPopularProductsLiveData() {
        return mPopularProductsLiveData;
    }


}
