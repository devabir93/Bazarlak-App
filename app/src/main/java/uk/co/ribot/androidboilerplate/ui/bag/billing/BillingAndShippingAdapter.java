package uk.co.ribot.androidboilerplate.ui.bag.billing;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.data.model.Product;
import uk.co.ribot.androidboilerplate.data.model.ProductOrder;

public class BillingAndShippingAdapter extends RecyclerView.Adapter<BillingAndShippingAdapter.CategoryViewHolder> {


    private List<ProductOrder> mProducts;
    private List<ProductOrder> savedProductOrders;
    private Context mcontext;

    @Inject
    public BillingAndShippingAdapter() {
        mProducts = new ArrayList<>();
        savedProductOrders = new ArrayList<>();
    }

    public void setProducts(Context context, List<ProductOrder> products) {
        mProducts = products;
        mcontext = context;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items_to_buy, parent, false);
        return new CategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final CategoryViewHolder holder, int position) {
        Product product = mProducts.get(position).getProduct();
        if (product != null) {

            holder.brandName.setText(product.getName());
            holder.priceTextView.setText(String.format("%s", product.getPrice()));
            holder.itemType.setText(product.getDescription());
            String size = String.format(mcontext.getResources().getString(R.string.item_size), mProducts.get(position).getSize());
            holder.itemSize.setText(size);

            if (product.getImage() != null && !product.getImage().isEmpty())
                Picasso.with(mcontext).load(product.getImage()).into(holder.bagImageView);
        }
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.bag_imageView)
        ImageView bagImageView;
        @BindView(R.id.brand_name)
        TextView brandName;
        @BindView(R.id.item_type)
        TextView itemType;
        @BindView(R.id.item_size)
        TextView itemSize;
        @BindView(R.id.price_textView)
        TextView priceTextView;
        @BindView(R.id.items_layout)
        RelativeLayout itemsLayout;


        public CategoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
