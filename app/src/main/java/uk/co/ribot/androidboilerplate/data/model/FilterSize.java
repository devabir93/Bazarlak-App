
package uk.co.ribot.androidboilerplate.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class FilterSize implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer sizeId;
    @SerializedName("name")
    @Expose
    private String name;
    public final static Creator<FilterSize> CREATOR = new Creator<FilterSize>() {


        @SuppressWarnings({
            "unchecked"
        })
        public FilterSize createFromParcel(Parcel in) {
            return new FilterSize(in);
        }

        public FilterSize[] newArray(int size) {
            return (new FilterSize[size]);
        }

    }
    ;

    protected FilterSize(Parcel in) {
        this.sizeId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public FilterSize() {
    }

    /**
     * 
     * @param id
     * @param name
     */
    public FilterSize(Integer id, String name) {
        super();
        this.sizeId = id;
        this.name = name;
    }

    public Integer getSizeId() {
        return sizeId;
    }

    public void setSizeId(Integer id) {
        this.sizeId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("sizeId", sizeId).append("name", name).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(sizeId).append(name).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof FilterSize)) {
            return false;
        }
        FilterSize rhs = ((FilterSize) other);
        return new EqualsBuilder().append(sizeId, rhs.sizeId).append(name, rhs.name).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(sizeId);
        dest.writeValue(name);
    }

    public int describeContents() {
        return  0;
    }

}
