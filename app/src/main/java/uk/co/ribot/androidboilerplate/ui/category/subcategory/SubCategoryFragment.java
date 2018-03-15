package uk.co.ribot.androidboilerplate.ui.category.subcategory;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.data.model.Category;
import uk.co.ribot.androidboilerplate.ui.base.BaseActivity;
import uk.co.ribot.androidboilerplate.ui.base.BaseFragment;
import uk.co.ribot.androidboilerplate.ui.category.CategoryAdapter;
import uk.co.ribot.androidboilerplate.ui.category.CategoryMvpView;
import uk.co.ribot.androidboilerplate.ui.category.CategoryPresenter;
import uk.co.ribot.androidboilerplate.ui.category.subcategory.products.ProductsFragment;
import uk.co.ribot.androidboilerplate.util.RecyclerItemClickListener;

public class SubCategoryFragment extends BaseFragment implements CategoryMvpView {
    @Inject CategoryPresenter categoryPresenter;
    @Inject SubCategoryMenuAdapter subCategoryMenuAdapter;
    @Inject GridViewRecyclerViewAdapter gridViewRecyclerViewAdapter;
    @BindView(R.id.sub_category_menu_RecyclerView)
    RecyclerView menuRecyclerView;
    @BindView(R.id.sub_cateory_details_RecyclerView)
    RecyclerView detailsRecyclerView;
    public SubCategoryFragment() {
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
        final View view = inflater.inflate(R.layout.sub_category_fragment, container, false);
        ButterKnife.bind(this,view);
        menuRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        categoryPresenter.attachView(this);
        menuRecyclerView.setAdapter(subCategoryMenuAdapter);
        subCategoryMenuAdapter.setCategories(getSubCategories());
        subCategoryMenuAdapter.notifyDataSetChanged();
        detailsRecyclerView.setAdapter(gridViewRecyclerViewAdapter);
        detailsRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        gridViewRecyclerViewAdapter.setData(getCategories());
        gridViewRecyclerViewAdapter.notifyDataSetChanged();
        menuRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), menuRecyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // do whatever
                        gridViewRecyclerViewAdapter.setData(getCategories());
                        gridViewRecyclerViewAdapter.notifyDataSetChanged();
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );

        detailsRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), detailsRecyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // do whatever
                        showProduct();
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        categoryPresenter.detachView();
    }

    private List<Category> getCategories(){
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(R.drawable.hejabipic,"Hejab"));
        categories.add(new Category(R.drawable.headbandspic,"Headbands"));
        categories.add(new Category(R.drawable.hejabipic,"Hejab"));
        categories.add(new Category(R.drawable.headbandspic,"Headbands"));
        return categories;
    }

    private List<String> getSubCategories(){
        List<String> strings = new ArrayList<>();
        strings.add("Clothes");
        strings.add("Dresses");
        strings.add("shoes");
        strings.add("Accessories");
        strings.add("Bags");
        return strings;
    }

    private void showProduct(){

        Fragment nextFrag= new ProductsFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_sub_category, nextFrag,"findThisFragment")
                .commit();
    }
}
