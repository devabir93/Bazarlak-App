package uk.co.ribot.androidboilerplate.ui.profile.your_order;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.data.model.ProductOrder;
import uk.co.ribot.androidboilerplate.data.model.Product;
import uk.co.ribot.androidboilerplate.ui.base.BaseActivity;
import uk.co.ribot.androidboilerplate.ui.base.BaseFragment;
import uk.co.ribot.androidboilerplate.util.ViewUtil;


/**
 * A simple {@link Fragment} subclass.
 */
public class YourOrdersFragment extends BaseFragment implements YourOrdersMvpView {


    int mOrdersType;
    @Inject
    YourOrdersPresenter yourOrdersPresenter;
    @BindView(R.id.empty_view)
    TextView emptyView;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @Inject YourOrdersAdapter yourOrdersAdapter;

    public YourOrdersFragment() {
        // Required empty public constructor
    }

    @NonNull
    public static YourOrdersFragment newInstance(int ordersType) {
        YourOrdersFragment fragment = new YourOrdersFragment();
        fragment.setOrdersType(ordersType);
        return fragment;
    }

    private void setOrdersType(int ordersType) {
        mOrdersType = ordersType;
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
        View view = inflater.inflate(R.layout.fragment_your_orders, container, false);
        ButterKnife.bind(this, view);
        yourOrdersPresenter.attachView(this);
        List<ProductOrder> productOrders = new ArrayList<>();
        Product product = new Product();
        product.setName("Chanele");
        product.setPrice("30$");
        product.setImage("http://bazarlak.com/uploads/products/5ad2358391597.png");
        product.setDescription("Nice bag");
        ProductOrder productOrder = new ProductOrder();
        productOrder.setProduct(product);
        //productOrder.setColor("#");
        productOrder.setSize("small");
        productOrder.setQuantity("2");
        productOrders.add(productOrder);
        recyclerView.setAdapter(yourOrdersAdapter);
        yourOrdersAdapter.setProducts(getContext(), productOrders);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showOrders(List<ProductOrder> productOrderList) {

    }

    @Override
    public void hasActiveInternetConnection(boolean b) {
        if (!b) {
            Timber.d("no connection");
            ViewUtil.createSnackbar(getActivity().getWindow().getDecorView().getRootView(), getResources().getString(R.string.no_connection)).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
