package uk.co.ribot.androidboilerplate.ui.category.subcategory;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.data.model.Extracategory;
import uk.co.ribot.androidboilerplate.data.model.Subcategory;
import uk.co.ribot.androidboilerplate.ui.base.BaseActivity;
import uk.co.ribot.androidboilerplate.ui.base.BaseFragment;
import uk.co.ribot.androidboilerplate.ui.category.subcategory.filter.FilterActivity;
import uk.co.ribot.androidboilerplate.ui.category.subcategory.products.ProductsFragment;
import uk.co.ribot.androidboilerplate.util.RecyclerItemClickListener;

public class SubCategoryFragment extends BaseFragment implements SubCategoryMvpView, SubCategoryMenuAdapter.UpdateDataClickListener {
    @Inject
    SubCategoryPresenter subCategoryPresenter;
    @Inject
    SubCategoryMenuAdapter subCategoryMenuAdapter;
    @Inject
    GridViewRecyclerViewAdapter gridViewRecyclerViewAdapter;
    @BindView(R.id.sub_category_menu_RecyclerView)
    RecyclerView menuRecyclerView;
    @BindView(R.id.sub_cateory_details_RecyclerView)
    RecyclerView detailsRecyclerView;
    @Nullable
    @BindView(R.id.second_toolbar)
    Toolbar secondToolbar;
    @BindView(R.id.empty_view)
    TextView emptyView;
    Unbinder unbinder;

    Integer mParentCategoryId;
    String mCategoryName, mExtracategoryName;
    String subCategoryID, ExtracategoryId;

    private List<Subcategory> mSubMenuCategories;
    private List<Extracategory> mExtrasubcategories;
    private String subCategoryName;

    public void setCategoryName(String categoryName) {
        this.mCategoryName = categoryName;
    }

    public void setParentCategoryId(Integer parentCategoryId) {
        this.mParentCategoryId = parentCategoryId;
    }

    public SubCategoryFragment() {
        // Required empty public constructor
    }

    @NonNull
    public static SubCategoryFragment newInstance(Integer mParentCategoryId, String categoryName) {
        SubCategoryFragment fragment = new SubCategoryFragment();
        fragment.setParentCategoryId(mParentCategoryId);
        fragment.setCategoryName(categoryName);
        return fragment;
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
        final View view = inflater.inflate(R.layout.sub_category_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        subCategoryPresenter.attachView(this);
        ((AppCompatActivity) getActivity()).setSupportActionBar(secondToolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView textView = (TextView) secondToolbar.findViewById(R.id.activity_name_textView_secondary);
        textView.setText(mCategoryName);
        subCategoryMenuAdapter.setOnItemClickListener(this);
        secondToolbar.setNavigationIcon(R.drawable.ic_back);
        secondToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = getActivity().getSupportFragmentManager().findFragmentByTag(SubCategoryFragment.class.getName());
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.remove(fragment).commit();
            }
        });
        subCategoryPresenter.getAllSubCategories(getContext(), mParentCategoryId);
        menuRecyclerView.setAdapter(subCategoryMenuAdapter);
        menuRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        detailsRecyclerView.setAdapter(gridViewRecyclerViewAdapter);
        detailsRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        detailsRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), detailsRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // do whatever
                        ExtracategoryId = String.valueOf(mExtrasubcategories.get(position).getExtracategoryId());
                        mExtracategoryName = mExtrasubcategories.get(position).getName();
                        showProduct(ExtracategoryId, mExtracategoryName);
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
    public void onDestroy() {
        super.onDestroy();
        subCategoryPresenter.detachView();
    }

    private void showProduct(String ExtracategoryId, String mExtracategoryName) {

        Fragment nextFrag = ProductsFragment.newInstance(String.valueOf(mParentCategoryId), mCategoryName, subCategoryID, mExtracategoryName, ExtracategoryId);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_sub_category, nextFrag, ProductsFragment.class.getName())
                .commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showEmpty() {
        emptyView.setVisibility(View.VISIBLE);
        detailsRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showAllCategories(List<Subcategory> subMenuCategories) {
        Timber.d("submenu %s", subMenuCategories);
        mSubMenuCategories = subMenuCategories;
        subCategoryMenuAdapter.setCategories(getContext(), subMenuCategories);
        if (mSubMenuCategories != null && mSubMenuCategories.size() > 0)
            subCategoryName = mSubMenuCategories.get(0).getName();
        subCategoryMenuAdapter.notifyDataSetChanged();
        onItemClick(0);
    }

    @Override
    public void showExtraSubCategories(List<Extracategory> extrasubcategories) {
        Timber.d("extrasubcategories %s", extrasubcategories);
        emptyView.setVisibility(View.GONE);
        detailsRecyclerView.setVisibility(View.VISIBLE);
        mExtrasubcategories = new ArrayList<>();
        mExtrasubcategories.add(0, new Extracategory());
        mExtrasubcategories.addAll(extrasubcategories);
        gridViewRecyclerViewAdapter.setData(subCategoryName, mExtrasubcategories);
        gridViewRecyclerViewAdapter.notifyDataSetChanged();
    }


    @Override
    public void onItemClick(int position) {
        subCategoryID = String.valueOf(mSubMenuCategories.get(position).getSubCategoryId());
         subCategoryPresenter.getExtraSubCategories(getContext(), subCategoryID);
        subCategoryMenuAdapter.selected(position);

/*        if (mSubMenuCategories.get(position).getExtracategory() != null) {

            Timber.d("extra %s" ,mSubMenuCategories.get(position).getExtracategory());
            emptyView.setVisibility(View.GONE);
            detailsRecyclerView.setVisibility(View.VISIBLE);
            mExtrasubcategories = new ArrayList<>();
            mExtrasubcategories.add(0, new Extracategory());
            mExtrasubcategories.addAll(mSubMenuCategories.get(position).getExtracategory());
            gridViewRecyclerViewAdapter.setCategoriesData(subCategoryName, mExtrasubcategories);
            gridViewRecyclerViewAdapter.notifyDataSetChanged();
        }*/
    }
}
