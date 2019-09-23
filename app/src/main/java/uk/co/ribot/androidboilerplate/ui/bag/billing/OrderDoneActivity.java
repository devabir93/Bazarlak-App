package uk.co.ribot.androidboilerplate.ui.bag.billing;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.ribot.androidboilerplate.R;

public class OrderDoneActivity extends AppCompatActivity {

    @BindView(R.id.activity_name_textView)
    TextView activityNameTextView;
    @BindView(R.id.toolbar1)
    Toolbar toolbar1;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.order_id)
    TextView orderId;
    @BindView(R.id.delivery_time)
    TextView deliveryTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thank_you_layout);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        this.finish();
    }
}
