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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.data.model.Category;
import uk.co.ribot.androidboilerplate.data.model.Extrasubcategory;
import uk.co.ribot.androidboilerplate.data.model.Subcategory;
import uk.co.ribot.androidboilerplate.ui.base.BaseActivity;
import uk.co.ribot.androidboilerplate.ui.base.BaseFragment;
import uk.co.ribot.androidboilerplate.ui.category.subcategory.filter.FilterActivity;
import uk.co.ribot.androidboilerplate.ui.category.subcategory.products.ProductsFragment;
import uk.co.ribot.androidboilerplate.util.RecyclerItemClickListener;

public class SubCategoryFragment extends BaseFragment implements SubCategoryMvpView {
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
    Unbinder unbinder;

    Integer mParentCategoryId;
    String mCategoryName;

    private List<Subcategory> mSubMenuCategories;
    private List<Extrasubcategory> mExtrasubcategories;

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
        TextView textView = (TextView) secondToolbar.findViewById(R.id.activity_name_textView);
        textView.setText(mCategoryName);
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
        menuRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), menuRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // do whatever
                        subCategoryPresenter.getExtraSubCategories(getContext(), String.valueOf(mSubMenuCategories.get(position).getSubCategoryId()));

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );

        detailsRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), detailsRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // do whatever
                        showProduct();
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        getActivity().getMenuInflater().inflate(R.menu.menu_second_toolbar, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ic_filter_action:
                getActivity().startActivity(new Intent(getActivity(), FilterActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        subCategoryPresenter.detachView();
    }

    private List<Category> getCategories() {
        List<Category> categories = new ArrayList<>();
//        categories.add(new Category(R.drawable.hejabipic, "Hejab"));
//        categories.add(new Category(R.drawable.headbandspic, "Headbands"));
//        categories.add(new Category(R.drawable.hejabipic, "Hejab"));
//        categories.add(new Category(R.drawable.headbandspic, "Headbands"));
        return categories;
    }

    private List<String> getSubCategories() {
        List<String> strings = new ArrayList<>();
        strings.add("Clothes");
        strings.add("Dresses");
        strings.add("shoes");
        strings.add("Accessories");
        strings.add("Bags");
        return strings;
    }

    private void showProduct() {

        Fragment nextFrag = ProductsFragment.newInstance("10", "Women", "25", "Tops", "1");
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

    }

    @Override
    public void showAllCategories(List<Subcategory> subMenuCategories) {
        Timber.d("submenu %s", subMenuCategories);
        mSubMenuCategories = subMenuCategories;
        subCategoryMenuAdapter.setCategories(subMenuCategories);
        subCategoryMenuAdapter.notifyDataSetChanged();
    }

    @Override
    public void showExtraSubCategories(List<Extrasubcategory> extrasubcategories) {
        Timber.d("extrasubcategories %s", extrasubcategories);
        mExtrasubcategories = extrasubcategories;
        gridViewRecyclerViewAdapter.setData(extrasubcategories);
        gridViewRecyclerViewAdapter.notifyDataSetChanged();
    }

    public void showFilter() {
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(getActivity());
        Dialog d = builderSingle.setView(new View(getActivity())).create();
        // (That new View is just there to have something inside the dialog that can grow big enough to cover the whole screen.)

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(d.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        d.show();
        d.getWindow().setAttributes(lp);
        //builderSingle.setTitle(Html.fromHtml(getString(R.string.dialog_incomplete_questions_title)));
        LayoutInflater factory = LayoutInflater.from(getActivity());
        View content = factory.inflate(R.layout.layout_filter, null);
        //ListView lv = (ListView) content.findViewById(R.id._dialog_list);
        //TextView tv = (TextView) content.findViewById(R.id.dialog_message);
        //tv.setText(getResources().getString(R.string.dialog_incomplete_questions_message, invalidAnswerList.size()));
//        builderSingle.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(@NonNull DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
        builderSingle.setView(content);
        final AlertDialog alertDialogObject = builderSingle.create();
        // Here you can change the layout direction via setLayoutDirection()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            alertDialogObject.getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LOCALE);
        }
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
//                alertDialogObject.dismiss();
//            }
//        });
        alertDialogObject.show();
    }

}
