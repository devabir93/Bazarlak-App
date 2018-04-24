package uk.co.ribot.androidboilerplate.ui.category.subcategory;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.data.model.Subcategory;

public class SubCategoryMenuAdapter extends RecyclerView.Adapter<SubCategoryMenuAdapter.CategoryViewHolder> {

    private List<Subcategory> mCategories;
    Context context;
    private static SparseBooleanArray sSelectedItems;
    private static UpdateDataClickListener sClickListener;
    private int sPosition;
    private int selectedItem;
    boolean isSelected = false;

    @Inject
    public SubCategoryMenuAdapter() {
        mCategories = new ArrayList<>();
        sSelectedItems = new SparseBooleanArray();
    }

    public void setCategories(Context context, List<Subcategory> categories) {
        mCategories = categories;
        this.context = context;
        selectedItem = 0;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sub_category, parent, false);
        return new CategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final CategoryViewHolder holder, int position) {

        holder.textView.setText(mCategories.get(position).getName());
        if (selectedItem == position && !isSelected) {
            sSelectedItems.put(selectedItem, false);
//            holder.cardView.setSelected(true);
            holder.textView.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
            holder.textView.setTextColor(context.getResources().getColor(R.color.white));
        } else {
//            holder.cardView.setSelected(false);
            if (sSelectedItems.get(position)) {
                Timber.d("pressed");
                holder.textView.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
                holder.textView.setTextColor(context.getResources().getColor(R.color.white));
            } else {
                holder.textView.setBackgroundColor(context.getResources().getColor(R.color.app_menu_grey));
                holder.textView.setTextColor(context.getResources().getColor(R.color.app_menu_txt_color));
            }
        }
        holder.cardView.setSelected(sSelectedItems.get(position, false));
    }

    @Override
    public int getItemCount() {
        return mCategories.size();
    }

    void setOnItemClickListener(UpdateDataClickListener clickListener) {
        sClickListener = clickListener;
    }

    public void selected(int position) {
        int previousItem = selectedItem;

        sPosition = position;
        notifyDataSetChanged();
        notifyItemChanged(previousItem);
        notifyItemChanged(position);
    }


    class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.category_name)
        TextView textView;
        @BindView(R.id.card_view)
        CardView cardView;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int currentPosition = getAdapterPosition();
            isSelected = true;
            if (sSelectedItems.get(getAdapterPosition(), false)) {
                sSelectedItems.delete(getAdapterPosition());
                cardView.setSelected(false);
                textView.setTextColor(ContextCompat.getColor(context, R.color.app_menu_txt_color));
                textView.setBackgroundColor(ContextCompat.getColor(context, R.color.app_menu_grey));
            } else {
                textView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
                sSelectedItems.put(sPosition, false);
                textView.setTextColor(ContextCompat.getColor(context, R.color.white));
                sSelectedItems.put(getAdapterPosition(), true);
                cardView.setSelected(true);
            }
            sClickListener.onItemClick(getAdapterPosition());
        }
    }

    interface UpdateDataClickListener {
        void onItemClick(int position);
    }
}
