package com.nezamipour.mehdi.digikala.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.nezamipour.mehdi.digikala.R;
import com.nezamipour.mehdi.digikala.data.model.product.Category;
import com.nezamipour.mehdi.digikala.databinding.RowItemCategoryBinding;
import com.nezamipour.mehdi.digikala.view.fragment.CategoryFragmentDirections;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryRecyclerAdapter extends RecyclerView.Adapter<CategoryRecyclerAdapter.CategoryViewHolder> {

    private List<Category> mCategories;


    public void setCategories(List<Category> categories) {
        mCategories = categories;
    }

    public CategoryRecyclerAdapter() {
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowItemCategoryBinding binding =
                DataBindingUtil.inflate(
                        LayoutInflater.from(parent.getContext()),
                        R.layout.row_item_category,
                        parent,
                        false);

        return new CategoryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.bindCategory(mCategories.get(position));
    }

    @Override
    public int getItemCount() {
        return mCategories.size();
    }


    public static class CategoryViewHolder extends RecyclerView.ViewHolder {

        private final RowItemCategoryBinding mBinding;
        private Category mCategory;

        public CategoryViewHolder(RowItemCategoryBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bindCategory(Category category) {
            mCategory = category;
            mBinding.textViewCategoryTitle.setText(mCategory.getName());

            Picasso.get()
                    .load(mCategory.getImage().getSrc())
                    .placeholder(R.drawable.place_holder)
                    .into(mBinding.imageViewProductCover);

            mBinding.rowLayoutCardView.setOnClickListener(v -> {
                CategoryFragmentDirections
                        .ActionNavFragCategoryToWholeProductsFragment action =
                        CategoryFragmentDirections
                                .actionNavFragCategoryToWholeProductsFragment(null, null, category);
                Navigation.findNavController(v).navigate(action);
            });
        }
    }


}
