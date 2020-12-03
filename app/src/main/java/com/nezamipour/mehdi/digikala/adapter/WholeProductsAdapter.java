package com.nezamipour.mehdi.digikala.adapter;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.nezamipour.mehdi.digikala.R;
import com.nezamipour.mehdi.digikala.data.model.product.Product;
import com.nezamipour.mehdi.digikala.databinding.RowItemWholeProductsBinding;
import com.nezamipour.mehdi.digikala.network.RetrofitInstance;
import com.nezamipour.mehdi.digikala.network.WooApi;
import com.nezamipour.mehdi.digikala.util.ImageUtil;
import com.nezamipour.mehdi.digikala.view.fragment.WholeProductsFragmentDirections;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WholeProductsAdapter extends RecyclerView.Adapter<WholeProductsAdapter.WholeProductsViewHolder> {


    private final WooApi mWooApi;
    private final MutableLiveData<List<Product>> mProducts = new MutableLiveData<>();
    private String mOrderBy;
    private Integer mCategoryId;
    private int mPage = 2;


    public void setProducts(List<Product> products) {
        mProducts.setValue(products);
    }

    public LiveData<List<Product>> getProducts() {
        return mProducts;
    }

    public void setOrderBy(String orderBy) {
        mOrderBy = orderBy;
    }

    public void setCategoryId(Integer categoryId) {
        mCategoryId = categoryId;
    }

    public WholeProductsAdapter() {
        mWooApi = RetrofitInstance.getInstance().create(WooApi.class);
    }

    private void addToList(int position) {
        if (position > getItemCount() - 2) {
            switch (mOrderBy) {
                case "onSale":
                    addToOnSaleProducts();
                    break;
                case "date":
                    addToLatestProducts();
                    break;
                case "popularity":
                    addToPopularProducts();
                    break;
                case "rating":
                    addToTopRatingProducts();
                    break;
                case "category":
                    addToCategoryProducts(mCategoryId);
                    break;
                default:
                    break;
            }
        }
    }

    private void addToOnSaleProducts() {
        mWooApi.getOnSaleProducts(10, mPage).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    mPage++;
                    List<Product> products = mProducts.getValue();
                    products.addAll(response.body());
                    mProducts.setValue(products);
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }

    private void addToLatestProducts() {
        mWooApi.getProducts(10, mPage, "date").enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    mPage++;
                    List<Product> products = mProducts.getValue();
                    products.addAll(response.body());
                    mProducts.setValue(products);
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }

    private void addToTopRatingProducts() {
        mWooApi.getProducts(10, mPage, "rating").enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    mPage++;
                    List<Product> products = mProducts.getValue();
                    products.addAll(response.body());
                    mProducts.setValue(products);
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }

    private void addToPopularProducts() {
        mWooApi.getProducts(10, mPage, "popularity").enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    mPage++;
                    List<Product> products = mProducts.getValue();
                    products.addAll(response.body());
                    mProducts.setValue(products);
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }

    private void addToCategoryProducts(int categoryId) {
        mWooApi.getCategoryProducts(categoryId, 10, mPage).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    mPage++;
                    List<Product> products = mProducts.getValue();
                    products.addAll(response.body());
                    mProducts.setValue(products);
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
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
        holder.bindProduct(mProducts.getValue().get(position));
        addToList(position);
    }

    @Override
    public int getItemCount() {
        return mProducts.getValue().size();
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


            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                mBinding.rowItemWholeProductsDescription.setText(Html.fromHtml(mProduct.getShortDescription(), Html.FROM_HTML_MODE_LEGACY));
            } else {
                mBinding.rowItemWholeProductsDescription.setText(Html.fromHtml(mProduct.getShortDescription()));
            }

            Picasso.get()
                    .load(ImageUtil.getFirstImageUrlOfProduct(mProduct))
                    .placeholder(R.drawable.logo)
                    .into(mBinding.rowItemWholeProductsImage);

            mBinding.cardViewRowItemWholeProducts.setOnClickListener(v -> {
                WholeProductsFragmentDirections.ActionWholeProductsFragmentToProductDetailLoadingFragment action =
                        WholeProductsFragmentDirections
                                .actionWholeProductsFragmentToProductDetailLoadingFragment(mProduct.getId());
                Navigation.findNavController(v).navigate(action);

            });
        }
    }
}
