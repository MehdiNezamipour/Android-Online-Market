package com.nezamipour.mehdi.digikala.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.nezamipour.mehdi.digikala.data.model.product.Product;
import com.nezamipour.mehdi.digikala.data.repository.ProductRepository;
import com.nezamipour.mehdi.digikala.util.enums.ConnectionState;

public class ProductDetailViewModel extends AndroidViewModel {

    private final ProductRepository mProductRepository;


    public ProductDetailViewModel(@NonNull Application application) {
        super(application);
        mProductRepository = ProductRepository.getInstance();
    }


    public void fetchProductById(Integer productId) {
        mProductRepository.fetchProductById(productId);
    }

    public LiveData<Product> getProductMutableLiveData() {
        return mProductRepository.getProductByIdMutableLiveData();
    }

    public MutableLiveData<ConnectionState> getConnectionStateLiveData() {
        return mProductRepository.getConnectionStateLiveData();
    }

}
