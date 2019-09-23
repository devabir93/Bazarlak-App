package uk.co.ribot.androidboilerplate.ui.profile.your_order;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.llollox.androidtoggleswitch.widgets.ToggleSwitch;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.data.model.Current;
import uk.co.ribot.androidboilerplate.data.model.ProductOrder;
import uk.co.ribot.androidboilerplate.ui.base.BaseActivity;


public class YourOrderActivity extends BaseActivity implements YourOrdersMvpView {
    @BindView(R.id.activity_name_textView)
    TextView activityNameTextView;
    @BindView(R.id.toolbar1)
    Toolbar toolbar;

    @BindView(R.id.signout_button)
    Button signoutButton;
    @BindView(R.id.toggle_switch)
    ToggleSwitch toggleSwitch;
    public static int CURRENT_ORDERS = 1;
    public static int PREV_ORDERS = 2;
    @Inject
    YourOrdersPresenter yourOrdersPresenter;

    @Inject
    YourOrdersAdapter yourOrdersAdapter;
    @BindView(R.id.empty_view)
    TextView emptyView;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.main_content)
    LinearLayout mainContent;

    private List<Current> Current, pervious;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setContentView(R.layout.activity_your_order);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        toolbar.postDelayed(new Runnable()
        {
            @Override
            public void run ()
            {
                int maxWidth = toolbar.getWidth();
                int titleWidth = activityNameTextView.getWidth();
                int iconWidth = maxWidth - titleWidth;

                if (iconWidth > 0)
                {
                    //icons (drawer, menu) are on left and right side
                    int width = maxWidth - iconWidth * 2;
                    activityNameTextView.setMinimumWidth(width);
                    activityNameTextView.getLayoutParams().width = width;
                }
            }
        }, 0);
        yourOrdersPresenter.attachView(this);
        yourOrdersPresenter.checkConnection(this);
        yourOrdersAdapter.setIsArabic(isArabic());
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.addItemDecoration(new DividerItemDecoration(this,LinearLayout.VERTICAL));
        recyclerview.setAdapter(yourOrdersAdapter);
        toggleSwitch.setCheckedPosition(0);
        toggleSwitch.setOnChangeListener(new ToggleSwitch.OnChangeListener() {
            @Override
            public void onToggleSwitchChanged(int i) {
                if (i == 0) {
                    if (Current != null && Current.size() > 0) {
                        yourOrdersAdapter.setProducts(YourOrderActivity.this, Current);
                        yourOrdersAdapter.notifyDataSetChanged();
                        emptyView.setVisibility(View.GONE);
                        recyclerview.setVisibility(View.VISIBLE);
                    } else {
                        emptyView.setVisibility(View.VISIBLE);
                        recyclerview.setVisibility(View.GONE);
                    }
                } else {

                    if (pervious != null && pervious.size() > 0) {
                        emptyView.setVisibility(View.GONE);
                        recyclerview.setVisibility(View.VISIBLE);
                        yourOrdersAdapter.setProducts(YourOrderActivity.this, pervious);
                        yourOrdersAdapter.notifyDataSetChanged();
                    } else {
                        emptyView.setVisibility(View.VISIBLE);
                        recyclerview.setVisibility(View.GONE);
                    }
                }
            }
        });
    }

    @Override
    public void hasActiveInternetConnection(boolean b) {
        super.hasActiveInternetConnection(b);
        if (b) {
            yourOrdersPresenter.getOrders(this);
        }
    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_your_order, menu);
        return true;
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showOrders(List<ProductOrder> productOrderList) {

    }

    @Override
    public void showCurrentOrders(List<Current> current) {
        Timber.d("showCurrentOrders %s",current);
        this.Current = current;
        emptyView.setVisibility(View.GONE);
        recyclerview.setVisibility(View.VISIBLE);
        yourOrdersAdapter.setProducts(this, current);
        yourOrdersAdapter.notifyDataSetChanged();
    }

    @Override
    public void showPreviousOrders(List<Current> pervious) {
        Timber.d("showPreviousOrders %s",pervious);
        this.pervious = pervious;

    }

    @OnClick(R.id.signout_button)
    public void onViewClicked() {
    }
}
