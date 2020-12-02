package com.nezamipour.mehdi.digikala.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.nezamipour.mehdi.digikala.data.model.product.Product;
import com.nezamipour.mehdi.digikala.data.repository.ProductRepository;
import com.nezamipour.mehdi.digikala.util.enums.SearchState;

import java.util.List;

public class SearchFragmentViewModel extends ViewModel {

    private final ProductRepository mProductRepository;


    public SearchFragmentViewModel() {
        mProductRepository = ProductRepository.getInstance();
    }

    public LiveData<List<Product>> getResultsLiveData() {
        return mProductRepository.getProductSearchLiveData();
    }

    public LiveData<SearchState> getSearchState (){
        return mProductRepository.getSearchStateLiveData();
    }

    public void fetchResults(String search) {
        mProductRepository.fetchProductsBySearch(search);
    }


}
