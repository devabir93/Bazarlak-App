
package uk.co.ribot.androidboilerplate.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class CategoriesData implements Parcelable
{

    @SerializedName("categories")
    @Expose
    private Categories categories;
    public final static Creator<CategoriesData> CREATOR = new Creator<CategoriesData>() {


        @SuppressWarnings({
            "unchecked"
        })
        public CategoriesData createFromParcel(Parcel in) {
            return new CategoriesData(in);
        }

        public CategoriesData[] newArray(int size) {
            return (new CategoriesData[size]);
        }

    }
    ;

    protected CategoriesData(Parcel in) {
        this.categories = ((Categories) in.readValue((Categories.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public CategoriesData() {
    }

    /**
     * 
     * @param categories
     */
    public CategoriesData(Categories categories) {
        super();
        this.categories = categories;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("categories", categories).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(categories).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof CategoriesData) == false) {
            return false;
        }
        CategoriesData rhs = ((CategoriesData) other);
        return new EqualsBuilder().append(categories, rhs.categories).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(categories);
    }

    public int describeContents() {
        return  0;
    }

}
