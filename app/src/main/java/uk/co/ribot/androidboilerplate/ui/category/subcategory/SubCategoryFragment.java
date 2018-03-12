package uk.co.ribot.androidboilerplate.ui.category.subcategory;

import android.os.Bundle;
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
import uk.co.ribot.androidboilerplate.ui.category.CategoryAdapter;
import uk.co.ribot.androidboilerplate.ui.category.CategoryMvpView;
import uk.co.ribot.androidboilerplate.ui.category.CategoryPresenter;
import uk.co.ribot.androidboilerplate.util.RecyclerItemClickListener;

public class SubCategoryFragment extends BaseFragment implements CategoryMvpView {
    @Inject CategoryPresenter categoryPresenter;
    @Inject CategoryAdapter categoryAdapter;
    @BindView(R.id.sub_category_listView)
    RecyclerView mRecyclerView;
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
        mRecyclerView.setAdapter(categoryAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        categoryPresenter.attachView(this);
        categoryAdapter.setCategories(getCategories());
        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), mRecyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // do whatever
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
        categoryAdapter.notifyDataSetChanged();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        categoryPresenter.detachView();
    }

    private List<Category> getCategories(){
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(0,"Women"));
        categories.add(new Category(0,"Man"));
        categories.add(new Category(0,"HomeWear"));
        categories.add(new Category(0,"Kids"));

        return categories;
    }

}
