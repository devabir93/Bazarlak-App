package uk.co.ribot.androidboilerplate.ui.profile;

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

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder> {

    private List<Category> mCategories;

    @Inject
    public ProfileAdapter() {
        mCategories = new ArrayList<>();
    }

    public void setCategories(List<Category> categories) {
        mCategories = categories;
    }

    @Override
    public ProfileAdapter.ProfileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_profile, parent, false);
        return new ProfileAdapter.ProfileViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ProfileAdapter.ProfileViewHolder holder, int position) {
        Category category = mCategories.get(position);
//        if (category.getImg() == -1) {
//            holder.imageView.setVisibility(View.GONE);
//        } else
//        holder.imageView.setImageResource(category.getImg());
        holder.textView.setText(category.getName());
    }

    @Override
    public int getItemCount() {
        return mCategories.size();
    }

    class ProfileViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.icon_profile)
        ImageView imageView;
        @BindView(R.id.text_name) TextView textView;

        public ProfileViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
