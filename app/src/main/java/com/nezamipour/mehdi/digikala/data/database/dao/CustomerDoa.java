package com.nezamipour.mehdi.digikala.data.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.nezamipour.mehdi.digikala.data.database.entity.Customer;

import java.util.List;

@Dao
public interface CustomerDoa {

    @Insert
    void insert(Customer customer);

    @Delete
    void delete(Customer customer);

    @Update
    void update(Customer customer);

    @Query("select * from customer")
    List<Customer> getAll();

    @Query("select * from customer where email =:email")
    Customer getByEmail(String email);

    @Query("select * from customer where LoginState = 1")
    Customer getCurrentLoginCustomer();



}
