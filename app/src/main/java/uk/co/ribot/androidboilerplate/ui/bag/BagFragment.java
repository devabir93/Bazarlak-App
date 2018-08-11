package uk.co.ribot.androidboilerplate.ui.bag;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.data.model.ProductOrder;
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
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    private List<ProductOrder> mSavedProductOrders;

    public BagFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BaseActivity) getActivity()).activityComponent().inject(this);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.bag_fragment, container, false);
        bagPresenter.attachView(this);
        ButterKnife.bind(this, view);
        bagPresenter.getSavedOrders();
        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setElevation(0);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
//        List<ProductOrder> productOrders = new ArrayList<>();
//        Product product = new Product();
//        product.setName("Chanele");
//        product.setPrice("30$");
//        product.setImage("http://bazarlak.com/uploads/products/5ad2358391597.png");
//        product.setDescription("Nice bag");
//        ProductOrder productOrder = new ProductOrder();
//        productOrder.setProduct(product);
//        //productOrder.setColor("#");
//        productOrder.setSize("small");
//        productOrder.setQuantity("2");
//        productOrders.add(productOrder);
        bagRecyclerView.setAdapter(bagAdapter);
        bagRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        menu.clear();
//        inflater.inflate(R.menu.menu_bag, menu);
//        super.onCreateOptionsMenu(menu, inflater);
//    }


//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.menu_main, menu);
//        super.onCreateOptionsMenu(menu, inflater);
//    }


    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showSavedOrders(List<ProductOrder> productOrderList) {
        bagRecyclerView.setVisibility(View.VISIBLE);
        emptyBagLayout.setVisibility(View.GONE);
        checkoutBt.setVisibility(View.VISIBLE);

        bagAdapter.setProducts(getContext(), this, productOrderList);

    }

    @Override
    public void showEmpty() {
        bagRecyclerView.setVisibility(View.GONE);
        emptyBagLayout.setVisibility(View.VISIBLE);
        checkoutBt.setVisibility(View.GONE);
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

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment nextFrag = BillingAndShippingFragment.newInstance(mSavedProductOrders);
//        getActivity().getSupportFragmentManager().beginTransaction()
        transaction.replace(R.id.container_bag, nextFrag, BillingAndShippingFragment.class.getName());
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void oncheckOrder(List<ProductOrder> saveProductOrders) {
        Timber.d("saveProductOrders %s", saveProductOrders);
        mSavedProductOrders = saveProductOrders;
        if (saveProductOrders != null && saveProductOrders.size() > 0)
            checkoutBt.setText(getContext().getResources().getString(R.string.checkout_label) + "(" + saveProductOrders.size() + ")");
        else
            checkoutBt.setText(R.string.checkout_label);

    }

    @Override
    public void showProgresBar(boolean b) {
        if (b) {
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setIndeterminate(true);

        } else
            progressBar.setVisibility(View.GONE);

    }
}
