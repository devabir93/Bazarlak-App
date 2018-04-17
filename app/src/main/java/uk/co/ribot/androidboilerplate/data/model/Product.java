
package uk.co.ribot.androidboilerplate.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import java.util.Objects;

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

    private String savedQuantity;

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
        colorFeatures = in.createTypedArrayList(ColorFeature.CREATOR);
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
        savedQuantity = in.readString();
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
        dest.writeTypedList(colorFeatures);
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
        dest.writeString(savedQuantity);
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


    public String getSavedQuantity() {
        return savedQuantity;
    }

    public void setSavedQuantity(String savedQuantity) {
        this.savedQuantity = savedQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (productId != null ? !productId.equals(product.productId) : product.productId != null)
            return false;
        if (userId != null ? !userId.equals(product.userId) : product.userId != null) return false;
        if (catId != null ? !catId.equals(product.catId) : product.catId != null) return false;
        if (subcatId != null ? !subcatId.equals(product.subcatId) : product.subcatId != null)
            return false;
        if (extrasubcatId != null ? !extrasubcatId.equals(product.extrasubcatId) : product.extrasubcatId != null)
            return false;
        if (brandnameId != null ? !brandnameId.equals(product.brandnameId) : product.brandnameId != null)
            return false;
        if (price != null ? !price.equals(product.price) : product.price != null) return false;
        if (priceOver != null ? !priceOver.equals(product.priceOver) : product.priceOver != null)
            return false;
        if (numberPeces != null ? !numberPeces.equals(product.numberPeces) : product.numberPeces != null)
            return false;
        if (available != null ? !available.equals(product.available) : product.available != null)
            return false;
        if (image != null ? !image.equals(product.image) : product.image != null) return false;
        if (status != null ? !status.equals(product.status) : product.status != null) return false;
        if (isFavourite != null ? !isFavourite.equals(product.isFavourite) : product.isFavourite != null)
            return false;
        if (isRate != null ? !isRate.equals(product.isRate) : product.isRate != null) return false;
        if (userName != null ? !userName.equals(product.userName) : product.userName != null)
            return false;
        if (colorFeatures != null ? !colorFeatures.equals(product.colorFeatures) : product.colorFeatures != null)
            return false;
        if (nameEn != null ? !nameEn.equals(product.nameEn) : product.nameEn != null) return false;
        if (nameAr != null ? !nameAr.equals(product.nameAr) : product.nameAr != null) return false;
        if (descriptionEn != null ? !descriptionEn.equals(product.descriptionEn) : product.descriptionEn != null)
            return false;
        if (descriptionAr != null ? !descriptionAr.equals(product.descriptionAr) : product.descriptionAr != null)
            return false;
        if (priceMin != null ? !priceMin.equals(product.priceMin) : product.priceMin != null)
            return false;
        if (offerPrice != null ? !offerPrice.equals(product.offerPrice) : product.offerPrice != null)
            return false;
        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        if (description != null ? !description.equals(product.description) : product.description != null)
            return false;
        return savedQuantity != null ? savedQuantity.equals(product.savedQuantity) : product.savedQuantity == null;
    }

    @Override
    public int hashCode() {
        int result = productId != null ? productId.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (catId != null ? catId.hashCode() : 0);
        result = 31 * result + (subcatId != null ? subcatId.hashCode() : 0);
        result = 31 * result + (extrasubcatId != null ? extrasubcatId.hashCode() : 0);
        result = 31 * result + (brandnameId != null ? brandnameId.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (priceOver != null ? priceOver.hashCode() : 0);
        result = 31 * result + (numberPeces != null ? numberPeces.hashCode() : 0);
        result = 31 * result + (available != null ? available.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (isFavourite != null ? isFavourite.hashCode() : 0);
        result = 31 * result + (isRate != null ? isRate.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (colorFeatures != null ? colorFeatures.hashCode() : 0);
        result = 31 * result + (nameEn != null ? nameEn.hashCode() : 0);
        result = 31 * result + (nameAr != null ? nameAr.hashCode() : 0);
        result = 31 * result + (descriptionEn != null ? descriptionEn.hashCode() : 0);
        result = 31 * result + (descriptionAr != null ? descriptionAr.hashCode() : 0);
        result = 31 * result + (priceMin != null ? priceMin.hashCode() : 0);
        result = 31 * result + (offerPrice != null ? offerPrice.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (savedQuantity != null ? savedQuantity.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", userId='" + userId + '\'' +
                ", catId='" + catId + '\'' +
                ", subcatId='" + subcatId + '\'' +
                ", extrasubcatId='" + extrasubcatId + '\'' +
                ", brandnameId='" + brandnameId + '\'' +
                ", price='" + price + '\'' +
                ", priceOver='" + priceOver + '\'' +
                ", numberPeces='" + numberPeces + '\'' +
                ", available='" + available + '\'' +
                ", image='" + image + '\'' +
                ", status='" + status + '\'' +
                ", isFavourite=" + isFavourite +
                ", isRate=" + isRate +
                ", userName='" + userName + '\'' +
                ", colorFeatures=" + colorFeatures +
                ", nameEn='" + nameEn + '\'' +
                ", nameAr='" + nameAr + '\'' +
                ", descriptionEn='" + descriptionEn + '\'' +
                ", descriptionAr='" + descriptionAr + '\'' +
                ", priceMin=" + priceMin +
                ", offerPrice=" + offerPrice +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", savedQuantity='" + savedQuantity + '\'' +
                '}';
    }
}
