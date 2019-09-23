package uk.co.ribot.androidboilerplate.ui.profile.your_profile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.ui.bag.billing.payment.PaymentFragment;
import uk.co.ribot.androidboilerplate.ui.base.BaseActivity;

public class PaymentDetailsActivity extends BaseActivity {
    String fragName;
    @BindView(R.id.toolbar1)
    Toolbar toolbar;
    @BindView(R.id.activity_name_textView)
    TextView activityNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_details);
        activityComponent().inject(this);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        toolbar.postDelayed(new Runnable() {
            @Override
            public void run() {
                int maxWidth = toolbar.getWidth();
                int titleWidth = activityNameTextView.getWidth();
                int iconWidth = maxWidth - titleWidth;

                if (iconWidth > 0) {
                    //icons (drawer, menu) are on left and right side
                    int width = maxWidth - iconWidth * 2;
                    activityNameTextView.setMinimumWidth(width);
                    activityNameTextView.getLayoutParams().width = width;
                }
            }
        }, 0);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = null;
        if (getIntent().hasExtra("frag")) {
            fragName = getIntent().getStringExtra("frag");
            if (fragName.equals(PaymentFragment.class.getName())) {
                fragment = new PaymentFragment();
                fragmentTransaction.add(R.id.fragment_container, fragment, PaymentFragment.class.getName());
            } else {
                fragment = new ShippingAddressActivityFragment();
                fragmentTransaction.add(R.id.fragment_container, fragment, ShippingAddressActivityFragment.class.getName());
            }

        }

        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

}
