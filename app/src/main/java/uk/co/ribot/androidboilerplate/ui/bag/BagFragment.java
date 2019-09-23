package uk.co.ribot.androidboilerplate.ui.bag;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
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

import org.greenrobot.eventbus.EventBus;

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
import uk.co.ribot.androidboilerplate.util.CartBadge;

public class BagFragment extends BaseFragment implements BagMvpView, BagAdapter.UpdateDataClickListener {

    @Inject
    BagPresenter bagPresenter;
    @Inject
    BagAdapter bagAdapter;
    @BindView(R.id.go_shopping_button)
    Button goShoppingButton;
    @BindView(R.id.empty_bag)
    LinearLayout emptyBagLayout;
    @BindView(R.id.bag_recyclerView)
    RecyclerView bagRecyclerView;
    @BindView(R.id.checkout_bt)
    Button checkoutBt;
    @BindView(R.id.bag_layout)
    RelativeLayout bagLayout;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    private List<ProductOrder> mProductOrderList;
    private List<ProductOrder> checkedProductOrders;

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
            ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        }

        bagRecyclerView.setAdapter(bagAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        bagRecyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(bagRecyclerView.getContext(),
                linearLayoutManager.getOrientation());
        bagRecyclerView.addItemDecoration(dividerItemDecoration);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        Timber.d("onPrepareOptionsMenu %s", mProductOrderList);
        //menu.clear();
        MenuItem filter = menu.findItem(R.id.ic_filter_action);
        MenuItem bag = menu.findItem(R.id.ic_delete_action);
        if (filter != null)
            filter.setVisible(false); // Display clear filters
        if (bag != null) {
            Timber.d("bag != null");
            if (mProductOrderList != null && mProductOrderList.size() > 0) {
                bag.setVisible(true); // Display clear filters
            }
        }
        MenuItem backAction = menu.findItem(android.R.id.home);
        if (backAction != null)
            backAction.setVisible(false); // Display clear filters
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ic_delete_action:
                deleteOrder();
        }
        return super.onOptionsItemSelected(item);
    }

    private void deleteOrder() {
        bagAdapter.notifyDataSetChanged();
        bagPresenter.deleteOrder(checkedProductOrders);
    }

    @Override
    public void showSavedOrders(List<ProductOrder> productOrderList) {
        mProductOrderList = productOrderList;
        bagRecyclerView.setVisibility(View.VISIBLE);
        emptyBagLayout.setVisibility(View.GONE);
        checkoutBt.setVisibility(View.VISIBLE);
        bagAdapter.setProducts(getContext(), this, productOrderList);
        bagAdapter.notifyDataSetChanged();
        getActivity().invalidateOptionsMenu();

    }

    @Override
    public void showEmpty() {
        mProductOrderList = null;
        bagRecyclerView.setVisibility(View.GONE);
        emptyBagLayout.setVisibility(View.VISIBLE);
        checkoutBt.setVisibility(View.GONE);
        bagAdapter.notifyDataSetChanged();
        getActivity().invalidateOptionsMenu();
    }

    private void checkAdapterIsEmpty() {
        if (bagAdapter.getItemCount() == 0) {
            emptyBagLayout.setVisibility(View.VISIBLE);
            bagRecyclerView.setVisibility(View.GONE);
            checkoutBt.setVisibility(View.GONE);
        } else {
            emptyBagLayout.setVisibility(View.GONE);
        }
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
        Fragment nextFrag = BillingAndShippingFragment.newInstance(checkedProductOrders);
//        getActivity().getSupportFragmentManager().beginTransaction()
        transaction.replace(R.id.container_bag, nextFrag, BillingAndShippingFragment.class.getName());
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void oncheckOrder(List<ProductOrder> checkedProductOrders) {
        Timber.d("checkedProductOrders %s", checkedProductOrders);
        this.checkedProductOrders = checkedProductOrders;
//        if (checkedProductOrders != null && checkedProductOrders.size() > 0)
//            checkoutBt.setText(getContext().getResources().getString(R.string.checkout_label) + "(" + checkedProductOrders.size() + ")");
//        else
        checkoutBt.setText(R.string.checkout_label);

    }

//    @Override
//    public void onDeleteOrder(int position) {
//        bagAdapter.removeAt(position);
//        int count = 0;
//        if (mProductOrderList != null)
//            count = mProductOrderList.size();
//        Timber.d("count %s", count);
//        EventBus.getDefault().post(new CartBadge(count));
//        checkAdapterIsEmpty();
//    }

    @Override
    public void showProgresBar(boolean b) {
        if (b) {
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setIndeterminate(true);

        } else
            progressBar.setVisibility(View.GONE);

    }
}
