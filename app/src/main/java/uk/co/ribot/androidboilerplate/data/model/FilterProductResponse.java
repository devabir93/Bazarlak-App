
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

public class FilterProductResponse implements Parcelable
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
    @SerializedName("items")
    @Expose
    private List<Products> items = null;
    public final static Creator<FilterProductResponse> CREATOR = new Creator<FilterProductResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public FilterProductResponse createFromParcel(Parcel in) {
            return new FilterProductResponse(in);
        }

        public FilterProductResponse[] newArray(int size) {
            return (new FilterProductResponse[size]);
        }

    }
    ;

    protected FilterProductResponse(Parcel in) {
        this.status = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.code = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.items, (uk.co.ribot.androidboilerplate.data.model.Products.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public FilterProductResponse() {
    }

    /**
     * 
     * @param message
     * @param items
     * @param status
     * @param code
     */
    public FilterProductResponse(Boolean status, Integer code, String message, List<Products> items) {
        super();
        this.status = status;
        this.code = code;
        this.message = message;
        this.items = items;
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

    public List<Products> getItems() {
        return items;
    }

    public void setItems(List<Products> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("status", status).append("code", code).append("message", message).append("items", items).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(message).append(items).append(status).append(code).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof FilterProductResponse) == false) {
            return false;
        }
        FilterProductResponse rhs = ((FilterProductResponse) other);
        return new EqualsBuilder().append(message, rhs.message).append(items, rhs.items).append(status, rhs.status).append(code, rhs.code).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(code);
        dest.writeValue(message);
        dest.writeList(items);
    }

    public int describeContents() {
        return  0;
    }

}
