
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
    private CategoriesData categoriesData;
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
        this.categoriesData = ((CategoriesData) in.readValue((CategoriesData.class.getClassLoader())));
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
     * @param categoriesData
     * @param code
     */
    public CategoryResponse(Boolean status, Integer code, String message, CategoriesData categoriesData) {
        super();
        this.status = status;
        this.code = code;
        this.message = message;
        this.categoriesData = categoriesData;
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

    public CategoriesData getCategoriesData() {
        return categoriesData;
    }

    public void setCategoriesData(CategoriesData categoriesData) {
        this.categoriesData = categoriesData;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("status", status).append("code", code).append("message", message).append("categoriesData", categoriesData).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(message).append(status).append(categoriesData).append(code).toHashCode();
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
        return new EqualsBuilder().append(message, rhs.message).append(status, rhs.status).append(categoriesData, rhs.categoriesData).append(code, rhs.code).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(code);
        dest.writeValue(message);
        dest.writeValue(categoriesData);
    }

    public int describeContents() {
        return  0;
    }

}
