package com.nezamipour.mehdi.digikala.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.nezamipour.mehdi.digikala.data.model.product.Category;
import com.nezamipour.mehdi.digikala.data.model.product.Product;
import com.nezamipour.mehdi.digikala.network.RetrofitInstance;
import com.nezamipour.mehdi.digikala.network.WooApi;
import com.nezamipour.mehdi.digikala.util.CategoryUtil;
import com.nezamipour.mehdi.digikala.util.enums.ConnectionState;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductRepository {

    //singleton

    private static ProductRepository sRepository;
    private List<Product> mAllProducts;
    private List<Product> mOfferedProducts;
    private List<Product> mLatestProducts;
    private List<Product> mTopRatingProducts;
    private List<Product> mPopularProducts;

    private final WooApi mWooApi;

    private final MutableLiveData<ConnectionState> mConnectionStateMutableLiveData;

    private ProductRepository() {
        mWooApi = RetrofitInstance.getInstance().create(WooApi.class);
        mConnectionStateMutableLiveData = new MutableLiveData<>();
    }

    public static ProductRepository getInstance() {
        if (sRepository == null) {
            sRepository = new ProductRepository();
        }
        return sRepository;
    }


    public MutableLiveData<ConnectionState> getConnectionStateLiveData() {
        return mConnectionStateMutableLiveData;
    }

    public List<Product> getAllProducts() {
        return mAllProducts;
    }

    public void setAllProducts(List<Product> allProducts) {
        mAllProducts = allProducts;
    }

    public List<Product> getOfferedProducts() {
        return mOfferedProducts;
    }

    public void setOfferedProducts(List<Product> offeredProducts) {
        mOfferedProducts = offeredProducts;
    }

    public List<Product> getLatestProducts() {
        return mLatestProducts;
    }

    public void setLatestProducts(List<Product> latestProducts) {
        mLatestProducts = latestProducts;
    }

    public List<Product> getTopRatingProducts() {
        return mTopRatingProducts;
    }

    public void setTopRatingProducts(List<Product> bestProducts) {
        mTopRatingProducts = bestProducts;
    }

    public List<Product> getPopularProducts() {
        return mPopularProducts;
    }

    public void setPopularProducts(List<Product> popularProducts) {
        mPopularProducts = popularProducts;
    }


    public void fetchInitData() {
        mConnectionStateMutableLiveData.setValue(ConnectionState.LOADING);
        //offered products
        mWooApi.getSaleProducts(10, 1).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    mOfferedProducts = response.body();
                    fetchLatestProducts();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                initInternetError();
            }
        });
    }


    private void fetchLatestProducts() {
        mWooApi.getProducts(10, 1, "date").enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    mLatestProducts = response.body();
                    //top rating products
                    fetchBestProducts();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                initInternetError();
            }
        });
    }

    private void fetchBestProducts() {
        mWooApi.getProducts(10, 1, "rating").enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    mTopRatingProducts = response.body();
                    //last step of fetch from api
                    fetchPopularProducts();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                initInternetError();
            }
        });
    }

    private void fetchPopularProducts() {
        mWooApi.getProducts(10, 1, "popularity").enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    mPopularProducts = response.body();
                    fetchAllCategories();

                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                initInternetError();
            }
        });
    }

    private void fetchAllCategories() {
        mWooApi.getAllCategories(18, 1).enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful()) {
                    CategoryRepository categoryRepository = CategoryRepository.getInstance();
                    categoryRepository.setAllCategories(response.body());
                    categoryRepository.setParentCategories(CategoryUtil.parentsCategory(response.body()));
                    //live data flag to start activity in Ui (SplashFragment) with observe this field
                    mConnectionStateMutableLiveData.setValue(ConnectionState.START_ACTIVITY);

                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                initInternetError();
            }
        });
    }

    private void initInternetError() {
        mConnectionStateMutableLiveData.setValue(ConnectionState.ERROR);
    }


}
