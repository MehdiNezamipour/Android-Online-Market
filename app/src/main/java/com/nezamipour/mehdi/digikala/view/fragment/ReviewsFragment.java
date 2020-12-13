package com.nezamipour.mehdi.digikala.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.nezamipour.mehdi.digikala.R;
import com.nezamipour.mehdi.digikala.adapter.ReviewsRecyclerAdapter;
import com.nezamipour.mehdi.digikala.databinding.FragmentReviewsBinding;
import com.nezamipour.mehdi.digikala.viewmodel.ReviewViewModel;

public class ReviewsFragment extends Fragment {


    private ReviewViewModel mViewModel;
    private FragmentReviewsBinding mBinding;
    private ReviewsRecyclerAdapter mReviewsRecyclerAdapter;
    private Integer mProductId;

    public ReviewsFragment() {
        // Required empty public constructor
    }

    public static ReviewsFragment newInstance() {
        return new ReviewsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProductId = ReviewsFragmentArgs.fromBundle(getArguments()).getProductId();

        mViewModel = new ViewModelProvider(this).get(ReviewViewModel.class);


        //TODO why not work ???
  /*      mViewModel.getConnectionStateLiveData().observe(this, connectionState -> {
            switch (connectionState) {
                case START_ACTIVITY:
                    initUi();
                    mBinding.loadingView.getRoot().setVisibility(View.GONE);
                    mBinding.mainView.setVisibility(View.VISIBLE);
                    break;
                case LOADING:
                    showLoadingUi();
                    break;
                case ERROR:
                    showErrorUi();
                    break;
                default:
                    break;
            }
        });*/

        //except above code
        mViewModel.getReviewsLiveData().observe(this, reviews -> {
            initUi();
            mBinding.loadingView.getRoot().setVisibility(View.GONE);
            mBinding.mainView.setVisibility(View.VISIBLE);
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mViewModel.fetchReviewsOfProduct(mProductId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_reviews, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBinding.toolbarBack.imageViewBack.setOnClickListener(v -> getActivity().onBackPressed());
        mBinding.fab.setOnClickListener(v -> {
            if (mViewModel.getCurrentLoginCustomer() != null)
                Navigation.findNavController(v)
                        .navigate(ReviewsFragmentDirections
                                .actionReviewsFragmentToWriteReviewFragment(mProductId));
            else
                Toast.makeText(getContext(),
                        getResources().getString(R.string.must_login)
                        , Toast.LENGTH_SHORT).show();

        });
    }

    private void initUi() {
        mReviewsRecyclerAdapter = new ReviewsRecyclerAdapter();
        mReviewsRecyclerAdapter.setReviews(mViewModel.getReviewsLiveData().getValue());
        mBinding.recyclerViewReviews.setAdapter(mReviewsRecyclerAdapter);
    }

    private void showLoadingUi() {
        mBinding.loadingView.buttonRetry.setVisibility(View.GONE);
        mBinding.loadingView.textViewNoInternet.setVisibility(View.GONE);
        mBinding.loadingView.progressBarLoadingFragment.setVisibility(View.VISIBLE);
        mBinding.loadingView.progressBarLoadingFragment.show();
    }


    private void showErrorUi() {
        mBinding.loadingView.buttonRetry.setVisibility(View.VISIBLE);
        mBinding.loadingView.textViewNoInternet.setVisibility(View.VISIBLE);
        mBinding.loadingView.progressBarLoadingFragment.setVisibility(View.GONE);
        mBinding.loadingView.progressBarLoadingFragment.hide();
    }
}