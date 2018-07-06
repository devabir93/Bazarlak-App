
package uk.co.ribot.androidboilerplate.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ProductData implements Parcelable
{

    @SerializedName("products")
    @Expose
    private Products products;
    public final static Creator<ProductData> CREATOR = new Creator<ProductData>() {


        @SuppressWarnings({
            "unchecked"
        })
        public ProductData createFromParcel(Parcel in) {
            return new ProductData(in);
        }

        public ProductData[] newArray(int size) {
            return (new ProductData[size]);
        }

    }
    ;

    protected ProductData(Parcel in) {
        this.products = ((Products) in.readValue((Products.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public ProductData() {
    }

    /**
     * 
     * @param products
     */
    public ProductData(Products products) {
        super();
        this.products = products;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("products", products).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(products).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ProductData) == false) {
            return false;
        }
        ProductData rhs = ((ProductData) other);
        return new EqualsBuilder().append(products, rhs.products).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(products);
    }

    public int describeContents() {
        return  0;
    }

}
