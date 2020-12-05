package com.nezamipour.mehdi.digikala.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nezamipour.mehdi.digikala.data.model.customer.Customer;
import com.nezamipour.mehdi.digikala.network.RetrofitInstance;
import com.nezamipour.mehdi.digikala.network.WooApi;
import com.nezamipour.mehdi.digikala.util.enums.ConnectionState;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShippingFragmentViewModel extends ViewModel {

    private MutableLiveData<ConnectionState> mConnectionStateMutableLiveData;
    private WooApi mWooApi;


    public ShippingFragmentViewModel() {
        mWooApi = RetrofitInstance.getInstance().create(WooApi.class);
        mConnectionStateMutableLiveData = new MutableLiveData<>();
    }

    public LiveData<ConnectionState> getConnectionStateLiveData() {
        return mConnectionStateMutableLiveData;
    }

    public void postCustomer(Customer customer) {
        mConnectionStateMutableLiveData.setValue(ConnectionState.LOADING);
        mWooApi.registerCustomer(customer).enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                if (response.isSuccessful()) {
                    mConnectionStateMutableLiveData.setValue(ConnectionState.START_ACTIVITY);
                }
            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {
                mConnectionStateMutableLiveData.setValue(ConnectionState.ERROR);
            }
        });
    }




}
