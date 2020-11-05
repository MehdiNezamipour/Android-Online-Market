package com.nezamipour.mehdi.digikala.view.activity;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.nezamipour.mehdi.digikala.data.model.product.Category;
import com.nezamipour.mehdi.digikala.view.fragment.WholeProductsFragment;

public class WholeProductsActivity extends MainActivity {


    public static final String WHOLE_PRODUCTS_FRAGMENT_TAG = "com.nezamipour.mehdi.digikala.wholeProductsFragment";
    public static final String EXTRA_ORDER_BY = "com.nezamipour.mehdi.digikala.orderBy";
    public static final String EXTRA_CATEGORY = "category";

    public static Intent newIntent(Context context, String orderBy) {
        Intent intent = new Intent(context, WholeProductsActivity.class);
        intent.putExtra(EXTRA_ORDER_BY, orderBy);
        return intent;
    }

    public static Intent newIntent(Context context, Category category) {
        Intent intent = new Intent(context, WholeProductsActivity.class);
        intent.putExtra(EXTRA_CATEGORY, category);
        return intent;
    }

    @Override
    protected String getFragmentTag() {
        return WHOLE_PRODUCTS_FRAGMENT_TAG;
    }

    @Override
    protected Fragment createFragment() {
        return WholeProductsFragment.newInstance(getIntent().getStringExtra(EXTRA_ORDER_BY));
    }
}