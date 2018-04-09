package uk.co.ribot.androidboilerplate.ui.category.subcategory.filter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.ribot.androidboilerplate.R;

public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.CategoryViewHolder> {

    private List<List<String>> filterDataList;
    private Context mcontext;
    private String filterType;
    HashMap<String, List<String>> filterHashMap;
    private String[] mKeys;

    @Inject
    public FilterAdapter() {
        filterHashMap = new HashMap<>();
    }

    protected void setData(Context context, HashMap<String, List<String>> filterHashMap) {
        this.filterHashMap = filterHashMap;
        this.mcontext = context;
        mKeys = filterHashMap.keySet().toArray(new String[filterHashMap.size()]);
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.filter_item_layout, parent, false);
        return new CategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final CategoryViewHolder holder, int position) {
        holder.textView.setText(mKeys[position]);
        holder.spinner.setItems(filterHashMap.get(mKeys[position]));
        holder.spinner.setHint(R.string.select_hint + "" + mKeys[position]);
    }

    @Override
    public int getItemCount() {
        return filterHashMap.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.filter_type)
        TextView textView;
        @BindView(R.id.filter_spinner)
        MaterialSpinner spinner;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
