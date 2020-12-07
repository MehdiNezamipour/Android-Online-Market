package com.nezamipour.mehdi.digikala.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.nezamipour.mehdi.digikala.R;
import com.nezamipour.mehdi.digikala.adapter.WholeProductsAdapter;
import com.nezamipour.mehdi.digikala.data.model.product.Category;
import com.nezamipour.mehdi.digikala.databinding.FragmentWholeProductsBinding;
import com.nezamipour.mehdi.digikala.viewmodel.WholeProductFragmentViewModel;

public class WholeProductsFragment extends Fragment {


    private FragmentWholeProductsBinding mBinding;
    private WholeProductFragmentViewModel mViewModel;
    private WholeProductsAdapter mWholeProductsAdapter;
    private String mOrderBy;
    private Category mCategory;
    private String mToolbarWord;


    public WholeProductsFragment() {
        // Required empty public constructor
    }

    public static WholeProductsFragment newInstance() {
        WholeProductsFragment fragment = new WholeProductsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mOrderBy = WholeProductsFragmentArgs.fromBundle(getArguments()).getOrderBy();
            mCategory = WholeProductsFragmentArgs.fromBundle(getArguments()).getCategory();
            mToolbarWord = WholeProductsFragmentArgs.fromBundle(getArguments()).getToolbarWord();
        }

        mViewModel = new ViewModelProvider(this).get(WholeProductFragmentViewModel.class);
        mViewModel.fetchDataFromRepository(mOrderBy);

        mViewModel.getSearchState().observe(this, searchState -> {
            switch (searchState) {
                case ERROR:
                    break;
                case SEARCHING:
                    break;
                case RESULT_BACKED:
                    mViewModel.fetchDataFromRepository(mOrderBy);
                    break;
                default:
                    break;
            }
        });

        initAdapter();


        mViewModel.getProducts().observe(this, products ->
                mWholeProductsAdapter.setProducts(mViewModel.getProducts().getValue()));

        mWholeProductsAdapter.getProducts().observe(
                this, products -> mWholeProductsAdapter.notifyDataSetChanged());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_whole_products, container, false);
        return mBinding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBinding.recyclerViewWholeProducts.setAdapter(mWholeProductsAdapter);
        initSpinner();
        initToolbarText();

        mBinding.toolbarSearch.imageViewBackToHome.setOnClickListener(v -> getActivity().onBackPressed());
        mBinding.toolbarBack.imageViewBack.setOnClickListener(v -> getActivity().onBackPressed());

        mBinding.toolbarFilter.spinnerSort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if (mToolbarWord != null)
                    if (selectedItem.equals(getResources().getString(R.string.latest_sort))) {
                        mViewModel.searchWithSorting(mToolbarWord, "date", "desc");
                    } else if (selectedItem.equals(getResources().getString(R.string.popular_sort))) {
                        mViewModel.searchWithSorting(mToolbarWord, "popularity", "desc");
                    } else if (selectedItem.equals(getResources().getString(R.string.topRating_sort))) {
                        mViewModel.searchWithSorting(mToolbarWord, "rating", "desc");
                    } else if (selectedItem.equals(getResources().getString(R.string.price_descending))) {
                        mViewModel.searchWithSorting(mToolbarWord, "price", "desc");
                    } else {
                        mViewModel.searchWithSorting(mToolbarWord, "price", "asc");
                    }
                else if (mCategory != null) {
                    if (selectedItem.equals(getResources().getString(R.string.latest_sort))) {
                        mViewModel.sortCategoryProducts(mCategory.getId(), "date", "desc");
                    } else if (selectedItem.equals(getResources().getString(R.string.popular_sort))) {
                        mViewModel.sortCategoryProducts(mCategory.getId(), "popularity", "desc");
                    } else if (selectedItem.equals(getResources().getString(R.string.topRating_sort))) {
                        mViewModel.sortCategoryProducts(mCategory.getId(), "rating", "desc");
                    } else if (selectedItem.equals(getResources().getString(R.string.price_descending))) {
                        mViewModel.sortCategoryProducts(mCategory.getId(), "price", "desc");
                    } else {
                        mViewModel.sortCategoryProducts(mCategory.getId(), "price", "asc");
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initToolbarText() {
        mBinding.toolbarSearch.editTextSearch.setEnabled(false);
        if (mCategory != null)
            mBinding.toolbarSearch.editTextSearch.setText(mCategory.getName());
        else if (!mOrderBy.equals("search")) {
            mBinding.toolbarSearch.getRoot().setVisibility(View.GONE);
            mBinding.toolbarFilter.getRoot().setVisibility(View.GONE);
            mBinding.toolbarBack.getRoot().setVisibility(View.VISIBLE);
        } else
            mBinding.toolbarSearch.editTextSearch.setText(mToolbarWord);
    }

    public void initAdapter() {
        mWholeProductsAdapter = new WholeProductsAdapter();
        mWholeProductsAdapter.setOrderBy(mOrderBy);
        if (mCategory != null)
            mWholeProductsAdapter.setCategoryId(mCategory.getId());
        mWholeProductsAdapter.setProducts(mViewModel.getProducts().getValue());
    }

    public void initSpinner() {
        ArrayAdapter<CharSequence> arrayAdapter =
                ArrayAdapter.createFromResource(
                        getContext(),
                        R.array.sort_items_array,
                        android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mBinding.toolbarFilter.spinnerSort.setAdapter(arrayAdapter);
    }
}