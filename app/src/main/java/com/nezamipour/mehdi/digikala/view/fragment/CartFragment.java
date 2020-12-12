package com.nezamipour.mehdi.digikala.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.nezamipour.mehdi.digikala.R;
import com.nezamipour.mehdi.digikala.adapter.CartRecyclerAdapter;
import com.nezamipour.mehdi.digikala.databinding.FragmentCartBinding;
import com.nezamipour.mehdi.digikala.util.enums.ConnectionState;
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

        mViewModel.fetchCartProducts();

        mViewModel.getConnectionStateLiveData().observe(this, connectionState -> {
            switch (connectionState) {
                case START_ACTIVITY:
                    mBinding.loadingView.getRoot().setVisibility(View.GONE);
                    mBinding.mainView.setVisibility(View.VISIBLE);
                    initUi();
                    mViewModel.getCartProducts().observe(
                            this, products -> {
                                mCartRecyclerAdapter.notifyDataSetChanged();
                                if (products.isEmpty())
                                    mBinding.buttonFinishShopping.setEnabled(false);
                            });
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


        mViewModel.getTotalPriceLiveData().observe(this, s -> mBinding.textViewSumOfCart.setText(s));
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

        mBinding.loadingView.buttonRetry.setOnClickListener(v -> {
            mViewModel.fetchCartProducts();
            showLoadingUi();
        });

        mBinding.buttonFinishShopping.setOnClickListener(v -> {
            //TODO Later Fix Bug : when app come up and not click on
            // login fragment at least for one time customer live data is null
            if (mViewModel.getCurrentLoginCustomer() == null && !mViewModel.getCartProducts().getValue().isEmpty()) {
                Toast.makeText(getContext(), R.string.login_for_payment, Toast.LENGTH_SHORT).show();
            } else if (mViewModel.getCustomerLiveData().getValue() != null) {
                CartFragmentDirections.ActionCartFragmentToFinishShoppingFragment action =
                        CartFragmentDirections.actionCartFragmentToFinishShoppingFragment(mViewModel.getCustomerLiveData().getValue()
                                , mViewModel.getTotalPriceLiveData().getValue());
                Navigation.findNavController(v).navigate(action);
            }
        });
    }

    private void initUi() {
        if (mViewModel.getCartProducts().getValue().isEmpty())
            mBinding.buttonFinishShopping.setEnabled(false);
        mBinding.textViewSumOfCart.setText(mViewModel.getTotalPriceLiveData().getValue());
        mCartRecyclerAdapter = new CartRecyclerAdapter(getContext());
        mCartRecyclerAdapter.setProducts(mViewModel.getCartProducts().getValue());
        mBinding.recyclerViewCart.setAdapter(mCartRecyclerAdapter);
    }

    private void showLoadingUi() {
        mBinding.loadingView.buttonRetry.setVisibility(View.GONE);
        mBinding.loadingView.textViewNoInternet.setVisibility(View.GONE);
        mBinding.loadingView.progressBarLoadingFragment.setVisibility(View.VISIBLE);
        mBinding.loadingView.progressBarLoadingFragment.show();
    }

    private void loadInternetError() {
        mBinding.loadingView.buttonRetry.setVisibility(View.VISIBLE);
        mBinding.loadingView.textViewNoInternet.setVisibility(View.VISIBLE);
        mBinding.loadingView.progressBarLoadingFragment.setVisibility(View.GONE);
        mBinding.loadingView.progressBarLoadingFragment.hide();
    }
}