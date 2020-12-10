package com.nezamipour.mehdi.digikala.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nezamipour.mehdi.digikala.R;
import com.nezamipour.mehdi.digikala.data.model.customer.Customer;
import com.nezamipour.mehdi.digikala.databinding.FragmentFinishShoppingBinding;
import com.nezamipour.mehdi.digikala.util.StringUtil;
import com.nezamipour.mehdi.digikala.util.enums.ConnectionState;
import com.nezamipour.mehdi.digikala.viewmodel.FinishShoppingFragmentViewModel;

public class FinishShoppingFragment extends Fragment {

    private FinishShoppingFragmentViewModel mViewModel;
    private FragmentFinishShoppingBinding mBinding;
    private Customer mCustomer;
    private String mTotalPrice;

    public FinishShoppingFragment() {
        // Required empty public constructor
    }


    public static FinishShoppingFragment newInstance() {
        return new FinishShoppingFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCustomer = FinishShoppingFragmentArgs.fromBundle(getArguments()).getCustomer();
        mTotalPrice = FinishShoppingFragmentArgs.fromBundle(getArguments()).getTotalPrice();

        mViewModel = new ViewModelProvider(this).get(FinishShoppingFragmentViewModel.class);

        mViewModel.getConnectionStateLiveData().observe(this, new Observer<ConnectionState>() {
            @Override
            public void onChanged(ConnectionState connectionState) {
                if (connectionState == ConnectionState.START_ACTIVITY) {
                    mTotalPrice = StringUtil
                            .approveCouponAmount(mTotalPrice
                                    , mViewModel.getCouponLiveData().getValue().getAmount());
                    mBinding.textViewTotalPriceNumber
                            .setText(mTotalPrice);

                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_finish_shopping, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding.textViewAddress.setText(mCustomer.getShipping().getAddress1());
        mBinding.textViewTotalPriceNumber.setText(mTotalPrice);

        mBinding.buttonAddCoupon.setOnClickListener(v -> {
            if (!mBinding.editTextAddCoupon.getText().toString().trim().isEmpty())
                mViewModel.fetchCoupon(mBinding.editTextAddCoupon.getText().toString());
        });

    }
}