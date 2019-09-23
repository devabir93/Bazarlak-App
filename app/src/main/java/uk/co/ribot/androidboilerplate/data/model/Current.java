
package uk.co.ribot.androidboilerplate.data.model;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Current implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("quantity")
    @Expose
    private String quantity;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("response")
    @Expose
    private Object response;
    @SerializedName("products")
    @Expose
    private List<Order> Orders = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Current() {
    }

    /**
     * 
     * @param response
     * @param id
     * @param price
     * @param status
     * @param createdAt
     * @param userId
     * @param quantity
     * @param Orders
     */
    public Current(Integer id, String userId, String price, String quantity, String status, String createdAt, Object response, List<Order> Orders) {
        super();
        this.id = id;
        this.userId = userId;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
        this.createdAt = createdAt;
        this.response = response;
        this.Orders = Orders;
    }

    protected Current(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        userId = in.readString();
        price = in.readString();
        quantity = in.readString();
        status = in.readString();
        createdAt = in.readString();
        Orders = in.createTypedArrayList(Order.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(userId);
        dest.writeString(price);
        dest.writeString(quantity);
        dest.writeString(status);
        dest.writeString(createdAt);
        dest.writeTypedList(Orders);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Current> CREATOR = new Creator<Current>() {
        @Override
        public Current createFromParcel(Parcel in) {
            return new Current(in);
        }

        @Override
        public Current[] newArray(int size) {
            return new Current[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    public List<Order> getOrders() {
        return Orders;
    }

    public void setOrders(List<Order> Orders) {
        this.Orders = Orders;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("userId", userId).append("price", price).append("quantity", quantity).append("status", status).append("createdAt", createdAt).append("response", response).append("Orders", Orders).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(response).append(id).append(price).append(status).append(createdAt).append(userId).append(quantity).append(Orders).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Current) == false) {
            return false;
        }
        Current rhs = ((Current) other);
        return new EqualsBuilder().append(response, rhs.response).append(id, rhs.id).append(price, rhs.price).append(status, rhs.status).append(createdAt, rhs.createdAt).append(userId, rhs.userId).append(quantity, rhs.quantity).append(Orders, rhs.Orders).isEquals();
    }

}
