package com.nezamipour.mehdi.digikala.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.nezamipour.mehdi.digikala.R;
import com.nezamipour.mehdi.digikala.data.model.product.Product;
import com.nezamipour.mehdi.digikala.databinding.RowItemProductBinding;
import com.nezamipour.mehdi.digikala.util.ImageUtil;
import com.nezamipour.mehdi.digikala.view.fragment.HomeFragmentDirections;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductRecyclerAdapter extends RecyclerView.Adapter<ProductRecyclerAdapter.ProductRecyclerViewHolder> {


    private List<Product> mProducts;

    public ProductRecyclerAdapter() {
        mProducts = new ArrayList<>();
    }

    public void setProducts(List<Product> products) {
        mProducts = products;
    }

    @NonNull
    @Override
    public ProductRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RowItemProductBinding binding =
                DataBindingUtil.inflate(
                        inflater,
                        R.layout.row_item_product,
                        parent,
                        false);
        return new ProductRecyclerViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductRecyclerViewHolder holder, int position) {
        holder.bindProduct(mProducts.get(position));

    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public static class ProductRecyclerViewHolder extends RecyclerView.ViewHolder {

        private final RowItemProductBinding mBinding;
        private Product mProduct;


        public ProductRecyclerViewHolder(@NonNull RowItemProductBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        public void bindProduct(Product product) {
            mProduct = product;
            mBinding.rowLayoutCardView.setOnClickListener(v -> {
                HomeFragmentDirections.ActionNavFragHomeToProductDetailFragment action = HomeFragmentDirections
                        .actionNavFragHomeToProductDetailFragment(mProduct);
                Navigation.findNavController(v).navigate(action);
            });

            mBinding.textViewProductTitle.setText(mProduct.getName());
            mBinding.textViewProductPrice.setText(mProduct.getPrice());

            Picasso.get()
                    .load(ImageUtil.getFirstImageUrlOfProduct(mProduct))
                    .placeholder(R.drawable.place_holder)
                    .into(mBinding.imageViewProductCover);

        }

    }
}
