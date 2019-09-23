package uk.co.ribot.androidboilerplate.ui.bag.billing;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.data.model.AddToCartBody;
import uk.co.ribot.androidboilerplate.data.model.AddressBody;
import uk.co.ribot.androidboilerplate.data.model.CartBody;
import uk.co.ribot.androidboilerplate.data.model.CartData;
import uk.co.ribot.androidboilerplate.data.model.PaymentDataBody;
import uk.co.ribot.androidboilerplate.data.model.ProductOrder;
import uk.co.ribot.androidboilerplate.data.model.RestResponse;
import uk.co.ribot.androidboilerplate.ui.bag.billing.payment.PaymentFragment;
import uk.co.ribot.androidboilerplate.ui.base.BaseActivity;
import uk.co.ribot.androidboilerplate.ui.base.BaseFragment;
import uk.co.ribot.androidboilerplate.ui.profile.your_profile.PaymentDetailsActivity;
import uk.co.ribot.androidboilerplate.ui.profile.your_profile.ShippingAddressActivityFragment;
import uk.co.ribot.androidboilerplate.util.ViewUtil;

public class BillingAndShippingFragment extends BaseFragment implements BillingAndShippingMvpView {

    @Inject
    BillingAndShippingPresenter billingAndShippingPresenter;
    List<ProductOrder> productOrders;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.choose_payment_textView)
    TextView choosePaymentTextView;
    @BindView(R.id.plus_imageView)
    ImageView plusImageView;
    @BindView(R.id.visa_imageView)
    ImageView visaImageView;
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
    @BindView(R.id.subTotalLable_textView)
    TextView subTotalLableTextView;
    @BindView(R.id.subTotal_textView)
    TextView subTotalTextView;
    @BindView(R.id.shippingLabel_textView)
    TextView shippingLabelTextView;
    @BindView(R.id.shipping_textView)
    TextView shippingTextView;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.totalLabel_textView)
    TextView totalLabelTextView;
    @BindView(R.id.total_textView)
    TextView totalTextView;
    @BindView(R.id.price_layout)
    RelativeLayout priceLayout;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.buy_button)
    Button buyButton;
    @BindView(R.id.container_billing_and_shipping)
    RelativeLayout containerBillingAndShipping;

    private PaymentDataBody paymentData;
    private AddressBody paymentAddress;

    public BillingAndShippingFragment() {
        // Required empty public constructor
    }

    public void setOrder(List<ProductOrder> productOrder) {
        this.productOrders = productOrder;
    }

    @NonNull
    public static BillingAndShippingFragment newInstance(List<ProductOrder> productOrder) {
        BillingAndShippingFragment fragment = new BillingAndShippingFragment();
        fragment.setOrder(productOrder);
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
        // Inflate the layout for this fragmentBillingAndShippingFragment
        final View view = inflater.inflate(R.layout.fragment_billing_and_shipping, container, false);
        ButterKnife.bind(this, view);
        Timber.d("onCreateView BillingAndShippingFragment");
        billingAndShippingPresenter.attachView(this);
        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setElevation(0);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        billingAndShippingPresenter.getPaymentAddressData();
        return view;
    }


    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        //menu.clear();
        MenuItem filter = menu.findItem(R.id.ic_filter_action);
        MenuItem bag = menu.findItem(R.id.ic_delete_action);
        if (filter != null)
            filter.setVisible(false); // Display clear filters
        if (bag != null)
            bag.setVisible(false); // Display clear filters
        MenuItem backAction = menu.findItem(android.R.id.home);
        if (backAction != null)
            backAction.setVisible(false); // Display clear filters
        super.onPrepareOptionsMenu(menu);
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
                showPaymentFragment();
                break;
            case R.id.shipping_layout:
                showAddressFragment();
                break;
            case R.id.buy_button:
                billingAndShippingPresenter.checkConnection(getContext());
                break;
        }
    }


    private void showPaymentFragment() {
        Intent intent = new Intent(getActivity(), PaymentDetailsActivity.class);
        intent.putExtra("frag", PaymentFragment.class.getName());
        startActivity(intent);

    }

    private void showAddressFragment() {
        Intent intent = new Intent(getActivity(), PaymentDetailsActivity.class);
        intent.putExtra("frag", ShippingAddressActivityFragment.class.getName());
        startActivity(intent);
    }

    @Override
    public void showEmptyAddress() {
        addressSummaryTextView.setVisibility(View.GONE);
        shippingImageView.setImageResource(R.drawable.ic_plus);

    }

    @Override
    public void showPayment(PaymentDataBody paymentVisa) {
        //if (paymentVisa.getType().equals("0"))
        plusImageView.setVisibility(View.INVISIBLE);
        visaImageView.setVisibility(View.VISIBLE);
        paymentData = paymentVisa;

    }

    @Override
    public void showAddress(List<AddressBody> paymentAddress) {
        String text = paymentAddress.toString().replace("[", "").replace("]", "");//remove brackets([) convert it to string

        addressSummaryTextView.setVisibility(View.VISIBLE);
        shippingImageView.setImageResource(R.drawable.baseline_edit_black_24dp);
        addressSummaryTextView.setText(text);
        this.paymentAddress = paymentAddress.get(0);

    }

    @Override
    public void showEmptyPayemnt() {
        plusImageView.setVisibility(View.VISIBLE);
        visaImageView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void isSuccess(RestResponse restResponse) {
        if (restResponse.getStatus()) {
            Intent intent = new Intent(getActivity(), OrderDoneActivity.class);
            startActivity(intent);
        } else showMessage(restResponse.getMessage());
    }

    @Override
    public void hasActiveInternetConnection(boolean b) {
        super.hasActiveInternetConnection(b);
        if (b) {
            AddToCartBody addToCartBody = new AddToCartBody();
            CartData cartData = new CartData();
            cartData.setProductId(1);
            cartData.setFeatureId(1);
            cartData.setQuantity(2);
            List<CartData> dataList = new ArrayList<>();
            dataList.add(cartData);
            addToCartBody.setCartData(dataList);
            CartBody cartBody = new CartBody();
            String cartJson = ViewUtil.convertobjToJson(addToCartBody, AddToCartBody.class);
            cartBody.setAddToCartBody(cartJson);
            Timber.d("%s", cartJson);
            billingAndShippingPresenter.buyOrder(cartBody);
        }
    }

    @Override
    public void showMessage(String msg) {
        showSnackBar(msg);
    }
}
