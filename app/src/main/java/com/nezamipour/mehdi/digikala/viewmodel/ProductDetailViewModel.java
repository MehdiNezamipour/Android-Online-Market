package com.nezamipour.mehdi.digikala.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.nezamipour.mehdi.digikala.data.model.product.Product;
import com.nezamipour.mehdi.digikala.network.RetrofitInstance;
import com.nezamipour.mehdi.digikala.network.WooApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailViewModel extends AndroidViewModel {

    private final WooApi mWooApi;
    private final MutableLiveData<Product> mProductMutableLiveData;
    private final MutableLiveData<Boolean> mStartNavigationLiveData;

    private final MutableLiveData<Boolean> mIsLoading = new MutableLiveData<>();
    private final MutableLiveData<Boolean> mIsError = new MutableLiveData<>();

    public ProductDetailViewModel(@NonNull Application application) {
        super(application);
        mProductMutableLiveData = new MutableLiveData<>();
        mWooApi = RetrofitInstance.getInstance().create(WooApi.class);
        mStartNavigationLiveData = new MutableLiveData<>();
        mStartNavigationLiveData.setValue(false);
    }


    public void fetchProductFromServer(Integer productId) {
        //TODO : later handle error when internet state enable and disable
        mIsLoading.setValue(true);
        mIsError.setValue(false);
        mStartNavigationLiveData.setValue(false);

        mWooApi.getProductById(productId).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (response.isSuccessful()) {
                    mProductMutableLiveData.setValue(response.body());
                    mStartNavigationLiveData.setValue(true);
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                mIsLoading.setValue(false);
                mIsError.setValue(true);
                mStartNavigationLiveData.setValue(false);
            }
        });
    }

    public LiveData<Product> getProductMutableLiveData() {
        return mProductMutableLiveData;
    }

    public void setProductMutableLiveData(Product product) {
        mProductMutableLiveData.setValue(product);
    }

    public LiveData<Boolean> getStartNavigationLiveData() {
        return mStartNavigationLiveData;
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return mIsLoading;
    }

    public MutableLiveData<Boolean> getIsError() {
        return mIsError;
    }
}
