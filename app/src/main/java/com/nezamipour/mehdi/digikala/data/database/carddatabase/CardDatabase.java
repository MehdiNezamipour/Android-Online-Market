package com.nezamipour.mehdi.digikala.data.database.carddatabase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.nezamipour.mehdi.digikala.data.database.dao.CardDao;
import com.nezamipour.mehdi.digikala.data.database.entitiy.CardProduct;

@Database(entities = {CardProduct.class}, version = 1, exportSchema = false)
public abstract class CardDatabase extends RoomDatabase {

    public abstract CardDao cardDao();

}
