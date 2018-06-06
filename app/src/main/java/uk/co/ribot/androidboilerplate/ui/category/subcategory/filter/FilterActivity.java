package uk.co.ribot.androidboilerplate.ui.category.subcategory.filter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.data.model.Brand;
import uk.co.ribot.androidboilerplate.data.model.Category;
import uk.co.ribot.androidboilerplate.data.model.FilterSize;
import uk.co.ribot.androidboilerplate.data.model.Product;
import uk.co.ribot.androidboilerplate.ui.base.BaseActivity;

public class FilterActivity extends BaseActivity implements FiltersDataMvpView{

    @BindView(R.id.refine_recyclerView)
    RecyclerView refineRecyclerView;
    @BindView(R.id.apply_button)
    Button applyButton;
    @Inject FilterAdapter filterAdapter;
    @Inject FiltersDataPresenter filtersDataPresenter;
    HashMap<String,Object> stringListHashMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setContentView(R.layout.layout_filter);
        ButterKnife.bind(this);
        filtersDataPresenter.attachView(this);
        filtersDataPresenter.getFiltersData("","");
        stringListHashMap = new HashMap<>();
        refineRecyclerView.setAdapter(filterAdapter);
        refineRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        filterAdapter.setData(FilterActivity.this,stringListHashMap);
        filterAdapter.notifyDataSetChanged();
    }

    @Override
    public void showFilteredProducts(List<Product> productList) {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showBrands(List<Brand> brandList) {
        stringListHashMap.put(getString(R.string.filter_brand),brandList);
        filterAdapter.notifyDataSetChanged();
    }

    @Override
    public void showColors() {

    }

    @Override
    public void showSizes(List<FilterSize> filterSize) {
        stringListHashMap.put(getString(R.string.filter_size),filterSize);
        filterAdapter.notifyDataSetChanged();
    }

    @Override
    public void hasActiveInternetConnection(boolean b) {

    }

    @Override
    public void onTimeout() {

    }

    @Override
    public void onNetworkError() {

    }

    @Override
    public void onUnknownError(String message) {

    }
}
