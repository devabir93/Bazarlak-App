package uk.co.ribot.androidboilerplate.ui.category.subcategory.products;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.data.model.Category;
import uk.co.ribot.androidboilerplate.ui.base.BaseActivity;
import uk.co.ribot.androidboilerplate.ui.base.BaseFragment;
import uk.co.ribot.androidboilerplate.ui.category.CategoryMvpView;
import uk.co.ribot.androidboilerplate.ui.category.CategoryPresenter;
import uk.co.ribot.androidboilerplate.ui.category.subcategory.GridViewRecyclerViewAdapter;
import uk.co.ribot.androidboilerplate.ui.category.subcategory.SubCategoryMenuAdapter;
import uk.co.ribot.androidboilerplate.util.RecyclerItemClickListener;

public class ProductsFragment extends BaseFragment implements ProductsMvpView {
    @Inject ProductsPresenter productsPresenter;
    @Inject ProductsGridViewRecyclerViewAdapter gridViewRecyclerViewAdapter;
    @BindView(R.id.products_recyclerView)
    RecyclerView productsRecyclerView;

    public ProductsFragment() {
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
        final View view = inflater.inflate(R.layout.prodcuts_fragment, container, false);
        ButterKnife.bind(this,view);
        productsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        productsPresenter.attachView(this);
        productsRecyclerView.setAdapter(gridViewRecyclerViewAdapter);
        productsRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        gridViewRecyclerViewAdapter.setData(getCategories());
        gridViewRecyclerViewAdapter.notifyDataSetChanged();
        productsRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), productsRecyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // do whatever
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
        productsPresenter.detachView();
    }

    private List<Category> getCategories(){
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(R.drawable.hejabipic,"Hejab"));
        categories.add(new Category(R.drawable.headbandspic,"Headbands"));
        categories.add(new Category(R.drawable.hejabipic,"Hejab"));
        categories.add(new Category(R.drawable.headbandspic,"Headbands"));
        return categories;
    }


}
