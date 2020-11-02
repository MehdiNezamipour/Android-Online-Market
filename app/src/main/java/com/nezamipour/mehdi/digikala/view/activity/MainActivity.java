package com.nezamipour.mehdi.digikala.view.activity;

import androidx.fragment.app.Fragment;

import com.nezamipour.mehdi.digikala.view.fragment.MainFragment;

public class MainActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return MainFragment.newInstance();
    }


}