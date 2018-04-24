
package uk.co.ribot.androidboilerplate.data.model;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Category extends SugarRecord implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer categoryId;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("subcategory")
    @Expose
    private List<Subcategory> subcategory = null;
    @SerializedName("name")
    @Expose
    private String name;
    public final static Creator<Category> CREATOR = new Creator<Category>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        public Category[] newArray(int size) {
            return (new Category[size]);
        }

    }
    ;

    protected Category(Parcel in) {
        this.categoryId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.image = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.subcategory, (Subcategory.class.getClassLoader()));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Category() {
    }

    /**
     * 
     * @param id
     * @param subcategory
     * @param name
     * @param image
     */
    public Category(Integer id, String image, List<Subcategory> subcategory, String name) {
        super();
        this.categoryId = id;
        this.image = image;
        this.subcategory = subcategory;
        this.name = name;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer id) {
        this.categoryId = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("categoryId", categoryId).append("image", image).append("subcategory", subcategory).append("name", name).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(categoryId).append(subcategory).append(name).append(image).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Category) == false) {
            return false;
        }
        Category rhs = ((Category) other);
        return new EqualsBuilder().append(categoryId, rhs.categoryId).append(subcategory, rhs.subcategory).append(name, rhs.name).append(image, rhs.image).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(categoryId);
        dest.writeValue(image);
        dest.writeList(subcategory);
        dest.writeValue(name);
    }

    public int describeContents() {
        return  0;
    }

}
