
package uk.co.ribot.androidboilerplate.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class MyorderResponse implements Parcelable
{

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("MyOrders")
    @Expose
    private MyOrders myOrders;
    public final static Creator<MyorderResponse> CREATOR = new Creator<MyorderResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public MyorderResponse createFromParcel(Parcel in) {
            return new MyorderResponse(in);
        }

        public MyorderResponse[] newArray(int size) {
            return (new MyorderResponse[size]);
        }

    }
    ;

    protected MyorderResponse(Parcel in) {
        this.status = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.code = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.myOrders = ((MyOrders) in.readValue((MyOrders.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public MyorderResponse() {
    }

    /**
     * 
     * @param message
     * @param status
     * @param code
     * @param myOrders
     */
    public MyorderResponse(Boolean status, Integer code, String message, MyOrders myOrders) {
        super();
        this.status = status;
        this.code = code;
        this.message = message;
        this.myOrders = myOrders;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MyOrders getMyOrders() {
        return myOrders;
    }

    public void setMyOrders(MyOrders myOrders) {
        this.myOrders = myOrders;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("status", status).append("code", code).append("message", message).append("myOrders", myOrders).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(message).append(status).append(code).append(myOrders).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof MyorderResponse) == false) {
            return false;
        }
        MyorderResponse rhs = ((MyorderResponse) other);
        return new EqualsBuilder().append(message, rhs.message).append(status, rhs.status).append(code, rhs.code).append(myOrders, rhs.myOrders).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(code);
        dest.writeValue(message);
        dest.writeValue(myOrders);
    }

    public int describeContents() {
        return  0;
    }

}
