package com.nezamipour.mehdi.digikala.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nezamipour.mehdi.digikala.adapter.ProductRecyclerAdapter;
import com.nezamipour.mehdi.digikala.data.model.product.Product;
import com.nezamipour.mehdi.digikala.data.repository.ProductRepository;

import java.util.List;

public class MainFragmentViewModel extends AndroidViewModel {

    private final MutableLiveData<List<Product>> mOfferedProductsLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<Product>> mLatestProductsLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<Product>> mTopRatingProductsLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<Product>> mPopularProductsLiveData = new MutableLiveData<>();

    private ProductRecyclerAdapter mOfferedProductsAdapter;
    private ProductRecyclerAdapter mLatestProductsAdapter;
    private ProductRecyclerAdapter mTopRatingProductsAdapter;
    private ProductRecyclerAdapter mPopularProductsAdapter;


    private ProductRepository mProductRepository;


    public MainFragmentViewModel(@NonNull Application application) {
        super(application);
        mProductRepository = ProductRepository.getInstance();
        fetchOfferedProductsFromRepository();
        fetchLatestProductsFromRepository();
        fetchTopRatingProductsFromRepository();
        fetchPopularProductsFromRepository();
    }

    public void initAdapters() {
        mOfferedProductsAdapter = new ProductRecyclerAdapter(getApplication());
        mOfferedProductsAdapter.setProducts(mOfferedProductsLiveData.getValue());

        mLatestProductsAdapter = new ProductRecyclerAdapter(getApplication());
        mLatestProductsAdapter.setProducts(mLatestProductsLiveData.getValue());

        mTopRatingProductsAdapter = new ProductRecyclerAdapter(getApplication());
        mTopRatingProductsAdapter.setProducts(mTopRatingProductsLiveData.getValue());

        mPopularProductsAdapter = new ProductRecyclerAdapter(getApplication());
        mPopularProductsAdapter.setProducts(mPopularProductsLiveData.getValue());
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

    public ProductRecyclerAdapter getOfferedProductsAdapter() {
        return mOfferedProductsAdapter;
    }

    public ProductRecyclerAdapter getLatestProductsAdapter() {
        return mLatestProductsAdapter;
    }

    public ProductRecyclerAdapter getTopRatingProductsAdapter() {
        return mTopRatingProductsAdapter;
    }

    public ProductRecyclerAdapter getPopularProductsAdapter() {
        return mPopularProductsAdapter;
    }
}
