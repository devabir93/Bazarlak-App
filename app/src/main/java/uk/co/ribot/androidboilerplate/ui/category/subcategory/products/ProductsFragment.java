package uk.co.ribot.androidboilerplate.ui.category.subcategory.products;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.data.model.Product;
import uk.co.ribot.androidboilerplate.data.model.ProductBody;
import uk.co.ribot.androidboilerplate.ui.base.BaseActivity;
import uk.co.ribot.androidboilerplate.ui.base.BaseFragment;
import uk.co.ribot.androidboilerplate.util.RecyclerItemClickListener;

public class ProductsFragment extends BaseFragment implements ProductsMvpView {
    @Inject
    ProductsPresenter productsPresenter;
    @Inject
    ProductsGridViewRecyclerViewAdapter gridViewRecyclerViewAdapter;
    @BindView(R.id.products_recyclerView)
    RecyclerView productsRecyclerView;

    @Nullable
    @BindView(R.id.second_toolbar)
    Toolbar secondToolbar;
    Unbinder unbinder;
    String categoryId;
    String subCategoryId;
    String ExtracategoryId;
    String categoryName, ExtracategoryName;
    private List<Product> mproductList;

    public ProductsFragment() {
        // Required empty public constructor
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public void setSubCategoryId(String subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public void setExtracategoryId(String ExtracategoryId) {
        this.ExtracategoryId = ExtracategoryId;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setExtracategoryName(String ExtracategoryName) {
        this.ExtracategoryName = ExtracategoryName;
    }

    @NonNull
    public static ProductsFragment newInstance(String categoryId, String categoryName, String subCategoryId, String ExtracategoryName, String ExtracategoryId) {
        ProductsFragment fragment = new ProductsFragment();
        fragment.setCategoryId(categoryId);
        fragment.setSubCategoryId(subCategoryId);
        fragment.setExtracategoryId(ExtracategoryId);
        fragment.setCategoryName(categoryName);
        fragment.setExtracategoryName(ExtracategoryName);
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
        final View view = inflater.inflate(R.layout.prodcuts_fragment, container, false);
        ButterKnife.bind(this, view);
        productsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        productsPresenter.attachView(this);
        ((AppCompatActivity) getActivity()).setSupportActionBar(secondToolbar);
        TextView textView = (TextView) secondToolbar.findViewById(R.id.activity_name_textView);
        textView.setText(categoryName + "/" + ExtracategoryName);
        secondToolbar.setNavigationIcon(R.drawable.ic_back);
        secondToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = getActivity().getSupportFragmentManager().findFragmentByTag(ProductsFragment.class.getName());
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.remove(fragment).commit();
            }
        });
        ProductBody productBody = new ProductBody();
        productBody.setCategory(categoryId);
        productBody.setSubcategory(subCategoryId);
        productBody.setExtracategory(ExtracategoryId);
        productsPresenter.getProducts(getContext(), categoryId,subCategoryId,ExtracategoryId,"");
        productsRecyclerView.setAdapter(gridViewRecyclerViewAdapter);
        productsRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        productsRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), productsRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // do whatever
                        showProductDetails(mproductList.get(position));
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
        return view;
    }

    private void showProductDetails(Product product) {
        Timber.d("product %s", product);
        Fragment nextFrag = ProductsDetailsFragment.newInstance(product);

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_fragment, nextFrag, ProductsDetailsFragment.class.getName())
                .commit();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        productsPresenter.detachView();
    }

    @Override
    public void showProducts(List<Product> productList) {
        mproductList = productList;
        gridViewRecyclerViewAdapter.setProductData(getContext(), productList);
        gridViewRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void addedToBag(boolean b) {

    }
}
