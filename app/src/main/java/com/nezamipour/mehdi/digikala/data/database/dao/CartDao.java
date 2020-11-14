package com.nezamipour.mehdi.digikala.data.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.nezamipour.mehdi.digikala.data.model.product.Product;

import java.util.List;

@Dao
public interface CartDao {


    @Query("Select * From Product")
    LiveData<List<Product>> getAll();

    @Query("Select * From Product Where id == :id ")
    LiveData<Product> getById(Integer id);

    @Insert
    void insert(Product cardProduct);

    @Delete
    void delete(Product cardProduct);


}
