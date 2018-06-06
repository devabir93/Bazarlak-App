
package uk.co.ribot.androidboilerplate.data.model;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class  Subcategory extends SugarRecord implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer subCategoryId;
    @SerializedName("cat_id")
    @Expose
    private String catId;
    @SerializedName("extracategory")
    @Expose
    private List<Extracategory> extracategory = null;
    @SerializedName("name")
    @Expose
    private String name;

    public final static Creator<Subcategory> CREATOR = new Creator<Subcategory>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Subcategory createFromParcel(Parcel in) {
            return new Subcategory(in);
        }

        public Subcategory[] newArray(int size) {
            return (new Subcategory[size]);
        }

    }
    ;

    protected Subcategory(Parcel in) {
        this.subCategoryId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.catId = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.extracategory, (Extracategory.class.getClassLoader()));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Subcategory() {
    }

    /**
     * 
     * @param id
     * @param extracategory
     * @param name
     * @param catId
     */
    public Subcategory(Integer id, String catId, List<Extracategory> extracategory, String name) {
        super();
        this.subCategoryId = id;
        this.catId = catId;
        this.extracategory = extracategory;
        this.name = name;
    }

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

    public List<Extracategory> getExtracategory() {
        return extracategory;
    }

    public void setExtracategory(List<Extracategory> extracategory) {
        this.extracategory = extracategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("subCategoryId", subCategoryId).append("catId", catId).append("extracategory", extracategory).append("name", name).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(subCategoryId).append(extracategory).append(name).append(catId).toHashCode();
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
        return new EqualsBuilder().append(subCategoryId, rhs.subCategoryId).append(extracategory, rhs.extracategory).append(name, rhs.name).append(catId, rhs.catId).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(subCategoryId);
        dest.writeValue(catId);
        dest.writeList(extracategory);
        dest.writeValue(name);
    }

    public int describeContents() {
        return  0;
    }
}
