package uk.co.ribot.androidboilerplate.ui.category.subcategory;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import butterknife.ButterKnife;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.ui.base.BaseActivity;

public class SubCategoryeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setContentView(R.layout.activity_sub_category);
        ButterKnife.bind(this);
//
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // Create new fragment and transaction
        Fragment fragment = new SubCategoryFragment();
        fragmentTransaction.add(R.id.activity_container, fragment);
        fragmentTransaction.commit();
//        setTitle(getResources().getString(R.string.button_search_offline) + " " + surveyName + " " +
//                getResources().getString(R.string.button_search_offline_entries));
    }

}
