package com.nezamipour.mehdi.digikala.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.nezamipour.mehdi.digikala.data.model.product.Category;
import com.nezamipour.mehdi.digikala.data.model.product.Coupon;
import com.nezamipour.mehdi.digikala.data.model.product.Product;
import com.nezamipour.mehdi.digikala.data.model.product.Review;
import com.nezamipour.mehdi.digikala.network.RetrofitInstance;
import com.nezamipour.mehdi.digikala.network.WooApi;
import com.nezamipour.mehdi.digikala.util.CategoryUtil;
import com.nezamipour.mehdi.digikala.util.enums.ConnectionState;

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
    private final MutableLiveData<List<Product>> mCategoryProductsLiveData;

    private final MutableLiveData<ConnectionState> mConnectionStateMutableLiveData;

    private final MutableLiveData<Product> mProductByIdMutableLiveData;
    private final MutableLiveData<List<Product>> mProductSearchMutableLiveData;
    private final MutableLiveData<Coupon> mCouponMutableLiveData;

    private final MutableLiveData<List<Review>> mReviewListMutableLiveData;
    private final MutableLiveData<Review> mReviewMutableLiveData;

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

        mProductByIdMutableLiveData = new MutableLiveData<>();
        mProductSearchMutableLiveData = new MutableLiveData<>();

        mCouponMutableLiveData = new MutableLiveData<>();

        mReviewListMutableLiveData = new MutableLiveData<>();
        mReviewMutableLiveData = new MutableLiveData<>();
    }


    public static ProductRepository getInstance() {
        if (sRepository == null) {
            sRepository = new ProductRepository();
        }
        return sRepository;
    }


    public LiveData<List<Review>> getReviewListMutableLiveData() {
        return mReviewListMutableLiveData;
    }

    public MutableLiveData<Review> getReviewMutableLiveData() {
        return mReviewMutableLiveData;
    }

    public MutableLiveData<List<Product>> getCategoryProductsLiveData() {
        return mCategoryProductsLiveData;
    }

    public MutableLiveData<ConnectionState> getConnectionStateLiveData() {
        return mConnectionStateMutableLiveData;
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

    public void fetchReviewsOfProduct(Integer productId) {
        mConnectionStateMutableLiveData.setValue(ConnectionState.LOADING);
        mWooApi.getReviewsOfProduct(productId, 10, 1).enqueue(new Callback<List<Review>>() {
            @Override
            public void onResponse(Call<List<Review>> call, Response<List<Review>> response) {
                if (response.isSuccessful()) {
                    mConnectionStateMutableLiveData.setValue(ConnectionState.START_ACTIVITY);
                    mReviewListMutableLiveData.setValue(response.body());
                }
                mConnectionStateMutableLiveData.setValue(ConnectionState.NOTHING);
            }

            @Override
            public void onFailure(Call<List<Review>> call, Throwable t) {
                mConnectionStateMutableLiveData.setValue(ConnectionState.ERROR);
            }
        });
    }

    public void postReview(Review review) {
        mWooApi.postReview(review).enqueue(new Callback<Review>() {
            @Override
            public void onResponse(Call<Review> call, Response<Review> response) {
                if (response.isSuccessful()) {
                    mReviewMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Review> call, Throwable t) {

            }
        });
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
        mWooApi.searchWithSorting(10, 1, search, orderBy, order).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    mProductSearchMutableLiveData.setValue(response.body());

                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
            }
        });
    }

    public void fetchProductsBySearch(String search) {
        mConnectionStateMutableLiveData.setValue(ConnectionState.LOADING);
        mWooApi.getProductsBySearch(10, 1, search).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    mProductSearchMutableLiveData.setValue(response.body());
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
        mWooApi.sortCategoryProducts(categoryId, 10, 1, orderBy, order).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    mCategoryProductsLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
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

    public void fetchOnSale() {
        //on sale products
        mConnectionStateMutableLiveData.setValue(ConnectionState.LOADING);
        mWooApi.getOnSaleProducts(10, 1).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    mOnSaleProductsLiveData.setValue(response.body());
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


    private void fetchLatestProducts() {
        mWooApi.getProducts(10, 1, "date").enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    mLatestProductsLiveData.setValue(response.body());
                    //top rating products
                    fetchTopRatingProducts();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                initInternetError();
            }
        });
    }

    public void fetchLatest() {
        mConnectionStateMutableLiveData.postValue(ConnectionState.LOADING);
        mWooApi.getProducts(10, 1, "date").enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    mLatestProductsLiveData.setValue(response.body());
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


    private void fetchTopRatingProducts() {
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

    public void fetchTopRating() {
        mConnectionStateMutableLiveData.setValue(ConnectionState.LOADING);
        mWooApi.getProducts(10, 1, "rating").enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    mTopRatingProductsLiveData.setValue(response.body());
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

    public void fetchPopular() {
        mConnectionStateMutableLiveData.setValue(ConnectionState.LOADING);
        mWooApi.getProducts(10, 1, "popularity").enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    mPopularProductsLiveData.setValue(response.body());
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
