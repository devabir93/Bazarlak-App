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
import timber.log.Timber;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.data.model.Current;
import uk.co.ribot.androidboilerplate.data.model.Order;
import uk.co.ribot.androidboilerplate.data.model.Product;
import uk.co.ribot.androidboilerplate.data.model.ProductOrder;

public class YourOrdersAdapter extends RecyclerView.Adapter<YourOrdersAdapter.CategoryViewHolder> {



    private List<Current> productOrders;
    private Context mcontext;
    private boolean isArabic;
    @Inject
    public YourOrdersAdapter() {
        productOrders = new ArrayList<>();
    }

    public void setProducts(Context context, List<Current> products) {
        Timber.d("setProducts %s",products);
        productOrders = products;
        mcontext = context;
    }
    public void setIsArabic(boolean arabic) {
        this.isArabic = arabic;
    }
    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_item, parent, false);
        Timber.d("onCreateViewHolder");
        return new CategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        Timber.d("onBindViewHolder");
        Current order = productOrders.get(position);
        Order product = order.getOrders().get(0);
        holder.brandName.setText(isArabic?product.getNameAr():product.getNameEn());
        holder.priceTextView.setText(String.format("%s", product.getPrice()+" "+mcontext.getString(R.string.currency)));
        holder.itemType.setText(product.getDetails());
        holder.itemSize.setText(mcontext.getString(R.string.item_size,product.getSize()));
        holder.orderStatusTextview.setText(mcontext.getString(R.string.order_status, order.getStatus()));
        holder.orderNoTextview.setText(mcontext.getString(R.string.order_no,String.valueOf(order.getId())));
        holder.qytTextView.setText(mcontext.getString(R.string.quntitye_label,order.getQuantity()));

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
}
