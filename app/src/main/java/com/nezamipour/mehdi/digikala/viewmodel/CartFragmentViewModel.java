package com.nezamipour.mehdi.digikala.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.nezamipour.mehdi.digikala.data.database.entity.CartProduct;
import com.nezamipour.mehdi.digikala.data.model.product.Product;
import com.nezamipour.mehdi.digikala.data.repository.CartRepository;
import com.nezamipour.mehdi.digikala.data.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

public class CartFragmentViewModel extends AndroidViewModel {

    private final ProductRepository mProductRepository;
    private final CartRepository mCartRepository;
    private final MutableLiveData<List<Product>> mProductsLiveData;

    public CartFragmentViewModel(@NonNull Application application) {
        super(application);
        mProductRepository = ProductRepository.getInstance();
        mCartRepository = CartRepository.getInstance(application);
        mProductsLiveData = new MutableLiveData<>();
        mProductsLiveData.postValue(allCartProducts(mCartRepository.getAllProducts()));
    }

    public List<Product> allCartProducts(List<CartProduct> cartProducts) {
        List<Product> products = new ArrayList<>();
        for (CartProduct cartProduct : cartProducts) {
            products.add(mProductRepository.findProductById(cartProduct.mId));
        }
        return products;
    }

    public LiveData<List<Product>> getProductsLiveData() {
        return mProductsLiveData;
    }

}
