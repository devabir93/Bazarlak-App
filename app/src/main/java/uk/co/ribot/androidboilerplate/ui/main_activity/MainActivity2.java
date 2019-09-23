package uk.co.ribot.androidboilerplate.ui.main_activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.mikepenz.actionitembadge.library.ActionItemBadge;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import q.rorbin.badgeview.Badge;
import timber.log.Timber;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.data.SyncService;
import uk.co.ribot.androidboilerplate.ui.bag.BagFragment;
import uk.co.ribot.androidboilerplate.ui.base.BaseActivity;
import uk.co.ribot.androidboilerplate.ui.category.CategoryFragment;
import uk.co.ribot.androidboilerplate.ui.home.HomeFragment;
import uk.co.ribot.androidboilerplate.ui.profile.ProfileFragment;
import uk.co.ribot.androidboilerplate.ui.search.SearchFragment;
import uk.co.ribot.androidboilerplate.util.CartBadge;
import uk.co.ribot.androidboilerplate.util.LoadFragment;

public class MainActivity2 extends BaseActivity implements MainMvpView {

    private static final String EXTRA_TRIGGER_SYNC_FLAG =
            "uk.co.ribot.androidboilerplate.ui.main_activity.SplashActivity.EXTRA_TRIGGER_SYNC_FLAG";
    @BindView(R.id.navigationView)
    BottomNavigationViewEx bnve;
    @BindView(R.id.activity_name_textView)
    TextView activityNameTextView;
    @BindView(R.id.toolbar1)
    Toolbar toolbar;
    TextView notificationCount;
    @Inject
    MainPresenter mainPresenter;
    Badge badge = null;
    private int mNotifCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        mainPresenter.attachView(this);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayShowHomeEnabled(false);
        }
        if (getIntent().getBooleanExtra(EXTRA_TRIGGER_SYNC_FLAG, true)) {
            startService(SyncService.getStartIntent(this));
        }
        Fragment fragment = new HomeFragment();
        loadFragment(new LoadFragment(fragment));
        bnve.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

//        bnve.enableAnimation(false);
//        bnve.enableShiftingMode(false);
//        bnve.enableItemShiftingMode(false);

        mainPresenter.getBadgeCount();

    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void updateCartBadge(CartBadge cartBadge) {
        mNotifCount = cartBadge.getCount();
        showBadgeCount(cartBadge.getCount());
    }

    @Override
    public void showBadgeCount(int count) {
        Timber.d("count %s", count);
        mNotifCount = count;
        BottomNavigationMenuView bottomNavigationMenuView =
                (BottomNavigationMenuView) bnve.getChildAt(0);
        View v = bottomNavigationMenuView.getChildAt(3);

/*
        if (badge == null)
            badge = new QBadgeView(getApplicationContext()).bindTarget(v);

        if (count == 0) {
            Timber.d("count==0");
            badge.hide(true);
        } else {
            badge.setBadgeNumber(count)
                    .setBadgeBackgroundColor(getResources().getColor(R.color.colorPrimary))
                    .setExactMode(true)
                    .setBadgeGravity(Gravity.END | Gravity.TOP)
                    .setBadgeTextColor(getResources().getColor(R.color.white))
                    .setGravityOffset(0, 0, true)
                    .setBadgePadding(4, true)
            ;
        }
*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        if (mNotifCount > 0)
            ActionItemBadge.update(this, menu.findItem(R.id.navigation_bag), this.getResources().getDrawable(R.drawable.bag_selector), ActionItemBadge.BadgeStyles.RED, mNotifCount);
        else
            ActionItemBadge.update(this, menu.findItem(R.id.navigation_bag), this.getResources().getDrawable(R.drawable.bag_selector), ActionItemBadge.BadgeStyles.RED, null);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        //  menu.clear();
        MenuItem backAction = menu.findItem(android.R.id.home);
        MenuItem filter = menu.findItem(R.id.ic_filter_action);
        MenuItem bag = menu.findItem(R.id.ic_delete_action);
        if (filter != null)
            filter.setVisible(false); // Display clear filters
        if (bag != null)
            bag.setVisible(false); // Display clear filters
        if (backAction != null)
            backAction.setVisible(false); // Display clear filters
        return super.onPrepareOptionsMenu(menu);
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

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //toolbar.setTitle(getString(R.string.home_tab));
                    fragment = new HomeFragment();
                    loadFragment(new LoadFragment(fragment));
                    return true;
                case R.id.navigation_category:
                    //toolbar.setTitle(getString(R.string.category_tab));
                    fragment = new CategoryFragment();
                    loadFragment(new LoadFragment(fragment));
                    return true;
                case R.id.navigation_search:
                    // toolbar.setTitle(getString(R.string.search_tab));
                    fragment = new SearchFragment();
                    loadFragment(new LoadFragment(fragment));
                    return true;
                case R.id.navigation_bag:
                    // toolbar.setTitle(getString(R.string.bag_tab));
                    fragment = new BagFragment();
                    loadFragment(new LoadFragment(fragment));
                    return true;

                case R.id.navigation_me:
                    // toolbar.setTitle(getString(R.string.me_tab));
                    fragment = new ProfileFragment();
                    loadFragment(new LoadFragment(fragment));
                    return true;
            }

            return false;
        }
    };

    // @Subscribe
    private void loadFragment(LoadFragment loadFragment) {
        // load fragment
        Timber.d("loadFragment", loadFragment.getFragment().getClass().getName());
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, loadFragment.getFragment(), loadFragment.getFragment().getClass().getName());
        transaction.commit();
    }

    @Override
    public void onBackPressed() {

//        if (getSupportFragmentManager().getBackStackEntryCount() > 0)
//            getSupportFragmentManager().popBackStackImmediate();

        super.onBackPressed();

    }

}
