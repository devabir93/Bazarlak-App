package uk.co.ribot.androidboilerplate.ui.profile.your_profile;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.ui.base.BaseActivity;

public class PaymentDetailsActivity extends BaseActivity {
    String fragName;
    @BindView(R.id.second_toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_details);
        ButterKnife.bind(this);
        TextView textView = toolbar.findViewById(R.id.activity_name_textView_secondary);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = null;
        if (getIntent().hasExtra("frag")) {
            fragName = getIntent().getStringExtra("frag");
            if (fragName.equals(PaymentDetailsActivityFragment.class.getName())) {
                textView.setText(getString(R.string.payment_details));
                fragment = new PaymentDetailsActivityFragment();
                fragmentTransaction.add(R.id.fragment_container, fragment,PaymentDetailsActivityFragment.class.getName());
            } else {
                textView.setText(getString(R.string.shipping_address));
                fragment = new ShippingAddressActivityFragment();
                fragmentTransaction.add(R.id.fragment_container, fragment,ShippingAddressActivityFragment.class.getName());
            }

        }

        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
