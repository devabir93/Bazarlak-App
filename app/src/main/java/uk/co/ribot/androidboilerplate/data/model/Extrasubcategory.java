
package uk.co.ribot.androidboilerplate.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Extrasubcategory extends SugarRecord implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer extraSubCategoryId;
    @SerializedName("cat_id")
    @Expose
    private String catId;
    @SerializedName("subcategory_id")
    @Expose
    private String subcategoryId;
    @SerializedName("name")
    @Expose
    private String name;

    public Extrasubcategory() {
    }

    protected Extrasubcategory(Parcel in) {
        if (in.readByte() == 0) {
            extraSubCategoryId = null;
        } else {
            extraSubCategoryId = in.readInt();
        }
        catId = in.readString();
        subcategoryId = in.readString();
        name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (extraSubCategoryId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(extraSubCategoryId);
        }
        dest.writeString(catId);
        dest.writeString(subcategoryId);
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Extrasubcategory> CREATOR = new Creator<Extrasubcategory>() {
        @Override
        public Extrasubcategory createFromParcel(Parcel in) {
            return new Extrasubcategory(in);
        }

        @Override
        public Extrasubcategory[] newArray(int size) {
            return new Extrasubcategory[size];
        }
    };

    public Integer getExtraSubCategoryId() {
        return extraSubCategoryId;
    }

    public void setExtraSubCategoryId(Integer extraSubCategoryId) {
        this.extraSubCategoryId = extraSubCategoryId;
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
        return new ToStringBuilder(this).append("extraSubCategoryId", extraSubCategoryId).append("catId", catId).append("subcategoryId", subcategoryId).append("name", name).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(subcategoryId).append(extraSubCategoryId).append(name).append(catId).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Extrasubcategory)) {
            return false;
        }
        Extrasubcategory rhs = ((Extrasubcategory) other);
        return new EqualsBuilder().append(subcategoryId, rhs.subcategoryId).append(extraSubCategoryId, rhs.extraSubCategoryId).append(name, rhs.name).append(catId, rhs.catId).isEquals();
    }

}
