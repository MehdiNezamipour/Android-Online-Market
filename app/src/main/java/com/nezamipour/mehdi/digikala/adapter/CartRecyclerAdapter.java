package com.nezamipour.mehdi.digikala.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.nezamipour.mehdi.digikala.R;
import com.nezamipour.mehdi.digikala.data.database.entity.CartProduct;
import com.nezamipour.mehdi.digikala.data.model.product.Product;
import com.nezamipour.mehdi.digikala.data.repository.CartRepository;
import com.nezamipour.mehdi.digikala.databinding.RowItemCartBinding;
import com.nezamipour.mehdi.digikala.util.ImageUtil;
import com.nezamipour.mehdi.digikala.view.fragment.CartFragmentDirections;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CartRecyclerAdapter extends RecyclerView.Adapter<CartRecyclerAdapter.CartRecyclerViewHolder> {

    private List<Product> mProducts;
    private final CartRepository mCartRepository;

    public CartRecyclerAdapter(Context context) {
        mCartRepository = CartRepository.getInstance(context);
        mCartRepository.calculateTotalPrice();
    }


    public void setProducts(List<Product> products) {
        mProducts = products;
    }

    @NonNull
    @Override
    public CartRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowItemCartBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext())
                        , R.layout.row_item_cart
                        , parent
                        , false);
        return new CartRecyclerViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartRecyclerViewHolder holder, int position) {
        holder.bindProduct(mProducts.get(position));
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public class CartRecyclerViewHolder extends RecyclerView.ViewHolder {

        private final RowItemCartBinding mBinding;

        public CartRecyclerViewHolder(RowItemCartBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bindProduct(Product product) {
            mBinding.textViewCartItemTitle.setText(product.getName());
            mBinding.textViewCartItemPrice.setText(product.getPrice());
            mBinding.textViewCartItemCount
                    .setText(String.valueOf(mCartRepository.get(product.getId()).getCount()));
            CartProduct cartProduct = mCartRepository.get(product.getId());


            mBinding.getRoot().setOnClickListener(v -> {
                CartFragmentDirections.ActionCartFragmentToProductDetailLoadingFragment action =
                        CartFragmentDirections
                                .actionCartFragmentToProductDetailLoadingFragment(product.getId());
                Navigation.findNavController(v).navigate(action);
            });

            mBinding.buttonCartItemDelete.setOnClickListener(v -> {
                mCartRepository.delete(cartProduct);
                mProducts.remove(product);
                notifyDataSetChanged();
                mCartRepository.calculateTotalPrice();
            });


            mBinding.buttonCartItemIncrease.setOnClickListener(v -> {
                mCartRepository.increaseCountOfCart(product);
                mBinding.textViewCartItemCount
                        .setText(String.valueOf(mCartRepository.get(product.getId()).getCount()));
            });

            mBinding.buttonCartItemDecrease.setOnClickListener(v -> {
                mCartRepository.decreaseCountOfCart(product);
                mBinding.textViewCartItemCount
                        .setText(String.valueOf(mCartRepository.get(product.getId()).getCount()));
            });


            Picasso.get()
                    .load(ImageUtil.getFirstImageUrlOfProduct(product))
                    .placeholder(R.drawable.place_holder)
                    .into(mBinding.imageViewCartItemImage);

        }
    }
}
