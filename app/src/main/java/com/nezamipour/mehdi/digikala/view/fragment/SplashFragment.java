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
import com.nezamipour.mehdi.digikala.databinding.FragmentSplashBinding;
import com.nezamipour.mehdi.digikala.view.activity.MainActivity;
import com.nezamipour.mehdi.digikala.viewmodel.SplashFragmentViewModel;

public class SplashFragment extends Fragment {

    private SplashFragmentViewModel mViewModel;
    private FragmentSplashBinding mBinding;

    public SplashFragment() {
        // Required empty public constructor
    }

    public static SplashFragment newInstance() {
        SplashFragment fragment = new SplashFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new ViewModelProvider(this).get(SplashFragmentViewModel.class);
        mViewModel.fetchInitData();

        mViewModel.getIsLoading().observe(this, aBoolean -> {
            if (!aBoolean) {
                loadInternetError();
            }
        });

        mViewModel.getIsError().observe(this, aBoolean -> {
            if (aBoolean) {
                loadInternetError();
            }
        });
        mViewModel.getStartMainActivity().observe(this, aBoolean -> {
            if (aBoolean) {
                getActivity().startActivity(MainActivity.newIntent(getContext()));
            }
        });

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
            mViewModel.getIsError().setValue(false);
            mViewModel.getIsLoading().setValue(true);
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

