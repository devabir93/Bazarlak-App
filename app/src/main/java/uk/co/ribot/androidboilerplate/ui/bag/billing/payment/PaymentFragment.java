package uk.co.ribot.androidboilerplate.ui.bag.billing.payment;

import android.graphics.LightingColorFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.data.model.PaymentDataBody;
import uk.co.ribot.androidboilerplate.data.model.RestResponse;
import uk.co.ribot.androidboilerplate.ui.base.BaseActivity;
import uk.co.ribot.androidboilerplate.ui.base.BaseFragment;
import uk.co.ribot.androidboilerplate.util.CheckValidity;
import uk.co.ribot.androidboilerplate.util.EventPosterHelper;

public class PaymentFragment extends BaseFragment implements PaymentMvpView, PaymentAdapter.HandleOnclick {

    @Inject
    PaymentPresenter paymentPresenter;
    @Inject
    PaymentAdapter paymentAdapter;
    @BindView(R.id.activity_name_textView_secondary)
    TextView activityNameTextViewSecondary;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.btn_save)
    Button btnSave;
    private PaymentDataBody mPaymentDataBody;

    Validate validate;

    public PaymentFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance() {
        PaymentFragment paymentFragment = new PaymentFragment();
        return paymentFragment;
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
        final View view = inflater.inflate(R.layout.fragment_payment, container, false);
        ButterKnife.bind(this, view);
        paymentPresenter.attachView(this);
        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setElevation(0);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(paymentAdapter);
        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);

        paymentAdapter.setData(getActivity(), integers, this);
        return view;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
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
        Fragment fragment = getActivity().getSupportFragmentManager().findFragmentByTag(PaymentFragment.class.getName());
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.remove(fragment).commit();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        paymentPresenter.detachView();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


//    @Override
//    public void onStart() {
//        super.onStart();
//        EventBus.getDefault().register(this);
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        EventBus.getDefault().unregister(this);
//    }
//

    @OnClick(R.id.btn_save)
    public void onViewClicked() {
        EventBus.getDefault().post(new CheckValidity());

//        paymentPresenter.savePaymentData(mPaymentDataBody);
    }

    //   @OnClick(R.id.btn_save)
    @Override
    public void onSaveClick(PaymentDataBody paymentDataBody) {

        Timber.d("paymentDataBody %s",paymentDataBody);
        if (paymentDataBody != null) {
            mPaymentDataBody = paymentDataBody;
            paymentPresenter.savePaymentData(mPaymentDataBody);
        }
    }

    @Override
    public void setEnabled(boolean b) {
        if (!b) {
            showSnackBar(getResources().getString(R.string.empty_inputs));
        }
    }

    @Override
    public void isSuccess(RestResponse restResponse) {
        showSnackBar(restResponse.getMessage());
    }

    interface Validate {
        void checkValidity();
    }
}
