package com.nezamipour.mehdi.digikala.data.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.nezamipour.mehdi.digikala.data.database.cartdatabase.CartDatabase;
import com.nezamipour.mehdi.digikala.data.model.product.Product;

import java.util.List;

public class CartRepository {

    public static final String CART_DATABASE_NAME = "cardDatabase.db";
    private static CartRepository sCartRepository;
    private final CartDatabase mDatabase;

    private CartRepository(Context context) {
        mDatabase = Room.databaseBuilder(context.getApplicationContext(),
                CartDatabase.class, CART_DATABASE_NAME).allowMainThreadQueries().build();
    }

    public static CartRepository getInstance(Context context) {
        if (sCartRepository == null)
            sCartRepository = new CartRepository(context);
        return sCartRepository;
    }


    public static void setCartRepository(CartRepository cartRepository) {
        sCartRepository = cartRepository;
    }


    public void insertToCard(Product product) {
        mDatabase.cartDao().insert(product);
    }

    public void deleteFromCard(Product product) {
        mDatabase.cartDao().delete(product);
    }

    public LiveData<Product> getProductFromCart(Integer id) {
        return mDatabase.cartDao().getById(id);
    }


    public LiveData<List<Product>> getAllFromCart() {
        return mDatabase.cartDao().getAll();
    }

}
