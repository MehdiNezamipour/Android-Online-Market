package com.nezamipour.mehdi.digikala.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.nezamipour.mehdi.digikala.R;
import com.nezamipour.mehdi.digikala.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {


    public static Intent newIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        NavController navController = Navigation.findNavController(this, R.id.main_nav_host);
        mBinding.navigationButton.setSelectedItemId(R.id.nav_fragHome);

        NavigationUI.setupWithNavController(mBinding.navigationButton, navController);


    }

}