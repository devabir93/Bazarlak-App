package uk.co.ribot.androidboilerplate.ui.category.subcategory;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.data.model.Category;
import uk.co.ribot.androidboilerplate.ui.category.CategoryAdapter;

/**
 * Created by Dev_Abir on 03/11/2018.
 */

public class GridViewRecyclerViewAdapter extends RecyclerView.Adapter<GridViewRecyclerViewAdapter.GridCategoryViewHolder> {

    private List<Category> mCategories;

    // data is passed into the constructor
    @Inject
    GridViewRecyclerViewAdapter() {
        mCategories = new ArrayList<>();
    }

    public void setData(List<Category> categories) {
        mCategories=categories;
    }

    // inflates the cell layout from xml when needed
    @Override
    public GridCategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.gridview_sub_categories_item, parent, false);
        return new GridCategoryViewHolder(itemView);
    }

    // binds the data to the textview in each cell
    @Override
    public void onBindViewHolder(GridCategoryViewHolder holder, int position) {
        Category category = mCategories.get(position);
        holder.imageView.setImageResource(category.getImg());
        holder.productInfotextView.setText(category.getName());
    }

    // total number of cells
    @Override
    public int getItemCount() {
        return mCategories.size();
    }


    // stores and recycles views as they are scrolled off screen
    class GridCategoryViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.roundedImageView)
        ImageView imageView;
        @BindView(R.id.textView)
        TextView productInfotextView;

        GridCategoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
