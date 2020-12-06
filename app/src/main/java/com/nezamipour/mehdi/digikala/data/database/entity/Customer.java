package com.nezamipour.mehdi.digikala.data.database.entity;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "customer")
public class Customer {

    @PrimaryKey(autoGenerate = true)
    private Integer mId;

    @ColumnInfo(name = "email")
    private String mEmail;

    @ColumnInfo(name = "password")
    private String mPassword;

    @ColumnInfo(name = "LoginState", defaultValue = "false")
    private boolean mLoginState;


    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public boolean isLoginState() {
        return mLoginState;
    }

    public void setLoginState(boolean loginState) {
        mLoginState = loginState;
    }

    @Ignore
    public Customer(String email, String password) {
        mEmail = email;
        mPassword = password;
        mLoginState = true;
    }


    public Customer() {
    }
}
