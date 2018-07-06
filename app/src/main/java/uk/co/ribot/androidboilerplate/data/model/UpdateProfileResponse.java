
package uk.co.ribot.androidboilerplate.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class UpdateProfileResponse implements Parcelable
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
    private UpdateProfileData data;
    public final static Creator<UpdateProfileResponse> CREATOR = new Creator<UpdateProfileResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public UpdateProfileResponse createFromParcel(Parcel in) {
            return new UpdateProfileResponse(in);
        }

        public UpdateProfileResponse[] newArray(int size) {
            return (new UpdateProfileResponse[size]);
        }

    }
    ;

    protected UpdateProfileResponse(Parcel in) {
        this.status = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.code = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.data = ((UpdateProfileData) in.readValue((UpdateProfileData.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public UpdateProfileResponse() {
    }

    /**
     * 
     * @param message
     * @param status
     * @param data
     * @param code
     */
    public UpdateProfileResponse(Boolean status, Integer code, String message, UpdateProfileData data) {
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

    public UpdateProfileData getData() {
        return data;
    }

    public void setData(UpdateProfileData data) {
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
        if ((other instanceof UpdateProfileResponse) == false) {
            return false;
        }
        UpdateProfileResponse rhs = ((UpdateProfileResponse) other);
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
