package com.nezamipour.mehdi.digikala.view.activity;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.nezamipour.mehdi.digikala.view.fragment.MainFragment;

public class MainActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }


    @Override
    protected Fragment createFragment() {
        return MainFragment.newInstance();
    }


}