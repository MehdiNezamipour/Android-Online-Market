package com.nezamipour.mehdi.digikala.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.nezamipour.mehdi.digikala.data.database.entity.Customer;
import com.nezamipour.mehdi.digikala.data.repository.CartRepository;
import com.nezamipour.mehdi.digikala.data.repository.CustomerRepository;
import com.nezamipour.mehdi.digikala.util.enums.ConnectionState;

public class LoginViewModel extends AndroidViewModel {

    private final CartRepository mCartRepository;
    private final CustomerRepository mCustomerRepository;


    public LoginViewModel(@NonNull Application application) {
        super(application);
        mCustomerRepository = CustomerRepository.getInstance(application);
        mCartRepository = CartRepository.getInstance(application);
    }

    public Customer getCurrentLoginCustomerFromDataBase() {
        return mCustomerRepository.getCurrentLoginCustomer();
    }

    public Customer getCustomerByEmailFromDatabase(String email) {
        return mCustomerRepository.getCustomerByEmail(email);
    }

    public void fetchCustomerFromServer(String email) {
        mCustomerRepository.fetchCustomerByEmail(email);
    }

    public LiveData<ConnectionState> getConnectionStateLiveData() {
        return mCustomerRepository.getConnectionStateLiveData();
    }

    public LiveData<com.nezamipour.mehdi.digikala.data.model.customer.Customer> getCustomerLiveData() {
        return mCustomerRepository.getCustomerLiveData();
    }

    public void changeStateToLogOut() {
        mCustomerRepository.changeStateToLogOut();
    }

    public void changeStateToLogIn(String email) {
        mCustomerRepository.changeStateToLogIn(email);
    }

    public boolean authorizePassword(String email, String password) {
        return mCustomerRepository.authorizePassword(email, password);
    }

    public void deleteAllCarts (){
        mCartRepository.deleteAll();
    }




}
