package uk.co.ribot.androidboilerplate.ui.category.subcategory.products;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.data.model.ColorFeature;
import uk.co.ribot.androidboilerplate.data.model.Product;
import uk.co.ribot.androidboilerplate.data.model.Size;
import uk.co.ribot.androidboilerplate.ui.bag.BagFragment;
import uk.co.ribot.androidboilerplate.ui.base.BaseActivity;
import uk.co.ribot.androidboilerplate.ui.base.BaseFragment;

public class ProductsDetailsFragment extends BaseFragment implements ProductsMvpView {
    @Inject
    ProductsPresenter productsPresenter;
    Product mProduct;
    @BindView(R.id.ic_close)
    ImageView icClose;
    @BindView(R.id.item_type_textView)
    TextView itemTypeTextView;
    @BindView(R.id.price_textView)
    TextView priceTextView;
    @BindView(R.id.size_spinner)
    MaterialSpinner sizeSpinner;
    @BindView(R.id.add_to_bag_layout)
    LinearLayout addToBagLayout;
    @BindView(R.id.color_spinner)
    MaterialSpinner colorSpinner;
    @BindView(R.id.size_guide_button)
    Button sizeGuideButton;
    @BindView(R.id.shipping_worldwide_button)
    Button shippingWorldwideButton;
    @BindView(R.id.brand_name)
    TextView brandName;
    @BindView(R.id.product_imageView)
    ImageView productImageView;


    public ProductsDetailsFragment() {
        // Required empty public constructor
    }

    public void setProduct(Product product) {
        Timber.d("setProduct %s", product);
        this.mProduct = product;
    }

    @NonNull
    public static ProductsDetailsFragment newInstance(Product product) {
        ProductsDetailsFragment fragment = new ProductsDetailsFragment();
        fragment.setProduct(product);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BaseActivity) getActivity()).activityComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.product_details, container, false);
        ButterKnife.bind(this, view);
        productsPresenter.attachView(this);
        Timber.d("mProduct %s", mProduct);
        if (mProduct != null) {

            brandName.setText(mProduct.getName());
            itemTypeTextView.setText(mProduct.getDescription());
            if (mProduct.getImage() != null && !mProduct.getImage().isEmpty())
                Picasso.with(getContext()).load(mProduct.getImage()).into(productImageView);
            priceTextView.setText(mProduct.getPrice());
            List<String> colorsList = new ArrayList<>();
            List<String> sizesList = new ArrayList<>();
            HashMap<String, List<Size>> hashMap = new HashMap<>();
//                for (ColorFeature colorFeature :
//                        mProduct.getColorFeatures()) {
//                    hashMap.put(colorFeature.getColor(), colorFeature.getSizes());
//                    // colorsList.add(colorFeature.getColor());
//                }
//                colorSpinner.setItems(hashMap.keySet());
        }

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        productsPresenter.detachView();
    }


    @Override
    public void showProducts(List<Product> productList) {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void addedToBag(boolean b) {
        if (b) showAlertDialog();
    }

    @OnClick({R.id.ic_close, R.id.add_to_bag_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ic_close:
                closeFragment();
                break;
            case R.id.add_to_bag_layout:
                //showAlertDialog();
                productsPresenter.addToBag(String.valueOf(mProduct.getProductId()));
                break;
        }
    }


    void closeFragment() {
        Fragment fragment = getActivity().getSupportFragmentManager().findFragmentByTag(ProductsDetailsFragment.class.getName());
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.remove(fragment).commit();
    }

    public void showAlertDialog() {
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(getActivity());
        // (That new View is just there to have something inside the dialog that can grow big enough to cover the whole screen.)
        LayoutInflater factory = LayoutInflater.from(getActivity());
        View content = factory.inflate(R.layout.bag_dialog_layout, null);
        Button contShoppingBt = (Button) content.findViewById(R.id.cont_shopping_button);
        Button viewBagBt = (Button) content.findViewById(R.id.view_bag_button);
        builderSingle.setView(content);
        final AlertDialog alertDialogObject = builderSingle.create();
        // Here you can change the layout direction via setLayoutDirection()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            alertDialogObject.getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LOCALE);
        }

        contShoppingBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogObject.dismiss();
            }
        });
        viewBagBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment nextFrag = new BagFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.product_details_fragment, nextFrag, BagFragment.class.getName())
                        .commit();
                alertDialogObject.dismiss();

            }
        });
        alertDialogObject.show();
    }

}
