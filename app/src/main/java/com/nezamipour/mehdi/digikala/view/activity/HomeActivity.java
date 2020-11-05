package com.nezamipour.mehdi.digikala.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.nezamipour.mehdi.digikala.view.fragment.HomeFragment;

public class HomeActivity extends MainActivity {

    public static final String HOME_FRAGMENT_TAG = "com.nezamipour.mehdi.digikala.homeFragment";

    public static Intent newIntent(Context context) {
        return new Intent(context, HomeActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected String getFragmentTag() {
        return HOME_FRAGMENT_TAG;
    }

    @Override
    protected Fragment createFragment() {
        return HomeFragment.newInstance();
    }


    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1)
            getSupportFragmentManager().popBackStack();
        else {
            Intent intent = new Intent(HomeActivity.this, SplashActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("EXIT", true);
            startActivity(intent);
        }
    }
}