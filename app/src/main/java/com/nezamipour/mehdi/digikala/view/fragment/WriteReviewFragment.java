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

import com.nezamipour.mehdi.digikala.R;
import com.nezamipour.mehdi.digikala.data.model.product.Review;
import com.nezamipour.mehdi.digikala.databinding.FragmentWriteReviewBinding;
import com.nezamipour.mehdi.digikala.viewmodel.ReviewViewModel;

public class WriteReviewFragment extends Fragment {

    private ReviewViewModel mViewModel;
    private FragmentWriteReviewBinding mBinding;
    private Integer mProductId;

    public WriteReviewFragment() {
        // Required empty public constructor
    }

    public static WriteReviewFragment newInstance() {
        return new WriteReviewFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mProductId = WriteReviewFragmentArgs.fromBundle(getArguments()).getProductId();

        mViewModel = new ViewModelProvider(this).get(ReviewViewModel.class);

        mViewModel.fetchCustomer(mViewModel.getCurrentLoginCustomer().getEmail());

        mViewModel.getReviewLiveData().observe(this, review -> {
            Toast.makeText(getContext(),
                    getResources().getString(R.string.your_comment_accepted),
                    Toast.LENGTH_LONG)
                    .show();

            getActivity().onBackPressed();
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_write_review, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding.toolbarBack.imageViewBack.setOnClickListener(v -> getActivity().onBackPressed());
        mBinding.addComment.setOnClickListener(v -> {
            if (mBinding.addComment.getText().toString().trim().length() < 9)

                Toast.makeText(getContext(),
                        getResources().getString(R.string.minimum_characters_of_comment),
                        Toast.LENGTH_SHORT)
                        .show();

            else if (mViewModel.getCustomerLiveData().getValue() != null) {
                Review review = new Review(
                        mProductId,
                        mViewModel.getCustomerLiveData().getValue().getFullName(),
                        mViewModel.getCustomerLiveData().getValue().getEmail(),
                        mBinding.editTextComment.getText().toString());

                mViewModel.postReview(review);
            }
        });
    }
}