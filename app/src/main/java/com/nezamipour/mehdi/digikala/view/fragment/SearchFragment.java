package com.nezamipour.mehdi.digikala.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.nezamipour.mehdi.digikala.R;
import com.nezamipour.mehdi.digikala.adapter.SearchRecyclerAdapter;
import com.nezamipour.mehdi.digikala.databinding.FragmentSearchBinding;
import com.nezamipour.mehdi.digikala.viewmodel.SearchFragmentViewModel;

public class SearchFragment extends Fragment {

    private FragmentSearchBinding mBinding;
    private SearchRecyclerAdapter mSearchRecyclerAdapter;
    private SearchFragmentViewModel mViewModel;

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
        openKeyboardWhenFragmentComeUp();
        initAdapter();
        setListeners();

    }

    private void initAdapter() {
        mSearchRecyclerAdapter = new SearchRecyclerAdapter();
        mBinding.recyclerViewSearchResult.setAdapter(mSearchRecyclerAdapter);
    }

    private void openKeyboardWhenFragmentComeUp() {
        mBinding.toolbarSearch.editTextSearch.requestFocus();
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(mBinding.toolbarSearch.editTextSearch, InputMethodManager.SHOW_IMPLICIT);
    }

    private void setListeners() {
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

        mBinding.toolbarSearch.editTextSearch.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == EditorInfo.IME_ACTION_DONE ||
                    keyCode == EditorInfo.IME_ACTION_GO ||
                    keyCode == EditorInfo.IME_ACTION_SEARCH ||
                    event.getAction() == KeyEvent.ACTION_DOWN &&
                            event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                SearchFragmentDirections.ActionSearchFragmentToWholeProductsFragment action =
                        SearchFragmentDirections
                                .actionSearchFragmentToWholeProductsFragment(
                                        "search",
                                        mBinding.toolbarSearch.editTextSearch.getText().toString(),
                                        null);

                Navigation.findNavController(v).navigate(action);

                return true;
            }
            return false;
        });
    }
}