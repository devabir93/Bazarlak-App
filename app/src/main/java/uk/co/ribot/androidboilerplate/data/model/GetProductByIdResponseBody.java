
package uk.co.ribot.androidboilerplate.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class GetProductByIdResponseBody implements Parcelable
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
    private ByIdData byIdData;
    public final static Creator<GetProductByIdResponseBody> CREATOR = new Creator<GetProductByIdResponseBody>() {


        @SuppressWarnings({
            "unchecked"
        })
        public GetProductByIdResponseBody createFromParcel(Parcel in) {
            return new GetProductByIdResponseBody(in);
        }

        public GetProductByIdResponseBody[] newArray(int size) {
            return (new GetProductByIdResponseBody[size]);
        }

    }
    ;

    protected GetProductByIdResponseBody(Parcel in) {
        this.status = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.code = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.byIdData = ((ByIdData) in.readValue((ByIdData.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public GetProductByIdResponseBody() {
    }

    /**
     * 
     * @param message
     * @param byIdData
     * @param status
     * @param code
     */
    public GetProductByIdResponseBody(Boolean status, Integer code, String message, ByIdData byIdData) {
        super();
        this.status = status;
        this.code = code;
        this.message = message;
        this.byIdData = byIdData;
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

    public ByIdData getByIdData() {
        return byIdData;
    }

    public void setByIdData(ByIdData byIdData) {
        this.byIdData = byIdData;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("status", status).append("code", code).append("message", message).append("byIdData", byIdData).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(message).append(byIdData).append(status).append(code).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof GetProductByIdResponseBody) == false) {
            return false;
        }
        GetProductByIdResponseBody rhs = ((GetProductByIdResponseBody) other);
        return new EqualsBuilder().append(message, rhs.message).append(byIdData, rhs.byIdData).append(status, rhs.status).append(code, rhs.code).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(code);
        dest.writeValue(message);
        dest.writeValue(byIdData);
    }

    public int describeContents() {
        return  0;
    }

}
