package com.nezamipour.mehdi.digikala.adapter;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.nezamipour.mehdi.digikala.R;
import com.nezamipour.mehdi.digikala.data.model.product.Review;
import com.nezamipour.mehdi.digikala.databinding.RowItemReviewBinding;

import java.util.List;

public class ReviewsRecyclerAdapter extends RecyclerView.Adapter<ReviewsRecyclerAdapter.ReviewsViewHolder> {


    private List<Review> mReviews;

    public void setReviews(List<Review> reviews) {
        mReviews = reviews;
    }

    @NonNull
    @Override
    public ReviewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RowItemReviewBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                R.layout.row_item_review, viewGroup, false);
        return new ReviewsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewsViewHolder reviewsViewHolder, int i) {
        reviewsViewHolder.onBindReviews(mReviews.get(i));
    }

    @Override
    public int getItemCount() {
        return mReviews.size();
    }

    public class ReviewsViewHolder extends RecyclerView.ViewHolder {

        private RowItemReviewBinding mBinding;

        public ReviewsViewHolder(RowItemReviewBinding binding) {
            super(binding.getRoot());
            mBinding = binding;

        }

        public void onBindReviews(Review review) {
            mBinding.textViewReviewer.setText(review.getReviewer());

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                mBinding.textViewReview.setText(Html.fromHtml(review.getReview(), Html.FROM_HTML_MODE_LEGACY));
            } else {
                mBinding.textViewReview.setText(Html.fromHtml(review.getReview()));
            }
        }
    }

}
