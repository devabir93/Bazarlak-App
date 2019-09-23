package uk.co.ribot.androidboilerplate.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CartBody {
    @SerializedName("cart")
    @Expose
    private String addToCartBody;


    public CartBody() {
    }

    public CartBody(String addToCartBody) {
        this.addToCartBody = addToCartBody;
    }

    public String getAddToCartBody() {
        return addToCartBody;
    }

    public void setAddToCartBody(String addToCartBody) {
        this.addToCartBody = addToCartBody;
    }

}
