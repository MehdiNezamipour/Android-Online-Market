package com.nezamipour.mehdi.digikala.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.nezamipour.mehdi.digikala.R;
import com.nezamipour.mehdi.digikala.databinding.FragmentLoadingBinding;
import com.nezamipour.mehdi.digikala.viewmodel.CartFragmentViewModel;


public class CartProductsLoadingFragment extends Fragment {


    private CartFragmentViewModel mViewModel;
    private FragmentLoadingBinding mBinding;


    public CartProductsLoadingFragment() {
        // Required empty public constructor
    }

    public static CartProductsLoadingFragment newInstance() {
        return new CartProductsLoadingFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new ViewModelProvider(this).get(CartFragmentViewModel.class);
        mViewModel.fetchCartProducts();

        mViewModel.getConnectionStateLiveData().observe(this, connectionState -> {
            switch (connectionState) {
                case START_ACTIVITY:
                    Navigation.findNavController(getView())
                            .navigate(CartProductsLoadingFragmentDirections.actionNavFragCartToCartFragment());
                    break;
                case ERROR:
                    loadInternetError();
                    break;
                case LOADING:
                    showLoadingUi();
                    break;
                default:
                    break;
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_loading, container, false);
        return mBinding.getRoot();
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