package com.nezamipour.mehdi.digikala.view.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.nezamipour.mehdi.digikala.R;
import com.nezamipour.mehdi.digikala.databinding.ActivityMainBinding;
import com.nezamipour.mehdi.digikala.view.fragment.HomeFragmentDirections;

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


        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            switch (destination.getId()) {
                case R.id.nav_fragHome:
                case R.id.nav_fragCategory:
                    mBinding.bottomNavigation.setVisibility(View.VISIBLE);
                    break;
                case R.id.wholeProductsFragment:
                    mBinding.bottomNavigation.setVisibility(View.VISIBLE);
                case R.id.productDetailFragment:
                    //TODO : But add a special tool bar to this fragment to click on close button to return to before fragment
                    mBinding.bottomNavigation.setVisibility(View.GONE);
                case R.id.searchFragment:
                    mBinding.bottomNavigation.setVisibility(View.GONE);
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
                    snackBarView.setTranslationY(-(convertDpToPixel(48, MainActivity.this)));
                    snackbar.show();
                }
            }
        };

    }


    public static float convertDpToPixel(float dp, Context context) {
        return dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
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