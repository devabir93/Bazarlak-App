package uk.co.ribot.androidboilerplate.ui.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.data.model.Offerproduct;

/**
 * Created by Dev_Abir on 03/11/2018.
 */

public class HomeGridViewAdapter extends RecyclerView.Adapter<HomeGridViewAdapter.ViewHolder> {

    private List<Offerproduct> mOfferproducts;
    private Context mContext;

    // data is passed into the constructor
    @Inject
    HomeGridViewAdapter() {
        mOfferproducts = new ArrayList<>();
    }

    public void setOfferProductData(Context context, List<Offerproduct> productList) {
        mOfferproducts = productList;
        mContext = context;
    }

    // inflates the cell layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.homepage_photos_item, parent, false);
        return new ViewHolder(itemView);
    }

    // binds the data to the textview in each cell
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Offerproduct product = mOfferproducts.get(position);
        if (product.getImage() != null && !product.getImage().isEmpty())
            Picasso.with(mContext).load(product.getImage()).into(holder.offerProductImageView);
    }

    // total number of cells
    @Override
    public int getItemCount() {
        return mOfferproducts.size();
    }


    // stores and recycles views as they are scrolled off screen
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.offer_product_ImageView)
        ImageView offerProductImageView;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
