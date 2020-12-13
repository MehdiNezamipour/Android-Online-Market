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


        mViewModel.getConnectionStateLiveData().observe(this, connectionState -> {
            switch (connectionState) {
                case LOADING:
                    showLoadingUi();
                    break;
                case ERROR:
                    loadInternetError();
                    break;
                case START_ACTIVITY:
                    initUi();
                    mBinding.loadingView.getRoot().setVisibility(View.GONE);
                    mBinding.mainView.setVisibility(View.VISIBLE);
                    mWholeProductsAdapter.getProducts().observe(
                            this, products -> mWholeProductsAdapter.notifyDataSetChanged());
                    break;
                default:
                    break;
            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_whole_products, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        initialFetching();
    }

    private void initialFetching() {
        if (mCategory != null) {
            mViewModel.fetchCategoryProducts(mCategory.getId());
        } else if (mOrderBy != null) {
            mViewModel.fetchOrderByProducts(mOrderBy);

        } else if (mToolbarWord != null)
            mViewModel.fetchSearchProducts(mToolbarWord);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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
        else if (mOrderBy != null) {
            mBinding.toolbarSearch.getRoot().setVisibility(View.GONE);
            mBinding.toolbarFilter.getRoot().setVisibility(View.GONE);
            mBinding.toolbarBack.getRoot().setVisibility(View.VISIBLE);
        } else
            mBinding.toolbarSearch.editTextSearch.setText(mToolbarWord);
    }

    public void initUi() {
        mWholeProductsAdapter = new WholeProductsAdapter();
        if (mOrderBy != null) {
            mWholeProductsAdapter.setOrderBy(mOrderBy);
            switch (mOrderBy) {
                case "onSale":
                    mWholeProductsAdapter.setProducts(mViewModel.getOnSaleProducts().getValue());
                    break;
                case "date":
                    mWholeProductsAdapter.setProducts(mViewModel.getLatestProducts().getValue());
                    break;
                case "popularity":
                    mWholeProductsAdapter.setProducts(mViewModel.getPopularProducts().getValue());
                    break;
                case "rating":
                    mWholeProductsAdapter.setProducts(mViewModel.getTopRatingProducts().getValue());
                    break;
                default:
                    break;
            }
        } else if (mCategory != null) {
            mWholeProductsAdapter.setCategoryId(mCategory.getId());
            mWholeProductsAdapter.setProducts(mViewModel.getCategoryProducts().getValue());
        } else if (mToolbarWord != null) {
            mWholeProductsAdapter.setSearch(mToolbarWord);
            mWholeProductsAdapter.setProducts(mViewModel.getProductSearchLiveData().getValue());
        }
        mBinding.recyclerViewWholeProducts.setAdapter(mWholeProductsAdapter);
        initSpinner();
        initToolbarText();
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

    private void showLoadingUi() {
        mBinding.loadingView.buttonRetry.setVisibility(View.GONE);
        mBinding.loadingView.textViewNoInternet.setVisibility(View.GONE);
        mBinding.loadingView.progressBarLoadingFragment.setVisibility(View.VISIBLE);
        mBinding.loadingView.progressBarLoadingFragment.show();
    }


    private void loadInternetError() {
        mBinding.loadingView.buttonRetry.setVisibility(View.VISIBLE);
        mBinding.loadingView.textViewNoInternet.setVisibility(View.VISIBLE);
        mBinding.loadingView.progressBarLoadingFragment.setVisibility(View.GONE);
        mBinding.loadingView.progressBarLoadingFragment.hide();
    }
}