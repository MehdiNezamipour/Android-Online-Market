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

import com.nezamipour.mehdi.digikala.R;
import com.nezamipour.mehdi.digikala.adapter.WholeProductsAdapter;
import com.nezamipour.mehdi.digikala.databinding.FragmentWholeProductsBinding;
import com.nezamipour.mehdi.digikala.viewmodel.WholeProductFragmentViewModel;

public class WholeProductsFragment extends Fragment {


    private FragmentWholeProductsBinding mBinding;
    private WholeProductFragmentViewModel mViewModel;
    private WholeProductsAdapter mWholeProductsAdapter;


    public WholeProductsFragment() {
        // Required empty public constructor
    }

    public static WholeProductsFragment newInstance() {
        WholeProductsFragment fragment = new WholeProductsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        assert getArguments() != null;
        String orderBy = WholeProductsFragmentArgs.fromBundle(getArguments()).getOrderBy();

        mViewModel = new ViewModelProvider(this).get(WholeProductFragmentViewModel.class);
        mViewModel.fetchDataFromRepository(orderBy);
        initAdapter();

        mViewModel.getProducts().observe(this, products -> {
            mWholeProductsAdapter.notifyDataSetChanged();
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_whole_products, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding.recyclerViewWholeProducts.setAdapter(mWholeProductsAdapter);


    }

    public void initAdapter() {
        mWholeProductsAdapter = new WholeProductsAdapter();
        mWholeProductsAdapter.setProducts(mViewModel.getProducts().getValue());
    }
}