package uk.co.ribot.androidboilerplate.ui.profile;

import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
    private boolean isLogged;

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
        if (category.getIcon() == -1 && isLogged) {
            holder.imageView.setVisibility(View.INVISIBLE);
            holder.imageView_go.setVisibility(View.VISIBLE);
            RelativeLayout.LayoutParams layoutParams =
                    (RelativeLayout.LayoutParams) holder.textView.getLayoutParams();
            layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
            holder.textView.setLayoutParams(layoutParams);
        } else if (category.getIcon() != -1) {
            holder.imageView.setVisibility(View.VISIBLE);
            holder.imageView_go.setVisibility(View.INVISIBLE);
            holder.imageView.setImageResource(category.getIcon());
        }
        holder.textView.setText(category.getTitle());
    }

    @Override
    public int getItemCount() {
        return mCategories.size();
    }

    public void isLogged(boolean b) {
        this.isLogged = b;
    }

    class ProfileViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.icon_profile)
        ImageView imageView;
        @BindView(R.id.icon_go)
        ImageView imageView_go;
        @BindView(R.id.text_name)
        TextView textView;
        @BindView(R.id.items_layout)
        LinearLayout linearLayout;

        public ProfileViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
