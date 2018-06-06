
package uk.co.ribot.androidboilerplate.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Offerproduct implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("cat_id")
    @Expose
    private String catId;
    @SerializedName("subcat_id")
    @Expose
    private String subcatId;
    @SerializedName("extrasubcat_id")
    @Expose
    private String extrasubcatId;
    @SerializedName("brandname_id")
    @Expose
    private String brandnameId;
    @SerializedName("available")
    @Expose
    private String available;
    @SerializedName("isOffer")
    @Expose
    private String isOffer;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("offer_Price")
    @Expose
    private String offerPrice;
    @SerializedName("brand_name")
    @Expose
    private String brandName;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("subcategory")
    @Expose
    private String subcategory;
    @SerializedName("extracategory")
    @Expose
    private String extracategory;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    public final static Creator<Offerproduct> CREATOR = new Creator<Offerproduct>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Offerproduct createFromParcel(Parcel in) {
            return new Offerproduct(in);
        }

        public Offerproduct[] newArray(int size) {
            return (new Offerproduct[size]);
        }

    }
    ;

    protected Offerproduct(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.userId = ((String) in.readValue((String.class.getClassLoader())));
        this.catId = ((String) in.readValue((String.class.getClassLoader())));
        this.subcatId = ((String) in.readValue((String.class.getClassLoader())));
        this.extrasubcatId = ((String) in.readValue((String.class.getClassLoader())));
        this.brandnameId = ((String) in.readValue((String.class.getClassLoader())));
        this.available = ((String) in.readValue((String.class.getClassLoader())));
        this.isOffer = ((String) in.readValue((String.class.getClassLoader())));
        this.image = ((String) in.readValue((String.class.getClassLoader())));
        this.price = ((String) in.readValue((String.class.getClassLoader())));
        this.offerPrice = ((String) in.readValue((String.class.getClassLoader())));
        this.brandName = ((String) in.readValue((String.class.getClassLoader())));
        this.category = ((String) in.readValue((String.class.getClassLoader())));
        this.subcategory = ((String) in.readValue((String.class.getClassLoader())));
        this.extracategory = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Offerproduct() {
    }

    /**
     * 
     * @param subcatId
     * @param image
     * @param brandnameId
     * @param available
     * @param brandName
     * @param id
     * @param extrasubcatId
     * @param isOffer
     * @param category
     * @param extracategory
     * @param price
     * @param description
     * @param subcategory
     * @param name
     * @param userId
     * @param offerPrice
     * @param catId
     */
    public Offerproduct(Integer id, String userId, String catId, String subcatId, String extrasubcatId, String brandnameId, String available, String isOffer, String image, String price, String offerPrice, String brandName, String category, String subcategory, String extracategory, String name, String description) {
        super();
        this.id = id;
        this.userId = userId;
        this.catId = catId;
        this.subcatId = subcatId;
        this.extrasubcatId = extrasubcatId;
        this.brandnameId = brandnameId;
        this.available = available;
        this.isOffer = isOffer;
        this.image = image;
        this.price = price;
        this.offerPrice = offerPrice;
        this.brandName = brandName;
        this.category = category;
        this.subcategory = subcategory;
        this.extracategory = extracategory;
        this.name = name;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getSubcatId() {
        return subcatId;
    }

    public void setSubcatId(String subcatId) {
        this.subcatId = subcatId;
    }

    public String getExtrasubcatId() {
        return extrasubcatId;
    }

    public void setExtrasubcatId(String extrasubcatId) {
        this.extrasubcatId = extrasubcatId;
    }

    public String getBrandnameId() {
        return brandnameId;
    }

    public void setBrandnameId(String brandnameId) {
        this.brandnameId = brandnameId;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getIsOffer() {
        return isOffer;
    }

    public void setIsOffer(String isOffer) {
        this.isOffer = isOffer;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(String offerPrice) {
        this.offerPrice = offerPrice;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public String getExtracategory() {
        return extracategory;
    }

    public void setExtracategory(String extracategory) {
        this.extracategory = extracategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("userId", userId).append("catId", catId).append("subcatId", subcatId).append("extrasubcatId", extrasubcatId).append("brandnameId", brandnameId).append("available", available).append("isOffer", isOffer).append("image", image).append("price", price).append("offerPrice", offerPrice).append("brandName", brandName).append("category", category).append("subcategory", subcategory).append("extracategory", extracategory).append("name", name).append("description", description).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(subcatId).append(image).append(brandnameId).append(available).append(brandName).append(isOffer).append(extrasubcatId).append(id).append(category).append(extracategory).append(price).append(description).append(subcategory).append(name).append(offerPrice).append(userId).append(catId).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Offerproduct) == false) {
            return false;
        }
        Offerproduct rhs = ((Offerproduct) other);
        return new EqualsBuilder().append(subcatId, rhs.subcatId).append(image, rhs.image).append(brandnameId, rhs.brandnameId).append(available, rhs.available).append(brandName, rhs.brandName).append(isOffer, rhs.isOffer).append(extrasubcatId, rhs.extrasubcatId).append(id, rhs.id).append(category, rhs.category).append(extracategory, rhs.extracategory).append(price, rhs.price).append(description, rhs.description).append(subcategory, rhs.subcategory).append(name, rhs.name).append(offerPrice, rhs.offerPrice).append(userId, rhs.userId).append(catId, rhs.catId).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(userId);
        dest.writeValue(catId);
        dest.writeValue(subcatId);
        dest.writeValue(extrasubcatId);
        dest.writeValue(brandnameId);
        dest.writeValue(available);
        dest.writeValue(isOffer);
        dest.writeValue(image);
        dest.writeValue(price);
        dest.writeValue(offerPrice);
        dest.writeValue(brandName);
        dest.writeValue(category);
        dest.writeValue(subcategory);
        dest.writeValue(extracategory);
        dest.writeValue(name);
        dest.writeValue(description);
    }

    public int describeContents() {
        return  0;
    }

}
