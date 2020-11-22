package com.nezamipour.mehdi.digikala.data.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.nezamipour.mehdi.digikala.data.database.cartdatabase.CartDatabase;
import com.nezamipour.mehdi.digikala.data.database.dao.CartDao;
import com.nezamipour.mehdi.digikala.data.database.entity.CartProduct;

import java.util.List;

public class CartRepository {

    private static CartRepository sCartRepository;
    private final CartDao mCartDao;

    private CartRepository(Context context) {
        CartDatabase dataBase = CartDatabase.getDataBase(context);
        mCartDao = dataBase.cartDao();
        // mAllProducts = mCartDao.getAll();
    }

    public static CartRepository getInstance(Context context) {
        if (sCartRepository == null)
            sCartRepository = new CartRepository(context);
        return sCartRepository;
    }


    public void insertToCard(CartProduct cartProduct) {
        mCartDao.insert(cartProduct);
    }

    public void deleteFromCard(CartProduct cartProduct) {
        mCartDao.delete(cartProduct);
    }

    /*public LiveData<Product> getProductFromCart(Integer id) {
        return mCartDao.getById(id);
    }*/

    public List<CartProduct> getAllProducts() {
        return mCartDao.getAll();
    }

}
