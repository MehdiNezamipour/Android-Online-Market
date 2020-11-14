package com.nezamipour.mehdi.digikala.data.database.cartdatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.nezamipour.mehdi.digikala.data.database.dao.CartDao;
import com.nezamipour.mehdi.digikala.data.model.product.Product;

@Database(entities = {Product.class}, version = 1, exportSchema = false)
public abstract class CartDatabase extends RoomDatabase {

    public static final String CART_DATABASE_NAME = "cardDatabase.db";

    public abstract CartDao cartDao();

    public static CartDatabase getDataBase(Context context) {
        return Room.databaseBuilder(
                context.getApplicationContext(),
                CartDatabase.class,
                CartDatabase.CART_DATABASE_NAME)
                .allowMainThreadQueries()
                .build();
    }

}
