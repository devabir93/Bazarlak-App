package uk.co.ribot.androidboilerplate.ui.bag;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.data.model.Order;
import uk.co.ribot.androidboilerplate.data.model.Product;
import uk.co.ribot.androidboilerplate.ui.bag.billing.BillingAndShippingFragment;
import uk.co.ribot.androidboilerplate.ui.base.BaseActivity;
import uk.co.ribot.androidboilerplate.ui.base.BaseFragment;

public class BagFragment extends BaseFragment implements BagMvpView, BagAdapter.UpdateDataClickListener {

    @Inject
    BagPresenter bagPresenter;
    @Inject
    BagAdapter bagAdapter;
    @BindView(R.id.go_shopping_button)
    Button goShoppingButton;
    @BindView(R.id.empty_bag_layout)
    LinearLayout emptyBagLayout;
    @BindView(R.id.bag_recyclerView)
    RecyclerView bagRecyclerView;
    @BindView(R.id.checkout_bt)
    Button checkoutBt;
    @BindView(R.id.bag_layout)
    RelativeLayout bagLayout;
    boolean isEmptyBag = false;
    private List<Order> mSavedOrders;

    public BagFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BaseActivity) getActivity()).activityComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.bag_fragment, container, false);
        bagPresenter.attachView(this);
        ButterKnife.bind(this, view);
        if (isEmptyBag) {
            bagRecyclerView.setVisibility(View.GONE);
            emptyBagLayout.setVisibility(View.VISIBLE);
        } else {
            bagRecyclerView.setVisibility(View.VISIBLE);
            emptyBagLayout.setVisibility(View.GONE);
        }

        List<Order> orders = new ArrayList<>();
        Product product = new Product();
        product.setName("Chanele");
        product.setPrice("30$");
        product.setImage("http://bazarlak.com/uploads/products/5ad2358391597.png");
        product.setDescription("Nice bag");
        Order order = new Order();
        order.setProduct(product);
        //order.setColor("#");
        order.setSize("small");
        order.setQuantity("2");
        orders.add(order);
        bagRecyclerView.setAdapter(bagAdapter);
        bagAdapter.setProducts(getContext(), this, orders);
        bagRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    @Override
    public void isEmptyBag(boolean b) {
        isEmptyBag = b;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bagPresenter.detachView();
    }

    @OnClick({R.id.go_shopping_button, R.id.checkout_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.go_shopping_button:
                break;
            case R.id.checkout_bt:
                showCheckoutFragment();
                break;
        }
    }

    private void showCheckoutFragment() {

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment nextFrag = BillingAndShippingFragment.newInstance(mSavedOrders);
//        getActivity().getSupportFragmentManager().beginTransaction()
        transaction.replace(R.id.container_bag, nextFrag, BillingAndShippingFragment.class.getName());
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void oncheckOrder(List<Order> saveOrders) {
        Timber.d("saveOrders %s", saveOrders);
        mSavedOrders = saveOrders;
        if (saveOrders != null && saveOrders.size() > 0)
            checkoutBt.setText(getContext().getResources().getString(R.string.checkout_label) + "(" + saveOrders.size() + ")");
        else
            checkoutBt.setText(R.string.checkout_label);

    }


}
