
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

public class Product extends SugarRecord implements Parcelable {

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
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("price_over")
    @Expose
    private String priceOver;
    @SerializedName("number_peces")
    @Expose
    private String numberPeces;
    @SerializedName("available")
    @Expose
    private String available;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("is_favourite")
    @Expose
    private Integer isFavourite;
    @SerializedName("is_rate")
    @Expose
    private Integer isRate;
    @SerializedName("user_name")
    @Expose
    private String userName;
    @SerializedName("color_features")
    @Expose
    private List<ColorFeature> colorFeatures = null;
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
    @SerializedName("price_min")
    @Expose
    private Object priceMin;
    @SerializedName("offer_Price")
    @Expose
    private Integer offerPrice;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;

    public Product() {
    }

    protected Product(Parcel in) {
        if (in.readByte() == 0) {
            productId = null;
        } else {
            productId = in.readInt();
        }
        userId = in.readString();
        catId = in.readString();
        subcatId = in.readString();
        extrasubcatId = in.readString();
        brandnameId = in.readString();
        price = in.readString();
        priceOver = in.readString();
        numberPeces = in.readString();
        available = in.readString();
        image = in.readString();
        status = in.readString();
        if (in.readByte() == 0) {
            isFavourite = null;
        } else {
            isFavourite = in.readInt();
        }
        if (in.readByte() == 0) {
            isRate = null;
        } else {
            isRate = in.readInt();
        }
        userName = in.readString();
        nameEn = in.readString();
        nameAr = in.readString();
        descriptionEn = in.readString();
        descriptionAr = in.readString();
        if (in.readByte() == 0) {
            offerPrice = null;
        } else {
            offerPrice = in.readInt();
        }
        name = in.readString();
        description = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (productId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(productId);
        }
        dest.writeString(userId);
        dest.writeString(catId);
        dest.writeString(subcatId);
        dest.writeString(extrasubcatId);
        dest.writeString(brandnameId);
        dest.writeString(price);
        dest.writeString(priceOver);
        dest.writeString(numberPeces);
        dest.writeString(available);
        dest.writeString(image);
        dest.writeString(status);
        if (isFavourite == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(isFavourite);
        }
        if (isRate == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(isRate);
        }
        dest.writeString(userName);
        dest.writeString(nameEn);
        dest.writeString(nameAr);
        dest.writeString(descriptionEn);
        dest.writeString(descriptionAr);
        if (offerPrice == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(offerPrice);
        }
        dest.writeString(name);
        dest.writeString(description);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPriceOver() {
        return priceOver;
    }

    public void setPriceOver(String priceOver) {
        this.priceOver = priceOver;
    }

    public String getNumberPeces() {
        return numberPeces;
    }

    public void setNumberPeces(String numberPeces) {
        this.numberPeces = numberPeces;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public List<ColorFeature> getColorFeatures() {
        return colorFeatures;
    }

    public void setColorFeatures(List<ColorFeature> colorFeatures) {
        this.colorFeatures = colorFeatures;
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

    public Object getPriceMin() {
        return priceMin;
    }

    public void setPriceMin(Object priceMin) {
        this.priceMin = priceMin;
    }

    public Integer getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(Integer offerPrice) {
        this.offerPrice = offerPrice;
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
        return new ToStringBuilder(this).append("productId", productId).append("userId", userId).append("catId", catId).append("subcatId", subcatId).append("extrasubcatId", extrasubcatId).append("brandnameId", brandnameId).append("price", price).append("priceOver", priceOver).append("numberPeces", numberPeces).append("available", available).append("image", image).append("status", status).append("isFavourite", isFavourite).append("isRate", isRate).append("userName", userName).append("colorFeatures", colorFeatures).append("nameEn", nameEn).append("nameAr", nameAr).append("descriptionEn", descriptionEn).append("descriptionAr", descriptionAr).append("priceMin", priceMin).append("offerPrice", offerPrice).append("name", name).append("description", description).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(isRate).append(subcatId).append(descriptionEn).append(descriptionAr).append(productId).append(priceMin).append(description).append(name).append(userId).append(isFavourite).append(userName).append(colorFeatures).append(status).append(nameEn).append(nameAr).append(image).append(brandnameId).append(available).append(extrasubcatId).append(priceOver).append(price).append(offerPrice).append(catId).append(numberPeces).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Product)) {
            return false;
        }
        Product rhs = ((Product) other);
        return new EqualsBuilder().append(isRate, rhs.isRate).append(subcatId, rhs.subcatId).append(descriptionEn, rhs.descriptionEn).append(descriptionAr, rhs.descriptionAr).append(productId, rhs.productId).append(priceMin, rhs.priceMin).append(description, rhs.description).append(name, rhs.name).append(userId, rhs.userId).append(isFavourite, rhs.isFavourite).append(userName, rhs.userName).append(colorFeatures, rhs.colorFeatures).append(status, rhs.status).append(nameEn, rhs.nameEn).append(nameAr, rhs.nameAr).append(image, rhs.image).append(brandnameId, rhs.brandnameId).append(available, rhs.available).append(extrasubcatId, rhs.extrasubcatId).append(priceOver, rhs.priceOver).append(price, rhs.price).append(offerPrice, rhs.offerPrice).append(catId, rhs.catId).append(numberPeces, rhs.numberPeces).isEquals();
    }

}
