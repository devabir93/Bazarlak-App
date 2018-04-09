
package uk.co.ribot.androidboilerplate.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Category extends SugarRecord implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer categoryId;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("subcategory")
    @Expose
    private List<Subcategory> subcategory = null;
    @SerializedName("extrasubcategory")
    @Expose
    private List<Extrasubcategory> extrasubcategory = null;
    @SerializedName("products")
    @Expose
    private List<Product> products = null;
    @SerializedName("name")
    @Expose
    private String name;

    public Category() {
    }

    protected Category(Parcel in) {
        if (in.readByte() == 0) {
            categoryId = null;
        } else {
            categoryId = in.readInt();
        }
        image = in.readString();
        subcategory = in.createTypedArrayList(Subcategory.CREATOR);
        name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (categoryId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(categoryId);
        }
        dest.writeString(image);
        dest.writeTypedList(subcategory);
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

    @Override
    public Long getId() {
        return super.getId();
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Subcategory> getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(List<Subcategory> subcategory) {
        this.subcategory = subcategory;
    }

    public List<Extrasubcategory> getExtrasubcategory() {
        return extrasubcategory;
    }

    public void setExtrasubcategory(List<Extrasubcategory> extrasubcategory) {
        this.extrasubcategory = extrasubcategory;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("categoryId", categoryId).append("image", image).append("subcategory", subcategory).append("extrasubcategory", extrasubcategory).append("products", products).append("name", name).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(categoryId).append(subcategory).append(name).append(image).append(extrasubcategory).append(products).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Category)) {
            return false;
        }
        Category rhs = ((Category) other);
        return new EqualsBuilder().append(categoryId, rhs.categoryId).append(subcategory, rhs.subcategory).append(name, rhs.name).append(image, rhs.image).append(extrasubcategory, rhs.extrasubcategory).append(products, rhs.products).isEquals();
    }

}
