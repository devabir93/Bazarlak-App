package uk.co.ribot.androidboilerplate.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Order implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("order_id")
    @Expose
    private String orderId;
    @SerializedName("product_id")
    @Expose
    private String productId;
    @SerializedName("productbrand")
    @Expose
    private String productbrand;
    @SerializedName("feature_id")
    @Expose
    private String featureId;
    @SerializedName("size")
    @Expose
    private String size;
    @SerializedName("dimension")
    @Expose
    private Object dimension;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("quantity")
    @Expose
    private String quantity;
    @SerializedName("details")
    @Expose
    private String details;
    @SerializedName("name_en")
    @Expose
    private String nameEn;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("name_ar")
    @Expose
    private String nameAr;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Order() {
    }

    /**
     * 
     * @param id
     * @param dimension
     * @param price
     * @param details
     * @param productbrand
     * @param color
     * @param nameEn
     * @param nameAr
     * @param featureId
     * @param image
     * @param quantity
     * @param orderId
     * @param size
     * @param productId
     */
    public Order(Integer id, String orderId, String productId, String productbrand, String featureId, String size, Object dimension, String color, String price, String quantity, String details, String nameEn, String image, String nameAr) {
        super();
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.productbrand = productbrand;
        this.featureId = featureId;
        this.size = size;
        this.dimension = dimension;
        this.color = color;
        this.price = price;
        this.quantity = quantity;
        this.details = details;
        this.nameEn = nameEn;
        this.image = image;
        this.nameAr = nameAr;
    }

    protected Order(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        orderId = in.readString();
        productId = in.readString();
        productbrand = in.readString();
        featureId = in.readString();
        size = in.readString();
        color = in.readString();
        price = in.readString();
        quantity = in.readString();
        details = in.readString();
        nameEn = in.readString();
        image = in.readString();
        nameAr = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(orderId);
        dest.writeString(productId);
        dest.writeString(productbrand);
        dest.writeString(featureId);
        dest.writeString(size);
        dest.writeString(color);
        dest.writeString(price);
        dest.writeString(quantity);
        dest.writeString(details);
        dest.writeString(nameEn);
        dest.writeString(image);
        dest.writeString(nameAr);
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductbrand() {
        return productbrand;
    }

    public void setProductbrand(String productbrand) {
        this.productbrand = productbrand;
    }

    public String getFeatureId() {
        return featureId;
    }

    public void setFeatureId(String featureId) {
        this.featureId = featureId;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Object getDimension() {
        return dimension;
    }

    public void setDimension(Object dimension) {
        this.dimension = dimension;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNameAr() {
        return nameAr;
    }

    public void setNameAr(String nameAr) {
        this.nameAr = nameAr;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("orderId", orderId).append("productId", productId).append("productbrand", productbrand).append("featureId", featureId).append("size", size).append("dimension", dimension).append("color", color).append("price", price).append("quantity", quantity).append("details", details).append("nameEn", nameEn).append("image", image).append("nameAr", nameAr).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(nameEn).append(nameAr).append(image).append(size).append(productId).append(id).append(dimension).append(price).append(details).append(productbrand).append(color).append(featureId).append(quantity).append(orderId).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Product) == false) {
            return false;
        }
        Order rhs = ((Order) other);
        return new EqualsBuilder().append(nameEn, rhs.nameEn).append(nameAr, rhs.nameAr).append(image, rhs.image).append(size, rhs.size).append(productId, rhs.productId).append(id, rhs.id).append(dimension, rhs.dimension).append(price, rhs.price).append(details, rhs.details).append(productbrand, rhs.productbrand).append(color, rhs.color).append(featureId, rhs.featureId).append(quantity, rhs.quantity).append(orderId, rhs.orderId).isEquals();
    }

}
