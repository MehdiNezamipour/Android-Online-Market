package com.nezamipour.mehdi.digikala.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.nezamipour.mehdi.digikala.data.database.entity.Customer;
import com.nezamipour.mehdi.digikala.data.model.product.Review;
import com.nezamipour.mehdi.digikala.data.repository.CustomerRepository;
import com.nezamipour.mehdi.digikala.data.repository.ProductRepository;
import com.nezamipour.mehdi.digikala.util.enums.ConnectionState;

import java.util.List;

public class ReviewViewModel extends AndroidViewModel {

    private final ProductRepository mProductRepository;
    private CustomerRepository mCustomerRepository;

    public ReviewViewModel(@NonNull Application application) {
        super(application);
        mProductRepository = ProductRepository.getInstance();
        mCustomerRepository = CustomerRepository.getInstance(application);
    }


    public void fetchReviewsOfProduct(Integer productId) {
        mProductRepository.fetchReviewsOfProduct(productId);
    }

    public LiveData<ConnectionState> getConnectionStateLiveData() {
        return mProductRepository.getConnectionStateLiveData();
    }

    public LiveData<List<Review>> getReviewsLiveData() {
        return mProductRepository.getReviewListMutableLiveData();
    }

    public Customer getCurrentLoginCustomer() {
        return mCustomerRepository.getCurrentLoginCustomer();
    }

    public void fetchCustomer(String email) {
        mCustomerRepository.fetchCustomerByEmail(email);
    }

    public LiveData<com.nezamipour.mehdi.digikala.data.model.customer.Customer> getCustomerLiveData() {
        return mCustomerRepository.getCustomerLiveData();
    }

    public void postReview(Review review) {
        mProductRepository.postReview(review);
    }

    public LiveData <Review> getReviewLiveData (){
        return mProductRepository.getReviewMutableLiveData();
    }



}
