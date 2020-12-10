package com.nezamipour.mehdi.digikala.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.nezamipour.mehdi.digikala.data.model.product.Coupon;
import com.nezamipour.mehdi.digikala.data.repository.CustomerRepository;
import com.nezamipour.mehdi.digikala.data.repository.ProductRepository;
import com.nezamipour.mehdi.digikala.util.enums.ConnectionState;

public class FinishShoppingFragmentViewModel extends AndroidViewModel {

    private final ProductRepository mProductRepository;
    private final CustomerRepository mCustomerRepository;


    public FinishShoppingFragmentViewModel(@NonNull Application application) {
        super(application);
        mProductRepository = ProductRepository.getInstance();
        mCustomerRepository = CustomerRepository.getInstance(application);
    }

    public void fetchCoupon(String code) {
        mProductRepository.fetchCouponByCode(code);
    }

    public LiveData<Coupon> getCouponLiveData() {
        return mProductRepository.getCouponLiveData();
    }

    public LiveData<ConnectionState> getConnectionStateLiveData() {
        return mProductRepository.getConnectionStateLiveData();
    }

}
