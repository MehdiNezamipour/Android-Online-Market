package com.nezamipour.mehdi.digikala.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nezamipour.mehdi.digikala.R;
import com.nezamipour.mehdi.digikala.data.model.customer.Customer;
import com.nezamipour.mehdi.digikala.databinding.FragmentCustomerBinding;
import com.nezamipour.mehdi.digikala.viewmodel.LoadingLoginViewModel;

public class CustomerFragment extends Fragment {

    private LoadingLoginViewModel mViewModel;
    private FragmentCustomerBinding mBinding;
    private String mEmail;


    public CustomerFragment() {
        // Required empty public constructor
    }

    public static CustomerFragment newInstance() {
        return new CustomerFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new ViewModelProvider(this).get(LoadingLoginViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_customer, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Customer customer = mViewModel.getCustomerLiveData().getValue();
        mBinding.textViewCustomerName.setText(customer.getFullName());
        mBinding.textViewCustomerUserName.setText(customer.getUsername());

    }
}