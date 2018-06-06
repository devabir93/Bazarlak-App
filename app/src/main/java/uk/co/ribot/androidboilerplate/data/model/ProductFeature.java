
package uk.co.ribot.androidboilerplate.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ProductFeature extends SugarRecord implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer ProductFeatureId;
    @SerializedName("product_id")
    @Expose
    private String productId;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("dimensions")
    @Expose
    private Object dimensions;
    @SerializedName("quentity")
    @Expose
    private String quentity;
    @SerializedName("price")
    @Expose
    private Object price;
    @SerializedName("offer_price")
    @Expose
    private Object offerPrice;
    @SerializedName("day_deliverd")
    @Expose
    private Object dayDeliverd;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("size")
    @Expose
    private String size;

    public ProductFeature() {
    }

    protected ProductFeature(Parcel in) {
        if (in.readByte() == 0) {
            ProductFeatureId = null;
        } else {
            ProductFeatureId = in.readInt();
        }
        productId = in.readString();
        color = in.readString();
        quentity = in.readString();
        status = in.readString();
        size = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (ProductFeatureId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(ProductFeatureId);
        }
        dest.writeString(productId);
        dest.writeString(color);
        dest.writeString(quentity);
        dest.writeString(status);
        dest.writeString(size);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ProductFeature> CREATOR = new Creator<ProductFeature>() {
        @Override
        public ProductFeature createFromParcel(Parcel in) {
            return new ProductFeature(in);
        }

        @Override
        public ProductFeature[] newArray(int size) {
            return new ProductFeature[size];
        }
    };

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Object getDimensions() {
        return dimensions;
    }

    public void setDimensions(Object dimensions) {
        this.dimensions = dimensions;
    }

    public String getQuentity() {
        return quentity;
    }

    public void setQuentity(String quentity) {
        this.quentity = quentity;
    }

    public Object getPrice() {
        return price;
    }

    public void setPrice(Object price) {
        this.price = price;
    }

    public Object getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(Object offerPrice) {
        this.offerPrice = offerPrice;
    }

    public Object getDayDeliverd() {
        return dayDeliverd;
    }

    public void setDayDeliverd(Object dayDeliverd) {
        this.dayDeliverd = dayDeliverd;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("ProductFeatureId", ProductFeatureId).append("productId", productId).append("color", color).append("dimensions", dimensions).append("quentity", quentity).append("price", price).append("offerPrice", offerPrice).append("dayDeliverd", dayDeliverd).append("status", status).append("size", size).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(price).append(status).append(color).append(offerPrice).append(quentity).append(dimensions).append(dayDeliverd).append(size).append(productId).append(ProductFeatureId).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof ProductFeature)) {
            return false;
        }
        ProductFeature rhs = ((ProductFeature) other);
        return new EqualsBuilder().append(price, rhs.price).append(status, rhs.status).append(color, rhs.color).append(offerPrice, rhs.offerPrice).append(quentity, rhs.quentity).append(dimensions, rhs.dimensions).append(dayDeliverd, rhs.dayDeliverd).append(size, rhs.size).append(productId, rhs.productId).append(ProductFeatureId, rhs.ProductFeatureId).isEquals();
    }

    public Integer getProductFeatureId() {
        return ProductFeatureId;
    }

    public void setProductFeatureId(Integer ProductFeatureId) {
        this.ProductFeatureId = ProductFeatureId;
    }
}
