package com.nezamipour.mehdi.digikala.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nezamipour.mehdi.digikala.data.model.product.Category;
import com.nezamipour.mehdi.digikala.data.model.product.Product;
import com.nezamipour.mehdi.digikala.data.repository.CategoryRepository;
import com.nezamipour.mehdi.digikala.data.repository.ProductRepository;
import com.nezamipour.mehdi.digikala.util.enums.ConnectionState;

import java.util.List;

public class CategoryFragmentViewModel extends ViewModel {

    private final MutableLiveData<List<Category>> mParentCategoriesLiveData;
    private final MutableLiveData<List<Category>> mChildParent1LiveData;
    private final MutableLiveData<List<Category>> mChildParent2LiveData;
    private final MutableLiveData<List<Category>> mChildParent3LiveData;
    private final MutableLiveData<List<Category>> mChildParent4LiveData;
    private final MutableLiveData<List<Category>> mChildParent5LiveData;
    private final MutableLiveData<List<Category>> mChildParent6LiveData;

    private final CategoryRepository mCategoryRepository;
    private final ProductRepository mProductRepository;


    public CategoryFragmentViewModel() {
        mCategoryRepository = CategoryRepository.getInstance();
        mProductRepository = ProductRepository.getInstance();
        mParentCategoriesLiveData = new MutableLiveData<>();
        mChildParent1LiveData = new MutableLiveData<>();
        mChildParent2LiveData = new MutableLiveData<>();
        mChildParent3LiveData = new MutableLiveData<>();
        mChildParent4LiveData = new MutableLiveData<>();
        mChildParent5LiveData = new MutableLiveData<>();
        mChildParent6LiveData = new MutableLiveData<>();

    }


    public void fetchCategories() {
        mCategoryRepository.fetchChildCategories();

        mParentCategoriesLiveData.setValue(mCategoryRepository.getParentCategories());
        mChildParent1LiveData.setValue(mCategoryRepository.getChildOfParent1());
        mChildParent2LiveData.setValue(mCategoryRepository.getChildOfParent2());
        mChildParent3LiveData.setValue(mCategoryRepository.getChildOfParent3());
        mChildParent4LiveData.setValue(mCategoryRepository.getChildOfParent4());
        mChildParent5LiveData.setValue(mCategoryRepository.getChildOfParent5());
        mChildParent6LiveData.setValue(mCategoryRepository.getChildOfParent6());
    }

    public LiveData<List<Category>> getParentCategoriesLiveData() {
        return mParentCategoriesLiveData;
    }

    public LiveData<List<Category>> getChildParent1LiveData() {
        return mChildParent1LiveData;
    }

    public LiveData<List<Category>> getChildParent2LiveData() {
        return mChildParent2LiveData;
    }

    public LiveData<List<Category>> getChildParent3LiveData() {
        return mChildParent3LiveData;
    }

    public LiveData<List<Category>> getChildParent4LiveData() {
        return mChildParent4LiveData;
    }

    public LiveData<List<Category>> getChildParent5LiveData() {
        return mChildParent5LiveData;
    }

    public LiveData<List<Category>> getChildParent6LiveData() {
        return mChildParent6LiveData;
    }


    //fetching category products

    public void fetchCategoryProducts(Integer productId) {
        mProductRepository.fetchCategoryProducts(productId);
    }

    public LiveData<List<Product>> getCategoryProducts() {
        return mProductRepository.getCategoryProductsLiveData();
    }

    public MutableLiveData<ConnectionState> getConnectionStateLiveData() {
        return mProductRepository.getConnectionStateLiveData();
    }
}
