package com.nezamipour.mehdi.digikala.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.nezamipour.mehdi.digikala.data.model.product.Product;
import com.nezamipour.mehdi.digikala.data.repository.CartRepository;
import com.nezamipour.mehdi.digikala.util.enums.ConnectionState;

import java.util.List;

public class CartFragmentViewModel extends AndroidViewModel {

    private final CartRepository mCartRepository;



    public CartFragmentViewModel(@NonNull Application application) {
        super(application);
        mCartRepository = CartRepository.getInstance(application);
    }

    public void fetchCartProducts() {
        mCartRepository.fetchCartProducts();
    }

    public LiveData<List<Product>> getCartProducts() {
        return mCartRepository.getProductsLiveData();
    }

    public LiveData<ConnectionState> getConnectionStateLiveData() {
        return mCartRepository.getConnectionStateMutableLiveData();
    }

    public LiveData <String> getTotalPriceLiveData (){
        return mCartRepository.getTotalPriceMutableLiveData();
    }


}

