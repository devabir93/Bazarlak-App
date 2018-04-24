package uk.co.ribot.androidboilerplate.ui.category;

import android.content.Context;
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
import uk.co.ribot.androidboilerplate.data.remote.BazarlakService;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private List<Category> mCategories;
    private Context mcontext;

    @Inject
    public CategoryAdapter() {
        mCategories = new ArrayList<>();
    }

    public void setCategories(Context context, List<Category> categories) {
        mCategories = categories;
        mcontext = context;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final CategoryViewHolder holder, int position) {
        Category category = mCategories.get(position);
        holder.textView.setText(category.getName());
        if (category.getImage() != null && !category.getImage().isEmpty())
            Picasso.with(mcontext).load(category.getImage()).into(holder.imageView);

        // holder.imageView.setImageResource(category.getImg());
    }

    @Override
    public int getItemCount() {
        return mCategories.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.category_name)
        TextView textView;
        @BindView(R.id.icon_category)
        ImageView imageView;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
