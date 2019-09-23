
package uk.co.ribot.androidboilerplate.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class CartData {

    @SerializedName("product_id")
    @Expose
    private Integer productId;
    @SerializedName("feature_id")
    @Expose
    private Integer featureId;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;

    /**
     * No args constructor for use in serialization
     */
    public CartData() {
    }

    /**
     * @param featureId
     * @param quantity
     * @param productId
     */
    public CartData(Integer productId, Integer featureId, Integer quantity) {
        super();
        this.productId = productId;
        this.featureId = featureId;
        this.quantity = quantity;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getFeatureId() {
        return featureId;
    }

    public void setFeatureId(Integer featureId) {
        this.featureId = featureId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "{" +
                "\"product_id\" =" + productId +
                ",\"feature_id\"=" + featureId +
                ",\"quantity\"=" + quantity +
                '}';
    }
}
