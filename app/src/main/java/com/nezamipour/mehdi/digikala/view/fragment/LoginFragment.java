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
import com.nezamipour.mehdi.digikala.databinding.FragmentLoginBinding;
import com.nezamipour.mehdi.digikala.viewmodel.LoginViewModel;

public class LoginFragment extends Fragment {

    private LoginViewModel mViewModel;
    private FragmentLoginBinding mBinding;
    private String mEmail;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEmail = LoginFragmentArgs.fromBundle(getArguments()).getEmail();

        mViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBinding.buttonLogin.setOnClickListener(v -> {
            if (mViewModel.authorizePassword(mEmail, mBinding.editTextLoginPassword.getText().toString())) {
                mViewModel.changeStateToLogIn(mEmail);
                Navigation.findNavController(v)
                        .navigate(LoginFragmentDirections.actionLoginFragmentToCustomerFragment());
            } else
                Toast.makeText(getContext(),
                        getResources().getString(R.string.incorrect_pass),
                        Toast.LENGTH_SHORT)
                        .show();
        });


    }
}