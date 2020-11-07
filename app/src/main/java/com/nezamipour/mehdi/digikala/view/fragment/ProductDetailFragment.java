package com.nezamipour.mehdi.digikala.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.nezamipour.mehdi.digikala.R;
import com.nezamipour.mehdi.digikala.adapter.ImageSliderAdapter;
import com.nezamipour.mehdi.digikala.data.model.product.Product;
import com.nezamipour.mehdi.digikala.databinding.FragmentProductDetailBinding;
import com.nezamipour.mehdi.digikala.viewmodel.ProductDetailFragmentViewModel;

public class ProductDetailFragment extends Fragment {


    private Integer mProductId;
    private ImageSliderAdapter mImageSliderAdapter;
    private ProductDetailFragmentViewModel mViewModel;
    private FragmentProductDetailBinding mBinding;

    public ProductDetailFragment() {
        // Required empty public constructor
    }

    public static ProductDetailFragment newInstance() {
        return new ProductDetailFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProductId = ProductDetailFragmentArgs.fromBundle(getArguments()).getProductId();
        mViewModel = new ViewModelProvider(this).get(ProductDetailFragmentViewModel.class);

        mViewModel.fetchProductFromServer(mProductId);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil
                .inflate(inflater,
                        R.layout.fragment_product_detail,
                        container,
                        false);


        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding.setViewModel(mViewModel);
        mBinding.executePendingBindings();

    }
}