package uk.co.ribot.androidboilerplate.ui.search;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.data.model.Product;
import uk.co.ribot.androidboilerplate.ui.base.BaseActivity;
import uk.co.ribot.androidboilerplate.ui.base.BaseFragment;
import uk.co.ribot.androidboilerplate.ui.category.subcategory.SubCategoryFragment;
import uk.co.ribot.androidboilerplate.ui.category.subcategory.products.ProductsDetailsFragment;
import uk.co.ribot.androidboilerplate.ui.category.subcategory.products.ProductsGridViewRecyclerViewAdapter;
import uk.co.ribot.androidboilerplate.util.DialogFactory;
import uk.co.ribot.androidboilerplate.util.RecyclerItemClickListener;
import uk.co.ribot.androidboilerplate.util.ViewUtil;

public class SearchFragment extends BaseFragment implements SearchMvpView {
    @BindView(R.id.search_result_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.searchView)
    SearchView searchView;
    @BindView(R.id.empty_view)
    TextView emptyView;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @Inject
    SearchPresenter searchPresenter;
    private String key;
    @Inject
    ProductsGridViewRecyclerViewAdapter productsGridViewRecyclerViewAdapter;

    List<Product> mproductList;

    public SearchFragment() {
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
        View view = inflater.inflate(R.layout.search_fragment, container, false);
        ButterKnife.bind(this, view);
        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);
        }
        searchView.setQueryHint(getString(R.string.search_hint));
        searchView.setIconifiedByDefault(false);
        searchPresenter.attachView(this);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // do something on text submit
                searchPresenter.checkConnection(getContext());
                key = query;
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // do something when text changes
                return false;
            }
        });

        mRecyclerView.setAdapter(productsGridViewRecyclerViewAdapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // do whatever
                        showProductDetails(mproductList.get(position));
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
        return view;
    }


    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        super.onPrepareOptionsMenu(menu);
    }

//    @Override
//    public void onPrepareOptionsMenu(Menu menu) {
//        super.onPrepareOptionsMenu(menu);
//        menu.clear();
//        MenuItem filter = menu.findItem(R.id.ic_filter_action);
//        MenuItem bag = menu.findItem(R.id.ic_delete_action);
//        filter.setVisible(false); // Display clear filters
//        bag.setVisible(false); // Display clear filters
//        MenuItem backAction = menu.findItem(android.R.id.home);
//        if (backAction != null)
//            backAction.setVisible(false); // Display clear filters
//    }


    private void showProductDetails(Product product) {
        Timber.d("product %s", product);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment nextFrag = ProductsDetailsFragment.newInstance(product);
        transaction.add(R.id.container_search, nextFrag, ProductsDetailsFragment.class.getName());
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void showEmpty() {
        emptyView.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);

    }

    @Override
    public void showSearchResult(List<Product> data) {
        Timber.d("showSearchResult %s", data);
        emptyView.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
        mproductList = data;
        productsGridViewRecyclerViewAdapter.setProductData(getContext(), data);
        productsGridViewRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void hasActiveInternetConnection(boolean b) {
        super.hasActiveInternetConnection(b);
        if (b) {
            searchPresenter.getSearchResult(key, "1");
        }
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
