package uk.co.ribot.androidboilerplate.ui.category.subcategory;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.data.model.Extracategory;

/**
 * Created by Dev_Abir on 03/11/2018.
 */

public class GridViewRecyclerViewAdapter extends RecyclerView.Adapter<GridViewRecyclerViewAdapter.GridCategoryViewHolder> {


    private List<Extracategory> mCategories;
    private String mSubCategoryName;

    // data is passed into the constructor
    @Inject
    GridViewRecyclerViewAdapter() {
        mCategories = new ArrayList<>();
    }

    public void setData(String subCategoryName, List<Extracategory> categories) {
        mCategories = categories;
        mSubCategoryName = subCategoryName;
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
        if (mCategories.get(position).getCatId() == null) {
            holder.itemsLayout.setVisibility(View.GONE);
            holder.allCategory.setVisibility(View.VISIBLE);
            holder.allCategory.setText(holder.allCategory.getContext().getString(R.string.all_label, mSubCategoryName));
        } else {
            holder.itemsLayout.setVisibility(View.VISIBLE);
            holder.allCategory.setVisibility(View.GONE);
            Extracategory extracategory = mCategories.get(position);
            holder.ExtracategoryTextView.setText(extracategory.getName());
        if (extracategory.getImage() != null && !extracategory.getImage().isEmpty())
            Picasso.with(holder.ExtracategoryImageView.getContext()).load(extracategory.getImage()).into(holder.ExtracategoryImageView);
        }
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
        @BindView(R.id.Extracategory_imageView)
        ImageView ExtracategoryImageView;
        @BindView(R.id.Extracategory_textView)
        TextView ExtracategoryTextView;
        @BindView(R.id.items_layout)
        LinearLayout itemsLayout;
        @BindView(R.id.all_category)
        TextView allCategory;
        @BindView(R.id.card_view)
        CardView cardView;

        GridCategoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
