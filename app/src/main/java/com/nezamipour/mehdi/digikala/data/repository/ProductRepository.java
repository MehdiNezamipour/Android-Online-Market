package com.nezamipour.mehdi.digikala.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.nezamipour.mehdi.digikala.data.model.product.Category;
import com.nezamipour.mehdi.digikala.data.model.product.Coupon;
import com.nezamipour.mehdi.digikala.data.model.product.Product;
import com.nezamipour.mehdi.digikala.network.RetrofitInstance;
import com.nezamipour.mehdi.digikala.network.WooApi;
import com.nezamipour.mehdi.digikala.util.CategoryUtil;
import com.nezamipour.mehdi.digikala.util.enums.ConnectionState;
import com.nezamipour.mehdi.digikala.util.enums.SearchState;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductRepository {

    //singleton

    //for special products
    private static ProductRepository sRepository;
    private final MutableLiveData<List<Product>> mAllProductsLiveData;
    private final MutableLiveData<List<Product>> mOnSaleProductsLiveData;
    private final MutableLiveData<List<Product>> mLatestProductsLiveData;
    private final MutableLiveData<List<Product>> mTopRatingProductsLiveData;
    private final MutableLiveData<List<Product>> mPopularProductsLiveData;

    //for products of specific category
    private MutableLiveData<List<Product>> mCategoryProductsLiveData;

    private final MutableLiveData<ConnectionState> mConnectionStateMutableLiveData;
    private final MutableLiveData<SearchState> mSearchStateMutableLiveData;

    private final MutableLiveData<Product> mProductByIdMutableLiveData;
    private final MutableLiveData<List<Product>> mProductSearchMutableLiveData;
    private final MutableLiveData<Coupon> mCouponMutableLiveData;

    private final WooApi mWooApi;


    private ProductRepository() {
        mWooApi = RetrofitInstance.getInstance().create(WooApi.class);

        mAllProductsLiveData = new MutableLiveData<>();
        mOnSaleProductsLiveData = new MutableLiveData<>();
        mLatestProductsLiveData = new MutableLiveData<>();
        mTopRatingProductsLiveData = new MutableLiveData<>();
        mPopularProductsLiveData = new MutableLiveData<>();

        mCategoryProductsLiveData = new MutableLiveData<>();

        mConnectionStateMutableLiveData = new MutableLiveData<>();
        mSearchStateMutableLiveData = new MutableLiveData<>();

        mProductByIdMutableLiveData = new MutableLiveData<>();
        mProductSearchMutableLiveData = new MutableLiveData<>();

        mCouponMutableLiveData = new MutableLiveData<>();
    }


    public static ProductRepository getInstance() {
        if (sRepository == null) {
            sRepository = new ProductRepository();
        }
        return sRepository;
    }


    public MutableLiveData<List<Product>> getCategoryProductsLiveData() {
        return mCategoryProductsLiveData;
    }

    public LiveData<ConnectionState> getConnectionStateLiveData() {
        return mConnectionStateMutableLiveData;
    }

    public LiveData<SearchState> getSearchStateLiveData() {
        return mSearchStateMutableLiveData;
    }

    public LiveData<Product> getProductByIdMutableLiveData() {
        return mProductByIdMutableLiveData;
    }


    public LiveData<List<Product>> getAllProductsLiveData() {
        return mAllProductsLiveData;
    }


    public LiveData<List<Product>> getOnSaleProductsLiveData() {
        return mOnSaleProductsLiveData;
    }


    public LiveData<List<Product>> getLatestProductsLiveData() {
        return mLatestProductsLiveData;
    }

    public LiveData<List<Product>> getTopRatingProductsLiveData() {
        return mTopRatingProductsLiveData;
    }


    public LiveData<List<Product>> getPopularProductsLiveData() {
        return mPopularProductsLiveData;
    }


    public LiveData<List<Product>> getProductSearchLiveData() {
        return mProductSearchMutableLiveData;
    }

    public LiveData<Coupon> getCouponLiveData() {
        return mCouponMutableLiveData;
    }

    public void fetchCouponByCode(String code) {
        mWooApi.getCouponByCode(code).enqueue(new Callback<List<Coupon>>() {
            @Override
            public void onResponse(Call<List<Coupon>> call, Response<List<Coupon>> response) {
                if (response.isSuccessful() && !response.body().isEmpty()) {
                    mCouponMutableLiveData.setValue(response.body().get(0));
                }
            }

            @Override
            public void onFailure(Call<List<Coupon>> call, Throwable t) {
            }
        });
    }

    public void searchWithSorting(String search, String orderBy, String order) {
        mSearchStateMutableLiveData.setValue(SearchState.SEARCHING);
        mWooApi.searchWithSorting(10, 1, search, orderBy, order).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    mProductSearchMutableLiveData.setValue(response.body());
                    mSearchStateMutableLiveData.setValue(SearchState.RESULT_BACKED);

                }
                mSearchStateMutableLiveData.setValue(SearchState.NOTHING);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                mSearchStateMutableLiveData.setValue(SearchState.ERROR);
            }
        });
    }

    public void fetchProductsBySearch(String search) {
        mSearchStateMutableLiveData.postValue(SearchState.SEARCHING);
        mWooApi.getProductsBySearch(10, 1, search).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    mProductSearchMutableLiveData.setValue(response.body());
                    mSearchStateMutableLiveData.setValue(SearchState.RESULT_BACKED);
                }
                mSearchStateMutableLiveData.setValue(SearchState.NOTHING);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                mSearchStateMutableLiveData.setValue(SearchState.ERROR);
            }
        });
    }

    public void fetchProductById(Integer productId) {
        mConnectionStateMutableLiveData.setValue(ConnectionState.LOADING);
        mWooApi.getProductById(productId).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (response.isSuccessful()) {
                    mProductByIdMutableLiveData.setValue(response.body());
                    mConnectionStateMutableLiveData.setValue(ConnectionState.START_ACTIVITY);
                }
                mConnectionStateMutableLiveData.setValue(ConnectionState.NOTHING);
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                mConnectionStateMutableLiveData.setValue(ConnectionState.ERROR);
            }
        });
    }


    public void fetchCategoryProducts(Integer categoryId) {
        mConnectionStateMutableLiveData.setValue(ConnectionState.LOADING);
        mWooApi.getCategoryProducts(categoryId, 10, 1).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    mCategoryProductsLiveData.setValue(response.body());
                    mConnectionStateMutableLiveData.setValue(ConnectionState.START_ACTIVITY);
                }
                mConnectionStateMutableLiveData.setValue(ConnectionState.NOTHING);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                mConnectionStateMutableLiveData.setValue(ConnectionState.ERROR);
            }
        });
    }

    public void sortCategoryProducts(Integer categoryId, String orderBy, String order) {
        mSearchStateMutableLiveData.setValue(SearchState.SEARCHING);
        mWooApi.sortCategoryProducts(categoryId, 10, 1, orderBy, order).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    mCategoryProductsLiveData.setValue(response.body());
                    mSearchStateMutableLiveData.setValue(SearchState.RESULT_BACKED);
                }
                mSearchStateMutableLiveData.setValue(SearchState.NOTHING);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                mSearchStateMutableLiveData.setValue(SearchState.ERROR);
            }
        });
    }

    public void fetchInitData() {
        mConnectionStateMutableLiveData.setValue(ConnectionState.LOADING);
        //get all products
        mWooApi.getAllProducts().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    mAllProductsLiveData.setValue(response.body());
                    fetchOnSaleProducts();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                initInternetError();
            }
        });

    }

    private void fetchOnSaleProducts() {
        //on sale products
        mWooApi.getOnSaleProducts(10, 1).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    mOnSaleProductsLiveData.setValue(response.body());
                    fetchLatestProducts();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                initInternetError();
            }
        });
    }


    private void fetchLatestProducts() {
        mWooApi.getProducts(10, 1, "date").enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    mLatestProductsLiveData.setValue(response.body());
                    //top rating products
                    fetchBestProducts();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                initInternetError();
            }
        });
    }

    private void fetchBestProducts() {
        mWooApi.getProducts(10, 1, "rating").enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    mTopRatingProductsLiveData.setValue(response.body());
                    //last step of fetch from api
                    fetchPopularProducts();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                initInternetError();
            }
        });
    }

    private void fetchPopularProducts() {
        mWooApi.getProducts(10, 1, "popularity").enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    mPopularProductsLiveData.setValue(response.body());
                    fetchAllCategories();

                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                initInternetError();
            }
        });
    }

    private void fetchAllCategories() {
        mWooApi.getAllCategories(18, 1).enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful()) {
                    CategoryRepository categoryRepository = CategoryRepository.getInstance();
                    categoryRepository.setAllCategories(response.body());
                    categoryRepository.setParentCategories(CategoryUtil.parentsCategory(response.body()));
                    //live data flag to start activity in Ui (SplashFragment) with observe this field
                    mConnectionStateMutableLiveData.setValue(ConnectionState.START_ACTIVITY);
                }
                mConnectionStateMutableLiveData.setValue(ConnectionState.NOTHING);
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                initInternetError();
            }
        });
    }


    private void initInternetError() {
        mConnectionStateMutableLiveData.setValue(ConnectionState.ERROR);
    }


}
