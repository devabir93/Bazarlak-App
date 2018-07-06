
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

public class ByIdData implements Parcelable
{

    @SerializedName("product")
    @Expose
    private List<Product> product = null;
    public final static Creator<ByIdData> CREATOR = new Creator<ByIdData>() {


        @SuppressWarnings({
            "unchecked"
        })
        public ByIdData createFromParcel(Parcel in) {
            return new ByIdData(in);
        }

        public ByIdData[] newArray(int size) {
            return (new ByIdData[size]);
        }

    }
    ;

    protected ByIdData(Parcel in) {
        in.readList(this.product, (Product.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public ByIdData() {
    }

    /**
     * 
     * @param product
     */
    public ByIdData(List<Product> product) {
        super();
        this.product = product;
    }

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("product", product).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(product).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ByIdData) == false) {
            return false;
        }
        ByIdData rhs = ((ByIdData) other);
        return new EqualsBuilder().append(product, rhs.product).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(product);
    }

    public int describeContents() {
        return  0;
    }

}
