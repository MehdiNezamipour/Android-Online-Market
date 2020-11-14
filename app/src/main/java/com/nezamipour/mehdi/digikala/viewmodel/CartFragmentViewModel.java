package com.nezamipour.mehdi.digikala.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.nezamipour.mehdi.digikala.data.model.product.Product;
import com.nezamipour.mehdi.digikala.data.repository.CartRepository;

import java.util.List;

public class CartFragmentViewModel extends AndroidViewModel {

    private final CartRepository mCartRepository;
    private LiveData<List<Product>> mProductsLiveData;

    public CartFragmentViewModel(@NonNull Application application) {
        super(application);
        mCartRepository = CartRepository.getInstance(getApplication());
        mProductsLiveData = new MutableLiveData<>();
        fetchAllProducts();
    }

    public void fetchAllProducts() {
        mProductsLiveData = mCartRepository.getAllFromCart();
    }

    public LiveData<List<Product>> getProductsLiveData() {
        return mProductsLiveData;
    }

}
