package uk.co.ribot.androidboilerplate.ui.bag;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.data.model.Product;
import uk.co.ribot.androidboilerplate.ui.bag.checkout.CheckoutFragment;
import uk.co.ribot.androidboilerplate.ui.base.BaseActivity;
import uk.co.ribot.androidboilerplate.ui.base.BaseFragment;

public class BagFragment extends BaseFragment implements BagMvpView , BagAdapter.UpdateDataClickListener {

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

        bagRecyclerView.setAdapter(bagAdapter);
        //bagAdapter.setProducts(getContext(), this,null);
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
        Fragment nextFrag = new CheckoutFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_bag, nextFrag, CheckoutFragment.class.getName())
                .commit();
    }

    @Override
    public void oncheckOrder(List<Product> products) {

    }


}
