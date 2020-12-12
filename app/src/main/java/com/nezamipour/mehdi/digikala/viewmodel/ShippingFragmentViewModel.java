package com.nezamipour.mehdi.digikala.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.nezamipour.mehdi.digikala.data.model.customer.Customer;
import com.nezamipour.mehdi.digikala.data.repository.CustomerRepository;
import com.nezamipour.mehdi.digikala.util.enums.ConnectionState;

public class ShippingFragmentViewModel extends AndroidViewModel {

    private final CustomerRepository mCustomerRepository;


    public ShippingFragmentViewModel(@NonNull Application application) {
        super(application);
        mCustomerRepository = CustomerRepository.getInstance(application);
    }


    public void postCustomerToServer(Customer customer) {
        mCustomerRepository.postCustomerToServer(customer);
    }

    public void postCustomerToDataBase(String email, String password) {
        com.nezamipour.mehdi.digikala.data.database.entity.Customer customerEntity =
                new com.nezamipour.mehdi.digikala.data.database.entity.Customer(email, password);
        mCustomerRepository.insert(customerEntity);
    }

    public void postCustomerToDataBase(String email, String password, Double lat, Double lon) {
        com.nezamipour.mehdi.digikala.data.database.entity.Customer customerEntity =
                new com.nezamipour.mehdi.digikala.data.database.entity.Customer(email, password, lat, lon);
        mCustomerRepository.insert(customerEntity);
    }


    public LiveData<ConnectionState> getConnectionStateLiveData() {
        return mCustomerRepository.getConnectionStateLiveData();
    }



}
