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
import com.nezamipour.mehdi.digikala.adapter.CartRecyclerAdapter;
import com.nezamipour.mehdi.digikala.databinding.FragmentCartBinding;
import com.nezamipour.mehdi.digikala.viewmodel.CartFragmentViewModel;

public class CartFragment extends Fragment {

    private CartRecyclerAdapter mCartRecyclerAdapter;
    private FragmentCartBinding mBinding;
    private CartFragmentViewModel mViewModel;

    public CartFragment() {
        // Required empty public constructor
    }

    public static CartFragment newInstance() {
        return new CartFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CartFragmentViewModel.class);

        mViewModel.getCartProducts().observe(
                this, products -> mCartRecyclerAdapter.notifyDataSetChanged());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mCartRecyclerAdapter = new CartRecyclerAdapter(getContext());
        mCartRecyclerAdapter.setProducts(mViewModel.getCartProducts().getValue());
        mBinding.recyclerViewCart.setAdapter(mCartRecyclerAdapter);


    }
}