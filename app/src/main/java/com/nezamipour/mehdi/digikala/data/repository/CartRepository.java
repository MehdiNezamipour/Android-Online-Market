package com.nezamipour.mehdi.digikala.data.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.nezamipour.mehdi.digikala.data.database.cartdatabase.CartDatabase;
import com.nezamipour.mehdi.digikala.data.database.dao.CartDao;
import com.nezamipour.mehdi.digikala.data.database.entity.CartProduct;
import com.nezamipour.mehdi.digikala.data.model.product.Product;
import com.nezamipour.mehdi.digikala.network.RetrofitInstance;
import com.nezamipour.mehdi.digikala.network.WooApi;
import com.nezamipour.mehdi.digikala.util.enums.ConnectionState;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartRepository {

    private final MutableLiveData<ConnectionState> mConnectionStateMutableLiveData;
    private final WooApi mWooApi;
    private static CartRepository sCartRepository;
    private final CartDao mCartDao;
    private final MutableLiveData<List<Product>> mProductsLiveData;

    private CartRepository(Context context) {
        CartDatabase dataBase = CartDatabase.getDataBase(context.getApplicationContext());
        mCartDao = dataBase.cartDao();
        mWooApi = RetrofitInstance.getInstance().create(WooApi.class);
        mProductsLiveData = new MutableLiveData<>();
        mConnectionStateMutableLiveData = new MutableLiveData<>();

    }

    public static CartRepository getInstance(Context context) {
        if (sCartRepository == null)
            sCartRepository = new CartRepository(context.getApplicationContext());
        return sCartRepository;
    }

    public MutableLiveData<ConnectionState> getConnectionStateMutableLiveData() {
        return mConnectionStateMutableLiveData;
    }

    public LiveData<List<Product>> getProductsLiveData() {
        return mProductsLiveData;
    }

    public void insert(CartProduct cartProduct) {
        mCartDao.insert(cartProduct);
    }

    public void delete(CartProduct cartProduct) {
        mCartDao.delete(cartProduct);
    }

    public void update(CartProduct cartProduct) {
        mCartDao.update(cartProduct);
    }


    public CartProduct get(Integer productId) {
        return mCartDao.getById(productId);
    }

    public List<CartProduct> getAll() {
        return mCartDao.getAll();
    }


    public void increaseCountOfCart(Product product) {
        CartProduct cartProduct = get(product.getId());
        int newCount = cartProduct.getCount() + 1;
        cartProduct.setCount(newCount);
        update(cartProduct);
    }

    public void decreaseCountOfCart(Product product) {
        CartProduct cartProduct = get(product.getId());
        if (cartProduct.getCount() > 1) {
            int newCount = cartProduct.getCount() - 1;
            cartProduct.setCount(newCount);
            update(cartProduct);
        }
    }


    public void fetchCartProducts() {
        mConnectionStateMutableLiveData.setValue(ConnectionState.LOADING);
        mProductsLiveData.postValue(new ArrayList<>());
        List<CartProduct> cartProducts;
        cartProducts = getAll();
        if (cartProducts.isEmpty()) {
            mConnectionStateMutableLiveData.postValue(ConnectionState.START_ACTIVITY);
            return;
        }
        for (CartProduct cartProduct : cartProducts) {
            mWooApi.getProductById(cartProduct.getProductId()).enqueue(new Callback<Product>() {
                @Override
                public void onResponse(Call<Product> call, Response<Product> response) {
                    if (response.isSuccessful()) {
                        List<Product> products = mProductsLiveData.getValue();
                        Product product = response.body();
                        products.add(product);
                        mProductsLiveData.postValue(products);
                        if (cartProducts.indexOf(cartProduct) == cartProducts.size() - 1) {
                            mConnectionStateMutableLiveData.postValue(ConnectionState.START_ACTIVITY);
                        }
                    }
                }

                @Override
                public void onFailure(Call<Product> call, Throwable t) {
                    mConnectionStateMutableLiveData.postValue(ConnectionState.ERROR);
                }
            });
        }
    }

}
