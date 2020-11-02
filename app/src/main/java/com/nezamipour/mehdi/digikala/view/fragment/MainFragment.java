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
import com.nezamipour.mehdi.digikala.adapter.ProductRecyclerAdapter;
import com.nezamipour.mehdi.digikala.data.database.ProductRepository;
import com.nezamipour.mehdi.digikala.databinding.FragmentMainBinding;

public class MainFragment extends Fragment {

    private ProductRepository mProductRepository;
    private FragmentMainBinding mBinding;
    private ProductRecyclerAdapter mProductRecyclerAdapter;


    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProductRepository = ProductRepository.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();

    }

    private void initView() {
        if (mProductRecyclerAdapter == null) {
            mProductRecyclerAdapter = new ProductRecyclerAdapter(getContext());
            mProductRecyclerAdapter.setProducts(mProductRepository.getOfferedProducts());
            mBinding.recyclerViewOfferedProduct.setAdapter(mProductRecyclerAdapter);
        }
        else{
            mBinding.recyclerViewOfferedProduct.setAdapter(mProductRecyclerAdapter);
            mProductRecyclerAdapter.setProducts(mProductRepository.getOfferedProducts());
            mProductRecyclerAdapter.notifyDataSetChanged();
        }
    }
}