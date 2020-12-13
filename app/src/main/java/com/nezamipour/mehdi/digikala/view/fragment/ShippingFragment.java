package com.nezamipour.mehdi.digikala.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.gms.maps.model.LatLng;
import com.nezamipour.mehdi.digikala.R;
import com.nezamipour.mehdi.digikala.data.model.customer.Customer;
import com.nezamipour.mehdi.digikala.data.model.customer.Shipping;
import com.nezamipour.mehdi.digikala.databinding.FragmentShippingBinding;
import com.nezamipour.mehdi.digikala.viewmodel.ShippingViewModel;

public class ShippingFragment extends Fragment {


    public static final String KEY = "latLng";
    public static final String SHIPPING_TAG = "shipping";
    private ShippingViewModel mViewModel;
    private FragmentShippingBinding mBinding;
    private String mEmail;
    private String mFirstName;
    private String mLastName;
    private String mPassword;
    private Double mLat;
    private Double mLon;

    public ShippingFragment() {
        // Required empty public constructor
    }

    public static ShippingFragment newInstance() {
        return new ShippingFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mEmail = ShippingFragmentArgs.fromBundle(getArguments()).getEmail();
            mFirstName = ShippingFragmentArgs.fromBundle(getArguments()).getFirstName();
            mLastName = ShippingFragmentArgs.fromBundle(getArguments()).getLastName();
            mPassword = ShippingFragmentArgs.fromBundle(getArguments()).getPassword();
        }

        mViewModel = new ViewModelProvider(this).get(ShippingViewModel.class);
        mViewModel.getConnectionStateLiveData().observe(this, connectionState -> {
            switch (connectionState) {
                case ERROR:
                    showErrorUi();
                    break;
                case LOADING:
                    showLoadingUi();
                    break;
                case START_ACTIVITY:
                    ShippingFragmentDirections.ActionShippingFragmentToLoginLoadingFragment action =
                            ShippingFragmentDirections.actionShippingFragmentToLoginLoadingFragment(mEmail);
                    Navigation.findNavController(getView())
                            .navigate(action);
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
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_shipping, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBinding.buttonFinalAccept.setOnClickListener(v -> {
            Shipping shipping = new Shipping(
                    mFirstName,
                    mLastName,
                    mBinding.editTextAddressOne.getText().toString(),
                    mBinding.editTextPostalCode.getText().toString(),
                    mBinding.editTextCity.getText().toString(),
                    mBinding.editTextState.getText().toString(),
                    mBinding.editTextCountry.getText().toString()
            );

            Customer customer = new Customer(mEmail, mFirstName, mLastName, shipping);
            if (mLat != null && mLon != null)
                mViewModel.postCustomerToDataBase(mEmail, mPassword, mLat, mLon);
            else
                mViewModel.postCustomerToDataBase(mEmail, mPassword);
            mViewModel.postCustomerToServer(customer);

        });

        mBinding.buttonUseMap.setOnClickListener(v ->
                Navigation.findNavController(v)
                        .navigate(ShippingFragmentDirections.actionShippingFragmentToMapFragment()));

        //result back form map fragment
        NavController navController = NavHostFragment.findNavController(this);
        // We use a String here, but any type that can be put in a Bundle is supported
        MutableLiveData<LatLng> liveData = navController.getCurrentBackStackEntry()
                .getSavedStateHandle()
                .getLiveData(KEY);
        liveData.observe(getViewLifecycleOwner(), new Observer<LatLng>() {
            @Override
            public void onChanged(LatLng latLng) {
                mLat = latLng.latitude;
                mLon = latLng.longitude;
                Log.d(SHIPPING_TAG, "onChanged: " + mLat + "    " + mLon);
            }
        });

    }

    private void showLoadingUi() {
        mBinding.textViewFinalAccept.setVisibility(View.GONE);
        mBinding.progressBarFinalAccept.show();
    }

    private void showErrorUi() {
        mBinding.progressBarFinalAccept.hide();
        mBinding.textViewFinalAccept.setVisibility(View.VISIBLE);
    }
}