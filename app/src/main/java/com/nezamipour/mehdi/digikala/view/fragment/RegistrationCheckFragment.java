package com.nezamipour.mehdi.digikala.view.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.nezamipour.mehdi.digikala.R;
import com.nezamipour.mehdi.digikala.databinding.FragmentRegistrationCheckBinding;
import com.nezamipour.mehdi.digikala.viewmodel.RegistrationCheckViewModel;

public class RegistrationCheckFragment extends Fragment {


    private RegistrationCheckViewModel mViewModel;
    private FragmentRegistrationCheckBinding mBinding;

    public RegistrationCheckFragment() {
        // Required empty public constructor
    }

    public static RegistrationCheckFragment newInstance() {
        return new RegistrationCheckFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(RegistrationCheckViewModel.class);

        mViewModel.getConnectionStateLiveData().observe(this, connectionState -> {
            switch (connectionState) {
                case START_ACTIVITY:
                    if (mViewModel.getCustomerLiveData().getValue() != null) {
                        RegistrationCheckFragmentDirections.ActionCheckRegistrationFragmentToLoginFragment action =
                                RegistrationCheckFragmentDirections
                                        .actionCheckRegistrationFragmentToLoginFragment(mBinding.editTextEmail.getText().toString());
                        Navigation.findNavController(getView()).navigate(action);
                    } else {
                        RegistrationCheckFragmentDirections.ActionCheckRegistrationFragmentToSignUpFragment action =
                                RegistrationCheckFragmentDirections
                                        .actionCheckRegistrationFragmentToSignUpFragment(mBinding.editTextEmail.getText().toString());
                        Navigation.findNavController(getView()).navigate(action);
                    }
                    break;
                case LOADING:
                    showLoadingUi();
                    break;
                case ERROR:
                    showErrorUi();
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
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_registration_check, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBinding.buttonLoginStepOne.setOnClickListener(v -> {
            if (isValidEmail(mBinding.editTextEmail.getText().toString())) {
                mViewModel.fetchCustomerFromServer(mBinding.editTextEmail.getText().toString());
            }
        });
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public void showErrorUi() {
        mBinding.progressBarCheckRegistration.hide();
        mBinding.textViewCheckRegistration.setVisibility(View.VISIBLE);
    }

    public void showLoadingUi() {
        mBinding.progressBarCheckRegistration.show();
        mBinding.textViewCheckRegistration.setVisibility(View.GONE);
    }
}