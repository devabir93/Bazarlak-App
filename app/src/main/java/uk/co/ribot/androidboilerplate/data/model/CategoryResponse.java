
package uk.co.ribot.androidboilerplate.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class CategoryResponse implements Parcelable
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
    @SerializedName("data")
    @Expose
    private Data data;
    public final static Creator<CategoryResponse> CREATOR = new Creator<CategoryResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public CategoryResponse createFromParcel(Parcel in) {
            return new CategoryResponse(in);
        }

        public CategoryResponse[] newArray(int size) {
            return (new CategoryResponse[size]);
        }

    }
    ;

    protected CategoryResponse(Parcel in) {
        this.status = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.code = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.data = ((Data) in.readValue((Data.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public CategoryResponse() {
    }

    /**
     * 
     * @param message
     * @param status
     * @param data
     * @param code
     */
    public CategoryResponse(Boolean status, Integer code, String message, Data data) {
        super();
        this.status = status;
        this.code = code;
        this.message = message;
        this.data = data;
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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("status", status).append("code", code).append("message", message).append("data", data).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(message).append(status).append(data).append(code).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof CategoryResponse) == false) {
            return false;
        }
        CategoryResponse rhs = ((CategoryResponse) other);
        return new EqualsBuilder().append(message, rhs.message).append(status, rhs.status).append(data, rhs.data).append(code, rhs.code).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(code);
        dest.writeValue(message);
        dest.writeValue(data);
    }

    public int describeContents() {
        return  0;
    }

}
