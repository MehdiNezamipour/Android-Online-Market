package com.nezamipour.mehdi.digikala.viewmodel;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.nezamipour.mehdi.digikala.data.database.entity.CartProduct;
import com.nezamipour.mehdi.digikala.data.model.product.Product;
import com.nezamipour.mehdi.digikala.data.repository.CartRepository;
import com.nezamipour.mehdi.digikala.data.repository.ProductRepository;
import com.nezamipour.mehdi.digikala.util.enums.ConnectionState;

public class ProductDetailViewModel extends AndroidViewModel {

    private final ProductRepository mProductRepository;
    private final CartRepository mCartRepository;
    private LiveData<Product> mProduct;

    public ProductDetailViewModel(@NonNull Application application) {
        super(application);
        mProduct = new MutableLiveData<>();
        mProductRepository = ProductRepository.getInstance();
        mCartRepository = CartRepository.getInstance(getApplication());
    }


    public void fetchProductById(Integer productId) {
        mProductRepository.fetchProductById(productId);
    }

    public LiveData<Product> getProductMutableLiveData() {
        mProduct = mProductRepository.getProductByIdMutableLiveData();
        return mProductRepository.getProductByIdMutableLiveData();
    }

    public MutableLiveData<ConnectionState> getConnectionStateLiveData() {
        return mProductRepository.getConnectionStateLiveData();
    }


    public void onClick(View v) {
        CartProduct cartProduct = new CartProduct(mProduct.getValue().getId(), "", 1);
        mCartRepository.insert(cartProduct);
    }


}
