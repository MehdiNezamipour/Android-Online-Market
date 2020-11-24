package com.nezamipour.mehdi.digikala.data.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.nezamipour.mehdi.digikala.data.database.entity.CartProduct;

import java.util.List;

@Dao
public interface CartDao {


    //TODO : use live data with room
    @Query("select * from cart_product")
    List<CartProduct> getAll();

    @Query("select * from cart_product where mId == :productId ")
    CartProduct getById(Integer productId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CartProduct cartProduct);

    @Update
    void update(CartProduct cartProduct);

    @Delete
    void delete(CartProduct cartProduct);

    @Query("delete from cart_product where productId = :productId ")
    void delete(Integer productId);




}
