package uk.co.ribot.androidboilerplate.ui.category.subcategory.products;

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

/**
 * Created by Dev_Abir on 03/11/2018.
 */

public class ProductsGridViewRecyclerViewAdapter extends RecyclerView.Adapter<ProductsGridViewRecyclerViewAdapter.GridCategoryViewHolder> {

    private List<Category> mCategories;

    // data is passed into the constructor
    @Inject
    ProductsGridViewRecyclerViewAdapter() {
        mCategories = new ArrayList<>();
    }

    public void setData(List<Category> categories) {
        mCategories = categories;
    }

    // inflates the cell layout from xml when needed
    @Override
    public GridCategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.products_item, parent, false);
        return new GridCategoryViewHolder(itemView);
    }

    // binds the data to the textview in each cell
    @Override
    public void onBindViewHolder(GridCategoryViewHolder holder, int position) {
        Category category = mCategories.get(position);
//        int width = (holder.productInfotextView.getContext().getResources().getDisplayMetrics().widthPixels) / 2;
//        int height = (holder.productInfotextView.getContext().getResources().getDisplayMetrics().heightPixels) / 4;
//        holder.imageView.setMinimumWidth(width);
//        holder.imageView.setMinimumHeight(height);
//        holder.imageView.setMaxWidth(width);
//        holder.imageView.setMaxHeight(height);
        if (category.getImg() == 0)
            holder.productImageView.setImageResource(R.color.placeholder_background);
        else
            holder.productImageView.setImageResource(category.getImg());




    }

    // total number of cells
    @Override
    public int getItemCount() {
        return mCategories.size();
    }


    // stores and recycles views as they are scrolled off screen
    class GridCategoryViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.product_imageView)
        ImageView productImageView;
        @BindView(R.id.price_textView)
        TextView priceTextView;
        @BindView(R.id.product_info_textView)
        TextView prodcutInfoTextView;

        GridCategoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
