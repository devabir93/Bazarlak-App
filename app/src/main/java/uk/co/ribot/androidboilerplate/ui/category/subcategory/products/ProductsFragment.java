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

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.data.model.Category;
import uk.co.ribot.androidboilerplate.data.model.Product;
import uk.co.ribot.androidboilerplate.ui.base.BaseActivity;
import uk.co.ribot.androidboilerplate.ui.base.BaseFragment;
import uk.co.ribot.androidboilerplate.ui.category.CategoryMvpView;
import uk.co.ribot.androidboilerplate.ui.category.CategoryPresenter;
import uk.co.ribot.androidboilerplate.ui.category.subcategory.GridViewRecyclerViewAdapter;
import uk.co.ribot.androidboilerplate.ui.category.subcategory.SubCategoryFragment;
import uk.co.ribot.androidboilerplate.ui.category.subcategory.SubCategoryMenuAdapter;
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
    String extraSubCategoryId;
    String categoryName, extraSubCategoryName;
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

    public void setExtraSubCategoryId(String extraSubCategoryId) {
        this.extraSubCategoryId = extraSubCategoryId;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setExtraSubCategoryName(String extraSubCategoryName) {
        this.extraSubCategoryName = extraSubCategoryName;
    }

    @NonNull
    public static ProductsFragment newInstance(String categoryId, String categoryName, String subCategoryId, String extraSubCategoryName, String extraSubCategoryId) {
        ProductsFragment fragment = new ProductsFragment();
        fragment.setCategoryId(categoryId);
        fragment.setSubCategoryId(subCategoryId);
        fragment.setExtraSubCategoryId(extraSubCategoryId);
        fragment.setCategoryName(categoryName);
        fragment.setExtraSubCategoryName(extraSubCategoryName);
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
        textView.setText(categoryName + "/" + extraSubCategoryName);
        secondToolbar.setNavigationIcon(R.drawable.ic_back);
        secondToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = getActivity().getSupportFragmentManager().findFragmentByTag(ProductsFragment.class.getName());
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.remove(fragment).commit();
            }
        });
        productsPresenter.getProducts(getContext(), categoryId, subCategoryId, extraSubCategoryId);
        productsRecyclerView.setAdapter(gridViewRecyclerViewAdapter);
        productsRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        productsRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), productsRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // do whatever
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        productsPresenter.detachView();
    }

    private List<Category> getCategories() {
        List<Category> categories = new ArrayList<>();
//        categories.add(new Category(R.drawable.hejabipic,"Hejab"));
//        categories.add(new Category(R.drawable.headbandspic,"Headbands"));
//        categories.add(new Category(R.drawable.hejabipic,"Hejab"));
//        categories.add(new Category(R.drawable.headbandspic,"Headbands"));
        return categories;
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
}
