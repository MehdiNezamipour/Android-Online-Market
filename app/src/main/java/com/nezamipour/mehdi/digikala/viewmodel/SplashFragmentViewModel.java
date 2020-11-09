package com.nezamipour.mehdi.digikala.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.nezamipour.mehdi.digikala.data.repository.ProductRepository;
import com.nezamipour.mehdi.digikala.util.enums.ConnectionState;

public class SplashFragmentViewModel extends AndroidViewModel {


    private final ProductRepository mProductRepository;
    private final MutableLiveData<ConnectionState> mConnectionStateLiveData;


    public SplashFragmentViewModel(@NonNull Application application) {
        super(application);
        mProductRepository = ProductRepository.getInstance();
        mConnectionStateLiveData = mProductRepository.getConnectionStateLiveData();
    }


    public void fetchInitData() {
        mProductRepository.fetchInitData();
    }

    public MutableLiveData<ConnectionState> getConnectionStateLiveData() {
        return mConnectionStateLiveData;
    }
}
