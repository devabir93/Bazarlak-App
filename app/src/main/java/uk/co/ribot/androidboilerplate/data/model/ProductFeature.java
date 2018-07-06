
package uk.co.ribot.androidboilerplate.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ProductFeature implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("product_id")
    @Expose
    private String productId;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("size")
    @Expose
    private String size;
    @SerializedName("dimensions")
    @Expose
    private Object dimensions;
    @SerializedName("quentity")
    @Expose
    private String quentity;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("offer_price")
    @Expose
    private Object offerPrice;
    @SerializedName("day_deliverd")
    @Expose
    private Object dayDeliverd;
    @SerializedName("status")
    @Expose
    private String status;
    public final static Creator<ProductFeature> CREATOR = new Creator<ProductFeature>() {


        @SuppressWarnings({
            "unchecked"
        })
        public ProductFeature createFromParcel(Parcel in) {
            return new ProductFeature(in);
        }

        public ProductFeature[] newArray(int size) {
            return (new ProductFeature[size]);
        }

    }
    ;

    protected ProductFeature(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.productId = ((String) in.readValue((String.class.getClassLoader())));
        this.color = ((String) in.readValue((String.class.getClassLoader())));
        this.size = ((String) in.readValue((String.class.getClassLoader())));
        this.dimensions = ((Object) in.readValue((Object.class.getClassLoader())));
        this.quentity = ((String) in.readValue((String.class.getClassLoader())));
        this.price = ((String) in.readValue((String.class.getClassLoader())));
        this.offerPrice = ((Object) in.readValue((Object.class.getClassLoader())));
        this.dayDeliverd = ((Object) in.readValue((Object.class.getClassLoader())));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public ProductFeature() {
    }

    /**
     * 
     * @param id
     * @param price
     * @param status
     * @param color
     * @param offerPrice
     * @param quentity
     * @param dimensions
     * @param dayDeliverd
     * @param size
     * @param productId
     */
    public ProductFeature(Integer id, String productId, String color, String size, Object dimensions, String quentity, String price, Object offerPrice, Object dayDeliverd, String status) {
        super();
        this.id = id;
        this.productId = productId;
        this.color = color;
        this.size = size;
        this.dimensions = dimensions;
        this.quentity = quentity;
        this.price = price;
        this.offerPrice = offerPrice;
        this.dayDeliverd = dayDeliverd;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
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

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("productId", productId).append("color", color).append("size", size).append("dimensions", dimensions).append("quentity", quentity).append("price", price).append("offerPrice", offerPrice).append("dayDeliverd", dayDeliverd).append("status", status).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(price).append(status).append(color).append(offerPrice).append(quentity).append(dimensions).append(dayDeliverd).append(size).append(productId).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ProductFeature) == false) {
            return false;
        }
        ProductFeature rhs = ((ProductFeature) other);
        return new EqualsBuilder().append(id, rhs.id).append(price, rhs.price).append(status, rhs.status).append(color, rhs.color).append(offerPrice, rhs.offerPrice).append(quentity, rhs.quentity).append(dimensions, rhs.dimensions).append(dayDeliverd, rhs.dayDeliverd).append(size, rhs.size).append(productId, rhs.productId).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(productId);
        dest.writeValue(color);
        dest.writeValue(size);
        dest.writeValue(dimensions);
        dest.writeValue(quentity);
        dest.writeValue(price);
        dest.writeValue(offerPrice);
        dest.writeValue(dayDeliverd);
        dest.writeValue(status);
    }

    public int describeContents() {
        return  0;
    }

}
