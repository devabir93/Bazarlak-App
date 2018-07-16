package uk.co.ribot.androidboilerplate.ui.category.subcategory.filter;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.data.model.Brand;
import uk.co.ribot.androidboilerplate.data.model.FilterBody;
import uk.co.ribot.androidboilerplate.data.model.FilterSize;
import uk.co.ribot.androidboilerplate.data.model.Product;
import uk.co.ribot.androidboilerplate.ui.base.BaseActivity;
import uk.co.ribot.androidboilerplate.ui.category.subcategory.products.SpinnerAdapter;

public class FilterActivity extends BaseActivity implements FiltersDataMvpView {

    @BindView(R.id.apply_button)
    Button applyButton;
    @Inject
    FilterAdapter filterAdapter;
    @Inject
    FiltersDataPresenter filtersDataPresenter;
    HashMap<String, Object> stringListHashMap;
    String subCategoryId, ExtracategoryId, category, categoryName;
    String brand = "";
    String size = "";
    String color = "";
    String price = "";
    @BindView(R.id.category_filter_layout)
    RelativeLayout categoryFilterLayout;
    @BindView(R.id.brand_filter_spinner)
    MaterialSpinner brandFilterSpinner;
    @BindView(R.id.brand_filter_layout)
    RelativeLayout brandFilterLayout;
    @BindView(R.id.color_filter_spinner)
    MaterialSpinner colorFilterSpinner;
    @BindView(R.id.color_filter_layout)
    RelativeLayout colorFilterLayout;
    @BindView(R.id.size_filter_spinner)
    MaterialSpinner sizeFilterSpinner;
    @BindView(R.id.size_filter_layout)
    RelativeLayout sizeFilterLayout;
    @BindView(R.id.category_filter_text)
    TextView categoryFilterText;
    SpinnerAdapter spinnerAdapter;
    @BindView(R.id.highPrice_radioButton)
    RadioButton highPriceRadioButton;
    @BindView(R.id.lowPrice_radioButton)
    RadioButton lowPriceRadioButton;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    public static int high_price = 1;
    public static int low_price = 2;
    List<Brand> brands;
    List<String> sizeList, colorsArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setContentView(R.layout.layout_filter);
        ButterKnife.bind(this);
        filtersDataPresenter.attachView(this);

        String[] colors = getResources().getStringArray(R.array.colors_list);
        colorsArrayList = Arrays.asList(colors);

        if (getIntent().hasExtra("subCategoryId")) {
            subCategoryId = getIntent().getStringExtra("subCategoryId");
            ExtracategoryId = getIntent().getStringExtra("ExtracategoryId");
            categoryName = getIntent().getStringExtra("categoryName");
            filtersDataPresenter.getFiltersData(subCategoryId, ExtracategoryId);
        }
        if (ExtracategoryId != null && !ExtracategoryId.isEmpty()) {
            category = ExtracategoryId;
        } else
            category = subCategoryId;

        categoryFilterText.setText(categoryName);
        spinnerAdapter = new SpinnerAdapter(this, colorsArrayList);
        colorFilterSpinner.setAdapter(spinnerAdapter);
        colorFilterSpinner.setDropdownHeight(WindowManager.LayoutParams.WRAP_CONTENT);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.highPrice_radioButton:
                        price = String.valueOf(high_price);
                        break;
                    case R.id.lowPrice_radioButton:
                        price = String.valueOf(low_price);
                        break;

                }
            }
        });

        colorFilterSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                // Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
                color = colorsArrayList.get(position);
            }
        });

        sizeFilterSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                // Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
                size = sizeList.get(position);
            }
        });

        brandFilterSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                brand = String.valueOf(brands.get(position).getBrandId());
                // Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
            }
        });
    }

    @OnClick(R.id.close_layout)
    void onCloseClick() {
        this.finish();
    }

    @OnClick(R.id.apply_button)
    void onApplyClick() {
        Timber.d("onApplyClick");
        FilterBody filterBody = new FilterBody();
        filterBody.setCategory(subCategoryId);
        filterBody.setExtracategory(ExtracategoryId);
        filterBody.setBrand(brand);
        filterBody.setSize(size);
        filterBody.setColor(color);
        filterBody.setPrice(price);
        filtersDataPresenter.getFilteredProducts(filterBody);

    }

    @Override
    public void showFilteredProducts(List<Product> productList) {
        Timber.d("productList %s", productList);

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showBrands(List<Brand> brandList) {
        brands = brandList;
        List<String> stringList = new ArrayList<>();
        if (brandList != null)
            for (Brand brand :
                    brandList) {
                stringList.add(brand.getName());
            }
        brandFilterSpinner.setItems(stringList);
//        stringListHashMap.put(getString(R.string.filter_brand), brandList);
//        filterAdapter.setData(FilterActivity.this, stringListHashMap);
//        filterAdapter.notifyDataSetChanged();
    }

    @Override
    public void showColors() {

    }

    @Override
    public void showSizes(List<String> filterSize) {
        sizeList = filterSize;
        if (filterSize != null)
            sizeFilterSpinner.setItems(filterSize);
//        stringListHashMap.put(getString(R.string.filter_size), filterSize);
//        filterAdapter.setData(FilterActivity.this, stringListHashMap);
//        filterAdapter.notifyDataSetChanged();
    }

    @Override
    public void showFilterdProduct(List<Product> data) {

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
