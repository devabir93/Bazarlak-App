package uk.co.ribot.androidboilerplate.ui.category;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import uk.co.ribot.androidboilerplate.ui.category.subcategory.SubCategoryFragment;
import uk.co.ribot.androidboilerplate.util.RecyclerItemClickListener;

public class CategoryFragment extends BaseFragment implements CategoryMvpView {
    @Inject
    CategoryPresenter categoryPresenter;
    @Inject
    CategoryAdapter categoryAdapter;
    @BindView(R.id.category_listView)
    RecyclerView mRecyclerView;
    public CategoryFragment() {
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
        final View view = inflater.inflate(R.layout.category_fragment, container, false);
        ButterKnife.bind(this,view);
        mRecyclerView.setAdapter(categoryAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        categoryPresenter.attachView(this);
        categoryAdapter.setCategories(getCategories());
        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), mRecyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // do whatever
                        showSubCategory();
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
        categories.add(new Category(R.drawable.change_lang,"Women"));
        categories.add(new Category(R.drawable.contactusicon,"Man"));
        categories.add(new Category(R.drawable.inviteicon,"HomeWear"));
        categories.add(new Category(R.drawable.contactusicon,"Kids"));

        return categories;
    }

    private void showSubCategory(){
//        Intent i = new Intent(getActivity(), SubCategoryeActivity.class);
//        startActivity(i);
        Fragment nextFrag= new SubCategoryFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, nextFrag,"findThisFragment")
                .commit();
    }

}
