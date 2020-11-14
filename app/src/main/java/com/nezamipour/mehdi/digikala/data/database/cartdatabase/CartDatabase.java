package com.nezamipour.mehdi.digikala.data.database.cartdatabase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.nezamipour.mehdi.digikala.data.database.dao.CartDao;
import com.nezamipour.mehdi.digikala.data.model.product.Product;

@Database(entities = {Product.class}, version = 1, exportSchema = false)
public abstract class CartDatabase extends RoomDatabase {
    public abstract CartDao cartDao();

}
