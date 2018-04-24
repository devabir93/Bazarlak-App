package uk.co.ribot.androidboilerplate.ui.bag.billing;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.data.model.Order;
import uk.co.ribot.androidboilerplate.ui.bag.BagFragment;
import uk.co.ribot.androidboilerplate.ui.bag.address.AddressFragment;
import uk.co.ribot.androidboilerplate.ui.base.BaseActivity;
import uk.co.ribot.androidboilerplate.ui.base.BaseFragment;

public class BillingAndShippingFragment extends BaseFragment implements BillingAndShippingMvpView {

    @Inject
    BillingAndShippingPresenter billingAndShippingPresenter;
    List<Order> orders;
    @BindView(R.id.choose_payment_textView)
    TextView choosePaymentTextView;
    @BindView(R.id.payment_imageView)
    ImageView paymentImageView;
    @BindView(R.id.payment_layout)
    RelativeLayout paymentLayout;
    @BindView(R.id.choose_shipping_textView)
    TextView chooseShippingTextView;
    @BindView(R.id.shipping_imageView)
    ImageView shippingImageView;
    @BindView(R.id.address_summary_textView)
    TextView addressSummaryTextView;
    @BindView(R.id.shipping_layout)
    RelativeLayout shippingLayout;
    @BindView(R.id.subTotal_textView)
    TextView subTotalTextView;
    @BindView(R.id.shipping_textView)
    TextView shippingTextView;
    @BindView(R.id.total_textView)
    TextView totalTextView;
    @BindView(R.id.price_layout)
    RelativeLayout priceLayout;
    @BindView(R.id.bag_imageView)
    ImageView bagImageView;
    @BindView(R.id.brand_name)
    TextView brandName;
    @BindView(R.id.item_type)
    TextView itemType;
    @BindView(R.id.item_size)
    TextView itemSize;
    @BindView(R.id.price_textView)
    TextView priceTextView;
    @BindView(R.id.items_layout)
    RelativeLayout itemsLayout;
    @BindView(R.id.buy_button)
    Button buyButton;

    public BillingAndShippingFragment() {
        // Required empty public constructor
    }

    public void setOrder(List<Order> order) {
        this.orders = order;
    }

    @NonNull
    public static BillingAndShippingFragment newInstance(List<Order> order) {
        BillingAndShippingFragment fragment = new BillingAndShippingFragment();
        fragment.setOrder(order);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BaseActivity) getActivity()).activityComponent().inject(this);
        Timber.d("BillingAndShippingFragment");
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_billing_and_shipping, container, false);
        ButterKnife.bind(this, view);
        Timber.d("onCreateView BillingAndShippingFragment");
        billingAndShippingPresenter.attachView(this);
        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void onBackPressed() {
        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);
        }
        Fragment fragment = getActivity().getSupportFragmentManager().findFragmentByTag(BillingAndShippingFragment.class.getName());
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.remove(fragment).commit();
 
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        billingAndShippingPresenter.detachView();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick({R.id.payment_layout, R.id.shipping_layout, R.id.buy_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.payment_layout:
                break;
            case R.id.shipping_layout:
                showAddressFragment();
                break;
            case R.id.buy_button:
                break;
        }
    }


    private void showPaymentFragment() {
//        Fragment nextFrag = BillingAndShippingFragment.newInstance();
////        getActivity().getSupportFragmentManager().beginTransaction()
////                .replace(R.id.container_billing_and_shipping, nextFrag, BillingAndShippingFragment.class.getName())
////                .commit();
    }

    private void showAddressFragment() {
        Fragment nextFrag = AddressFragment.newInstance();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_billing_and_shipping, nextFrag, AddressFragment.class.getName())
                .commit();
    }
}
