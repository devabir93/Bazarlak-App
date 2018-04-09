
package uk.co.ribot.androidboilerplate.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Subcategory extends SugarRecord implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer subCategoryId;
    @SerializedName("cat_id")
    @Expose
    private String catId;
    @SerializedName("name")
    @Expose
    private String name;

    public Subcategory() {
    }

    public Subcategory(Integer subCategoryId, String catId, String name) {
        this.subCategoryId = subCategoryId;
        this.catId = catId;
        this.name = name;
    }

    protected Subcategory(Parcel in) {
        if (in.readByte() == 0) {
            subCategoryId = null;
        } else {
            subCategoryId = in.readInt();
        }
        catId = in.readString();
        name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (subCategoryId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(subCategoryId);
        }
        dest.writeString(catId);
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Subcategory> CREATOR = new Creator<Subcategory>() {
        @Override
        public Subcategory createFromParcel(Parcel in) {
            return new Subcategory(in);
        }

        @Override
        public Subcategory[] newArray(int size) {
            return new Subcategory[size];
        }
    };

    public Integer getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(Integer id) {
        this.subCategoryId = id;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("subCategoryId", subCategoryId).append("catId", catId).append("name", name).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(subCategoryId).append(name).append(catId).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Subcategory)) {
            return false;
        }
        Subcategory rhs = ((Subcategory) other);
        return new EqualsBuilder().append(subCategoryId, rhs.subCategoryId).append(name, rhs.name).append(catId, rhs.catId).isEquals();
    }

}
