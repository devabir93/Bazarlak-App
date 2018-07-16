package uk.co.ribot.androidboilerplate.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

public class Order extends SugarRecord implements Parcelable {

    @SerializedName("product_id")
    @Expose
    private int productId;

    @SerializedName("feature_id")
    @Expose
    private int productFeature;
    @SerializedName("quantity")
    @Expose
    private String quantity;
    @SerializedName("size")
    @Expose
    private String size;
    @SerializedName("color")
    @Expose
    private String color;
    private Product product;

    public Order() {
    }

    protected Order(Parcel in) {
        productId = in.readInt();
        product = in.readParcelable(Product.class.getClassLoader());
        productFeature = in.readInt();
        quantity = in.readString();
        size = in.readString();
        color = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(productId);
        dest.writeParcelable(product, flags);
        dest.writeInt(productFeature);
        dest.writeString(quantity);
        dest.writeString(size);
        dest.writeString(color);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

    public Order(int productId, Product product, int productFeature, String quantity, String size, String color) {
        this.productId = productId;
        this.product = product;
        this.productFeature = productFeature;
        this.quantity = quantity;
        this.size = size;
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (productId != order.productId) return false;
        if (productFeature != order.productFeature) return false;
        if (product != null ? !product.equals(order.product) : order.product != null) return false;
        if (quantity != null ? !quantity.equals(order.quantity) : order.quantity != null)
            return false;
        if (size != null ? !size.equals(order.size) : order.size != null) return false;
        return color != null ? color.equals(order.color) : order.color == null;
    }

    @Override
    public int hashCode() {
        int result = productId;
        result = 31 * result + (product != null ? product.hashCode() : 0);
        result = 31 * result + productFeature;
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (size != null ? size.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        return result;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductFeature() {
        return productFeature;
    }

    public void setProductFeature(int productFeature) {
        this.productFeature = productFeature;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Order{" +
                "product=" + product +
                ", quantity='" + quantity + '\'' +
                ", size='" + size + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
