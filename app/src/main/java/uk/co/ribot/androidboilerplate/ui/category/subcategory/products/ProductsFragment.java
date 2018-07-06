package uk.co.ribot.androidboilerplate.ui.category.subcategory.products;

import android.app.Dialog;
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
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.data.model.Product;
import uk.co.ribot.androidboilerplate.data.model.ProductBody;
import uk.co.ribot.androidboilerplate.ui.base.BaseActivity;
import uk.co.ribot.androidboilerplate.ui.base.BaseFragment;
import uk.co.ribot.androidboilerplate.util.RecyclerItemClickListener;

public class ProductsFragment extends BaseFragment implements ProductsMvpView {
    @Inject
    ProductsPresenter productsPresenter;
    @Inject
    ProductsGridViewRecyclerViewAdapter gridViewRecyclerViewAdapter;
    @BindView(R.id.products_recyclerView)
    RecyclerView productsRecyclerView;
    @Nullable
    @BindView(R.id.second_toolbar)
    Toolbar secondToolbar;
    Unbinder unbinder;
    String categoryId="";
    String subCategoryId="";
    String ExtracategoryId="";
    String categoryName, ExtracategoryName;
    private List<Product> mproductList;

    public ProductsFragment() {
        // Required empty public constructor
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public void setSubCategoryId(String subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public void setExtracategoryId(String ExtracategoryId) {
        this.ExtracategoryId = ExtracategoryId;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setExtracategoryName(String ExtracategoryName) {
        this.ExtracategoryName = ExtracategoryName;
    }

    @NonNull
    public static ProductsFragment newInstance(String categoryId, String categoryName, String subCategoryId, String ExtracategoryName, String ExtracategoryId) {
        ProductsFragment fragment = new ProductsFragment();
        fragment.setCategoryId(categoryId);
        fragment.setSubCategoryId(subCategoryId);
        fragment.setExtracategoryId(ExtracategoryId);
        fragment.setCategoryName(categoryName);
        fragment.setExtracategoryName(ExtracategoryName);
        return fragment;
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
        ButterKnife.bind(this, view);
        productsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        productsPresenter.attachView(this);
        ((AppCompatActivity) getActivity()).setSupportActionBar(secondToolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView textView = (TextView) secondToolbar.findViewById(R.id.activity_name_textView_secondary);
        secondToolbar.setNavigationIcon(R.drawable.ic_back);
        secondToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = getActivity().getSupportFragmentManager().findFragmentByTag(ProductsFragment.class.getName());
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.remove(fragment).commit();
            }
        });
        secondToolbar.inflateMenu(R.menu.menu_second_toolbar);//changed
        //toolbar2 menu items CallBack listener
        secondToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem arg0) {
                if(arg0.getItemId() == R.id.ic_filter_action){
                    showFilter();
                }
                return false;
            }
        });
        textView.setText(categoryName + "/" + ExtracategoryName);
        ProductBody productBody = new ProductBody();
        productBody.setCategory(categoryId);
        productBody.setSubcategory(subCategoryId);
        productBody.setExtracategory(ExtracategoryId);
        productsPresenter.getProducts(getContext(), categoryId, subCategoryId, ExtracategoryId, "");
        productsRecyclerView.setAdapter(gridViewRecyclerViewAdapter);
        productsRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        productsRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), productsRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
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


//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.menu_second_toolbar, menu);
//        super.onCreateOptionsMenu(menu, inflater);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        switch (id) {
//            case R.id.ic_filter_action:
//                // do stuff, like showing settings fragment
//                showFilter();
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    private void showProductDetails(Product product) {
        Timber.d("product %s", product);
        Fragment nextFrag = ProductsDetailsFragment.newInstance(product);

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_fragment, nextFrag, ProductsDetailsFragment.class.getName())
                .commit();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        productsPresenter.detachView();
    }

    @Override
    public void showProducts(List<Product> productList) {
        mproductList = productList;
        gridViewRecyclerViewAdapter.setProductData(getContext(), productList);
        gridViewRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void addedToBag(boolean b) {

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
