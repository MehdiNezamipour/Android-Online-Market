package com.nezamipour.mehdi.digikala.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.nezamipour.mehdi.digikala.view.fragment.SplashFragment;

public class SplashActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return SplashFragment.newInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }
    }
}