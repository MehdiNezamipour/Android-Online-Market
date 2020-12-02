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

    private ProductRecyclerAdapter mOnSaleProductsAdapter;
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

        //test room
        /*CartRepository cartRepository = CartRepository.getInstance(getContext());
        Log.d("HomeFragment", cartRepository.get(1).toString());*/


        initAdapters();

        mViewModel.getOfferedProductsLiveData().observe(this, products -> {
            mOnSaleProductsAdapter.notifyDataSetChanged();
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

/*        Toolbar toolbar = getActivity().findViewById(R.id.main_toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);*/

        mImageSliderAdapter = new ImageSliderAdapter(getContext());
        List<String> stringsResource = new ArrayList<>();

        stringsResource.add(ImageUtil.convertResourceIdToUrl(R.drawable.main_slider_image01));
        stringsResource.add(ImageUtil.convertResourceIdToUrl(R.drawable.main_slider_image02));
        stringsResource.add(ImageUtil.convertResourceIdToUrl(R.drawable.main_slider_image03));
        stringsResource.add(ImageUtil.convertResourceIdToUrl(R.drawable.main_slider_image02));

        mImageSliderAdapter.setStringImageUrl(stringsResource);

        mBinding.imageSlider.setSliderAdapter(mImageSliderAdapter);


        mBinding.recyclerViewOnSaleProduct.setAdapter(mOnSaleProductsAdapter);
        mBinding.recyclerViewLatestProduct.setAdapter(mLatestProductsAdapter);
        mBinding.recyclerViewTopRatingProduct.setAdapter(mTopRatingProductsAdapter);
        mBinding.recyclerViewPopularProduct.setAdapter(mPopularProductsAdapter);

        setListeners();


    }

    private void setListeners() {
        mBinding.textViewWholeOnSaleProducts.setOnClickListener(v -> navigateToWholeProductsFragment(v, "onSale"));

        mBinding.textViewWholeLatestProducts.setOnClickListener(v -> navigateToWholeProductsFragment(v, "date"));

        mBinding.textViewWholePopularProducts.setOnClickListener(v -> navigateToWholeProductsFragment(v, "popularity"));

        mBinding.textViewWholeTopRatingProducts.setOnClickListener(v -> navigateToWholeProductsFragment(v, "rating"));

        mBinding.homeToolbar.setOnClickListener(v -> Navigation.findNavController(v).navigate(HomeFragmentDirections.actionNavFragHomeToSearchFragment()));
    }

    private void navigateToWholeProductsFragment(View v, String orderBy) {
        HomeFragmentDirections.ActionHomeFragmentToWholeProductsFragment action = HomeFragmentDirections
                .actionHomeFragmentToWholeProductsFragment(orderBy);
        Navigation.findNavController(v)
                .navigate(action);
    }

    public void initAdapters() {
        mOnSaleProductsAdapter = new ProductRecyclerAdapter();
        mOnSaleProductsAdapter.setProducts(mViewModel.getOfferedProductsLiveData().getValue());

        mLatestProductsAdapter = new ProductRecyclerAdapter();
        mLatestProductsAdapter.setProducts(mViewModel.getLatestProductsLiveData().getValue());

        mTopRatingProductsAdapter = new ProductRecyclerAdapter();
        mTopRatingProductsAdapter.setProducts(mViewModel.getTopRatingProductsLiveData().getValue());

        mPopularProductsAdapter = new ProductRecyclerAdapter();
        mPopularProductsAdapter.setProducts(mViewModel.getPopularProductsLiveData().getValue());
    }

}