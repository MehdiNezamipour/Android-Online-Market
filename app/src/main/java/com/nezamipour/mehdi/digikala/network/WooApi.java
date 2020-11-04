package com.nezamipour.mehdi.digikala.network;

import com.nezamipour.mehdi.digikala.data.model.product.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
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


}
