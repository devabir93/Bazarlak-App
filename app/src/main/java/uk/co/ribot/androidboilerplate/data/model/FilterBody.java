package uk.co.ribot.androidboilerplate.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FilterBody {
    @SerializedName("subcategory")
    @Expose
    private String category;

    @SerializedName("extracategory")
    @Expose
    private String extracategory;
    @SerializedName("brand")
    @Expose
    private String brand;
    @SerializedName("size")
    @Expose
    private String size;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("price")
    @Expose
    private String price;

    public FilterBody() {
    }

    public FilterBody(String category,String extracategory, String brand, String size, String color, String price) {
        this.category = category;
        this.extracategory=extracategory;
        this.brand = brand;
        this.size = size;
        this.color = color;
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getExtracategory() {
        return extracategory;
    }

    public void setExtracategory(String extracategory) {
        this.extracategory = extracategory;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
