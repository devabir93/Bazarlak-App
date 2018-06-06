
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
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("for_shoes")
    @Expose
    private String forShoes;
    public Extracategory() {
    }

    public Extracategory(Integer extracategoryId, String catId, String subcategoryId, String name, String image, String forShoes) {
        ExtracategoryId = extracategoryId;
        this.catId = catId;
        this.subcategoryId = subcategoryId;
        this.name = name;
        this.image = image;
        this.forShoes = forShoes;
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
        image = in.readString();
        forShoes = in.readString();
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
        dest.writeString(image);
        dest.writeString(forShoes);
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

    @Override
    public String toString() {
        return "Extracategory{" +
                "ExtracategoryId=" + ExtracategoryId +
                ", catId='" + catId + '\'' +
                ", subcategoryId='" + subcategoryId + '\'' +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", forShoes='" + forShoes + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Extracategory that = (Extracategory) o;

        if (ExtracategoryId != null ? !ExtracategoryId.equals(that.ExtracategoryId) : that.ExtracategoryId != null)
            return false;
        if (catId != null ? !catId.equals(that.catId) : that.catId != null) return false;
        if (subcategoryId != null ? !subcategoryId.equals(that.subcategoryId) : that.subcategoryId != null)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (image != null ? !image.equals(that.image) : that.image != null) return false;
        return forShoes != null ? forShoes.equals(that.forShoes) : that.forShoes == null;
    }

    @Override
    public int hashCode() {
        int result = ExtracategoryId != null ? ExtracategoryId.hashCode() : 0;
        result = 31 * result + (catId != null ? catId.hashCode() : 0);
        result = 31 * result + (subcategoryId != null ? subcategoryId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (forShoes != null ? forShoes.hashCode() : 0);
        return result;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getForShoes() {
        return forShoes;
    }

    public void setForShoes(String forShoes) {
        this.forShoes = forShoes;
    }

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

}
