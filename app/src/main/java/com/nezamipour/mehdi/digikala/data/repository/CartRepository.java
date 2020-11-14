package com.nezamipour.mehdi.digikala.data.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.nezamipour.mehdi.digikala.data.database.carddatabase.CardDatabase;
import com.nezamipour.mehdi.digikala.data.database.entitiy.CardProduct;
import com.nezamipour.mehdi.digikala.data.model.product.Product;

import java.util.List;

public class CartRepository {

    public static final String CARD_DATABASE_NAME = "cardDatabase.db";
    private static CartRepository sCartRepository;

    private MutableLiveData<Product> mProductsLiveData;
    private final CardDatabase mDatabase;

    private CartRepository(Context context) {
        mProductsLiveData = new MutableLiveData<>();
        mDatabase = Room.databaseBuilder(context.getApplicationContext(),
                CardDatabase.class, CARD_DATABASE_NAME).build();
    }

    public static CartRepository getInstance(Context context) {
        if (sCartRepository == null)
            sCartRepository = new CartRepository(context);
        return sCartRepository;
    }

    public static CartRepository getCartRepository() {
        return sCartRepository;
    }

    public static void setCartRepository(CartRepository cartRepository) {
        sCartRepository = cartRepository;
    }

    public MutableLiveData<Product> getProductsLiveData() {
        return mProductsLiveData;
    }

    public void setProductsLiveData(MutableLiveData<Product> productsLiveData) {
        mProductsLiveData = productsLiveData;
    }

    public void insertToCard(CardProduct cardProduct) {
        mDatabase.cardDao().insert(cardProduct);
    }

    public void deleteFromCard(CardProduct cardProduct) {
        mDatabase.cardDao().delete(cardProduct);
    }

    public LiveData<CardProduct> getProductFromCard(Integer id) {
        return mDatabase.cardDao().getById(id);
    }


    public LiveData<List<CardProduct>> getAllFromCard() {
        return mDatabase.cardDao().getAll();
    }

}
