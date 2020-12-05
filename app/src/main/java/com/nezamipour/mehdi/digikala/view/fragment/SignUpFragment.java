package com.nezamipour.mehdi.digikala.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.nezamipour.mehdi.digikala.R;
import com.nezamipour.mehdi.digikala.databinding.FragmentSignUpBinding;
import com.nezamipour.mehdi.digikala.util.StringUtil;

public class SignUpFragment extends Fragment {

    private FragmentSignUpBinding mBinding;
    private String mEmail;


    public SignUpFragment() {
        // Required empty public constructor
    }

    public static SignUpFragment newInstance() {
        return new SignUpFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEmail = SignUpFragmentArgs.fromBundle(getArguments()).getEmail();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

            mBinding.buttonLoginNextStep.setOnClickListener(v -> {
                if (StringUtil.checkInputs(
                        mBinding.editTextFirstName.getText().toString(),
                        mBinding.editTextLastName.getText().toString(),
                        mBinding.editTextPassword.getText().toString())) {
                    SignUpFragmentDirections.ActionSignUpFragmentToShippingFragment action =
                            SignUpFragmentDirections.actionSignUpFragmentToShippingFragment(
                                    mBinding.editTextFirstName.getText().toString(),
                                    mBinding.editTextLastName.getText().toString(),
                                    mBinding.editTextPassword.getText().toString(),
                                    mEmail
                            );
                    Navigation.findNavController(v).navigate(action);
                }
            });

        }




}