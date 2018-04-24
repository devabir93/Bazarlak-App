
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

public class ProductResponse implements Parcelable
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
    private List<Product> productData = null;
    public final static Creator<ProductResponse> CREATOR = new Creator<ProductResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public ProductResponse createFromParcel(Parcel in) {
            return new ProductResponse(in);
        }

        public ProductResponse[] newArray(int size) {
            return (new ProductResponse[size]);
        }

    }
    ;

    protected ProductResponse(Parcel in) {
        this.status = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.code = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.productData, (Product.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public ProductResponse() {
    }

    /**
     * 
     * @param message
     * @param status
     * @param productData
     * @param code
     */
    public ProductResponse(Boolean status, Integer code, String message, List<Product> productData) {
        super();
        this.status = status;
        this.code = code;
        this.message = message;
        this.productData = productData;
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

    public List<Product> getProductData() {
        return productData;
    }

    public void setProductData(List<Product> productData) {
        this.productData = productData;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("status", status).append("code", code).append("message", message).append("productData", productData).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(message).append(status).append(productData).append(code).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ProductResponse) == false) {
            return false;
        }
        ProductResponse rhs = ((ProductResponse) other);
        return new EqualsBuilder().append(message, rhs.message).append(status, rhs.status).append(productData, rhs.productData).append(code, rhs.code).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(code);
        dest.writeValue(message);
        dest.writeList(productData);
    }

    public int describeContents() {
        return  0;
    }

}
