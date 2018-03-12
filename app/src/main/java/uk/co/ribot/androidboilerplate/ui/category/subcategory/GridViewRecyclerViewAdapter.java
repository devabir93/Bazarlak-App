package uk.co.ribot.androidboilerplate.ui.category.subcategory;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.data.model.Category;

/**
 * Created by Dev_Abir on 03/11/2018.
 */

    public class GridViewRecyclerViewAdapter extends RecyclerView.Adapter<GridViewRecyclerViewAdapter.ViewHolder> {

        private List<Category> mCategories;
        private LayoutInflater mInflater;
        private ItemClickListener mClickListener;

        // data is passed into the constructor
        @Inject
        GridViewRecyclerViewAdapter(Context context, List<Category> categories) {
            this.mInflater = LayoutInflater.from(context);
            this.mCategories = categories;
        }

        // inflates the cell layout from xml when needed
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.gridview_sub_categories_item, parent, false);
            return new ViewHolder(view);
        }

        // binds the data to the textview in each cell
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Category category = mCategories.get(position);
            holder.imageView.setImageResource(category.getImg());
        }

        // total number of cells
        @Override
        public int getItemCount() {
            return mCategories.size();
        }


        // stores and recycles views as they are scrolled off screen
        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            @BindView(R.id.roundedImageView) ImageView imageView;

            ViewHolder(View itemView) {
                super(itemView);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
            }
        }

        // allows clicks events to be caught
        void setClickListener(ItemClickListener itemClickListener) {
            this.mClickListener = itemClickListener;
        }

        // parent activity will implement this method to respond to click events
        public interface ItemClickListener {
            void onItemClick(View view, int position);
        }
    }
