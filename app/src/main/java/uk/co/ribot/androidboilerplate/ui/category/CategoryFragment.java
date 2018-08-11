package uk.co.ribot.androidboilerplate.ui.category;

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

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.data.model.Category;
import uk.co.ribot.androidboilerplate.ui.base.BaseActivity;
import uk.co.ribot.androidboilerplate.ui.base.BaseFragment;
import uk.co.ribot.androidboilerplate.ui.category.subcategory.SubCategoryFragment;
import uk.co.ribot.androidboilerplate.util.DialogFactory;
import uk.co.ribot.androidboilerplate.util.RecyclerItemClickListener;

public class CategoryFragment extends BaseFragment implements CategoryMvpView {
    @Inject
    CategoryPresenter categoryPresenter;
    @Inject
    CategoryAdapter categoryAdapter;
    @BindView(R.id.category_listView)
    RecyclerView mRecyclerView;
    private List<Category> mCategories;

    public CategoryFragment() {
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
        final View view = inflater.inflate(R.layout.category_fragment, container, false);
        ButterKnife.bind(this, view);
        mRecyclerView.setAdapter(categoryAdapter);
        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setElevation(0);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
//        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
//            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);
//        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        categoryPresenter.attachView(this);
        categoryPresenter.getAllCategories(getContext());
        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // do whatever
                        Timber.d("paarent id %s", mCategories.get(position).getCategoryId());
                        showSubCategory(mCategories.get(position).getCategoryId(), mCategories.get(position).getName());
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
//

    @Override
    public void onDestroy() {
        super.onDestroy();
        categoryPresenter.detachView();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);
        }
    }

    private void showSubCategory(Integer categoryId, String categoryName) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment nextFrag = SubCategoryFragment.newInstance(categoryId, categoryName);
        transaction.add(R.id.container_category, nextFrag, SubCategoryFragment.class.getName());
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void showEmpty() {
        mRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showAllCategories(List<Category> categories) {
        Timber.d("categories %s", categories);
        mCategories = categories;
        mRecyclerView.setVisibility(View.VISIBLE);
        categoryAdapter.setCategories(getContext(), categories);
        categoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void showProgresBar(boolean b) {
        DialogFactory.createNormailProgressBar(getContext(), b);
    }
}
