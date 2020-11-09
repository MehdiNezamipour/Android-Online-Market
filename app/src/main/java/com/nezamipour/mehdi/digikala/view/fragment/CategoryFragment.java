package com.nezamipour.mehdi.digikala.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.nezamipour.mehdi.digikala.R;
import com.nezamipour.mehdi.digikala.adapter.CategoryRecyclerAdapter;
import com.nezamipour.mehdi.digikala.databinding.FragmentCategoryBinding;
import com.nezamipour.mehdi.digikala.viewmodel.CategoryFragmentViewModel;

public class CategoryFragment extends Fragment {

    private FragmentCategoryBinding mBinding;
    private CategoryFragmentViewModel mViewModel;

    private CategoryRecyclerAdapter mChildParent1Adapter;
    private CategoryRecyclerAdapter mChildParent2Adapter;
    private CategoryRecyclerAdapter mChildParent3Adapter;
    private CategoryRecyclerAdapter mChildParent4Adapter;
    private CategoryRecyclerAdapter mChildParent5Adapter;
    private CategoryRecyclerAdapter mChildParent6Adapter;


    public CategoryFragment() {
        // Required empty public constructor
    }

    public static CategoryFragment newInstance() {
        return new CategoryFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CategoryFragmentViewModel.class);
        mViewModel.fetchCategories();

        mViewModel.getChildParent1LiveData().observe(this, categories -> {
            mChildParent1Adapter.notifyDataSetChanged();
        });
        mViewModel.getChildParent2LiveData().observe(this, categories -> {
            mChildParent2Adapter.notifyDataSetChanged();

        });
        mViewModel.getChildParent3LiveData().observe(this, categories -> {
            mChildParent3Adapter.notifyDataSetChanged();

        });
        mViewModel.getChildParent4LiveData().observe(this, categories -> {
            mChildParent4Adapter.notifyDataSetChanged();

        });
        mViewModel.getChildParent5LiveData().observe(this, categories -> {
            mChildParent5Adapter.notifyDataSetChanged();

        });
        mViewModel.getChildParent6LiveData().observe(this, categories -> {
            mChildParent6Adapter.notifyDataSetChanged();

        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.
                inflate(inflater, R.layout.fragment_category, container, false);

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpAdapters();
        initUi();

    }

    private void initUi() {
        mBinding.textViewCategory1.setText(mViewModel.getParentCategoriesLiveData().getValue().get(0).getName());
        mBinding.textViewCategory2.setText(mViewModel.getParentCategoriesLiveData().getValue().get(1).getName());
        mBinding.textViewCategory3.setText(mViewModel.getParentCategoriesLiveData().getValue().get(2).getName());
        mBinding.textViewCategory4.setText(mViewModel.getParentCategoriesLiveData().getValue().get(3).getName());
        mBinding.textViewCategory5.setText(mViewModel.getParentCategoriesLiveData().getValue().get(4).getName());
        mBinding.textViewCategory6.setText(mViewModel.getParentCategoriesLiveData().getValue().get(5).getName());

    }

    private void setUpAdapters() {
        mChildParent1Adapter = new CategoryRecyclerAdapter();
        mChildParent1Adapter.setCategories(mViewModel.getChildParent1LiveData().getValue());
        mBinding.recyclerViewCategory1.setAdapter(mChildParent1Adapter);

        mChildParent2Adapter = new CategoryRecyclerAdapter();
        mChildParent2Adapter.setCategories(mViewModel.getChildParent2LiveData().getValue());
        mBinding.recyclerViewCategory2.setAdapter(mChildParent2Adapter);

        mChildParent3Adapter = new CategoryRecyclerAdapter();
        mChildParent3Adapter.setCategories(mViewModel.getChildParent3LiveData().getValue());
        mBinding.recyclerViewCategory3.setAdapter(mChildParent3Adapter);


        mChildParent4Adapter = new CategoryRecyclerAdapter();
        mChildParent4Adapter.setCategories(mViewModel.getChildParent4LiveData().getValue());
        mBinding.recyclerViewCategory4.setAdapter(mChildParent4Adapter);


        mChildParent5Adapter = new CategoryRecyclerAdapter();
        mChildParent5Adapter.setCategories(mViewModel.getChildParent5LiveData().getValue());
        mBinding.recyclerViewCategory5.setAdapter(mChildParent5Adapter);

        mChildParent6Adapter = new CategoryRecyclerAdapter();
        mChildParent6Adapter.setCategories(mViewModel.getChildParent6LiveData().getValue());
        mBinding.recyclerViewCategory6.setAdapter(mChildParent6Adapter);
    }
}