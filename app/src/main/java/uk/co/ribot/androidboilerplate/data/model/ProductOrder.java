package uk.co.ribot.androidboilerplate.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

public class ProductOrder extends SugarRecord implements Parcelable {

    @SerializedName("product_id")
    @Expose
    private int productId;
    @SerializedName("feature_id")
    @Expose
    private int productFeature;
    @SerializedName("quantity")
    @Expose
    private String quantity = "1";
    @SerializedName("size")
    @Expose
    private String size;
    @SerializedName("color")
    @Expose
    private String color;
    private Product product;

    public ProductOrder() {
    }

    protected ProductOrder(Parcel in) {
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

    public static final Creator<ProductOrder> CREATOR = new Creator<ProductOrder>() {
        @Override
        public ProductOrder createFromParcel(Parcel in) {
            return new ProductOrder(in);
        }

        @Override
        public ProductOrder[] newArray(int size) {
            return new ProductOrder[size];
        }
    };

    public ProductOrder(int productId, Product product, int productFeature, String quantity, String size, String color) {
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

        ProductOrder productOrder = (ProductOrder) o;

        if (productId != productOrder.productId) return false;
        if (productFeature != productOrder.productFeature) return false;
        if (product != null ? !product.equals(productOrder.product) : productOrder.product != null)
            return false;
        if (quantity != null ? !quantity.equals(productOrder.quantity) : productOrder.quantity != null)
            return false;
        if (size != null ? !size.equals(productOrder.size) : productOrder.size != null)
            return false;
        return color != null ? color.equals(productOrder.color) : productOrder.color == null;
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
        return "ProductOrder{" +
                "productId=" + productId +
                ", productFeature=" + productFeature +
                ", quantity='" + quantity + '\'' +
                ", size='" + size + '\'' +
                ", color='" + color + '\'' +
                ", product=" + product +
                '}';
    }
}
