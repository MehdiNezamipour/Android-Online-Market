package com.nezamipour.mehdi.digikala.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nezamipour.mehdi.digikala.data.model.product.Product;
import com.nezamipour.mehdi.digikala.data.repository.ProductRepository;
import com.nezamipour.mehdi.digikala.util.enums.ConnectionState;

import java.util.List;

public class CategoryProductsLoadingFragmentViewModel extends ViewModel {

    private final ProductRepository mProductRepository;


    public CategoryProductsLoadingFragmentViewModel() {
        mProductRepository = ProductRepository.getInstance();
    }

    public void fetchCategoryProducts(Integer productId) {
        mProductRepository.fetchCategoryProducts(productId);
    }

    public LiveData<List<Product>> getCategoryProducts() {
        return mProductRepository.getCategoryProductsLiveData();
    }

    public MutableLiveData<ConnectionState> getConnectionStateLiveData() {
        return mProductRepository.getConnectionStateLiveData();
    }
}
