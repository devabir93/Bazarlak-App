package uk.co.ribot.androidboilerplate.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductBody {
    @SerializedName("category")
    @Expose
    String category;
    @SerializedName("subcategory")
    @Expose
    String subcategory;
    @SerializedName("extracategory")
    @Expose
    String extracategory;

    public ProductBody() {
    }

    public ProductBody(String category, String subcategory, String extracategory) {
        this.category = category;
        this.subcategory = subcategory;
        this.extracategory = extracategory;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductBody that = (ProductBody) o;

        if (category != null ? !category.equals(that.category) : that.category != null)
            return false;
        if (subcategory != null ? !subcategory.equals(that.subcategory) : that.subcategory != null)
            return false;
        return extracategory != null ? extracategory.equals(that.extracategory) : that.extracategory == null;
    }

    @Override
    public int hashCode() {
        int result = category != null ? category.hashCode() : 0;
        result = 31 * result + (subcategory != null ? subcategory.hashCode() : 0);
        result = 31 * result + (extracategory != null ? extracategory.hashCode() : 0);
        return result;
    }

    @Override
    public String
    toString() {
        return "ProductBody{" +
                "category='" + category + '\'' +
                ", subcategory='" + subcategory + '\'' +
                ", extracategory='" + extracategory + '\'' +
                '}';
    }
}
