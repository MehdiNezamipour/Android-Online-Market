package com.nezamipour.mehdi.digikala.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    public static final String BASE_URL = "https://woocommerce.maktabsharif.ir/wp-json/wc/v3/";
    public static final String CONSUMER_KEY = "ck_dda03e6eb717396dd14d511a94137c15e3c7365d";
    public static final String CONSUMER_SECRET = "cs_065abe66a9a354981648904bb7bcdc247cb5209a";

    public static final String API_KEY = "?consumer_key=" +
            RetrofitInstance.CONSUMER_KEY +
            "&consumer_secret=" +
            RetrofitInstance.CONSUMER_SECRET;


    // Singleton


    private RetrofitInstance() {
    }

    private static Retrofit sRetrofit = null;

    public static Retrofit getInstance() {
        if (sRetrofit == null) {
            sRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return sRetrofit;
    }
}
