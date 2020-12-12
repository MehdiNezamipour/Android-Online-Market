package com.nezamipour.mehdi.digikala.viewmodel;

import android.app.Application;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.nezamipour.mehdi.digikala.R;
import com.nezamipour.mehdi.digikala.data.database.entity.CartProduct;
import com.nezamipour.mehdi.digikala.data.model.product.Product;
import com.nezamipour.mehdi.digikala.data.repository.CartRepository;
import com.nezamipour.mehdi.digikala.data.repository.ProductRepository;
import com.nezamipour.mehdi.digikala.util.enums.ConnectionState;

public class ProductDetailViewModel extends AndroidViewModel {

    private final ProductRepository mProductRepository;
    private final CartRepository mCartRepository;
    private LiveData<Product> mProduct;

    public ProductDetailViewModel(@NonNull Application application) {
        super(application);
        mProduct = new MutableLiveData<>();
        mProductRepository = ProductRepository.getInstance();
        mCartRepository = CartRepository.getInstance(getApplication());
    }


    public void fetchProductById(Integer productId) {
        mProductRepository.fetchProductById(productId);
    }

    public LiveData<Product> getProductMutableLiveData() {
        mProduct = mProductRepository.getProductByIdMutableLiveData();
        return mProductRepository.getProductByIdMutableLiveData();
    }

    public LiveData<ConnectionState> getConnectionStateLiveData() {
        return mProductRepository.getConnectionStateLiveData();
    }


    public void onClick(View v) {
        CartProduct cartProduct = new CartProduct(mProduct.getValue().getId(), "", 1);
        mCartRepository.insert(cartProduct);
        Toast.makeText(getApplication(), R.string.added_to_cart, Toast.LENGTH_SHORT).show();
    }

    public Spanned getShortDescription() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            return Html.fromHtml(mProduct.getValue().getShortDescription(), Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(mProduct.getValue().getShortDescription());
        }
    }


}
