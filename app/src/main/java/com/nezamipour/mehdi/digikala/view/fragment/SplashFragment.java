package com.nezamipour.mehdi.digikala.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nezamipour.mehdi.digikala.R;
import com.nezamipour.mehdi.digikala.data.repository.ProductRepository;
import com.nezamipour.mehdi.digikala.data.model.product.Product;
import com.nezamipour.mehdi.digikala.databinding.FragmentSplashBinding;
import com.nezamipour.mehdi.digikala.network.RetrofitInstance;
import com.nezamipour.mehdi.digikala.network.WooApi;
import com.nezamipour.mehdi.digikala.view.activity.MainActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashFragment extends Fragment {


    private WooApi mWooApi;
    private ProductRepository mProductRepository;
    private FragmentSplashBinding mBinding;

    public SplashFragment() {
        // Required empty public constructor
    }

    public static SplashFragment newInstance() {
        SplashFragment fragment = new SplashFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_splash,
                container,
                false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mProductRepository = ProductRepository.getInstance();
        mWooApi = RetrofitInstance.getInstance().create(WooApi.class);
        requestForInitData();

        mBinding.textViewRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.textViewRetry.setVisibility(View.GONE);
                mBinding.textViewNoInternet.setVisibility(View.GONE);
                mBinding.progressBar.setVisibility(View.VISIBLE);
                mBinding.progressBar.show();
                requestForInitData();
            }
        });
    }

    private void requestForInitData() {
        mWooApi.getSaleProducts(8, 1).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    mProductRepository.setOfferedProducts(response.body());
                    requestForLatestProducts();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                loadInternetError();
            }
        });
    }

    private void requestForLatestProducts() {
        mWooApi.getProducts(10, 1, "date").enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()){
                    mProductRepository.setLatestProducts(response.body());
                    mBinding.progressBar.setVisibility(View.GONE);
                    mBinding.progressBar.hide();
                    startActivity(MainActivity.newIntent(getContext()));
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                loadInternetError();
            }
        });
    }



    private void loadInternetError() {
        mBinding.textViewNoInternet.setVisibility(View.VISIBLE);
        mBinding.textViewRetry.setVisibility(View.VISIBLE);
    }

}

