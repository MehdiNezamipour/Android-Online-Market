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

    @ColumnInfo(name = "latitude")
    private Double mLatitude;

    @ColumnInfo(name = "longitude")
    private Double mLongitude;


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

    public Double getLatitude() {
        return mLatitude;
    }

    public void setLatitude(Double latitude) {
        mLatitude = latitude;
    }

    public Double getLongitude() {
        return mLongitude;
    }

    public void setLongitude(Double longitude) {
        mLongitude = longitude;
    }

    @Ignore
    public Customer(String email, String password) {
        mEmail = email;
        mPassword = password;
        mLoginState = true;
    }

    @Ignore
    public Customer(String email, String password, Double latitude, Double longitude) {
        mEmail = email;
        mPassword = password;
        mLoginState = true;
        mLatitude = latitude;
        mLongitude = longitude;
    }

    public Customer() {
    }
}
