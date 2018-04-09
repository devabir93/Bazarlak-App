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
import uk.co.ribot.androidboilerplate.ui.base.BaseActivity;

public class FilterActivity extends BaseActivity {

    @BindView(R.id.refine_recyclerView)
    RecyclerView refineRecyclerView;
    @BindView(R.id.apply_button)
    Button applyButton;
    @Inject FilterAdapter filterAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setContentView(R.layout.layout_filter);
        ButterKnife.bind(this);
        refineRecyclerView.setAdapter(filterAdapter);
        refineRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        filterAdapter.setData(FilterActivity.this,getFilterData());
        filterAdapter.notifyDataSetChanged();
    }


    private HashMap<String,List<String>> getFilterData(){
        HashMap<String,List<String>> stringListHashMap = new HashMap<>();
        List<String> category = new ArrayList<>();
        category.add("cat1");
        category.add("cat2");
        category.add("cat3");
        stringListHashMap.put(getString(R.string.filter_category),category);
        stringListHashMap.put(getString(R.string.filter_brand),category);
        stringListHashMap.put(getString(R.string.filter_size),category);
        stringListHashMap.put(getString(R.string.filter_color),category);
        return stringListHashMap;

    }
}
