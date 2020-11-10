package com.nezamipour.mehdi.digikala.view.activity;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.nezamipour.mehdi.digikala.view.fragment.SplashFragment;

public class SplashActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context context) {
        return new Intent(context, SplashActivity.class);
    }

    @Override
    protected Fragment createFragment() {
        return SplashFragment.newInstance();
    }


}