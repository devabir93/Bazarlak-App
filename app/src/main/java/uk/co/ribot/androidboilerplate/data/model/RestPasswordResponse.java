
package uk.co.ribot.androidboilerplate.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class RestPasswordResponse implements Parcelable
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
    public final static Creator<RestPasswordResponse> CREATOR = new Creator<RestPasswordResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public RestPasswordResponse createFromParcel(Parcel in) {
            return new RestPasswordResponse(in);
        }

        public RestPasswordResponse[] newArray(int size) {
            return (new RestPasswordResponse[size]);
        }

    }
    ;

    protected RestPasswordResponse(Parcel in) {
        this.status = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.code = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public RestPasswordResponse() {
    }

    /**
     * 
     * @param message
     * @param status
     * @param code
     */
    public RestPasswordResponse(Boolean status, Integer code, String message) {
        super();
        this.status = status;
        this.code = code;
        this.message = message;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("status", status).append("code", code).append("message", message).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(message).append(status).append(code).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof RestPasswordResponse) == false) {
            return false;
        }
        RestPasswordResponse rhs = ((RestPasswordResponse) other);
        return new EqualsBuilder().append(message, rhs.message).append(status, rhs.status).append(code, rhs.code).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(code);
        dest.writeValue(message);
    }

    public int describeContents() {
        return  0;
    }

}
