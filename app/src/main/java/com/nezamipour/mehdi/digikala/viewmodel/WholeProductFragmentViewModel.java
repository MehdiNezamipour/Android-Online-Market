package com.nezamipour.mehdi.digikala.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nezamipour.mehdi.digikala.data.model.product.Product;
import com.nezamipour.mehdi.digikala.data.repository.ProductRepository;

import java.util.List;

public class WholeProductFragmentViewModel extends ViewModel {

    private final ProductRepository mProductRepository;
    private final MutableLiveData<List<Product>> mProducts = new MutableLiveData<>();


    public WholeProductFragmentViewModel() {
        mProductRepository = ProductRepository.getInstance();
    }

    public void fetchDataFromRepository(String orderBy) {
        switch (orderBy) {
            case "onSale":
                mProducts.setValue(mProductRepository.getOnSaleProductsLiveData().getValue());
                break;
            case "date":
                mProducts.setValue(mProductRepository.getLatestProductsLiveData().getValue());
                break;
            case "popularity":
                mProducts.setValue(mProductRepository.getPopularProductsLiveData().getValue());
                break;
            case "rating":
                mProducts.setValue(mProductRepository.getTopRatingProductsLiveData().getValue());
                break;
            case "category":
                mProducts.setValue(mProductRepository.getCategoryProductsLiveData().getValue());
                break;
            case "search":
                mProducts.setValue(mProductRepository.getProductSearchLiveData().getValue());
                break;
            default:
                break;
        }
    }


    public LiveData<List<Product>> getProducts() {
        return mProducts;
    }
}
