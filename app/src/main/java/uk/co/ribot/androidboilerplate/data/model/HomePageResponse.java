
package uk.co.ribot.androidboilerplate.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class HomePageResponse implements Parcelable
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
    private HomePageData homePageData;
    public final static Creator<HomePageResponse> CREATOR = new Creator<HomePageResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public HomePageResponse createFromParcel(Parcel in) {
            return new HomePageResponse(in);
        }

        public HomePageResponse[] newArray(int size) {
            return (new HomePageResponse[size]);
        }

    }
    ;

    protected HomePageResponse(Parcel in) {
        this.status = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.code = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.homePageData = ((HomePageData) in.readValue((HomePageData.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public HomePageResponse() {
    }

    /**
     * 
     * @param message
     * @param status
     * @param homePageData
     * @param code
     */
    public HomePageResponse(Boolean status, Integer code, String message, HomePageData homePageData) {
        super();
        this.status = status;
        this.code = code;
        this.message = message;
        this.homePageData = homePageData;
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

    public HomePageData getHomePageData() {
        return homePageData;
    }

    public void setHomePageData(HomePageData homePageData) {
        this.homePageData = homePageData;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("status", status).append("code", code).append("message", message).append("homePageData", homePageData).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(message).append(status).append(homePageData).append(code).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof HomePageResponse) == false) {
            return false;
        }
        HomePageResponse rhs = ((HomePageResponse) other);
        return new EqualsBuilder().append(message, rhs.message).append(status, rhs.status).append(homePageData, rhs.homePageData).append(code, rhs.code).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(code);
        dest.writeValue(message);
        dest.writeValue(homePageData);
    }

    public int describeContents() {
        return  0;
    }

}
