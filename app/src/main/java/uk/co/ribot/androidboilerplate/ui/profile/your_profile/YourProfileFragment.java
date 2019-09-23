package uk.co.ribot.androidboilerplate.ui.profile.your_profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.data.local.PreferencesHelper;
import uk.co.ribot.androidboilerplate.ui.bag.billing.payment.PaymentFragment;
import uk.co.ribot.androidboilerplate.ui.base.BaseActivity;
import uk.co.ribot.androidboilerplate.ui.base.BaseFragment;
import uk.co.ribot.androidboilerplate.ui.profile.ProfileMvpView;
import uk.co.ribot.androidboilerplate.util.Message;

public class YourProfileFragment extends BaseActivity implements ProfileMvpView {

    @BindView(R.id.edit_info_layout)
    RelativeLayout editInfoLayout;
    @BindView(R.id.password_layout)
    RelativeLayout passwordLayout;
    @BindView(R.id.email_layout)
    RelativeLayout emailLayout;
    @BindView(R.id.payment_details_layout)
    RelativeLayout paymentDetailsLayout;
    @BindView(R.id.shipping_address_layout)
    RelativeLayout shippingAddressLayout;
    @BindView(R.id.signout_button)
    Button signoutButton;
    @BindView(R.id.activity_name_textView)
    TextView title;

    @BindView(R.id.toolbar1)
    Toolbar toolbar;
    @Inject
    PreferencesHelper preferencesHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setContentView(R.layout.your_profile_fragment);
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
                int titleWidth = title.getWidth();
                int iconWidth = maxWidth - titleWidth;

                if (iconWidth > 0)
                {
                    //icons (drawer, menu) are on left and right side
                    int width = maxWidth - iconWidth * 2;
                    title.setMinimumWidth(width);
                    title.getLayoutParams().width = width;
                }
            }
        }, 0);
    }
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
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @OnClick({R.id.signout_button, R.id.edit_info_layout, R.id.password_layout, R.id.email_layout, R.id.payment_details_layout, R.id.shipping_address_layout})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.signout_button:
                preferencesHelper.removeUserSession();
                this.finish();
                break;
            case R.id.edit_info_layout:
                startActivity(new Intent(this, AccountInfoFragment.class));
                break;
            case R.id.password_layout:
                startActivity(new Intent(this, ChangePasswordFragment.class));
                ;
                break;
            case R.id.email_layout:
                startActivity(new Intent(this, ChangeEmailFragment.class));
                break;
            case R.id.payment_details_layout:
                intent = new Intent(this, PaymentDetailsActivity.class);
                intent.putExtra("frag", PaymentFragment.class.getName());
                startActivity(intent);
                break;
            case R.id.shipping_address_layout:
                intent = new Intent(this, PaymentDetailsActivity.class);
                intent.putExtra("frag", ShippingAddressActivityFragment.class.getName());
                startActivity(intent);
                break;
        }
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

    @Override
    public void showMessage(String s) {

    }

    @Override
    public void showMessage(boolean b, String string, Message logging) {

    }

    @Override
    public void showProgresBar(boolean b) {

    }

    @Override
    public void finishActivity(boolean b) {

    }
}
