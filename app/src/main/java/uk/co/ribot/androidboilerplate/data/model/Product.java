
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

public class Product extends SugarRecord implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer productId;
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
    @SerializedName("is_favourite")
    @Expose
    private Integer isFavourite;
    @SerializedName("is_rate")
    @Expose
    private Integer isRate;
    @SerializedName("user_name")
    @Expose
    private String userName;
    @SerializedName("product_features")
    @Expose
    private List<ProductFeature> productFeatures = null;
    @SerializedName("name_en")
    @Expose
    private String nameEn;
    @SerializedName("name_ar")
    @Expose
    private String nameAr;
    @SerializedName("description_en")
    @Expose
    private String descriptionEn;
    @SerializedName("description_ar")
    @Expose
    private String descriptionAr;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("offer_Price")
    @Expose
    private String offerPrice;
    @SerializedName("images")
    @Expose
    private List<Image> images = null;
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
    public final static Creator<Product> CREATOR = new Creator<Product>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        public Product[] newArray(int size) {
            return (new Product[size]);
        }

    }
    ;

    protected Product(Parcel in) {
        this.productId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.userId = ((String) in.readValue((String.class.getClassLoader())));
        this.catId = ((String) in.readValue((String.class.getClassLoader())));
        this.subcatId = ((String) in.readValue((String.class.getClassLoader())));
        this.extrasubcatId = ((String) in.readValue((String.class.getClassLoader())));
        this.brandnameId = ((String) in.readValue((String.class.getClassLoader())));
        this.available = ((String) in.readValue((String.class.getClassLoader())));
        this.isOffer = ((String) in.readValue((String.class.getClassLoader())));
        this.image = ((String) in.readValue((String.class.getClassLoader())));
        this.isFavourite = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.isRate = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.userName = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.productFeatures, (ProductFeature.class.getClassLoader()));
        this.nameEn = ((String) in.readValue((String.class.getClassLoader())));
        this.nameAr = ((String) in.readValue((String.class.getClassLoader())));
        this.descriptionEn = ((String) in.readValue((String.class.getClassLoader())));
        this.descriptionAr = ((String) in.readValue((String.class.getClassLoader())));
        this.price = ((String) in.readValue((String.class.getClassLoader())));
        this.offerPrice = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.images, (Image.class.getClassLoader()));
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
    public Product() {
    }

    /**
     * 
     * @param isRate
     * @param subcatId
     * @param descriptionEn
     * @param descriptionAr
     * @param brandName
     * @param id
     * @param productFeatures
     * @param description
     * @param name
     * @param userId
     * @param isFavourite
     * @param userName
     * @param nameEn
     * @param nameAr
     * @param image
     * @param brandnameId
     * @param available
     * @param isOffer
     * @param extrasubcatId
     * @param category
     * @param price
     * @param extracategory
     * @param subcategory
     * @param offerPrice
     * @param images
     * @param catId
     */
    public Product(Integer id, String userId, String catId, String subcatId, String extrasubcatId, String brandnameId, String available, String isOffer, String image, Integer isFavourite, Integer isRate, String userName, List<ProductFeature> productFeatures, String nameEn, String nameAr, String descriptionEn, String descriptionAr, String price, String offerPrice, List<Image> images, String brandName, String category, String subcategory, String extracategory, String name, String description) {
        super();
        this.productId = id;
        this.userId = userId;
        this.catId = catId;
        this.subcatId = subcatId;
        this.extrasubcatId = extrasubcatId;
        this.brandnameId = brandnameId;
        this.available = available;
        this.isOffer = isOffer;
        this.image = image;
        this.isFavourite = isFavourite;
        this.isRate = isRate;
        this.userName = userName;
        this.productFeatures = productFeatures;
        this.nameEn = nameEn;
        this.nameAr = nameAr;
        this.descriptionEn = descriptionEn;
        this.descriptionAr = descriptionAr;
        this.price = price;
        this.offerPrice = offerPrice;
        this.images = images;
        this.brandName = brandName;
        this.category = category;
        this.subcategory = subcategory;
        this.extracategory = extracategory;
        this.name = name;
        this.description = description;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer id) {
        this.productId = id;
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

    public Integer getIsFavourite() {
        return isFavourite;
    }

    public void setIsFavourite(Integer isFavourite) {
        this.isFavourite = isFavourite;
    }

    public Integer getIsRate() {
        return isRate;
    }

    public void setIsRate(Integer isRate) {
        this.isRate = isRate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<ProductFeature> getProductFeatures() {
        return productFeatures;
    }

    public void setProductFeatures(List<ProductFeature> productFeatures) {
        this.productFeatures = productFeatures;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameAr() {
        return nameAr;
    }

    public void setNameAr(String nameAr) {
        this.nameAr = nameAr;
    }

    public String getDescriptionEn() {
        return descriptionEn;
    }

    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    public String getDescriptionAr() {
        return descriptionAr;
    }

    public void setDescriptionAr(String descriptionAr) {
        this.descriptionAr = descriptionAr;
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

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
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
        return new ToStringBuilder(this).append("productId", productId).append("userId", userId).append("catId", catId).append("subcatId", subcatId).append("extrasubcatId", extrasubcatId).append("brandnameId", brandnameId).append("available", available).append("isOffer", isOffer).append("image", image).append("isFavourite", isFavourite).append("isRate", isRate).append("userName", userName).append("productFeatures", productFeatures).append("nameEn", nameEn).append("nameAr", nameAr).append("descriptionEn", descriptionEn).append("descriptionAr", descriptionAr).append("price", price).append("offerPrice", offerPrice).append("images", images).append("brandName", brandName).append("category", category).append("subcategory", subcategory).append("extracategory", extracategory).append("name", name).append("description", description).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(isRate).append(subcatId).append(descriptionEn).append(descriptionAr).append(brandName).append(productId).append(productFeatures).append(description).append(name).append(userId).append(isFavourite).append(userName).append(nameEn).append(nameAr).append(image).append(brandnameId).append(available).append(isOffer).append(extrasubcatId).append(category).append(extracategory).append(price).append(subcategory).append(offerPrice).append(images).append(catId).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Product) == false) {
            return false;
        }
        Product rhs = ((Product) other);
        return new EqualsBuilder().append(isRate, rhs.isRate).append(subcatId, rhs.subcatId).append(descriptionEn, rhs.descriptionEn).append(descriptionAr, rhs.descriptionAr).append(brandName, rhs.brandName).append(productId, rhs.productId).append(productFeatures, rhs.productFeatures).append(description, rhs.description).append(name, rhs.name).append(userId, rhs.userId).append(isFavourite, rhs.isFavourite).append(userName, rhs.userName).append(nameEn, rhs.nameEn).append(nameAr, rhs.nameAr).append(image, rhs.image).append(brandnameId, rhs.brandnameId).append(available, rhs.available).append(isOffer, rhs.isOffer).append(extrasubcatId, rhs.extrasubcatId).append(category, rhs.category).append(extracategory, rhs.extracategory).append(price, rhs.price).append(subcategory, rhs.subcategory).append(offerPrice, rhs.offerPrice).append(images, rhs.images).append(catId, rhs.catId).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(productId);
        dest.writeValue(userId);
        dest.writeValue(catId);
        dest.writeValue(subcatId);
        dest.writeValue(extrasubcatId);
        dest.writeValue(brandnameId);
        dest.writeValue(available);
        dest.writeValue(isOffer);
        dest.writeValue(image);
        dest.writeValue(isFavourite);
        dest.writeValue(isRate);
        dest.writeValue(userName);
        dest.writeList(productFeatures);
        dest.writeValue(nameEn);
        dest.writeValue(nameAr);
        dest.writeValue(descriptionEn);
        dest.writeValue(descriptionAr);
        dest.writeValue(price);
        dest.writeValue(offerPrice);
        dest.writeList(images);
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
