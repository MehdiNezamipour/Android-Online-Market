package com.nezamipour.mehdi.digikala.view.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.nezamipour.mehdi.digikala.R;
import com.nezamipour.mehdi.digikala.adapter.SearchRecyclerAdapter;
import com.nezamipour.mehdi.digikala.data.model.product.Product;
import com.nezamipour.mehdi.digikala.databinding.ActivityMainBinding;
import com.nezamipour.mehdi.digikala.databinding.FragmentSearchBinding;
import com.nezamipour.mehdi.digikala.util.enums.SearchState;
import com.nezamipour.mehdi.digikala.viewmodel.SearchFragmentViewModel;

import java.util.List;

public class SearchFragment extends Fragment {

    private FragmentSearchBinding mBinding;
    private SearchRecyclerAdapter mSearchRecyclerAdapter;
    private SearchFragmentViewModel mViewModel;
    private ActivityMainBinding mMainBinding;

    public SearchFragment() {
        // Required empty public constructor
    }

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mViewModel = new ViewModelProvider(this).get(SearchFragmentViewModel.class);

        mViewModel.getSearchState().observe(this, searchState -> {
            switch (searchState) {
                case RESULT_BACKED:
                    mSearchRecyclerAdapter.setProducts(mViewModel.getResultsLiveData().getValue());
                    mSearchRecyclerAdapter.notifyDataSetChanged();
                    break;
                case ERROR:
                    break;
                case SEARCHING:
                    break;
                default:
                    break;
            }

        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBinding.toolbarSearch.editTextSearch.requestFocus();
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(mBinding.toolbarSearch.editTextSearch, InputMethodManager.SHOW_IMPLICIT);


        mSearchRecyclerAdapter = new SearchRecyclerAdapter();
        mBinding.recyclerViewSearchResult.setAdapter(mSearchRecyclerAdapter);
        mBinding.toolbarSearch.imageViewBackToHome.setOnClickListener(v -> getActivity().onBackPressed());


        mBinding.toolbarSearch.editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                mViewModel.fetchResults(s.toString());
            }
        });
    }
}