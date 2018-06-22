package uk.co.ribot.androidboilerplate.ui.profile;

import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.data.model.Category;
import uk.co.ribot.androidboilerplate.data.model.CustomCategory;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder> {

    private List<CustomCategory> mCategories;

    @Inject
    public ProfileAdapter() {
        mCategories = new ArrayList<>();
    }

    public void setCategories(List<CustomCategory> categories) {
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
        CustomCategory category = mCategories.get(position);
        if (category.getIcon() == -1) {
            holder.imageView.setVisibility(View.GONE);
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
//            params.gravity = Gravity.CENTER_HORIZONTAL;
//
//            holder.linearLayout.setLayoutParams(params);
            holder.linearLayout.setGravity(Gravity.CENTER_HORIZONTAL);
        } else
        holder.imageView.setImageResource(category.getIcon());
        holder.textView.setText(category.getTitle());
    }

    @Override
    public int getItemCount() {
        return mCategories.size();
    }

    class ProfileViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.icon_profile)
        ImageView imageView;
        @BindView(R.id.text_name) TextView textView;
        @BindView(R.id.items_layout)
        LinearLayout linearLayout;

        public ProfileViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
