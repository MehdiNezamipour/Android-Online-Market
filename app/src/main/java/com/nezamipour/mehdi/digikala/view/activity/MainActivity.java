package com.nezamipour.mehdi.digikala.view.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.nezamipour.mehdi.digikala.R;
import com.nezamipour.mehdi.digikala.databinding.ActivityMainBinding;
import com.nezamipour.mehdi.digikala.util.UiUtil;

public class MainActivity extends AppCompatActivity {


    private BroadcastReceiver mInternetState;

    public static Intent newIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        NavController navController = Navigation.findNavController(this, R.id.main_nav_host);
        mBinding.bottomNavigation.setSelectedItemId(R.id.nav_fragHome);
        NavigationUI.setupWithNavController(mBinding.bottomNavigation, navController);

        mBinding.mainSearchToolbar.setOnClickListener(v -> navController.navigate(R.id.action_global_searchFragment));

        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            switch (destination.getId()) {
                case R.id.nav_fragHome:
                case R.id.nav_fragCategory:
                    mBinding.bottomNavigation.setVisibility(View.VISIBLE);
                    mBinding.mainSearchToolbar.setVisibility(View.VISIBLE);
                    break;
                case R.id.categoryProductsLoadingFragment:
                case R.id.signUpFragment:
                case R.id.shippingFragment:
                case R.id.nav_fragCart:
                case R.id.loginFragment:
                case R.id.wholeProductsFragment:
                case R.id.productDetailFragment:
                case R.id.loginLoadingFragment:
                case R.id.checkRegistrationFragment:
                case R.id.customerFragment:
                    mBinding.mainSearchToolbar.setVisibility(View.GONE);
                    mBinding.bottomNavigation.setVisibility(View.VISIBLE);
                    break;

                case R.id.searchFragment:
                    mBinding.bottomNavigation.setVisibility(View.GONE);
                    mBinding.mainSearchToolbar.setVisibility(View.GONE);
                    break;


                default:
                    break;
            }
        });

        mInternetState = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (!internetConnected(MainActivity.this)) {
                    Snackbar snackbar = Snackbar.make(mBinding.getRoot(), R.string.no_internet, BaseTransientBottomBar.LENGTH_SHORT);
                    View snackBarView = snackbar.getView();
                    snackBarView.setTranslationY(-(UiUtil.convertDpToPixel(48, MainActivity.this)));
                    snackbar.show();
                }
            }
        };

    }


    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(mInternetState, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(mInternetState);

    }

    private boolean internetConnected(Context context) {
        try {
            ConnectivityManager connectivityManager =
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return (networkInfo != null && networkInfo.isConnected());
        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        }
    }


}