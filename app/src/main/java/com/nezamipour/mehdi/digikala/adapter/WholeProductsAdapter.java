package com.nezamipour.mehdi.digikala.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.nezamipour.mehdi.digikala.R;
import com.nezamipour.mehdi.digikala.data.model.product.Category;
import com.nezamipour.mehdi.digikala.data.model.product.Product;
import com.nezamipour.mehdi.digikala.databinding.RowItemWholeProductsBinding;
import com.nezamipour.mehdi.digikala.util.ImageUtil;
import com.squareup.picasso.Picasso;

import java.util.List;

public class WholeProductsAdapter extends RecyclerView.Adapter<WholeProductsAdapter.WholeProductsViewHolder> {


    private List<Product> mProducts;
    private String mOrderBy;
    private Category mCategory;
    private Context mContext;


    public void setProducts(List<Product> products) {
        mProducts = products;
    }

    public void setOrderBy(String orderBy) {
        mOrderBy = orderBy;
    }

    public void setCategory(Category category) {
        mCategory = category;
    }

    public WholeProductsAdapter(Context context) {
        mContext = context;
    }

    public WholeProductsAdapter(Context context, String orderBy) {
        mOrderBy = orderBy;
        mContext = context;
    }

    public WholeProductsAdapter(Context context, Category category) {
        mCategory = category;
        mContext = context;
    }


    @NonNull
    @Override
    public WholeProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowItemWholeProductsBinding binding = DataBindingUtil
                .inflate(
                        LayoutInflater.from(mContext),
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

    public class WholeProductsViewHolder extends RecyclerView.ViewHolder {

        private RowItemWholeProductsBinding mBinding;
        private Product mProduct;

        public WholeProductsViewHolder(@NonNull RowItemWholeProductsBinding binding) {
            super(binding.getRoot());
            mBinding = binding;

            mBinding.cardViewRowItemWholeProducts.setOnClickListener(v -> {
                //TODO : startActivity to product detail fragment
            });
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
        }
    }
}
