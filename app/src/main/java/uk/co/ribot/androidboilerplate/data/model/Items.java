
package uk.co.ribot.androidboilerplate.data.model;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Items implements Parcelable
{

    @SerializedName("size")
    @Expose
    private List<String> filterSize = null;
    @SerializedName("brand")
    @Expose
    private List<Brand> brand = null;
    public final static Creator<Items> CREATOR = new Creator<Items>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Items createFromParcel(Parcel in) {
            return new Items(in);
        }

        public Items[] newArray(int size) {
            return (new Items[size]);
        }

    }
    ;

    protected Items(Parcel in) {
        in.readList(this.filterSize, (String.class.getClassLoader()));
        in.readList(this.brand, (Brand.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Items() {
    }

    /**
     * 
     * @param brand
     * @param filterSize
     */
    public Items(List<String> filterSize, List<Brand> brand) {
        super();
        this.filterSize = filterSize;
        this.brand = brand;
    }

    public List<String> getFilterSize() {
        return filterSize;
    }

    public void setFilterSize(List<String> filterSize) {
        this.filterSize = filterSize;
    }

    public List<Brand> getBrand() {
        return brand;
    }

    public void setBrand(List<Brand> brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("filterSize", filterSize).append("brand", brand).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(brand).append(filterSize).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Items) == false) {
            return false;
        }
        Items rhs = ((Items) other);
        return new EqualsBuilder().append(brand, rhs.brand).append(filterSize, rhs.filterSize).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(filterSize);
        dest.writeList(brand);
    }

    public int describeContents() {
        return  0;
    }

}
