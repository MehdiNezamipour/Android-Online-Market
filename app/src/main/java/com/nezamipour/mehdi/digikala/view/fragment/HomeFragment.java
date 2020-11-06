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
import com.nezamipour.mehdi.digikala.adapter.ProductRecyclerAdapter;
import com.nezamipour.mehdi.digikala.databinding.FragmentHomeBinding;
import com.nezamipour.mehdi.digikala.util.ImageUtil;
import com.nezamipour.mehdi.digikala.viewmodel.HomeFragmentViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding mBinding;
    private HomeFragmentViewModel mViewModel;

    private ProductRecyclerAdapter mOfferedProductsAdapter;
    private ProductRecyclerAdapter mLatestProductsAdapter;
    private ProductRecyclerAdapter mTopRatingProductsAdapter;
    private ProductRecyclerAdapter mPopularProductsAdapter;
    private ImageSliderAdapter mImageSliderAdapter;


    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new ViewModelProvider(this).get(HomeFragmentViewModel.class);
        mViewModel.fetchDataFromRepository();

        initAdapters();

        mViewModel.getOfferedProductsLiveData().observe(this, products -> {
            mOfferedProductsAdapter.notifyDataSetChanged();
        });

        mViewModel.getLatestProductsLiveData().observe(this, products -> {
            mLatestProductsAdapter.notifyDataSetChanged();
        });

        mViewModel.getTopRatingProductsLiveData().observe(this, products -> {
            mTopRatingProductsAdapter.notifyDataSetChanged();
        });

        mViewModel.getPopularProductsLiveData().observe(this, products -> {
            mPopularProductsAdapter.notifyDataSetChanged();
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mImageSliderAdapter = new ImageSliderAdapter(getContext());
        List<String> stringsResource = new ArrayList<>();

        stringsResource.add(ImageUtil.convertResourceIdToUrl(R.drawable.main_slider_image01));
        stringsResource.add(ImageUtil.convertResourceIdToUrl(R.drawable.main_slider_image02));
        stringsResource.add(ImageUtil.convertResourceIdToUrl(R.drawable.main_slider_image03));
        stringsResource.add(ImageUtil.convertResourceIdToUrl(R.drawable.main_slider_image02));

        mImageSliderAdapter.setStringImageUrl(stringsResource);

        mBinding.imageSlider.setSliderAdapter(mImageSliderAdapter);


        mBinding.recyclerViewOfferedProduct.setAdapter(mOfferedProductsAdapter);
        mBinding.recyclerViewLatestProduct.setAdapter(mLatestProductsAdapter);
        mBinding.recyclerViewTopRatingProduct.setAdapter(mTopRatingProductsAdapter);
        mBinding.recyclerViewPopularProduct.setAdapter(mPopularProductsAdapter);

        setListeners();


    }

    private void setListeners() {
        mBinding.textViewWholeLatestProducts.setOnClickListener(v -> {

            HomeFragmentDirections.ActionHomeFragmentToWholeProductsFragment action = HomeFragmentDirections
                    .actionHomeFragmentToWholeProductsFragment("date");

            Navigation.findNavController(v)
                    .navigate(action);

        });

        mBinding.textViewWholePopularProducts.setOnClickListener(v -> {
            HomeFragmentDirections.ActionHomeFragmentToWholeProductsFragment action = HomeFragmentDirections
                    .actionHomeFragmentToWholeProductsFragment("popularity");

            Navigation.findNavController(v)
                    .navigate(action);
        });

        mBinding.textViewWholeTopRatingProducts.setOnClickListener(v -> {
            HomeFragmentDirections.ActionHomeFragmentToWholeProductsFragment action = HomeFragmentDirections
                    .actionHomeFragmentToWholeProductsFragment("rating");

            Navigation.findNavController(v)
                    .navigate(action);

        });
    }

    public void initAdapters() {
        mOfferedProductsAdapter = new ProductRecyclerAdapter(getContext());
        mOfferedProductsAdapter.setProducts(mViewModel.getOfferedProductsLiveData().getValue());

        mLatestProductsAdapter = new ProductRecyclerAdapter(getContext());
        mLatestProductsAdapter.setProducts(mViewModel.getLatestProductsLiveData().getValue());

        mTopRatingProductsAdapter = new ProductRecyclerAdapter(getContext());
        mTopRatingProductsAdapter.setProducts(mViewModel.getTopRatingProductsLiveData().getValue());

        mPopularProductsAdapter = new ProductRecyclerAdapter(getContext());
        mPopularProductsAdapter.setProducts(mViewModel.getPopularProductsLiveData().getValue());
    }

}