
package uk.co.ribot.androidboilerplate.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Extracategory extends SugarRecord implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer ExtracategoryId;
    @SerializedName("cat_id")
    @Expose
    private String catId;
    @SerializedName("subcategory_id")
    @Expose
    private String subcategoryId;
    @SerializedName("name")
    @Expose
    private String name;

    public Extracategory() {
    }

    protected Extracategory(Parcel in) {
        if (in.readByte() == 0) {
            ExtracategoryId = null;
        } else {
            ExtracategoryId = in.readInt();
        }
        catId = in.readString();
        subcategoryId = in.readString();
        name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (ExtracategoryId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(ExtracategoryId);
        }
        dest.writeString(catId);
        dest.writeString(subcategoryId);
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Extracategory> CREATOR = new Creator<Extracategory>() {
        @Override
        public Extracategory createFromParcel(Parcel in) {
            return new Extracategory(in);
        }

        @Override
        public Extracategory[] newArray(int size) {
            return new Extracategory[size];
        }
    };

    public Integer getExtracategoryId() {
        return ExtracategoryId;
    }

    public void setExtracategoryId(Integer ExtracategoryId) {
        this.ExtracategoryId = ExtracategoryId;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getSubcategoryId() {
        return subcategoryId;
    }

    public void setSubcategoryId(String subcategoryId) {
        this.subcategoryId = subcategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("ExtracategoryId", ExtracategoryId).append("catId", catId).append("subcategoryId", subcategoryId).append("name", name).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(subcategoryId).append(ExtracategoryId).append(name).append(catId).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Extracategory)) {
            return false;
        }
        Extracategory rhs = ((Extracategory) other);
        return new EqualsBuilder().append(subcategoryId, rhs.subcategoryId).append(ExtracategoryId, rhs.ExtracategoryId).append(name, rhs.name).append(catId, rhs.catId).isEquals();
    }

}
