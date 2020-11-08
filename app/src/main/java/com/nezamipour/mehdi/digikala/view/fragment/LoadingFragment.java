package com.nezamipour.mehdi.digikala.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.nezamipour.mehdi.digikala.R;
import com.nezamipour.mehdi.digikala.adapter.ImageSliderAdapter;
import com.nezamipour.mehdi.digikala.databinding.FragmentLoadingBinding;
import com.nezamipour.mehdi.digikala.viewmodel.ProductDetailViewModel;

public class LoadingFragment extends Fragment {

    private FragmentLoadingBinding mBinding;

    private Integer mProductId;
    private ImageSliderAdapter mImageSliderAdapter;
    private ProductDetailViewModel mViewModel;

    public LoadingFragment() {
        // Required empty public constructor
    }

    public static LoadingFragment newInstance() {
        return new LoadingFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mProductId = LoadingFragmentArgs.fromBundle(getArguments()).getProductId();
        mViewModel = new ViewModelProvider(this).get(ProductDetailViewModel.class);

        mViewModel.fetchProductFromServer(mProductId);
        mViewModel.getStartNavigationLiveData().observe(this, aBoolean -> {

            if (aBoolean) {
                LoadingFragmentDirections.ActionLoadingFragmentToProductDetailFragment action =
                        LoadingFragmentDirections
                                .actionLoadingFragmentToProductDetailFragment
                                        (mViewModel.getProductMutableLiveData().getValue());
                Navigation.findNavController(getView()).navigate(action);
            }
        });
        mViewModel.getIsError().observe(this, aBoolean -> {
            if (aBoolean)
                loadInternetError();
        });
        mViewModel.getIsLoading().observe(this, aBoolean -> {
            if (!aBoolean)
                loadInternetError();
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.
                inflate(inflater, R.layout.fragment_loading, container, false);


        return inflater.inflate(R.layout.fragment_loading, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding.buttonRetry.setOnClickListener(v -> {
            mViewModel.fetchProductFromServer(mProductId);
            mViewModel.getIsError().setValue(false);
            mViewModel.getIsLoading().setValue(true);
            showLoadingUi();
        });
    }

    private void showLoadingUi() {
        mBinding.buttonRetry.setVisibility(View.GONE);
        mBinding.textViewNoInternet.setVisibility(View.GONE);
        mBinding.progressBarLoadingFragment.setVisibility(View.VISIBLE);
        mBinding.progressBarLoadingFragment.show();
    }


    private void loadInternetError() {
        mBinding.textViewNoInternet.setVisibility(View.VISIBLE);
        mBinding.buttonRetry.setVisibility(View.VISIBLE);
        mBinding.progressBarLoadingFragment.setVisibility(View.GONE);
        mBinding.progressBarLoadingFragment.hide();
    }

}