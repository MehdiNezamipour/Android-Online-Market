package com.nezamipour.mehdi.digikala.network;

import com.nezamipour.mehdi.digikala.data.model.product.Category;
import com.nezamipour.mehdi.digikala.data.model.product.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static com.nezamipour.mehdi.digikala.network.RetrofitInstance.BASE_URL;
import static com.nezamipour.mehdi.digikala.network.RetrofitInstance.API_KEY;

public interface WooApi {

    @GET(BASE_URL + "products" + API_KEY + "&on_sale=true")
    Call<List<Product>> getSaleProducts(@Query("per_page") int perPage, @Query("page") int numberOfPage);

    @GET(BASE_URL + "products" + API_KEY)
    Call<List<Product>> getProducts(@Query("per_page") int perPage,
                                    @Query("page") int numberOfPage,
                                    @Query("orderby") String orderBy);

    @GET(BASE_URL + "products" + API_KEY)
    Call<List<Product>> getAllProducts();


    @GET(BASE_URL + "products/{productId}" + API_KEY)
    Call<Product> getProductById(@Path("productId") Integer productId);

    //this api has 18 categories right now
    //so for get all of them page = 1 and per_page= 18
    @GET(BASE_URL + "products" + "/categories" + API_KEY)
    Call<List<Category>> getAllCategories(@Query("per_page") int perPage, @Query("page") int page);



}
