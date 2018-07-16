package uk.co.ribot.androidboilerplate.ui.category.subcategory.products;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.jaredrummler.materialspinner.MaterialSpinnerAdapter;
import com.jaredrummler.materialspinner.MaterialSpinnerBaseAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;
import uk.co.ribot.androidboilerplate.R;

public class SpinnerAdapter extends MaterialSpinnerAdapter<String> {

    private List<String> mItems;
    private Context mContext;
    LayoutInflater flater;

    public SpinnerAdapter(Context context, List<String> objects) {
        super(context,objects);
        Timber.d("objects %s", objects);
        mItems = objects;
        mContext = context;
        // inflter = (LayoutInflater.from(context));
    }

    //    @NonNull
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//        return rowview(convertView, position,parent);
//    }
//
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = new TextView(mContext);
        int red = Color.parseColor(mItems.get(position));
        textView.setBackgroundColor(red);
        textView.setPadding(0,4,0,4);


        return textView;
    }

    @Override
    public String getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public String get(int position) {
        return mItems.get(position);
    }

    @Override
    public List getItems() {
        return mItems;
    }


}
