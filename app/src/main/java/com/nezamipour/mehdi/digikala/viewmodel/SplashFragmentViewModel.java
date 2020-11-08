package com.nezamipour.mehdi.digikala.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.nezamipour.mehdi.digikala.data.model.product.Category;
import com.nezamipour.mehdi.digikala.data.model.product.Product;
import com.nezamipour.mehdi.digikala.data.repository.CategoryRepository;
import com.nezamipour.mehdi.digikala.data.repository.ProductRepository;
import com.nezamipour.mehdi.digikala.network.RetrofitInstance;
import com.nezamipour.mehdi.digikala.network.WooApi;
import com.nezamipour.mehdi.digikala.util.CategoryUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashFragmentViewModel extends AndroidViewModel {


    private final ProductRepository mProductRepository;
    private final WooApi mWooApi;

    private final MutableLiveData<Boolean> mIsLoading = new MutableLiveData<>();
    private final MutableLiveData<Boolean> mIsError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> mStartMainActivity = new MutableLiveData<>();

    public SplashFragmentViewModel(@NonNull Application application) {
        super(application);
        mProductRepository = ProductRepository.getInstance();
        mWooApi = RetrofitInstance.getInstance().create(WooApi.class);
    }


    private void initInternetError() {
        mIsLoading.setValue(false);
        mIsError.setValue(true);
    }

    public void fetchInitData() {
        mIsLoading.setValue(true);
        mIsError.setValue(false);
        mStartMainActivity.setValue(false);
        //offered products
        mWooApi.getSaleProducts(10, 1).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    mProductRepository.setOfferedProducts(response.body());
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
                    mProductRepository.setLatestProducts(response.body());
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
                    mProductRepository.setTopRatingProducts(response.body());
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
                    mProductRepository.setPopularProducts(response.body());
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
                    mStartMainActivity.setValue(true);
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                initInternetError();
            }
        });
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return mIsLoading;
    }

    public MutableLiveData<Boolean> getIsError() {
        return mIsError;
    }

    public LiveData<Boolean> getStartMainActivity() {
        return mStartMainActivity;
    }
}
