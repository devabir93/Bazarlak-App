package uk.co.ribot.androidboilerplate.ui.category.subcategory;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.data.model.Category;
import uk.co.ribot.androidboilerplate.data.model.Extrasubcategory;
import uk.co.ribot.androidboilerplate.data.remote.BazarlakService;

/**
 * Created by Dev_Abir on 03/11/2018.
 */

public class GridViewRecyclerViewAdapter extends RecyclerView.Adapter<GridViewRecyclerViewAdapter.GridCategoryViewHolder> {

    private List<Extrasubcategory> mCategories;

    // data is passed into the constructor
    @Inject
    GridViewRecyclerViewAdapter() {
        mCategories = new ArrayList<>();
    }

    public void setData(List<Extrasubcategory> categories) {
        mCategories = categories;
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
        Extrasubcategory category = mCategories.get(position);
        holder.productInfotextView.setText(category.getName());
//        if (category.get() != null && !category.getImage().isEmpty())
//            Picasso.with(mcontext).load(BazarlakService.IMAGE_URL+category.getImage()).into(holder.imageView);
//        int width = (holder.productInfotextView.getContext().getResources().getDisplayMetrics().widthPixels) / 2;
//        int height = (holder.productInfotextView.getContext().getResources().getDisplayMetrics().heightPixels) / 4;
//        holder.imageView.setMinimumWidth(width);
//        holder.imageView.setMinimumHeight(height);
//        holder.imageView.setMaxWidth(width);
//        holder.imageView.setMaxHeight(height);
//        if (category.getImg() == 0)
//            holder.imageView.setImageResource(R.color.placeholder_background);
//        else
//            holder.imageView.setImageResource(category.getImg());


    }

    // total number of cells
    @Override
    public int getItemCount() {
        return mCategories.size();
    }


    // stores and recycles views as they are scrolled off screen
    class GridCategoryViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.extraSubCategory_imageView)
        ImageView imageView;
        @BindView(R.id.extraSubCategory_textView)
        TextView productInfotextView;

        GridCategoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
