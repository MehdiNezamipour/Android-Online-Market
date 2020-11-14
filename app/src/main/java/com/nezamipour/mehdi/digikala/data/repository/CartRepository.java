package com.nezamipour.mehdi.digikala.data.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.nezamipour.mehdi.digikala.data.database.cartdatabase.CartDatabase;
import com.nezamipour.mehdi.digikala.data.database.dao.CartDao;
import com.nezamipour.mehdi.digikala.data.model.product.Product;

import java.util.List;

public class CartRepository {

    private static CartRepository sCartRepository;
    private final CartDao mCartDao;

    private CartRepository(Context context) {
        CartDatabase mCrimeRoomDataBase = CartDatabase.getDataBase(context);
        mCartDao = mCrimeRoomDataBase.cartDao();
    }

    public static CartRepository getInstance(Context context) {
        if (sCartRepository == null)
            sCartRepository = new CartRepository(context);
        return sCartRepository;
    }


    public void insertToCard(Product product) {
        mCartDao.insert(product);
    }

    public void deleteFromCard(Product product) {
        mCartDao.delete(product);
    }

    public LiveData<Product> getProductFromCart(Integer id) {
        return mCartDao.getById(id);
    }


    public LiveData<List<Product>> getAllFromCart() {
        return mCartDao.getAll();
    }

}
