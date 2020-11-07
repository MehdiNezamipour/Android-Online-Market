package com.nezamipour.mehdi.digikala.viewmodel;

import android.renderscript.ScriptGroup;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nezamipour.mehdi.digikala.data.model.product.Product;
import com.nezamipour.mehdi.digikala.network.RetrofitInstance;
import com.nezamipour.mehdi.digikala.network.WooApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailFragmentViewModel extends ViewModel {

    private MutableLiveData<Product> mProductMutableLiveData;
    private WooApi mWooApi;

    public ProductDetailFragmentViewModel() {
        mProductMutableLiveData = new MutableLiveData<>();
        mWooApi = RetrofitInstance.getInstance().create(WooApi.class);
    }


    public void fetchProductFromServer(Integer productId) {

        mWooApi.getProductById(productId).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (response.isSuccessful()) {
                    mProductMutableLiveData.postValue(response.body());

                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                try {
                    throw new Exception("response not successful");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public LiveData<Product> getProductMutableLiveData() {
        return mProductMutableLiveData;
    }

    public void setProductMutableLiveData(Product product) {
        mProductMutableLiveData.setValue(product);
    }
}
