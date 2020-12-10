package com.nezamipour.mehdi.digikala.network;

import com.nezamipour.mehdi.digikala.data.model.customer.Customer;
import com.nezamipour.mehdi.digikala.data.model.customer.Order;
import com.nezamipour.mehdi.digikala.data.model.product.Category;
import com.nezamipour.mehdi.digikala.data.model.product.Coupon;
import com.nezamipour.mehdi.digikala.data.model.product.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static com.nezamipour.mehdi.digikala.network.RetrofitInstance.BASE_URL;
import static com.nezamipour.mehdi.digikala.network.RetrofitInstance.API_KEY;

public interface WooApi {

    @GET(BASE_URL + "products" + API_KEY + "&on_sale=true")
    Call<List<Product>> getOnSaleProducts(@Query("per_page") int perPage, @Query("page") int numberOfPage);

    @GET(BASE_URL + "products" + API_KEY)
    Call<List<Product>> getProducts(@Query("per_page") int perPage,
                                    @Query("page") int numberOfPage,
                                    @Query("orderby") String orderBy);


    @GET(BASE_URL + "products" + API_KEY)
    Call<List<Product>> getAllProducts();


    @GET(BASE_URL + "products/{productId}" + API_KEY)
    Call<Product> getProductById(@Path("productId") Integer productId);


    //get products with search
    @GET(BASE_URL + "products" + API_KEY)
    Call<List<Product>> getProductsBySearch(@Query("per_page") int perPage,
                                            @Query("page") int numberOfPage,
                                            @Query("search") String search);

    //search with sort (order and orderBy)
    @GET(BASE_URL + "products" + API_KEY)
    Call<List<Product>> searchWithSorting(@Query("per_page") int perPage,
                                          @Query("page") int numberOfPage,
                                          @Query("search") String search,
                                          @Query("orderby") String orderBy,
                                          @Query("order") String order);


    //this api has 18 categories right now
    //so for get all of them page = 1 and per_page= 18
    @GET(BASE_URL + "products" + "/categories" + API_KEY)
    Call<List<Category>> getAllCategories(@Query("per_page") int perPage, @Query("page") int page);


    //for get products of a specific category
    @GET(BASE_URL + "products" + API_KEY)
    Call<List<Product>> getCategoryProducts(@Query("category") int categoryId,
                                            @Query("per_page") int perPage,
                                            @Query("page") int page);

    //sort products of category
    @GET(BASE_URL + "products" + API_KEY)
    Call<List<Product>> sortCategoryProducts(@Query("category") int categoryId,
                                             @Query("per_page") int perPage,
                                             @Query("page") int page,
                                             @Query("orderby") String orderBy,
                                             @Query("order") String order);


    //customers
    @POST(BASE_URL + "customers" + API_KEY)
    Call<Customer> registerCustomer(@Body Customer customer);

    @GET(BASE_URL + "customers" + API_KEY)
    Call<List<Customer>> getCustomer(@Query("email") String email);


    //orders
    @POST(BASE_URL + "orders" + API_KEY)
    Call<Customer> postOrder(@Body Order order);

    @GET(BASE_URL + "orders" + API_KEY)
    Call<List<Order>> getOrdersOfCustomer(@Query("customer") int customerId,
                                          @Query("per_page") int perPage,
                                          @Query("page") int page);

    //coupons
    @GET(BASE_URL + "coupons" + API_KEY)
    Call<List<Coupon>> getCouponByCode(@Query("code") String code);

    @PUT(BASE_URL + "coupons/{couponId}" + API_KEY)
    Call<Coupon> updateCoupon(@Path("couponId") int couponId,
                              @Body Coupon coupon);



}
