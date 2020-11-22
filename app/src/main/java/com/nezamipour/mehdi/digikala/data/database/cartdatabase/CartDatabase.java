package com.nezamipour.mehdi.digikala.data.database.cartdatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.nezamipour.mehdi.digikala.data.database.dao.CartDao;
import com.nezamipour.mehdi.digikala.data.database.entity.CartProduct;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {CartProduct.class}, version = 1, exportSchema = false)
public abstract class CartDatabase extends RoomDatabase {

    private static final String CART_DATABASE_NAME = "cardDatabase.db";
   // private static volatile CartDatabase INSTANCE;

 /*   private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);*/

    public abstract CartDao cartDao();

    public static CartDatabase getDataBase(Context context) {
        return Room.databaseBuilder(
                context.getApplicationContext(),
                CartDatabase.class,
                CartDatabase.CART_DATABASE_NAME)
                .allowMainThreadQueries()
                .build();
    }

/*    public static CartDatabase getDataBase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CartDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CartDatabase.class, CART_DATABASE_NAME)
                            .build();
                }
            }
        }
        return INSTANCE;
    }*/

}
