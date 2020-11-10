package com.nezamipour.mehdi.digikala.view.fragment;

import android.content.Intent;
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
import com.nezamipour.mehdi.digikala.databinding.FragmentSplashBinding;
import com.nezamipour.mehdi.digikala.util.enums.ConnectionState;
import com.nezamipour.mehdi.digikala.view.activity.MainActivity;
import com.nezamipour.mehdi.digikala.viewmodel.SplashFragmentViewModel;

public class SplashFragment extends Fragment {

    private SplashFragmentViewModel mViewModel;
    private FragmentSplashBinding mBinding;

    public SplashFragment() {
        // Required empty public constructor
    }

    public static SplashFragment newInstance() {
        return new SplashFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new ViewModelProvider(this).get(SplashFragmentViewModel.class);
        mViewModel.fetchInitData();


        mViewModel.getConnectionStateLiveData().observe(this, connectionState -> {
            switch (connectionState) {
                case ERROR:
                    loadInternetError();
                    break;
                case LOADING:
                    showLoadingUi();
                    break;
                case START_ACTIVITY:
                    getActivity().startActivity(MainActivity.newIntent(getContext()));
                    getActivity().finish();
                    break;
                default:
                    break;
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getActivity().finish();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_splash,
                container,
                false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding.buttonRetry.setOnClickListener(v -> {
            mViewModel.fetchInitData();
            showLoadingUi();
            mViewModel.getConnectionStateLiveData().setValue(ConnectionState.LOADING);
        });
    }

    private void showLoadingUi() {
        mBinding.buttonRetry.setVisibility(View.GONE);
        mBinding.textViewNoInternet.setVisibility(View.GONE);
        mBinding.progressBar.setVisibility(View.VISIBLE);
        mBinding.progressBar.show();
    }


    private void loadInternetError() {
        mBinding.textViewNoInternet.setVisibility(View.VISIBLE);
        mBinding.buttonRetry.setVisibility(View.VISIBLE);
        mBinding.progressBar.setVisibility(View.GONE);
        mBinding.progressBar.hide();

    }

}

