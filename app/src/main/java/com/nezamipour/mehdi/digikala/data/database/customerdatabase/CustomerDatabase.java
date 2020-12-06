package com.nezamipour.mehdi.digikala.data.database.customerdatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.nezamipour.mehdi.digikala.data.database.dao.CustomerDoa;
import com.nezamipour.mehdi.digikala.data.database.entity.Customer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Customer.class}, version = 1, exportSchema = false)
public abstract class CustomerDatabase extends RoomDatabase {

    public static final String CUSTOMER_DATABASE_NAME = "customerDatabase.db";

    private static volatile CustomerDatabase sINSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    

    public abstract CustomerDoa customerDoa();

    public static CustomerDatabase getInstance(final Context context) {

        synchronized (CustomerDatabase.class) {
            if (sINSTANCE == null) {
                sINSTANCE = Room.databaseBuilder(context.getApplicationContext()
                        , CustomerDatabase.class,
                        CUSTOMER_DATABASE_NAME)
                        .allowMainThreadQueries()
                        .build();
            }
        }
        return sINSTANCE;
    }

}
