package com.nezamipour.mehdi.digikala.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.nezamipour.mehdi.digikala.R;
import com.nezamipour.mehdi.digikala.data.model.product.Category;
import com.nezamipour.mehdi.digikala.data.model.product.Product;
import com.nezamipour.mehdi.digikala.databinding.RowItemWholeProductsBinding;
import com.nezamipour.mehdi.digikala.util.ImageUtil;
import com.nezamipour.mehdi.digikala.view.fragment.WholeProductsFragmentDirections;
import com.squareup.picasso.Picasso;

import java.util.List;

public class WholeProductsAdapter extends RecyclerView.Adapter<WholeProductsAdapter.WholeProductsViewHolder> {


    private List<Product> mProducts;
    private String mOrderBy;
    private Category mCategory;


    public void setProducts(List<Product> products) {
        mProducts = products;
    }

    public void setOrderBy(String orderBy) {
        mOrderBy = orderBy;
    }

    public void setCategory(Category category) {
        mCategory = category;
    }

    public WholeProductsAdapter() {
    }

    public WholeProductsAdapter(String orderBy) {
        mOrderBy = orderBy;
    }

    public WholeProductsAdapter(Category category) {
        mCategory = category;
    }


    @NonNull
    @Override
    public WholeProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowItemWholeProductsBinding binding = DataBindingUtil
                .inflate(
                        LayoutInflater.from(parent.getContext()),
                        R.layout.row_item_whole_products,
                        parent,
                        false);
        return new WholeProductsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull WholeProductsViewHolder holder, int position) {
        holder.bindProduct(mProducts.get(position));
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public static class WholeProductsViewHolder extends RecyclerView.ViewHolder {

        private final RowItemWholeProductsBinding mBinding;
        private Product mProduct;

        public WholeProductsViewHolder(@NonNull RowItemWholeProductsBinding binding) {
            super(binding.getRoot());
            mBinding = binding;

        }

        public void bindProduct(Product product) {
            mProduct = product;
            mBinding.rowItemWholeProductsTitle.setText(mProduct.getName());
            mBinding.rowItemWholeProductsSalesPrice.setText(mProduct.getPrice());
            mBinding.rowItemWholeProductsRegularPrice.setText(mProduct.getRegularPrice());

            //mBinding.rowItemWholeProductsDescription.setText(mProduct.getDescription());
            Picasso.get()
                    .load(ImageUtil.getFirstImageUrlOfProduct(mProduct))
                    .placeholder(R.drawable.logo)
                    .into(mBinding.rowItemWholeProductsImage);

            mBinding.cardViewRowItemWholeProducts.setOnClickListener(v -> {
                WholeProductsFragmentDirections.ActionWholeProductsFragmentToLoadingFragment action =
                        WholeProductsFragmentDirections.actionWholeProductsFragmentToLoadingFragment(mProduct.getId());
                Navigation.findNavController(v).navigate(action);

            });
        }
    }
}
