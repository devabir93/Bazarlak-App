package uk.co.ribot.androidboilerplate.ui.profile.your_order;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import uk.co.ribot.androidboilerplate.data.model.ProductOrder;
import uk.co.ribot.androidboilerplate.data.model.Product;

public class YourOrdersAdapter extends RecyclerView.Adapter<YourOrdersAdapter.CategoryViewHolder> {



    private List<ProductOrder> productOrders;
    private Context mcontext;

    @Inject
    public YourOrdersAdapter() {
        productOrders = new ArrayList<>();
    }

    public void setProducts(Context context, List<ProductOrder> products) {
        productOrders = products;
        mcontext = context;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_item, parent, false);
        return new CategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final CategoryViewHolder holder, int position) {
        Product product = productOrders.get(position).getProduct();
        holder.brandName.setText(product.getName());
        holder.priceTextView.setText(String.format("%s", product.getPrice()));
        holder.itemType.setText(product.getDescription());
        holder.itemSize.setText(productOrders.get(position).getSize());
        holder.qytTextView.setText(productOrders.get(position).getQuantity());

        if (product.getImage() != null && !product.getImage().isEmpty())
            Picasso.with(mcontext).load(product.getImage()).into(holder.bagImageView);
        // holder.imageView.setImageResource(category.getImg());
    }

    @Override
    public int getItemCount() {
        return productOrders.size();
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
        @BindView(R.id.qyt_textView)
        TextView qytTextView;
        @BindView(R.id.price_textView)
        TextView priceTextView;
        @BindView(R.id.order_no_textview)
        TextView orderNoTextview;
        @BindView(R.id.order_status_textview)
        TextView orderStatusTextview;
        @BindView(R.id.items_layout)
        RelativeLayout itemsLayout;


        public CategoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    interface UpdateDataClickListener {
        void oncheckOrder(List<ProductOrder> products);
    }
}
