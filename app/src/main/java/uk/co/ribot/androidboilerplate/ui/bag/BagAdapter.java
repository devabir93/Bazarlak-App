package uk.co.ribot.androidboilerplate.ui.bag;

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
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.refactor.library.SmoothCheckBox;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.data.model.Order;
import uk.co.ribot.androidboilerplate.data.model.Product;
import uk.co.ribot.androidboilerplate.data.remote.BazarlakService;
import uk.co.ribot.androidboilerplate.ui.category.subcategory.SubCategoryMenuAdapter;

public class BagAdapter extends RecyclerView.Adapter<BagAdapter.CategoryViewHolder> {


    private List<Order> mProducts;
    private List<Order> savedOrders;
    private Context mcontext;
    UpdateDataClickListener updateDataClickListener;

    @Inject
    public BagAdapter() {
        mProducts = new ArrayList<>();
        savedOrders = new ArrayList<>();
    }

    public void setProducts(Context context, UpdateDataClickListener updateDataClickListener, List<Order> products) {
        mProducts = products;
        mcontext = context;
        this.updateDataClickListener = updateDataClickListener;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bag_item, parent, false);
        return new CategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final CategoryViewHolder holder, int position) {
        Product product = mProducts.get(position).getProduct();
        holder.brandName.setText(product.getName());
        holder.priceTextView.setText(String.format("%s", product.getPrice()));
        holder.itemType.setText(product.getDescription());
        holder.itemSize.setText(mProducts.get(position).getSize());
        holder.itemQyn.setText(mProducts.get(position).getQuantity());
        holder.itemQyn.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    // do your stuff here
                    mProducts.get(holder.getAdapterPosition()).setQuantity(holder.itemQyn.getText().toString());
                    Log.d("quantity %s", holder.itemQyn.getText().toString());
                    holder.itemQyn.setCursorVisible(false);
                }
                return false;
            }
        });
        holder.checkBox.setOnCheckedChangeListener(new SmoothCheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SmoothCheckBox checkBox, boolean isChecked) {
                if (isChecked) {
                    if (!savedOrders.contains(mProducts.get(holder.getAdapterPosition())))
                        savedOrders.add(mProducts.get(holder.getAdapterPosition()));
                } else
                    savedOrders.remove(mProducts.get(holder.getAdapterPosition()));

                updateDataClickListener.oncheckOrder(savedOrders);
            }
        });
        if (product.getImage() != null && !product.getImage().isEmpty())
            Picasso.with(mcontext).load(product.getImage()).into(holder.bagImageView);
        // holder.imageView.setImageResource(category.getImg());
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.checkBox)
        SmoothCheckBox checkBox;
        @BindView(R.id.bag_imageView)
        ImageView bagImageView;
        @BindView(R.id.brand_name)
        TextView brandName;
        @BindView(R.id.item_type)
        TextView itemType;
        @BindView(R.id.item_size)
        TextView itemSize;
        @BindView(R.id.item_qyn)
        EditText itemQyn;
        @BindView(R.id.price_textView)
        TextView priceTextView;


        public CategoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    interface UpdateDataClickListener {
        void oncheckOrder(List<Order> products);
    }
}
